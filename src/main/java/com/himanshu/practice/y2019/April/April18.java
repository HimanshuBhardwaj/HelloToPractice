package com.himanshu.practice.y2019.April;

import com.himanshu.practice.y2018.sept.moveInSync.MAtrixDFS;
import lombok.AllArgsConstructor;
import lombok.ToString;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by himanshubhardwaj on 18/04/19.
 * 12:13 pm -- 1:35 pm
 */
public class April18 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);

        Graph g = new Graph(n, m);

        for (int i = 0; i < m; i++) {
            str = br.readLine().split(" ");
            int s = Integer.parseInt(str[0]);
            int d = Integer.parseInt(str[1]);
            int w = Integer.parseInt(str[2]);
            g.insert(s, d, w);
        }

        //List<Graph.Edge> edges = g.transitiveClosure();
//        System.out.println("Transitive closure.");
//        for (Graph.Edge e : edges) {
//            System.out.println(e);
//        }
//
//
//        System.out.println();
//        System.out.println();
//        System.out.println("BellmonFord algorithm");
//        edges = g.bellmonFordSSSP(0);
//        for (Graph.Edge e : edges) {
//            System.out.println(e);
//        }

        System.out.println();
        System.out.println();
        System.out.println("Floyed Warshell");
        List<Graph.Edge> edges = g.floyedWarshelAPSP();
        for (Graph.Edge e : edges) {
            System.out.println(e);
        }

    }
}
/*
8 11
0 1 2
0 2 3
0 3 5
1 2 4
1 6 3
2 6 -3
3 2 4
3 5 -4
5 2 1
6 5 10
5 7 2
* */

//Directed
class Graph {
    int[][] adjMat;
    ArrayList<Edge> edgeList;
    int nodes;
    int edges;

    public Graph(int nodes, int edges) {
        edgeList = new ArrayList<>(edges);
        this.nodes = nodes;
        this.edges = edges;
        edgeList = new ArrayList<Edge>();
        adjMat = new int[nodes][nodes];
    }

    void insert(int source, int destination, int weight) {
        adjMat[source][destination] = weight;
        edgeList.add(new Edge(source, destination, weight));
    }

    //Tested
    //assuming graph is unweighted
    ArrayList<Edge> transitiveClosure() {
        int adjMatC[][] = this.adjMat.clone();

        for (int i = 0; i < nodes; i++) {
            for (int j = 0; j < nodes; j++) {
                for (int k = 0; k < nodes; k++) {
                    if (Math.abs(adjMatC[j][i]) > 0 && Math.abs(adjMatC[i][k]) > 0) {
                        adjMatC[j][k] = 1;
                    }
                }
            }
        }

        ArrayList<Edge> list = new ArrayList<>();

        for (int i = 0; i < nodes; i++) {
            for (int j = 0; j < nodes; j++) {
                if (adjMatC[i][j] > 0) {
                    list.add(new Edge(i, j, 1));
                }
            }
        }

        return list;
    }

    //tested
    ArrayList<Edge> bellmonFordSSSP(int source) {
        int shortestPAth[] = new int[nodes];

        for (int i = 0; i < nodes; i++) {
            shortestPAth[i] = Integer.MAX_VALUE;
        }
        shortestPAth[source] = 0;


        for (int i = 0; i < nodes; i++) {
            for (Edge e : edgeList) {
                if (shortestPAth[e.source] != Integer.MAX_VALUE) {
                    if (shortestPAth[e.destination] == Integer.MAX_VALUE) {
                        shortestPAth[e.destination] = shortestPAth[e.source] + e.weight;
                    } else {
                        //Overflow alert
                        shortestPAth[e.destination] = Math.min(shortestPAth[e.destination], shortestPAth[e.source] + e.weight);
                    }
                }
            }
        }

        ArrayList<Edge> arrayList = new ArrayList<>();

        for (int i = 0; i < shortestPAth.length; i++) {
            arrayList.add(new Edge(source, i, shortestPAth[i]));
        }

        return arrayList;
    }


    ArrayList<Edge> floyedWarshelAPSP() {
        int cloneMAt[][] = adjMat.clone();

        for (int i = 0; i < nodes; i++) {
            for (int j = 0; j < nodes; j++) {
                System.out.print(cloneMAt[i][j]+"\t");
            }
            System.out.println();
        }

        for (int i = 0; i < nodes; i++) {
            for (int j = 0; j < nodes; j++) {
                cloneMAt[i][j] = (cloneMAt[i][j] == 0) ? Integer.MAX_VALUE : cloneMAt[i][j];
            }
        }


        for (int k = 0; k < nodes; k++) {
            for (int i = 0; i < nodes; i++) {
                for (int j = 0; j < nodes; j++) {
                    if (cloneMAt[i][k] != Integer.MAX_VALUE && cloneMAt[k][j] != Integer.MAX_VALUE) {
                        cloneMAt[i][j] = Math.min(cloneMAt[i][j], cloneMAt[i][k] + cloneMAt[k][j]);
                    }
                }
            }
        }


        ArrayList<Edge> sPaths = new ArrayList<>();
        for (int i = 0; i < nodes; i++) {
            for (int j = 0; j < nodes; j++) {
                if (cloneMAt[i][j] != Integer.MAX_VALUE) {
                    sPaths.add(new Edge(i, j, cloneMAt[i][j]));
                }
            }
        }
        return sPaths;
    }


    @AllArgsConstructor
    @ToString
    static class Edge {
        int source;
        int destination;
        int weight;
    }
}


