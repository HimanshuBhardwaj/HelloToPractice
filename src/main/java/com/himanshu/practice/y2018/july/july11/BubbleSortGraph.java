package com.himanshu.practice.y2018.july.july11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Himanshu Bhardwaj on 11/07/18.
 * Link: https://codeforces.com/contest/340/problem/D
 * Algorithm: LongestIncreasingSubsequence in nLogn Time
 * Submission: https://codeforces.com/contest/340/submission/40181780
 */
public class BubbleSortGraph {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] str = br.readLine().split(" ");
        int arr[] = new int[str.length];

        for (int i = 0; i < str.length; i++) {
            arr[i] = Integer.parseInt(str[i]);
        }

        System.out.print(LISnLogN(arr));

    }

    public static int LISnLogN(int arr[]) {
        int LIS[] = new int[arr.length];
        int pos = 0;
        LIS[pos] = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < LIS[0]) {
                LIS[0] = arr[i];
            } else if (arr[i] >= LIS[pos]) {
                LIS[++pos] = arr[i];
            } else {
                LIS[getCeilIndex(0, pos, LIS, arr[i])] = arr[i];
            }
        }

        return pos + 1;
    }

    //arr[start]<=value<end
    public static int getCeilIndex(int start, int end, int arr[], int value) {
        if (start == end) {
            return end;
        }
        if (start == end - 1) {
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

        if (arr[mid] <= value) {
            return getCeilIndex(mid + 1, end, arr, value);
        } else {
            return getCeilIndex(start, mid, arr, value);
        }
    }
}
