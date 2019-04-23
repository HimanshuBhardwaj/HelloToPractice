package com.himanshu.practice.y2019.April.april15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Created by himanshubhardwaj on 14/04/19.
 * MergeSort: 10:01 -10:15
 */
public class Sorting {
    public static void main(String[] arr) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");
        int[] array = new int[str.length];
        for (int i = 0; i < array.length; i++) {
            array[i] = Integer.parseInt(str[i]);
        }

        array = new MergeSort().sort(array);
        for (int x : array) {
            System.out.print(x + ", ");
        }
        System.out.println();
    }
}


class MergeSort {
    public int[] sort(int arr[]) {
        return sortHelper(arr, 0, arr.length - 1);
    }

    private int[] sortHelper(int[] arr, int start, int end) {
        if (start == end || arr == null) {
            return arr;
        }

        if (start + 1 == end) {
            Arrays.sort(arr);
        }

        int mid = (start + end) / 2;
        int[] A = new int[mid - start + 1];
        int[] B = new int[(end - start + 1) - (mid - start + 1)];


        for (int i = 0; i < A.length; i++) {
            A[i] = arr[i];
        }

        for (int i = 0; i < B.length; i++) {
            B[i] = arr[A.length + i];
        }

        A= sortHelper(A, 0, A.length - 1);
        B = sortHelper(B, 0, B.length - 1);
        //Arrays.sort(A);
        //Arrays.sort(B);

        return merge(A, B);

    }

    private int[] merge(int[] a, int[] b) {
        System.out.print("Merging: ");
        for (int x : a) {
            System.out.print(x + ",");
        }

        System.out.print("\t\t");
        for (int x : b) {
            System.out.print(x + ",");
        }

//        System.out.println();
        int pA = 0;
        int pB = 0;
        int result[] = new int[a.length + b.length];
        int i = 0;

        while (pA < a.length && pB < b.length) {
            if (a[pA] < b[pB]) {
                result[i++] = a[pA++];
            } else {
                result[i++] = b[pB++];
            }
        }


        while (pA < a.length) {
            result[i++] = a[pA++];
        }
        while (pB < b.length) {
            result[i++] = b[pB++];
        }

        System.out.print("\t\t\t Result: ");
        for (int x : result) {
            System.out.print(x + ", ");
        }
        System.out.println();


        return result;
    }

}


class QSort {


    public void sort(int arr[]) {
        sortHelper(arr, 0, arr.length - 1);
    }


    private void sortHelper(int[] arr, int start, int end) {
        if (start >= end || start < 0 || end >= arr.length) {
            return;
        }

        if (start == end - 1) {
            if (arr[end] < arr[start]) {
                int temp = arr[end];
                arr[end] = arr[start];
                arr[start] = temp;
            }
            return;
        }

        int partitionKey = getPArtitionIndex(start, end);
        int pos = partition(start, end, partitionKey, arr);
        sortHelper(arr, start, pos - 1);
        sortHelper(arr, pos + 1, end);
    }

    private int partition(int start, int end, int partitionKey, int[] arr) {
        System.out.println("--------------------Before partition --------------------");
        int pivot = partitionKey;
        System.out.println(arr[pivot]);
        for (int i = start; i <= end; i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.println();


        swapElements(arr, end, pivot);
        System.out.println(arr[end]);


        int pi = start - 1;

        for (int j = start; j < end; j++) {
            if (arr[j] <= arr[end]) {
                pi++;
                swapElements(arr, pi, j);
            }
        }

        pi++;
        swapElements(arr, pi, end);
        System.out.println("Pivot:" + pi);


        for (int i = start; i <= end; i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.println();
        System.out.println("--------------------End partition --------------------");
        return pi;
    }

    private void swapElements(int[] arr, int end, int start) {
        try {
            //System.out.println("Before: "+arr[end]+", "+arr[start]);
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            //System.out.println("After: "+arr[end]+", "+arr[start]);
        } catch (Exception e) {
            System.out.println(String.format("Wrong  start: %d  end:%d  size:%d ", end, start, arr.length));
            throw e;
        }

    }

    private int getPArtitionIndex(int start, int end) {
        return (start + end) / 2;
    }

}