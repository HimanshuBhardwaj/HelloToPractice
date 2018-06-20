package com.himanshu.practice.june10.june9;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by himanshubhardwaj on 10/06/18.
 */


public class LongestPAthGraph {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberNodes = scanner.nextInt();

        Graph graph = new Graph(numberNodes);

        while ((numberNodes--) > 0) {
            String information = scanner.next();
            StringTokenizer st = new StringTokenizer(information, ",");
            int source = Integer.parseInt(st.nextToken());
            while (st.hasMoreTokens()) {
                graph.insert(source, Integer.parseInt(st.nextToken()));
            }
        }
        graph.printGraph();


        System.out.println();
        System.out.println("Reversed GraphOp");
        System.out.println();
        System.out.println();


        Graph reverseGraph = graph.reverseGraph(graph);
        reverseGraph.printGraph();

        System.out.println();
        System.out.println();
        System.out.println();


        System.out.println("Connected: " + graph.checkStronglyConncectedness(graph));


    }
}


class Graph {
    int numberNodes;
    LinkedList<Node> adjMAtrix[];
    HashMap<Integer, Node> nodMap = new HashMap<>();

    public Graph(int nodeCount) {
        this.numberNodes = nodeCount;
        adjMAtrix = new LinkedList[nodeCount];
        for (int i = 0; i < nodeCount; i++) {
            adjMAtrix[i] = new LinkedList<Node>();
        }
    }

    //Tested
    public void insert(int source, int destination) {
        Node destinatinN;
        Node sourceN;
        if (nodMap.containsKey(destination)) {
            destinatinN = nodMap.get(destination);
        } else {
            destinatinN = new Node(destination);
            nodMap.put(destination, destinatinN);
        }


        if (nodMap.containsKey(source)) {
            sourceN = nodMap.get(source);
        } else {
            sourceN = new Node(source);
            nodMap.put(source, sourceN);
        }

        adjMAtrix[source].push(destinatinN);
    }

    //tested
    public void printGraph() {
        for (int i = 0; i < adjMAtrix.length; i++) {
            System.out.print(i + " --> ");
            for (int j = 0; j < adjMAtrix[i].size(); j++) {
                System.out.print(adjMAtrix[i].get(j).value + ", ");
            }
            System.out.println();
        }

    }

    //return another graph with reversed edges
    Graph reverseGraph(Graph graph) {
        if (graph == null) {
            return null;
        }

        Graph newGraph = new Graph(graph.adjMAtrix.length);

        LinkedList<Node> adjMAtrix[] = graph.adjMAtrix;
        int destinatin, source;

        for (int i = 0; i < adjMAtrix.length; i++) {
            for (int j = 0; j < adjMAtrix[i].size(); j++) {
                Node node = adjMAtrix[i].get(j); //this is destinationwhich we source of new graph
                newGraph.insert(node.getValue(), i);
            }
        }
        return newGraph;
    }


    //tested
    public boolean checkStronglyConncectedness(Graph graph) {
        int visited[] = new int[graph.adjMAtrix.length];
        for (int i = 0; i < visited.length; i++) {
            visited[i] = -1;
        }

        isConnected(0, graph, visited);

        boolean connected = true;
        for (int i : visited) {
            if (i != 1) {
                connected = false;
                break;
            }
        }
        if (connected == false) {
            return connected;
        }
        Graph reverseGraph = graph.reverseGraph(graph);

        for (int i = 0; i < visited.length; i++) {
            visited[i] = -1;
        }

        isConnected(0, reverseGraph, visited);

        connected = true;
        for (int i : visited) {
            if (i != 1) {
                connected = false;
                break;
            }
        }


        return connected ;
    }

    //Tested
    private void isConnected(int root, Graph graph, int[] visited) {
        if (visited[root] == 1) {
            return;
        } else if (visited[root] == 0) {
            System.out.println("Cycle");
            return;
        }

        visited[root] = 0;

        for (int i = 0; i < graph.adjMAtrix[root].size(); i++) {
            isConnected(graph.adjMAtrix[root].get(i).getValue(), graph, visited);
        }

        visited[root] = 1;
    }

    public boolean longestPAthSize() {
        //TODO: Implement it
        return false;
    }


}


@AllArgsConstructor
class Node implements Comparable<Node> {
    @Getter
    int value;

    @Override
    public int compareTo(Node o) {
        return (this.value - o.value);
    }
}
