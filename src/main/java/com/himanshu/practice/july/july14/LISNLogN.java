package com.himanshu.practice.july.july14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 14/07/18.
 */
public class LISNLogN {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int[] arr = new int[str.length];
        int LISN2[] = new int[str.length];


        for (int i = 0; i < str.length; i++) {
            LISN2[i] = 1;
            try {
                arr[i] = Integer.parseInt(str[i]);
            } catch (Exception e) {
                System.out.println("Error: " + str[i]);
            }

            for (int j = i - 1; j >= 0; j--) {
                if (arr[i] >= arr[j]) {
                    LISN2[i] = Math.max(LISN2[i], 1 + LISN2[j]);
                }
            }
        }

        int max = LISN2[0];

        for (int i : LISN2) {
            max = Math.max(max, i);
        }

        System.out.println("N^2 C: " + max);
        System.out.println("NlogN solution: " + getMaximumIncreasingSubSeq(arr));


    }

    //arr[start] <= value < arr[end]
    private static int getCeilPosition(int start, int end, int arr[], int value) {
        if (start == end) {
            //System.out.println(start + "\t" + end + "\t" + value + "\t" + start);
            return start;
        }
        if (start == (end - 1)) {
            if (arr[start] > value) {
                //System.out.println(start + "\t" + end + "\t" + value + "\t" + start);
                return start;
            } else {
                //System.out.println(start + "\t" + end + "\t" + value + "\t" + end);
                return end;
            }
        }
        if (value < arr[start]) {
            return start;
        }

        int mid = start + (end - start) / 2;

        if (arr[mid] <= value) {
            return getCeilPosition(mid + 1, end, arr, value);
        } else {
            return getCeilPosition(start, mid, arr, value);
        }
    }

    private static int getMaximumIncreasingSubSeq(int[] arr) {
        int LIS[] = new int[arr.length];
        int pos = 0;
        LIS[pos] = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < LIS[0]) {
                LIS[0] = arr[i];
            } else if (arr[i] >= LIS[pos]) {
                LIS[pos + 1] = arr[i];
                pos++;
            } else {
                LIS[getCeilPosition(0, pos, LIS, arr[i])] = arr[i];
            }
        }


        return pos+1;
    }
}
