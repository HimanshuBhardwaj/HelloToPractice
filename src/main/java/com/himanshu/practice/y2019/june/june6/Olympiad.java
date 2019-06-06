package com.himanshu.practice.y2019.june.june6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by himanshubhardwaj on 06/06/19.
 * Statement: https://codeforces.com/problemset/problem/222/D
 * Also: Two pointers. Also this problem demostrates that Arrays sort is slower than Collection sort
 * Submission: https://codeforces.com/contest/222/submission/55201963
 *
 */
public class Olympiad {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int x = Integer.parseInt(str[1]);

        str = br.readLine().split(" ");
        ArrayList<Integer> a= new ArrayList<>(str.length);


        for (int i = 0; i < str.length; i++) {
            a.add(i,Integer.parseInt(str[i]));
        }


        str = br.readLine().split(" ");
        ArrayList<Integer> b= new ArrayList<>(str.length);

        for (int i = 0; i < str.length; i++) {
            b.add(i,Integer.parseInt(str[i]));

        }

        Collections.sort(a);
        Collections.sort(b);

        int startA = 0;
        int endB = n - 1;
        int count = 0;


        while (startA <= (n - 1) && endB >= 0) {
            // System.out.println(startA+"\t"+endB);
            //System.out.println(startB+"\t"+endA);
            if (a.get(startA) + b.get(endB) >= x) {
                startA++;
                endB--;
                count++;
            } else {
                startA++;
            }
        }
        System.out.println("1 "+count);

    }
}
/*
5 45
1 2 3 6 10
10 20 30 30 35
* */