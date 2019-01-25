package com.himanshu.practice.y2018.Aug.aug29;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 29/08/18.
 * Statement: https://codeforces.com/contest/678/problem/C
 * Algo: Inclusion, exclusion
 * Submission: https://codeforces.com/contest/678/my
 */
public class MilaAndChocolate {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");


        long n = Integer.parseInt(str[0]);
        long a = Integer.parseInt(str[1]);
        long b = Integer.parseInt(str[2]);
        long p = Integer.parseInt(str[3]);
        long q = Integer.parseInt(str[4]);

        long result = (p * (n / a)) + (q * (n / b)) - (Math.min(p, q) * (n / lcm(a, b)));
        System.out.println(result);


    }

    static long lcm(long a, long b) {
        long gcd = gcd(a, b);
        return (a / gcd) * (b);
    }

    //assume a>=b
    static long gcd(long a, long b) {
        if (b > a) {
            return gcd(b, a);
        }
        if (b < 1) {
            return a;
        }
        if (b == 1) {
            return b;
        }
        return gcd(a % b, b);
    }

}
