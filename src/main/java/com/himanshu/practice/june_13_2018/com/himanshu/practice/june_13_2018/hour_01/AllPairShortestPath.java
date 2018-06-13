package com.himanshu.practice.june_13_2018.com.himanshu.practice.june_13_2018.hour_01;

import lombok.Getter;

import java.util.Scanner;

/**
 * Created by himanshubhardwaj on 13/06/18.
 */
public class AllPairShortestPath {
    public static void main(String[] args) {
        System.out.println("Enter number of nodes:");
        Scanner sc = new Scanner(System.in);
        int node = Integer.parseInt(sc.next());
        Graph graph = new Graph(node);

        for (int i = 0; i < node; i++) {
            for (int j = 0; j < node; j++) {
                graph.getMatrix()[i][j] = sc.nextInt();
            }
        }

        System.out.println("Printing for validation");


        for (int i = 0; i < node; i++) {
            for (int j = 0; j < node; j++) {
                System.out.print(graph.getMatrix()[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();

        int apsp[][] = graph.allPairshortestPath();

        for (int i = 0; i < node; i++) {
            System.out.println();
            for (int j = 0; j < node; j++) {
                System.out.print(apsp[i][j] + " ");
            }
        }
        System.out.println();
        System.out.println();


    }
}

@Getter
class Graph {
    int matrix[][];
    int node;

    public Graph(int node) {

        this.node = node;
        matrix = new int[node][node];
    }

    int[][] allPairshortestPath() {
        int allPairShortestPAth[][] = new int[node][node];

        for (int i = 0; i < node; i++) {
            for (int j = 0; j < node; j++) {
                allPairShortestPAth[i][j] = matrix[i][j];
            }
        }

        for (int i = 0; i < node; i++) {
            for (int j = 0; j < node; j++) {
                for (int k = 0; k < node; k++) {
                    if (allPairShortestPAth[i][k] >= 0 && allPairShortestPAth[k][j] >= 0)
                        allPairShortestPAth[i][j] = min(allPairShortestPAth[i][j], allPairShortestPAth[i][k] + allPairShortestPAth[k][j]);
                }
            }
        }

        return allPairShortestPAth;
    }

    private int min(int a, int b) {
        if (a > b || a == -1) {
            return b;
        }
        return a;
    }
}