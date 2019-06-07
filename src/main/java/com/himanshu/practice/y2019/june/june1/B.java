package com.himanshu.practice.y2019.june.june1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.TreeSet;

/**
 * Created by himanshubhardwaj on 01/06/19.
 * https://codeforces.com/contest/1148/problem/B
 * Also: Ad-Hoc
 * Submission: https://codeforces.com/contest/1148/submission/55028457
 */
public class B {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);
        long ta = Long.parseLong(str[2]);
        long tb = Long.parseLong(str[3]);
        int k = Integer.parseInt(str[4]);

        long[] fltA = new long[n];
        long[] fltB = new long[m];
        str = br.readLine().split(" ");


        for (int i = 0; i < str.length; i++) {
            fltA[i] = Long.parseLong(str[i]);
        }


        str = br.readLine().split(" ");
        TreeSet<ElementB> flightB = new TreeSet<>();
        for (int i = 0; i < m; i++) {
            fltB[i] = Long.parseLong(str[i]);
            flightB.add(new ElementB(fltB[i], i));
        }

        long longest = Long.MIN_VALUE;
        ElementB tempElementB = new ElementB(0, 0);

        if (k >= n || k >= m) {
            System.out.println(-1);
            return;
        }

        for (int i = 0; i < n; i++) {
            //we will remove flights till i
            if (i > k) {
                break;
            }

            long destinationTime = fltA[i] + ta;
            tempElementB.element = destinationTime;
            ElementB elementB = flightB.ceiling(tempElementB);
            if (elementB == null) {
                System.out.println(-1);
                return;
            }

            int remainingFlight = k - (i); //this many flights we will have to remove from B
            int nextIndex = elementB.index + remainingFlight;

            if (nextIndex >= m) {
                System.out.println(-1);
                return;
            }

            longest = Math.max(longest, tb + fltB[nextIndex]);
        }

        if (longest == Long.MIN_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(longest);
        }
    }
}


class ElementB implements Comparable<ElementB> {
    long element;
    int index;

    @java.beans.ConstructorProperties({"element", "index"})
    public ElementB(long element, int index) {
        this.element = element;
        this.index = index;
    }

    @Override
    public int compareTo(ElementB o) {
        return (int) (this.element - o.element);
    }
}
/*

4 4 1 1 3
1 3 5 10
4 6 8 11000

* */