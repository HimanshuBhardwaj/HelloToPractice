package com.himanshu.practice.july.july13.hour6;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Created by himanshubhardwaj on 13/07/18.
 */
public class Bridges {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);
        Graph graph = new Graph(n);

        for (int i = 0; i < m; i++) {
            str = br.readLine().split(" ");
            int source = Integer.parseInt(str[0]);
            int destination = Integer.parseInt(str[1]);
            graph.insert(source, destination);
        }

        graph.printBridges();
    }
}

class Graph {
    LinkedList<Edge>[] edgeList;
    boolean[] visited;
    int discovery[];
    int finished[];
    int lowest[];
    int timer = 0;


    public Graph(int numNodes) {
        numNodes++;
        edgeList = new LinkedList[numNodes];
        visited = new boolean[numNodes];
        discovery = new int[numNodes];
        lowest = new int[numNodes];
        finished = new int[numNodes];

        for (int i = 0; i < numNodes; i++) {
            edgeList[i] = new LinkedList<>();
        }
    }

    public void insert(int source, int destination) {
        Edge edge = new Edge(source, destination, false);
        edgeList[source].addLast(edge);
        edgeList[destination].addLast(edge);
    }

    private void bridges(int root, int par) {
        if (visited[root]) {
            return;
        }
        visited[root] = true;
        timer++;

        discovery[root] = lowest[root] = timer;

        for (Edge e : edgeList[root]) {
            int neighbour = (e.node1 == root) ? e.node2 : e.node1;

            if (neighbour != par && !visited[neighbour]) {
                bridges(neighbour, root);
                int temp = lowest[neighbour];
                if (discovery[root] < temp) {
                    e.isBridge = true;
                }
            }
        }

        for (Edge e : edgeList[root]) {
            int neighbour = (e.node1 == root) ? e.node2 : e.node1;
            if (neighbour != par) {
                lowest[root] = Math.min(lowest[neighbour], lowest[root]);
            }
        }

        finished[root] = ++timer;
    }

    public void printBridges() {
        for (int i = 1; i < edgeList.length; i++) {
            if (!visited[i]) {
                bridges(i, -1);
            }
        }
        Set<Edge> printed = new HashSet<>();

        for (LinkedList<Edge> el : edgeList) {
            for (Edge e : el) {
                if (e.isBridge && !printed.contains(e)) {
                    printed.add(e);
                    System.out.println(e);
                }
            }

        }
    }

}


@EqualsAndHashCode
@AllArgsConstructor
@ToString
class Edge {
    int node1;
    @EqualsAndHashCode.Exclude
    int node2;
    @EqualsAndHashCode.Exclude
    boolean isBridge;
}