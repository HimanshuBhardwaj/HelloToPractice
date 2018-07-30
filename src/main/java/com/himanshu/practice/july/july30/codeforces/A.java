package com.himanshu.practice.july.july30.codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 30/07/18.
 */
public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long firstSum = 0;
        long secondSum = 0;
        String str[] = br.readLine().split(" ");

        for (int i = 0; i < str.length; i++) {
            firstSum += Integer.parseInt(str[i]);
        }

        str = br.readLine().split(" ");

        for (int i = 0; i < str.length; i++) {
            secondSum += Integer.parseInt(str[i]);
        }

        if(secondSum<=firstSum) {
            System.out.print("Yes");
        } else {
            System.out.println("No");
        }
    }
}