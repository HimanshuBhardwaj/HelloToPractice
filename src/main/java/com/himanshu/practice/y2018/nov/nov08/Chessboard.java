package com.himanshu.practice.y2018.nov.nov08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 08/11/18.
 * Statement: https://codeforces.com/contest/961/problem/C
 * 2:01-2:25 : Could have saved around 10 mins
 * Algo: Adhoc
 * Submission:  https://codeforces.com/contest/961/submission/45437524
 */
public class Chessboard {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Board[] b = new Board[4];


        for (int i = 0; i < 4; i++) {
            b[i] = new Board(n);

            for (int j = 0; j < n; j++) {
                String str = br.readLine();

                for (int k = 0; k < n; k++) {
                    if (str.charAt(k) != '0') {
                        b[i].mat[j][k] = 1;
                    }
                }
            }
            if (i != 3) {
                String ss = br.readLine();
            }
        }

        int cost = Integer.MAX_VALUE;
        for (int i = 0; i < 4; i++) {
            for (int j = i + 1; j < 4; j++) {
                cost = Math.min(cost, Board.computeCostToColourWhite(b, i, j) + Board.computeCostToColourBlack(b, i, j));
            }
        }
        System.out.print(cost);
    }
}


class Board {
    int[][] mat;

    public Board(int n) {
        mat = new int[n][n];
    }

    public static int computeCostToColourWhite(Board[] boa, int a, int b) {
        return costToPrintWhite(boa[a].mat) + costToPrintWhite(boa[b].mat);


    }

    private static int costToPrintWhite(int[][] mat) {
        int cost = 0;
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                if ((i + j) % 2 == 0) {
                    if (mat[i][j] != 0) {
                        cost++;
                    }
                } else {
                    if (mat[i][j] != 1) {
                        cost++;
                    }
                }
            }
        }
        return cost;
    }


    private static int costToPrintBlack(int[][] mat) {
        int cost = 0;
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                if ((i + j) % 2 == 1) {
                    if (mat[i][j] != 0) {
                        cost++;
                    }
                } else {
                    if (mat[i][j] != 1) {
                        cost++;
                    }
                }
            }
        }
        return cost;
    }

    public static int computeCostToColourBlack(Board[] boa, int a, int b) {
        int cost = 0;

        for (int i = 0; i < 4; i++) {
            if (i != a && i != b) {
                cost += costToPrintBlack(boa[i].mat);
            }

        }

        return cost;
    }
}
