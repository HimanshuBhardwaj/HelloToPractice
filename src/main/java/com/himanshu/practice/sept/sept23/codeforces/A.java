package com.himanshu.practice.sept.sept23.codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 23/09/18.
 */
public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String str[] = br.readLine().split(" ");

        for (int i = 0; i < str.length; i++) {
            if (Integer.parseInt(str[i]) == 1) {
                System.out.print("HARD");
                return;
            }
        }

        System.out.print("EASY");
    }
}
