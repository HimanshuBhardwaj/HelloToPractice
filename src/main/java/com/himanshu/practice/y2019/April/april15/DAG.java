package com.himanshu.practice.y2019.April.april15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by himanshubhardwaj on 14/04/19.
 */
public class DAG {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);

        GGraph graph = new GGraph(n);

        for (int i = 0; i < m; i++) {
            str = br.readLine().split(" ");
            graph.insert(Integer.parseInt(str[0]), Integer.parseInt(str[1]));
        }

        System.out.println(graph.isDag());


    }
}

/*
6 8
0 1
1 2
4 1
2 3
3 4
4 5
5 3
4 0
* */

//DAG
class GGraph {
    ArrayList<Integer> adjList[];
    int size;

    public GGraph(int size) {
        this.size = size;
        adjList = new ArrayList[size];

        for (int i = 0; i < size; i++) {
            adjList[i] = new ArrayList<>();
        }
    }

    public void insert(int source, int destination) {
        adjList[source].add(destination);
    }


    public boolean isDag() {
        int state[] = new int[size];
        for (int i = 0; i < size; i++) {
            if (state[i] == 0) {
                if (hasCycle(i, state)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasCycle(int node, int[] state) {
        if (state[node] == 2) {
            return false;
        } else if (state[node] == 1) {
            return true;
        }

        state[node] = 1;

        for (int neighbour : adjList[node]) {
            if (hasCycle(neighbour, state)) {
                return true;
            }
        }

        state[node] = 2;

        return false;
    }


}
