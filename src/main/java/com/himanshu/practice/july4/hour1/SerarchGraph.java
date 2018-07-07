package com.himanshu.practice.july4.hour1;

import lombok.AllArgsConstructor;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by Himanshu Bhardwaj on 04/07/18.
 */
public class SerarchGraph {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringTokenizer st = new StringTokenizer(sc.next(), ",");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        Graph graph = new Graph(n);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(sc.next(), ",");
            int source = Integer.parseInt(st.nextToken());
            int destination = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph.insert(source, destination, weight);
        }


        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        int levels[] = new int[n];

        for (int i = 0; i < n; i++) {
            levels[i] = (i == 0) ? 0 : Integer.MAX_VALUE;
        }
        graph.BFS(queue, new int[n], levels);
        System.out.println();

//        System.out.println("Levels after BFS");
//
//        for (int i = 0; i < levels.length; i++) {
//            System.out.println(i + ", " + levels[i]);
//        }


        LinkedList<Integer> visited = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            visited.add(i, 0);
        }

        for (int i = 0; i < n; i++) {
            if (visited.get(i) == 0) {
                LinkedList<Integer> list = new LinkedList<>();
                list.addLast(i);
                graph.printAllCycle(i, visited, list);
            }
        }
    }
}


class Graph {
    LinkedList<Edge> edges;
    int numNodes;


    public Graph(int numNodes) {
        this.numNodes = numNodes;
        edges = new LinkedList<>();
    }

    public void insert(int source, int destination, int weight) {
        edges.addLast(new Edge(source, destination, weight));
    }


    public void DFS(int source, int visited[]) {
        if (visited[source] == 1) {
            System.out.println("Cycle: " + source);
            return;
        }
        if (visited[source] == 1) {
            return;
        }

        visited[source] = 1;
        System.out.print(source + ", ");

        for (int i = 0; i < edges.size(); i++) {
            Edge edge = edges.get(i);
            if (edge.source == source) {
                DFS(edge.destination, visited);
            }
        }
    }

    //print level of all nodes
    public void BFS(Queue<Integer> queue, int[] visited, int[] levels) {
        if (queue == null || queue.isEmpty()) {
            return;
        }
        int source = queue.poll();

        System.out.print(source + " ");


        for (int i = 0; i < edges.size(); i++) {
            Edge edge = edges.get(i);
            if (edge.source == source) {
                if (visited[edge.destination] == 1) {
                    continue;
                } else if (visited[edge.destination] == 2) {
                    continue;
                } else {
                    queue.add(edge.destination);
                    visited[edge.destination] = 1;
                    levels[edge.destination] = levels[source] + 1;//safe side, try t remove it and then move
                }
            }
        }

        visited[source] = 2;
        BFS(queue, visited, levels);
    }

    public void printAllCycle(int source, LinkedList<Integer> visited, LinkedList<Integer> path) {
        //System.out.println(path + "\t\t\t" + visited);
        if (visited.get(source) == 1) {
            System.out.print("Cycle:\t");
            boolean flag = false;
            for (int i : path) {
                if (i == source) {
                    flag = true;
                }
                if (flag) {
                    System.out.print(i + " ");
                }
            }
            System.out.println();
            return;
        }
        if (visited.get(source) == 2) {
            return;
        }


        visited.remove(source);
        visited.add(source, 1);

        for (int i = 0; i < edges.size(); i++) {
            Edge edge = edges.get(i);

            if (edge.source == source) {
                path.addLast(edge.destination);
                printAllCycle(edge.destination, visited, path);
                path.removeLast();
            }
        }
        visited.remove(source);
        visited.add(source, 2);
    }

}


@AllArgsConstructor
class Edge {
    int source;
    int destination;
    int weight;


    public boolean equals(Object anObject) {
        if (anObject instanceof Edge) {
            Edge newEdge = (Edge) anObject;
            if (newEdge.source == this.source && newEdge.destination == this.destination && newEdge.weight == this.weight) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return "(" + source + ", " + destination + ", " + weight + ")";
    }
}