package com.himanshu.practice.y2019.April.april11;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Singular;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by himanshubhardwaj on 11/04/19.
 * 10:30 <-->10:50
 */
/*
6 9
0 5
0 1
0 2
2 3
1 3
1 4
1 5
3 4
5 4

* */
public class GraphDFSBFS {
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

        g.DFS(0, new boolean[g.size]);
        System.out.println();
        g.BFS(0);


    }
}


//Undirected
class Graph {
    LinkedList<GNode>[] adjList;
    int size;
    ArrayList<GNode> nodes;


    public Graph(int n) {
        this.size = n;
        adjList = new LinkedList[n];
        nodes = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adjList[i] = new LinkedList<>();
            nodes.add(new GNode(i));
        }
    }

    public void insert(int s, int d) {
        adjList[s].add(nodes.get(d));
        adjList[d].add(nodes.get(s));
    }


    public void DFS(int root, boolean[] isVisited) {
        if (root >= size || root < 0 || isVisited[root]) {
            return;
        }
        System.out.print(root + ", ");

        isVisited[root] = true;

        for (GNode neighbour : adjList[root]) {
            if (!isVisited[neighbour.value]) {
                DFS(neighbour.value, isVisited);
            }
        }
    }


    public void BFS(int root) {
        Queue<GNode> level = new LinkedList<>();
        boolean isVisited[] = new boolean[size];

        level.add(nodes.get(root));
        isVisited[root] = true;
        int l = 0;


        while (!level.isEmpty()) {
            System.out.print(l + ": ");
            LinkedList<GNode> levelNode = new LinkedList<>(level);
            level = new LinkedList<>();
            for (GNode gn : levelNode) {
                System.out.print(gn.value + ", ");

                for (GNode neighbour : adjList[gn.value]) {
                    if (!isVisited[neighbour.value]) {
                        level.add(neighbour);
                        isVisited[neighbour.value] = true;
                    }
                }
            }
            l++;
            System.out.println();
        }
    }


}


@EqualsAndHashCode
@AllArgsConstructor
class GNode {
    int value;
}
