package com.himanshu.practice.y2019.April.april27;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeMap;

/**
 * Created by himanshubhardwaj on 26/04/19.
 */
public class E {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] str = br.readLine().split(" ");
        long[] a = new long[str.length];
        long[] b = new long[str.length];
        long[] c = new long[str.length];

        for (int i = 0; i < n; i++) {
            a[i] = Long.parseLong(str[i]);
        }


        TreeMap<Long, Integer> treeMap = new TreeMap<>();
        str = br.readLine().split(" ");

        for (int i = 0; i < str.length; i++) {
            long bb = Integer.parseInt(str[i]) % n;

            if (!treeMap.containsKey(bb)) {
                treeMap.put(bb, 0);
            }
            treeMap.put(bb, treeMap.get(bb) + 1);
        }

        for (int i = 0; i < n; i++) {
            long x = n - a[i];
            Long ceilKey = treeMap.ceilingKey(x);

            if (ceilKey == null) {
                ceilKey = treeMap.firstKey();
                int bs = treeMap.get(ceilKey);
                c[i] = (a[i] + ceilKey) % n;
                bs--;
                if (bs == 0) {
                    treeMap.remove(ceilKey);
                } else {
                    treeMap.put(ceilKey, bs);
                }


            } else {
                int bs = treeMap.get(ceilKey);
                c[i] = (a[i] + ceilKey) % n;
                bs--;
                if (bs == 0) {
                    treeMap.remove(ceilKey);
                } else {
                    treeMap.put(ceilKey, bs);
                }
            }
        }


        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(c[i] + " ");
        }

        System.out.print(sb.toString());

    }
}