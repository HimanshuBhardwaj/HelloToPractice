package com.himanshu.practice.june.june22.hour4;

import lombok.AllArgsConstructor;

import java.util.*;

/**
 * Created by Himanshu Bhardwaj on 22/06/18.
 */

//Minimum cost cycle covering all nodes
public class TravellingSalesmanProblem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numNode = Integer.parseInt(sc.next());
        Graph graph = new Graph(numNode);

        for (int i = 0; i < numNode; i++) {
            StringTokenizer st = new StringTokenizer(sc.next(), ",");
            int source = Integer.parseInt(st.nextToken());
            while (st.hasMoreTokens()) {
                int destination = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());
                graph.insert(source, destination, weight);
            }
        }

        graph.print();

        LinkedList<Integer> covered = new LinkedList<>();
        System.out.println(graph.TSPWeight(covered, 0, 0, Integer.MAX_VALUE, new boolean[graph.adjList.length]));
    }
}


class Graph {
    LinkedList<Edge> adjList[];
    int numNode;

    public Graph(int num) {
        numNode = num;
        adjList = new LinkedList[num];

        for (int i = 0; i < num; i++) {
            adjList[i] = new LinkedList<Edge>();
        }
    }


    public void insert(int source, int destination, int weight) {
        adjList[source].add(new Edge(source, destination, weight));
        adjList[destination].add(new Edge(destination, source, weight));
    }

    public int TSPWeight(LinkedList<Integer> covered, int node, int cummulativeWeight, int currentBest, boolean[] visited) {
        System.out.print("cummulativeWeight: " + cummulativeWeight + "\t");
        printCovered(covered);
        if (covered.size() == (adjList.length)) {
            System.out.print("Selected\t");
            int weight = Math.min(cummulativeWeight + edge(covered.get(0), covered.get(covered.size() - 1)), currentBest);
            System.out.print("Weight: " + weight + "\t");
            printCovered(covered);
            return weight;
        }

        if (cummulativeWeight >= currentBest) {
            return currentBest;
        }


        visited[node] = true;

        for (int i = 0; i < adjList[node].size(); i++) {
            if (!covered.contains(adjList[node].get(i).destination)) {
                covered.addLast(adjList[node].get(i).destination);
                currentBest = Math.min(currentBest,
                        TSPWeight(covered, adjList[node].get(i).destination, adjList[node].get(i).weight + cummulativeWeight, currentBest, visited));
                covered.remove(covered.indexOf(adjList[node].get(i).destination));
            }
        }
        return currentBest;
    }

    private int edge(Integer source, Integer destination) {
        int weight = Integer.MAX_VALUE - 1000;
        for (int i = 0; i < adjList[source].size(); i++) {
            if (adjList[source].get(i).destination == destination) {
                return adjList[source].get(i).weight;
            }
        }
        return weight;
    }

    public void print() {
        for (int i = 0; i < adjList.length; i++) {
            System.out.print(i + ": ");
            for (int j = 0; j < adjList[i].size(); j++) {
                if (adjList[i].get(j).destination != i) {
                    System.out.print("(" + adjList[i].get(j).destination + ", " + adjList[i].get(j).weight + "), ");
                }
            }
            System.out.println();
        }
    }

    public void printCovered(LinkedList<Integer> covered) {
        System.out.print("Covered Set:\t");
        for (int i : covered) {
            System.out.print(i + ", ");
        }
        System.out.println();
    }
}

@AllArgsConstructor
class Edge {
    int source;
    int destination;
    int weight;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Edge) {
            Edge newEdge = (Edge) obj;
            if (newEdge.source == this.source && newEdge.destination == this.destination && newEdge.weight == this.weight) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return source + "\t" + destination;
    }
}





