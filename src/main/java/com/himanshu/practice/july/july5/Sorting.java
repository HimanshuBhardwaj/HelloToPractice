package com.himanshu.practice.july.july5;

/**
 * Created by Himanshu Bhardwaj on 05/07/18.
 * Mergesort:  3:51 --> 4:05
 * Quick Sort, insertionSort:  4:07 --> 4:31
 * rotationPivot 4:32 -->5:12 --> needs improvement
 * binary search 5:12 --> 5:20
 */
public class Sorting {
    public static void main(String[] args) {
        int arr[] = new int[23];


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

        int[] arrInsert = arr.clone();
        InsertionSort.sort(arrInsert);
        System.out.println();
        for (int i = 0; i < arrInsert.length; i++) {

            System.out.print(arrInsert[i] + " ");
        }
        System.out.println();
        System.out.println("Rotation time");


        for (int i = 0; i < arr.length; i++) {
            arr[i] = i * i;
        }
//        arr[0] = 0;
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }

        System.out.println();
        System.out.println(Array.rotationPoint(arr));
        System.out.println(Array.binarySearch(0,arr.length-1,arr,121));

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

    public static void swap(int pos1, int pos2, int[] arr) {
        int temp = arr[pos1];
        arr[pos1] = arr[pos2];
        arr[pos2] = temp;
    }
}

class InsertionSort {
    public static void sort(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[i]) {
                    QuickSort.swap(i, j, arr);
                }
            }
        }
    }
}

class Array {
    public static int rotationPoint(int arr[]) {
        return rotationPointHelper(0, arr.length - 1, arr);
    }

    //rotation point is always inside the segment and in case of equality it is start
    private static int rotationPointHelper(int start, int end, int[] arr) {
        if (start > end || start == end) {
            return 0;
        }
        if (start == end - 1) {
            if (arr[end] < arr[start]) {
                return end;
            } else {
                return start;
            }
        }

        int mid = start + (end - start) / 2;

        if (mid < end) {
            if (arr[mid + 1] < arr[mid]) {
                return mid + 1;
            }
        }
        if (mid > start) {
            if (arr[mid] < arr[mid - 1]) {
                return mid;
            }
        }
        if (arr[mid] > arr[end]) {
            return rotationPointHelper(mid + 1, end, arr);
        } else {
            return rotationPointHelper(start, mid - 1, arr);
        }
    }


    public static int binarySearch(int start, int end, int[] arr, int element) {
        if (start > end) {
            return -1;
        }

        if (start == end) {
            if (arr[start] == element) {
                return start;
            } else {
                return -1;
            }
        }

        int mid = start + (end - start) / 2;

        if (arr[mid] == element) {
            return mid;
        }
        if (arr[mid] < element) {
            return binarySearch(mid + 1, end, arr, element);
        } else {
            return binarySearch(start, mid - 1, arr, element);
        }

    }
}
