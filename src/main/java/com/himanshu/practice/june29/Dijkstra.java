package com.himanshu.practice.june29;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by Himanshu Bhardwaj on 29/06/18.
 */
public class Dijkstra {
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

        int[] dis = graph.ssssp(0);
        for (int i = 0; i < dis.length; i++) {
            System.out.print(dis[i] + "\t");
        }
        System.out.println();
    }
}


class Graph {
    LinkedList<Edge> adjList[];
    int numNodes;


    public Graph(int numNodes) {
        this.numNodes = numNodes;
        adjList = new LinkedList[numNodes];

        for (int i = 0; i < numNodes; i++) {
            adjList[i] = new LinkedList<Edge>();
        }
    }


    public void insert(int source, int destination, int weight) {
        Edge edge = new Edge(source, destination, weight);
        adjList[source].addLast(edge);
        adjList[destination].addLast(edge);
    }


    public int[] ssssp(int source) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        Node[] nodeArray = new Node[adjList.length];
        int[] sssp = new int[adjList.length];
        System.out.println();

        for (int i = 0; i < adjList.length; i++) {
            Node node = new Node(i, (i == source) ? 0 : Integer.MAX_VALUE);
            System.out.println(node);
            nodeArray[i] = node;
            priorityQueue.add(node);
            sssp[i] = node.distance;
        }

        while (!priorityQueue.isEmpty()) {
            Node node = priorityQueue.poll();
            sssp[node.index] = node.distance;

            for (int i = 0; i < adjList[node.index].size() && (node.distance != Integer.MAX_VALUE); i++) {
                Edge neighbourEdges = adjList[node.index].get(i);
                int neighbourNodeIndex = (neighbourEdges.vertex1 == node.index) ? neighbourEdges.vertex2 : neighbourEdges.vertex1;
                Node neighbourNode = nodeArray[neighbourNodeIndex];
                if (neighbourNode.distance > (node.distance + neighbourEdges.weight)) {
                    priorityQueue.remove(neighbourNode);
                    neighbourNode.distance = node.distance + neighbourEdges.weight;
                    priorityQueue.add(neighbourNode);
                }
            }
        }
        return sssp;
    }

    public void print() {
        for (int i = 0; i < adjList.length; i++) {
            System.out.print(i + ": ");
            for (int j = 0; j < adjList[i].size(); j++) {
                System.out.print(adjList[i].get(j) + "\t");
            }
            System.out.println();
        }
    }


}

@AllArgsConstructor
@ToString
class Edge implements Comparable<Edge> {
    int vertex1;
    int vertex2;
    int weight;

    @Override
    public int compareTo(Edge o) {

        return this.weight - o.weight;
    }

    public boolean equals(Object anObject) {
        if (anObject instanceof Edge) {
            Edge newEdge = (Edge) anObject;
            if (((newEdge.vertex1 == this.vertex1) && (newEdge.vertex2 == this.vertex2)) ||
                    ((newEdge.vertex2 == this.vertex1) && (newEdge.vertex1 == this.vertex2))) {
                return true;
            }
        }
        return false;
    }
}


@ToString
class Node implements Comparable<Node> {
    int index;
    int distance;

    public Node(int index, int weight) {
        this.index = index;
        this.distance = weight;
    }

    @Override
    public int compareTo(Node o) {
        return this.distance - o.distance;
    }

    public boolean equals(Object anObject) {
        if (anObject instanceof Node) {
            Node newNode = (Node) anObject;
            return this.index == newNode.index;
        }
        return false;
    }
}