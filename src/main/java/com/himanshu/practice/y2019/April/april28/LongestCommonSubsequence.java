package com.himanshu.practice.y2019.April.april28;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 29/04/19.
 * 30 mins
 * 12:52 -- 1:07
 */
public class LongestCommonSubsequence {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s1 = br.readLine();
        String s2 = br.readLine();


        System.out.println(longestCommonSubsequece(s1, s2));

    }

    private static int longestCommonSubsequece(String s1, String s2) {
        int[][] DP = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                DP[i][j] = (s1.charAt(i - 1) == s2.charAt(j - 1)) ? DP[i - 1][j - 1] + 1 : (Math.max(DP[i][j - 1], DP[i - 1][j]));
            }

        }
        return DP[s1.length()][s2.length()];
    }
}
