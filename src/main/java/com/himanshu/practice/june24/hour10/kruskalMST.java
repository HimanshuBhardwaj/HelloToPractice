package com.himanshu.practice.june24.hour10;

import lombok.AllArgsConstructor;

import java.util.*;

/**
 * Created by Himanshu Bhardwaj on 24/06/18.
 */
public class kruskalMST {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numNodes = Integer.parseInt(sc.next());
        Graph graph = new Graph(numNodes);

        for (int i = 0; i < numNodes; i++) {
            StringTokenizer st = new StringTokenizer(sc.next(), ",");
            int source = Integer.parseInt(st.nextToken());

            while (st.hasMoreTokens()) {
                int destination = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());
                graph.insert(source, destination, weight);
            }
        }
        graph.print();
        System.out.println("ContainsCycle: " + graph.containsCycle(graph.allEdges));
        System.out.println();
        System.out.println();
        System.out.println();
        List<Edge> mst = graph.getMST();
        System.out.println("mst.leangth: " + mst.size());

        for (Edge edge : mst) {
            System.out.println(edge);
        }
    }
}


//undirectedGraph
class Graph {
    LinkedList<Edge> adjList[];
    LinkedList<Edge> allEdges;
    int numberNodes;

    public Graph(int nodeNum) {
        this.numberNodes = nodeNum;
        allEdges = new LinkedList<>();
        adjList = new LinkedList[nodeNum];

        for (int i = 0; i < nodeNum; i++) {
            adjList[i] = new LinkedList<Edge>();
        }
    }

    public void insert(int source, int destination, int weight) {
        Edge newEdge = new Edge(source, destination, weight);
        adjList[source].add(newEdge);
        allEdges.add(newEdge);
    }


    //return if these edges make cycle or not
    public boolean containsCycle(LinkedList<Edge> mst) {
        int[] visited = new int[numberNodes];
        boolean containsCycle = false;
        for (int i = 0; i < visited.length; i++) {
            if (visited[i] == 0) {
                containsCycle = containsCycle | containsCycleHelper(i, mst, visited);
            }
        }
        return containsCycle;
    }

    private boolean containsCycleHelper(int root, LinkedList<Edge> allEdges, int[] visited) {
        if (visited[root] == 1) {
            return true;
        }
        visited[root] = 1;

        boolean containsCycle = false;
        for (int i = 0; i < allEdges.size(); i++) {
            if (allEdges.get(i).node1 == root) {
                containsCycle = containsCycle | containsCycleHelper(allEdges.get(i).node2, allEdges, visited);
            }
        }
        return containsCycle;
    }

    public void print() {
        System.out.println("number of nodes: " + adjList.length);
        for (int i = 0; i < adjList.length; i++) {
            System.out.print(i + ": ");
            for (int j = 0; j < adjList[i].size(); j++) {
                System.out.print(adjList[i].get(j));
            }
            System.out.println();
        }
    }


    public List<Edge> getMST() {
        Collections.sort(allEdges);
        LinkedList<Edge> mst = new LinkedList<>();

        for (int i = 0; i < allEdges.size(); i++) {
            mst.addLast(allEdges.get(i));
            if (containsCycle(mst)) {
                mst.removeLast();
            }
        }
        return mst;
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


    public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        }
        if (anObject instanceof Edge) {
            Edge newEdge = (Edge) anObject;

            if (this.node1 == newEdge.node1 && this.node2 == newEdge.node2 && this.weight == newEdge.weight) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return "(" + node1 + ", " + node2 + ", " + weight + "), ";
    }

}

/*
*
6
0,1,1,3,1
1,2,2,4,1
2,5,1
3,2,2,4,2,5,3
4,5,1
5,
* */