package com.himanshu.practice.sept.sept19.googlepractice;

import javax.swing.*;
import java.util.Arrays;

/**
 * Created by himanshubhardwaj on 19/09/18.
 */
public class ArrayUtils {
    public static void main(String[] args) {
        long arr[] = new long[10];

        for(int i=0;i<arr.length;i++) {
            arr[i] = i+i+99+(99-8*i);
        }
        Arrays.sort(arr);
        for(long x:arr) {
            System.out.print(x+" ");
        }
        System.out.println();
        System.out.println(Util.ceil(arr,199));
    }
}


class Util {
    static long ceil(long[] array, long element) {
        return ceilHelper(0, array.length - 1, array, element);
    }

    static long ceilHelper(int start, int end, long[] array, long element) {
        if (start > end || start < 0 || end >= array.length || array[end] < element) {
            return -1;//element not present
        }
        if (start == end) {
            return array[end];
        }

        int mid = start + (end - start) / 2;

        if (array[mid] >= element) {
            return ceilHelper(start, mid, array, element);
        } else {
            return ceilHelper(mid + 1, end, array, element);
        }
    }

}