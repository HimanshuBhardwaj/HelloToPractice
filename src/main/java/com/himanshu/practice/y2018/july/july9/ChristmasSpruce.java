package com.himanshu.practice.y2018.july.july9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * Created by Himanshu Bhardwaj on 09/07/18.
 *Algo: PTree traversal
 *Submission: https://codeforces.com/contest/913/submission/40140598
 */
public class ChristmasSpruce {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numNodes = Integer.parseInt(br.readLine());
        Tree tree = new Tree(numNodes);
        int[] parentA = new int[numNodes + 1];
        Tree treeTransPose = new Tree(numNodes);

        for (int i = 2; i <= numNodes; i++) {
            int child = i;
            int parent = Integer.parseInt(br.readLine());
            parentA[i] = parent;
            tree.insert(parent, child);
        }
        tree.root = getRoot(parentA);


//        System.out.println();
//        System.out.println("Root: " + tree.root);
//        tree.printTree(tree.root);
//        System.out.println();

        System.out.print((tree.isSpruce(tree.root) >= 3) ? "Yes" : "No");

//        System.out.println(tree.isSpruce(tree.root));

    }

    private static int getRoot(int[] parent) {
//        for (int i = 1; i < parent.length; i++) {
//            System.out.print(parent[i] + " ");
//        }
//        System.out.println();


        int node = parent[parent.length - 1];

        while (parent[node] != 0) {
            node = parent[node];
        }
        return node;
    }
}


class Tree {
    LinkedList<Integer>[] adjList;

    int root = Integer.MIN_VALUE;


    public Tree(int numNodes) {
        adjList = new LinkedList[numNodes + 1];

        for (int i = 0; i <= numNodes; i++) {
            adjList[i] = new LinkedList<>();
        }
    }

    public void insert(int source, int destination) {
        adjList[source].addLast(destination);
    }


    public int isSpruce(int node) {

        if (adjList[node].size() == 0) {
            return 1;
        }

        int countChildLeaf = 0;
        for (int i = 0; i < adjList[node].size(); i++) {
            int childLeaf = isSpruce(adjList[node].get(i));
            if (childLeaf < 3 && (adjList[adjList[node].get(i)].size() > 0)) {
                return Integer.MIN_VALUE;
            }
            if (adjList[adjList[node].get(i)].size() == 0) {
                countChildLeaf++;
            }
        }
        if (countChildLeaf < 3) {
            return Integer.MIN_VALUE;
        }
        return countChildLeaf;
    }


    void printTree(int root) {
        System.out.print(root + ", ");
        for (int i = 0; i < adjList[root].size(); i++) {
            printTree(adjList[root].get(i));
        }
    }

}
