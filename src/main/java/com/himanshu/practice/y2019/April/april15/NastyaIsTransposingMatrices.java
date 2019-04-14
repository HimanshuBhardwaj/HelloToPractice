package com.himanshu.practice.y2019.April.april15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by himanshubhardwaj on 14/04/19.
 * Statemenht: https://codeforces.com/contest/1136/problem/C
 * Algo: Sorting
 * 6:40--7:01
 */
public class NastyaIsTransposingMatrices {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);

        int arr[][] = new int[n][m];
        int brr[][] = new int[n][m];

        for (int i = 0; i < n; i++) {
            str = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(str[j]);
            }
        }

        Digonals d1 = new Digonals(n, m, arr);

        for (int i = 0; i < n; i++) {
            str = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                brr[i][j] = Integer.parseInt(str[j]);
            }
        }

        Digonals d2 = new Digonals(n, m, brr);

        //d1.printDigonal();
        //d2.printDigonal();

        if (d1.equals(d2)) {
            System.out.print("YES");
        } else {
            System.out.print("NO");
        }


    }
}


class Digonals {
    ArrayList<Integer>[] digonals;
    int n;
    int m;
    int arr[][];

    public Digonals(int n, int m, int[][] arr) {
        this.n = n;
        this.m = m;
        this.arr = arr;
        digonals = new ArrayList[n + m];

        for (int i = 0; i < (n + m); i++) {
            digonals[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int pos = (i + j);
                digonals[pos].add(arr[i][j]);
            }
        }

        for (int i = 0; i < digonals.length; i++) {
            Collections.sort(digonals[i]);
        }
    }


    public void printDigonal() {
        for (int i = 0; i < digonals.length; i++) {
            System.out.println(1 + ": " + digonals[i]);
        }
        System.out.println("--------------END--------------");

    }

    public boolean equals(Object obj) {
        Digonals d1 = this;
        Digonals d2 = (Digonals) obj;

        for (int i = 0; i < d1.digonals.length; i++) {
            if (!d1.digonals[i].equals(d2.digonals[i])) {
                return false;
            }
        }
        return true;
    }


}
