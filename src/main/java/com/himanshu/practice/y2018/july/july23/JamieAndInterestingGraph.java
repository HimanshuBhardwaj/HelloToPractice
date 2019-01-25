package com.himanshu.practice.y2018.july.july23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by himanshubhardwaj on 23/07/18.
 * Problem Statement: https://codeforces.com/contest/916/problem/C
 * Also: Shortest Path, MST
 * Submission: https://codeforces.com/contest/916/submission/40660731
 *
 */
public class JamieAndInterestingGraph {

    static LinkedList<Integer> primeNumbers = new LinkedList<>();
    static ArrayList<Integer> clonesPrimeNumbers;

    public static void main(String[] args) throws IOException {
        listAllPrimesLEssThan(100238);


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);
        clonesPrimeNumbers = new ArrayList<>(primeNumbers);


        System.out.println(clonesPrimeNumbers.get(clonesPrimeNumbers.size() - 2) + " "
                + clonesPrimeNumbers.get(clonesPrimeNumbers.size() - 2));
        int sum = clonesPrimeNumbers.get(clonesPrimeNumbers.size() - 2);

        for (int i = 1; i < n; i++) {
            if (i < (n - 1)) {
                System.out.println(i + " " + (i + 1) + " " + 1);
                sum--;
            } else {
                System.out.println(i + " " + (i + 1) + " " + sum);
            }
        }

        int remainingEdges = m - (n - 1);
        int newBranch = clonesPrimeNumbers.get(clonesPrimeNumbers.size() - 1);

        for (int i = 1; (i < n) && (remainingEdges > 0); i++) {
            for (int j = i + 2; (j <= n) && (remainingEdges > 0); j++) {
                System.out.println(i + " " + j + " " + newBranch);
                remainingEdges--;
            }
        }
    }


    static void listAllPrimesLEssThan(int n) {
        boolean[] isPrime = new boolean[n + 1];
        isPrime[0] = true;
        isPrime[1] = true;

        for (int i = 2; i <= n; i++) {
            if (!isPrime[i]) {
                primeNumbers.addLast(i);
                for (int k = 2; (k * i) <= n; k++) {
                    isPrime[k * i] = true;
                }
            }
        }

    }
}
