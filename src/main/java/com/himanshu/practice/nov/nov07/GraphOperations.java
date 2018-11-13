package com.himanshu.practice.nov.nov07;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by himanshubhardwaj on 07/11/18.
 * 10:42
 */

/*
* DFS, BFS, CYCLE
* */
public class GraphOperations {
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
        g.DFS(0);
        System.out.println();
        g.BFS(0);
        System.out.println(g.hasCycle());

    }
}


//GRAPH is Directed UGraph
class Graph {
    ArrayList<Integer>[] adjList;


    public Graph(int size) {
        adjList = new ArrayList[size];

        for (int i = 0; i < size; i++) {
            adjList[i] = new ArrayList<>();
        }
    }

    public void insert(int source, int destination) {
        adjList[source].add(destination);

    }

    public void DFS(int root) {
        DFSHeler(root, new HashSet<>());
    }

    private void DFSHeler(int root, HashSet<Integer> isVisited) {
        System.out.print(root + "-->");
        isVisited.add(root);


        for (int neighbour : adjList[root]) {
            if (!isVisited.contains(neighbour)) {
                DFSHeler(neighbour, isVisited);
            }
        }
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


    public void BFS(int root) {

        Queue<Integer> queue = new LinkedList<>();
        queue.add(root);
        HashSet<Integer> visited = new HashSet<>();
        visited.add(root);


        while (!queue.isEmpty()) {
            int value = queue.poll();
            System.out.print(value + "-->");


            for (int nei : adjList[value]) {
                if (!visited.contains(nei)) {
                    visited.add(nei);
                    queue.add(nei);
                }
            }

        }

    }


    public boolean hasCycle() {
        HashSet<Integer> visited = new HashSet<>();

        for (int i = 0; i < adjList.length; i++) {
            if (!visited.contains(i)) {
                if (hasCycleHelper(i, new HashSet<Integer>(), visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasCycleHelper(int root, HashSet<Integer> path, HashSet<Integer> visited) {
        if (path.contains(root)) {
            return true;
        }

        visited.add(root);
        path.add(root);


        for (int x : adjList[root]) {
            if (path.contains(x)) {
                return true;
            }
            if (!visited.contains(x) && hasCycleHelper(x, path, visited)) {
                return true;
            }
        }

        path.remove(root);


        return false;

    }

}


/*

7 11
0 1
0 2
2 1
1 3
2 4
2 5
3 4
3 5
4 5
5 1
0 6


* */