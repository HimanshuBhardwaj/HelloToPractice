package com.himanshu.practice.july9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by himanshubhardwaj on 09/07/18.
 * TREE: DFS
 * https://codeforces.com/contest/580/submission/40105211
 *
 */
public class KefaAndPark {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        boolean containsCat[] = new boolean[n + 1];
        st = new StringTokenizer(br.readLine(), " ");

        for (int i = 1; i <= n; i++) {
            containsCat[i] = ("1".equals(st.nextToken())) ? true : false;
        }
        Graph graph = new Graph(n);

        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int source = Integer.parseInt(st.nextToken());
            int destination = Integer.parseInt(st.nextToken());
            graph.insert(source, destination);
        }
        st = null;
        boolean visited[] = new boolean[n + 1];
        System.out.print(graph.numNodesSatisfyingConstraint(1, containsCat, (containsCat[1]) ? 1 : 0, m, visited));


    }
}


class Graph {
    ArrayList<Integer> edgeList[];
    int numNodes;

    public Graph(int n) {
        numNodes = n;
        edgeList = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            edgeList[i] = new ArrayList<>(100);
        }
    }

    public void insert(int source, int destination) {
        edgeList[source].add(destination);
        edgeList[destination].add(source);
    }

    public int numNodesSatisfyingConstraint(int node, boolean[] containsCat, int commulativeCount, int maxCat, boolean[] visited) {
        if (maxCat < commulativeCount) {
            return 0;
        }
        visited[node] = true;

        boolean flag = true;


        int sum = 0;
        for (int i = 0; i < edgeList[node].size(); i++) {
//            System.out.println(node + ": " + edgeList[node].get(i));

            if (!visited[edgeList[node].get(i)]) {
                flag = false;
                sum += (!visited[edgeList[node].get(i)]) ?
                        numNodesSatisfyingConstraint(edgeList[node].get(i), containsCat, (containsCat[edgeList[node].get(i)] ? commulativeCount + 1 : 0), maxCat, visited)
                        : 0;
            }
        }

        return (flag) ? 1 : sum;
    }
}