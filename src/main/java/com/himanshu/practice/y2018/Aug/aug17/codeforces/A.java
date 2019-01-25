package com.himanshu.practice.y2018.Aug.aug17.codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 17/08/18.
 * Statement: https://codeforces.com/contest/1023/problem/B
 * Algo: Pattern Matching
 * Submission: https://codeforces.com/contest/1023/submission/41701647
 */
public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        String regX = br.readLine();
        String string = br.readLine();

        int pos = -1;

        for (int i = 0; i < regX.length(); i++) {
            if (regX.charAt(i) == '*') {
                pos = i;
                break;
            }
        }


        if (pos == -1) {
            if (string.equals(regX)) {
                System.out.print("YES");
            } else {
                System.out.print("NO");
            }
            return;
        }

        if ((string.length() + 1) < (regX.length())) {
            System.out.print("NO");
            return;
        }


        if (regX.length() == 1) {
            System.out.println("YES");
            return;
        }

        boolean couldEqual = true;

        for (int i = 0; couldEqual && (i < pos); i++) {
            if (regX.charAt(i) != string.charAt(i)) {
                couldEqual = false;
            }
        }

        if (couldEqual && (pos != (regX.length() - 1))) {
            String SubString = regX.substring(pos + 1);
            int start = string.lastIndexOf(SubString);

            if (start == -1 || start < pos) {
                couldEqual = false;
            }

            if ((start + SubString.length()) != string.length()) {
                couldEqual = false;
            }
        }


        if (couldEqual) {
            System.out.print("YES");
        } else {
            System.out.print("NO");
        }


    }
}
