package com.himanshu.practice.sept.sept23.QuESTGlobalCodingChallenge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 23/09/18.
 */
public class PerfectSubarray {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[] arr = new long[n];
        long[] suffixSum = new long[n];
        String[] str = br.readLine().split(" ");

        for (int i = 0; i < arr.length; i++) {
            arr[i] = Long.parseLong(str[i]);
            suffixSum[i] = (i == 0) ? arr[i] : (suffixSum[i - 1] + arr[i]);
        }
        int DP[] = new int[n];
        DP[n - 1] = (isPerfectSquart(arr[n - 1])) ? 1 : 0;


        for (int i = n - 2; i >= 0; i--) {
            int count = 0;

            for (int j = i; j < n; j++) {
        //        System.out.println("Sum: " + i + " " + j + ": " + (suffixSum[j] - suffixSum[i] + arr[i]) + " " + isPerfectSquart(suffixSum[j] - suffixSum[i] + arr[i]));
                if (isPerfectSquart(suffixSum[j] - suffixSum[i] + arr[i])) {
                    count++;
                }
            }

            DP[i] = DP[i + 1] + count;

        }




        System.out.println(DP[0]);


    }

    static boolean isPerfectSquart(long x) {
        long sX = (long) Math.sqrt(x);

        if (((sX - 1) * (sX - 1) == x) || (sX * sX == x) || ((sX + 1) * (sX + 1) == x)) {
            return true;
        }

        return false;

    }
}
