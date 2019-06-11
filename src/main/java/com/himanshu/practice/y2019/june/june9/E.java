package com.himanshu.practice.y2019.june.june9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by himanshubhardwaj on 09/06/19.
 */
public class E {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);
        String str[] = null;

        while (t-- > 0) {
            str = br.readLine().split(" ");
            int n = Integer.parseInt(str[0]);
            int m = Integer.parseInt(str[1]);

            Graph graph = new Graph(n);


            for (int i = 0; i < m; i++) {
                str = br.readLine().split(" ");
                int v1 = Integer.parseInt(str[0]);
                int v2 = Integer.parseInt(str[1]);
                graph.insert(v1, v2);
            }
            pw.append(graph.BFS(graph.root).toString());
        }
        pw.flush();
    }
}


class Graph {
    int size;
    ArrayList<Node> nodes;
    ArrayList<Node>[] adjList;
    Integer root = null;

    public Graph(int n) {
        this.size = n;
        adjList = new ArrayList[n];
        nodes = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            nodes.add(i, new Node(i));
        }
        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<>();
        }
    }

    public void insert(int v1, int v2) {
        v1--;
        v2--;
        adjList[v1].add(nodes.get(v2));
        adjList[v2].add(nodes.get(v1));
        if (root == null) {
            root = v1;
        }
    }

    public StringBuilder BFS(int root) {

        int level = 0;
        Queue<Node> levelNodes = new LinkedList<>();
        levelNodes.add(nodes.get(root));
        nodes.get(root).level = level;
        nodes.get(root).isVisited = true;


        while (!levelNodes.isEmpty()) {
            Node top = levelNodes.poll();

            for (Node neighbour : adjList[top.index]) {
                if (!neighbour.isVisited) {
                    neighbour.isVisited = true;
                    neighbour.level = top.level + 1;
                    levelNodes.add(neighbour);
                }
            }
        }

        ArrayList<Node> allLevelNodes[] = new ArrayList[2];
        allLevelNodes[0] = new ArrayList<>();
        allLevelNodes[1] = new ArrayList<>();

        for (int i = 0; i < nodes.size(); i++) {
            allLevelNodes[nodes.get(i).level % 2].add(nodes.get(i));
        }

        if (allLevelNodes[0].size() < allLevelNodes[1].size()) {
            return print(allLevelNodes[0]);
        } else {
            return print(allLevelNodes[1]);
        }


    }

    private StringBuilder print(ArrayList<Node> allLevelNode) {
        StringBuilder sb = new StringBuilder();
        sb.append(allLevelNode.size());
        sb.append("\n");


        for (Node n : allLevelNode) {
            sb.append((n.index + 1) + " ");
        }
        sb.append("\n");
        return sb;
    }
}


class Node {
    int index;
    int level;
    boolean isVisited = false;

    @java.beans.ConstructorProperties({"index"})
    public Node(int index) {
        this.index = index;
    }

}