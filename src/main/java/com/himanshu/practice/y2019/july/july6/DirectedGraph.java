package com.himanshu.practice.y2019.july.july6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by himanshubhardwaj on 06/07/19.
 */
public class DirectedGraph {
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
            graph.insert(s, d);
        }
        graph.printGraph();
        System.out.println();
        System.out.println("---------------");
        System.out.println();

        Graph transitiveClosire = GraphUtils.transitiveClosire(graph);
        transitiveClosire.printGraph();


    }
}

class Graph {
    ArrayList<Integer>[] adjList;
    int size;

    public Graph(int n) {
        adjList = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<>();
        }
        this.size = n;
    }

    public Graph(Graph g) {
        this.size = g.size;
        this.adjList = g.adjList.clone();
    }


    public void insert(int s, int d) {
        adjList[s].add(d);
    }

    public void printGraph() {
        System.out.println("Printing graph");
        for (int i = 0; i < size; i++) {
            System.out.print(i + ": ");
            for (int neighbour : adjList[i]) {
                System.out.print(neighbour + ",");
            }
            System.out.println();
        }
    }
}

class GraphUtils {
    static Graph transitiveClosire(Graph graph) {
        boolean adjMatrix[][] = new boolean[graph.size][graph.size];
        for (int i = 0; i < graph.size; i++) {
            for (int n : graph.adjList[i]) {
                adjMatrix[i][n] = true;
            }
        }

        for (int k = 0; k < graph.size; k++) {
            for (int i = 0; i < graph.size; i++) {
                for (int j = 0; j < graph.size; j++) {
                    adjMatrix[i][j] = adjMatrix[i][j] || (adjMatrix[i][k] && adjMatrix[k][j]);
                }
            }
        }


        Graph transitiveClosureGrah = new Graph(graph.size);


        for (int i = 0; i < graph.size; i++) {
            for (int j = 0; j < graph.size; j++) {
                if (adjMatrix[i][j]) {
                    transitiveClosureGrah.insert(i, j);
                }
            }
        }
        return transitiveClosureGrah;
    }

}
/*

5 8
0 1
0 2
1 4
2 3
2 4
3 1
4 3
4 0
 */