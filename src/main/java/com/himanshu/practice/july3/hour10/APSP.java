package com.himanshu.practice.july3.hour10;

import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by himanshubhardwaj on 03/07/18.
 * 10:16
 */
public class APSP {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numNodes = Integer.parseInt(sc.next());
        Graph graph = new Graph(numNodes);

        for (int i = 0; i < numNodes; i++) {
            StringTokenizer st = new StringTokenizer(sc.next(), ",");
            int source = Integer.parseInt(st.nextToken());

            while (st.hasMoreTokens()) {
                int destinaation = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());

                graph.insert(source, destinaation, weight);
            }
        }
        graph.print();
        System.out.println();
        System.out.println();


        int[][] apsp = graph.floydWarshall();

        for (int i = 0; i < apsp.length; i++) {
            for (int j = 0; j < apsp[0].length; j++) {
                if (apsp[i][j] == Integer.MAX_VALUE) {
                    System.out.print("*\t");
                } else {
                    System.out.print(apsp[i][j] + "\t");
                }
            }
            System.out.println();
        }

    }
}

class Graph {
    int adj[][];
    int numNodes;


    public Graph(int numNode) {
        adj = new int[numNode][numNode];
        this.numNodes = numNode;
        for (int i = 0; i < adj.length; i++) {
            for (int j = 0; j < adj[0].length; j++) {
                adj[i][j] = Integer.MAX_VALUE;
            }
            System.out.println();
        }
    }

    public void insert(int source, int destination, int weight) {
        adj[source][destination] = weight;

    }

    public int[][] floydWarshall() {
        int apsp[][] = adj.clone();

        for (int k = 0; k < numNodes; k++) {
            for (int i = 0; i < numNodes; i++) {
                for (int j = 0; j < numNodes; j++) {
                    if ((apsp[i][k] != Integer.MAX_VALUE) && (apsp[k][j] != Integer.MAX_VALUE) && (apsp[i][j] > apsp[i][k] + apsp[k][j])) {
                        apsp[i][j] = apsp[i][k] + apsp[k][j];
                    }
                }
            }
        }
        return apsp;
    }

    public void print() {
        for (int i = 0; i < adj.length; i++) {
            for (int j = 0; j < adj[0].length; j++) {
                if (adj[i][j] == Integer.MAX_VALUE) {
                    System.out.print("*\t");
                } else {
                    System.out.print(adj[i][j] + "\t");
                }
            }
            System.out.println();
        }
    }

}
