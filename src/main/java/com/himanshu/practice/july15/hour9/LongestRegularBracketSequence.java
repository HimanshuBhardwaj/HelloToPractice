package com.himanshu.practice.july15.hour9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

/**
 * Created by himanshubhardwaj on 16/07/18
 * Problem Statement: https://codeforces.com/contest/5/problem/C.
 * Algo: DP, Stack
 * Submission: https://codeforces.com/contest/5/submission/40395844
 */
public class LongestRegularBracketSequence {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
//        System.out.println(str);
        printMAtchingPairs(str);
    }

    private static void printMAtchingPairs(String str) {
        char[] chars = str.toCharArray();
        int c[] = new int[chars.length];
        int d[] = new int[chars.length];
        Stack<Character> stack = new Stack<>();
        Stack<Integer> position = new Stack<>();


        for (int i = 0; i < chars.length; i++) {
            switch (chars[i]) {
                case '(':
                    stack.push(chars[i]);
                    position.push(i);
                    c[i] = -1;
                    d[i] = -1;
                    break;
                case ')':
                    if (!stack.empty()) {
                        if (stack.peek() == '(') {
                            stack.pop();
                            int startPos = position.pop();
                            c[i] = startPos;
                            d[i] = (startPos == 0) ? startPos :
                                    (d[startPos - 1] >= 0) ? d[startPos - 1] : startPos;
                        } else {
                            while (!stack.isEmpty()) {
                                stack.pop();
                                position.pop();
                            }
                            c[i] = -1;
                            d[i] = -1;
                        }
                    } else {
                        c[i] = -1;
                        d[i] = -1;
                    }
                    break;
            }
        }


//        System.out.println();
//        for (int i = 0; i < c.length; i++) {
//            System.out.print(c[i] + " ");
//        }
//        System.out.println();
//        for (int i = 0; i < c.length; i++) {
//            System.out.print(d[i] + " ");
//        }
//        System.out.println();

        TreeMap<Integer, Integer> map = new TreeMap<>();
        //Map.Entry<K,V> lastEntry()

        for (int i = 0; i < c.length; i++) {
            if (d[i] >= 0) {
                int length = i - d[i] + 1;
                if (map.containsKey(length)) {
                    map.put(length, map.get(length) + 1);
                } else {
                    map.put(length, 1);
                }
            }
        }

        Map.Entry<Integer, Integer> entry = map.lastEntry();

        if (entry != null) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        } else {
            System.out.println("0 1");
        }
    }
}
/*
*
Input:
))(()()()())(((((()))))

* */
