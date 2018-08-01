package com.himanshu.practice.july.jult31.codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 31/07/18.
 */
public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);

        int arr[] = new int[m + 1];

        for (int i = 0; i < n; i++) {
            str = br.readLine().split(" ");
            int ls = Integer.parseInt(str[0]);
            int rs = Integer.parseInt(str[1]);
            arr[ls]++;
            if (rs < m) {
                arr[rs + 1]--;
            }
        }

        int count = 0;
        for (int i = 1; i <= m; i++) {
            arr[i] += arr[i - 1];
            if (arr[i] == 0) {
                count++;
            }
        }

        System.out.println(count);
        if (count > 0) {
            for (int i = 1; i <= m; i++) {
                if (arr[i] == 0) {
                    System.out.print(i + " ");
                }
            }
        }

    }
}
