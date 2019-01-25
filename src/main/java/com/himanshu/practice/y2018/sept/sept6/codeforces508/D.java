package com.himanshu.practice.y2018.sept.sept6.codeforces508;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Created by himanshubhardwaj on 06/09/18.
 */
public class D {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long a[] = new long[n];


        String[] str = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(str[i]);
        }

        if(n==1) {
            System.out.println(a[0]);
            return;
        }

        Arrays.sort(a);


        long sum = -1 * a[0];

        for (int i = 1; i < a.length; i++) {
            sum += Math. abs(a[i]);
        }

        System.out.println(sum);


    }
}
