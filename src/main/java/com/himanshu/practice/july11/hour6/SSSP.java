package com.himanshu.practice.july11.hour6;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * Created by Himanshu Bhardwaj on 11/07/18.
 */
public class SSSP {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);

        Graph graph = new Graph(n);

        for (int i = 0; i < m; i++) {
            str = br.readLine().split(",");
            graph.insert(Integer.parseInt(str[0]), Integer.parseInt(str[1]), Integer.parseInt(str[2]));
        }

        int source = 0;
        int[] ssspDij = graph.dijkstra(source);
        int[] ssspBell = graph.bellmonFord(source);

        for (int i : ssspDij) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i : ssspBell) {
            System.out.print(i + " ");
        }


    }
}


//Directed
class Graph {
    LinkedList<Edge> edgeList[];

    public Graph(int num) {
        edgeList = new LinkedList[num];

        for (int i = 0; i < num; i++) {
            edgeList[i] = new LinkedList<>();
        }

    }

    public void insert(int source, int destination, int weight) {
        Edge e = new Edge(source, destination, weight);
        edgeList[source].addLast(e);
    }


    public int[] bellmonFord(int source) {
        int[] sssp = new int[edgeList.length];
        for (int i = 0; i < edgeList.length; i++) {
            sssp[i] = (i != source) ? Integer.MAX_VALUE : 0;
        }

        for (int i = 0; i < edgeList.length; i++) {
            for (int j = 0; j < edgeList.length; j++) {
                for (int k = 0; k < edgeList[j].size(); k++) {
                    Edge e = edgeList[j].get(k);
                    if (sssp[e.source] != Integer.MAX_VALUE) {
                        sssp[e.destination] = Math.min(sssp[e.destination], sssp[e.source] + e.weight);
                    }
                }
            }
        }
        return sssp;
    }

    public int[] dijkstra(int source) {
        int[] sssp = new int[edgeList.length];
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        Node[] nodeMapping = new Node[edgeList.length];
        for (int i = 0; i < sssp.length; i++) {
            Node n = new Node(i, (i == source) ? 0 : Integer.MAX_VALUE);
            priorityQueue.add(n);
            nodeMapping[n.index] = n;
        }

        while (!priorityQueue.isEmpty()) {
            Node nearest = priorityQueue.poll();
            sssp[nearest.index] = nearest.distance;
            System.out.println(nearest.index + "\t" + nearest.distance);

            for (int i = 0; i < edgeList[nearest.index].size() && (nearest.distance != Integer.MAX_VALUE); i++) {
                Edge neighbourEdge = edgeList[nearest.index].get(i);
                int neighbourVertex = neighbourEdge.destination;
                Node neiNode = nodeMapping[neighbourVertex];

                if (neiNode.distance == Integer.MAX_VALUE) {
                    priorityQueue.remove(neiNode);
                    neiNode.distance = nearest.distance + neighbourEdge.weight;
                    priorityQueue.add(neiNode);
                } else if (neiNode.distance > (neighbourEdge.weight + nearest.distance)) {
                    priorityQueue.remove(neiNode);
                    neiNode.distance = nearest.distance + neighbourEdge.weight;
                    priorityQueue.add(neiNode);
                }
            }
        }

        return sssp;
    }
}

@EqualsAndHashCode
@AllArgsConstructor
@ToString
class Node implements Comparable<Node> {
    @EqualsAndHashCode.Include
    int index;
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    int distance;

    @Override
    public int compareTo(Node o) {
        return this.distance - o.distance;
    }
}

//source --> destination
@AllArgsConstructor
class Edge implements Comparable<Edge> {
    int source;
    int destination;
    int weight;

    public String toString() {
        return source + "," + destination + "," + weight + "\t";
    }

    @Override
    public int compareTo(Edge o) {
        return this.weight - o.weight;
    }

    public boolean equals(Object anObject) {
        if (anObject instanceof Edge) {
            Edge newEdg = (Edge) anObject;
            if (newEdg.source == this.source && newEdg.destination == this.destination) {
                return true;
            }
        }
        return false;
    }
}