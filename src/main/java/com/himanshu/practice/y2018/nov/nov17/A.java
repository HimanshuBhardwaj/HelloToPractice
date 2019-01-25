package com.himanshu.practice.y2018.nov.nov17;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by himanshubhardwaj on 16/11/18.
 */
public class A {
    public static void main(String[] args) throws IOException {
        A a = new A();
        System.out.println(a.solve("bcacacab"));

    }


    public int solve(String A) {
        HashMap<Integer, Long> prefixHashMap = new HashMap<>();
        char[] cA = A.toCharArray();
        long prime = 1000000007;
        long[] commulativeHash = new long[cA.length];

        for (int i = 0; i < cA.length; i++) {
            if (i == 0) {
                commulativeHash[i] = (long) (cA[i] - 'a');
            } else {
                commulativeHash[i] = ((commulativeHash[i - 1] * 37) + (cA[i] - 'a'))%prime;
            }
            prefixHashMap.put(i + 1, commulativeHash[i]);
        }


        long product = 1;
        for (int start = 0; start < A.length(); start++) {
            long tp = 0;
            for (int len = 1; (len <= A.length()) && ((start + len) <= cA.length); len++) {
                int end = start + len - 1;
                tp = ((tp * 37) + (cA[start + len - 1] - 'a')) % prime;

                if (prefixHashMap.get(len) == tp) {
                    if (isEqual(A, len, start, end)) {
                        product = (product * len) % prime;
                    }
                }
            }
        }
        return (int) product;
    }

    private boolean isEqual(String a, int len, int start, int end) {
        if (len != (end - start + 1)) {
            return false;
        }
        for (int i = 0; i < len; i++) {
            if (a.charAt(i) != a.charAt(start + i)) {
                return false;
            }
        }
        return true;
    }
}
