package com.himanshu.practice.y2018.july.july26;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * Created by himanshubhardwaj on 26/07/18.
 */
public class Journey {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);
        int k = Integer.parseInt(str[2]);


        Graph g = new Graph(n);

        for (int i = 0; i < m; i++) {
            str = br.readLine().split(" ");
            g.insert(Integer.parseInt(str[0]) - 1, Integer.parseInt(str[1]) - 1, Integer.parseInt(str[2]));

        }
        g.print();

        Node[] sssp = SSSP.SSSPath(0, g.edgL, g.edgL.length);

        for (Node v : sssp) {
            System.out.println(v);
        }
    }


}


class Graph {
    LinkedList<Edge> edgL[];
    LinkedList<Edge> reverseEdL[];

    int n;

    public Graph(int numNodes) {
        edgL = new LinkedList[numNodes];
        reverseEdL = new LinkedList[numNodes];
        n = numNodes;
        for (int i = 0; i < n; i++) {
            edgL[i] = new LinkedList<>();
            reverseEdL[i] = new LinkedList<>();
        }
    }


    public void insert(int source, int destination, int weight) {
        Edge e = new Edge(source, destination, weight);
        Edge re = new Edge(destination, source, weight);

        edgL[source].addLast(e);
        reverseEdL[destination].addLast(re);
    }


    public void print() {
        for (int i = 0; i < edgL.length; i++) {
            System.out.print(i + ": ");
            for (Edge e : edgL[i]) {
                System.out.print(e.destination + ",");
            }
            System.out.println();
        }
    }

}

class SSSP {
    static Node[] SSSPath(int source, LinkedList<Edge> graph[], int numNodes) {
        Node[] nodeMapping = new Node[numNodes];
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();

        for (int i = 0; i < numNodes; i++) {
            nodeMapping[i] = new Node(i, Long.MAX_VALUE, 0);
        }

        nodeMapping[source].sssp = 0;
        priorityQueue.add(nodeMapping[source]);

        while (!priorityQueue.isEmpty()) {
            Node nearest = priorityQueue.poll();

            if (nearest.sssp != Long.MAX_VALUE) {
                //make it iterable
                for (Edge neighbourEdge : graph[nearest.index]) {
                    Node neighbour = nodeMapping[neighbourEdge.destination];
                    priorityQueue.remove(neighbour);///could be optimised

                    if (neighbour.sssp == Long.MAX_VALUE) {
                        neighbour.sssp = nearest.sssp + neighbourEdge.weight;
                        neighbour.numNodes = 1 + nearest.numNodes;
                        priorityQueue.add(neighbour);
                    } else {
                        //TODO: Complete it
                        neighbour.sssp = Math.min(neighbour.sssp, nearest.sssp + neighbourEdge.weight);
                        neighbour.numNodes = 1 + nearest.numNodes;
                        priorityQueue.add(neighbour);
                    }
                }
            }
        }
        return nodeMapping;
    }
}


@AllArgsConstructor
class Edge {
    int source;
    int destination;
    int weight;
}


@ToString
@AllArgsConstructor
class Node implements Comparable<Node> {
    int index;
    long sssp;
    int numNodes;

    @Override
    public int compareTo(Node o) {
        return (int) (this.sssp - o.sssp);
    }
}