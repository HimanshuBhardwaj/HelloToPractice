package com.himanshu.practice.y2018.nov.nov5;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.LinkedList;

/**
 * Created by himanshubhardwaj on 05/11/18.
 */
public class GraphBFSDFS {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Graph g = new Graph(n);
        int m = Integer.parseInt(br.readLine());

        while (m-- > 0) {
            String str[] = br.readLine().split(" ");
            int s = Integer.parseInt(str[0]);
            int d = Integer.parseInt(str[1]);
            g.insert(s, d);
        }
        g.printGraph();
        g.DFS(0, new HashSet<>());
        System.out.println();
        System.out.println(g.hasCycle(0, -1, new HashSet<>()));
        g.topologicalSort(0);


    }
}


class Graph {
    List<Integer>[] adjList;


    public Graph(int size) {
        adjList = new List[size];

        for (int i = 0; i < size; i++) {
            adjList[i] = new LinkedList<>();
        }
    }

    //assume undirected graph
    public void insert(int source, int destination) {
        adjList[source].add(destination);
        adjList[destination].add(source);
    }


    public void printGraph() {
        for (int i = 0; i < adjList.length; i++) {
            System.out.print(i + ": ");
            for (int x : adjList[i]) {
                System.out.print(x + ",");
            }
            System.out.println();
        }
    }

    public void DFS(int root, HashSet<Integer> visited) {
        System.out.print(root + "\t");
        visited.add(root);
        for (int neighour : adjList[root]) {
            if (!visited.contains(neighour)) {
                DFS(neighour, visited);
            }
        }
    }


    public boolean hasCycle(int root, int parent, HashSet<Integer> visited) {
        visited.add(root);

        for (int x : adjList[root]) {
            if (x != parent) {
                if (visited.contains(x)) {
                    return true;
                } else {
                    hasCycle(x, root, visited);
                }
            }
        }

        return false;
    }


    public void topologicalSort(int root) {
        System.out.println("@topologicalSort");
        if (hasCycle(root, -1, new HashSet<>())) {
            System.out.println("Topological Sort not possible. Has cycle");
            return;
        }

        topologicalSortHelper(root,-1);
    }

    private void topologicalSortHelper(int root, int par) {
        for (int x : adjList[root]) {
            if (x != par) {
                topologicalSortHelper(x, root);
            }
        }
        System.out.print(root+" ");
    }


}
