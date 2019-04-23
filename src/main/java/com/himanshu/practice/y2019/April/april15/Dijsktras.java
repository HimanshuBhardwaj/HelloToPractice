package com.himanshu.practice.y2019.April.april15;

import lombok.AllArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * Created by himanshubhardwaj on 14/04/19.
 * started: 12:05 pm
 */

/*
5 7
0 1 1
0 3 9
1 2 5
2 3 1
3 4 1
4 2 3
1 4 4
* */

public class Dijsktras {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);

        Graph g = new Graph(n);

        for (int i = 0; i < m; i++) {
            str = br.readLine().split(" ");
            int s = Integer.parseInt(str[0]);
            int d = Integer.parseInt(str[1]);
            int w = Integer.parseInt(str[2]);

            g.insert(s, d, w);
        }

        g.printGraph();
        System.out.println();
        System.out.println();
        System.out.println(g.shortestPAth(0));

    }
}


//Directed
class Graph {
    ArrayList<Edge> adjList[];
    int size;

    public Graph(int size) {
        adjList = new ArrayList[size];
        this.size = size;

        for (int i = 0; i < size; i++) {
            adjList[i] = new ArrayList<>();
        }
    }

    void insert(int source, int destination, int weight) {
        Edge e = new Edge(source, destination, weight);
        adjList[source].add(e);
    }


    //dijkstra's
    LinkedList<Integer> shortestPAth(int source) {
        Node[] graphNodes = new Node[size];
        PriorityQueue<Node> nerarestNodes = new PriorityQueue<>();

        for (int i = 0; i < graphNodes.length; i++) {
            graphNodes[i] = new Node(i, Integer.MAX_VALUE);
        }

        graphNodes[source].shortestDistance = 0;
        nerarestNodes.add(graphNodes[source]);

        while (!nerarestNodes.isEmpty()) {
            Node nearest = nerarestNodes.poll();

            for (Edge e : adjList[nearest.index]) {
                if (graphNodes[e.destination].shortestDistance == Integer.MAX_VALUE) {
                    nerarestNodes.remove(graphNodes[e.destination]);
                    graphNodes[e.destination].shortestDistance = nearest.shortestDistance + e.weight;
                    nerarestNodes.add(graphNodes[e.destination]);
                } else if (graphNodes[e.destination].shortestDistance > (nearest.shortestDistance + e.weight)) {
                    nerarestNodes.remove(graphNodes[e.destination]);
                    graphNodes[e.destination].shortestDistance = nearest.shortestDistance + e.weight;
                    nerarestNodes.add(graphNodes[e.destination]);
                    //overflow alert
                }
            }
        }

        LinkedList<Integer> shortestDistances = new LinkedList<>();

        for (Node n : graphNodes) {
            shortestDistances.addLast(n.shortestDistance);
        }

        return shortestDistances;
    }


    void printGraph() {
        for (int i = 0; i < size; i++) {
            System.out.print(i + ": ");
            for (Edge e : adjList[i]) {
                System.out.print(e.destination + ", ");
            }
            System.out.println();
        }
    }


}

@AllArgsConstructor
class Node implements Comparable<Node> {
    int index;
    int shortestDistance = Integer.MAX_VALUE;

    @Override
    public int compareTo(Node o) {
        return this.shortestDistance - o.shortestDistance;
    }
}


@AllArgsConstructor
class Edge {
    int source;
    int destination;
    int weight;
}
