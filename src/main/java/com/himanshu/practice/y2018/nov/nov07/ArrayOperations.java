package com.himanshu.practice.y2018.nov.nov07;

import java.util.Arrays;

/**
 * Created by himanshubhardwaj on 07/11/18.
 */
//Binary Search, Ceil in sorted Array, search in increasing then decreasng array

public class ArrayOperations {
    public static void main(String[] args) {
        int n = 7;
        int[] arr = new int[n];


        for (int i = 0; i < n; i++) {
            arr[i] = (i * i + 3) * (4 - i);

        }

        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        System.out.println(exactBinarySearch(0, arr.length - 1, arr, 12));
        System.out.println(ceil(0, arr.length - 1, arr, -1000));


        arr[0]=0;arr[1]=1;arr[2]=5;arr[3]=10;
        arr[4]=-10;arr[5]=-9;arr[6]=-5;

        System.out.println(rotatedSearch(0,arr.length-1,arr,5));


    }


    static Integer exactBinarySearch(int start, int end, int[] arr, int element) {
        if (start < 0 || end >= arr.length || start > end) {
            return null;
        }

        if (start == end) {
            if (arr[start] == element) {
                return start;
            } else {
                return null;
            }
        }


        int mid = (start + end) / 2;

        if (arr[mid] == element) {
            return mid;
        }
        Integer leftPos = exactBinarySearch(start, mid - 1, arr, element);
        if (leftPos != null) {
            return leftPos;
        }

        return exactBinarySearch(mid + 1, end, arr, element);
    }


    static Integer ceil(int start, int end, int[] arr, int element) {
        if (start < 0 || end >= arr.length || start > end || element > arr[end]) {
            return null;
        }

        if (start == end) {
            return arr[start];
        }

        if (start + 1 == end) {
            if (arr[start] >= element) {
                return arr[start];
            } else {
                return arr[end];
            }
        }

        int mid = (start + end) / 2;

        if (arr[mid] >= element) {
            if (arr[mid - 1] >= element) {
                return ceil(start, mid - 1, arr, element);
            } else {
                return arr[mid];
            }
        } else {
            return ceil(mid + 1, end, arr, element);
        }
    }

    //TODO: Correct it; not correct
    static Integer rotatedSearch(int start, int end, int[] arr, int element) {
        if (start < 0 || end >= arr.length || start > end) {
            return null;
        }

        if (start == end) {
            if (arr[start] == element) {
                return start;
            } else {
                return null;
            }
        }

        if (start == (end - 1)) {
            if (arr[start] == element) {
                return start;
            } else if (arr[end] == element) {
                return end;
            } else {
                return null;
            }
        }

        int mid = (start + end) / 2;

        if (arr[mid] == element) {
            return mid;
        }

        if (arr[mid] >= arr[start]) {
            if (element > arr[mid]) {
                return rotatedSearch(mid + 1, end, arr, element);
            }
            if (element < arr[mid]) {
                return rotatedSearch(mid + 1, end, arr, element);
            } else {
                return rotatedSearch(start, mid - 1, arr, element);
            }
        } else {
            if (element > arr[mid]) {
                if (element >= arr[start]) {
                    return rotatedSearch(start, mid - 1, arr, element);
                } else {
                    return rotatedSearch(mid + 1, end, arr, element);
                }
            } else {
                return rotatedSearch(start, mid - 1, arr, element);
            }
        }
    }
}
