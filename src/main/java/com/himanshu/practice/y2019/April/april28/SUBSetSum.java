package com.himanshu.practice.y2019.April.april28;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 29/04/19.
 * 20 mins + 10 mins backtrack
 * 1:10 -- 1:23 am
 */
public class SUBSetSum {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int sum = Integer.parseInt(br.readLine());
        String[] str = br.readLine().split(" ");
        int[] arr = new int[str.length];


        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(str[i]);
        }

        //assuming no weight weinging to 0
        boolean dp[][] = new boolean[1 + arr.length][1 + sum];
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = true;
        }
        for (int i = 1; i < dp[0].length; i++) {
            dp[0][i] = false;
        }


        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                dp[i][j] = dp[i - 1][j];

                if (j >= arr[i - 1]) {
                    dp[i][j] |= dp[i - 1][j - arr[i - 1]];
                }
            }
        }

        System.out.println(dp[arr.length][sum]);


    }
}
