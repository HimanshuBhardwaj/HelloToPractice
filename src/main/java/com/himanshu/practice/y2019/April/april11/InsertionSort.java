package com.himanshu.practice.y2019.April.april11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Created by himanshubhardwaj on 11/04/19.
 */
public class InsertionSort {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");
        int[] arr = new int[str.length];


        for (int i = 0; i < str.length; i++) {
            arr[i] = Integer.parseInt(str[i]);
        }

        int[] sortedAray = ArrayT.insertionSort(arr);

        for (int x : sortedAray) {
            System.out.print(x + " ");
        }
        System.out.println();

    }
}

class ArrayT {

    public static int[] insertionSort(int[] arr) {


        for (int i = 0; i < arr.length; i++) {
            int k = i;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[k] >= arr[j]) {
                    break;
                } else {
                    int temp = arr[k];
                    arr[k] = arr[j];
                    arr[j] = temp;
                    k--;
                }
            }
        }
        return arr;
    }
}