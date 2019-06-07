package com.himanshu.practice.y2019.june.june6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * Created by himanshubhardwaj on 06/06/19.
 * Statement: https://codeforces.com/problemset/problem/567/C
 * Also: TreeMAp
 * Submission: https://codeforces.com/contest/567/submission/55195105
 * InPractice: Hash map is almost twice fater than tree map
 */
public class GeometricProgression {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        long n = Long.parseLong(str[0]);
        long k = Long.parseLong(str[1]);

        HashMap<Long, Long> suffixFrequeccy = new HashMap<>();
        HashMap<Long, Long> prefixFrequeccy = new HashMap<>();

        str = br.readLine().split(" ");
        long[] arr = new long[str.length];


        for (int i = 0; i < arr.length; i++) {
            arr[i] = Long.parseLong(str[i]);
            if (!suffixFrequeccy.containsKey(arr[i])) {
                suffixFrequeccy.put(arr[i], 0l);
            }
            if (!prefixFrequeccy.containsKey(arr[i])) {
                prefixFrequeccy.put(arr[i], 0l);
            }
            addFrequency(suffixFrequeccy, arr[i]);
        }
  //      System.out.println(prefixFrequeccy);
//        System.out.println(suffixFrequeccy);

        addFrequency(prefixFrequeccy, arr[0]);
        removeFrequency(suffixFrequeccy, arr[0]);

        long count = 0;

        for (int i = 1; i < n; i++) {
            removeFrequency(suffixFrequeccy, arr[i]);
            if (arr[i] % k == 0) {
                Long p = prefixFrequeccy.get(arr[i] / k);
                Long s = suffixFrequeccy.get(arr[i] * k);

                if (p != null && s != null) {
                    count += (p * s);
                }
            }
            addFrequency(prefixFrequeccy, arr[i]);
        }

        System.out.println(count);


    }

    private static void addFrequency(HashMap<Long, Long> numberMap, long number) {
  //      System.out.print(numberMap + "\t\t" + number);
        numberMap.put(number, numberMap.get(number) + 1);
//        System.out.println("--->" + numberMap);
    }

    private static void removeFrequency(HashMap<Long, Long> numberMap, long number) {
        numberMap.put(number, numberMap.get(number) - 1);
    }
}
