package com.himanshu.practice.nov.nov6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by himanshubhardwaj on 06/11/18.
 * Statement: https://codeforces.com/contest/893/problem/C
 * Algo: Connected componenet
 * Submission: https://codeforces.com/contest/893/submission/45374307
 */
public class Rumor {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");

        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);

        str = br.readLine().split(" ");
        long[] costs = new long[n];


        for (int i = 0; i < n; i++) {
            costs[i] = Long.parseLong(str[i]);
        }

        Graph g = new Graph(n, costs);

        for (int i = 0; i < m; i++) {
            str = br.readLine().split(" ");
            g.insert(Integer.parseInt(str[0])-1, Integer.parseInt(str[1])-1);
        }

        System.out.print(g.computeCost());


    }
}


class Graph {
    ArrayList<Integer> adjList[];
    long costs[];
    boolean[] visited;

    public Graph(int n, long[] cost) {
        adjList = new ArrayList[n];
        this.costs = cost;
        visited = new boolean[n];

        for (int i = 0; i < adjList.length; i++) {
            adjList[i] = new ArrayList<>();
        }
    }

    public void insert(int source, int destination) {
        adjList[source].add(destination);
        adjList[destination].add(source);
    }

    long computeCost() {
        long cost = 0;

        for (int i = 0; i < costs.length; i++) {
            if (!visited[i]) {
                cost += computeCostHelper(i, -1);
            }
        }
        return cost;
    }

    private long computeCostHelper(int root, int parent) {
        long cost = costs[root];
        visited[root] = true;

        for (int x : adjList[root]) {
            if (x != parent) {
                if (!visited[x]) {
                    cost = Math.min(cost, computeCostHelper(x, root));
                }
            }
        }

        return cost;
    }


}