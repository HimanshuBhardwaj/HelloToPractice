package com.himanshu.practice.y2019.june.june8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Created by himanshubhardwaj on 08/06/19.
 *  Statement: https://codeforces.com/contest/1056/problem/D
 *  Algo: DFS
 *  Submission: https://codeforces.com/contest/1056/submission/55297564
 */
public class DecorateAppleTree {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Tree tree = new Tree(n);

        if (n == 1) {
            System.out.println(1);
            return;
        }

        String[] parentStr = br.readLine().split(" ");

        for (int i = 0; i < parentStr.length; i++) {
            tree.insert(Integer.parseInt(parentStr[i]) - 1, i + 1);
        }

        //tree.printTree();
        tree.computeChildren(0, -1);
        //System.out.println(tree.numChidren);

        System.out.println(tree.getMnimumColours());


    }
}


class Tree {
    Node[] nodes;
    ArrayList<Node>[] adjList;
    int numNodes;
    PriorityQueue<Node> numChidren = new PriorityQueue<>();
    private boolean mnimumColours;


    public Tree(int numNodes) {
        this.numNodes = numNodes;
        adjList = new ArrayList[numNodes];
        nodes = new Node[numNodes];

        for (int i = 0; i < numNodes; i++) {
            adjList[i] = new ArrayList<>();
            nodes[i] = new Node(i, 0);
        }
    }

    public void insert(int source, int destination) {
        adjList[source].add(nodes[destination]);
        adjList[destination].add(nodes[source]);
    }

    public int computeChildren(int root, int parent) {
        // System.out.println(root);
        if (isLeaf(root)) {
            nodes[root].numChildren = 1;
            numChidren.add(nodes[root]);
            return 1;
        }

        int count = 0;
        for (Node neighbour : adjList[root]) {
            if (neighbour.index != parent) {
                count += computeChildren(neighbour.index, root);
            }
        }

        nodes[root].numChildren = count;
        numChidren.add(nodes[root]);
        return count;
    }

    private boolean isLeaf(int root) {
        if (root == 0) {
            return adjList[root].size() == 0;

        } else {
            return adjList[root].size() == 1;
        }
    }

    public void printTree() {
        System.out.println();
        System.out.println("Size: " + numNodes);

        for (int i = 0; i < adjList.length; i++) {
            System.out.print(i + ": ");
            for (Node n : adjList[i]) {
                System.out.print(n.index + ", ");
            }
            System.out.println();
        }
    }


    public String getMnimumColours() {
        //System.out.println(numChidren);
        //System.out.println();
        StringBuilder stringBuffer = new StringBuilder();
        while (!numChidren.isEmpty()) {
            stringBuffer.append(numChidren.poll().numChildren + " ");
        }

        return stringBuffer.toString();
    }
}

class Node implements Comparable<Node> {
    int index;
    int numChildren;

    @java.beans.ConstructorProperties({"index", "numChildren"})
    public Node(int index, int numChildren) {
        this.index = index;
        this.numChildren = numChildren;
    }

    @Override
    public int compareTo(Node o) {
        if (this.numChildren != o.numChildren) {
            return this.numChildren - o.numChildren;
        } else {
            return this.index - o.index;
        }
    }

    public String toString() {
        return "Node(index=" + this.index + ", numChildren=" + this.numChildren + ")\n";
    }
}