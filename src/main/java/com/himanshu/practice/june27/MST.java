package com.himanshu.practice.june27;

import lombok.AllArgsConstructor;
import sun.awt.image.ImageWatched;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by himanshubhardwaj on 27/06/18.
 */
public class MST {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.next());
        Graph graph = new Graph(n);

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(sc.next(), ",");
            int source = Integer.parseInt(st.nextToken());

            while (st.hasMoreTokens()) {
                int destinatioon = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());
                graph.insert(source, destinatioon, weight);
            }
        }
        graph.print();
        System.out.println();
        System.out.println();

        LinkedList<Edge> mstKurashkal = graph.KurashkalMST();
        System.out.println("Printing KurashkalMST");

        for (Edge e : mstKurashkal) {
            System.out.println(e);
        }

        System.out.println();
        System.out.println();
        System.out.println("@Prism MST");
        LinkedList<Edge> mstPrism = graph.prismMST();

        for (Edge e : mstPrism) {
            System.out.println(e);
        }



    }

}


class Graph {
    LinkedList<Edge> edges;
    LinkedList<Edge> adj[];
    int nodes;

    public Graph(int node) {
        this.nodes = node;
        edges = new LinkedList<>();
        adj = new LinkedList[node];

        for (int i = 0; i < node; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    public void insert(int source, int destination, int weight) {
        Edge edge = new Edge(source, destination, weight);
        edges.add(edge);
        adj[source].add(edge);
        adj[destination].add(edge);
    }

    //would return, if this set of graph contains cycle or not
    public boolean containsCycle(LinkedList<Edge> graph) {
        int[] visited = new int[adj.length];

        boolean containsCycle = false;
        for (int i = 0; i < visited.length && !containsCycle; i++) {
            if (visited[i] == 0) {
                containsCycle = containsCycleHelper((LinkedList<Edge>) graph.clone(), visited, i);
            }
        }
        System.out.println(graph + "\t\t" + containsCycle);

        return containsCycle;
    }

    //undirected

    private boolean containsCycleHelper(LinkedList<Edge> graph, int[] visited, int source) {
        if (visited[source] > 0) {
            return true;
        }
        visited[source] = 1;
        boolean containsCycle = false;

        for (int i = 0; i < graph.size() && (containsCycle == false); i++) {
            Edge edge = graph.get(i);
            if (edge.node1 == source || edge.node2 == source) {
                int anotherNode = (edge.node1 == source) ? edge.node2 : edge.node1;
                graph.remove(i);
                containsCycle = containsCycleHelper(graph, visited, anotherNode);
                graph.add(i, edge);
            }
        }
        return containsCycle;
    }

    public void print() {
        for (int i = 0; i < adj.length; i++) {
            System.out.print(i + ": ");
            for (int j = 0; j < adj[i].size(); j++) {
                System.out.print(adj[i].get(j));
            }
            System.out.println();
        }
    }


    public LinkedList<Edge> KurashkalMST() {
        System.out.println("@prismMST");
        for (int i = 0; i < adj.length; i++) {
            Collections.sort(adj[i]);
        }
        Collections.sort(edges);
        LinkedList<Edge> mst = new LinkedList<>();

        while (mst.size() < (adj.length - 1)) {
            for (int i = 0; i < edges.size(); i++) {
                Edge edge = edges.get(i);
                if (mst.contains(edge)) {
                    continue;
                }
                mst.addLast(edge);
                if (containsCycle(mst)) {
                    mst.removeLast();
                }
            }
        }
        return mst;
    }

    public LinkedList<Edge> prismMST() {
        LinkedList<Integer> visited = new LinkedList<>();
        LinkedList<Edge> edgeList = new LinkedList<Edge>();

        visited.addLast(0);
        prismMSTHelper(0, visited, edgeList);

        return edgeList;
    }

    private void prismMSTHelper(int node, LinkedList<Integer> visited, LinkedList<Edge> edgeList) {
        if (edgeList.size() == (adj.length - 1)) {
            return;
        }

        for (int i = 0; i < adj[node].size(); i++) {
            Edge edge = adj[node].get(i);
            if (edgeList.contains(edge)) {
                continue;
            }
            int neighboutNode = (edge.node1 == node) ? edge.node2 : edge.node1;
            if (visited.contains(neighboutNode)) {
                continue;
            }
            visited.addLast(neighboutNode);
            edgeList.addLast(edge);
            if (containsCycle(edgeList)) {
                edgeList.removeLast();
                visited.removeLast();
            } else {
                prismMSTHelper(neighboutNode, visited, edgeList);
            }
        }
    }
}

@AllArgsConstructor
class Edge implements Comparable<Edge> {
    int node1;
    int node2;
    int weight;


    @Override
    public int compareTo(Edge o) {
        return this.weight - o.weight;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Edge) {
            Edge edge1 = (Edge) obj;
            if ((this.node1 == edge1.node1 && this.node2 == edge1.node2) || (this.node2 == edge1.node1 && this.node1 == edge1.node2)) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return "(" + node1 + ", " + node2 + ", " + weight + ") ";
    }
}

