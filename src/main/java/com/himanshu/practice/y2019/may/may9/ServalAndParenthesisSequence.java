package com.himanshu.practice.y2019.may.may9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Stack;

/**
 * Created by himanshubhardwaj on 12/05/19.
 * Problem Statement:
 * Algo: Stack, greedy
 * Submission: https://codeforces.com/contest/1153/submission/54004640
 */
public class ServalAndParenthesisSequence {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        char[] string = br.readLine().toCharArray();

        string = getSolved(string);

        if (string == null) {
            System.out.print(":(");
        } else {
            PrintWriter printWriter = new PrintWriter(System.out);
            for (char c : string) {
                printWriter.append(c);
            }
            printWriter.flush();
        }


    }

    private static char[] getSolved(char[] string) {
        if (string == null || string.length % 2 == 1) {
            return null;
        }
        int openCount = 0;
        int closeCount = 0;
        int question = 0;
        for (char c : string) {
            if (c == '(') {
                openCount++;
            } else if (c == ')') {
                closeCount++;
            } else {
                question++;
            }
        }

        if (openCount > string.length / 2 || closeCount > string.length / 2) {
            return null;
        }

        int remainingOpen = string.length / 2 - openCount;
        int remainingClose = string.length / 2 - closeCount;
        int commulativeSum = 0;

        for (int i = 0; i < string.length; i++) {
            if (string[i] == '(') {
                commulativeSum++;
            } else if (string[i] == ')') {
                commulativeSum--;
            } else {
                if (remainingOpen > 0) {
                    remainingOpen--;
                    string[i] = '(';
                    commulativeSum++;
                } else {
                    remainingClose--;
                    string[i] = ')';
                    string[i] = ')';
                }
            }

            if (commulativeSum < 0 || (i < (string.length - 1) && commulativeSum == 0)) {
                return null;
            }
        }

        if (checkBalance(string)) {
            return string;
        }


        return null;
    }

    private static boolean checkBalance(char[] string) {
        Stack<Character> characters = new Stack<>();
        for (int i = 0; i < string.length; i++) {
            if (string[i] == '(') {
                characters.push('(');
            } else {
                if (characters.isEmpty()) {
                    return false;
                }
                characters.pop();

                if (i < (string.length - 1)) {
                    if (characters.isEmpty()) {
                        return false;
                    }
                }
            }
        }

        return characters.isEmpty();
    }
}
