package com.himanshu.practice.y2018.nov.nov6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 06/11/18.
 * Statement :https://codeforces.com/contest/946/problem/C
 * Algo: String traversal; Ad-Hoc
 * Submission: https://codeforces.com/contest/946/submission/45385822
 * I should have understood this question well before attempting
 */
public class StringTransformation {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] str = br.readLine().toCharArray();


        int pos = 0;
        int expectedChar = 0;

        while ((expectedChar < 26) && (pos < str.length)) {
            if ((expectedChar - (int) (str[pos] - 'a')) == 0) {
                expectedChar++;
                pos++;
            } else if ((expectedChar - (int) (str[pos] - 'a')) >= 1) {
                str[pos] = (char) (str[pos] + 1);
            } else {
                pos++;
            }
        }

        if (expectedChar == 26) {
            System.out.print(new String(str));
        } else {
            System.out.print(-1);
        }


    }
}
