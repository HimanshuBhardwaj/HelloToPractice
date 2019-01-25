package com.himanshu.practice.y2018.Aug.aug17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by himanshubhardwaj on 16/08/18.
 * Statement: https://codeforces.com/contest/616/problem/D
 * Algo:: Queue
 * This is best example of case where hashingmap works over treemap
 * Submission:https://codeforces.com/contest/616/submission/41654511
 */
public class LongestKGoodSegment {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");

        int n = Integer.parseInt(str[0]);
        int k = Integer.parseInt(str[1]);
        str = br.readLine().split(" ");
        int arr[] = new int[str.length];

        for (int i = 0; i < str.length; i++) {
            arr[i] = Integer.parseInt(str[i]);
        }

        Queue<Integer> queue = new LinkedList<>();
        Map<Integer, Integer> hasMap = new HashMap<>();
        int previousSize = 0;
        int previousStart = 0;
        int previousEnd = 0;

        for (int i = 0; i < arr.length; i++) {
            if (hasMap.containsKey(arr[i])) {
                hasMap.put(arr[i], hasMap.get(arr[i]) + 1);
            } else {
                hasMap.put(arr[i], 1);
            }
            queue.add(arr[i]);

            while (hasMap.size() > k) {
                int top = queue.poll();
                int freq = hasMap.get(top);

                if (freq == 1) {
                    hasMap.remove(top);
                } else {
                    hasMap.put(top, freq - 1);
                }
            }

            if (queue.size() > previousSize) {
                previousSize = queue.size();
                previousEnd = i;
                previousStart = i - queue.size() + 1;
            }
        }

        System.out.print((previousStart + 1) + " " + (1 + previousEnd));


    }
}


