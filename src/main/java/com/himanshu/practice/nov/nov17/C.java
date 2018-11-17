package com.himanshu.practice.nov.nov17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

/**
 * Created by himanshubhardwaj on 16/11/18.
 */
public class C {
    public static void main(String[] args) throws IOException {
        C c = new C();
        int arr[] = new int[4];
        arr[0]=1;
        arr[1]=2;
        arr[2]=4;
        arr[2]=3;

        System.out.println(c.solve(arr));

    }

    public int solve(int[] A) {
        long prieSubseqCount[] = new long[A.length];
        boolean[] isPrime = primeNumbers(1000001);
        long count = 0;

        for (int i = 0; i < A.length; i++) {
            if (isPrime[A[i]]) {
                prieSubseqCount[i] = 1;
                for (int j = i - 1; j >= 0; j--) {
                    prieSubseqCount[i] = (prieSubseqCount[i] + prieSubseqCount[j]) % 1000000007;
                }
                count = (count + prieSubseqCount[i]) % 1000000007;
            }
        }
        return (int) count;
    }


    boolean[] primeNumbers(int X) {
        X++;
        boolean[] isPrime = new boolean[X];
        for (int i = 0; i < isPrime.length; i++) {
            isPrime[i] = true;
        }
        isPrime[0] = false;
        isPrime[1] = false;

        for (int i = 2; i < isPrime.length; i++) {
            int k = 2;
            while (k * i < isPrime.length) {
                isPrime[k * i] = false;
                k++;
            }
        }
        return isPrime;
    }


}
