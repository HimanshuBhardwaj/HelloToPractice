package com.himanshu.practice.y2018.nov.nov6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Created by himanshubhardwaj on 06/11/18.
* statement:https://codeforces.com/contest/903/problem/C
 * Algo: Sorting, LIS
 * Submission: https://codeforces.com/contest/903/submission/45375653
 */
public class BoxesPacking {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int a[] = new int[n];
        int sN[] = new int[n];
        boolean reserved[] = new boolean[n];

        String str[] = br.readLine().split(" ");

        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(str[i]);
        }

        Arrays.sort(a);
        int currentStack = 0;
        for (int i = 0; i < n; i++) {
            int foundIndex = -1;
            for (int j = i - 1; j >= 0; j--) {
                if ((!reserved[j]) && (a[j] < a[i])) {
                    foundIndex = j;
                    break;
                }
            }
            if (foundIndex != -1) {
                sN[i] = sN[foundIndex];
                reserved[foundIndex] = true;
            } else {
                sN[i] = ++currentStack;
            }
        }

        System.out.print(currentStack);


    }
}
