package com.himanshu.practice.y2019.june.june7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Created by himanshubhardwaj on 07/06/19.
 * Statement: https://codeforces.com/contest/1174/problem/C
 * Algo: Sieve
 * Submission: https://codeforces.com/contest/1174/problem/C
 */
public class EhabAndASpecialColoringProblem {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int arr[] = new int[n + 1];

        int num = 1;

        for (int i = 2; i <= n; i++) {
            if (arr[i] == 0) {
                int k = 1;
                while (k * i <= n) {
                    arr[k * i] = num;
                    k++;
                }
                num++;
            }
        }

        PrintWriter pw = new PrintWriter(System.out);
        for (int i = 2; i <= n; i++) {
            pw.append(arr[i] + " ");
        }
        pw.flush();
    }
}
