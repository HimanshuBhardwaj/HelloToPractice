package com.himanshu.practice.y2018.Aug.aug12;

/**
 * Created by himanshubhardwaj on 13/08/18.
 */
public class TestingPerformance {
    public static void main(String[] args) {
        long count = 0;
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 100000; i++) {
            for (int j = 0; j < 1000000; j++) {
                count++;
            }
        }
        long endTime = System.currentTimeMillis();


        System.out.println(endTime - startTime);
        System.out.println(count);

        startTime = System.currentTimeMillis();
        state1(0);
        endTime = System.currentTimeMillis();
        System.out.println();
        System.out.println(endTime - startTime);


    }


    static void state1(int x) {
        if (x == 10000) {
            return;
        }
        int count = 1;
        for (int i = 0; i < 10000000; i++) {
            count++;
        }
        state1(x + 1);
    }


}
