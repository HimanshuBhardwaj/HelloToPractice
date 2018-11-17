package com.himanshu.practice.nov.nov14.codeforces520;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by himanshubhardwaj on 14/11/18.
 */
public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());


        Set<Integer> sets = primeFactors(n);
        //System.out.println(sets);

        long ans = 1;

        int count = 0;
        int buffer = 0;

        for (Integer x : sets) {
            ans *= x;
            int localC = 0;
            int tempn = n;

            while (tempn % x == 0) {
                tempn = tempn / x;
                localC++;
            }

            if (count != 0) {
                if (count != localC) {
                    buffer = 1;
                }
            }
            count = Math.max(count, localC);
        }

        int ans1 = (count != 0) ? (int) Math.ceil(Math.log(count) / Math.log(2)) : 0;

        System.out.println(ans + " " + (ans1 + buffer));


    }


    public static Set<Integer> primeFactors(long number) {
        Set<Integer> primefactors = new HashSet<>();
        long copyOfInput = number;

        for (int i = 2; i <= copyOfInput; i++) {
            if (copyOfInput % i == 0) {
                primefactors.add(i); // prime factor
                copyOfInput /= i;
                i--;
            }
        }
        return primefactors;
    }

}
