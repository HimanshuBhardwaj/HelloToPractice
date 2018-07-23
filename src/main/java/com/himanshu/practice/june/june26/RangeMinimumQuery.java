package com.himanshu.practice.june.june26;

/**
 * Created by Himanshu Bhardwaj on 26/06/18.
 */
public class RangeMinimumQuery {
    public static void main(String[] args) {
        int size = 7;
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * 1001d);
            System.out.print(arr[i] + " ");
        }

        SegmentTree segmentTree = new SegmentTree(arr);
        System.out.println(segmentTree.rangeMinimum(2, 5));
        segmentTree.update(4,333);
        segmentTree.print();
        System.out.println(segmentTree.rangeMinimum(2, 5));



    }
}


class SegmentTree {
    int[] segmentTree;
    int endNode;


    public SegmentTree(int[] arr) {
        int size = (int) Math.pow(2, Math.ceil(Math.log(arr.length) / Math.log(2)));
        size = 2 * size - 1;

        segmentTree = new int[size];

        for (int i = 0; i < (segmentTree.length + 1) / 2; i++) {
            if (i < arr.length) {
                segmentTree[i + segmentTree.length / 2] = arr[i];
                endNode = i + segmentTree.length / 2;
            } else {
                segmentTree[i + segmentTree.length / 2] = Integer.MAX_VALUE;
            }
        }


        for (int i = (segmentTree.length / 2) - 1; i >= 0; i--) {
            int child1 = 2 * i + 1;
            int child2 = 2 * i + 2;
            segmentTree[i] = Math.min(segmentTree[child1], segmentTree[child2]);
        }
        print();
    }

    public void print() {
        System.out.println("Segment Tree size: " + segmentTree.length);
        System.out.println("Segment Tree end: " + endNode);

        for (int i = 0; i < segmentTree.length; i++) {
            System.out.print(segmentTree[i] + " ");
        }
        System.out.println();
    }

    public int rangeMinimum(int start, int end) {
        if (start < 0 || end > (endNode - segmentTree.length / 2)) {
            return Integer.MAX_VALUE;
        }
        return rangeMinimumHelper(0, segmentTree.length / 2, start, end, 0);
    }

    private int rangeMinimumHelper(int segStart, int segEnd, int start, int end, int segIndex) {
        if (segEnd < segStart || segEnd < start || segStart > end || segIndex > endNode) {
            return Integer.MAX_VALUE;
        }

        if (start <= segStart && end >= segEnd) {
            return segmentTree[segIndex];
        }
        int mid = start + (end - start) / 2;

        return Math.min(rangeMinimumHelper(segStart, mid, start, end, 2 * segIndex + 1),
                rangeMinimumHelper(mid + 1, end, start, end, 2 * segIndex + 2));
    }

    public void update(int index, int value) {
        if (index > (endNode - segmentTree.length / 2)) {
            return;
        }
        int diff = value - segmentTree[segmentTree.length / 2 + index];
        updateHelper(0, segmentTree.length / 2, diff, index, 0);
    }

    private void updateHelper(int segStart, int segmentEnd, int diff, int index, int segmentIndex) {
        if (segmentEnd < segStart || segmentIndex > endNode) {
            return;
        }
        if (index >= segStart && index <= segmentEnd) {
            segmentTree[segmentIndex] += diff;
        }
        if (segStart == segmentEnd) {
            //leaf
            return;
        }


        int mid = segStart + (segmentEnd - segStart) / 2;
        updateHelper(segStart, mid, diff, index, 2 * segmentIndex + 1);
        updateHelper(mid + 1, segmentEnd, diff, index, 2 * segmentIndex + 2);
        segmentTree[segmentIndex] = Math.min(segmentTree[2 * segmentIndex + 2], segmentTree[2 * segmentIndex + 1]);
    }
}
