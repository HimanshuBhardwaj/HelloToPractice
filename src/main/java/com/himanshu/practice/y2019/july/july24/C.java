package com.himanshu.practice.y2019.july.july24;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * Created by himanshubhardwaj on 14/07/19.
 */
public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int q = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (q-- > 0) {
            String s = br.readLine();
            String t = br.readLine();
            String p = br.readLine();

            if (!isSubsequence(s, t)) {
                pw.append("NO\n");
                continue;
            }

            char[] newSA = (s + p).toCharArray();
            Arrays.sort(newSA);
            char[] tA = t.toCharArray();
            Arrays.sort(tA);

            if (isSubsequence(new String(tA), new String(newSA))) {
                pw.append("YES\n");
            } else {
                pw.append("NO\n");
            }

        }
        pw.flush();

    }

    //will return id s is subsequence of t
    static boolean isSubsequence(String s, String t) {
        if (s.length() == 0) {
            return true;
        }

        int startT = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == t.charAt(startT)) {
                startT++;
            } else {
                startT++;
                i--;
            }

            if (i == (s.length() - 1)) {
                return true;
            }

            if (startT == t.length()) {
                return false;
            }
        }
        return false;

    }
}
