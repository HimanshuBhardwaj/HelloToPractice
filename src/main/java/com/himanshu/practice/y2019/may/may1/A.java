package com.himanshu.practice.y2019.may.may1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Created by himanshubhardwaj on 01/05/19.
 */
public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        String[] str = br.readLine().split(" ");
        int[] arr = new int[n];

        long count = 0;
        boolean isInfinite = false;
        for (int i = 0; i < str.length; i++) {
            arr[i] = Integer.parseInt(str[i]);
        }

        for (int i = 1; !isInfinite && (i < n); i++) {
            if (arr[i] == 1) {
                if (arr[i - 1] == 1) {
                    isInfinite = true;
                } else if (arr[i - 1] == 2) {
                    count += 3;
                }
                if (arr[i - 1] == 3) {
                    count += 4;
                }
            } else if (arr[i] == 2) {
                if (arr[i - 1] == 1) {
                    if (i >= 2 && arr[i - 2] == 3) {
                        count += 2;
                    } else {
                        count += 3;
                    }
                } else if (arr[i - 1] == 2) {
                    isInfinite = true;
                }
                if (arr[i - 1] == 3) {
                    isInfinite = true;
                }
            } else if (arr[i] == 3) {
                if (arr[i - 1] == 1) {
                    count += 4;
                } else if (arr[i - 1] == 2) {
                    isInfinite = true;
                } else if (arr[i - 1] == 3) {
                    isInfinite = true;
                }
            }
        }


        if (isInfinite) {
            System.out.println("Infinite");
        } else {
            System.out.println("Finite");
            System.out.println(count);
        }
    }
}
