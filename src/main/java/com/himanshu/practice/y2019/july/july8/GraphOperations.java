package com.himanshu.practice.y2019.july.july8;

import lombok.AllArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by himanshubhardwaj on 08/07/19.
 */
public class GraphOperations {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);

        Graph graph = new Graph(n);

        for (int i = 0; i < m; i++) {
            str = br.readLine().split(" ");
            int s = Integer.parseInt(str[0]);
            int d = Integer.parseInt(str[1]);
            double w = Integer.parseInt(str[2]);
            graph.insert(s, d, w);
        }

        graph.printGraph();

        System.out.println("Printing spannign tree");
        ArrayList<Edge> spanningTree = graph.mstKurashkal();

        for (Edge e : spanningTree) {
            System.out.println(e);
        }
    }
}


//7:27 --> 7:41 (Excluding MST Kurashkal)
class Graph {
    ArrayList<Edge> edgeList;
    int numNodes;
    ArrayList<Node> nodes;

    public Graph(int n) {
        this.numNodes = n;
        edgeList = new ArrayList<>();
        nodes = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            nodes.add(new Node(i));
        }
    }

    void insert(int s, int d, double w) {
        edgeList.add(new Edge(edgeList.size(), nodes.get(s), nodes.get(d), w));
    }

    void printGraph() {
        for (int i = 0; i < edgeList.size(); i++) {
            System.out.println(edgeList.get(i));
        }
    }

    //10 mins
    ArrayList<Edge> mstKurashkal() {
        Collections.sort(edgeList);
        int parent[] = new int[numNodes];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
        ArrayList<Edge> spanningTree = new ArrayList<>();


        for (int i = 0; i < edgeList.size(); i++) {
            if (!sameComponent(edgeList.get(i), parent)) {
                spanningTree.add(edgeList.get(i));
                mergeCluster(edgeList.get(i), parent);
            }
        }

        return spanningTree;
    }

    private void mergeCluster(Edge edge, int[] parent) {
        int parent1 = getParent(edge.v1.index, parent);
        int parent2 = getParent(edge.v2.index, parent);
        parent[parent1] = parent2;
    }

    private boolean sameComponent(Edge edge, int[] parent) {
        return (getParent(edge.v1.index, parent) == getParent(edge.v2.index, parent));
    }

    int getParent(int index, int[] parent) {
        if (parent[index] == index) {
            return index;
        } else {
            parent[index] = getParent(parent[index], parent);
            return parent[index];
        }
    }

}


@AllArgsConstructor
class Edge implements Comparable<Edge> {
    int index;
    Node v1;
    Node v2;
    double weight;

    @Override
    public int compareTo(Edge o) {
        return Double.compare(this.weight, o.weight);
    }

    public String toString() {
        return "Edge(index=" + this.index + ", v1=" + this.v1.index + ", v2=" + this.v2.index + ", weight=" + this.weight + ")";
    }
}

@AllArgsConstructor
class Node implements Comparable<Node> {
    int index;

    @Override
    public int compareTo(Node o) {
        return this.index - o.index;
    }
}


/*

6 8
0 1 1
0 2 3
2 1 2
2 4 1
2 3 2
3 4 1
3 5 3
4 5 1

* */