package com.himanshu.practice.Aug.aug17.codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * Created by himanshubhardwaj on 17/08/18.
 * Statement: https://codeforces.com/contest/1023/problem/C
 * Algo: Subsequence, Bracket Matching
 * Submission: https://codeforces.com/contest/1023/submission/41713455
 */
public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String st[] = br.readLine().split(" ");
        int n = Integer.parseInt(st[0]);
        int k = Integer.parseInt(st[1]);
        Bracket[] brc = new Bracket[n];
        String str = br.readLine();

        for (int i = 0; i < str.length(); i++) {
            brc[i] = new Bracket(i, (str.charAt(i) == '(') ? true : false, false);
        }


        Stack<Bracket> stack = new Stack<>();
        int printed = 0;
        for (int i = 0; (printed < k) && (i < brc.length); i++) {
            if (brc[i].isOpen) {
                stack.push(brc[i]);
            } else {
                if (stack.peek().isOpen) {
                    Bracket b = stack.pop();
                    b.isRequired = true;
                    brc[i].isRequired = true;
                    printed += 2;
                }
            }
        }


        StringBuilder sb = new StringBuilder();

        for (int i = 0; (i < n); i++) {
            if (brc[i].isRequired) {
                if (brc[i].isOpen) {
                    sb.append('(');
                } else {
                    sb.append(')');
                }
                printed++;
            }
        }

        System.out.print(sb.toString());


    }
}


class Bracket {
    int index;
    boolean isOpen;
    boolean isRequired;

    public Bracket(int index, boolean isOpen, boolean isRequired) {
        this.index = index;
        this.isOpen = isOpen;
        this.isRequired = isRequired;
    }
}