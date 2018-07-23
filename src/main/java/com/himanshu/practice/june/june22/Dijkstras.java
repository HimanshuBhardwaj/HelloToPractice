package com.himanshu.practice.june.june22;

import lombok.AllArgsConstructor;

import java.util.*;

/**
 * Created by Himanshu Bhardwaj on 22/06/18.
 */
public class Dijkstras {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numnode = Integer.parseInt(sc.next());

        Graph  graph = new Graph (numnode);

        for (int i = 0; i < numnode; i++) {
            StringTokenizer st = new StringTokenizer(sc.next(), ",");
            int source = Integer.parseInt(st.nextToken());

            while (st.hasMoreTokens()) {
                int destination = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());
                graph.insert(source, destination, weight);
            }
        }

        graph.print();

        System.out.println("Computing Nearest distances");
        int[] nearestDistances = graph.getDijkstrasSinglePairShortestPAth(1);

        System.out.println();
        System.out.println("Nearest distances from node 0");
        for (int i : nearestDistances) {
            System.out.print(i + " ");
        }
        System.out.println();

    }
}


class Graph {
    LinkedList<Edge> edgeList[];
    int numNodes;

    public Graph(int numN) {
        edgeList = new LinkedList[numN];
        numNodes = numN;

        for (int i = 0; i < numN; i++) {
            edgeList[i] = new LinkedList<Edge>();
        }
    }

    public void insert(int source, int destination, int weight) {
        edgeList[source].add(new Edge(source, destination, weight));
    }


    public void print() {
        for (int i = 0; i < edgeList.length; i++) {
            System.out.print(i + ": ");
            for (int j = 0; j < edgeList[i].size(); j++) {
                System.out.print("(" + edgeList[i].get(j).destination + ", " + edgeList[i].get(j).weight + ")");
                System.out.print("\t");
            }
            System.out.println();
        }
    }

    public int[] getDijkstrasSinglePairShortestPAth(int source) {
        int[] distance = new int[edgeList.length];
        PriorityQueue<Node> pQueue = new PriorityQueue<>();
        Map<Integer, Node> nodeMap = new HashMap<>();

        for (int i = 0; i < numNodes; i++) {
            Node newNode = null;
            if (i == source) {
                newNode = new Node(i, 0);
            } else {
                newNode = new Node(i, Integer.MAX_VALUE);
            }
            pQueue.add(newNode);
            distance[i] = Integer.MAX_VALUE;
            nodeMap.put(newNode.index, newNode);
        }

        System.out.println("PQueue and distance array initialised");

        while (!pQueue.isEmpty()) {
            Node nearest = pQueue.poll();
            distance[nearest.index] = nearest.distance;

            for (int i = 0; i < edgeList[nearest.index].size(); i++) {
                Node neighbour = nodeMap.get(edgeList[nearest.index].get(i).destination);
                if (pQueue.contains(neighbour)) {
                    pQueue.remove(neighbour);

                    neighbour.distance = Math.min(neighbour.distance, nearest.distance + edgeList[nearest.index].get(i).weight);
                    pQueue.add(neighbour);
                }
            }
        }
        return distance;
    }
}

@AllArgsConstructor
class Node implements Comparable<Node> {
    int index;
    int distance;

    @Override
    public int compareTo(Node o) {
        return this.distance - o.distance;
    }

    public boolean equals(Object ob) {
        if (ob instanceof Node) {
            Node node = (Node) ob;
            if (node.index == this.index) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return Integer.toString(index);
    }
}

@AllArgsConstructor
class Edge implements Comparable<Edge> {
    int source;
    int destination;
    int weight;


    public String toString() {
        return Integer.toString(source) + "-->" + Integer.toString(destination) + ": " + Integer.toString(weight);
    }

    public boolean equals(Object obj) {
        if (obj instanceof Edge) {
            Edge newEd = (Edge) obj;
            return (this.source == newEd.source) && (this.destination == newEd.destination) && (this.weight == newEd.weight);
        }
        return false;
    }

    @Override
    public int compareTo(Edge o) {
        return (this.weight - o.weight);
    }
}

