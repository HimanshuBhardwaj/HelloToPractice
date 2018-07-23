package com.himanshu.practice.july.july10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Himanshu Bhardwaj on 10/07/18.
 * Algo: DP
 * Submission: https://codeforces.com/contest/855/submission/40160923
 */
public class MarvoloGauntSRing {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");

        int n = (int) Long.parseLong(s[0]);
        long p = Long.parseLong(s[1]);
        long q = Long.parseLong(s[2]);
        long r = Long.parseLong(s[3]);
        long a[] = new long[n];
        long R[] = new long[n];
        long Q[] = new long[n];
        long P[] = new long[n];

        s = br.readLine().split(" ");
        //System.out.prlongln("p=" + p + "\t" + "q=" + q + "\t" + "r=" + r);

        for (int i = 0; i < n; i++) {
            a[i] = Long.parseLong(s[i]);
            R[i] = r * a[i];
            Q[i] = q * a[i];
            P[i] = p * a[i];
            //System.out.prlongln(P[i] + "\t" + Q[i] + "\t" + R[i]);
        }
        //System.out.prlongln();
        //System.out.prlongln();

        for (int i = n - 1; i >= 0; i--) {
            if (i <= (n - 2)) {
                R[i] = Math.max(R[i], R[i + 1]);
            }
            Q[i] = Q[i] + R[i];
            if (i <= (n - 2)) {
                Q[i] = Math.max(Q[i], Q[i + 1]);
            }
            P[i] = P[i] + Q[i];
            if (i <= (n - 2)) {
                P[i] = Math.max(P[i], P[i + 1]);
            }
        }

        System.out.print(P[0]);
//        System.out.println();
//
//        for (int i = 0; i < n; i++) {
//            System.out.print(R[i] + "\t");
//        }
//        System.out.println();
//        for (int i = 0; i < n; i++) {
//            System.out.print(Q[i] + "\t");
//        }
//        System.out.println();
//        for (int i = 0; i < n; i++) {
//            System.out.print(P[i] + "\t");
//        }
//        System.out.println();
    }
}
