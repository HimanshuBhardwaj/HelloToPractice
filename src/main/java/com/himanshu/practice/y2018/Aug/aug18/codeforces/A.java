package com.himanshu.practice.y2018.Aug.aug18.codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 18/08/18.
 */
public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        while ((t-- > 0)) {
            boolean isPossible = true;
            int n = Integer.parseInt(br.readLine());
            char[] str = br.readLine().toCharArray();

            int diff = 5;
            for (int i = 0; isPossible && (i < (n / 2)); i++) {
                diff = Math.abs(str[i] - str[n - 1 - i]);
                if (str[i] == str[n - i - 1]) {
                    continue;
                } else if ((str[i] == 'a' && str[n - i - 1] != 'c') || (str[n - 1 - i] == 'a' && str[i] != 'c') ||
                        (str[n - 1 - i] == 'z' && str[i] != 'x') || (str[i] == 'z' && str[n - 1 - i] != 'x')) {
                    isPossible = false;
                } else {
                    if (!((str[i] + 1) == (str[n - i - 1] + 1) || (str[i] + 1) == (str[n - i - 1] - 1) || (str[i] - 1) == (str[n - i - 1] + 1) || (str[i] - 1) == (str[n - i - 1] - 1))) {
                        isPossible = false;
                    }
                }

            }
            if (isPossible) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}
