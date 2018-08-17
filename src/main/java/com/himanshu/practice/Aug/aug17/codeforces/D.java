//package com.himanshu.practice.Aug.aug17.codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * Created by himanshubhardwaj on 17/08/18.
 * Statement: https://codeforces.com/contest/1023/problem/D
 * Algo: Segment Tree
 * Submission: https://codeforces.com/contest/1023/submission/41730821
 */
public class D {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str[] = br.readLine().split(" ");

        int n = Integer.parseInt(str[0]);
        int k = Integer.parseInt(str[1]);

        //str = br.readLine().split(" ");
        int arr[] = new int[n];
        str = br.readLine().split(" ");


        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(str[i]);
        }

        SegmentTree segmentTree = new SegmentTree(arr, k);

        boolean isBalanced = true;

        for (int i = 1; isBalanced && i <= k; i++) {
            if (SegmentTree.first[i] != 0 && SegmentTree.last[i] != 0) {
                int min = segmentTree.rangeMinimum(SegmentTree.first[i] - 1, SegmentTree.last[i] - 1);
                if (min != Integer.MAX_VALUE && min < i) {
                    isBalanced = false;
                }
            }
        }

        if (SegmentTree.globalMax != k && SegmentTree.globalMin != 0) {
            isBalanced = false;
        }

        if (isBalanced) {
            int pos = arr.length - 1;
            if (SegmentTree.globalMax != k) {
                for (int i = arr.length - 1; i >= 0; i--) {
                    if (arr[i] == Integer.MAX_VALUE) {
                        pos = i;

                        while (pos >= 0 && arr[pos] == Integer.MAX_VALUE) {
                            arr[pos] = k;
                            pos--;
                        }
                        break;
                    }
                }
            }

            Stack<Integer> stack = new Stack<>();
            int last = k;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == Integer.MAX_VALUE) {
                    stack.push(i);
                } else if (!stack.isEmpty()) {
                    while (!stack.isEmpty()) {
                        pos = stack.pop();
                        arr[pos] = arr[i];
                    }
                }

                if (arr[i] != Integer.MAX_VALUE) {
                    last = arr[i];
                }
            }

            while (!stack.isEmpty()) {
                pos = stack.pop();
                arr[pos] = last;
            }

        }

        if (isBalanced) {
            System.out.println("YES");
            for (int x : arr) {
                System.out.print(x + " ");
            }
        } else {
            System.out.print("NO");
        }
    }
}

class SegmentTree {
    int segmentTree[];
    int lastPos;
    static int first[];
    static int last[];
    static int globalMax = Integer.MIN_VALUE;
    static int globalMin = Integer.MAX_VALUE;


    public SegmentTree(int[] arr, int k) {
        k++;
        int size = (int) Math.pow(2, Math.ceil(Math.log(arr.length) / Math.log(2)));
        size = 2 * size - 1;
        segmentTree = new int[size];
        first = new int[k + 1];
        last = new int[k + 1];

        for (int i = 0; i <= segmentTree.length / 2; i++) {
            if (i < arr.length) {
                globalMax = Math.max(globalMax, arr[i]);
                globalMin = Math.min(globalMin, arr[i]);
                if (arr[i] == 0) {
                    arr[i] = Integer.MAX_VALUE;
                } else {
                    if (first[arr[i]] == 0) {
                        first[arr[i]] = i + 1;
                    } else {
                        last[arr[i]] = i + 1;
                    }
                }
                segmentTree[i + (segmentTree.length / 2)] = arr[i];
                lastPos = i + (segmentTree.length / 2);
            } else {
                segmentTree[i + (segmentTree.length / 2)] = Integer.MAX_VALUE;
            }
        }
        for (int i = (segmentTree.length / 2 - 1); i >= 0; i--) {
            segmentTree[i] = Math.min(segmentTree[2 * i + 1], segmentTree[2 * i + 2]);
        }

//        print();
//        System.out.println();
//        System.out.println("last Pos: " + lastPos);
//        System.out.println("segment Tree size: " + segmentTree.length);
    }

    public void print() {
        System.out.println("last Pos: " + lastPos);
        System.out.println("segment Tree size: " + segmentTree.length);
        for (int i = 0; i <= lastPos; i++) {
            System.out.print(segmentTree[i] + " ");
        }
        System.out.println();
    }


    public int rangeMinimum(int start, int end) {
        if (start > end) {
            return Integer.MIN_VALUE;
        }
        return rangeMinimumhelper(0, segmentTree.length / 2, start, end, 0);
    }

    private int rangeMinimumhelper(int segStart, int segEnd, int start, int end, int segIndex) {
        if (segEnd < segStart || segEnd < start || segStart > end || segIndex > lastPos) {
            return Integer.MAX_VALUE;
        }


        if (start <= segStart && end >= segEnd) {
            return segmentTree[segIndex];
        }

        int mid = segStart + (segEnd - segStart) / 2;

        return Math.min(rangeMinimumhelper(segStart, mid, start, end, 2 * segIndex + 1), rangeMinimumhelper(mid + 1, segEnd, start, end, 2 * segIndex + 2));
    }


    public void changeElement(int index, int newValue) {
        if (index > (lastPos - (segmentTree.length / 2))) {
            return;
        }

        int diff = newValue - segmentTree[segmentTree.length / 2 + index];
        changeElementHelper(0, segmentTree.length / 2, diff, index, 0);

    }


    private void changeElementHelper(int segStart, int segEnd, int diff, int index, int segIndex) {
        if (segStart > segEnd || segStart > index || segEnd < index || segIndex > lastPos) {
            return;
        }
        if (segEnd == index && segStart == index) {
            segmentTree[segIndex] += diff;
            return;
        }

        int mid = segStart + (segEnd - segStart) / 2;

        changeElementHelper(segStart, mid, diff, index, 2 * segIndex + 1);
        changeElementHelper(mid + 1, segEnd, diff, index, 2 * segIndex + 2);
        segmentTree[segIndex] = Math.min(segmentTree[2 * segIndex + 1], segmentTree[2 * segIndex + 2]);
    }

}

