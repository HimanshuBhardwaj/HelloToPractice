package com.himanshu.practice.y2019.june.june15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.TreeSet;

/**
 * Created by himanshubhardwaj on 15/06/19.
 * Algo: Dijkstra(n log m)
 * Submission: https://codeforces.com/contest/20/submission/55583283
 * Statement: https://codeforces.com/contest/20/problem/C
 */
public class Dijkstra {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);

        DGraph graph = new DGraph(n);

        for (int i = 0; i < m; i++) {
            str = br.readLine().split(" ");
            graph.insert(Integer.parseInt(str[0]), Integer.parseInt(str[1]), Integer.parseInt(str[2]));
        }

        System.out.print(graph.shortestDistanceDijkstra(0));
    }
}


class DGraph {
    ArrayList<DEdge>[] adjList;
    int numNodes;
    DVertex[] vertices;
    long impossibleDisance = Long.MAX_VALUE - (Integer.MAX_VALUE * 10000l);

    public DGraph(int n) {
        numNodes = n;
        adjList = new ArrayList[n];
        vertices = new DVertex[n];
        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<>();
            vertices[i] = new DVertex(i, impossibleDisance);
        }
    }

    public void insert(int v1, int v2, int weight) {
        v1--;
        v2--;
        DEdge edge = new DEdge(vertices[v1], vertices[v2], weight);
        adjList[v1].add(edge);
        adjList[v2].add(edge);
    }

    String shortestDistanceDijkstra(int source) {
        vertices[source].shortestDistance = 0;
        TreeSet<DVertex> nearestNodes = new TreeSet<>();
        nearestNodes.add(vertices[source]);

        while (!nearestNodes.isEmpty()) {
            DVertex nearest = nearestNodes.first();
            nearestNodes.remove(nearest);

            if (nearest.shortestDistance == impossibleDisance) {
                continue;
            }

            for (DEdge nEdge : adjList[nearest.index]) {
                DVertex neighbour = null;
                if (nEdge.vertex1.index == nearest.index) {
                    neighbour = nEdge.vertex2;
                } else {
                    neighbour = nEdge.vertex1;
                }

                if (neighbour.shortestDistance > (nearest.shortestDistance + nEdge.weight)) {
                    nearestNodes.remove(neighbour);
                    neighbour.shortestDistance = nearest.shortestDistance + nEdge.weight;
                    neighbour.parent = nearest.index;
                    nearestNodes.add(neighbour);
                }
            }

//            for (int i = 0; i < vertices.length; i++) {
//                System.out.print(vertices[i].shortestDistance + ", ");
//            }
//            System.out.println();
        }


        if (vertices[numNodes - 1].shortestDistance == impossibleDisance) {
            return "-1";
        }


        StringBuilder sb = new StringBuilder();
        int currentNode = numNodes - 1;
        Stack<Integer> stack = new Stack<>();

        while (currentNode != 0) {
            stack.push(currentNode);
            currentNode = vertices[currentNode].parent;
            if (currentNode == 0) {
                stack.push(currentNode);
            }
        }


        while (!stack.isEmpty()) {
            sb.append((stack.pop() + 1) + " ");
        }


        return sb.toString();
    }
}


class DEdge implements Comparable<DEdge> {
    DVertex vertex1;
    DVertex vertex2;
    long weight;

    @java.beans.ConstructorProperties({"vertex1", "vertex2", "weight"})
    public DEdge(DVertex vertex1, DVertex vertex2, long weight) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.weight = weight;
    }

    @Override
    public int compareTo(DEdge o) {
        if ((this.vertex1 == o.vertex1 && this.vertex2 == o.vertex2) || (this.vertex2 == o.vertex1 && this.vertex1 == o.vertex2)) {
            if (this.weight == o.weight) {
                return 0;
            } else {
                return 1;
            }
        } else {
            return 1;
        }
    }
}


class DVertex implements Comparable<DVertex> {
    int index;
    long shortestDistance;
    int parent = -1;


    public DVertex(int index, long shortestDistance) {
        this.index = index;
        this.shortestDistance = shortestDistance;
    }

    @Override
    public int compareTo(DVertex o) {
        if (this.shortestDistance != o.shortestDistance) {
            return (int) (this.shortestDistance - o.shortestDistance);
        } else {
            return this.index - o.index;
        }
    }
}