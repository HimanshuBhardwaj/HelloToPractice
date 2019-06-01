package com.himanshu.practice.y2018.nov.nov09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by himanshubhardwaj on 09/11/18.
 * Statement: https://codeforces.com/contest/1000/problem/C
 * Algo: Reverse SegmentP construction
 * Submission: https://codeforces.com/contest/1000/submission/45474797
 */
public class CoveredPointsCount {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] str = null;

        TreeMap<Long, Integer> freqMap = new TreeMap<>();


        for (int i = 0; i < n; i++) {
            str = br.readLine().split(" ");
            long s = Long.parseLong(str[0]);
            long d = Long.parseLong(str[1]);

            if (freqMap.containsKey(s)) {
                freqMap.put(s, freqMap.get(s) + 1);
            } else {
                freqMap.put(s, 1);
            }

            if (freqMap.containsKey(d + 1)) {
                freqMap.put(d + 1, freqMap.get(d + 1) - 1);
            } else {
                freqMap.put(d + 1, -1);
            }

        }



        Set<Long> keySet = freqMap.keySet();


        Long prevKey = null;
        for (long keys : keySet) {
            if (prevKey == null) {
                prevKey = keys;
            } else {
                freqMap.put(keys, freqMap.get(keys) + freqMap.get(prevKey));
                prevKey = keys;
            }
        }

        long[] freqCount = new long[1000000];
        prevKey = null;
        for (long keys : keySet) {
            if (prevKey == null) {
                prevKey = keys;
            } else {
                freqCount[freqMap.get(prevKey)] += keys - prevKey;
                prevKey = keys;

            }

        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(Long.toString(freqCount[i]));
            sb.append(" ");
        }

        System.out.print(sb.toString());


    }
}
