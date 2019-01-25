package com.himanshu.practice.y2018.july.july11;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by Himanshu Bhardwaj on 11/07/18.
 * Prism: 3:48am -- 5:17am
 * Kurushkal: 5:17 -- 6:22
 * <p>
 * both could be improved
 */
public class SpanningTree {
    public static void main(String[] args) throws IOException {


        boolean testingCycleCode = false;
        if (testingCycleCode) {
            LinkedList<Edge> linkedList = new LinkedList<>();
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String str[] = br.readLine().split(" ");

            int n = Integer.parseInt(str[0]);
            int m = Integer.parseInt(str[1]);

            Graph graph = new Graph(n);

            for (int i = 0; i < m; i++) {
                str = br.readLine().split(",");
                linkedList.addLast(new Edge(parse(str[0]), parse(str[1]), parse(str[2])));
            }
            System.out.println(Graph.containsCycle(linkedList, n));
            return;
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");

        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);

        Graph graph = new Graph(n);

        for (int i = 0; i < m; i++) {
            str = br.readLine().split(",");
            graph.insert(parse(str[0]), parse(str[1]), parse(str[2]));
        }

//        graph.print();

        System.out.println(graph.prismMST());
        System.out.println(graph.kurashkalMST());
    }

    private static int parse(String s) {
        return Integer.parseInt(s);
    }
}


class Graph {
    LinkedList<Edge> edgeList[];

    public Graph(int n) {
        edgeList = new LinkedList[n];
        for (int i = 0; i < n; i++) {
            edgeList[i] = new LinkedList<>();
        }
    }


    public void insert(int source, int destination, int weight) {
        Edge e = new Edge(source, destination, weight);
        edgeList[source].addLast(e);
        edgeList[destination].addLast(e);
    }


    public void print() {
        int count = 0;
        for (LinkedList<Edge> l : edgeList) {
            System.out.print(count++ + ": ");
            for (Edge e : l) {
                System.out.print(e);
            }
            System.out.println();
        }
    }

    public LinkedList<Edge> prismMST() {
        System.out.println("@prismMST");
        LinkedList<Node> explored = new LinkedList<Node>();
        PriorityQueue<Node> yetToExplored = new PriorityQueue<Node>();
        Node[] nodeMapping = new Node[edgeList.length];
        PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
        LinkedList<Edge> mst = new LinkedList<Edge>();


        for (int i = 0; i < edgeList.length; i++) {
            Node n = new Node(i, (i == 0) ? 0 : Integer.MAX_VALUE, null);
            nodeMapping[i] = n;
            yetToExplored.add(n);
        }

        Node n = yetToExplored.poll();

        for (int i = 0; i < edgeList[n.index].size(); i++) {
            Edge e = edgeList[n.index].get(i);
            pq.add(e);
        }

        explored.add(n);

        while (!yetToExplored.isEmpty()) {
            Edge smallestEdgeOfCut = pq.poll();


            if ((explored.contains(nodeMapping[smallestEdgeOfCut.node1]) && explored.contains(nodeMapping[smallestEdgeOfCut.node2])) || mst.contains(smallestEdgeOfCut)) {

                continue;
            }

            //System.out.println(mst + "\t" + smallestEdgeOfCut + "\t" + mst.contains(smallestEdgeOfCut));
            mst.add(smallestEdgeOfCut);
            Node source = null;
            Node destination = null;

            if (explored.contains(nodeMapping[smallestEdgeOfCut.node1])) {
                source = nodeMapping[smallestEdgeOfCut.node1];
                destination = nodeMapping[smallestEdgeOfCut.node2];
            } else {
                source = nodeMapping[smallestEdgeOfCut.node2];
                destination = nodeMapping[smallestEdgeOfCut.node1];
            }

            if (explored.contains(destination)) {
                System.out.println("List:\t" + explored + "\t" + smallestEdgeOfCut);
                System.out.println(explored.contains(edgeList[smallestEdgeOfCut.node1]) && explored.contains(edgeList[smallestEdgeOfCut.node2]));
                throw new RuntimeException("Something has gone wrong");
            }


            explored.addLast(destination);
            yetToExplored.remove(destination);


            for (int i = 0; i < edgeList[destination.index].size(); i++) {
                Edge e = edgeList[destination.index].get(i);
                if (!(explored.contains(edgeList[e.node1]) && explored.contains(edgeList[e.node1]))) {
                    pq.add(e);
                }
            }
        }

        return mst;
    }

    public List<Edge> kurashkalMST() {
        System.out.println("@kurashkalMST");
        List<Edge> listE = new ArrayList<>();

        for (int i = 0; i < edgeList.length; i++) {
            for (int j = 0; j < edgeList[i].size(); j++) {
                listE.add(edgeList[i].get(j));
            }
        }
        Collections.sort(listE);

        LinkedList<Edge> mst = new LinkedList<>();
        for (Edge e : listE) {
            if (mst.size() == (edgeList.length - 1)) {
                break;
            }
            if (mst.contains(e)) {
                continue;
            }
            mst.addLast(e);
            if (containsCycle(mst, edgeList.length)) {
                mst.removeLast();
            }

        }

        return mst;
    }

    //this wont have repeated Edges
    public static boolean containsCycle(List<Edge> cycle, int n) {

        boolean[] visited = new boolean[n];
        boolean containsCycle = false;
        Set<Edge> visitedEdges = new HashSet<>();


        for (int i = 0; i < visited.length && !containsCycle; i++) {
            if (!visited[i]) {
                containsCycle = containsCycleHelper(cycle, i, visited, visitedEdges);
            }
        }
        return containsCycle;
    }

    private static boolean containsCycleHelper(List<Edge> cycle, int node, boolean[] visited, Set<Edge> visitedEdges) {
        if (visited[node]) {
            return true;
        }
        visited[node] = true;
        for (Edge e : cycle) {
            if (!visitedEdges.contains(e) && (e.node2 == node || e.node1 == node)) {
                visitedEdges.add(e);
                if (containsCycleHelper(cycle, (e.node1 != node) ? e.node1 : e.node2, visited, visitedEdges)) {
                    return true;
                }
            }
        }
        return false;
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
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    Edge shortestPAthEdge;

    @Override
    public int compareTo(Node o) {
        return this.distance - o.distance;
    }
}


@AllArgsConstructor
class Edge implements Comparable<Edge> {
    int node1;
    int node2;
    int weight;

    public String toString() {
        return node1 + "," + node2 + "," + weight + "\t";
    }

    @Override
    public int compareTo(Edge o) {
        return this.weight - o.weight;
    }

    public boolean equals(Object anObject) {
        if (anObject instanceof Edge) {
            Edge newEdg = (Edge) anObject;
            if (newEdg.node1 == this.node1 && newEdg.node2 == this.node2) {
                return true;
            }
            if (newEdg.node2 == this.node1 && newEdg.node1 == this.node2) {
                return true;
            }
        }
        return false;
    }

}