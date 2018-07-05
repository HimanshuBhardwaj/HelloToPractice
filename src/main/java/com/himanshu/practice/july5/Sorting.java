package com.himanshu.practice.july5;

/**
 * Created by himanshubhardwaj on 05/07/18.
 * Mergesort:  3:51 --> 4:05
 * Quick Sort:  4:07 -->
 */
public class Sorting {
    public static void main(String[] args) {
        int arr[] = new int[19];


        for (int i = 0; i < arr.length; i++) {
            arr[i] = (i + 111) * (4 + i + 2) % 21 + i;
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        int[] arrMerge = MergeSort.sort(arr);

        for (int i = 0; i < arrMerge.length; i++) {

            System.out.print(arrMerge[i] + " ");
        }
        System.out.println();

        int[] arrQuick = arr.clone();
        QuickSort.sort(arrQuick);
        System.out.println();
        for (int i = 0; i < arrQuick.length; i++) {

            System.out.print(arrQuick[i] + " ");
        }
        System.out.println();


    }

}


class MergeSort {
    public static int[] sort(int arr[]) {
        return sortHelper(0, arr.length - 1, arr);
    }

    private static int[] sortHelper(int start, int end, int[] arr) {
        if (start > end) {
            return null;
        }
        if (start == end) {
            int[] a = new int[1];
            a[0] = arr[start];
            return a;
        }
        if (start + 1 == end) {
            int[] a = new int[2];
            a[0] = Math.min(arr[start], arr[end]);
            a[1] = Math.max(arr[start], arr[end]);
            return a;
        }


        int mid = start + (end - start) / 2;

        int[] left = sortHelper(start, mid, arr);
        int[] right = sortHelper(mid + 1, end, arr);

        return merge(left, right);


    }

    private static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int posL = 0;
        int posR = 0;

        for (int i = 0; i < result.length; i++) {
            if (posL < left.length && posR < right.length) {
                if (left[posL] < right[posR]) {
                    result[i] = left[posL++];
                } else {
                    result[i] = right[posR++];
                }
            } else if (posL < left.length) {
                result[i] = left[posL++];
            } else {
                result[i] = right[posR++];
            }
        }
        return result;
    }

}


class QuickSort {
    public static void sort(int[] arr) {
        sortHelper(0, arr.length - 1, arr);
    }

    private static void sortHelper(int start, int end, int[] arr) {
        if (start >= end) {
            return;
        }
        if (start + 1 == end) {
            int temp1 = (arr[start] < arr[end]) ? arr[start] : arr[end];
            int temp2 = (arr[start] > arr[end]) ? arr[start] : arr[end];
            arr[start] = temp1;
            arr[end] = temp2;
            return;
        }

        int pivot = end;
        int less = -1 + start;
        //less+1 >= arr[pivot]

        for (int i = start; i <= end; i++) {
            if (arr[i] < arr[pivot]) {
                less++;
                swap(less, i, arr);
            }
        }

        swap(less + 1, pivot, arr);
        sortHelper(start, less, arr);
        sortHelper(less + 2, end, arr);
    }

    private static void swap(int pos1, int pos2, int[] arr) {
        int temp = arr[pos1];
        arr[pos1] = arr[pos2];
        arr[pos2] = temp;
    }
}
