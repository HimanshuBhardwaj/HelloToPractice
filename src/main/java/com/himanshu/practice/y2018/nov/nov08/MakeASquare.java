package com.himanshu.practice.y2018.nov.nov08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * Created by himanshubhardwaj on 08/11/18.
 * 2:52 --3:06
 * could have saved 4 mins
 * Statement: https://codeforces.com/contest/962/problem/C
 * Algo: All subsets
 * Submission: https://codeforces.com/contest/962/submission/45438602
 */
public class MakeASquare {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int minReq = minRemovalRequired(str, 0, new java.util.LinkedList<Integer>());
        System.out.print((minReq==Integer.MAX_VALUE)?-1:minReq);


    }

    private static int minRemovalRequired(String str, int start, java.util.LinkedList<Integer> linkedList) {
        if (start == str.length()) {
            //System.out.println(linkedList);
            if (valid(linkedList)) {
                return str.length() - linkedList.size();
            } else {
                return Integer.MAX_VALUE;
            }
        }

        linkedList.addLast((int) (str.charAt(start) - '0'));
        int a = minRemovalRequired(str, start + 1, linkedList);
        linkedList.removeLast();
        int b = minRemovalRequired(str, start + 1, linkedList);

        return Math.min(a, b);
    }

    private static boolean valid(LinkedList<Integer> linkedList) {
        if (linkedList.isEmpty() || linkedList.getFirst() == 0) {
            return false;
        }
        long number = 0;
        for (int x : linkedList) {
            number = number * 10 + x;
        }

        return isSqure(number);
    }

    private static boolean isSqure(long number) {
        long sqrt = (long) Math.sqrt(number);

        if (((sqrt * sqrt) == number) || ((sqrt + 1) * (sqrt + 1) == number)) {
            return true;
        }

        return false;
    }
}
