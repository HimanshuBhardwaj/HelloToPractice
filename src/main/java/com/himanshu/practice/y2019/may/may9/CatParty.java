package com.himanshu.practice.y2019.may.may9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * Created by himanshubhardwaj on 09/05/19.
 * Statement: https://codeforces.com/contest/1163/problem/B2
 * Algo: Ad-Hoc
 * Submission: https://codeforces.com/contest/1163/submission/54002465
 */
public class CatParty {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        String[] str = br.readLine().split(" ");
        int[] arr = new int[n];
        HashMap<Integer, Integer> numberFrequency = new HashMap<>();
        TreeMap<Integer, Integer> frequencyCount = new TreeMap<>();
        int last = 1;


        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(str[i]);

            if (numberFrequency.containsKey(arr[i])) {
                Integer frequency = numberFrequency.get(arr[i]);
                Integer fc = frequencyCount.get(frequency);
                numberFrequency.put(arr[i], 1 + ((frequency != null) ? frequency : 0));

                if (fc == 1) {
                    frequencyCount.remove(frequency);
                } else {
                    frequencyCount.put(frequency, fc - 1);
                }

                Integer fc_1_plusC = frequencyCount.get(frequency + 1);
                if (fc_1_plusC == null) {
                    frequencyCount.put(frequency + 1, 1);
                } else {
                    frequencyCount.put(frequency + 1, 1 + fc_1_plusC);
                }

            } else {
                numberFrequency.put(arr[i], 1);
                Integer frequency_1 = frequencyCount.get(1);
                if (frequency_1 == null) {
                    frequencyCount.put(1, 1);
                } else {
                    frequencyCount.put(1, 1 + frequency_1);
                }
            }


            if ((frequencyCount.size() == 2 && getOtherCondition(frequencyCount)) ||
                    (frequencyCount.size() == 1 && ((frequencyCount.firstEntry().getValue() == 1) || (frequencyCount.firstEntry().getKey() == 1)))) {
                last = i + 1;
            }
        }
        System.out.print(last);

    }

    private static boolean getOtherCondition(TreeMap<Integer, Integer> frequencyCount) {
        if (!(frequencyCount.firstEntry().getValue() == 1 || frequencyCount.lastEntry().getValue() == 1)) {
            return false;
        }

        if ((frequencyCount.firstEntry().getKey() == 1)) {
            if (frequencyCount.firstEntry().getValue() == 1) {
                return true;
            }

            if (frequencyCount.lastEntry().getKey() != 2) {
                return false;
            }
            if (frequencyCount.lastEntry().getValue() != 1) {
                return false;
            }
            return true;
        }
        if (frequencyCount.firstEntry().getKey() != 1) {
            if ((frequencyCount.lastEntry().getKey() - frequencyCount.firstEntry().getKey() == 1) && (frequencyCount.lastEntry().getValue() == 1)) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }
}
