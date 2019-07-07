package com.himanshu.practice.y2019.july.july7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by himanshubhardwaj on 08/07/19.
 */
public class GraphOperations {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);


        Graph graph = new Graph(n);
        for (int i = 0; i < m; i++) {
            str = br.readLine().split(" ");
            int s = Integer.parseInt(str[0]);
            int d = Integer.parseInt(str[1]);
            graph.insert(s, d);
        }
        graph.print();
        System.out.println();
        System.out.println("Printing  level order traversal ");

        ArrayList<ArrayList<Integer>> levelNodes = graph.levelOrerTraversal(0);
        for (int i = 0; i < levelNodes.size(); i++) {
            System.out.print(i + ": ");
            for (int j = 0; j < levelNodes.get(i).size(); j++) {
                System.out.print(levelNodes.get(i).get(j) + ",");
            }
            System.out.println();
        }
    }
}


//directed
//Graph formation: 12:28--12:36
class Graph {
    ArrayList<Integer> adjList[];
    int size;

    public Graph(int n) {
        this.size = n;
        adjList = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<>();
        }
    }


    void insert(int s, int d) {
        adjList[s].add(d);
    }

    void print() {
        System.out.println("Printing Graph");

        for (int i = 0; i < this.size; i++) {
            System.out.print(i + ": ");
            for (int j = 0; j < adjList[i].size(); j++) {
                System.out.print(adjList[i].get(j) + ",");
            }
            System.out.println();
        }
    }


    ArrayList<ArrayList<Integer>> levelOrerTraversal(int root) {//aks BFS
        int level = 0;
        boolean isVisited[] = new boolean[size];

        Queue<Integer> queue = new LinkedList<>();
        ArrayList<ArrayList<Integer>> levelNode = new ArrayList<>();
        queue.add(root);
        isVisited[root] = true;

        while (!queue.isEmpty()) {
            levelNode.add(new ArrayList(queue));
            queue = new LinkedList<>();

            for (Integer levelNodeInt : levelNode.get(levelNode.size() - 1)) {
                for (int neighbour : adjList[levelNodeInt]) {
                    if (!isVisited[neighbour]) {
                        queue.add(neighbour);
                        isVisited[neighbour] = true;
                    }
                }
            }
            System.out.println(levelNode.size() + "\t" + queue);
        }

        return levelNode;
    }


}