package com.himanshu.practice.july.july15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * Created by himanshubhardwaj on 16/07/18.
 * 6:25 am
 */
public class Articulationpoint {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);

        Graph g = new Graph(n);

        for (int i = 0; i < m; i++) {
            str = br.readLine().split(" ");
            g.insert(Integer.parseInt(str[0]), Integer.parseInt(str[1]));
        }

        //g.printGraph();
        g.printArticulation();
    }
}


//directed graph
class Graph {
    LinkedList<Integer> edgeList[];
    int[] tDiscovery;
    int[] tOut;
    int[] low;
    int parent[];
    int timer = 0;
    boolean[] isArticulation;

    public Graph(int numNodes) {
        edgeList = new LinkedList[numNodes];
        tDiscovery = new int[numNodes];
        tOut = new int[numNodes];
        low = new int[numNodes];
        parent = new int[numNodes];
        isArticulation = new boolean[numNodes];

        for (int i = 0; i < numNodes; i++) {
            edgeList[i] = new LinkedList<>();
        }
    }

    public void insert(int source, int destination) {
        edgeList[source].addLast(destination);
    }

    private void dfs() {
        boolean[] visited = new boolean[edgeList.length];
        dfsHelper(0, visited, -1);
    }

    private int dfsHelper(int node, boolean[] visited, int par) {
        visited[node] = true;
        int child = 0;
        parent[node] = par;

        timer++;
        tDiscovery[node] = timer;
        low[node] = timer;

        for (int neighbour : edgeList[node]) {
            if (neighbour != par && !visited[neighbour]) {
                child++;
                dfsHelper(neighbour, visited, node);
                if (tDiscovery[node] <= low[neighbour]) {
                    isArticulation[node] = true;
                }
                child++;
            }
        }

        if (0 == node && child > 1) {
            isArticulation[node] = true;
        }
        for (int neighbour : edgeList[node]) {
            low[node] = Math.min(low[neighbour], low[node]);
        }


        timer++;
        tOut[node] = timer;


        return low[node];
    }

    public void printArticulation() {
        dfs();
//        System.out.println();
//        for (int i = 0; i < edgeList.length; i++) {
//            System.out.println(i + "\t" + parent[i] + "\t" + tDiscovery[i] + "\t" + tOut[i] + "\t" + low[i]);
//        }

        for (int i = 0; i < edgeList.length; i++) {
            if (isArticulation[i]) {
                System.out.print(i + ", ");
            }
        }
        System.out.println();
    }

    public void printGraph() {
        System.out.println();
        for (int i = 0; i < edgeList.length; i++) {
            System.out.print(i + ": ");
            for (int j : edgeList[i]) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

}

/*
Input:
8 10
0 1
1 2
2 3
4 3
1 4
4 0
4 5
5 6
6 7
7 5

*
* */