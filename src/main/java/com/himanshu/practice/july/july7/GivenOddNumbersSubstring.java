package com.himanshu.practice.july.july7;

import java.util.HashSet;
import java.util.Scanner;

/**
 * Created by Himanshu Bhardwaj on 07/07/18.
 *
 * Q: Given an array, find distinct subarrays which contains atmax m odd numbers;
 *      Array size is n;
 *      m: subbarray with maximum m is permitted
 *
 */
public class GivenOddNumbersSubstring {
    private static String[] segmentTree = null;
    private static int lastPos;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        int arr[] = new int[n];
        int leftOdd[] = new int[n];
        HashSet<String> hasReadBefore = new HashSet<String>();

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(sc.nextLine());
            leftOdd[i] = (i == 0) ? (arr[i] % 2) : (leftOdd[i - 1] + (arr[i] % 2));
        }

        int m = Integer.parseInt(sc.nextLine());
        createSegmentTree(arr);
        int count = 0;

        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if ((leftOdd[j] - leftOdd[i] + arr[i] % 2) <= m) {
                    String bruteforce = getStringFromArray(i, j, arr);
                    String segmentTreeResult = getString(0, segmentTree.length / 2, i, j, 0);
                    //System.out.println("(" + i + "," + j + ")\tComparision: " + bruteforce + "\t\t" + segmentTreeResult);
                    if (!hasReadBefore.contains(segmentTreeResult)) {
                        hasReadBefore.add(getStringFromArray(i, j, arr));
                        count++;
                    }
                }
            }
        }
        System.out.println(count);
        System.out.println();
        createSegmentTree(arr);
    }

    private static void createSegmentTree(int[] arr) {
        int size = (int) Math.pow(2d, Math.ceil(Math.log(arr.length) / Math.log(2)));
        size = size + size - 1;
        segmentTree = new String[size];

        for (int i = 0; i <= segmentTree.length / 2; i++) {
            if (i < arr.length) {
                segmentTree[i + segmentTree.length / 2] = Integer.toString(arr[i]);
                lastPos = i + segmentTree.length / 2;
            } else {
                segmentTree[i + segmentTree.length / 2] = null;
            }
        }

        for (int i = segmentTree.length / 2 - 1; i >= 0; i--) {
            segmentTree[i] = (segmentTree[2 * i + 1] != null) ? segmentTree[2 * i + 1] +
                    ((segmentTree[2 * i + 2] != null) ? "," + segmentTree[2 * i + 2] : "") : null;
        }
        //printSegmentTree();
    }

    private static String getString(int segStart, int segEnd, int start, int end, int segIndex) {
        if (segEnd < segStart || end < start || start > segEnd || end < segStart) {
            return null;
        }


        if (start <= segStart && end >= segEnd) {
            return segmentTree[segIndex];
        }
        if (segStart == segEnd) {
            //leaf
            return null;
        }


        int mid = segStart + (segEnd - segStart) / 2;

        String left = getString(segStart, mid, start, end, 2 * segIndex + 1);
        String right = getString(mid + 1, segEnd, start, end, 2 * segIndex + 2);
        return (left != null) ? ((right != null) ? left + "," + right : left) : ((right != null) ? right : null);
    }

    private static String getStringFromArray(int start, int end, int[] arr) {
        String str = "";

        for (int i = start; i <= end; i++) {
            str = str + Integer.toString(arr[i]);
            if (i != end) {
                str += ",";
            }
        }
        return str;
    }


    private static void printSegmentTree() {
        System.out.println("@printSegmentTree");
        System.out.println(segmentTree.length + "\t" + lastPos);
        System.out.println();
        for (int i = 0; i < segmentTree.length; i++) {
            System.out.println(segmentTree[i]);
        }
    }

}
