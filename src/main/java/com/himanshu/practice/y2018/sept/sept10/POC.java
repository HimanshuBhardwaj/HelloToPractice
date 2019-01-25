package com.himanshu.practice.y2018.sept.sept10;

import java.util.Arrays;

/**
 * Created by himanshubhardwaj on 11/09/18.
 */
public class POC {
    public static void main(String[] args) {
        int number[] = {1, 6, 4, 1, 4, 5, 8, 8, 4, 6, 8, 8, 9, 7, 9, 5, 9};
        Arrays.sort(number);
        for(int x: number) {
            System.out.print(x+" ");
        }

    }
}
