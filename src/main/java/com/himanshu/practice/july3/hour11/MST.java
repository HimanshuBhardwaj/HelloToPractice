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
        //LinkedList<Edge> mst = graph.mst();

        System.out.println("Contains Cycle: " + graph.containsCycleHelperTest());
        System.out.println(graph.mstKurashkal());
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


    public LinkedList<Edge> mstKurashkal() {
        LinkedList<Edge> edges = new LinkedList<>();
        LinkedList<Edge> mst = new LinkedList<>();

        for (int i = 0; i < numNodes; i++) {
            for (int j = 0; j < edgeList[i].size(); j++) {
                edges.add(edgeList[i].get(j));
            }
        }

        Collections.sort(edges);

        for (int i = 0; (i < edges.size()) && mst.size() < (numNodes - 1); i++) {
            Edge edge = edges.get(i);
            mst.addLast(edge);
            if (containsCycle(mst)) {
                mst.removeLast();
            }
        }


        return mst;
    }

    public LinkedList<Edge> mstPrism() {
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

//        return mstPrismHelper(0, priorityQueue, explored, yetToExplored, mst, nodeList);
    }


//    private LinkedList<Edge> mstPrismHelper(int node, PriorityQueue<Edge> priorityQueue, LinkedList<Node> explored,
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


    //directed

    public boolean containsCycleHelperTest() {
        LinkedList<Edge> edges = new LinkedList<>();

        for (int i = 0; i < numNodes; i++) {
            for (int j = 0; j < edgeList[i].size(); j++) {
                edges.addLast(edgeList[i].get(j));
            }
        }

        System.out.println(edges.size());

        return containsCycle(edges);
    }

    private boolean containsCycle(LinkedList<Edge> mst) {
        if (mst == null || mst.size() < 2) {
            return false;
        }

        int visited[] = new int[numNodes];
        boolean containsCycle = false;

        for (int i = 0; (i < numNodes) && !containsCycle; i++) {
            if (visited[i] == 0) {
                containsCycle = containsCycle || containsCycleHelper(i, mst, visited);
            }
        }

        return containsCycle;
    }

    private boolean containsCycleHelper(int source, LinkedList<Edge> mst, int[] visited) {
        if (visited[source] == 1) {
            return true;
        }
        if (visited[source] == 2) {
            return false;
        }

        visited[source] = 1;
        boolean containsCycle = false;
        for (Edge e : mst) {
            if (e.source == source) {
                containsCycle = containsCycleHelper(e.destination, mst, visited);
            }
            if (containsCycle) {
                return containsCycle;
            }
        }
        visited[source] = 2;
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

