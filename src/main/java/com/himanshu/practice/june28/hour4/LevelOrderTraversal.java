package com.himanshu.practice.june28.hour4;

import lombok.AllArgsConstructor;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by himanshubhardwaj on 28/06/18.
 */

//undirected Graph
public class LevelOrderTraversal {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int size = Integer.parseInt(sc.next());
        Graph graph = new Graph(size);

        for (int i = 0; i < size; i++) {
            StringTokenizer st = new StringTokenizer(sc.next(), ",");
            int source = Integer.parseInt(st.nextToken());

            while (st.hasMoreTokens()) {
                int destination = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());
                graph.insert(source, destination, weight);
            }
        }
        graph.print();
        Node root = new Node(0, 0);
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        LinkedList<Node> linkedList = new LinkedList<>();
        linkedList.addLast(root);
        System.out.println();
        graph.BFS(queue, linkedList);
        System.out.println("topologicalSort Time");
        LinkedList<Integer> topologicalSort = new LinkedList<>();
        graph.topologicalSort(0, new boolean[graph.adjList.length], topologicalSort);
        System.out.println();
        System.out.println(topologicalSort);

    }


}

class Graph {
    LinkedList<Edge>[] adjList;
    int numnodes;

    public Graph(int node) {
        this.numnodes = node;
        adjList = new LinkedList[numnodes];

        for (int i = 0; i < numnodes; i++) {
            adjList[i] = new LinkedList<>();
        }
    }

    void insert(int source, int destination, int weight) {
        Edge edge = new Edge(source, destination, weight);
        adjList[source].addLast(edge);
        adjList[destination].addLast(edge);
    }

    void print() {
        for (int i = 0; i < adjList.length; i++) {
            System.out.print(i + ":\t");
            for (int j = 0; j < adjList[i].size(); j++) {
                System.out.print(adjList[i].get(j));
            }
            System.out.println();
        }
    }

    void BFS(Queue<Node> queue, LinkedList<Node> visited) {
        if (queue.isEmpty()) {
            return;
        }
        Node node = queue.poll();
        System.out.print(node);

        for (int i = 0; i < adjList[node.index].size(); i++) {
            Edge neighbourEdge = adjList[node.index].get(i);
            Node neighbour = new Node((neighbourEdge.node1 == node.index) ? neighbourEdge.node2 : neighbourEdge.node1, node.level + 1);

            if (!visited.contains(neighbour)) {
                visited.addLast(neighbour);
                queue.add(neighbour);
            }
        }
        BFS(queue, visited);
    }

    //8:32 pm
    //8:42
    //undirected
    public void topologicalSort(int root, boolean[] visited, LinkedList<Integer> topologicalSort) {
        if (visited[root]) {
            return;
        }
        visited[root] = true;
        System.out.print(root + "-->");


        for (int i = 0; i < adjList[root].size(); i++) {
            int neighbour = (adjList[root].get(i).node1 == root) ? adjList[root].get(i).node2 : adjList[root].get(i).node1;
            topologicalSort(neighbour, visited, topologicalSort);
        }
        topologicalSort.addLast(root);
    }
}


class Node {
    int index;
    int level;

    public Node(int index, int level) {
        this.index = index;
        this.level = level;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Node) {
            Node node = (Node) obj;
            return node.index == this.index;
        }
        return false;
    }

    public String toString() {
        return "(" + index + ", " + level + ")\t";
    }


}

@AllArgsConstructor
class Edge {
    int node1;
    int node2;
    int wegight;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Edge) {
            Edge newEdge = (Edge) obj;
            if ((newEdge.wegight == this.wegight) &&
                    ((newEdge.node1 == this.node1 && this.node2 == newEdge.node2)
                            || (newEdge.node2 == this.node1 && newEdge.node1 == this.node2))) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return "(" + node1 + ", " + node2 + ", " + wegight + ")\t";
    }

}


