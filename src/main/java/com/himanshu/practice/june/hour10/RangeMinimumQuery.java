package com.himanshu.practice.june.hour10;

/**
 * Created by Himanshu Bhardwaj on 25/06/18.
 */
public class RangeMinimumQuery {
    public static void main(String[] args) {
        int size = 5;
        int arr[] = new int[size];


        for (int i = 0; i < size; i++) {
            arr[size - i - 1] = ((i + 1) * (i + 3) - 5) % (3 * size - 1);
        }
        System.out.println("Orignal array");
        for (int i = 0; i < size; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        SegmentTree segmentTree = new SegmentTree(arr);
        segmentTree.print();
        System.out.println();
        System.out.println(segmentTree.rangeMinimum(2, 4));
        segmentTree.changeElement(4, 1);
        segmentTree.print();
        System.out.println(segmentTree.rangeMinimum(2, 4));


    }
}


class SegmentTree {
    int segmentTree[];
    int lastPos;


    public SegmentTree(int[] arr) {
        int size = (int) Math.pow(2, Math.ceil(Math.log(arr.length) / Math.log(2)));
        size = 2 * size - 1;
        segmentTree = new int[size];

        for (int i = 0; i <= segmentTree.length / 2; i++) {
            if (i < arr.length) {
                segmentTree[i + (segmentTree.length / 2)] = arr[i];
                lastPos = i + (segmentTree.length / 2);
            } else {
                segmentTree[i + (segmentTree.length / 2)] = Integer.MAX_VALUE;
            }
        }
        print();

        System.out.println();
        System.out.println("last Pos: " + lastPos);
        System.out.println("segment Tree size: " + segmentTree.length);
        for (int i = (segmentTree.length / 2 - 1); i >= 0; i--) {
            segmentTree[i] = Math.min(segmentTree[2 * i + 1], segmentTree[2 * i + 2]);
        }
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
