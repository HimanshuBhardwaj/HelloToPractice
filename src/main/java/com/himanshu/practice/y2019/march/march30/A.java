package com.himanshu.practice.y2019.march.march30;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 30/03/19.
 */
public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String str[] = br.readLine().split(" ");

        int[] left = new int[n];
        int[] right = new int[n];


        for (int i = 0; i < n; i++) {
            if (Integer.parseInt(str[i]) == 0) {
                left[i] = 1;
            } else {
                right[i] = 1;
            }
        }

        int min = Integer.MAX_VALUE;
        boolean leffValid = true;
        boolean rightValid = true;

        for (int i = (n - 1); i >= 0; i--) {
            if (left[i] == 0) {
                leffValid = false;
            }
            if (right[i] == 0) {
                rightValid = false;
            }


            if (leffValid==false && right[i]==0) {
                min=i+1;
                break;
            }

            if (rightValid==false && left[i]==0) {
                min=i+1;
                break;
            }
        }

        if (min==Integer.MAX_VALUE) {
            min=0;
        }

        System.out.println(min);

    }
}
