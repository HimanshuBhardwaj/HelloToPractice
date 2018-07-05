package com.himanshu.practice.july5.hour8;

import com.sun.javafx.geom.Edge;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by himanshubhardwaj on 05/07/18.
 */

//undirected
public class DFSSpanningTree {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StringTokenizer st = new StringTokenizer(sc.next(), ",");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        Graph graph = new Graph(n);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(sc.next(),",");
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            graph.insert(s, d);
        }


        boolean tree[][] = new boolean[n][n];
        boolean[] visited = new boolean[n];
        graph.spanningTree(0, tree, visited);


        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (tree[i][j]) {
                    System.out.println(i + ", " + j);
                }
            }
        }

    }
}


class Graph {
    boolean adjMat[][];
    int numNodes;

    public Graph(int numNodes) {
        adjMat = new boolean[numNodes][numNodes];
        this.numNodes = numNodes;
    }


    public void insert(int source, int destination) {
        adjMat[source][destination] = true;
    }


    public void spanningTree(int source, boolean[][] spanningTree, boolean[] visited) {
        if (visited[source]) {
            return;
        }

        visited[source] = true;

        for (int i = 0; i < adjMat.length; i++) {
            if ((adjMat[i][source] || adjMat[source][i]) && !visited[i]) {
                spanningTree[i][source] = true;
                spanningTree[source][i] = true;
                spanningTree(i, spanningTree, visited);
            }
        }
    }
}