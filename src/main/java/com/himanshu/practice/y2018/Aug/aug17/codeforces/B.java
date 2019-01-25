package com.himanshu.practice.y2018.Aug.aug17.codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 17/08/18.
 * Statement: https://codeforces.com/contest/1023/problem/B
 * Algo: Maths
 * Submission: https://codeforces.com/contest/1023/problem/B
 */
public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");

        long n = Long.parseLong(str[0]);
        long k = Long.parseLong(str[1]);

        if (k <= n) {
            if (k < n) {
                n = k;
            }
            if ((n % 2) == 0) {
                System.out.print((n - 2) / 2);

            } else {
                System.out.print((n - 1) / 2);
            }
        } else if (k > n) {
            if ((k / 2) >= n) {
                System.out.println(0);
            } else {
                System.out.print((n - (k / 2)));
            }

        }

    }
}
