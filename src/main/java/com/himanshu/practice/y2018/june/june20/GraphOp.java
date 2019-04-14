package com.himanshu.practice.y2018.june.june20;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by Himanshu Bhardwaj on 20/06/18.
 * dfs, bfs, hascycle
 */
public class GraphOp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nodes = Integer.parseInt(sc.next());


        Graph graph = new Graph(nodes);

        for (int i = 0; i < nodes; i++) {
            StringTokenizer st = new StringTokenizer(sc.next(), ",");
            int source = Integer.parseInt(st.nextToken());

            while (st.hasMoreTokens()) {
                int destionaton = Integer.parseInt(st.nextToken());
                graph.insert(source, destionaton);
            }
        }

        graph.printGraph();
        graph.DFS(0, new boolean[nodes]);
        System.out.println("topologicalSort done");

        Queue<Integer> queue = new LinkedList<Integer>();

        System.out.println();
        System.out.println("BFS Started");
        queue.add(0);
        graph.BFS(queue, new boolean[nodes]);
        System.out.println();
        System.out.println(graph.hasCycle(0, new int[nodes]));
    }
}


//graph is directed
class Graph {
    int numberNodes;
    LinkedList<Integer>[] adjMatrix;

    public Graph(int node) {
        numberNodes = node;
        adjMatrix = new LinkedList[node];

        for (int i = 0; i < node; i++) {
            adjMatrix[i] = new LinkedList<Integer>();
        }
    }


    public void insert(int source, int destination) {
        adjMatrix[source].add(destination);
    }

    public void printGraph() {
        for (int i = 0; i < numberNodes; i++) {
            System.out.print(i + ": ");
            for (int j = 0; j < adjMatrix[i].size(); j++) {
                System.out.print(adjMatrix[i].get(j) + ", ");
            }
            System.out.println();
        }
    }


    public void DFS(int node, boolean[] visited) {
        if (visited[node]) {
            return;
        }

        visited[node] = true;
        System.out.println("Started Exploring " + node);

        for (int i = 0; i < adjMatrix[node].size(); i++) {
            if (!visited[adjMatrix[node].get(i)]) {
                DFS(adjMatrix[node].get(i), visited);
            }
        }
        System.out.println(node + " visited");
    }

    public void BFS(Queue<Integer> queue, boolean[] visited) {
        if (queue == null || queue.isEmpty()) {
            return;
        }
        int root = queue.poll();


        for (int i = 0; i < adjMatrix[root].size(); i++) {
            if (!visited[adjMatrix[root].get(i)]) {
                queue.add(adjMatrix[root].get(i));
                visited[adjMatrix[root].get(i)] = true;
                System.out.println("PTree: " + adjMatrix[root].get(i));
            }
        }

        BFS(queue, visited);
    }

    public boolean hasCycle(int node, int[] visited) {
        if (visited[node] == 2) {
            return false;
        }
        if (visited[node] == 1) {
            return true;
        }

        visited[node] = 1;

        boolean result = false;
        for (int i = 0; i < adjMatrix[node].size(); i++) {
            result = result | hasCycle(adjMatrix[node].get(i), visited);
        }
        visited[node] = 2;
        return result;
    }


}