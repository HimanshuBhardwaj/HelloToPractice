package com.himanshu.practice.y2018.july.july4.hour9;

import lombok.AllArgsConstructor;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * Created by Himanshu Bhardwaj on 04/07/18.
 * 9:24 pm
 * 10:42 pm
 *
 *
 * could have been done in  30 mins
 */
public class StronglyConnectedcomponents {
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

        graph.print(graph.edgeList);
        System.out.println();
        System.out.println();
        graph.print(graph.reverseEdgeList);
        graph.strongLyconnectedComponents();
    }
}


class Graph {
    LinkedList<Edge>[] edgeList;
    LinkedList<Edge>[] reverseEdgeList;
    int numNodes;

    public Graph(int num) {
        this.numNodes = num;
        edgeList = new LinkedList[num];
        reverseEdgeList = new LinkedList[num];

        for (int i = 0; i < num; i++) {
            edgeList[i] = new LinkedList<>();
            reverseEdgeList[i] = new LinkedList<>();
        }
    }


    public void insert(int source, int destination, int weight) {
        edgeList[source].addLast(new Edge(source, destination, weight));
        reverseEdgeList[destination].addLast(new Edge(destination, source, weight));
    }

    public void strongLyconnectedComponents() {
        Stack<Integer> dfsOrder = new Stack<>();

        boolean visited[] = new boolean[numNodes];
        for (int i = 0; i < numNodes; i++) {
            visited[i] = false;
        }

        for (int i = 0; i < numNodes; i++) {
            if (!visited[i]) {
                DFS(new LinkedList<Integer>(), i, visited, dfsOrder, edgeList);
            }
        }

        System.out.println("dfsOrder\t" + dfsOrder);


        for (int i = 0; i < numNodes; i++) {
            visited[i] = false;
        }


        while (!dfsOrder.isEmpty()) {
            int node = dfsOrder.pop();
            if (!visited[node]) {
                LinkedList<Integer> connectedComponents = new LinkedList<>();
                DFS(connectedComponents, node, visited, new Stack<Integer>(), reverseEdgeList);
                System.out.println(connectedComponents);
            }
        }
    }


    public void print(LinkedList<Edge>[] edgeList) {
        for (int i = 0; i < numNodes; i++) {
            System.out.print(i + ": ");
            for (int j = 0; j < edgeList[i].size(); j++) {
                System.out.print(edgeList[i].get(j));
            }
            System.out.println();
        }
    }

    private void DFS(LinkedList<Integer> path, int node, boolean[] visited, Stack<Integer> dfsOrder, LinkedList<Edge>[] edgeList) {
        path.addLast(node);
        visited[node] = true;

        for (int i = 0; i < edgeList[node].size(); i++) {
            if (!visited[edgeList[node].get(i).destination]) {
                visited[edgeList[node].get(i).destination] = true;
                DFS(path, edgeList[node].get(i).destination, visited, dfsOrder, edgeList);
            }
        }
        dfsOrder.push(node);
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
        return "(" + source + ", " + destination + ", " + weight + ")\t";
    }
}