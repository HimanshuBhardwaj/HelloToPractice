package com.himanshu.practice.y2018.nov.nov09;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * Created by himanshubhardwaj on 12/11/18.
 * Topological Sort, DAG1 DGraph Prep: 5:53--6:08
 */
public class DirectedGraphOperations {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);

        DAG1 g = new DAG1(n);

        for (int i = 0; i < m; i++) {
            str = br.readLine().split(" ");
            g.insert(Integer.parseInt(str[0]), Integer.parseInt(str[1]));
        }

        g.print();
        System.out.println("Topological Sort: ");
        LinkedList<Integer> topologiclSort = new LinkedList<>();
        g.topologicalSort(0, new HashSet<>(), topologiclSort);
        System.out.println("Topological insertionSort: " + topologiclSort);
        System.out.println(g.hasCycle(0, new int[g.adjList.length]));


    }

}


class DAG1 {
    ArrayList<Integer>[] adjList;

    public DAG1(int numNodes) {
        adjList = new ArrayList[numNodes];

        for (int i = 0; i < numNodes; i++) {
            adjList[i] = new ArrayList<>();
        }
    }


    public void insert(int source, int destination) {
        adjList[source].add(destination);
    }

    public void print() {
        for (int i = 0; i < adjList.length; i++) {
            System.out.print(i + ": ");
            for (int x : adjList[i]) {
                System.out.print(x + ", ");
            }
            System.out.println();
        }
    }


    public void topologicalSort(int source, HashSet<Integer> visited, LinkedList<Integer> topologicalSort) {
        if (visited.contains(source)) {
            return;
        }

        visited.add(source);


        for (int n : adjList[source]) {
            if (!visited.contains(n)) {
                topologicalSort(n, visited, topologicalSort);
            }
        }
        topologicalSort.addLast(source);
    }

    public boolean hasCycle(int source, int nodesState[]) {
        if (nodesState[source] == 1) {
            return true;
        }

        if (nodesState[source] == 2) {
            return false;
        }

        nodesState[source] = 1;


        for (int neighbour : adjList[source]) {
            if (hasCycle(neighbour, nodesState)) {
                return true;
            }
        }


        nodesState[source] = 2;
        return false;

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





*
* */