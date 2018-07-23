package com.himanshu.practice.june.june26.hour4;

import lombok.AllArgsConstructor;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by Himanshu Bhardwaj on 26/06/18.
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
        System.out.println(graph.TSP());
        System.out.println(graph.tspLength);
    }
}

//undirected graph
class Graph {
    LinkedList<Edge> edgeList[];
    int numNodes;
    int tspLength = Integer.MAX_VALUE;
    LinkedList<Integer> tsp;


    public Graph(int numNodes) {
        edgeList = new LinkedList[numNodes];
        this.numNodes = numNodes;
        for (int i = 0; i < numNodes; i++) {
            edgeList[i] = new LinkedList<Edge>();
        }
    }

    public void insert(int source, int destination, int edge) {
        Edge newEdge = new Edge(source, destination, edge);
        edgeList[source].addLast(newEdge);
        edgeList[destination].addLast(newEdge);
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

    public LinkedList<Integer> TSP() {
        int source = 0;
        LinkedList<Integer> visited = new LinkedList<>();
        visited.addLast(0);
        LinkedList<Integer> notVisited = new LinkedList<>();
        for (int i = 0; i < numNodes; i++) {
            if (i != source) {
                notVisited.addLast(i);
            }
        }
        TSPHelper(0, visited, notVisited, 0);
        return tsp;
    }

    private void TSPHelper(int node, LinkedList<Integer> visited, LinkedList<Integer> notVisited, int currentPath) {
        System.out.println("Visited:" + visited + "\tNot Visited: " + notVisited);
        if (notVisited.isEmpty()) {
            if (isConnedted(visited.getFirst(), visited.getLast()) && (tspLength > (currentPath + weight(visited.getLast(), visited.getFirst())))) {
                tspLength = currentPath + weight(visited.getLast(), visited.getFirst());
                tsp = (LinkedList<Integer>) visited.clone();
                tsp.addLast(tsp.getFirst());
            }
        } else {
            for (int i = 0; i < notVisited.size(); i++) {
                int destination = notVisited.get(i);

                if (!visited.contains(destination) && isConnedted(destination, node)) {
                    visited.addLast(destination);
                    notVisited.remove(i);
                    TSPHelper(destination, visited, notVisited, currentPath + weight(node, destination));
                    visited.removeLast();
                    notVisited.add(i, destination);
                }
            }
        }
    }

    private int weight(Integer second, Integer first) {
        for (int i = 0; i < edgeList[first].size(); i++) {
            Edge edge = edgeList[first].get(i);
            if ((first == edge.node1 && second == edge.node2) || (first == edge.node2 && second == edge.node1)) {
                return edge.weight;
            }
        }
        return Integer.MAX_VALUE;
    }

    private boolean isConnedted(Integer first, Integer second) {
        for (int i = 0; i < edgeList[first].size(); i++) {
            Edge edge = edgeList[first].get(i);
            if ((first == edge.node1 && second == edge.node2) || (first == edge.node2 && second == edge.node1)) {
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
        return "(" + node1 + ", " + node2 + ", " + weight + ") ";
    }
}