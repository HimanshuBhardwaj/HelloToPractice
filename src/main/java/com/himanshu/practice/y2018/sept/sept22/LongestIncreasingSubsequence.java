package com.himanshu.practice.y2018.sept.sept22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 22/09/18.
 * Find longest increasing subsequence
 */
public class LongestIncreasingSubsequence {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


    }




     static int findLIS(int[] arr) {
        int LIS[] = new int[arr.length];
        int maxSeqLengthComm = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            LIS[i] = 1;
            maxSeqLengthComm = Math.max(LIS[i], maxSeqLengthComm);
            for (int j = i - 1; j >= 0; j--) {
                if (arr[i] > arr[j]) {
                    LIS[i] = Math.max(LIS[i], 1 + LIS[j]);
                    maxSeqLengthComm = Math.max(LIS[i], maxSeqLengthComm);
                }
            }
        }
        return maxSeqLengthComm;
    }
}
