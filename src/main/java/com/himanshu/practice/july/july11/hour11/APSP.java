package com.himanshu.practice.july.july11.hour11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Himanshu Bhardwaj on 11/07/18.
 */
public class APSP {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);

        Graph graph = new Graph(n);

        for (int i = 0; i < m; i++) {
            str = br.readLine().split(",");
            graph.insert(Integer.parseInt(str[0]), Integer.parseInt(str[1]), Integer.parseInt(str[2]));
        }


        System.out.println("---------------Printing Tree---------------");
        graph.print(graph.adjMat);
        System.out.println("---------------floydWarshall---------------");
        graph.print(graph.floydWarshall());
        System.out.println("---------------transitiveClosure---------------");
        graph.print(graph.transitiveClosure());
    }

}


class Graph {
    int adjMat[][];

    public Graph(int n) {
        adjMat = new int[n][n];
    }

    public void insert(int source, int destination, int weight) {
        adjMat[source][destination] = weight;
    }

    public int[][] floydWarshall() {
        int[][] temp = new int[adjMat.length][];//adjMat.clone();

        for (int i = 0; i < adjMat.length; i++) {
            temp[i] = adjMat[i].clone();
        }

        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp.length; j++) {
                if (temp[i][j] == 0) {
                    temp[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        for (int k = 0; k < temp.length; k++) {
            for (int i = 0; i < temp.length; i++) {
                for (int j = 0; j < temp.length; j++) {
                    if (temp[i][k] != Integer.MAX_VALUE && temp[k][j] != Integer.MAX_VALUE) {
                        temp[i][j] = Math.min(temp[i][j], temp[i][k] + temp[k][j]);
                    }
                }
            }
        }
        return temp;
    }


    public int[][] transitiveClosure() {
        int[][] temp = new int[adjMat.length][];//adjMat.clone();

        for (int i = 0; i < adjMat.length; i++) {
            temp[i] = adjMat[i].clone();
        }


        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp.length; j++) {
                if (temp[i][j] > 0) {
                    temp[i][j] = 1;
                }
            }
        }


        for (int k = 0; k < temp.length; k++) {
            for (int i = 0; i < temp.length; i++) {
                for (int j = 0; j < temp.length; j++) {
                    if (temp[i][k] == 1 && temp[k][j] == 1) {
                        temp[i][j] = 1;
                    }
                }
            }
        }

        return temp;
    }

    public void print(int[][] adjMat) {
        for (int i = 0; i < adjMat.length; i++) {
            for (int j = 0; j < adjMat.length; j++) {
                System.out.print(adjMat[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
