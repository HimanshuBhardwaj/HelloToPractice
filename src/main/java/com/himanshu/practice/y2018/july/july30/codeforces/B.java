package com.himanshu.practice.y2018.july.july30.codeforces;//package com.himanshu.practice.y2018.july.july30.codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;

/**
 * Created by himanshubhardwaj on 30/07/18.
 * TODO: Getting wrong answer. Think on why I'm getting wrong answer
 */
public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int x = Integer.parseInt(str[1]);

        TreeSet<Integer> orignalIntegers = new TreeSet<Integer>();
        TreeSet<Integer> andedIntegers = new TreeSet<Integer>();

        str = br.readLine().split(" ");
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            int currentInt = Integer.parseInt(str[i]);
            int andedInt = currentInt & x;

            if (orignalIntegers.contains(currentInt)) {
                min = Math.min(min, 0);
            } else if (andedIntegers.contains(currentInt)) {
                min = Math.min(min, 1);
            } else if (orignalIntegers.contains(andedInt)) {
                min = Math.min(min, 1);
            } else if (andedIntegers.contains(andedInt)) {
                min = Math.min(min, 2);
            }
            orignalIntegers.add(currentInt);
            andedIntegers.add(andedInt);
        }

        if (min == Integer.MAX_VALUE) {
            System.out.print("-1");
        } else {
            System.out.print(min);
        }


    }
}
