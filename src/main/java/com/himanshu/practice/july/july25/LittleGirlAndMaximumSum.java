package com.himanshu.practice.july.july25;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Created by himanshubhardwaj on 25/07/18.
 * Statement: https://codeforces.com/contest/276/problem/C
 * Algo: Sorting
 * Submission: https://codeforces.com/contest/276/submission/40744098
 *
 */
public class LittleGirlAndMaximumSum {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str[] = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int q = Integer.parseInt(str[1]);

        int arr[] = new int[n];
        int freq[] = new int[n];

        str = br.readLine().split(" ");
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(str[i]);
        }

        for (int i = 0; i < q; i++) {
            str = br.readLine().split(" ");
            int l = Integer.parseInt(str[0]);
            int r = Integer.parseInt(str[1]);
            l--;
            r--;

            freq[l]++;

            if (r < (n - 1)) {
                freq[r + 1]--;
            }
        }


        for (int i = 1; i < freq.length; i++) {
            freq[i] += freq[i - 1];
        }

        Arrays.sort(freq);
        Arrays.sort(arr);

        long sum = 0;

        for (int i = 0; i < arr.length; i++) {
            sum = sum + ((long) arr[i]) * freq[i];
        }

//        for (int x : freq) {
//            System.out.print(x+" ");
//        }
//        System.out.println();
//
//        for (int x : arr) {
//            System.out.print(x+" ");
//        }
//        System.out.println();
//
        System.out.print(sum);
    }
}
