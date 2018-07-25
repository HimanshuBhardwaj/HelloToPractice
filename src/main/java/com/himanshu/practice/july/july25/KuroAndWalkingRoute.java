package com.himanshu.practice.july.july25;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by himanshubhardwaj on 25/07/18.
 * Statement: https://codeforces.com/contest/979/problem/C
 * Algo: DFS, Num of PAths
 * Submission: https://codeforces.com/contest/979/submission/40749805
 */
public class KuroAndWalkingRoute {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int x = Integer.parseInt(str[1]);
        int y = Integer.parseInt(str[2]);

        Tree tree = new Tree(n, x - 1, y - 1);


        for (int i = 1; i <= (n - 1); i++) {
            str = br.readLine().split(" ");
            int s = Integer.parseInt(str[0]);
            int d = Integer.parseInt(str[1]);
            tree.insert(s, d);
        }
        //tree.printTree();
        tree.DFS(x - 1, 0, x - 1, -1);
        tree.DFS(y - 1, 0, y - 1, -1);
        //System.out.println(tree.numChildNodes[x - 1] + " " + tree.numChildNodes[y - 1]);
        System.out.println((((n * ((long) n - 1l))) - ((long)tree.numChildNodes[x - 1] * tree.numChildNodes[y - 1])));
    }
}

class Tree {
    LinkedList<Integer>[] edgeList;
    HashSet<Integer> twoSets = new HashSet<>();
    int[] numChildNodes;


    public Tree(int numNodes, int x, int y) {
        edgeList = new LinkedList[numNodes];
        numChildNodes = new int[numNodes];
        for (int i = 0; i < edgeList.length; i++) {
            edgeList[i] = new LinkedList<>();
        }
        twoSets.add(x);
        twoSets.add(y);
    }


    public void insert(int source, int destination) {
        source--;
        destination--;
        edgeList[source].add(destination);
        edgeList[destination].add(source);
    }

    //state 0 normal
    //state 1 means we found second one
    public void DFS(int root, int state, int startState, int parent) {
        if (root != startState) {
            if (twoSets.contains(root)) {
                state++;
            }
        }

       // System.out.println((root + 1) + " " + state + " " + (startState + 1));

        numChildNodes[startState] += state;

        for (int neighbour : edgeList[root]) {
            if (neighbour != parent) {
                DFS(neighbour, state, startState, root);
            }
        }
    }


    public void printTree() {
        for (int i = 0; i < edgeList.length; i++) {
            System.out.print(i + ":  ");
            for (int x : edgeList[i]) {
                System.out.print(x + " ");
            }
            System.out.println();
        }
    }
}
/*
*
Input

6 2 4
1 2
2 3
2 4
4 5
5 6


* */