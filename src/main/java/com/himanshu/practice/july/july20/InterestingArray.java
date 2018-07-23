package com.himanshu.practice.july.july20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 19/07/18.
 *Problem Statement:https://codeforces.com/contest/118/problem/D
 * Algo: This question id DP but present state is constrianed on last value which I took.
 *Submission: https://codeforces.com/contest/118/submission/40560660
 */
public class InterestingArray {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");
        int n1 = Integer.parseInt(str[0]);
        int n2 = Integer.parseInt(str[1]);
        int k1 = Integer.parseInt(str[2]);
        int k2 = Integer.parseInt(str[3]);

        int DP[][][] = new int[n1 + 1][n2 + 1][2];

        for (int i = 0; i <= Math.min(n1, k1); i++) {
            DP[i][0][0] = 1;
        }

        for (int i = 0; i <= Math.min(n2, k2); i++) {
            DP[0][i][1] = 1;
        }


        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                for (int k = 1; k <= k1; k++) {
                    DP[i][j][0] += ((i >= k) ? DP[i - k][j][1] : 0);
                }
                for (int k = 1; k <= k2; k++) {
                    DP[i][j][1] += ((j >= k) ? DP[i][j - k][0] : 0);
                }

                DP[i][j][0] = DP[i][j][0] % 100000000;
                DP[i][j][1] = DP[i][j][1] % 100000000;
            }
        }

        System.out.println((DP[n1][n2][0] + DP[n1][n2][1]) % 100000000);


    }
}
