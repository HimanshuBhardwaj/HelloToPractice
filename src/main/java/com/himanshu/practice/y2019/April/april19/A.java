package com.himanshu.practice.y2019.April.april19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 18/04/19.
 */
public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        char str[] = br.readLine().toCharArray();

        int min = Integer.MAX_VALUE;


        for (int i = 0; (i + 4) <= n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = i; j <= (i + 3); j++) {
                sb.append(str[j]);
            }
            min = Math.min(getNumer(sb.toString()), min);
        }


        System.out.print(min);


    }

    private static int getNumer(String s) {
        String str = "ACTG";
        int min = 0;

        for (int i = 0; i < s.length(); i++) {
            int localMin =Math.min(Math.abs(str.charAt(i) - s.charAt(i)), 26 - Math.abs(str.charAt(i) - s.charAt(i)));
            min += localMin;
        }
        return min;
    }
}
