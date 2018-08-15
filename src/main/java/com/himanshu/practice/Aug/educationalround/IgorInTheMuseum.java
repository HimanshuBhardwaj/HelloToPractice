package com.himanshu.practice.Aug.educationalround;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by himanshubhardwaj on 13/08/18.
 * 6:15 pm -- 6:49
 * Good
 * Algo: DFS
 */
public class IgorInTheMuseum {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");

        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);
        int k = Integer.parseInt(str[2]);

        Graph graph = new Graph(n, m);


        for (int i = 0; i < n; i++) {
            graph.insertRow(i, br.readLine());
        }
        graph.computeCluster();
//        graph.print();


        for (int i = 0; i < k; i++) {
            str = br.readLine().split(" ");
            System.out.println(graph.query(Integer.parseInt(str[0]), Integer.parseInt(str[1])));
        }


    }
}


class Graph {
    int arr[][];
    int row;
    int column;
    Map<Integer, Integer> numPaintingsInCluster;

    public Graph(int r, int c) {
        this.row = r;
        this.column = c;
        arr = new int[r][c];
        numPaintingsInCluster = new TreeMap<>();
    }

    public void insertRow(int row, String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '*') {
                arr[row][i] = -1;
            }
        }
    }

    public void computeCluster() {
        int cluster = 1;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (arr[i][j] == 0) {
                    numPaintingsInCluster.put(cluster, dfs(cluster, i, j));
                    cluster++;
                }
            }
        }
    }

    private int dfs(int cluster, int r, int c) {
    //    System.out.println(r + "\t" + c);
        if (arr[r][c] == -1) {
            return 1;
        } else if (arr[r][c] != 0) {
            return 0;
        }
        arr[r][c] = cluster;
        return dfs(cluster, r + 1, c) + dfs(cluster, r - 1, c) + dfs(cluster, r, c + 1) + dfs(cluster, r, c - 1);
    }

    public int query(int r, int c) {
        r--;
        c--;
        return numPaintingsInCluster.get(arr[r][c]);
    }

    public void print() {
        System.out.println(numPaintingsInCluster);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                System.out.print(arr[i][j] + "\t");
            }
            System.out.println();
        }
    }
}