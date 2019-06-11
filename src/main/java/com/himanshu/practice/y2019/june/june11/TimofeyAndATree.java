package com.himanshu.practice.y2019.june.june11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by himanshubhardwaj on 11/06/19.10:06 pm
 * 15 mins
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
            System.out.println("NO");
        } else {
            System.out.println("YES");
        }


    }
}

class NGraph {
    ArrayList<Integer> adjList[];
    int size;
    int colour[];

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
            for (int j = 0; j < adjList[i].size(); j++) {
                if (colour[i] != colour[j]) {
                    v1 = i;
                    v2 = j;
                }
            }
        }

        System.out.println(v1+"\t"+colour[v1]);
        System.out.println(v2+"\t"+colour[v2]);

        if (v1 == null || v2 == null) {
            return false;
        }
        int answer1 = countNodes(v1, -1, colour[v1]);
        System.out.println(answer1+"answer1");
        if (answer1 == size - 1) {
            return false;
        }




        int answer2 = countNodes(v2, -1, colour[v2]);
        System.out.println(answer2+"answer2");
        if (answer2 == size - 1) {
            return false;
        }
        return true;
    }


    //return number of nodes with given colour in graph
    public int countNodes(int node, int parent, int c) {
        int count = 0;
        if (colour[node] == c) {
            count = 1;
        }

        for (int neighbour : adjList[node]) {
            if (neighbour != parent) {
                count += countNodes(neighbour, node, c);
            }
        }

        return count;
    }
}

