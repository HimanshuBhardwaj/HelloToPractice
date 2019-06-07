package com.himanshu.practice.y2019.may.may12.Carousell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 12/05/19.
 * //How report to be genetated when input size has to be grater than 30??
 */
public class A {
    public static void main(String[] args) throws IOException {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String str[] = br.readLine().split(",");
            long commulativeMax = 0l;
            long tempLong = 0;

            for (int i = 0; i < str.length; i++) {
                long num = Long.parseLong(str[i].trim());
                if ((tempLong + num) > 0) {
                    tempLong = tempLong + num;
                } else {
                    tempLong = 0;
                }
                commulativeMax = Math.max(commulativeMax, tempLong);
            }

            System.out.print(commulativeMax);

        } catch (Exception e) {
            System.out.print(0);
        }

    }
}
