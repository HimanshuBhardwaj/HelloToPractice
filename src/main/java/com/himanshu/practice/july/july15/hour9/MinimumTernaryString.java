package com.himanshu.practice.july.july15.hour9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 16/07/18.
 * Algo: Ad-Hoc
 *Submission: http://codeforces.com/contest/1009/submission/40398237
 */
public class MinimumTernaryString {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] str = br.readLine().toCharArray();

        String sortedString;

        int count1 = 0;
        int first2 = -1;

        for (int i = 0; i < str.length; i++) {
            char c = str[i];
            if (c == '1') {
                count1++;
            }
            if (first2 == -1 && c == '2') {
                first2 = i;
            }
        }

        if (first2 == -1) {
            for (int i = 0; i < str.length; i++) {
                if (str[i] != '1') {
                    System.out.print(str[i]);
                }
            }
            while (count1 > 0) {
                count1--;
                System.out.print('1');
            }
            return;
        }

        for (int i = 0; i < first2; i++) {
            if (str[i] != '1') {
                System.out.print(str[i]);
            }
        }
        while (count1 > 0) {
            count1--;
            System.out.print('1');
        }
        for (int i = first2; i < str.length; i++) {
            if (str[i] != '1') {
                System.out.print(str[i]);
            }
        }
    }
}
