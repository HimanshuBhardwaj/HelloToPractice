package com.himanshu.practice.june26;

import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by Himanshu Bhardwaj on 26/06/18.
 */
public class APSP {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        int size = Integer.parseInt(sc.next());
        Graph graph = new Graph(size);

        for (int i = 0; i < size; i++) {
            StringTokenizer st = new StringTokenizer(sc.next(), ",");
            int source = Integer.parseInt(st.nextToken());

            while (st.hasMoreTokens()) {
                int destination = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());
                graph.insert(source, destination, weight);
            }
        }

        int[][] apsp = graph.FloydWarshall();

        for (int i = 0; i < apsp.length; i++) {
            for (int j = 0; j < apsp[i].length; j++) {
                System.out.print(apsp[i][j] + "\t");
            }
            System.out.println();
        }

    }
}


//directed graph
class Graph {
    int adjMat[][];
    int numNodes;


    public Graph(int numNodes) {
        this.numNodes = numNodes;
        adjMat = new int[numNodes][numNodes];
        for (int i = 0; i < numNodes; i++) {
            for (int j = 0; j < numNodes; j++) {
                adjMat[i][j] = Integer.MAX_VALUE;
            }
            adjMat[i][i] = 0;
        }
    }

    public void insert(int source, int destination, int weight) {
        adjMat[source][destination] = weight;
    }

    public int[][] FloydWarshall() {
        System.out.println("Computing shortest path @FloydWarshall");
        int[][] adjMatTemp = adjMat.clone();
        boolean temp = true;
        for (int k = 0; k < numNodes; k++) {
            for (int i = 0; i < numNodes; i++) {
                for (int j = 0; j < numNodes; j++) {
                    if (i == 4 && j == 0) {
                        if (temp) {
                            System.out.println("(" + i + ", " + j + ")--> " + adjMatTemp[i][j]);
                            temp = false;
                        }
                        System.out.print("(" + i + ", " + k + ")--> " + adjMatTemp[i][k] + "\t");
                        System.out.println("(" + k + ", " + j + ")--> " + adjMatTemp[k][j] + "");
                    }
                    if (adjMatTemp[i][k] != Integer.MAX_VALUE && adjMatTemp[k][j] != Integer.MAX_VALUE) {
                        adjMatTemp[i][j] = Math.min(adjMatTemp[i][j], adjMatTemp[i][k] + adjMatTemp[k][j]);
                    }
                }
            }
        }

        return adjMatTemp;
    }


}