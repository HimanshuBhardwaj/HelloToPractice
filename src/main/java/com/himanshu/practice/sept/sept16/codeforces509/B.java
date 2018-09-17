package com.himanshu.practice.sept.sept16.codeforces509;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 16/09/18.
 */
public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1000000);

        String str[] = br.readLine().split(" ");
        long a = Long.parseLong(str[0]);
        long b = Long.parseLong(str[1]);
        long x = Long.parseLong(str[2]);
        long y = Long.parseLong(str[3]);

        long gcd = gcd(x, y);
        x = x / gcd;
        y = y / gcd;
        System.out.print(Math.min(a / x, b / y));

        if(x==y) {
            System.out.println(Math.min(a,b));
        } else if (x>y) {
            System.out.println(a/x);
        } else {
            System.out.println(b/y);
        }


    }

    static long gcd(long a, long b) {
        if (b > a) {
            return gcd(b, a);
        }
        if (b < 1) {
            return a;
        }
        if (b == 1) {
            return b;
        }
        return gcd(a % b, b);
    }

}
