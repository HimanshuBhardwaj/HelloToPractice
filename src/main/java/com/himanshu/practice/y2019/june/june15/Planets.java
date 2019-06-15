package com.himanshu.practice.y2019.june.june15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Created by himanshubhardwaj on 15/06/19.
 * Statement: https://codeforces.com/contest/229/problem/B
 * Algo: Dijkstra
 * Submisison: https://codeforces.com/contest/229/submission/55588316
 */
public class Planets {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);

        Graph graph = new Graph(n);

        for (int i = 0; i < m; i++) {
            str = br.readLine().split(" ");
            graph.insert(Integer.parseInt(str[0]), Integer.parseInt(str[1]), Integer.parseInt(str[2]));
        }

        for (int i = 0; i < n; i++) {
            str = br.readLine().split(" ");
            graph.insertArrivalTime(i, str);
        }


        System.out.println(graph.shortestTime(0));
    }

}


class Graph {
    ArrayList<Edge>[] adjList;
    int numNodes;
    Vertex[] vertices;
    long maxPossibleTime = Long.MAX_VALUE - (200000l * Integer.MAX_VALUE);

    public Graph(int n) {
        this.numNodes = n;
        adjList = new ArrayList[n];
        vertices = new Vertex[n];

        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<>();
            vertices[i] = new Vertex(i, maxPossibleTime, maxPossibleTime, new TreeSet<>());
        }
    }

    public void insert(int v1, int v2, int weight) {
        v1--;
        v2--;
        Edge e = new Edge(vertices[v1], vertices[v2], weight);
        adjList[v1].add(e);
        adjList[v2].add(e);
    }

    long shortestTime(int source) {
        vertices[source].timeOfArrival = 0;
        vertices[source].computeDepartureTime();

        TreeSet<Vertex> explorableVertices = new TreeSet<>();
        explorableVertices.add(vertices[source]);

        while (!explorableVertices.isEmpty()) {
            Vertex nearestVertex = explorableVertices.first();
            explorableVertices.remove(nearestVertex);


            if (nearestVertex.timeOfArrival == maxPossibleTime) {
                break;
            }


            for (Edge e : adjList[nearestVertex.index]) {
                Vertex neighbour = (e.v1.index == nearestVertex.index) ? e.v2 : e.v1;
                if (neighbour.timeOfArrival > nearestVertex.timeofdeparture + e.weight) {
                    explorableVertices.remove(neighbour);
                    neighbour.timeOfArrival = nearestVertex.timeofdeparture + e.weight;
                    neighbour.computeDepartureTime();
                    explorableVertices.add(neighbour);
                }
            }
        }

        return (vertices[numNodes - 1].timeOfArrival != maxPossibleTime) ? vertices[numNodes - 1].timeOfArrival : -1;
    }


    void insertArrivalTime(int vertexId, String[] str) {
        for (int i = 1; i < str.length; i++) {
            vertices[vertexId].inserstArrivalTime(Integer.parseInt(str[i]));
        }
    }

}


class Edge {
    Vertex v1;
    Vertex v2;
    int weight;

    @java.beans.ConstructorProperties({"v1", "v2", "weight"})
    public Edge(Vertex v1, Vertex v2, int weight) {
        this.v1 = v1;
        this.v2 = v2;
        this.weight = weight;
    }
}


class Vertex implements Comparable<Vertex> {
    int index;
    long timeOfArrival;
    long timeofdeparture;
    TreeSet<Long> guestArrivalTime;

    @java.beans.ConstructorProperties({"index", "timeOfArrival", "timeofdeparture", "guestArrivalTime"})
    public Vertex(int index, long timeOfArrival, long timeofdeparture, TreeSet<Long> guestArrivalTime) {
        this.index = index;
        this.timeOfArrival = timeOfArrival;
        this.timeofdeparture = timeofdeparture;
        this.guestArrivalTime = guestArrivalTime;
    }

    @Override
    public int compareTo(Vertex o) {
        if (this.timeofdeparture != o.timeofdeparture) {
            return (int) (this.timeofdeparture - o.timeofdeparture);
        } else {
            return this.index - o.index;
        }
    }

    void inserstArrivalTime(int time) {
        guestArrivalTime.add((long) time);
    }

    void computeDepartureTime() {
        timeofdeparture = this.timeOfArrival;
        while (guestArrivalTime.contains(timeofdeparture)) {
            timeofdeparture++;
        }
    }
}