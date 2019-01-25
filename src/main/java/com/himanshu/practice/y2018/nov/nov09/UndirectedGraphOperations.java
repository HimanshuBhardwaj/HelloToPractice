package com.himanshu.practice.y2018.nov.nov09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * Created by himanshubhardwaj on 12/11/18.
 * Bridge, Articulation Point,
 */
public class UndirectedGraphOperations {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);

        Graph g = new Graph(n);

        for (int i = 0; i < m; i++) {
            str = br.readLine().split(" ");
            g.insert(Integer.parseInt(str[0]), Integer.parseInt(str[1]));
        }

        g.print();
        g.computeArtuculationPoint(0, -1);
        g.printArticulationPoints();
    }
}


class Graph {
    LinkedList<Integer>[] adjList;
    boolean[] isArticulation;
    int[] discovery;
    int[] finish;
    int[] lowest;
    static int timer = 0;


    public Graph(int nodeNum) {
        adjList = new LinkedList[nodeNum];


        for (int i = 0; i < nodeNum; i++) {
            adjList[i] = new LinkedList<>();
        }

        isArticulation = new boolean[nodeNum];
        discovery = new int[nodeNum];
        finish = new int[nodeNum];
        lowest = new int[nodeNum];
    }


    public void insert(int source, int destination) {
        adjList[source].addLast(destination);
        adjList[destination].addLast(source);
    }


    public void print() {
        for (int i = 0; i < adjList.length; i++) {
            System.out.print(i + ": ");
            for (int j = 0; j < adjList[i].size(); j++) {
                System.out.print(adjList[i].get(j) + ", ");
            }
            System.out.println();
        }
    }


    //Assuming connected graph
    public void computeArtuculationPoint(int source, int parent) {
        if (discovery[source] > 0) {
            return;//we have already been here
        }
        timer++;
        discovery[source] = timer;
        lowest[source] = timer;
        int successorLowert = Integer.MAX_VALUE;

        for (int neighbour : adjList[source]) {
            if (neighbour != parent) {
                if (discovery[neighbour] == 0) {
                    computeArtuculationPoint(neighbour, source);
                    if (lowest[neighbour] >= discovery[source]) {
                        isArticulation[source] = true;
                    }
                }

            }
        }


        for (int neighbour : adjList[source]) {
            if (neighbour != parent) {
                lowest[source] = Math.min(lowest[source], lowest[neighbour]);
            }
        }

        timer++;
        finish[source] = timer;
    }


    public void printArticulationPoints() {
        System.out.println("Node\tdic\t\tfinish\tlowest");
        for (int i = 0; i < adjList.length; i++) {
            System.out.print(i + "\t\t" + discovery[i] + "\t\t" + finish[i] + "\t\t" + lowest[i]);
            if (isArticulation[i]) {
                System.out.println("\t\tTrue");
            } else {
                System.out.println("\tFalse");
            }
        }
    }


}



/*
7 11
0 1 1
0 2 2
2 1 3
1 3 5
2 4 1
2 5 1
3 4 1
3 5 10
4 5 1
6 5 100
0 6 100



7 8
0 1
0 3
1 2
2 3
3 4
4 5
5 6
4 6



9 11
1 0
0 3
1 2
2 3
2 4
2 5
5 6
5 8
6 8
6 7
8 7




9 11
0 1
1 2
0 3
2 3
2 4
2 5
5 6
5 8
6 8
6 7
7 8


* */
