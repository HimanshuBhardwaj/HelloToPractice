package com.himanshu.practice.y2019.june.june11;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by himanshubhardwaj on 11/06/19.
 */
public class AsyaAndKittens {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        TGraph graph = new TGraph(n);

        for (int i = 0; i < (n - 1); i++) {
            String str[] = br.readLine().split(" ");
            graph.insert(Integer.parseInt(str[0]), Integer.parseInt(str[1]));
        }

        System.out.println(graph.DFSSolution().toString());
    }
}

class TGraph {
    int numNodes;
    ArrayList<Node> adjList[];
    Node[] nodes;
    Integer root = null;
    Integer last;

    public TGraph(int n) {
        this.numNodes = n;
        adjList = new ArrayList[n];
        nodes = new Node[n];

        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new Node(i);
            adjList[i] = new ArrayList<>();
        }
    }


    public void insert(int v1, int v2) {
        v1--;
        v2--;
        adjList[v1].add(nodes[v2]);
        adjList[v2].add(nodes[v1]);
        if (root == null) {
            this.root = v1;
        }
    }

    public StringBuilder DFSSolution() {

        HashSet<Integer> visired = new HashSet<>();
        for (int i = 0; i < numNodes; i++) {
            if (!visired.contains(i)) {
                DFSSolutionHelper(i, visired);
            }
        }

        StringBuilder sb = new StringBuilder();
        //System.out.println("Last: " + last);
        DFSSolutionComputation(last, -1, sb, new HashSet<>());
        return sb;
    }


    private void DFSSolutionComputation(int index, int parent, StringBuilder sb, HashSet<Integer> visited) {
        if (visited.contains(index)) {
            return;
        }
        visited.add(index);
        sb.append((index + 1) + " ");

        for (Node neighbour : adjList[index]) {
            if (neighbour.index != parent) {
                DFSSolutionComputation(neighbour.index, index, sb, visited);
            }
        }
    }

    //this will count number of children reachable from index
    private void DFSSolutionHelper(int index, HashSet<Integer> visited) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(index);
        visited.add(index);

        while (!queue.isEmpty()) {
            last = queue.poll();

            for (Node n : adjList[last]) {
                if (!visited.contains(n.index)) {
                    visited.add(n.index);
                }
            }
        }
    }
}


class Node {
    int index;
    Integer numChildReachable;

    @java.beans.ConstructorProperties({"index"})
    public Node(int index) {
        this.index = index;
        this.numChildReachable = null;
    }

}
