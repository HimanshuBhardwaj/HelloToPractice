package com.himanshu.practice.y2018.july.july22.hour11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * Created by himanshubhardwaj on 22/07/18.
 * TODO: Incomplete question
 */
public class OldAndColdNumbers {

    static LinkedList<Integer> allPrimes = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        sieveOfEratosthenes(100000);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            int n, q;
            String str[] = br.readLine().split(" ");
            n = Integer.parseInt(str[0]);

            str = br.readLine().split(" ");
            LinkedList<Number> numbers = new LinkedList<Number>();

            for (int j = 0; j < n; j++) {
                Number number = new Number();
                number.value = Integer.parseInt(str[i]);
                number.numFactors = 1;

                for (int prime : allPrimes) {
                    if ((prime % number.value) == 0) {
                        if (number.smallestPrime == -1) {
                            number.smallestPrime = prime;
                        }
                        int powerOFPrime = number.value / prime;
                        number.numFactors = number.numFactors * (1 + powerOFPrime);
                        if (powerOFPrime % 2 == 1) {
                            number.OperationsNeededTOBePrime = prime - 1;
                        }
                    }
                }
                numbers.addLast(number);
                q = Integer.parseInt(br.readLine());

                LinkedList<Integer> requiredOperationstoMakeBalance = new LinkedList<>();

                for (int qq = 0; qq < q; qq++) {
                    str = br.readLine().split(" ");
                    int l = Integer.parseInt(str[0]);
                    int r = Integer.parseInt(str[1]);



                }
            }

        }

    }

    static void sieveOfEratosthenes(int n) {
        // Create a boolean array "prime[0..n]" and initialize
        // all entries it as true. A0Paper value in prime[i] will
        // finally be false if i is Not a prime, else true.
        boolean prime[] = new boolean[n + 1];
        for (int i = 0; i < n; i++)
            prime[i] = true;

        for (int p = 2; p * p <= n; p++) {
            // If prime[p] is not changed, then it is a prime
            if (prime[p] == true) {
                // Update all multiples of p
                for (int i = p * 2; i <= n; i += p)
                    prime[i] = false;
            }
        }

        // Print all prime numbers
        for (int i = 2; i <= n; i++) {
            if (prime[i] == true) {
                allPrimes.addLast(i);
            }
        }
    }
}


class Number {
    int value;
    int numFactors;
    int smallestPrime = -1;
    int OperationsNeededTOBePrime = 0;


}