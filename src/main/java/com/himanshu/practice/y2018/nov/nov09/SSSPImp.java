package com.himanshu.practice.y2018.nov.nov09;

import lombok.ToString;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by himanshubhardwaj on 12/11/18.
 * Grapgh Setup: 10 mins
 * bellmanFord: 20 mins;
 * dijkstra: 20 mins
 * Bellmanford: 12 mins
 * DFS: 20 mins; could be reduced to 10 mins
 * BFS: 10 mins
 * TOPOLOGICAL_SORT:
 */
public class SSSPImp {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);

        UGraph g = new UGraph(n);

        for (int i = 0; i < m; i++) {
            str = br.readLine().split(" ");
            g.insert(Integer.parseInt(str[0]), Integer.parseInt(str[1]), Integer.parseInt(str[2]));
        }

        g.print();

        int[] sssp = SSSP.bellmanFord(0, g);
        System.out.println("bellmanFord");
        for (int x : sssp) {
            System.out.print(x + ", ");
        }
        System.out.println();
        System.out.println("dijkstra");
        sssp = SSSP.dijkstra(0, g);
        for (int x : sssp) {
            System.out.print(x + ", ");
        }
        System.out.println();

        System.out.println("floydWarshall");
        int[][] apsp = SSSP.floydWarshall(g);
        for (int i = 0; i < apsp.length; i++) {
            System.out.print(apsp[0][i] + ", ");
        }
        System.out.println();

        System.out.println("DFS");
        g.DFS(0, new HashSet<>());

        System.out.println("BFS");
        g.BFS(0);

        System.out.println();
        System.out.println("TOPOLOGICAL Sort");
        g.topologicalSort();
    }
}

class UGraph {
    ArrayList<Edge> adjList[];


    public UGraph(int numNodes) {
        adjList = new ArrayList[numNodes];

        for (int i = 0; i < numNodes; i++) {
            adjList[i] = new ArrayList<>();
        }
    }


    //  undirected graph
    public void insert(int source, int destination, int weight) {
        Edge e = new Edge(source, destination, weight);
        adjList[source].add(e);
        adjList[destination].add(e);

    }

    public void print() {
        for (int i = 0; i < adjList.length; i++) {
            System.out.print(i + ": ");
            for (Edge x : adjList[i]) {
                System.out.print(x + ", ");
            }
            System.out.println();
        }
    }

    public void DFS(int source, HashSet<Integer> visited) {
        if (visited.contains(source)) {
            return;
        }

        visited.add(source);

        System.out.print(source + ", ");
        for (Edge e : adjList[source]) {
            int neighbour = (e.node2 != source) ? e.node2 : e.node1;
            DFS(neighbour, visited);

        }
    }

    public void BFS(int source) {
        Queue<Integer> queue = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();
        queue.add(source);
        visited.add(source);


        int level = 0;
        while (!queue.isEmpty()) {
            LinkedList<Integer> levelNodes = new LinkedList<>(queue);
            System.out.print("level: " + level + "\t");
            for (int x : levelNodes) {
                System.out.print(x + ",");
            }

            queue.removeAll(levelNodes);
            for (int x : levelNodes) {
                for (Edge neighbour : adjList[x]) {
                    int des = (neighbour.node1 != x) ? neighbour.node1 : neighbour.node2;
                    if (!visited.contains(des)) {
                        visited.add(des);
                        queue.add(des);
                    }
                }
            }
            level++;
            System.out.println();
        }
    }

    //This is wrong as Topologial insertionSort makes sense only in DAs
    public void topologicalSort() {
        boolean visited[] = new boolean[adjList.length];

        for (int i = 0; i < adjList.length; i++) {
            if (!visited[i]) {
                DFSTopological(i, visited);
            }
        }
    }

