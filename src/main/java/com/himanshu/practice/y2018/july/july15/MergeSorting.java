package com.himanshu.practice.y2018.july.july15;

/**
 * Created by Himanshu Bhardwaj on 15/06/18.
 * //finished
 */
public class MergeSorting {
    public static void main(String[] args) {
        int size = 81;
        int arr[] = new int[size];

        for (int i = 0; i < size; i++) {
            arr[i] = (i * i + (i + 1) * (i + 3)) % 471;
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        MergeSort mergeSort = new MergeSort();
        int[] newArr = mergeSort.mergeSort(0, arr.length - 1, arr);
        System.out.println("newArrSize: " + newArr.length);
        for (int j : newArr) {
            System.out.print(j + " ");
        }
        System.out.println();
    }
}


class MergeSort {

    public int[] mergeSort(int start, int end, int arr[]) {
        if (arr == null) {
            System.out.println("Array is null");
            return arr;
        }
        if (start > end || end >= arr.length) {
            return null;
        }
        if (start == end) {
            int[] newArr = new int[1];
            newArr[0] = arr[start];
            return newArr;
        }
        if ((start + 1) == end) {
            int[] newArr = new int[2];
            if (arr[start] > arr[end]) {
                newArr[0] = arr[end];
                newArr[1] = arr[start];
            } else {
                newArr[0] = arr[start];
                newArr[1] = arr[end];
            }
            return newArr;
        }

        int mid = start + (end - start) / 2;
        int tempArr1[] = mergeSort(start, mid, arr);
        int tempArr2[] = mergeSort(mid + 1, end, arr);
        return merge(tempArr1, tempArr2);
    }

    private int[] merge(int[] array1, int[] array2) {
        int[] arr = new int[array1.length + array2.length];
        int pos1 = 0;
        int pos2 = 0;
        int pos = 0;

        while (pos1 < array1.length && pos2 < array2.length) {
            if (array1[pos1] < array2[pos2]) {
                arr[pos++] = array1[pos1++];
            } else {
                arr[pos++] = array2[pos2++];
            }
        }

        if (array1.length != pos1) {
            for (int i = pos; pos1 < array1.length; i++) {
                arr[i] = array1[pos1++];
                pos = i + 1;
            }
        }

        if (array2.length != pos2) {
            for (int i = pos; pos2 < array2.length; i++) {
                arr[i] = array2[pos2++];
                pos = i + 1;
            }
        }
        System.out.printf("pos:%d\tarr.length: %d\n", pos, arr.length);
        return arr;
    }

    private void swap(int start, int end, int[] arr) {
        int temp = arr[start];
        arr[start] = arr[end];
        arr[end] = temp;
    }

}
