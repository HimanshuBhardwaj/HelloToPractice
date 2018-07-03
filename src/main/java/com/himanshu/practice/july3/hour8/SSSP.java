package com.himanshu.practice.july3.hour8;

import lombok.AllArgsConstructor;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by himanshubhardwaj on 03/07/18.
 * 8:13 am
 * Directed
 */
public class SSSP {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nodes = Integer.parseInt(sc.next());
        Graph graph = new Graph(nodes);

        for (int i = 0; i < nodes; i++) {
            StringTokenizer st = new StringTokenizer(sc.next(), ",");
            int source = Integer.parseInt(st.nextToken());
            while (st.hasMoreTokens()) {
                int destination = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());
                graph.insert(source, destination, weight);
            }
        }

        graph.print();
        int[] sssp = graph.dijkstra(0);
        System.out.println();
        for (int i = 0; i < sssp.length; i++) {
            System.out.print(sssp[i] + "\t");
        }
    }
}


class Graph {
    LinkedList<Edge> adjList[];
    int numNodes;

    public Graph(int numNode) {
        this.numNodes = numNode;
        adjList = new LinkedList[numNodes];

        for (int i = 0; i < numNode; i++) {
            adjList[i] = new LinkedList<>();
        }
    }


    public void insert(int source, int destination, int weight) {
        adjList[source].add(new Edge(source, destination, weight));
    }

    public void print() {
        for (int i = 0; i < adjList.length; i++) {
            System.out.print(i + ": ");
            for (int j = 0; j < adjList[i].size(); j++) {
                System.out.print(adjList[i].get(j));
            }
            System.out.println();
        }
    }


    public int[] dijkstra(int source) {
        int[] sssp = new int[numNodes];
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        LinkedList<Node> linkedList = new LinkedList<>();


        for (int i = 0; i < numNodes; i++) {
            Node node = new Node(i, (i == source) ? 0 : Integer.MAX_VALUE);
            System.out.println(node);
            linkedList.add(i, node);
            priorityQueue.add(node);
        }


        while (!priorityQueue.isEmpty()) {
            Node nearest = priorityQueue.poll();
            sssp[nearest.index] = nearest.distance;

            for (int i = 0; i < adjList[nearest.index].size(); i++) {
                Edge neighbourEdge = adjList[nearest.index].get(i);
                Node neighbourNode = linkedList.get(neighbourEdge.destination);
                if (nearest.distance != Integer.MAX_VALUE) {
                    if (neighbourNode.distance != Integer.MAX_VALUE) {
                        if (neighbourNode.distance > (nearest.distance + neighbourEdge.weight)) {
                            priorityQueue.remove(neighbourNode);
                            neighbourNode.distance = nearest.distance + neighbourEdge.weight;
                            priorityQueue.add(neighbourNode);
                        }
                    } else {
                        priorityQueue.remove(neighbourNode);
                        neighbourNode.distance = nearest.distance + neighbourEdge.weight;
                        priorityQueue.add(neighbourNode);
                    }
                }
            }
        }
        return sssp;
    }
}


@AllArgsConstructor
class Node implements Comparable<Node> {
    int index;
    int distance;

    @Override
    public int compareTo(Node o) {
        return this.distance - o.distance;
    }

    public boolean equals(Object var1) {
        if (var1 instanceof Node) {
            if (this.index == ((Node) var1).index) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return "(" + index + ", " + distance + ")";
    }
}

@AllArgsConstructor
class Edge implements Comparable<Edge> {
    int source;
    int destination;
    int weight;

    @Override
    public int compareTo(Edge o) {
        return this.weight - o.destination;
    }


    public boolean equals(Object var1) {
        if (var1 instanceof Edge) {
            Edge edge1 = (Edge) var1;

            if (edge1.destination == this.destination && edge1.source == this.source && this.weight == edge1.weight)

            {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return "(" + source + ", " + destination + ", " + weight + "),\t";
    }

}

