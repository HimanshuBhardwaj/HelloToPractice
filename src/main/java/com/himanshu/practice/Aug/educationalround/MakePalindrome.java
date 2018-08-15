package com.himanshu.practice.Aug.educationalround;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * Created by himanshubhardwaj on 13/08/18.
 * Problem Statement: https://codeforces.com/contest/600/problem/C
 * Algo: Ad-hoc
 * Submission: https://codeforces.com/contest/600/submission/41561577
 *
 */
public class MakePalindrome {
    static char[] string;
    static int frequency[] = new int[26];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        string = br.readLine().toCharArray();

        for (char c : string) {
            frequency[c - 'a']++;
        }

        System.out.println(getString());
    }

    public static String getString() {
        LinkedList<Integer> linkedList = new LinkedList<>();

        for (int i = 0; i < frequency.length; i++) {
            if (frequency[i] % 2 == 1) {
                linkedList.addLast(i);
            }
        }

        char ch[] = new char[string.length];
        int start = 0;
        int end = ch.length - 1;

        for (int i = 0; i < frequency.length; i++) {
            if (linkedList.size() >= 2 && i == linkedList.peek()) {
                linkedList.poll();
                frequency[i]++;
                frequency[linkedList.getLast()]--;
                linkedList.removeLast();
            }

            while (frequency[i] > 1) {
                ch[start] = (char) ('a' + i);
                ch[end] = (char) ('a' + i);
                frequency[i] = frequency[i] - 2;
                start++;
                end--;
            }

            if (start == end && !linkedList.isEmpty()) {
                ch[start] = (char) ('a' + linkedList.poll());
            }

        }
//        for (char c : ch) {
//            System.out.print(c);
//        }
//        System.out.println();
        return new String(ch);
    }
}
