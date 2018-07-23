package com.himanshu.practice.july.july10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Himanshu Bhardwaj on 10/07/18.
 * Both nlogN and n^2
 */
public class LongestIncreasingSubsequence {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str[] = br.readLine().split(",");
        int[] arr = new int[str.length];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(str[i]);
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
//        System.out.println(getCeil(0,arr.length-1,4,arr));

        System.out.println(LISN2(arr));
        System.out.println(LISnLOGn(arr));

    }


    private static int LISnLOGn(int[] arr) {
        int LIS[] = new int[arr.length];
        LIS[0] = arr[0];
        int pos = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < LIS[0]) {
                LIS[0] = arr[i];
            } else if (arr[i] >= LIS[pos]) {
                LIS[++pos] = arr[i];
            } else {
                //it is somewhere inbetween LIS[0] and LIS[pos]
                LIS[getCeil(0, pos, arr[i], LIS)] = arr[i];
            }
        }

        System.out.print("NLogN:\t");
        for (int i = 0; i <= pos; i++) {
            System.out.print(LIS[i] + ", ");
        }
        System.out.println();
        return pos + 1;
    }

    //this function will return liwest index with value greater than value
    //note: even if there are values with value equal to value, we will return index with value greater than value
    //also it is asured that arr[start]<= value < arr[end]
    private static int getCeil(int start, int end, int value, int[] arr) {
        if (start == end) {
            return end;
        }
        if (start + 1 == end) {
            if (arr[start] > value) {
                return start;
            } else {
                return end;
            }
        }
        if (arr[start] > value) {
            return start;
        }


        int mid = start + (end - start) / 2;
        if (arr[mid] > value) {
            return getCeil(start, mid, value, arr);
        } else {
            return getCeil(mid + 1, end, value, arr);
        }
    }


    private static int LISN2(int[] arr) {
        int LIS[] = new int[arr.length];
        int maxV = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            LIS[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[i] >= arr[j]) {
                    LIS[i] = Math.max(LIS[i], 1 + LIS[j]);
                    maxV = Math.max(LIS[i], maxV);
                }
            }
        }
        System.out.print("N^2\t");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(LIS[i] + ", ");
        }
        System.out.println();

        return maxV;
    }
}
//1 2 -23 2 -1 4 1 -3 3 3 3 4 5 7
