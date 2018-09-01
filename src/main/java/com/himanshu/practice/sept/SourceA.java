package com.himanshu.practice.sept;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 01/09/18.
 * Problem: Two lines A, B; A[i], B[i] may be swapped, you have to tell if they can be make non decreasing or not.
 */
public class SourceA {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] a = new int[n];
        int[] b = new int[n];
        String str[] = br.readLine().split(" ");

        for (int i = 0; i < str.length; i++) {
            a[i] = Integer.parseInt(str[i]);
        }

        str = br.readLine().split(" ");

        for (int i = 0; i < str.length; i++) {
            b[i] = Integer.parseInt(str[i]);
        }

        boolean dp[][] = new boolean[n][2];

        dp[0][0] = true; //without swapping
        dp[0][1] = true; // with swaping


        for (int i = 1; i < n; i++) {
            dp[i][0] = ((a[i] >= a[i - 1]) && (b[i] >= b[i - 1])) && (dp[i - 1][0] || dp[i - 1][1]);
            dp[i][1] = ((a[i] >= b[i - 1]) && (b[i] >= a[i - 1])) && (dp[i - 1][0] || dp[i - 1][1]);
        }

        if(dp[n-1][0] || dp[n-1][1]) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
