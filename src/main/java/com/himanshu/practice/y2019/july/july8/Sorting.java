package com.himanshu.practice.y2019.july.july8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Created by himanshubhardwaj on 08/07/19.
 */
public class Sorting {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");
        double[] arrBubble = new double[str.length];
        double[] arrInsertion = new double[str.length];
        double[] arrMerge = new double[str.length];

        for (int i = 0; i < str.length; i++) {
            arrBubble[i] = Double.parseDouble(str[i]);
            arrInsertion[i] = arrBubble[i];
            arrMerge[i] = arrBubble[i];
        }

        SortingUtils.bubbleSort(arrBubble);
        SortingUtils.insertionSort(arrInsertion);
        SortingUtils.mergeSort(arrMerge);

        printArray(arrBubble);
        printArray(arrInsertion);
        printArray(arrMerge);
    }


    static void printArray(double[] arr) {

        for (double x : arr) {
            System.out.print(x + "\t");
        }
        System.out.println();
    }
}


class SortingUtils {
    static void bubbleSort(double[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (Double.compare(array[j], array[j + 1]) == 1) {
                    double temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    static void insertionSort(double[] arr) {
        if (arr == null || arr.length == 0 || arr.length == 1) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            Double element = arr[i];
            for (int j = i - 1; j >= 0; j--) {
                if (Double.compare(arr[j], element) == 1) {
                    arr[j + 1] = arr[j];
                } else {
                    arr[j + 1] = element;
                    element = null;
                    break;
                }
            }
            if (element != null) {
                arr[0] = element;
            }
        }
    }

    //7:07 pm -- 7:17 pm
    static void mergeSort(double[] arr) {
        if (arr == null || arr.length == 0 || arr.length == 1) {
            return;
        }
        double[] sortedArr = mergeSortHelper(0, arr.length - 1, arr);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = sortedArr[i];
        }
    }

    private static double[] mergeSortHelper(int start, int end, double[] arr) {
        if (arr == null) {
            return arr;
        }

        if (start == end) {
            double[] sortedArray = new double[1];
            sortedArray[0] = arr[start];
            return sortedArray;
        }


        if (start + 1 == end) {
            double[] sortedArray = new double[2];
            sortedArray[0] = arr[start];
            sortedArray[1] = arr[end];
            Arrays.sort(sortedArray);
            return sortedArray;
        }

        int mid = (start + end) / 2;

        double[] sortedLeft = mergeSortHelper(start, mid, arr);
        double[] sortedRight = mergeSortHelper(mid + 1, end, arr);

        return merge(sortedLeft, sortedRight);

    }

    private static double[] merge(double[] sortedLeft, double[] sortedRight) {
        double[] sortedArray = new double[sortedLeft.length + sortedRight.length];
        int lefPos = 0;
        int rightPos = 0;
        int index = 0;

        while (lefPos < sortedLeft.length && rightPos < sortedRight.length) {
            if (sortedLeft[lefPos] < sortedRight[rightPos]) {
                sortedArray[index] = sortedLeft[lefPos];
                lefPos++;
                index++;
            } else {
                sortedArray[index] = sortedRight[rightPos];
                rightPos++;
                index++;
            }
        }

        while (lefPos < sortedLeft.length) {
            sortedArray[index++] = sortedLeft[lefPos++];
        }

        while (rightPos < sortedRight.length) {
            sortedArray[index++] = sortedRight[rightPos++];
        }
        return sortedArray;
    }
}



/*
2 .2 1.00002 .2 1 -1 2 3 4.1 0.6 0.5999 0.599999 0.600001 0.6000000001


 */