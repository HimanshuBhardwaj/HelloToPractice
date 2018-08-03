package com.himanshu.practice.Aug.Aug3.codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 03/08/18.
 */
public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        long m = Long.parseLong(str[1]);

        long remaining = m;
        int pages = 0;

        str = br.readLine().split(" ");


        for (int i = 0; (i < str.length); i++) {
            long element = Long.parseLong(str[i]);
            if (remaining > element) {
                remaining = remaining - element;
                System.out.print(0 + " ");
                continue;
            }
            if (((element - remaining) % m) == 0) {
                System.out.print((1 + ((element - remaining) / m)) + " ");
                remaining = m;
                continue;
            }

            //element > remaining

            System.out.print((1 + ((element - remaining) / m)) + " ");
            remaining = m - ((element - remaining) % m);
        }


    }
}
