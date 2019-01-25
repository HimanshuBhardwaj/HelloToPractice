package com.himanshu.practice.y2018.Aug.aug27.norwaytest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 27/08/18.
 * Beautiful question: given n and p, Find expected longest winning streak
 * Took hint from http://lbv-pc.blogspot.com/2012/06/winning-streak.html
 */
public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        while (str != null && (str.length() != 0)) {
            String[] split = str.split(" ");
            int n = Integer.parseInt(split[0]);
            double p = Double.parseDouble(split[1]);
            getProbablity(n, p);
            str = br.readLine();
        }

    }

    static void getProbablity(int n, double p) {
        double dp[][] = new double[n + 1][n + 1];
        double pow[] = new double[n + 1];
        pow[0] = 1;
        for (int i = 1; i <= n; i++) {
            pow[i] = pow[i - 1] * p;
        }


        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if (j >= i) {
                    dp[i][j] = 1d;
                } else if (j == (i - 1)) {
                    dp[i][j] = 1d - pow[i];//Math.pow(p, i);
                } else {
                    //dp[i][j] = (dp[i - 1][j] - (dp[i - j - 2][j] * (1 - p) * Math.pow(p, j + 1)));
                    dp[i][j] = (dp[i - 1][j] - (dp[i - j - 2][j] * (1 - p) * pow[j+1]));
                }
            }
        }
        double exp = 0;

        for (int i = 1; i <= n; i++) {
            exp += i * (dp[n][i] - dp[n][i - 1]);
        }

        System.out.println(exp);
    }
}
