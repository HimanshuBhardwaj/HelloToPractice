package com.himanshu.practice.y2019.july.july5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;

/**
 * Created by himanshubhardwaj on 03/07/19.
 * Statement: https://codeforces.com/contest/678/problem/D
 * Submission: https://codeforces.com/contest/678/submission/56468056
 * Algo: Number Theory
 *
 */
public class IteratedLinearFunction {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");

        long A = Long.parseLong(str[0]);
        long B = Long.parseLong(str[1]);
        long n = Long.parseLong(str[2]);
        long x = Long.parseLong(str[3]);
        long prime = 1000000007l;

        if (A != 1) {
            long Ai = power(A, n, prime);

            long tempR1 = (Ai * x) % prime;
            long tempR2 = (((B * ((Ai == 0) ? (prime - 1) : (Ai - 1))) % prime) * (moduloInversePrime(A - 1, prime))) % prime;
            System.out.println((tempR1 + tempR2) % prime);
        } else {
            System.out.println(((((n % prime) * B) + x) % prime) % prime);
        }
    }


    private static long moduloInversePrime(long n, long p) {
        return power(n, p - 2, p);
    }

    private static long power(long n, long pow, long prime) {
        if (pow == 0) {
            return 1;
        }

        if (pow == 1) {
            return n % prime;
        }

        long result = power(n, pow / 2, prime);
        if (pow % 2 == 0) {
            return (result * result) % prime;
        } else {
            return (((result * result) % prime) * n) % prime;
        }
    }

}
