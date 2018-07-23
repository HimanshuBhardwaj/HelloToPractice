package com.himanshu.practice.july.july5.hour9;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by Himanshu Bhardwaj on 05/07/18.
 * 10:40
 * not completed; printng some but not all cycles
 */
public class AllCyclesInDirectedGraph {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringTokenizer st = new StringTokenizer(sc.next(), ",");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        Graph graph = new Graph(n);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(sc.next(), ",");
            int source = Integer.parseInt(st.nextToken());
            int destination = Integer.parseInt(st.nextToken());
            graph.insert(source, destination);
        }
        graph.printAllCycles();
    }
}

class Graph {
    boolean adj[][];
    int numNodes;

    public Graph(int numNodes) {
        this.numNodes = numNodes;
        adj = new boolean[numNodes][numNodes];
    }

    public void insert(int source, int destination) {
        adj[source][destination] = true;
    }

    public void printAllCycles() {
        int visited[] = new int[numNodes];

        for (int i = 0; i < numNodes; i++) {
            if (visited[i] == 0) {
                LinkedList<Integer> path = new LinkedList<>();
                path.add(i);
                printAllCyclesHelper(i, path, visited);
            }
        }
    }

    private void printAllCyclesHelper(int node, LinkedList<Integer> path, int[] visited) {
        System.out.println(path);
        if (visited[node] == 1) {
            System.out.print("Cycle\t");
            boolean flag = false;
            ;
            for (int i = 0; i < path.size(); i++) {
                if (path.get(i) == node) {
                    flag = true;
                }
                if (flag) {
                    System.out.print(path.get(i) + " ");
                }
            }
            System.out.println();
            return;
        }
        if (visited[node] == 2) {
            return;
        }

        visited[node] = 1;

        for (int i = 0; i < adj.length; i++) {
            if (adj[node][i]) {
                path.addLast(i);
                printAllCyclesHelper(i, path, visited);
                path.removeLast();
            }
        }

        visited[node] = 2;
    }


}
