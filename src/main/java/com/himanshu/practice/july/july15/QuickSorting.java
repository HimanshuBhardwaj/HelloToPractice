package com.himanshu.practice.july.july15;

/**
 * Created by Himanshu Bhardwaj on 15/06/18.
 */
public class QuickSorting {
    public static void main(String[] args) {
        int size = 51;
        int arr[] = new int[size];

        for (int i = 0; i < size; i++) {
            arr[i] = ((i + 1) * (i - 2) + i * i * i + 321) % 311;
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        QuickSort quickSort = new QuickSort(arr);
        quickSort.sort(0, quickSort.arr.length - 1);
        quickSort.print();
    }
}


class QuickSort {
    int arr[];

    public QuickSort(int[] qArr) {
        arr = qArr;
    }

    void sort(int start, int end) {
        if (arr == null) {
            System.out.println("no element");
            return;
        }
        if (start > end || start == end || end >= arr.length) {
            return;
        }
        if ((start + 1) == end) {
            if (arr[start] > arr[end]) {
                swap(start, end);
            }
        }


        //more than one element;

        int pivot = getPivot(start, end);
        int left = start - 1;

        //all elements right of left are greater than pivot
        //it assumes that pivot element is at the end
        for (int j = start; j < end; j++) {
            if (arr[j] < arr[pivot]) {
                swap(left + 1, j);
                left++;
            }
        }

        swap(pivot, left + 1);
        sort(start, left);
        sort(left + 2, end);
    }

    public void swap(int start, int end) {
        int temp = arr[start];
        arr[start] = arr[end];
        arr[end] = temp;
    }

    //coud be changed in future
    private int getPivot(int start, int end) {
        return end;
    }

    public void print() {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}