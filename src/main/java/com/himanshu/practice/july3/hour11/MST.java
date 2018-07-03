package com.himanshu.practice.july3.hour11;

import lombok.AllArgsConstructor;

import java.util.*;

/**
 * Created by himanshubhardwaj on 03/07/18.
 * 11:22
 * <p>
 * directed graph
 * //TODO: Complete it
 */
public class MST {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nodes = Integer.parseInt(sc.next());
        Graph graph = new Graph(nodes);

        for (int i = 0; i < nodes; i++) {
            StringTokenizer st = new StringTokenizer(sc.next(), ",");
            int source = Integer.parseInt(st.nextToken());
            while (st.hasMoreTokens()) {
                int destination = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());
                graph.insert(source, destination, weight);
            }
        }
        System.out.println("Printing Graph");
        graph.print();


        System.out.println();
        System.out.println();
        LinkedList<Edge> mst = graph.mst();

        System.out.println(mst);
    }
}


class Graph {
    LinkedList<Edge> edgeList[];
    int numNodes;

    public Graph(int numNodes) {
        this.numNodes = numNodes;
        edgeList = new LinkedList[numNodes];
        for (int i = 0; i < edgeList.length; i++) {
            edgeList[i] = new LinkedList<Edge>();
        }
    }

    public void insert(int source, int destination, int weight) {
        edgeList[source].add(new Edge(source, destination, weight));
    }

    public LinkedList<Edge> mst() {
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<Edge>();
        LinkedList<Node> explored = new LinkedList<>();
        LinkedList<Node> yetToExplored = new LinkedList<>();
        LinkedList<Edge> mst = new LinkedList<>();
        ArrayList<Node> nodeList = new ArrayList<>();


        for (int i = 0; i < numNodes; i++) {

            Node newNode = null;
            if (i != 0) {
                newNode = new Node(i, Integer.MAX_VALUE, null);
                yetToExplored.add(newNode);
            } else {
                newNode = new Node(i, 0, null);
                explored.addLast(newNode);
            }

            nodeList.add(i, newNode);
        }
        return null;

//        return mstHelper(0, priorityQueue, explored, yetToExplored, mst, nodeList);
    }

//    private LinkedList<Edge> mstHelper(int node, PriorityQueue<Edge> priorityQueue, LinkedList<Node> explored,
//                                       LinkedList<Node> yetToExplored, LinkedList<Edge> mst, ArrayList<Node> nodeList) {
//
//        if (yetToExplored.size() == 0) {
//            return mst;
//        }
//
//        for (int i = 0; i < edgeList[node].size(); i++) {
//            Edge edge = edgeList[node].get(i);
//
//            if (mst.contains(edge) || explored.contains(edge.destination)) {
//                continue;
//            }
//            //update its destination
//
//            Node node1 = nodeList.get(edge.destination);
//            if (node1.distance != Integer.MAX_VALUE) {
//                if (nodeList.get(node).distance != Integer.MAX_VALUE && node1.distance > nodeList.get(node).distance + edge.weight) {
//                    Node nearestNode = nodeList.get(edge.destination);
//                    priorityQueue.remove(nearestNode);
//                    nearestNode.distance = nodeList.get(node).distance + edge.weight;
//                    nearestNode.nearestEdge = edge;
//                    priorityQueue.add(nearestNode);
//                }
//            } else {
//                Node nearestNode = nodeList.get(edge.destination);
//                priorityQueue.remove(nearestNode);
//                nearestNode.distance = nodeList.get(node).distance + edge.weight;
//                nearestNode.nearestEdge = edge;
//                priorityQueue.add(nearestNode);
//            }
//        }
//        System.out.println("NodeIndex: " + node + "\tExplored: " + explored + "\t" + "yetToExplored: " + yetToExplored);
//
//        Node neigherestNode = priorityQueue.poll();
//        mst.addLast(neigherestNode.nearestEdge);
//        explored.addLast(neigherestNode);
//        yetToExplored.remove(neigherestNode);
//
//        return mstHelper(neigherestNode.index, priorityQueue, explored, yetToExplored, mst, nodeList);
//    }

    private boolean containsCycle(LinkedList<Edge> mst) {
        return false;
    }


    public void print() {
        for (int i = 0; i < numNodes; i++) {
            System.out.print(i + ": ");
            for (int j = 0; j < edgeList[i].size(); j++) {
                System.out.print(edgeList[i].get(j));
            }
            System.out.println();
        }
    }

}


@AllArgsConstructor
class Node implements Comparable<Node> {
    int index;
    int distance;
    Edge nearestEdge;

    @Override
    public int compareTo(Node o) {
        return this.distance - o.distance;
    }

    public boolean equals(Object var1) {
        if (var1 instanceof Node) {
            if (this.index == ((Node) var1).index) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return "(" + index + ", " + distance + ")";
    }
}

@AllArgsConstructor
class Edge implements Comparable<Edge> {
    int source;
    int destination;
    int weight;

    @Override
    public int compareTo(Edge o) {
        return this.weight - o.destination;
    }


    public boolean equals(Object var1) {
        if (var1 instanceof Edge) {
            Edge edge1 = (Edge) var1;

            if (edge1.destination == this.destination && edge1.source == this.source && this.weight == edge1.weight)

            {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return "(" + source + ", " + destination + ", " + weight + "),\t";
    }

}

