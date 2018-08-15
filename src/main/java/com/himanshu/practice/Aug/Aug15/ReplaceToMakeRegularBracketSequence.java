package com.himanshu.practice.Aug.Aug15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Created by himanshubhardwaj on 16/08/18.
 * Problem Statament: https://codeforces.com/contest/612/problem/C
 * Algo: String matching
 * Submission: https://codeforces.com/contest/612/submission/41633860
 */
public class ReplaceToMakeRegularBracketSequence {
    static Set<Character> closing = new HashSet<>();
    static Set<Character> opening = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] str = br.readLine().toCharArray();
        closing.add('>');
        closing.add('}');
        closing.add(']');
        closing.add(')');
        opening.add('<');
        opening.add('{');
        opening.add('[');
        opening.add('(');


        int changes = getMinChanges(str);
        System.out.print((changes >= 0) ? changes : "Impossible");
    }

    private static int getMinChanges(char[] str) {
        Stack<Character> stack = new Stack<>();
        int changes = 0;

        for (int i = 0; i < str.length; i++) {
            if (opening.contains(str[i])) {
                stack.push(str[i]);
            } else if (!stack.empty()) {
                char top = stack.pop();
                if (closing.contains(top)) {
                    return -1;
                } else {
                    if (!matching(top, str[i])) {
                        changes++;
                    }
                }
            } else {
                return -1;
            }
        }

        return (stack.isEmpty()) ? changes : -1;

    }


    private static boolean matching(char top, char c) {
        switch (top) {
            case '<':
                return (c == '>') ? true : false;
            case '{':
                return (c == '}') ? true : false;
            case '[':
                return (c == ']') ? true : false;
            case '(':
                return (c == ')') ? true : false;
        }

        //wont reach till here
        return false;

    }
}
