package com.himanshu.practice.y2018.Aug.Aug3.codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.TreeMap;

/**
 * Created by himanshubhardwaj on 03/08/18.
 * TODO: Complete it
 * I could not finish in contexst
 */
public class C {
    static long[][] glad;
    static boolean visited[][];
    static long collectedGlad[][];
    static long timer = -1;
    static long sumGlad = 0;
    static int remaininpos = 0;
    static int n;
    static TreeMap<Integer, TreeMap<Integer, Long>>[] DP = new TreeMap[2];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        glad = new long[2][n];
        visited = new boolean[2][n];
        collectedGlad = new long[2][n];
        sumGlad = 0;
        remaininpos = 2 * n;
        DP[0] = new TreeMap<>();
        DP[1] = new TreeMap<>();


        String str[] = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            glad[0][i] = Long.parseLong(str[i]);
            sumGlad = sumGlad + glad[0][i];
        }

        str = br.readLine().split(" ");

        for (int i = 0; i < n; i++) {
            glad[1][i] = Long.parseLong(str[i]);
            sumGlad = sumGlad + glad[1][i];
        }


        System.out.print(getMaxGladBF(0, 0, -1));

    }


    private static long getMaxGladBF(int row, int colum, int timer) {
        if (notValidPos(row, colum) || timer > 2 * n) {
            return 0l;
        }

        timer++;
        visited[row][colum] = true;

        if (DP[row].containsKey(colum) && DP[row].get(colum).containsKey(timer)) {
            return DP[row].get(colum).get(timer);
        }


        LinkedList<Pos> getPositions = getPositions(row, colum, 0, 0);


        long max = 0;
        for (Pos p : getPositions) {
            max = Math.max(max, getMaxGladBF(p.row, p.column, timer + 1));
        }

        if (DP[row].get(colum) == null) {
            DP[row].put(colum, new TreeMap<Integer, Long>());
        }

        DP[row].get(colum).put(timer, max + timer * glad[row][colum]);
        return DP[row].get(colum).get(timer);
    }


    private static long getMaxGlad(int row, int colum) {
        if (notValidPos(row, colum)) {
            return 0l;
        }
        if (visited[row][colum]) {
            return 0l;
        }
        timer++;
        //System.out.println(row + "  " + colum + "\t" + timer);
        visited[row][colum] = true;
        collectedGlad[row][colum] = timer * ((long) glad[row][colum]);
        sumGlad = sumGlad - glad[row][colum];
        remaininpos--;

        LinkedList<Pos> getPositions = getPositions(row, colum, sumGlad, (int) timer);

        for (Pos p : getPositions) {
            collectedGlad[row][colum] += getMaxGlad(p.row, p.column);
        }

        return collectedGlad[row][colum];
    }


    private static LinkedList<Pos> getPositions(int row, int colum, long sumGlad, int time) {
        LinkedList<Pos> list = new LinkedList<>();
        Pos up = new Pos(row - 1, colum, getValue(row - 1, colum, sumGlad), (time + 1));
        Pos down = new Pos(row + 1, colum, getValue(row + 1, colum, sumGlad), time + 1);
        Pos left = new Pos(row, colum - 1, getValue(row, colum - 1, sumGlad), time + 1);
        Pos right = new Pos(row, colum + 1, getValue(row, colum + 1, sumGlad), time + 1);

        list.add(up);
        list.add(down);
        list.add(left);
        list.add(right);

        //Collections.insertionSort(list);
        return list;
    }

    private static long getValue(int row, int colum, long sumGlad) {
        if (notValidPos(row, colum)) {
            return 0;
        }

        if (visited[row][colum]) {
            return 0;
        }

        return (sumGlad - glad[row][colum]) + remaininpos * (sumGlad - glad[row][colum]);


    }

    private static boolean notValidPos(int row, int colum) {
        if (row < 0 || colum >= glad[0].length || colum < 0 || row > 1) {
            return true;
        }

        return false;

    }
}

class Pos implements Comparable<Pos> {
    int row;
    int column;
    long value;
    int time;

    public Pos(int row, int column, long value, int time) {
        this.row = row;
        this.column = column;
        this.value = value;
        this.time = time;
    }

    @Override
    public int compareTo(Pos o) {
        return (int) ((o.time * o.value) - (this.time * this.value));
    }
}