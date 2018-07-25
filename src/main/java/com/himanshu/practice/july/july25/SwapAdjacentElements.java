package com.himanshu.practice.july.july25;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Created by himanshubhardwaj on 25/07/18.
 * Statement: https://codeforces.com/contest/920/problem/C
 * Algo: Sorting
 * Submission: https://codeforces.com/contest/920/submission/40746748
 */
public class SwapAdjacentElements {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String str[] = br.readLine().split(" ");

        int arr[] = new int[str.length];
        for (int i = 0; i < str.length; i++) {
            arr[i] = Integer.parseInt(str[i]);
        }

        str = br.readLine().split("");

        int startPos = -1;
        for (int i = 0; i < str.length; i++) {
            if (Integer.parseInt(str[i]) == 1) {
                if (startPos == -1) {
                    startPos = i;
                }
            } else {
                if (startPos != -1) {
                    Arrays.sort(arr, startPos, i + 1);
                    startPos = -1;
                }
            }
        }

        if (startPos != -1) {
            Arrays.sort(arr, startPos, arr.length);
        }

        int cloneArr[] = new int[arr.length];


        for (int i = 0; i < arr.length; i++) {
            cloneArr[i] = arr[i];
        }
        Arrays.sort(cloneArr);

        if (Arrays.equals(cloneArr, arr)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
