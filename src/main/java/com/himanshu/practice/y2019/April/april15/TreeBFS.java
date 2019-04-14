package com.himanshu.practice.y2019.April.april15;

import lombok.AllArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by himanshubhardwaj on 14/04/19.
 * 17 mins
 */
public class TreeBFS {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        PTree pTree = new PTree(n);


        for (int i = 0; i < n - 1; i++) {
            String str[] = br.readLine().split(" ");
            pTree.insert(Integer.parseInt(str[0]), Integer.parseInt(str[1]));
        }

        pTree.BFS(1);


    }
}

/*
8
0 1
1 3
1 7
1 2
3 4
4 5
2 6
1 7

* */

@AllArgsConstructor
class PTree {
    LinkedList<Integer>[] adjList;
    int size;

    public PTree(int size) {
        this.size = size;
        adjList = new LinkedList[size];

        for (int i = 0; i < size; i++) {
            adjList[i] = new LinkedList<>();
        }
    }

    void insert(int v1, int v2) {
        adjList[v1].addLast(v2);
        adjList[v2].addLast(v1);
    }

    void BFS(int source) {
        boolean isVisited[] = new boolean[size];
        Queue<Integer> levelN = new LinkedList<>();
        int level = 0;
        levelN.add(source);
        isVisited[source] = true;


        while (!levelN.isEmpty()) {
            LinkedList<Integer> levelNode = new LinkedList<>(levelN);
            levelN = new LinkedList<>();

            System.out.print("Level" + level + ": ");
            for (Integer nodes : levelNode) {
                System.out.print(nodes + ", ");
                for (int neighbour : adjList[nodes]) {
                    if (!isVisited[neighbour]) {
                        isVisited[neighbour] = true;
                        levelN.add(neighbour);
                    }
                }
            }
            System.out.println();
            level++;
        }
    }

}
