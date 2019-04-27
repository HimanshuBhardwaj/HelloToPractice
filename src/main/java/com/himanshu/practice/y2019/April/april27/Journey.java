package com.himanshu.practice.y2019.April.april27;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;

/**
 * Created by himanshubhardwaj on 27/04/19.
 * 3:51 -- 4:52pm
 * Expected time: 30 mins
 * Time which I took 1 hour. Because I was making it complicated
 * Algo: Expected Time in tree traversal
 * Statement: https://codeforces.com/contest/839/problem/C
 * Submission: https://codeforces.com/contest/839/submission/53413868
 */
public class Journey {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Tree t = new Tree(n);
        String str[];

        for (int i = 0; i < (n - 1); i++) {
            str = br.readLine().split(" ");
            int v1 = Integer.parseInt(str[0]);
            int v2 = Integer.parseInt(str[1]);
            t.insert(v1, v2);
        }

        t.DFS(0, -1, 0, 1d);
        System.out.print(t.getExpectedPAth());
    }
}

class Tree {
    private ArrayList<Integer>[] adjList;
    private int size;
    private double expectedPAth = 0d;

    public Tree(int size) {
        adjList = new ArrayList[size];
        this.size = size;
        for (int i = 0; i < size; i++) {
            adjList[i] = new ArrayList<>();
        }
    }

    public void insert(int v1, int v2) {
        v1--;
        v2--;
        adjList[v1].add(v2);
        adjList[v2].add(v1);
    }


    void DFS(int node, int parent, int currentLength, double currentProbablity) {

        if (parent != -1 && adjList[node].size() == 1) {
            expectedPAth += currentLength * currentProbablity;
        }

        currentProbablity /= (parent == -1) ? (adjList[node].size()) : (adjList[node].size() - 1);


        for (int neighbour : adjList[node]) {
            if (neighbour != parent) {

                DFS(neighbour, node, currentLength + 1, currentProbablity);
            }
        }
    }


    public double getExpectedPAth() {
        return this.expectedPAth;
    }
}
