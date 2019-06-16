package com.himanshu.practice.y2019.june.june15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by himanshubhardwaj on 15/06/19.
 */
public class DistanceInTree {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int k = Integer.parseInt(str[1]);

        Tree tree = new Tree(n, k);

        for (int i = 0; i < (n - 1); i++) {
            str = br.readLine().split(" ");
            tree.insert(Integer.parseInt(str[0]), Integer.parseInt(str[1]));
        }

        System.out.print(tree.countDFS());
    }
}


class Tree {
    ArrayList<Integer> adjList[];
    int numNodes;
    int k;

    public Tree(int n, int k) {
        this.numNodes = n;
        adjList = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<>();
        }
        this.k = k;
    }

    public void insert(int v1, int v2) {
        v1--;
        v2--;
        adjList[v1].add(v2);
        adjList[v2].add(v1);
    }

    //This is n^2
    long countBFS() {
        long c = 0l;
        for (int i = 0; i < numNodes; i++) {
            long tempc = BFS(i, -1, k);
            //System.out.println(i + "\t" + tempc);
            c += (tempc);
        }
        return c / 2;
    }

    private long BFS(int node, int parent, int k) {
        int level = 0;
        Queue<Integer> queue = new LinkedList<>();
        ArrayList<Integer> temp = new ArrayList<>();
        queue.add(node);
        HashSet<Integer> visited = new HashSet<>();
        visited.add(node);
        ArrayList<Integer> arrayList = new ArrayList<>();

        while (!queue.isEmpty()) {
            if (level == k) {
                return queue.size();
            }
            temp.addAll(queue);
            queue.retainAll(arrayList);
            for (int l : temp) {
                for (int neighbour : adjList[level]) {
                    if (!visited.contains(neighbour)) {
                        queue.add(neighbour);
                        visited.add(neighbour);
                    }
                }
            }
            level--;
            temp.retainAll(arrayList);
        }

        return 0;
    }

    //This is n^2
    long countDFS() {
        long c = 0l;
        for (int i = 0; i < numNodes; i++) {
            long tempc = DFS(i, -1, k);
            //System.out.println(i + "\t" + tempc);
            c += (tempc);
        }
        return c / 2;
    }

    private long DFS(int node, int parent, int k) {
        if (k < 0) {
            return 0;
        }
        if (k == 0) {
            return 1;
        }
        long count = 0;

        for (int neighbout : adjList[node]) {
            if (neighbout != parent) {
                count += DFS(neighbout, node, k - 1);
            }
        }

        return count;
    }

}