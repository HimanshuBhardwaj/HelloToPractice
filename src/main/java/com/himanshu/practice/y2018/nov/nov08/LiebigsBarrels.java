package com.himanshu.practice.y2018.nov.nov08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by himanshubhardwaj on 08/11/18.
 * 5:06--5:51
 * Statement: https://codeforces.com/contest/985/problem/C
 * Algo: Sorting. This problem was specially designed to recall that sort function of arrays in java uses quic sort. while of collection uses merge sort.
 * Submission: https://codeforces.com/contest/985/submission/45443584
 */
public class LiebigsBarrels {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");

        int n = Integer.parseInt(str[0]);
        int k = Integer.parseInt(str[1]);
        int l = Integer.parseInt(str[2]);
        ArrayList<Integer> arr = new ArrayList<>(n * k);
        str = br.readLine().split(" ");

        for (int i = 0; i < str.length; i++) {
            arr.add(Integer.parseInt(str[i]));
        }

        Collections.sort(arr);

        int start = arr.get(0);
        int pos = 1;

        while (pos < arr.size() && (arr.get(pos) - start) <= l) {
            pos++;
        }


        long sum = 0;
        if (pos >= n) {
            pos--;
            int collected = 0;
            boolean flag = false;
            for (int i = 0; (collected < n) && (i <= pos); ) {
                sum += arr.get(i);
                collected++;

                if (!flag) {
                    int nextExpectedPosition = i + k;
                    int remaining = pos - nextExpectedPosition + 1;

                    if (collected + remaining >= n) {
                        i = nextExpectedPosition;
                    } else {
                        i = pos;
                        flag = true;
                    }
                } else {
                    i--;
                }
            }
        }

        System.out.print(sum);

    }


}
