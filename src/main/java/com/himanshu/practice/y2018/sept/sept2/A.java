package com.himanshu.practice.y2018.sept.sept2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Created by himanshubhardwaj on 02/09/18.
 * Statement: https://codeforces.com/contest/1037/problem/A
 * Algo: Math
 Submission: https://codeforces.com/contest/1037/submission/42375424
 */
public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pr = new PrintWriter(System.out);

        int n = Integer.parseInt(br.readLine());
        int count = 0;
        int sub = 1;

        while (n > 0) {
            count++;
            n = n - sub;
            sub = sub * 2;
        }


        System.out.println(count);


    }
}
