package com.himanshu.practice.y2019.may.may12.Carousell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Created by himanshubhardwaj on 12/05/19.
 */
public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        String t = br.readLine();

        System.out.println(findSmallestDivisor(s, t));


    }

    public static int findSmallestDivisor(final String s, final String t) {

        if (s == null || t == null) {
            return -1;
        }

        if (isDivisible(s, 0, t)) {
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i <= t.length(); i++) {
                sb.append(t.charAt(i - 1));
                if ((t.length() % i == 0) && (s.length() % i == 0)) {
                    String subString = sb.toString();
                    if (isDivisible(t, 0, subString) && isDivisible(s, 0, subString)) {
                        return subString.length();
                    }
                }
            }
        }
        return -1;
    }


    private static boolean isDivisible(final String s, int start, final String t) {
        if (s.length() < t.length()) {
            return false;
        }

        if (s.length() == t.length()) {
            if (s.equals(t)) {
                return true;
            } else {
                return false;
            }
        }

        if (s.length() - start < t.length()) {
            return false;
        }


        for (int i = start; i <= start + t.length() - 1; i++) {
            if (s.charAt(i) != t.charAt(i - start)) {
                return false;
            }
        }

        int newStart = start + t.length();

        if (newStart == s.length()) {
            return true;
        } else {
            return isDivisible(s, newStart, t);
        }
    }
}
