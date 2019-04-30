package com.himanshu.practice.y2019.April.april29;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * Created by himanshubhardwaj on 29/04/19.
 */
public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);
        int r = Integer.parseInt(str[2]);

        int buy[] = new int[n];
        int sell[] = new int[m];
        str = br.readLine().split(" ");

        for (int i = 0; i < n; i++) {
            buy[i] = Integer.parseInt(str[i]);
        }
        Arrays.sort(buy);

        str = br.readLine().split(" ");

        for (int i = 0; i < m; i++) {
            sell[i] = Integer.parseInt(str[i]);
        }


        Arrays.sort(sell);

        if (buy[0] >= sell[sell.length - 1]) {
            System.out.print(r);
            return;
        }

        int count = r / buy[0];
        r = r % buy[0];

        r += count * sell[sell.length - 1];
        System.out.print(r);

    }
}
