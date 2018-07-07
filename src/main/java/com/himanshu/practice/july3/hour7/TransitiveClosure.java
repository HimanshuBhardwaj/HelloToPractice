package com.himanshu.practice.july3.hour7;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by Himanshu Bhardwaj on 03/07/18.
 * directed
 * 7:04-7:21
 *2 mins could have been saved
 */
public class TransitiveClosure {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int node = Integer.parseInt(sc.next());
        Graph graph = new Graph(node);

        for (int i = 0; i < node; i++) {
            StringTokenizer st = new StringTokenizer(sc.next(), ",");
            int source = Integer.parseInt(st.nextToken());

            while (st.hasMoreTokens()) {
                int destination = Integer.parseInt(st.nextToken());
                graph.insert(source, destination);
            }
        }
        graph.print();


        System.out.println();
        System.out.println();
        int[][] closure = graph.transitiveClosure();

        for (int i = 0; i < graph.numNodes; i++) {
            System.out.print(i + ": ");
            for (int j = 0; j < graph.numNodes; j++) {
                if (closure[i][j] == 1) {
                    System.out.print(j + ",");
                }
            }
            System.out.println();
        }
    }
}


class Graph {
    int[][] adjMat;
    int numNodes;


    public Graph(int verties) {
        adjMat = new int[verties][verties];
        this.numNodes = verties;
    }

    public void insert(int source, int destination) {
        adjMat[source][destination] = 1;
    }

    public void print() {
        for (int i = 0; i < numNodes; i++) {
            System.out.print(i + ": ");
            for (int j = 0; j < numNodes; j++) {
                if (adjMat[i][j] == 1) {
                    System.out.print(j + ", ");
                }
            }
            System.out.println();
        }
    }


    public int[][] transitiveClosure() {
        int[][] closure = adjMat.clone();

        for (int k = 0; k < numNodes; k++) {
            for (int i = 0; i < numNodes; i++) {
                for (int j = 0; j < numNodes; j++) {
                    if (closure[i][k] == 1 && closure[k][j] == 1) {
                        closure[i][j] = 1;
                    }
                }
            }
        }

        return closure;
    }
}