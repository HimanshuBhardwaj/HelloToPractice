package com.himanshu.practice.nov.nov13;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 13/11/18.
 * maximum j-i such that A[j]>A[i]
 * 4:00
 * time complexity is O(n) :p :p
 */
public class SegmentTreeApplication {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = 4;

        int arr[] = new int[n];
        arr[0] = 10;
        arr[1] = -100;
        arr[2] = 1000;
        arr[3] = -1;
//        arr[2] = -120;
//        arr[3] = 11;
//        arr[4] = -1000;

        SegmentTree st = new SegmentTree(arr);

        int pos = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            int output = st.findLastGreaterIndex(0, st.segTree.length / 2, i, 0);
            System.out.println(i + "\t\t" + arr[i] + "\t\t" + output);

            if (output != Integer.MIN_VALUE) {
                pos = Math.max(Math.abs(output - i), pos);
            }
        }

        System.out.println("Result\t\t" + pos);


    }
}


class SegmentTree {
    int[] segTree;
    int end;

    public SegmentTree(int[] arr) {
        int size = (int) (Math.pow(2, Math.ceil(Math.log(arr.length) / Math.log(2))));
        size = size + size - 1;
        segTree = new int[size];

        for (int i = 0; i <= size / 2; i++) {
            if (i < arr.length) {
                segTree[i + (size / 2)] = arr[i];
                end = i + (size / 2);
            } else {
                segTree[i] = Integer.MIN_VALUE;
            }
        }

        for (int i = (size / 2 - 1); i >= 0; i--) {
            segTree[i] = Math.max(segTree[2 * i + 1], segTree[2 * i + 2]);
        }
    }


    //this will return the last index of element which is just greater then the index
    int findLastGreaterIndex(int segStart, int segEnd, int cP, int index) {
        if (segStart < 0 || segEnd < segStart || index > end || segTree[index] <= segTree[(segTree.length / 2) + cP] || segEnd <= cP) {
            return Integer.MIN_VALUE;
        }

        if (segEnd == segStart) {
            return segEnd;
        }


        int mid = (segStart + segEnd) / 2;
        return Math.max(findLastGreaterIndex(segStart, mid, cP, 2 * index + 1),
                findLastGreaterIndex(mid + 1, segEnd, cP, 2 * index + 2));
    }
}
