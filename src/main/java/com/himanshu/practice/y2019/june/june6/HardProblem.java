package com.himanshu.practice.y2019.june.june6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 06/06/19.
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

        String str[][] = new String[n][2];

        long[][] DP = new long[n][2];

        for (int i = 0; i < n; i++) {
            str[i][0] = br.readLine();
            StringBuilder sb = new StringBuilder(str[i][0]);
            str[i][1] = sb.reverse().toString();
        }

        for (int i = 1; i < n; i++) {
            if (str[i][0].compareTo(str[i - 1][0]) == 1) {
                if (str[i][0].compareTo(str[i - 1][1]) == 1) {
                    DP[i][0] = Math.min(DP[i - 1][0], DP[i - 1][1] + cost[i]);
                    DP[i][1] = DP[i-1][1];
                } else {
                    DP[i][0] = DP[i - 1][0];
                    DP[i][1] = Long.MAX_VALUE;
                }
            } else if (str[i][0].compareTo(str[i - 1][0]) == -1) {
                if (str[i][1].compareTo(str[i - 1][1]) == 1) {
                    DP[i][1] = Math.min(DP[i - 1][0], DP[i - 1][1] + cost[i - 1]) + cost[i];
                } else {
                    DP[i][1] = DP[i - 1][0] + cost[i];
                }
            } else {
                if (str[i][1].compareTo(str[i - 1][0]) == 0) {
                    DP[i][0] = Math.min(DP[i - 1][0], DP[i - 1][1]);
                    DP[i][1] = Math.min(DP[i - 1][0], DP[i - 1][1]);
                } else {
                    //TODO: Finish it
                }
            }

        }


    }
}
