package com.himanshu.practice.y2019.june.june1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Created by himanshubhardwaj on 03/06/19.
 * Problem Statement: https://codeforces.com/problemset/problem/776/C
 */
public class MollyChemicals {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");

        int n = Integer.parseInt(str[0]);
        long k = Long.parseLong(str[1]);

        str = br.readLine().split(" ");
        long[] arr = new long[str.length];

        for (int i = 0; i < str.length; i++) {
            arr[i] = Long.parseLong(str[i]);
        }


        TreeMap<Long, TreeSet<Integer>> treeSetTreeMap = new TreeMap<>();

        long prefixSum = 0l;

        for (int i = 0; i < str.length; i++) {
            prefixSum += arr[i];
            if (!treeSetTreeMap.containsKey(prefixSum)) {
                treeSetTreeMap.put(prefixSum, new TreeSet<>());
            }
            treeSetTreeMap.get(prefixSum).add(i);
        }

        int currentSum = 0;


        for (long powK = 1; powK <= prefixSum; powK = powK * k) {
            long sSum = 0;
            for (int i = 0; i < str.length; i++) {
                sSum += arr[i];
                //TreeSet<Integer>

            }

        }


    }
}
