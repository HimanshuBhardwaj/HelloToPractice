package com.himanshu.practice.y2018.july.july14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by himanshubhardwaj on 14/07/18.
 */
public class StronglyConnectedComponents {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);
        Graph graph = new Graph(n);

        for (int i = 0; i < m; i++) {
            str = br.readLine().split(" ");
            graph.insert(Integer.parseInt(str[0]), Integer.parseInt(str[1]));
        }

        graph.print();
        System.out.println();
        System.out.println(graph.stringlyConnectedComponent());


    }
}


//directed
class Graph {
    LinkedList<Integer> edgeList[];
    int numNodes;

    public Graph(int num) {
        numNodes = num;
        num++;

        edgeList = new LinkedList[num];

        for (int i = 1; i < num; i++) {
            edgeList[i] = new LinkedList<>();
        }
    }

    public void insert(int source, int destination) {
        edgeList[source].addLast(destination);
    }

    public LinkedList<Integer>[] reverseEdges(LinkedList<Integer> edgeList[]) {
        LinkedList<Integer> rEdgeList[] = new LinkedList[edgeList.length];
        for (int i = 0; i <= numNodes; i++) {
            rEdgeList[i] = new LinkedList<Integer>();
        }

        for (int i = 1; i < edgeList.length; i++) {
            for (int neighbour : edgeList[i]) {
                rEdgeList[neighbour].addLast(i);
            }
        }

        return rEdgeList;
    }


    LinkedList<LinkedList<Integer>> stringlyConnectedComponent() {
        boolean visited[] = new boolean[numNodes + 1];
        LinkedList<LinkedList<Integer>> connectedComponents = new LinkedList<LinkedList<Integer>>();
        Stack<Integer> stack = new Stack<Integer>();

        for (int i = 1; i <= numNodes; i++) {
            if (!visited[i]) {
                DFSStackPrepare(i, stack, visited);
            }
        }

        System.out.println(stack);

        visited = new boolean[numNodes + 1];
        LinkedList<Integer> rEdgeList[] = reverseEdges(edgeList);
        while (!stack.isEmpty()) {
            int top = stack.pop();
            if (!visited[top]) {
                LinkedList<Integer> connectedComponent = new LinkedList<>();
                stronglyConnectedComponentsHelper(top, visited, connectedComponent, rEdgeList);
                connectedComponents.addLast(connectedComponent);
            }
        }
        return connectedComponents;
    }

    private void stronglyConnectedComponentsHelper(int top, boolean[] visited, LinkedList<Integer> connectedComponent, LinkedList<Integer>[] edgeList) {
        if (visited[top]) {
            return;
        }
        visited[top] = true;
        connectedComponent.addLast(top);

        for (int neighbour : edgeList[top]) {
            if (!visited[neighbour]) {
                stronglyConnectedComponentsHelper(neighbour, visited, connectedComponent, edgeList);
            }
        }
    }

    private void DFSStackPrepare(int node, Stack<Integer> stack, boolean[] visited) {
        if (visited[node]) {
            return;
        }
        visited[node] = true;

        for (int neighbour : edgeList[node]) {
            if (!visited[neighbour]) {
                DFSStackPrepare(neighbour, stack, visited);
            }
        }
        stack.push(node);
    }

    public void print() {
        System.out.println("@print: " + numNodes);
        for (int i = 1; i <= numNodes; i++) {
            System.out.print(i + ":  ");
            for (int j : edgeList[i]) {
                System.out.print(j + ",");
            }
            System.out.println();
        }
    }

}


/*
Directed graph
13 17
1 2
2 3
4 1
3 4
3 5
5 9
5 6
5 7
6 7
7 8
8 5
9 11
11 10
9 10
9 12
12 13
13 9
*
* */

/*
Output: [[1, 4, 3, 2], [5, 8, 7, 6], [9, 13, 12], [11], [10]]
*
* */