package com.himanshu.practice.Aug.educationalround;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Created by himanshubhardwaj on 14/08/18.
 * Statement: https://codeforces.com/contest/609/problem/C
 * Algo: Greedy
 * Submission: https://codeforces.com/contest/609/submission/41569390
 */
public class LoadBalancing {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String str[] = br.readLine().split(" ");
        int arr[] = new int[n];
        long total = 0;

        for (int i = 0; i < str.length; i++) {
            arr[i] = Integer.parseInt(str[i]);
            total += arr[i];
        }

        long ceil = (long) Math.ceil(((double) total) / (double) n);
        long floor = (long) Math.floor(((double) total) / (double) n);
        int numWithOneMore = (int) (total - (floor * n));

        Arrays.sort(arr);
        long totalTaken = 0;
        int count = 0;
        int seconds = 0;


        for (int i = (arr.length - 1); i >= 0; i--) {
            if (count < numWithOneMore) {
                if (arr[i] > ceil) {
                    seconds += arr[i] - ceil;
                    totalTaken += arr[i] - ceil;
                    arr[i] = (int) ceil;
                } else {
                    totalTaken -= ceil - arr[i];
                    arr[i] = (int) ceil;
                }
                count++;
            } else {
                if (arr[i] > floor) {
                    seconds += arr[i] - floor;
                    totalTaken += arr[i] - floor;
                    arr[i] = (int) floor;
                } else {
                    totalTaken -= floor - arr[i];
                    arr[i] = (int) floor;
                }
            }
        }
        System.out.println(seconds);
    }
}