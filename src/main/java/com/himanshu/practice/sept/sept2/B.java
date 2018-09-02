package com.himanshu.practice.sept.sept2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * Created by himanshubhardwaj on 02/09/18.
 * Statement: https://codeforces.com/contest/1037/problem/B
 * Algo: Ad-Hoc
 * Submission: https://codeforces.com/contest/1037/submission/42380763
 */
public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pr = new PrintWriter(System.out);
        String str[] = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        long s = Long.parseLong(str[1]);
        long ar[] = new long[n];


        str = br.readLine().split(" ");

        for (int i = 0; i < n; i++) {
            ar[i] = Long.parseLong(str[i]);
        }
        Arrays.sort(ar);

        long count = 0;
        if (s == ar[ar.length / 2]) {

        } else if (s > ar[ar.length / 2]) {
            for (int i = ar.length / 2; i < ar.length; i++) {
                if (ar[i] >= s) {
                    break;
                } else {
                    count += Math.abs(s - ar[i]);
                }
            }
        } else {
            for (int i = ar.length / 2; i >= 0; i--) {
                if (s > ar[i]) {
                    break;
                } else {
                    count += Math.abs(s - ar[i]);
                }
            }
        }

        System.out.println(count);

    }
}
