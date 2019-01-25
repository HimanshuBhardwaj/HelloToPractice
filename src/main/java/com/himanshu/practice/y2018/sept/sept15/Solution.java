package com.himanshu.practice.y2018.sept.sept15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 15/09/18.
 * find sum of all or of all subArrays.
 */
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        String str[] = br.readLine().split(" ");
        int arr[] = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(str[i]);
        }


        System.out.println(smart(arr));
//        System.out.println(bruteForce(arr));

    }

    static long smart(int arr[]) {
        int n = arr.length;
        int arrBP[][] = new int[n][31];


        for (int j = 0; j <= 30; j++) {
            if (((1 << j) & arr[n - 1]) != 0) {
                arrBP[n - 1][j] = n;
            }
        }


        for (int i = n - 2; i >= 0; i--) {
            for (int j = 30; j >= 0; j--) {
                //System.out.println(i + "\t" + j + "\t" + (1 << j) + "\t" + arr[i] + "\t" + (((1 << j) & arr[i]) != 0));
                if (((1 << j) & arr[i]) != 0) {
                    //System.out.println(i + "\t" + j);
                    arrBP[i][j] = i + 1;
                } else {
                    arrBP[i][j] = arrBP[i + 1][j];
                }
            }
        }


//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j <= 30; j++) {
//                System.out.print(arrBP[i][j] + "\t");
//            }
//            System.out.println();
//        }

        long sum = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= 30; j++) {
                if (arrBP[i][j] != 0) {
                    sum += ((long)(1 << j)) * (n - arrBP[i][j] + 1);
                }
            }
        }

        return sum;

    }

    static long bruteForce(int[] arr) {
        long sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                long t = 0;

                for (int k = i; k <= j; k++) {
                    t = t | arr[k];
                }

//                for (int k = i; k <= j; k++) {
//                    System.out.print(arr[k] + ", ");
//                }
//                System.out.println(t);
                sum += t;
            }
        }
        return sum;
    }


}
