package com.himanshu.practice.y2019.june.june9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.TreeMap;

/**
 * Created by himanshubhardwaj on 09/06/19.
 * https://codeforces.com/contest/1176/problem/D
 * TODO: Complete it
 */
public class D {
    static TreeMap<Integer, Integer> primeToCount = new TreeMap<>();
    static TreeMap<Integer, Integer> countToPrime = new TreeMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sieveOfEratosthenes();
        int n = Integer.parseInt(br.readLine());
        String[] str = br.readLine().split(" ");
        ArrayList<Integer> primes = new ArrayList<>();
        ArrayList<Integer> composites = new ArrayList<>();

        for (int i = 0; i < str.length; i++) {
            int num = Integer.parseInt(str[i]);
            if (primeToCount.containsKey(num)) {
                primes.add(num);
            } else {
                composites.add(num);
            }
        }

        HashSet<Integer> numberNotToConsider = new HashSet<>();
        HashSet<Integer> orignalList = new HashSet<>();
        Collections.sort(composites, Collections.reverseOrder());
        //System.out.println(composites);

        for (int composite : composites) {
            if (!numberNotToConsider.contains(composite)) {
                numberNotToConsider.add(largestDivisor(composite));
                //System.out.println(composite+"\t"+largestDivisor(composite));
                orignalList.add(composite);
            }
        }

        Collections.sort(primes);
        //System.out.println(primes);


        for (int prime : primes) {
            if (!numberNotToConsider.contains(prime)) {
                numberNotToConsider.add(countToPrime.get(prime));
                orignalList.add(prime);
            }
        }





        PrintWriter pw = new PrintWriter(System.out);

        for (int num : orignalList) {
            pw.append(num + " ");
        }
        pw.flush();
    }

    public static int largestDivisor(int n) {
        if (n % 2 == 0) {
            return n / 2;
        }
        final int sqrtn = (int) Math.sqrt(n);
        for (int i = 3; i <= sqrtn; i += 2) {
            if (n % i == 0) {
                return n / i;
            }
        }
        return 1;
    }

    static void sieveOfEratosthenes() {
        int n = 2750131;
        // Create a boolean array "prime[0..n]" and initialize
        // all entries it as true. A value in prime[i] will
        // finally be false if i is Not a prime, else true.
        boolean prime[] = new boolean[n + 1];
        for (int i = 0; i < n; i++)
            prime[i] = true;

        for (int p = 2; p * p <= n; p++) {
            // If prime[p] is not changed, then it is a prime
            if (prime[p] == true) {
                // Update all multiples of p
                for (int i = p * p; i <= n; i += p)
                    prime[i] = false;
            }
        }

        // Print all prime numbers
        int count = 1;
        for (int i = 2; i <= n; i++) {
            if (prime[i] == true) {
                primeToCount.put(i, count);
                countToPrime.put(count, i);
                count++;
            }
        }
    }
}
