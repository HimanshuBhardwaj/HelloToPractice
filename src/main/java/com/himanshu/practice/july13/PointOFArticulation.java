package com.himanshu.practice.july13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * Created by himanshubhardwaj on 13/07/18.
 * Root by definition is articulation point
 */


//undirected
public class PointOFArticulation {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);
        Graph graph = new Graph(n);

        for (int i = 0; i < m; i++) {
            str = br.readLine().split(" ");
            int source = Integer.parseInt(str[0]);
            int destination = Integer.parseInt(str[1]);
            graph.insert(source, destination);

        }
        //graph.printGraph();
        graph.dfsForArticulation(1, -1);
        graph.printArticulaationPoints();

    }

}


// 1..n, node umbering
//undirected graph
class Graph {
    LinkedList<Integer> edgeList[];
    int[] discovery;
    boolean isVisited[];
    int lowest[];
    int parent[];
    boolean isArticulation[];
    int finished[];
    int timer = 0;


    public Graph(int numNodes) {
        numNodes++;
        edgeList = new LinkedList[numNodes];
        discovery = new int[numNodes];
        lowest = new int[numNodes];
        parent = new int[numNodes];
        isVisited = new boolean[numNodes];
        isArticulation = new boolean[numNodes];
        finished = new int[numNodes];

        for (int i = 0; i < numNodes; i++) {
            edgeList[i] = new LinkedList<>();
        }
    }

    public void insert(int source, int destination) {
        edgeList[source].addLast(destination);
        edgeList[destination].addLast(source);
    }

    public void dfsForArticulation(int root, int par) {
        if (isVisited[root]) {
            return;
        }
        int numChild = 0;//num child in DFS tree
        isVisited[root] = true;
        timer++;
        discovery[root] = lowest[root] = timer;


        for (int neighbour : edgeList[root]) {
            if (neighbour != par && !isVisited[neighbour]) {
                parent[neighbour] = root;
                numChild++;
                dfsForArticulation(neighbour, root);
                int temp = lowest[neighbour];
                //System.out.println(root + " " + par + " " + neighbour + " " + " " + discovery[root] + " " + temp);


                if (parent[root] == -1 && numChild >= 2) {
                    isArticulation[root] = true;
                } else if (parent[root] != -1 && temp >= discovery[root]) {
                    isArticulation[root] = true;
                }
            }
        }


        for (int neighbour : edgeList[root]) {
            if (neighbour != par) {
                lowest[root] = Math.min(lowest[root], lowest[neighbour]);
            }
        }


        finished[root] = ++timer;
        //System.out.println(root + " " + parent[root] + " " + discovery[root] + " " + lowest[root]);
    }

    public void printArticulaationPoints() {
        for (int i = 1; i < isArticulation.length; i++) {
            if (isArticulation[i]) {
                System.out.print(i + " ");
            }
        }
    }

    public void printGraph() {
        for (int i = 1; i < edgeList.length; i++) {
            System.out.print(i + ": ");
            for (int j : edgeList[i]) {
                System.out.print(j + ", ");
            }
            System.out.println();
        }
    }


}
