package com.himanshu.practice.y2019.April.april9;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by himanshubhardwaj on 09/04/19.
 * Statement: https://codeforces.com/contest/1139/problem/C
 * Algo: Connected Component
 * Submission: https://codeforces.com/contest/1139/submission/52537867
 */
public class EdgyTrees {
    static long mod = 1000000007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");

        int n = Integer.parseInt(str[0]);
        int k = Integer.parseInt(str[1]);

        Tree tree = new Tree(n);

        for (int i = 0; i < (n - 1); i++) {
            str = br.readLine().split(" ");
            int source = Integer.parseInt(str[0]) - 1;
            int destination = Integer.parseInt(str[1]) - 1;
            boolean isGood = (Integer.parseInt(str[2]) == 1) ? false : true;
            tree.insert(source, destination, isGood);
        }
        long sum = 0;
        for (int x : tree.connectedComponents(n)) {
          //  System.out.print(x+", ");
            sum += powMod(x, k);
            sum = sum % mod;
        }
        //System.out.println("------");
        //System.out.println(tree.count);


        System.out.print((powMod(n, k) - sum+mod) % mod);

    }


    static long powMod(long a, long x) {
        long temp = 1;
        for (int i = 1; i <= x; i++) {
            temp = (temp * a) % mod;
        }
        return temp;
    }
}


class Tree {
    ArrayList<Integer>[] neighbours = null;
    int count = 0;


    public Tree(int n) {
        neighbours = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            neighbours[i] = new ArrayList<>();
        }
    }


    void insert(int source, int destination, boolean isGood) {
        if (isGood) {
            neighbours[source].add(destination);
            neighbours[destination].add(source);
            count++;
        }
    }

    ArrayList<Integer> connectedComponents(int n) {
        boolean[] reached = new boolean[n];
        ArrayList<Integer> connectedComponentSize = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (!reached[i]) {
                connectedComponentSize.add(connectedComponentSize(i, -1, reached));
            }
        }


        return connectedComponentSize;
    }

    private Integer connectedComponentSize(int node, int parent, boolean[] reached) {
        if (reached[node]) {
            return 0;
        }

        reached[node] = true;
        int count = 1;

        for (int neightbour : neighbours[node]) {
            if (neightbour != parent) {
                count += connectedComponentSize(neightbour, node, reached);
            }
        }
        return count;
    }
}