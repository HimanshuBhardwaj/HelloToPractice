package com.himanshu.practice.sept.sept22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 22/09/18.
 * Given an preorder traversal, detect if it could be of bst
 */
public class BSTTraversalValidation {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        while ((t--) > 0) {
            int n = Integer.parseInt(br.readLine());
            String str[] = br.readLine().split(" ");
            int arr[] = new int[n];

            for (int i = 0; i < arr.length; i++) {
                arr[i] = Integer.parseInt(str[i]);
            }

            boolean isValid = isValid(arr, 0, arr.length - 1);
            if (isValid) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    private static boolean isValid(int[] arr, int start, int end) {
        if (start > end || start < 0 || end >= arr.length) {
            return true;
        }

        if (start == end) {
            for (int i = start + 1; i < arr.length; i++) {
                if (arr[i] < arr[start]) {
                    return false;
                }
            }
            return true;
        }

        int root = arr[start];
        int pos = -1;


        for (int i = start + 1; i <= end; i++) {
            if (arr[i] > root) {
                pos = i;
                break;
            }
        }

        if (pos == -1) {
            return true;
        }


        for (int i = pos + 1; i < arr.length; i++) {
            if (arr[i] < root) {
                return false;
            }
        }

        return isValid(arr, start + 1, pos - 1) && isValid(arr, pos, end);
    }
}
