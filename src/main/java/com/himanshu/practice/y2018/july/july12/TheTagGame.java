package com.himanshu.practice.y2018.july.july12;
//

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * Created by himanshubhardwaj on 12/07/18.
 * https://codeforces.com/contest/813/problem/C
 * Algo: Preprocessing, dfsHelper
 * https://codeforces.com/contest/813/submission/40214883
 */
public class TheTagGame {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int x = Integer.parseInt(str[1]);
        Tree tree = new Tree(n);

        for (int i = 0; i < (n - 1); i++) {
            str = br.readLine().split(" ");
            int source = Integer.parseInt(str[0]);
            int destination = Integer.parseInt(str[1]);
            tree.insert(source, destination);
        }
//        tree.print();
        tree.bobDFS(x, -1, 0);
        //System.out.println();
        //System.out.println();
        tree.DFS(1, -1, false, 0, x);
        System.out.print(2 * tree.num);
    }

}

class Tree {
    LinkedList<Integer>[] adjList;
    int num = Integer.MIN_VALUE;
    int[] bobPAthLength;


    public Tree(int numNodes) {
        adjList = new LinkedList[numNodes + 1];

        for (int i = 0; i <= numNodes; i++) {
            adjList[i] = new LinkedList<>();
        }
        bobPAthLength = new int[numNodes + 1];
    }

    public void insert(int source, int destination) {
        adjList[source].addLast(destination);
        adjList[destination].addLast(source);
    }

    public void bobDFS(int index, int parent, int pathLength) {
        bobPAthLength[index] = pathLength;
        //System.out.println(index + "\t" + pathLength);

        for (int neighbour : adjList[index]) {
            if (neighbour != parent) {
                bobDFS(neighbour, index, pathLength + 1);
            }
        }
    }

    public void DFS(int index, int parent, boolean isRequiredPath, int currentLength, int bob) {

        if (isRequiredPath && currentLength > num) {
            num = currentLength;
        }

        for (int neighbour : adjList[index]) {
            if (neighbour != parent) {
                boolean temp = currentLength + 1 >= bobPAthLength[neighbour];
                DFS(neighbour, index, temp, currentLength + 1, bob);
            }
        }
    }

    private boolean isChildNode(int index, int parent) {
        if (adjList[index].size() == 1 && (adjList[index].get(0) == parent)) {
            return true;
        }

        return false;
    }


    public void print() {
        for (int i = 1; i < adjList.length; i++) {
            System.out.print(i + ": ");
            for (int j : adjList[i]) {
                System.out.print(j + "\t");
            }
            System.out.println();
        }
    }


}
