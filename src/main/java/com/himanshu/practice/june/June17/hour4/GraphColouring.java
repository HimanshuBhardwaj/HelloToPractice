package com.himanshu.practice.june.June17.hour4;

import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by Himanshu Bhardwaj on 17/06/18.
 * TODO: Do it
 */
public class GraphColouring {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int node = Integer.parseInt(sc.nextLine());

        Graph graph = new Graph(node);


        for (int i = 0; i < node; i++) {
            String nexLine = sc.nextLine();

            StringTokenizer st = new StringTokenizer(nexLine, ",");
            int source = Integer.parseInt(st.nextToken());
            while (st.hasMoreTokens()) {
                int destination = Integer.parseInt(st.nextToken());
                graph.insert(source, destination);
            }
        }

        System.out.println();
        graph.print();
    }
}


class Graph {
    int nodeNumber;
    int adjMat[][];


    public Graph(int nodeNumber) {
        this.nodeNumber = nodeNumber;
        adjMat = new int[nodeNumber][nodeNumber];
    }

    //undirected graph
    public void insert(int source, int destination) {
        adjMat[source][destination] = 1;
        adjMat[destination][source] = 1;
    }


    public void print() {
        for (int i = 0; i < nodeNumber; i++) {
            System.out.print(i + ": ");
            for (int j = 0; j < nodeNumber; j++) {
                if (adjMat[i][j] == 1) {
                    System.out.print(j + ",");
                }

            }
            System.out.println();
        }
    }
}
