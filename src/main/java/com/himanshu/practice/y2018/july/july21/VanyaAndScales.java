package com.himanshu.practice.y2018.july.july21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * * Created by himanshubhardwaj on 21/07/18.
 * ProblemSet: https://codeforces.com/contest/552/problem/C
 *Algo: Number Theory, baseM conversion
 * Submission: https://codeforces.com/contest/552/submission/40592369
 */
public class VanyaAndScales {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");

        int w = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);

        int[] mBaseW = convertToBaseW(m, w);
        if (mBaseW.length > 100) {
            System.out.println("NO");
        }


        int carry = 0;


        for (int i = 0; i < mBaseW.length; i++) {
            int newNum = carry + mBaseW[i];
            carry = newNum / w;
            newNum = newNum % w;

            if (newNum == 1 || newNum == 0) {
                continue;
            }
            if (newNum == (w - 1)) {
                carry++;
                continue;
            }

            System.out.println("NO");
            return;
        }


        System.out.println("YES");


    }

    private static int[] convertToBaseW(int m, int w) {
        LinkedList<Integer> list = new LinkedList<>();

        while (m > 0) {
            list.addLast(m % w);
            m = m / w;
            //   System.out.println(list + " \t" + m);
        }
        list.addLast(0);

        int array[] = new int[list.size()];
        int pos = 0;

        for (int x : list) {
            array[pos] = x;
            pos++;
        }
        return array;
    }
}
