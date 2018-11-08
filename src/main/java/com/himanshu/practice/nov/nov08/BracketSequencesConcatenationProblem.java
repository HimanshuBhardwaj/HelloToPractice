package com.himanshu.practice.nov.nov08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * Created by himanshubhardwaj on 08/11/18.
 * Statement: https://codeforces.com/contest/990/problem/C
 * Algo:
 */

//TODO: Complete it
public class BracketSequencesConcatenationProblem {
    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        int n = Integer.parseInt(br.readLine());
//
//        Pattern[] patterns = new Pattern[n];
//
//        for (int i = 0; i < n; i++) {
//            patterns[i] = new Pattern();
//            patterns[i].charArray = br.readLine().toCharArray();
//        }

        String s = "(())(()";
        Pattern p = new Pattern();
        p.charArray = s.toCharArray();
        System.out.println(p.computePrefixScore());

    }
}


class Pattern {
    char[] charArray;
    int suffixScore;
    int prefixScore;


    Integer computePrefixScore() {
        Stack<Character> stack = new Stack<>();

        Integer maxEmpty = charArray.length;


        for (int i = charArray.length - 1; i >= 0; i--) {
            if (stack.isEmpty()) {
                stack.push(charArray[i]);
            } else {
                if (charArray[i] == ')') {
                    stack.push(')');
                } else {
                    //'('

                    if (stack.peek() == ')') {
                        stack.pop();
                    } else {
                        stack.push('(');
                    }
                }
            }

            if (stack.isEmpty()) {
                maxEmpty = i;
            }
        }


        for (int i = maxEmpty - 1; i >= 0; i--) {

        }

        return maxEmpty;


    }
}