package com.himanshu.practice.y2018.july.july23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 23/07/18.
 * Statement: https://codeforces.com/contest/414/problem/B
 * Algo: DP
 * Submission: https://codeforces.com/contest/414/submission/40659444
 */

public class MashmokhAndACM {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n, length;
        String st[] = br.readLine().split(" ");
        n = Integer.parseInt(st[0]);
        length = Integer.parseInt(st[1]);

        long DP[][] = new long[n + 1][length + 1];

        //DP[i][j] --> subsequence of length j ending at i
        for (int i = 1; i <= n; i++) {
            DP[i][1] = 1;
        }

        long sum = 0;

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < length; j++) {
                for (int k = 1; (k * i) <= n; k++) {
                    DP[k * i][j + 1] = (DP[k * i][j + 1] + DP[i][j]) % 1000000007;
                }
            }
        }

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= length; j++) {
                sum = (sum + DP[i][j]) % 1000000007;
            }
        }
        System.out.print(sum);
    }
}
