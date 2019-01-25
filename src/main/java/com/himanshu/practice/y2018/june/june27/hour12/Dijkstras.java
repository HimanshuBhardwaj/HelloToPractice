package com.himanshu.practice.y2018.june.june27.hour12;

import lombok.AllArgsConstructor;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by Himanshu Bhardwaj on 27/06/18.
 */
//directed
public class Dijkstras {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.next());
        Graph graph = new Graph(n);


        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(sc.next(), ",");
            int source = Integer.parseInt(st.nextToken());

            while (st.hasMoreTokens()) {
                int destination = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());
                graph.insert(source, destination, weight);
            }
        }

        graph.print();
        for (int i : graph.dijkstrasSSSP(0)) {
            System.out.print(i + "\t");
        }
        System.out.println();
        System.out.println();
        for (int i : graph.bellmonfordSSSSP(0)) {
            System.out.print(i + "\t");
        }

    }

}


class Graph {
    LinkedList<Edge> adjList[];
    int numNode;

    public Graph(int nodeNum) {
        this.numNode = nodeNum;
        adjList = new LinkedList[numNode];

        for (int i = 0; i < numNode; i++) {
            adjList[i] = new LinkedList<Edge>();
        }
    }

    public void insert(int source, int destination, int weight) {
        adjList[source].addLast(new Edge(source, destination, weight));
    }

    public void print() {
        for (int i = 0; i < numNode; i++) {
            System.out.print(i + ": ");
            for (int j = 0; j < adjList[i].size(); j++) {
                System.out.print(adjList[i].get(j));
            }
            System.out.println();
        }
    }

    public int[] dijkstrasSSSP(int source) {
        System.out.println("@dijkstrasSSSP");
        int[] sssp = new int[numNode];
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        LinkedList<Node> linkedList = new LinkedList<Node>();
        for (int i = 0; i < sssp.length; i++) {
            sssp[i] = (i == source) ? 0 : Integer.MAX_VALUE;
            Node node = new Node(i, sssp[i]);
            priorityQueue.add(node);
            linkedList.add(node);
        }

        while (!priorityQueue.isEmpty()) {
            Node nearsetNode = priorityQueue.poll();
            sssp[nearsetNode.index] = nearsetNode.sssp;

            for (int i = 0; i < adjList[nearsetNode.index].size(); i++) {
                Node tempNode = new Node(adjList[nearsetNode.index].get(i).destination, 0);
                Node neighbour = linkedList.get(linkedList.indexOf(tempNode));
                if (priorityQueue.contains(neighbour) && neighbour.sssp > (nearsetNode.sssp + adjList[nearsetNode.index].get(i).weight)) {
                    priorityQueue.remove(neighbour);
                    neighbour.sssp = nearsetNode.sssp + adjList[nearsetNode.index].get(i).weight;
                    priorityQueue.add(neighbour);
                }
            }
        }
        return sssp;
    }


    public int[] bellmonfordSSSSP(int source) {
        System.out.println("@bellmonfordSSSSP");
        int[] sssp = new int[numNode];
        for (int i = 0; i < sssp.length; i++) {
            sssp[i] = (i == source) ? 0 : Integer.MAX_VALUE;
        }

        for (int i = 0; i < adjList.length; i++) {
            for (int j = 0; j < adjList.length; j++) {
                for (int k = 0; k < adjList[j].size(); k++) {
                    Edge edge = adjList[j].get(k);
                    if (sssp[adjList[j].get(k).source] != Integer.MAX_VALUE) {
                        sssp[adjList[j].get(k).destination] = Math.min(sssp[adjList[j].get(k).destination], sssp[adjList[j].get(k).source] + adjList[j].get(k).weight);
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
    int sssp;

    @Override
    public int compareTo(Node o) {
        return this.sssp - o.sssp;
    }

    public boolean equals(Object o) {
        if (o instanceof Node) {
            Node newNode = (Node) o;
            return newNode.index == this.index;
        }

        return false;
    }
}

@AllArgsConstructor
class Edge implements Comparable<Edge> {
    int source;
    int destination;
    int weight;

    public String toString() {
        return "(" + source + ", " + destination + ", " + weight + ")\t";
    }

    @Override
    public int compareTo(Edge o) {
        return this.weight - o.weight;
    }

    public boolean equals(Object o) {
        if (o instanceof Edge) {
            Edge newEdge = (Edge) o;
            return (newEdge.source == this.source && newEdge.destination == this.destination && newEdge.weight == this.weight);
        }
        return false;
    }
}
