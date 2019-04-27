package com.himanshu.practice.y2019.April.april27;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

/**
 * Created by himanshubhardwaj on 26/04/19.
 */
public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine());

        HashSet<Long> reachableNumbers = new HashSet<>();
        reachableNumbers.add(n);

        while (true) {
            long result = computef(n);
            if (reachableNumbers.contains(result)) {
                break;
            } else {
                reachableNumbers.add(result);
                n = result;
            }
        }

        System.out.print(reachableNumbers.size());


    }

    public static long computef(long x) {
        long val = x + 1;

        while (val % 10 == 0) {
            val = val / 10;
        }
        return val;
    }
}