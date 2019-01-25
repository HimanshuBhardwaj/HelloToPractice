package com.himanshu.practice.y2018.sept.sept23.codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 23/09/18.
 */
public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        char[] num = br.readLine().toCharArray();
        int[] arr = new int[n];
        int sum = 0;


        for (int i = 0; i < num.length; i++) {
            arr[i] = num[i] - '0';
            sum += arr[i];
        }

        boolean couldBeSplittedF = false;
        for (int numofSegMent = 2; (numofSegMent <= n) && (!couldBeSplittedF); numofSegMent++) {
            couldBeSplittedF = couldBeSplitted(arr, 0, sum, numofSegMent);
        }

        if (couldBeSplittedF) {
            System.out.print("YES");
        } else {
            System.out.print("NO");
        }
    }

    private static boolean couldBeSplitted(int[] arr, int start, int sum, int numofSegMent) {
        if (start >= arr.length || sum == 0) {
            return true;
        }

        if (sum % numofSegMent != 0) {
            return false;
        }

        int localSum = 0;
        int expectedSum = sum / numofSegMent;
        int pos = start;

        while ((pos < arr.length) && (localSum < expectedSum)) {
            localSum += arr[pos];
            pos++;
        }

        if (localSum != expectedSum) {
            return false;
        }

        while (pos < arr.length && arr[pos] == 0) {
            pos++;
        }

        return couldBeSplitted(arr, pos, sum, numofSegMent);
    }
}
