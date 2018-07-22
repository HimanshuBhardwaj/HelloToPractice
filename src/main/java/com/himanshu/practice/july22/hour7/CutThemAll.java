package com.himanshu.practice.july22.hour7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * Created by himanshubhardwaj on 22/07/18.
 * Statement: https://codeforces.com/contest/982/problem/C
 * Algo: DFS
 * Submission: https://codeforces.com/contest/982/submission/40638434
 */
public class CutThemAll {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Tree tree = new Tree(n);

        for (int i = 0; i < (n - 1); i++) {
            String[] s = br.readLine().split(" ");
            tree.insert(Integer.parseInt(s[0]), Integer.parseInt(s[1]));
        }

        if (n % 2 == 1) {
            System.out.println(-1);
            return;
        }

        tree.DFS(0, -1);
        System.out.print(tree.deletedEdges);

    }

}

class Tree {
    LinkedList<Integer> edj[];
    int numNodes;
    int deletedEdges = 0;

    public Tree(int nuNodes) {
        edj = new LinkedList[nuNodes];
        for (int i = 0; i < nuNodes; i++) {
            edj[i] = new LinkedList<>();
        }
        this.numNodes = nuNodes;
    }


    public void insert(int source, int destination) {
        source--;
        destination--;
        edj[source].addLast(destination);
        edj[destination].addLast(source);

    }

    public int DFS(int node, int parent) {
        int numChild = 1;

        for (int neighbour : edj[node]) {
            if (neighbour != parent) {
                int subRootChildren = DFS(neighbour, node);
                if (subRootChildren % 2 == 0) {
                    deletedEdges++;
                }
                numChild += subRootChildren;
            }
        }
        return numChild;
    }

}
