package com.himanshu.practice.y2019.April.april15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 14/04/19.
 */
public class Sorting {
    public static void main(String[] arr) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");
        int[] array = new int[str.length];
        for (int i = 0; i < array.length; i++) {
            array[i] = Integer.parseInt(str[i]);
        }

        new QSort().sort(array);
        for (int x : array) {
            System.out.print(x + ", ");
        }
        System.out.println();
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
        System.out.println("Pivot:"+pi);


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