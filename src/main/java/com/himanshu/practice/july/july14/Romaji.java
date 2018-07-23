package com.himanshu.practice.july.july14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 13/07/18.
 * https://codeforces.com/contest/1008/problem/A
 * Algo: Ad-hoc
 * Submission: https://codeforces.com/contest/1008/submission/40284740
 */
public class Romaji {
    static String vovel = "aeioun";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] st = br.readLine().toCharArray();

        boolean isValid = true;
        boolean flag = false;
        for (int i = 0; i < st.length; i++) {
            if (flag) {
                flag = !isVovel(st[i], false);
                if (flag) {
                    System.out.print("NO");
                    return;
                }
            } else {
                flag = !isVovel(st[i], true);
                if (flag && (i == (st.length - 1))) {
                    System.out.print("NO");
                    return;
                }
            }
        }
        System.out.print("YES");

    }

    public static boolean isVovel(char c, boolean t) {
        if (!t) {
            if (c == 'n') {
                return false;
            }
        }
        return vovel.contains(String.valueOf(c));
    }
}
