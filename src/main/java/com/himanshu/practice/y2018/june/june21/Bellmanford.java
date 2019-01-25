package com.himanshu.practice.y2018.june.june21;

import lombok.AllArgsConstructor;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by Himanshu Bhardwaj on 21/06/18.
 */
public class Bellmanford {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nodeC = Integer.parseInt(sc.nextLine());
        Graph graph = new Graph(nodeC);

        for (int i = 0; i < nodeC; i++) {
            StringTokenizer st = new StringTokenizer(sc.next(), ",");
            int source = Integer.parseInt(st.nextToken());

            while (st.hasMoreTokens()) {
                int destination = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());
                graph.insert(source, destination, weight);
            }
        }

        graph.printGraph();
        System.out.println();

        int[] distance = graph.sssp(0);
        for (int i : distance) {
            System.out.println(i + "\t");
        }

    }
}


//graph directed hai
class Graph {
    LinkedList<Node> adjList[];
    int nodeCount;

    public Graph(int cNode) {
        this.nodeCount = cNode;
        adjList = new LinkedList[nodeCount];
        for (int i = 0; i < cNode; i++) {
            adjList[i] = new LinkedList<Node>();
        }
    }


    public void insert(int source, int destination, int weight) {
        Node destinationN = new Node(destination, weight);
        adjList[source].add(destinationN);

    }

    public int[] sssp(int root) {
        int[] sDistance = new int[nodeCount];

        for (int i = 0; i < nodeCount; i++) {
            sDistance[i] = Integer.MAX_VALUE;
        }
        sDistance[root] = 0;

        ssspHelper(sDistance);
        return sDistance;
    }


    private void ssspHelper(int[] sDistance) {

        for (int i = 0; i < nodeCount; i++) {
            for (int j = 0; j < nodeCount; j++) {
                for (int k = 0; k < adjList[j].size(); k++) {
                    int source = j;
                    Node destination = adjList[source].get(k);
                    if (destination == null || sDistance[source] == Integer.MAX_VALUE) {
                        continue;
                    }
                    if (sDistance[destination.destination] == Integer.MAX_VALUE) {
                        sDistance[destination.destination] = (sDistance[source] + destination.weight);
                    } else if (sDistance[destination.destination] < (sDistance[source] + destination.weight)) {
                        sDistance[destination.destination] = (sDistance[source] + destination.weight);
                    }
                }
            }
        }
    }


    public void printGraph() {
        for (int i = 0; i < adjList.length; i++) {
            System.out.print(i + ": ");
            for (int j = 0; j < adjList[i].size(); j++) {
                System.out.print("(" + adjList[i].get(j).destination + ", " + adjList[i].get(j).weight + ")\t");
            }
            System.out.println();
        }
    }

}


@AllArgsConstructor
class Node {
    int destination;
    int weight;

    public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        }

        if (anObject instanceof Node) {
            Node anotherObject = (Node) anObject;
            if (this.destination == anotherObject.destination && this.weight == anotherObject.weight) {
                return true;
            }
        }
        return false;
    }
}