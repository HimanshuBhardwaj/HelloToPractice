package com.himanshu.practice.Aug.aug14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * Created by himanshubhardwaj on 14/08/18.
 * Grab Test
 */
public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = 1;
        int arr[] = new int[n];
        arr[0] = 0;
//        arr[0] = 1;
//        arr[1] = 1;
//        arr[2] = 1;
//        arr[3] = 1;
//        arr[4] = 0;
//        arr[5] = 4;
//        arr[6] = 4;


        SolutionC solution = new SolutionC();
        System.out.println(solution.solution(arr).length);


    }

}


class SolutionC {
    public int[] solution(int[] T) {
        // write your code in Java SE 8
        Tree tree = new Tree(T.length);
        for (int i = 0; i < T.length; i++) {
            tree.insert(i, T[i]);
        }
        tree.DFS(tree.getRoot(), tree.getRoot(), 0);
        for (int x : tree.getDepthArray()) {
            System.out.print(x + " ");
        }

        return tree.getDepthArray();
    }
}


class Tree {
    private LinkedList<Integer>[] edgeList;
    private int numNodes;
    private int root;
    private int depthArray[];

    public Tree(int numNodes) {
        this.numNodes = numNodes;
        edgeList = new LinkedList[numNodes];
        depthArray = new int[this.numNodes - 1];

        for (int i = 0; i < numNodes; i++) {
            edgeList[i] = new LinkedList<>();
        }
    }

    public void insert(int node1, int node2) {
        if (node1 == node2) {
            root = node1;
        } else {
            // assume tree is undirected
            edgeList[node1].addLast(node2);
            edgeList[node2].addLast(node1);
        }
    }

    public void DFS(int root, int parent, int depth) {
        if (depth > 0) {
            depthArray[depth - 1]++;
        }

        for (int neighbour : edgeList[root]) {
            if (neighbour != parent) {
                DFS(neighbour, root, depth + 1);
            }
        }
    }

    public int[] getDepthArray() {
        return this.depthArray;
    }

    public int getRoot() {
        return this.root;
    }
}

