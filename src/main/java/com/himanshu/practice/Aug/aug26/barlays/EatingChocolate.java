package com.himanshu.practice.Aug.aug26.barlays;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 26/08/18.
 */
public class EatingChocolate {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        while ((t--) > 0) {
            long n = Long.parseLong(br.readLine());
            long count = 1;
            long position = 0;

            if (n <= 10) {
                result(n, count, position);
            } else {
                long k = (int)((-1 + Math.sqrt((8 * n) + 1)) / 4);
                n = n - (k * ((k * 2) + 1));
                result(n, (2 * k) + 1, 0);
            }
        }
    }

    private static long get(int start, long n, long end) {
        return 0;
    }

    static void result(long n, long count, long position) {
        System.out.println(n + "\t" + count + "\t" + position);
        while (n >= count) {
            n = n - count;
            count++;
            position = (position + 1) % 2;
        }

        if (position == 0) {
            System.out.println("Source");
        } else {
            System.out.println("RotateAndSpeakGame");
        }
    }
}
