package com.himanshu.practice.y2019.march.march30;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 30/03/19.
 */
public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int max = Integer.MIN_VALUE;
        int max2Digit[] = new int[100];

        for (int i = 1; i < 100; i++) {
            int x = i;
            int product = 1;
            while (x > 0) {
                product = product * (x % 10);
                x = x / 10;
            }
            max2Digit[i] = Math.max(product, max2Digit[i - 1]);
        }
//        for (int i = 1; i < 100; i++) {
//            System.out.println(i + "\t\t" + max2Digit[i]);
//        }

        char[] orignalNumberC = String.valueOf(n).toCharArray();

        if (orignalNumberC.length <= 2) {
            System.out.print(max2Digit[n]);
            return;
        }


        int[] orignalNumber = new int[orignalNumberC.length];
        int[] orignalNumberCopy = new int[orignalNumberC.length];


        boolean first = true;


        for (int i = 2; i < orignalNumberC.length; i++) {
            if (orignalNumberC[i] != '9') {
                first = false;
            }
        }


        long product = (long) Math.pow(9, orignalNumberC.length - 2);
        int remainingNum = ((orignalNumberC[0]-'0')*10)+(orignalNumberC[1]-'0');

        if (!first) {
            remainingNum--;
        }

        System.out.print(product*max2Digit[remainingNum]);

    }
}
