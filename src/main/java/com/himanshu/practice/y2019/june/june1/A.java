package com.himanshu.practice.y2019.june.june1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Created by himanshubhardwaj on 01/06/19.
 */
public class A {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        long a = Integer.parseInt(str[0]);
        long b = Integer.parseInt(str[1]);
        long c = Integer.parseInt(str[2]);

        long result = (2 * c) + (Math.min(a, b) * 2) + ((a == b) ? 0 : 1);
        System.out.print(result);
    }
}
