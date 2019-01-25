package com.himanshu.practice.y2018.july.july21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

/**
 * Created by himanshubhardwaj on 21/07/18.
 * ProblemStatement: https://codeforces.com/contest/723/problem/D
 * Algo: DFS, Processing
 * Submission: https://codeforces.com/contest/723/submission/40613111
 */
public class LakesInBerland {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);
        int minLakes = Integer.parseInt(str[2]);

        String segment;
        char area[][] = new char[n][m];


        for (int i = 0; i < n; i++) {
            segment = br.readLine();
            area[i] = segment.toCharArray();
        }

        countLakes(n, m, minLakes, area);



    }

    private static void countLakes(int n, int m, int k, char[][] area) {
        //System.out.println(area.length + "\t" + area[0].length);
        ArrayList<Integer> oceanRow = new ArrayList<>();
        ArrayList<Integer> oceanColumn = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            fillOcean(i, 0, n, m, area, oceanRow, oceanColumn);
            fillOcean(i, m - 1, n, m, area, oceanRow, oceanColumn);
        }

        for (int i = 0; i < m; i++) {
            fillOcean(0, i, n, m, area, oceanRow, oceanColumn);
            fillOcean(n - 1, i, n, m, area, oceanRow, oceanColumn);
        }

        char[][] filledArea = new char[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                filledArea[i][j] = area[i][j];
            }
        }

        LinkedList<Lake> lakes = new LinkedList<>();
        int lakeCount = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (filledArea[i][j] == '.') {
                    Lake lake = new Lake();
                    lakes.addLast(lake);
                    lake.index = ++lakeCount;
                    //lake.posR.addLast(i);
                    //lake.posC.addLast(j);
                    countLakeSizes(i, j, n, m, filledArea, lake);
                }
            }
        }

//        for(Lake l:lakes){
//            System.out.println(l);
//        }


        Collections.sort(lakes);

        int count = 0;

//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < m; j++) {
//                System.out.print(area[i][j]);
//            }
//            if (i != (n - 1)) {
//                System.out.println();
//            }
//        }
//        System.out.println();
//        System.out.println();

        for (int i = k; i < lakes.size(); i++) {
            Lake lakeToFill = lakes.get(i);
            ArrayList<Integer> rows = new ArrayList<>(lakeToFill.posR);
            ArrayList<Integer> column = new ArrayList<>(lakeToFill.posC);

            for (int j = 0; j < rows.size(); j++) {
                count++;
                area[rows.get(j)][column.get(j)] = '*';
            }
        }

//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < m; j++) {
//                System.out.print(area[i][j]);
//            }
//            if (i != (n - 1)) {
//                System.out.println();
//            }
//        }
//        System.out.println();
//        System.out.println();

        for(int i=0;i<oceanColumn.size();i++) {
            area[oceanRow.get(i)][oceanColumn.get(i)]='.';
        }

        System.out.println(count);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(area[i][j]);
            }
            if (i != (n - 1)) {
                System.out.println();
            }
        }
        //
    }

    private static void countLakeSizes(int row, int column, int n, int m, char[][] filledArea, Lake lake) {
        if (!followLakeConstrains(row, column, n, m, filledArea)) {
            return;
        }

        lake.size++;
        lake.posR.addLast(row);
        lake.posC.addLast(column);
        filledArea[row][column] = '*';
        countLakeSizes(row - 1, column, n, m, filledArea, lake);
        countLakeSizes(row + 1, column, n, m, filledArea, lake);
        countLakeSizes(row, column - 1, n, m, filledArea, lake);
        countLakeSizes(row, column + 1, n, m, filledArea, lake);

    }

    private static boolean followLakeConstrains(int row, int column, int n, int m, char[][] filledArea) {
        if (row >= 0 && row < n && column >= 0 && column < m && filledArea[row][column] == '.') {
            return true;
        }
        return false;
    }

    private static void fillOcean(int row, int column, int n, int m, char[][] area, ArrayList<Integer> oceanRow, ArrayList<Integer> oceancolumn) {
        if (!followConstrainsforOcean(row, column, n, m, area)) {
            return;
        }

        area[row][column] = '*';
        oceanRow.add(oceanRow.size(), row);
        oceancolumn.add(oceancolumn.size(), column);
        fillOcean(row - 1, column, n, m, area, oceanRow, oceancolumn);
        fillOcean(row + 1, column, n, m, area, oceanRow, oceancolumn);
        fillOcean(row, column - 1, n, m, area, oceanRow, oceancolumn);
        fillOcean(row, column + 1, n, m, area, oceanRow, oceancolumn);
    }

    //it will also check aboout the ocean this.
    private static boolean followConstrainsforOcean(int row, int column, int n, int m, char[][] area) {
        if (row >= 0 && row < n && column >= 0 && column < m && area[row][column] == '.') {
            return true;
        }
        return false;
    }
}


class Lake implements Comparable<Lake> {
    int index;//index 0 for land, 1 for ocean and 2 for lake
    int size;
    LinkedList<Integer> posR = new LinkedList<>();
    LinkedList<Integer> posC = new LinkedList<>();

    public Lake() {
    }

    @Override
    public int compareTo(Lake o) {
        return  o.size-this.size ;
    }

    public String toString() {
        return "Lake(index=" + this.index + ", size=" + this.size + ", posR=" + this.posR + ", posC=" + this.posC + ")";
    }
}


/*
*
*
*
*


5 5 1
***..
****.
*.*.*
*.*.*
.****




*
* */