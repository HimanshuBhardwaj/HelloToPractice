package com.himanshu.practice.y2019.june.june11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by himanshubhardwaj on 11/06/19.10:06 pm
 * Statement: https://codeforces.com/contest/763/problem/A
 * Algo: DFS
 * Submission: https://codeforces.com/contest/763/submission/55471627
 */
public class TimofeyAndATree {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        NGraph graph = new NGraph(n);


        for (int i = 0; i < (n - 1); i++) {
            String str[] = br.readLine().split(" ");
            graph.insert(Integer.parseInt(str[0]), Integer.parseInt(str[1]));
        }

        String str[] = br.readLine().split(" ");
        graph.setColour(str);

        if (graph.isAnnoyed()) {
            System.out.println("YES");
            System.out.print(graph.answer);
        } else {
            System.out.print("NO");
        }


    }
}

class NGraph {
    ArrayList<Integer> adjList[];
    int size;
    int colour[];
    int answer;

    public NGraph(int n) {
        this.size = n;
        this.adjList = new ArrayList[n];
        this.colour = new int[n];

        for (int i = 0; i < n; i++) {
            this.adjList[i] = new ArrayList<>();
        }
    }

    public void insert(int v1, int v2) {
        v1--;
        v2--;
        adjList[v1].add(v2);
        adjList[v2].add(v1);
    }

    public void setColour(String[] str) {
        for (int i = 0; i < str.length; i++) {
            colour[i] = Integer.parseInt(str[i]);
        }
    }

    public boolean isAnnoyed() {
        Integer v1 = null;
        Integer v2 = null;

        for (int i = 0; i < adjList.length; i++) {
            //   System.out.println(i+": "+adjList[i]);
            for (int j = 0; j < adjList[i].size(); j++) {
                if (colour[i] != colour[adjList[i].get(j)]) {
                    v1 = i;
                    v2 = adjList[i].get(j);
                }
            }
        }
        //    System.out.println();

        //      System.out.println(v1 + "\t" + colour[v1]);
//        System.out.println(v2 + "\t" + colour[v2]);

        if (v1 == null || v2 == null) {
            answer = 1;
            return true;
        }

        boolean result = true;

        for (int neighbour : adjList[v1]) {
            result = result && checkColour(neighbour, v1, colour[neighbour]);
            if (!result) {
                break;
            }
        }

        if (result) {
            answer = v1 + 1;
            return result;
        } else {
            result = true;
        }

        for (int neighbour : adjList[v2]) {
            result = result && checkColour(neighbour, v2, colour[neighbour]);
        }

        if (result) {
            answer = v2 + 1;
        }

        return result;

    }


    //return if all nodes constains the same colour c or not
    public boolean checkColour(int node, int parent, int c) {
        boolean result = (c == colour[node]);

        if (!result) {
            return result;
        }

        for (int neighbour : adjList[node]) {
            if (neighbour != parent) {
                result = result && checkColour(neighbour, node, c);
                if (!result) {
                    break;
                }
            }
        }

        return result;
    }
}

