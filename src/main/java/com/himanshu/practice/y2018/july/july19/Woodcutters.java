package com.himanshu.practice.y2018.july.july19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 19/07/18.
 * Problem Statement: https://codeforces.com/contest/545/problem/C
 * Algo: DP
 * Submission:https://codeforces.com/contest/545/submission/40533978
 */
public class Woodcutters {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int pos[] = new int[n];
        int height[] = new int[n];
        String str[];
        int DP[][] = new int[n][3];

        for (int i = 0; i < n; i++) {
            str = br.readLine().split(" ");
            pos[i] = Integer.parseInt(str[0]);
            height[i] = Integer.parseInt(str[1]);
        }
        if (n == 1) {
            System.out.println("1");
            return;
        }

        DP[0][1] = 1;
        DP[0][2] = ((pos[1] - height[0]) > pos[0]) ? 1 : 0;
        //print(DP, 0, 0, "\n");

        int i = 1;
        int max;
        for (i = 1; i < n; i++) {
            max = Math.max(Math.max(DP[i - 1][0], DP[i - 1][1]), DP[i - 1][2]);
            DP[i][0] = max;


            //0 --> no cut
            //1 --> left
            //2 right
            if (pos[i] > (pos[i - 1] + height[i])) {
                DP[i][1] = 1 + Math.max(DP[i - 1][0], DP[i - 1][1]);

                if (pos[i] > (pos[i - 1] + height[i - 1] + height[i])) {
                    DP[i][1] = Math.max(DP[i][1], 1 + DP[i - 1][2]);
                }
            } else {
                DP[i][1] = max;
            }


            if (i == n - 1) {
                DP[i][2] = 1 + max;
            } else {
                if (pos[i + 1] > (pos[i] + height[i])) {
                    DP[i][2] = 1 + max;
                } else {
                    DP[i][2] = max;
                }
            }

          //  print(DP, i, max, "\n");
        }

        System.out.print(Math.max(Math.max(DP[i - 1][0], DP[i - 1][1]), DP[i - 1][2]));
    }

    public static void print(int[][] DP, int pos, int max, String str) {
        System.out.print(DP[pos][0] + "\t" + DP[pos][1] + "\t" + DP[pos][2] + "\t" + max + str);
    }
}
