package com.himanshu.practice.y2018.june.hour_3;

/**
 * Created by Himanshu Bhardwaj on 08/06/18.
 */
public class Sort {
    public static void main(String[] args) {
        int size = 17;
        int arr[] = new int[size];

        for (int i = 0; i < size; i++) {
            arr[i] = (i * i * i + i + 23) % 17;
        }
        System.out.print("Actual array: ");
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();

        QuickSort.quickSort(arr, 0, arr.length - 1);

        System.out.print("After Sorting: ");
        for (int j : arr) {
            System.out.print(j + " ");
        }
        System.out.println();
    }
}


class QuickSort {

    //both extrime points are included
    static void quickSort(int[] arr, int start, int end) {
        if (arr == null || start > end || start == end || start < 0 || end >= arr.length) {
            return;
        }

        int pivot = end;
        int i = start - 1;

        for (int j = start; j < end; j++) {
            if (arr[j] <= arr[pivot]) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, pivot);


        quickSort(arr, start, i);
        quickSort(arr, i + 1, end);
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


}