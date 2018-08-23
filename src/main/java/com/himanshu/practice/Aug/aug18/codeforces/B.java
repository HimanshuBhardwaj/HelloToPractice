package com.himanshu.practice.Aug.aug18.codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 18/08/18.
 */
public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");

        long n = Long.parseLong(str[0]);
        int q = Integer.parseInt(str[1]);

        for (int i = 0; i < q; i++) {
            str = br.readLine().split(" ");
            long x = Long.parseLong(str[0]);
            long y = Long.parseLong(str[1]);


            if (n % 2 == 1) {
                if ((x + y) % 2 == 0) {
                    if (x % 2 == 1 && y % 2 == 1) {
                        long offset = ((x / 2) * n) + ((y + 1) / 2);
                        System.out.println(offset);
                    } else {
                        long offset = (((x - 1) / 2) * n) + ((n + 1) / 2) + ((y) / 2);
                        System.out.println(offset);
                    }

                } else {
                    long offset = (n * n + 1) / 2;

                    if (x % 2 == 1 && y % 2 == 0) {
                        offset += ((x - 1) / 2) * n + (y / 2);
                        System.out.println(offset);
                    } else {
                        offset += (((x - 1) / 2) * n) + (n / 2) + (y + 1) / 2;
                        System.out.println(offset);
                    }
                }
            } else {
                //n is even
                if ((x + y) % 2 == 0) {
                    if (x % 2 == 1 && y % 2 == 1) {
                        long offset = ((n * x) / 4) + (y + 1) / 2;
                        System.out.println(offset);
                    } else {
                        long offset = (((x - 1) * n) / 4) + (n / 2) + (y / 2);
                        System.out.println(offset);
                    }

                } else {
                    long offset = (n * n) / 2;

                    if (x % 2 == 1 && y % 2 == 0) {
                        offset += ((x * n) / 4) + (y / 2);
                        System.out.println(offset);
                    } else {
                        offset += (n / 2) + (((x - 1) / 2) * (n / 2)) + (y + 1) / 2;
                        System.out.println(offset);
                    }
                }


            }

        }

    }
}
