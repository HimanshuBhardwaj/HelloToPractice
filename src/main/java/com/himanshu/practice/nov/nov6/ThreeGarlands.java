package com.himanshu.practice.nov.nov6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by himanshubhardwaj on 06/11/18.
 */
//TODO: Complete it
public class ThreeGarlands {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");
        int[] arr = new int[3];

        arr[0] = Integer.parseInt(str[0]);
        arr[1] = Integer.parseInt(str[1]);
        arr[2] = Integer.parseInt(str[2]);

        Arrays.sort(arr);

        if (1 == arr[0]) {
            System.out.print("YES");
            return;
        }


        int index[] = new int[arr[2]];



    }


    static int lcm(int a, int b) {
        return (a * b) / gcd(a, b);
    }

    static int gcd(int a, int b) {
        if (a < b) {
            return gcd(b, a);
        }
        if (b == 0) {
            return a;
        }
        if (b == 1) {
            return 1;
        }

        return gcd(b, a % b);
    }
}
