package com.himanshu.practice.y2019.june.june11;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by himanshubhardwaj on 11/06/19.
 * Statement: https://codeforces.com/contest/771/problem/A
 * Algo: DFS, Connected component
 * Submission: https://codeforces.com/contest/771/submission/55469182
 */
public class BearAndFriendshipCondition {
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
        if (graph.isReasonable()) {
            System.out.print("YES");
        } else {
            System.out.print("NO");
        }
    }

}


class Graph {
    ArrayList<Integer>[] arrayList;
    int size;
    int edgeCount[];

    public Graph(int n) {
        this.size = n;
        arrayList = new ArrayList[n];
        edgeCount = new int[n];
        for (int i = 0; i < size; i++) {
            arrayList[i] = new ArrayList<>();
        }
    }

    public void insert(int v1, int v2) {
        v1--;
        v2--;
        arrayList[v1].add(v2);
        arrayList[v2].add(v1);
        edgeCount[v1]++;
        edgeCount[v2]++;
    }

    public boolean isReasonable() {
        boolean visited[] = new boolean[size];
        HashSet<Integer> connectedComponent = new HashSet<>();

        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                connectedComponent.clear();
                connectedComponent.add(i);
                visited[i] = true;
                DFS(i, connectedComponent, visited);

                long count = 0;
                for (int nodes : connectedComponent) {
                    count += edgeCount[nodes];
                }

                if (count != ((long)connectedComponent.size() * (connectedComponent.size() - 1))) {
                    return false;
                }
            }
        }
        return true;
    }

    private void DFS(int root, HashSet<Integer> connectedComponent, boolean[] visited) {
        for (int neighbour : arrayList[root]) {
            if (!visited[neighbour]) {
                connectedComponent.add(neighbour);
                visited[neighbour] = true;
                DFS(neighbour, connectedComponent, visited);
            }
        }
    }
}
