package com.himanshu.practice.Aug.aug26.goldmansacs.women;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by himanshubhardwaj on 26/08/18.
 * Detect Longest letter L in the matrix
 */
public class TestClass {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");

        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);


        int arr[][] = new int[n][m];
        int visited[][] = new int[n][m];


        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < s.length(); j++) {
                arr[i][j] = (s.charAt(j) == '.') ? 0 : -1;
            }
        }

        int size = DFS(arr, visited);
        if (size == -1) {
            System.out.println("NO");
        } else {
            System.out.println("YES");
            System.out.println(size);
        }

    }

    private static int DFS(int[][] arr, int[][] visited) {
        List<Cluster> linkedList = new LinkedList<Cluster>();
        int clusterIndex = 1;


        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] == 0) {
                    Cluster c = new Cluster(clusterIndex);
                    DFSHelper(i, j, arr, c);
                    linkedList.add(c);
                    clusterIndex++;
                }
            }
        }

        //System.out.println(linkedList.size());

        int result = -1;
        for (Cluster c : linkedList) {
            Collections.sort(c.points);
            int tResult = isL(c.points);
           // System.out.println("..." + tResult);
            if (result < tResult) {
                result = tResult;
            }
        }
        return result;
    }


    private static int isL(ArrayList<Point> points) {
        if (points.size() <= 1) {
            return -1;
        }

        int endPosTillVerLine = -1;

        int countLength = 0;

        for (int i = 0; (endPosTillVerLine == -1) && (i < points.size()); i++) {
            if (i < (points.size() - 1)) {
                if (points.get(i).c != points.get(i + 1).c) {
                    endPosTillVerLine = i;
                } else {
                    countLength++;
                }
            }
        }

        int countWidth = 1;
        for (int i = endPosTillVerLine; (i != -1) && i < points.size(); i++) {
            if (i < (points.size() - 1)) {
                if (points.get(i).r == points.get(i + 1).r) {
                    countWidth++;
                }
            }
        }

        //System.out.println(points + "\t" + countLength + "\t" + countWidth + "\t" + points.size());

        if ((countLength > 1 && countLength > 1) && ((countWidth + countLength) == points.size())) {
            return points.size();
        }
        return -1;
    }


    private static void DFSHelper(int row, int column, int[][] arr, Cluster c) {
        if (!valid(row, column, arr.length, arr[0].length) || (arr[row][column] == -1) || (arr[row][column] > 0)) {
            return;
        }

        c.points.add(new Point(row, column));
        arr[row][column] = c.index;

        DFSHelper(row, column + 1, arr, c);
        DFSHelper(row, column - 1, arr, c);
        DFSHelper(row - 1, column, arr, c);
        DFSHelper(row + 1, column, arr, c);
    }

    private static boolean valid(int row, int column, int rowSize, int colSize) {
        if (row < 0 || column < 0 || row >= rowSize || column >= colSize) {
            return false;
        }
        return true;
    }

    private static void printArray(int[][] arr) {
        System.out.println();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }
}

class Cluster {
    int index;
    ArrayList<Point> points = new ArrayList<>();


    public Cluster(int index) {
        this.index = index;
    }

    public String toString() {
        return this.index + ":\t" + this.points + ")";
    }
}


class Point implements Comparable<Point> {
    int r;
    int c;


    public Point(int r, int c) {
        this.r = r;
        this.c = c;
    }

    @Override
    public int compareTo(Point o) {
        if (this.c == o.c) {
            return this.r - o.r;
        } else {
            return this.c - o.c;
        }
    }

    public String toString() {
        return "(" + this.r + ", " + this.c + ")";
    }
}

/*
Input:

4 3
.**
.**
.**
..*
* */
