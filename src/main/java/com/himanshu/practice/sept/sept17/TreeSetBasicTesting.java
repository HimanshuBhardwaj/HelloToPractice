package com.himanshu.practice.sept.sept17;

import java.util.TreeSet;

/**
 * Created by himanshubhardwaj on 17/09/18.
 * lesson: In treeset, headSet, tailset can be find in logn time but iterating them could be linear.
 */
public class TreeSetBasicTesting {
    public static void main(String[] args) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 1; i < 200000; i++) {
            set.add(i);
        }

        long start = System.currentTimeMillis();

        for (int i = 100; i < 100000; i++) {
            set.headSet(i);
            set.tailSet(i);
        }


        long end = System.currentTimeMillis();

        System.out.println(end - start);
    }
}
