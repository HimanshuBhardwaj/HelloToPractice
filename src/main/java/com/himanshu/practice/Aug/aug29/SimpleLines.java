package com.himanshu.practice.Aug.aug29;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Stack;

/**
 * Created by himanshubhardwaj on 29/08/18.
 * Statement: https://codeforces.com/contest/665/problem/C
 * Alg: DP, Stack, Fastoutput
 * Submission: https://codeforces.com/contest/665/submission/42240323
 */
public class SimpleLines {
    public static void main(String[] args) throws IOException {
        mySolution();
    }

    private static void mySolution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char str[] = br.readLine().toCharArray();
        int arr[][] = new int[str.length][26];


        for (int i = 0; i < str.length; i++) {
            for (int j = 0; j < 26; j++) {
                arr[i][j] = Integer.MAX_VALUE;
                if (i == 0) {
                    if (j == (str[i] - 'a')) {
                        arr[i][j] = 0;
                    } else {
                        arr[i][j] = 1;
                    }

                } else {
                    for (int k = 0; k < 26; k++) {
                        if (k != j && arr[i - 1][k] != Integer.MAX_VALUE) {
                            arr[i][j] = Math.min(arr[i][j], arr[i - 1][k] + ((j == (str[i] - 'a')) ? 0 : 1));
                        }
                    }
                }
            }
        }

        int min = Integer.MAX_VALUE;
        int ch = -1;
        String stri = "";

        for (int j = 0; j < 26; j++) {
            if (min > arr[str.length - 1][j]) {
                min = arr[str.length - 1][j];
                ch = -1;
            }
        }
//        System.out.println(min);

        Stack<Character> stack = new Stack<>();

        for (int i = str.length - 1; i >= 0; i--) {
            for (int j = 0; j < 26; j++) {
                if (min == arr[i][j] && (j != ch)) {
                    stack.push((char) ('a' + j));
                    //stri = (char) ('a' + j) + stri;
                    min = (j == (str[i] - 'a')) ? min : min - 1;
                    ch = j;
                    break;
                }
            }
        }

        PrintWriter pe = new PrintWriter(System.out);

        while (!stack.empty()) {
            pe.append(stack.pop());
        }
        pe.flush();
    }
}
