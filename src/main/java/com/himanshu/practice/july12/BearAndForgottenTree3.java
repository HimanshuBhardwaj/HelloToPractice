package com.himanshu.practice.july12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 12/07/18.
 */
public class BearAndForgottenTree3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");

        int n = Integer.parseInt(str[0]);
        int d = Integer.parseInt(str[1]);
        int h = Integer.parseInt(str[2]);

        if ((2 * h) < d || d < h || n <= h || n < (d + 1) || n < h) {
            System.out.print("-1");
            return;
        }
        if (d == 1) {
            if (h == 1 && n == 2) {
                System.out.println("1 2");
            } else {
                System.out.println("-1");

            }
            return;
        }

        // 2*h>=d>=h
        int destination = 1;
        int source = 1;
        for (int i = 1; i <= h; i++) {
            System.out.println((source + " " + (++destination)));
            source = destination;
        }


        // h+1 nodes printed

        int nd = d - h;
        source = 1;
        for (int i = 1; i <= nd; i++) {
            System.out.println((source + " " + (++destination)));
            source = destination;
        }
//till here  d+1 nodes printed


        //last node printed = destination;

        if (n == (d + 1)) {
            return;
        }

        int remNodes = n - (d + 1);

        for (int i = 0; i < remNodes; i++) {
            if (d != h) {
                System.out.println("1 " + (++destination));
            } else {
                System.out.println("2 " + (++destination));
            }
        }
    }
}