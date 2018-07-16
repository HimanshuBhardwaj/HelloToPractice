package com.himanshu.practice.july16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * Created by himanshubhardwaj on 17/07/18.
 * 1:57 am -- 2:40 am ;--> DAG
 *
 *
 */
public class DiameterOfGraph {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);

        DAG graph = new DAG(n);

        for (int i = 0; i < m; i++) {
            str = br.readLine().split(" ");
            int source = Integer.parseInt(str[0]);
            int destination = Integer.parseInt(str[1]);
            graph.insert(source, destination);
        }

        graph.printDiameter();

    }
}


class DAG {
    LinkedList<Integer> edgeList[];
    private LinkedList<Integer> topologicalSort;
    private LinkedList<Integer> diameter;

    public DAG(int numN) {
        edgeList = new LinkedList[numN];
        for (int i = 0; i < numN; i++) {
            edgeList[i] = new LinkedList<>();
        }
        diameter = new LinkedList<>();
        topologicalSort = new LinkedList<>();
    }

    public void insert(int source, int destination) {
        edgeList[source].addLast(destination);
    }

    public void printDiameter() {

        boolean[] visited = new boolean[edgeList.length];
        getTopologicalSort();
        System.out.println("TOPOLOGICAL SORT:\t\t" + topologicalSort);
        for (int i = visited.length - 1; i >= 0; i--) {
            if (!visited[topologicalSort.get(i)]) {
                LinkedList<Integer> path = new LinkedList<>();
                path.addLast(topologicalSort.get(i));
                graphDiameter(topologicalSort.get(i), path, visited);
            }
        }
        System.out.println("Diameter" + diameter);
    }

    private void graphDiameter(int index, LinkedList<Integer> path, boolean[] visited) {
        if (visited[index]) {
            return;
        }
        //System.out.println(index + "\t" + path.size() + "\t" + path);
        visited[index] = true;

        for (int neighbour : edgeList[index]) {
            if (!visited[neighbour]) {
                path.addLast(neighbour);
                graphDiameter(neighbour, path, visited);
                path.removeLast();
            }
        }
        if (path.size() > diameter.size()) {
            diameter = (LinkedList<Integer>) path.clone();
        }
    }

    public LinkedList<Integer> getTopologicalSort() {
        boolean visited[] = new boolean[edgeList.length];

        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                DFSTopological(i, visited);
            }
        }
        return topologicalSort;
    }

    private void DFSTopological(int index, boolean[] visited) {
        if (visited[index]) {
            return;
        }
        visited[index] = true;

        for (int neighbour : edgeList[index]) {
            DFSTopological(neighbour, visited);
        }

        topologicalSort.addLast(index);
    }
}
/*
*
13 13
0 1
2 1
2 3
3 4
3 5
5 6
6 4
6 7
1 8
9 10
10 11
11 12
12 5
* */