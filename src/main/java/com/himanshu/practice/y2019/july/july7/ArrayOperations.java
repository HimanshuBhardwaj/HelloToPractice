package com.himanshu.practice.y2019.july.july7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 07/07/19.
 */
public class ArrayOperations {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");
        double[] elements = new double[str.length];
        for (int i = 0; i < elements.length; i++) {
            elements[i] = Double.parseDouble(str[i]);
        }

        System.out.println(ArrayUtils.binarySearch(elements, -1.4));

    }
}


class ArrayUtils {
    //11:15 pm--11:25
    static Integer binarySearch(double[] arr, double element) {
        return binarySearchHelper(0, arr.length - 1, arr, element);
    }

    private static Integer binarySearchHelper(int start, int end, double[] arr, double element) {
        if (start > end || start < 0 || end >= arr.length) {
            return null;
        }
        if (start == end) {
            return (Double.compare(arr[start], element) == 0) ? start : null;
        }

        if (start + 1 == end) {
            //only two elements
            if (Double.compare(arr[start], element) == 0) {
                return start;
            } else if (Double.compare(arr[end], element) == 0) {
                return end;
            } else {
                return null;
            }
        }

        int mid = (start + end) / 2;

        if (Double.compare(arr[mid], element) == 0) {
            return mid;
        } else if (Double.compare(arr[mid], element) == -1) {
            return binarySearchHelper(mid + 1, end, arr, element);
        } else {
            return binarySearchHelper(start, mid - 1, arr, element);
        }
    }
}
/*
*

-1.5 -1.4 -1.2 -1.1 -1 -0.5 0.0 0.59 0.599 0.5999 0.600000001 0.61 0.611 1.5 1.6 2 2.5
*
*
* */