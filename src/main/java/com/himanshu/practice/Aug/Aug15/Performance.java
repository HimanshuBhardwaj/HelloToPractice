package com.himanshu.practice.Aug.Aug15;

import java.util.LinkedList;

/**
 * Created by himanshubhardwaj on 15/08/18.
 */
public class Performance {
    public static void main(String[] args) {
        long count = 0;
        int[] arr = new int[100000];
        LinkedList<Integer> list = new LinkedList<>();

        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 100000);
            list.addLast(arr[i]);
        }

        long start = System.currentTimeMillis();

        while (count++ < (100000)) {
            long sum = 0l;
            for (int x : list) {
                sum += x;
            }
        }

        long end = System.currentTimeMillis();

        System.out.println(end - start);

    }
}
