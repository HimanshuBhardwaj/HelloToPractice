package com.himanshu.practice.y2018.july.july23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 23/07/18.
 * Statement: https://codeforces.com/contest/743/problem/C
 * Algo: Number Theory
 * Submission: https://codeforces.com/contest/743/submission/40663646
 */
public class VladikAndFractions {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        if(n==1) {
            System.out.println(-1);
            return;
        }
        System.out.print(n + " " + (n + 1) + " " + (n * (n + 1)));
    }
}
