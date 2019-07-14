package com.himanshu.practice.y2019.july.july12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.TreeSet;

/**
 * Created by himanshubhardwaj on 12/07/19.
 */
public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        long n = Long.parseLong(str[0]);
        long m = Long.parseLong(str[1]);
        long k = Long.parseLong(str[2]);


        str = br.readLine().split(" ");
        TreeSet<Long> treeSet = new TreeSet<Long>();
        TreeSet<Long> treeSet2 = new TreeSet<Long>();

        for (int i = 0; i < str.length; i++) {
            treeSet.add(Long.parseLong(str[i]) - 1);
            treeSet2.add(Long.parseLong(str[i]) - 1);
        }

        long numAllowedItems = k;

        int count = 0;
        Stack<Long> stack = new Stack<>();
        long start = startOFCurrentPage(n, k, getPageNumber(n, k, treeSet.first()));
        long lastremoved;

        while (!treeSet.isEmpty()) {

            count++;
            int tempCount = 0;
            while ((treeSet.first() - start + 1) <= numAllowedItems) {
                tempCount++;
                lastremoved = treeSet.first();
//                if (nextStart == null) {
//                    if ((treeSet.first()-start) )
//                }

//                numAllowedItems -= (treeSet.first() - start + 1);
                treeSet.remove(treeSet.first());
            }
            start = start + tempCount;

            if ((treeSet.first()-start+1)>numAllowedItems) {

            }
        }

        System.out.println(count);


    }

    static long getPageNumber(long n, long k, long index) {
        return index / k;
    }

    static long startOFCurrentPage(long n, long k, long currentPage) {
        return (k * currentPage);
    }
}
