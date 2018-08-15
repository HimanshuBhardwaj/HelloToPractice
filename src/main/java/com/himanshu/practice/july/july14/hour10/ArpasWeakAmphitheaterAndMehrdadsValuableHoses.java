package com.himanshu.practice.july.july14.hour10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

/**
 * Created by himanshubhardwaj on 15/07/18.
 * Problem set: https://codeforces.com/problemset/problem/741/B
 * Algo: DP, Tree, dfsHelper, ConnectedComponents, Beautiful question
 * Submission: https://codeforces.com/contest/741/submission/40386853
 */
public class ArpasWeakAmphitheaterAndMehrdadsValuableHoses {
    public static void main(String[] args) throws IOException {
        int n, m, W;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");

        n = Integer.parseInt(str[0]);
        m = Integer.parseInt(str[1]);
        W = Integer.parseInt(str[2]);

        ArrayList<Node> list = new ArrayList<>(n);

        str = br.readLine().split(" ");
        for (int i = 0; i < str.length; i++) {
            list.add(new Node());
            list.get(i).weight = Integer.parseInt(str[i]);
            list.get(i).index = i;
        }

        str = br.readLine().split(" ");
        for (int i = 0; i < str.length; i++) {
            list.get(i).beauty = Integer.parseInt(str[i]);
        }


        Graph graph = new Graph(n);
        for (int i = 0; i < m; i++) {
            str = br.readLine().split(" ");
            int s = Integer.parseInt(str[0]) - 1;
            int d = Integer.parseInt(str[1]) - 1;
            graph.insert(list.get(s), list.get(d));
        }

        ArrayList<Node> newList = graph.DFS(list, n);

        int prev = -1;
        int currentContainer = 1;
        for (int i = 0; i < newList.size(); i++) {
            if (currentContainer == list.get(i).cluster) {
                list.get(i).justBeforStarting = prev;
            } else {
                currentContainer++;
                prev = i - 1;
                list.get(i).justBeforStarting = prev;
            }
        }

//        for (int i = 0; i < newList.size(); i++) {
//            System.out.println(list.get(i));
//        }

        long knapsack[][] = new long[list.size()][W + 1];
        long clusterMax[][] = new long[list.get(list.size() - 1).cluster + 1][W + 1];

        for (int j = 0; j <= W; j++) {
            if (j >= list.get(0).weight) {
                knapsack[0][j] = list.get(0).beauty;
                clusterMax[list.get(0).cluster][j] = Math.max(clusterMax[list.get(0).cluster][j], knapsack[0][j]);
            }
        }


        for (int i = 1; i < list.size(); i++) {
            for (int j = 0; j <= W; j++) {
                if (j >= list.get(i).weight) {
                    if (list.get(i).justBeforStarting >= 0) {
                        int previousCluster = list.get(list.get(i).justBeforStarting).cluster;
                        knapsack[i][j] = Math.max(knapsack[i - 1][j], list.get(i).beauty + clusterMax[previousCluster][j - list.get(i).weight]);
                    } else {
                        knapsack[i][j] = Math.max(knapsack[i - 1][j], list.get(i).beauty);
                    }
                } else {
                    knapsack[i][j] = knapsack[i - 1][j];
                }
                clusterMax[list.get(i).cluster][j] = Math.max(clusterMax[list.get(i).cluster][j], knapsack[i][j]);
            }
        }


//        System.out.println();
//        for (int i = 0; i < list.size(); i++) {
//            //System.out.print(knapsack[i][W]);
//            for (int j = 0; j <= W; j++) {
//                System.out.print(knapsack[i][j] + "\t");
//            }
//            System.out.println();
//        }

        System.out.print(knapsack[list.size() - 1][W]);


    }
}


class Graph {
    LinkedList<Node> edj[];

    public Graph(int n) {
        edj = new LinkedList[n];

        for (int i = 0; i < n; i++) {
            edj[i] = new LinkedList<>();
        }
    }


    public void insert(Node source, Node destination) {
        edj[source.index].addLast(destination);
        edj[destination.index].addLast(source);
    }

    public ArrayList<Node> DFS(ArrayList<Node> list, int n) {
        LinkedList<Node> listOfCluster = new LinkedList<>();
        int clusterId = 1;
        boolean visited[] = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                Node cluster = new Node();
                cluster.cluster = clusterId;
                cluster.index = n + clusterId - 1;
                DFSHelper(i, visited, cluster, clusterId, list);
                listOfCluster.addLast(cluster);
                clusterId++;
            }
        }
//        for (DNode c : listOfCluster) {
//            System.out.println(c);
//        }
        list.addAll(listOfCluster);
        Collections.sort(list);
        return list;
    }

    private void DFSHelper(int node, boolean[] visited, Node cluster, int clusterId, ArrayList<Node> list) {
        if (visited[node]) {
            return;
        }

        visited[node] = true;
        list.get(node).cluster = clusterId;
        cluster.beauty += list.get(node).beauty;
        cluster.weight += list.get(node).weight;


        for (Node n : edj[node]) {
            if (!visited[n.index]) {
                DFSHelper(n.index, visited, cluster, clusterId, list);
            }
        }
    }


}

class Node implements Comparable<Node> {
    int index;
    int beauty;
    int weight;
    int cluster;
    int justBeforStarting;

    public Node() {
    }

    @Override
    public int compareTo(Node o) {
        return this.cluster - o.cluster;
    }

    public String toString() {
        return "DNode(index=" + this.index + ", beauty=" + this.beauty + ", weight=" + this.weight + ", cluster=" + this.cluster + ", justBeforStarting=" + this.justBeforStarting + ")";
    }
}


/*
9 7 17
2 1 3 5 4 2 1 2 1
3 1 5 2 1 3 1 5 5
1 2
2 0
0 3
2 3
4 5
5 6
7 8

* */

/*
Another test case:
10 5 10
7 6 1 6 2 8 1 6 8 1
5 5 2 2 3 8 9 8 7 3
1 7
2 4
3 6
5 7
1 5

*
* */