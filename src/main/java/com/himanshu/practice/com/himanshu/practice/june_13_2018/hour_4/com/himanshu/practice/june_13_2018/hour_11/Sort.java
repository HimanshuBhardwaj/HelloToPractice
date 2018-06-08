package com.himanshu.practice.com.himanshu.practice.june_13_2018.hour_4.com.himanshu.practice.june_13_2018.hour_11;

import java.util.Queue;

/**
 * Created by himanshubhardwaj on 14/06/18.
 */

//TODO: Complete it, as it is not working

class Sort {
    public static void main(String[] args) {
        int arr[] = new int[15];
        for (int i = 0; i < 15; i++) {
            arr[i] = (i * i + 101 * i + 93) % 101;
        }

        QuickSort.print(arr);
        System.out.println();
        QuickSort.prtition(arr, 0, arr.length - 1);
        QuickSort.print(arr);
    }

}


class QuickSort {


    static void prtition(int arr[], int start, int end) {
        if (start == end || start > end || start < -1 || end >= arr.length) {
            return;
        }

        if (start == (end - 1)) {
            int min = Math.min(arr[start], arr[end]);
            int max = Math.max(arr[start], arr[end]);
            arr[start] = min;
            arr[end] = max;
            return;
        }

        int pivot = (start + end) / 2;
        int pos = 0;

        for (int i = start; i <= end; i++) {
            if (arr[i] < arr[pivot])
                pos++;
        }
        System.out.println("pivot: " + pivot + "\tpos: " + pos + "\tstart: " + start + "end: " + end);
        swap(arr, pivot, start + pos);
        int low = start;
        int high = end;
        int pivPos = start + pos;

        while (low < high && low < pivPos && high > pivPos && high <= end && low >= start) {
            while (arr[low] < arr[pivPos] && low<pivPos)
                low++;
            while (arr[high] >= arr[pivot] && high>pivPos)
                high--;

            if (!(low < high && low < pivPos && high > pivPos && high <= end && low >= start))
                break;
            else
                swap(arr, low, high);
        }


        prtition(arr, start, pivPos - 1);
        prtition(arr, pivPos + 1, end);

        print(arr);
    }

    static void swap(int arr[], int start, int end) {
        System.out.println("start: " + start + "end: " + end);
        int temp = arr[start];
        arr[start] = arr[end];
        arr[end] = temp;
    }

    static void print(int arr[]) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

}