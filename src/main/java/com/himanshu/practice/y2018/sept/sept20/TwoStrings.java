package com.himanshu.practice.y2018.sept.sept20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;

/**
 * Created by himanshubhardwaj on 20/09/18.
 * Statement: https://codeforces.com/contest/762/problem/C
 * Algo:
 * Submission:
 * TODO: Complete it
 */
public class TwoStrings {
    static int computePrefix[];
    static TreeMap<Integer, Integer> suffix = new TreeMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] a = br.readLine().toCharArray();
        char[] b = br.readLine().toCharArray();
        computePrefix = new int[a.length];


        preProcess(a, b);
        System.out.println(suffix);

        if (computePrefix[computePrefix.length - 1] == Integer.MAX_VALUE) {
            System.out.println("-");
            return;
        }


        int dis = Integer.MAX_VALUE;
        int start;

        for (int i = 0; i < a.length; i++) {
            if (suffix.higherKey(computePrefix[i]) != null) {
                if ((suffix.get(suffix.higherKey(computePrefix[i])) - i - 1) < dis) {
                    dis = suffix.get(suffix.higherKey(computePrefix[i])) - i - 1;
                    start = i;
                }
            }
        }

        System.out.println(dis);

    }

    static void preProcess(char[] a, char[] b) {
        int pos = 0;
        int i;
        for (i = 0; (i < a.length) && pos < b.length; i++) {
            while ((pos < b.length) && (a[i] != b[pos])) {
                pos++;
            }

            if (pos >= b.length) {
                break;
            }

            computePrefix[i] = pos;
            pos++;
        }

        while (i < a.length) {
            computePrefix[i++] = Integer.MAX_VALUE;
        }

        pos = b.length - 1;
        for (i = a.length - 1; (i >= 0) && pos >= 0; i--) {
            while ((pos >= 0) && (a[i] != b[pos])) {
                pos--;
            }

            if (pos < 0) {
                break;
            }
            suffix.put(pos, i);
            pos--;
        }

        while (i >= 0) {
            suffix.put(i, Integer.MAX_VALUE);
        }
    }
}
