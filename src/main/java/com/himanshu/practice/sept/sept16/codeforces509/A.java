package com.himanshu.practice.sept.sept16.codeforces509;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Created by himanshubhardwaj on 16/09/18.
 */
public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int arr[] = new int[n];
        String str[] = br.readLine().split(" ");

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(str[i]);
        }

        Arrays.sort(arr);
        int min = arr[0];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] - min;
        }


        System.out.println((arr[n - 1] - (n - 1)));
    }
}
