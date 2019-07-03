package com.himanshu.practice.y2019.july;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 03/07/19.
 * Submission: https://codeforces.com/contest/540/problem/D
 * Algo: DP, Probablity
 * Statement: https://codeforces.com/contest/540/problem/D
 */
public class BadLuckIsland {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");
        int r = Integer.parseInt(str[0]);
        int s = Integer.parseInt(str[1]);
        int p = Integer.parseInt(str[2]);

        double[][][] dp = new double[r + 1][s + 1][p + 1];
        dp[r][s][p] = 1;


        for (int i = r; i >= 0; i--) {
            for (int j = s; j >= 0; j--) {
                for (int k = p; k >= 0; k--) {
                    double temp = dp[i][j][k];
                    double temp1 = (double) (i * j + j * k + k * i);

                    if (temp1 == 0) {
                        continue;
                    }

                    if (j != 0) {
                        dp[i][j - 1][k] += (i * j * temp) / temp1;
                    }
                    if (k != 0) {
                        dp[i][j][k - 1] += (k * j * temp) / temp1;
                    }
                    if (i != 0) {
                        dp[i - 1][j][k] += (k * i * temp) / temp1;
                    }
                }
            }
        }


//        for (int i = r; i >= 0; i--) {
//            for (int j = s; j >= 0; j--) {
//                for (int k = p; k >= 0; k--) {
//                    if (i == 0 || j == 0 || k == 0) {
//                        System.out.println(i + "," + j + "," + k + ": " + dp[i][j][k] + ",");
//                    }
//                }
//            }
//        }
//
//        System.out.println();

        double R = 0;
        double S = 0;
        double P = 0;

        for (int i = 1; i <= r; i++) {
            //System.out.print(dp[i][0][0] + ",");
            R += dp[i][0][0];
        }
        //System.out.println();
        for (int i = 1; i <= s; i++) {
            S += dp[0][i][0];
          //  System.out.print(dp[0][i][0] + ",");
        }
        //System.out.println();

        for (int i = 1; i <= p; i++) {
            P += dp[0][0][i];
          //  System.out.print(dp[0][0][i] + ",");
        }
        //System.out.println();

        System.out.print(R + " " + S + " " + P);


    }
}


