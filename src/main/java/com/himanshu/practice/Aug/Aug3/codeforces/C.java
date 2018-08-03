package com.himanshu.practice.Aug.Aug3.codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;

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

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        glad = new long[2][n];
        visited = new boolean[2][n];
        collectedGlad = new long[2][n];
        sumGlad = 0;
        remaininpos = 2 * n;


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


        System.out.print(getMaxGlad(0, 0));


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

        Collections.sort(list);
        return list;
    }

    private static long getValue(int row, int colum, long sumGlad) {
        if (notValidPos(row, colum)) {
            return 0;
        }

        if (visited[row][colum]) {
            return 0;
        }

        return (sumGlad - glad[row][colum]))+remaininpos * (sumGlad - glad[row][colum]));


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