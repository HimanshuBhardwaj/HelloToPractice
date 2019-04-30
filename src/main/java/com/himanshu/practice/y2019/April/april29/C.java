package com.himanshu.practice.y2019.April.april29;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.TreeSet;

/**
 * Created by himanshubhardwaj on 29/04/19.
 */

//give priority to 2
public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        SieveOfEratosthenes sieveOfEratosthenes = new SieveOfEratosthenes(400002);
        int n = Integer.parseInt(br.readLine());
        int prefixSum = 0;
        String str[] = br.readLine().split(" ");
        int count[] = new int[3];
        int array[] = new int[n];
        int prmtC = 0;

        for (int i = 0; i < n; i++) {
            count[Integer.parseInt(str[i])]++;
        }

        for (int i = 0; i < n; i++) {
            if (sieveOfEratosthenes.primeList.contains(prefixSum + 2) && (count[2] != 0)) {
                array[i] = 2;
                prefixSum += 2;
                count[2]--;
                prmtC++;
            } else if (sieveOfEratosthenes.primeList.contains(prefixSum + 1) && (count[1] != 0)) {
                array[i] = 1;
                prefixSum += 1;
                count[1]--;
                prmtC++;
            } else if (count[2] != 0) {
                array[i] = 2;
                prefixSum += 2;
                count[2]--;
            } else {
                array[i] = 1;
                prefixSum += 1;
                count[1]--;
            }
        }

        PrintWriter pw = new PrintWriter(System.out);
        for (int i = 0; i < n; i++) {
            pw.append(array[i] + " ");
        }
        //pw.append("\n");
        //pw.append(prmtC + " ");
        pw.flush();
    }
}


class SieveOfEratosthenes {
    TreeSet<Integer> primeList = new TreeSet<Integer>();

    //n=400002
    public SieveOfEratosthenes(int n) {
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
        for (int i = 2; i <= n; i++) {
            if (prime[i] == true) {
                primeList.add(i);
            }
        }
    }
}
