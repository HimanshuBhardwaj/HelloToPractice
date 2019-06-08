package com.himanshu.practice.y2019.june.june6;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 06/06/19.
 * Statement: https://codeforces.com/contest/706/problem/C
 * Algo: DP
 * Submission: https://codeforces.com/contest/706/submission/55309962
 */
public class HardProblem {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long cost[] = new long[n];
        String c[] = br.readLine().split(" ");


        for (int i = 0; i < n; i++) {
            cost[i] = Long.parseLong(c[i]);
        }

        String strings[] = new String[n];
        long DP[][] = new long[n][2];

        for (int i = 0; i < n; i++) {
            DP[i][0] = Long.MAX_VALUE;
            DP[i][1] = Long.MAX_VALUE;
        }

        strings[0] = br.readLine();
        DP[0][0] = 0;
        DP[0][1] = cost[0];


        for (int i = 1; i < n; i++) {
            strings[i] = br.readLine();
            String rotatedS = new StringBuffer(strings[i]).reverse().toString();

            //do not rotate
            if (strings[i].compareTo(strings[i - 1]) >= 1) {
                DP[i][0] = Math.min(DP[i][0], DP[i - 1][0]);
            }
            if (strings[i].compareTo(new StringBuffer(strings[i - 1]).reverse().toString()) >= 1) {
                DP[i][0] = Math.min(DP[i][0], DP[i - 1][1]);
            }
            if (strings[i].compareTo(strings[i - 1]) == 0) {
                DP[i][0] = Math.min(DP[i][0], DP[i - 1][0]);
            }
            if (strings[i].compareTo(new StringBuffer(strings[i - 1]).reverse().toString()) == 0) {
                DP[i][0] = Math.min(DP[i][0], DP[i - 1][1]);
            }

            //or rotate
            if (DP[i - 1][0] != Long.MAX_VALUE) {
                if (rotatedS.compareTo(strings[i - 1]) >= 1) {
                    DP[i][1] = Math.min(DP[i][1], DP[i - 1][0] + cost[i]);
                }
                if (rotatedS.compareTo(strings[i - 1]) == 0) {
                    DP[i][1] = Math.min(DP[i][1], DP[i - 1][0] + cost[i]);
                }
            }

            if (DP[i - 1][1] != Long.MAX_VALUE) {
                if (rotatedS.compareTo(new StringBuffer(strings[i - 1]).reverse().toString()) >= 1) {
                    DP[i][1] = Math.min(DP[i][1], DP[i - 1][1] + cost[i]);
                }

                if (rotatedS.compareTo(new StringBuffer(strings[i - 1]).reverse().toString()) == 0) {
                    DP[i][1] = Math.min(DP[i][1], DP[i - 1][1] + cost[i]);
                }
            }


        }


//        for (int i = 0; i < n; i++) {
//            System.out.print(DP[i][0] + "\t");
//        }
//        System.out.println();
//        for (int i = 0; i < n; i++) {
//            System.out.print(DP[i][1] + "\t");
//        }
//        System.out.println();

        if (Math.min(DP[n - 1][0], DP[n - 1][1]) != Long.MAX_VALUE) {
            System.out.print(Math.min(DP[n - 1][0], DP[n - 1][1]));
        } else {
            System.out.println(-1);
        }


//        System.out.println();
//        System.out.println();
//        System.out.println("qp".compareTo("bi"));
//        System.out.println("pq".compareTo("ib"));
//        System.out.println("qp".compareTo("bi"));
//        System.out.println("pq".compareTo("ib"));


    }
}
