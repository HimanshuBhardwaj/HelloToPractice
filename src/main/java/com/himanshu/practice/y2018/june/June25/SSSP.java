package com.himanshu.practice.y2018.june.June25;

import lombok.AllArgsConstructor;

import java.util.*;

/**
 * Created by Himanshu Bhardwaj on 25/06/18.
 */
public class SSSP {
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


        for (int i : Bellmanford.shortestPath(graph, 1)) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i : Dijastras.shortestPath(graph, 1)) {
            System.out.print(i + " ");
        }
    }

}

//undirected graph
class Bellmanford {
    public static int[] shortestPath(Graph graph, int source) {
        System.out.println("@ Bellmanford shortestPath");
        int[] shortestPath = new int[graph.numNodes];

        for (int i = 0; i < graph.numNodes; i++) {
            if (i == source) {
                shortestPath[i] = 0;
            } else {
                shortestPath[i] = Integer.MAX_VALUE;
            }
        }


        for (int i = 0; i < graph.numNodes; i++) {
            for (int j = 0; j < graph.edgelist.size(); j++) {
                int node1 = graph.edgelist.get(j).node1;
                int node2 = graph.edgelist.get(j).node2;
                int weight = graph.edgelist.get(j).weight;

                //node1 -> node2
                if (shortestPath[node2] == Integer.MAX_VALUE) {
                    if ((shortestPath[node1] != Integer.MAX_VALUE) && (shortestPath[node2] > (shortestPath[node1] + weight))) {
                        shortestPath[node2] = shortestPath[node1] + weight;
                    }
                } else {
                    if ((shortestPath[node1] != Integer.MAX_VALUE) && shortestPath[node2] > shortestPath[node1] + weight) {
                        shortestPath[node2] = shortestPath[node1] + weight;
                    }
                }

                //node2 -> node1
                if (shortestPath[node1] == Integer.MAX_VALUE) {
                    if ((shortestPath[node2] != Integer.MAX_VALUE) && (shortestPath[node1] > (shortestPath[node2] + weight))) {
                        shortestPath[node1] = shortestPath[node2] + weight;
                    }
                } else {
                    if ((shortestPath[node2] != Integer.MAX_VALUE) && shortestPath[node1] > (shortestPath[node2] + weight)) {
                        shortestPath[node1] = shortestPath[node2] + weight;
                    }
                }

            }
        }
        return shortestPath;
    }
}

//undirected Grah
class Dijastras {
    public static int[] shortestPath(Graph graph, int source) {
        System.out.println("@ Dijastras shortestPath");
        PriorityQueue<Node> priorityQueue = new PriorityQueue<Node>();
        ArrayList<Node> arrayList = new ArrayList<>();
        int[] shortestPath = new int[graph.numNodes];

        for (int i = 0; i < graph.numNodes; i++) {
            if (i == source) {
                Node node = new Node(i, 0);
                priorityQueue.add(node);
                arrayList.add(i, node);
            } else {
                Node node = new Node(i, Integer.MAX_VALUE);
                priorityQueue.add(node);
                arrayList.add(i, node);
            }
        }

        while (!priorityQueue.isEmpty()) {
            Node node = priorityQueue.poll();
            shortestPath[node.index] = node.sssp;

            //make adjList as undirected
            for (int j = 0; j < graph.adjList[node.index].size(); j++) {
                Edge neighbourNode = graph.adjList[node.index].get(j);
                int neighbourIndex = (neighbourNode.node1 == node.index) ? neighbourNode.node2 : neighbourNode.node1;
                int weight = neighbourNode.weight;
                Node nearestNeighbour = arrayList.get(neighbourIndex);
                if ((node.sssp != Integer.MAX_VALUE) && (nearestNeighbour.sssp > (node.sssp) + weight)) {
                    priorityQueue.remove(nearestNeighbour);
                    nearestNeighbour.sssp = (node.sssp) + weight;
                    priorityQueue.add(nearestNeighbour);
                }
            }
        }


        return shortestPath;
    }
}


@AllArgsConstructor
class Node implements Comparable<Node> {
    int index;
    int sssp;

    @Override
    public int compareTo(Node o) {
        return this.sssp - o.sssp;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Node) {
            Node newNode = (Node) obj;
            if (newNode.index == this.index) {
                return true;
            }
        }
        return false;
    }

}

class Graph {
    LinkedList<Edge>[] adjList;
    LinkedList<Edge> edgelist;
    int numNodes;


    public Graph(int numNodes) {
        adjList = new LinkedList[numNodes];
        edgelist = new LinkedList<Edge>();
        this.numNodes = numNodes;

        for (int i = 0; i < numNodes; i++) {
            adjList[i] = new LinkedList<Edge>();
        }
    }


    public void insert(int source, int destination, int weight) {
        Edge edge = new Edge(source, destination, weight);
        adjList[source].add(edge);
        adjList[destination].add(edge);
        edgelist.add(edge);
    }


    public void print() {

        System.out.println("Printing adj Matrix\n\n");
        for (int i = 0; i < numNodes; i++) {
            System.out.print(i + ": ");
            for (int j = 0; j < adjList[i].size(); j++) {
                System.out.print(adjList[i].get(j));
            }
            System.out.println();
        }

        System.out.println();
        System.out.println();
        System.out.println("Edge List");
        for (Edge e : edgelist) {
            System.out.println(e);
        }
    }
}


@AllArgsConstructor
class Edge implements Comparable<Edge> {
    int node1;
    int node2;
    int weight;


    public boolean equals(Object obj) {
        if (obj instanceof Edge) {
            Edge newEdge = (Edge) obj;
            if (newEdge.node1 == this.node1 && newEdge.node2 == this.node2 && newEdge.weight == this.weight) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int compareTo(Edge o) {
        return this.weight - o.weight;
    }

    public String toString() {
        return "(" + node1 + ", " + node2 + ", " + weight + ")\t";
    }
}
