package com.himanshu.practice.Aug.aug23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 25/08/18.
 */
public class BestIndex {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String str[] = br.readLine().split(" ");

        long arr[] = new long[n];
        long right[] = new long[n];
        long left[] = new long[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(str[i]);
            if (i != 0) {
                left[i] += left[i - 1] + arr[i];
            } else {
                left[i] = arr[i];
            }
        }

        br.close();


        long max = Long.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            int nextPos = getMaximalPos(i, n - 1);
            long tempSum = getSum(i, nextPos, left, arr);
            if (tempSum > max) {
                max = tempSum;
            }
        }

        System.out.println(max);
    }

    private static long getSum(int start, int end, long[] left, long[] right) {
        if (start == 0) {
            return left[end];
        } else {
            return left[end] - left[start - 1];
        }
    }

    private static int getMaximalPos(int start, int end) {
        int jump = 2;

        while ((jump + start) <= end) {
            start = jump + start;
            jump++;
        }
        return start;
    }
}
