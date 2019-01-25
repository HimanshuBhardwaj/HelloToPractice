package com.himanshu.practice.y2018.Aug.Aug15;

import java.util.ArrayList;

/**
 * Created by himanshubhardwaj on 15/08/18.
 */
public class Performance {
    public static void main(String[] args) {
        long count = 0;
        int[] arr = new int[100000];
        ArrayList<Integer> list = new ArrayList<>();

        long start = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 100000);
            list.add(arr[i]);
        }


        while (count++ < (1000000)) {
            long sum = 0l;
            for (int x : list) {
                sum += x;
            }
        }

        long end = System.currentTimeMillis();

        System.out.println(end - start);

    }
}
