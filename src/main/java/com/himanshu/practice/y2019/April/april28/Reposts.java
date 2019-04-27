package com.himanshu.practice.y2019.April.april28;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by himanshubhardwaj on 28/04/19.
 * Start: 2:27 -- 2:47 am
 * time 20 mins
 * Algo: DFS, Simple
 * Statement: https://codeforces.com/problemset/problem/522/A
 * Submission: https://codeforces.com/contest/522/submission/53438873
 */
public class Reposts {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        HashMap<String, Integer> map = new HashMap<>();
        int index = 0;
        int root = -1;
        String orignalInput[] = new String[n];


        for (int i = 0; i < n; i++) {
            orignalInput[i] = br.readLine();

            String str[] = orignalInput[i].split(" ");
            str[0] = str[0].toLowerCase();
            str[2] = str[2].toLowerCase();

           // System.out.println(str[0] + "-" + str[1] + "-" + str[2]);


            if (!map.containsKey(str[0])) {
                map.put(str[0], index);
                index++;
            }

            if (!map.containsKey(str[2])) {
                map.put(str[2], index);
                index++;
            }

            if (str[2].equalsIgnoreCase("polycarp")) {
                root = map.get("polycarp");

            }
        }

        Graph g = new Graph(map.size());
        g.insertRoot(root);
        for (int i = 0; i < n; i++) {
            String str[] = orignalInput[i].split(" ");
            str[0] = str[0].toLowerCase();
            str[2] = str[2].toLowerCase();
            g.insertNode(map.get(str[2]), map.get(str[0]));
        }
        //System.out.println(g.root);
        System.out.print(g.getLongestPath(g.root)+1);


    }
}


class Graph {
    ArrayList<Integer>[] adjList;
    int numNodes;
    int root;


    public Graph(int n) {
        adjList = new ArrayList[n];
        numNodes = n;
        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<>();
        }
    }

    public void insertNode(int source, int destination) {
//        source--;
//        destination--;
        adjList[source].add(destination);
    }

    public void insertRoot(int r) {
        root = r;
    }

    public int getLongestPath(int root) {
        if (adjList[root].size() == 0) {
            return 0;
        }

        int size = 0;

        for (int neighbour : adjList[root]) {
            int temp = getLongestPath(neighbour);
            size = Math.max(temp, size);
        }

        return size+1;
    }

}