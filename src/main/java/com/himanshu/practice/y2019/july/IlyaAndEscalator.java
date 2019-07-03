package com.himanshu.practice.y2019.july;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 03/07/19.
 * Statement: https://codeforces.com/contest/518/problem/D
 * Submission: https://codeforces.com/contest/518/submission/56467113
 * Algo: DP, probablity
 */
public class IlyaAndEscalator {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        double p = Double.parseDouble(str[1]);
        int t = Integer.parseInt(str[2]);

        double dp[][] = new double[n + 1][t + 1];
        dp[0][0] = 1;

        for (int j = 1; j <= t; j++) {
            dp[0][j] = dp[0][j - 1] * (1 - p);
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= t; j++) {
                if (i<n) {
                    dp[i][j] = ((1 - p) * dp[i][j - 1]) + (p * dp[i - 1][j - 1]);
                } else {
                    dp[i][j] = (dp[i][j - 1]) + (p * dp[i - 1][j - 1]);
                }
            }
        }


//        for (int i = 0; i <= n; i++) {
//            for (int j = 0; j <= t; j++) {
//
//                System.out.print(dp[i][j] + "\t\t\t");
//            }
//            System.out.println();
//        }

        double expectedPeople = 0;

        for (int i = 0; i <= n; i++) {
            expectedPeople += dp[i][t] * i;
        }

        System.out.print(expectedPeople);
    }

}
