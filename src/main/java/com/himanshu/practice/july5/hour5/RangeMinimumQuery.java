package com.himanshu.practice.july5.hour5;

import javax.swing.text.Segment;

import static jdk.nashorn.internal.objects.Global.print;

/**
 * Created by Himanshu Bhardwaj on 05/07/18.
 * 5:48 -- 6:38
 * 20 mins could have reduced
 */
public class RangeMinimumQuery {
    public static void main(String[] args) {
        int size = 7;
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (i - 1) * (i - 5) + 11;
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        SegmentTree segmentTree = new SegmentTree(arr);
        System.out.println(segmentTree.rangeMinimum(1, 6));
        segmentTree.update(5, 5);
        segmentTree.update(6, 4);
        segmentTree.update(2, 3);
        segmentTree.print();
        System.out.println(segmentTree.rangeMinimum(1, 6));

    }
}


class SegmentTree {
    int[] segmentTree;
    int endPos;


    public SegmentTree(int[] arr) {
        int size = (int) Math.pow(2d, Math.ceil(Math.log(arr.length) / Math.log(2)));
        size = 2 * size - 1;
        segmentTree = new int[size];

        for (int i = 0; i <= segmentTree.length / 2; i++) {
            if (i < arr.length) {
                segmentTree[segmentTree.length / 2 + i] = arr[i];
                endPos = segmentTree.length / 2 + i;
            } else {
                segmentTree[segmentTree.length / 2 + i] = Integer.MAX_VALUE;
            }
        }

        for (int i = segmentTree.length / 2 - 1; i >= 0; i--) {
            segmentTree[i] = Math.min(segmentTree[2 * i + 1], segmentTree[2 * i + 2]);
        }

        print(segmentTree);
    }


    public void print(int[] segmentTree) {
        System.out.println("Size: " + segmentTree.length);
        for (int i : segmentTree) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public void print() {
        print(segmentTree);
    }


    public int rangeMinimum(int start, int end) {
        return rangeMinimumHelper(0, segmentTree.length / 2, start, end, 0);
    }

    private int rangeMinimumHelper(int segStart, int segEnd, int start, int end, int segIndex) {
        if (segStart > segEnd || segEnd < start || segStart > end || segIndex > segmentTree.length || end < start) {
            return Integer.MAX_VALUE;
        }

        if (segStart >= start && segEnd <= end) {
            return segmentTree[segIndex];
        }


        int mid = segStart + (segEnd - segStart) / 2;

        return Math.min(rangeMinimumHelper(segStart, mid, start, end, 2 * segIndex + 1),
                rangeMinimumHelper(mid + 1, segEnd, start, end, 2 * segIndex + 2));
    }

    public void update(int pos, int newValue) {
        if (pos > (endPos - segmentTree.length / 2)) {
            return;
        }

        int diff = newValue - segmentTree[segmentTree.length / 2 + pos];
        updateHelper(0, segmentTree.length / 2, pos, diff, 0);
    }

    private void updateHelper(int segStart, int segEnd, int pos, int diff, int segIndex) {
        if (segStart > segEnd || pos > segEnd || pos < segStart || segIndex > endPos) {
            return;
        }

        if (segStart == pos && pos == segEnd) {
            segmentTree[segIndex] += diff;
            return;
        }


        int mid = segStart + (segEnd - segStart) / 2;

        updateHelper(segStart, mid, pos, diff, 2 * segIndex + 1);
        updateHelper(mid + 1, segEnd, pos, diff, 2 * segIndex + 2);

        segmentTree[segIndex] = Math.min(segmentTree[2 * segIndex + 1], segmentTree[2 * segIndex + 2]);

    }
}
