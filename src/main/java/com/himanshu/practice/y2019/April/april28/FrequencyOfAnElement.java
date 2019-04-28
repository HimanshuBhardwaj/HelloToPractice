package com.himanshu.practice.y2019.April.april28;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 28/04/19.
 * 2:09 pm
 * 20 mins
 */
public class FrequencyOfAnElement {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int number = Integer.parseInt(br.readLine());
        String str[] = br.readLine().split(" ");
        int arr[] = new int[str.length];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(str[i]);
        }

        System.out.print(ArrayUtilsSelf.getFrequency(arr, number));


    }
}


class ArrayUtilsSelf {
    public static int getFrequency(int[] arr, int element) {
        int posR = lastPosition(arr, 0, arr.length - 1, element);
        System.out.println("posR: " + posR);

        if (posR == -1) {
            return 0;
        }

        int posL = firstPosition(arr, 0, arr.length - 1, element);
        System.out.println("posL: " + posL);
        //this can't be -1

        return posR - posL + 1;


    }

    //this array will return last position of index.
    private static int lastPosition(int[] arr, int start, int end, int element) {
        if (start > end) {
            return -1;
        }

        if (arr[end] == element) {
            return end;
        }

        if (start == end) {
            return -1;
        }


        if ((start + 1) == end) {
            if (arr[start] == element) {
                return start;
            }
            return -1;
        }


        int mid = start + (end - start) / 2;

        if (arr[mid] == element) {
            return lastPosition(arr, mid, end, element);
        } else if (arr[mid] > element) {
            return lastPosition(arr, start, mid - 1, element);
        } else {
            return lastPosition(arr, mid + 1, end, element);
        }
    }

    private static int firstPosition(int[] arr, int start, int end, int element) {
        if (start > end) {
            return -1;
        }
        System.out.println(start + "\t\t" + end + "\t\t" + element);
        for (int i = start; i <= end; i++) {
            System.out.print(arr[i] + ",");
        }
        System.out.println();
        System.out.println(".......");


        if (arr[start] == element) {
            return start;
        }

        if (start == end) {
            return -1;
        }


        if ((start + 1) == end) {
            if (arr[end] == element) {
                return end;
            }
            return -1;
        }

        int mid = start + (end - start) / 2;

        if (arr[mid] == element) {
            return firstPosition(arr, start, mid, element);
        } else if (arr[mid] > element) {
            return firstPosition(arr, start, mid - 1, element);
        } else {
            return firstPosition(arr, mid + 1, end, element);
        }


    }


}