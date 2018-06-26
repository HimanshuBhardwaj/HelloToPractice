package com.himanshu.practice.June25.hour5;


import lombok.AllArgsConstructor;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by himanshubhardwaj on 25/06/18.
 */
public class TSP {

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
        System.out.println("TSP :-)");

        LinkedList<Integer> visited = new LinkedList<>();
        LinkedList<Integer> notVisited = new LinkedList<>();
        visited.add(0);

        for (int i = 1; i < graph.numNodes; i++) {
            notVisited.add(i);
        }

        graph.TSP(0, visited, 0, notVisited);
        System.out.println("TSP COST: " + graph.tspWeight);
        System.out.println("TSP: " + graph.tsp);

    }


}


//undirected graph
class Graph {
    LinkedList<Edge>[] edgeList;
    int numNodes;
    LinkedList<Integer> tsp;
    int tspWeight = Integer.MAX_VALUE;

    public Graph(int numNode) {
        this.numNodes = numNode;
        edgeList = new LinkedList[numNode];

        for (int i = 0; i < numNode; i++) {
            edgeList[i] = new LinkedList<Edge>();
        }
    }

    public void insert(int source, int destination, int weight) {
        Edge edge = new Edge(source, destination, weight);
        edgeList[source].add(edge);
        edgeList[destination].add(edge);
    }

    public void print() {
        for (int i = 0; i < numNodes; i++) {
            System.out.print(i + ": ");
            for (int j = 0; j < edgeList[i].size(); j++) {
                System.out.print(edgeList[i].get(j));
            }
            System.out.println();
        }
    }

    public void TSP(int source, LinkedList<Integer> visited, int currentWeight, LinkedList<Integer> notvisited) {
        System.out.println("visited" + visited + "\tNotVisited: " + notvisited);
        if (notvisited.isEmpty()) {

            if (isConnected(visited.get(0), visited.get(visited.size() - 1))) {
                int tempTspWeight = currentWeight + weight(visited.get(0), visited.get(visited.size() - 1));
                if (tempTspWeight < tspWeight) {
                    tspWeight = tempTspWeight;
                    tsp = (LinkedList<Integer>) visited.clone();
                    tsp.addLast(visited.get(0));
                }
            }
        } else {
            for (int i = 0; i < notvisited.size(); i++) {
                int desNode = notvisited.get(i);
                visited.addLast(desNode);
                notvisited.remove(i);
                if (isConnected(source, desNode)) {
                    TSP(desNode, visited, currentWeight + weight(source, desNode), notvisited);
                }
                notvisited.add(i, desNode);
                visited.removeLast();
            }
        }
    }

    //assumes that nodes are conneted
    private int weight(int node1, int node2) {
        for (int i = 0; i < edgeList[node1].size(); i++) {
            Edge edge = edgeList[node1].get(i);

            if ((edge.node1 == node1 && edge.node2 == node2) || (edge.node1 == node2 && edge.node2 == node1)) {
                return edge.weight;
            }
        }
        return Integer.MAX_VALUE;
    }

    private boolean isConnected(int node1, int node2) {
        for (int i = 0; i < edgeList[node1].size(); i++) {
            Edge edge = edgeList[node1].get(i);

            if ((edge.node1 == node1 && edge.node2 == node2) || (edge.node1 == node2 && edge.node2 == node1)) {
                return true;
            }
        }
        return false;
    }

}

@AllArgsConstructor
class Edge {
    int node1;
    int node2;
    int weight;

    public String toString() {
        return "(" + node1 + ", " + node2 + ", " + weight + ")\t";
    }
}
