package com.himanshu.practice.july.jult31.codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 31/07/18.
 * Problem statement: https://codeforces.com/contest/1015/problem/C
 * this was an easy question and could have been solved in the compettetion.
 *
 * TODO: Get Ac on it
 */
public class D {
    static long lastHouse = 1;
    static long remainingDis = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");
        long n = Long.parseLong(str[0]);
        long k = Long.parseLong(str[1]);
        long s = Long.parseLong(str[2]);

        if ((k * (n - 1)) < s) {
            System.out.print("NO");
            return;
        }

        System.out.println("YES");
        if (s % (n - 1) == 0) {
            print(n, s - 1, k - 1);

            if (lastHouse + remainingDis <= n) {
                System.out.print((lastHouse + remainingDis));
            } else {
                System.out.print((lastHouse - remainingDis));
            }
        } else {
            print(n, s, k);
        }


    }

    static void print(long n, long s, long k) {
        long k1 = s / (n - 1);

        for (int i = 1; i <= k1; i++) {
            if (i % 2 == 1) {
                System.out.print(n + " ");
                lastHouse = n;
            } else {
                System.out.print(1 + " ");
                lastHouse = 1;
            }
        }


        long k2 = k - k1; //k1 is the number of remainin move after going through all this cycle; could be zero also
        long p = s % (n - 1); //p is the remaining move now
        remainingDis = p;


        if (k2 > 0) {
            if (lastHouse == 1) {
                System.out.print((lastHouse + (p - (k2 - 1))) + " ");
                lastHouse = lastHouse + (p - (k2 - 1));
                remainingDis = remainingDis - (p - (k2 - 1));
            } else {
                System.out.print((lastHouse - (p - (k2 - 1))) + " ");
                lastHouse = lastHouse - (p - (k2 - 1));
                remainingDis = remainingDis - (p - (k2 - 1));
            }

            long remainingMove = k - (k1 + 1);


            while (remainingMove > 0) {
                if (lastHouse > 1) {
                    lastHouse--;
                } else {
                    lastHouse++;
                }
                remainingMove--;
                remainingDis--;
                System.out.print(lastHouse + " ");
            }
        }
    }


}
