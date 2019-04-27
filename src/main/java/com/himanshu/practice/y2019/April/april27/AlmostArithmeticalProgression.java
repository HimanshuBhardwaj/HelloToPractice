package com.himanshu.practice.y2019.April.april27;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

/**
 * Created by himanshubhardwaj on 27/04/19.
 * Started: 1:28 -- 1:55 am
 * Expected Time: 40 mins
 * Statement: https://codeforces.com/contest/255/problem/C
 * Algo: Longest Increasing subsequence
 * Submisson: https://codeforces.com/contest/255/submission/53437741
 */
public class AlmostArithmeticalProgression {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        String[] str = br.readLine().split(" ");
        HashMap<Integer, Integer> distinctKeys = new HashMap<>();


        int index = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(str[i]);
            if (!distinctKeys.containsKey(arr[i])) {
                distinctKeys.put(arr[i], index);
                index++;
            }
        }

        TreeSet<Integer>[] adjList = new TreeSet[distinctKeys.size()];
        for (int i = 0; i < adjList.length; i++) {
            adjList[i] = new TreeSet<>();
        }


        for (int i = 0; i < n; i++) {
            adjList[distinctKeys.get(arr[i])].add(i);
        }

        if (distinctKeys.size() == 1) {
            System.out.print(adjList[0].size());
            return;
        }

        int longest = 0;


        for (int i = 0; i < adjList.length; i++) {
            longest = Math.max(longest, adjList[i].size());
            for (int j = i + 1; j < adjList.length; j++) {
                int tempMAx = getMax(adjList[i], adjList[j]);
                if (longest < tempMAx) {
                    longest = tempMAx;
                }
            }
        }

        System.out.print(longest);


    }

    private static int getMax(TreeSet<Integer> integers1, TreeSet<Integer> integers2) {
        Integer min1 = integers1.first();
        Integer min2 = integers2.first();
        int state = (min1 < min2) ? 1 : 2;
        int count = 0;
        int last = -1;

        while (min1 != null && min2 != null) {
            if (state == 1) {
                count++;
                last = min1;
                while (min1 != null && min1 < min2) {
                    min1 = integers1.ceiling(min1 + 1);
                }
                state = 2;
            } else {
                count++;
                last = min2;
                while (min2 != null && min2 < min1) {
                    min2 = integers2.ceiling(min2 + 1);
                }
                state = 1;
            }
        }

        if (min1 == null) {
            if (last < integers2.last()) {
                count++;
            }
        } else {
            if (last < integers1.last()) {
                count++;
            }
        }


        //System.out.println(integers1 + "\t\t" + integers2 + "\t\t" + count);

        return count;
    }

}
