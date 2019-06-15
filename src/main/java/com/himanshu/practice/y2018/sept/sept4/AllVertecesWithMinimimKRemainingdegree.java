package com.himanshu.practice.y2018.sept.sept4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;
import java.util.TreeSet;

/**
 * Created by himanshubhardwaj on 04/09/18.
 * ProblemStateent: https://codeforces.com/contest/1037/problem/E
 * Algo: DGraph, Ad-Hoc
 * Submission: https://codeforces.com/contest/1037/submission/42457749
 */
public class AllVertecesWithMinimimKRemainingdegree {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");

        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);
        int k = Integer.parseInt(str[2]);

        Graph g = new Graph(n, m);

        for (int i = 0; i < m; i++) {
            str = br.readLine().split(" ");
            int s = Integer.parseInt(str[0]) - 1;
            int d = Integer.parseInt(str[1]) - 1;
            g.insert(s, d);
        }
        g.computeResult(k);
    }

}


class Graph {
    HashSet<Integer>[] adjList;
    ArrayList<Edge> edgeList;
    int n;

    public Graph(int n, int m) {
        adjList = new HashSet[n];
        edgeList = new ArrayList<>(m);
        this.n = n;

        for (int i = 0; i < n; i++) {
            adjList[i] = new HashSet<>();
        }
    }

    void insert(int source, int destination) {
        adjList[source].add(destination);
        adjList[destination].add(source);
        edgeList.add(new Edge(source, destination));
    }


    void computeResult(int k) {
        HashSet<Integer> kDegreeSet = getKDegreeSets(k);
        Stack<Integer> stack = new Stack<>();
        for (int i = edgeList.size() - 1; i >= 0; i--) {
            stack.push(kDegreeSet.size());
            Edge edge = edgeList.get(i);
            if (kDegreeSet.contains(edge.destination) && kDegreeSet.contains(edge.source)) {
                removeEdge(kDegreeSet, edge.source, edge.destination, k);
            }
        }

        PrintWriter pr = new PrintWriter(System.out);

        while (!stack.isEmpty()) {
            pr.append(String.valueOf(stack.pop()));
            pr.append("\n");
        }
        pr.flush();
        pr.close();

    }


    void removeEdge(HashSet<Integer> kDegreeSet, int source, int destination, int k) {
        adjList[source].remove(destination);
        adjList[destination].remove(source);
        TreeSet<Integer> set = new TreeSet<>();

        if (adjList[source].size() < k) {
            set.add(source);
        }

        if (adjList[destination].size() < k) {
            set.add(destination);
        }

        while (!set.isEmpty()) {
            int n = set.pollFirst();
            kDegreeSet.remove(n);
            if (adjList[n] == null) {
                continue;
            }
            for (int x : adjList[n]) {
                if (adjList[x] != null) {
                    adjList[x].remove(n);
                    if (adjList[x].size() < k) {
                        set.add(x);
                    }
                }
                adjList[n] = null;
            }
        }
    }

    HashSet<Integer> getKDegreeSets(int k) {
        TreeSet<Integer> set = new TreeSet<>();


        for (int i = 0; i < n; i++) {
            if (adjList[i].size() < k) {
                set.add(i);
            }
        }


        while (!set.isEmpty()) {
            //System.out.println(set);
            int n = set.pollFirst();
            if (adjList[n] == null) {
                continue;
            }
            for (int x : adjList[n]) {
                if (adjList[x] != null) {
                    adjList[x].remove(n);
                    if (adjList[x].size() < k) {
                        set.add(x);
                    }
                }
                adjList[n] = null;
            }
        }

        HashSet<Integer> returnNodes = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (adjList[i] != null) {
                if (adjList[i].size() < k) {
                    //System.out.println(i + "\t" + adjList[i]);
                    //throw new RuntimeException("Something is wrong");
                } else {
                    returnNodes.add(i);
                }
            }
        }

        //System.out.println(returnNodes);
        return returnNodes;
    }

    public void printGraph() {
        System.out.println("@printGraph");

        for (int i = 0; i < n; i++) {
            System.out.print(i + ": ");
            for (int x : adjList[i]) {
                System.out.print(x + ",");
            }
            System.out.println();
        }
        System.out.println();
    }
}

class Edge {
    int source;
    int destination;

    public Edge(int source, int destination) {
        this.source = source;
        this.destination = destination;
    }
}


/*

9 11 2
1 2
1 3
1 4
3 4
3 5
4 5
5 8
5 6
5 7
7 6
8 9

* */