    private void DFSTopological(int source, boolean[] visited) {
        if (visited[source]) {
            return;
        }

        visited[source] = true;


        for (Edge neighbour : adjList[source]) {
            int des = (neighbour.node2 == source) ? neighbour.node1 : neighbour.node2;
            if (!visited[des]) {
                DFSTopological(des, visited);
            }
        }
        System.out.print(source + ", ");
    }
}


class Edge {
    int node1;
    int node2;
    int weight;

    public Edge(int source, int destination, int weight) {
        this.node1 = source;
        this.node2 = destination;
        this.weight = weight;
    }

    public String toString() {
        return "(" + this.node1 + ", " + this.node2 + ", " + this.weight + ")";
    }
}

@ToString
class Node implements Comparable<Node> {
    int index;
    int comulativedistance;

    public Node(int index, int comulativedistance) {
        this.index = index;
        this.comulativedistance = comulativedistance;
    }

    @Override
    public int compareTo(Node o) {
        return (this.comulativedistance - o.comulativedistance);
    }
}

class SSSP {

    static int[] bellmanFord(int source, UGraph g) {
        int sssp[] = new int[g.adjList.length];
        for (int i = 0; i < g.adjList.length; i++) {
            if (i == source) {
                sssp[i] = 0;
            } else {
                sssp[i] = Integer.MAX_VALUE;
            }
        }


        for (int i = 0; i < g.adjList.length; i++) {
            for (int j = 0; j < g.adjList.length; j++) {
                for (Edge k : g.adjList[j]) {
                    if (sssp[j] != Integer.MAX_VALUE) {
                        int s = j;
                        int d = (k.node1 == j) ? k.node2 : k.node1;
                        if (sssp[s] + k.weight < sssp[d]) {
                            sssp[d] = sssp[s] + k.weight;
                        }
                    }
                }
            }
        }
        return sssp;
    }

    static int[] dijkstra(int source, UGraph g) {
        int sssp[] = new int[g.adjList.length];
        Node[] nodes = new Node[g.adjList.length];
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();

        for (int i = 0; i < g.adjList.length; i++) {
            nodes[i] = new Node(i, Integer.MAX_VALUE);
            if (i == source) {
                nodes[i].comulativedistance = 0;
            }
            priorityQueue.add(nodes[i]);
        }

        while (!priorityQueue.isEmpty()) {
            Node top = priorityQueue.poll();

            if (top.comulativedistance != Integer.MAX_VALUE) {
                for (Edge neighbour : g.adjList[top.index]) {
                    int des = (neighbour.node1 == top.index) ? neighbour.node2 : neighbour.node1;
                    Node desNode = nodes[des];
                    if ((desNode.comulativedistance) > (top.comulativedistance + neighbour.weight)) {
                        priorityQueue.remove(desNode);
                        desNode.comulativedistance = top.comulativedistance + neighbour.weight;
                        priorityQueue.add(desNode);
                    }
                }
            }
            sssp[top.index] = top.comulativedistance;
        }
        return sssp;
    }


    //4:27 -- 4:39
    //
    static int[][] floydWarshall(UGraph g) {
        int n = g.adjList.length;
        int[][] distance = new int[n][n];


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                distance[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int i = 0; i < g.adjList.length; i++) {
            for (Edge e : g.adjList[i]) {
                distance[e.node1][e.node2] = e.weight;
                distance[e.node2][e.node1] = e.weight;
            }
            distance[i][i] = 0;
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if ((distance[i][k] != Integer.MAX_VALUE) && (distance[k][j] != Integer.MAX_VALUE)) {
                        if (distance[i][j] > (distance[i][k] + distance[k][j])) { //overflow will happen
                            distance[i][j] = distance[i][k] + distance[k][j];
                        }
                    }
                }
            }
        }
        return distance;
    }

}


/*

7 11
0 1 1
0 2 2
2 1 3
1 3 5
2 4 1
2 5 1
3 4 1
3 5 10
4 5 1
5 1 100
0 6 100



*
* */