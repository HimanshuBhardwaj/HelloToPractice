package com.himanshu.practice.nov.nov5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;

/**
 * Created by himanshubhardwaj on 06/11/18.
 * Link: https://codeforces.com/contest/825/problem/C
 * Algo: Binary raise
 * Submisstion: https://codeforces.com/contest/825/submission/45338342
 */
public class MultiJudgeSolving {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str[] = br.readLine().split(" ");

        int n = Integer.parseInt(str[0]);
        int k = Integer.parseInt(str[1]);


        str = br.readLine().split(" ");

        TreeSet<Integer> tSet = new TreeSet<>();


        for (int i = 0; i < str.length; i++) {
            tSet.add(Integer.parseInt(str[i]));
        }

        int count = 0;


        while (!tSet.isEmpty()) {
            if ((k * 2) >= (tSet.first())) {
                int first = tSet.first();
                tSet.remove(first);
                k = Math.max(k, first);
            } else {
                k = k * 2;
                count++;
            }
        }

        System.out.println(count);

    }
}
