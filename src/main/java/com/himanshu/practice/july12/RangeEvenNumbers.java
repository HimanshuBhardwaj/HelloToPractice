package com.himanshu.practice.july12;

/**
 * Created by himanshubhardwaj on 12/07/18.
 * To print Even numbers in a given range
 * 9:45 -- 11:15
 * could be improved half hour
 */
public class RangeEvenNumbers {
    public static void main(String[] args) {
        int size = 5;
        int[] arr = new int[size];

        for (int i = 0; i < size; i++) {
            arr[i] = ((1 + i) * (211 + i)) % (3 * size - 2);
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        SegmentTree segmentTree = new SegmentTree(arr);
        segmentTree.print();
        System.out.println("(2,4): " + segmentTree.getNumEvenNumbers(2, 4));
        System.out.println("(1,3): " + segmentTree.getNumEvenNumbers(1, 3));
        segmentTree.update(3, 14);
        segmentTree.print();
        System.out.println("(2,4): " + segmentTree.getNumEvenNumbers(2, 4));
        System.out.println("(1,3): " + segmentTree.getNumEvenNumbers(1, 3));

    }

}


class SegmentTree {
    int segMentTree[];
    int endPos;

    public SegmentTree(int arr[]) {
        int size = (int) Math.pow(2d, Math.ceil(Math.log(arr.length) / Math.log(2)));
        size = 2 * size - 1;
        segMentTree = new int[size];

        for (int i = 0; i < segMentTree.length / 2; i++) {
            if (i < arr.length) {
                segMentTree[segMentTree.length / 2 + i] = arr[i] % 2;
                endPos = i + segMentTree.length / 2;
            } else {
                segMentTree[segMentTree.length / 2 + i] = 0;
            }
        }

        for (int i = segMentTree.length / 2 - 1; i >= 0; i--) {
            segMentTree[i] = segMentTree[2 * i + 1] + segMentTree[2 * i + 2];
            //System.out.println("..." + i + " " + (2 * i + 1 - segMentTree.length / 2) + " " + (2 * i + 2 - segMentTree.length / 2)
            //      + " " + segMentTree[i] + " " + segMentTree[2 * i + 1] + " " + segMentTree[2 * i + 2]);
        }
    }


    public int getNumEvenNumbers(int start, int end) {
        if (start < 0 || end > endPos) {
            return 0;
        }
        return getNumEvenNumbersHelper(0, segMentTree.length / 2, start, end, 0);
    }

    private int getNumEvenNumbersHelper(int segStart, int segEnd, int start, int end, int segIndex) {
        if (segStart > segEnd || segStart < 0 || end < segStart || segEnd < start || segIndex > endPos) {
            return 0;
        }
        //System.out.println(segStart + " " + segEnd + " " + segMentTree[segIndex]);

        if (start <= segStart && end >= segEnd) {
            return segMentTree[segIndex];
        }

        if (segEnd == segStart) {
            return 0;
        }

        int mid = segStart + (segEnd - segStart) / 2;

        return getNumEvenNumbersHelper(segStart, mid, start, end, 2 * segIndex + 1) +
                getNumEvenNumbersHelper(mid + 1, segEnd, start, end, 2 * segIndex + 2);
    }


    public void update(int pos, int newValue) {
        if (pos > endPos) {
            return;
        }
        int value = segMentTree[segMentTree.length / 2 + pos];
        if (value == (newValue % 2)) {
            return;
        }
        updateHelper(0, segMentTree.length / 2, pos, newValue, 0);

    }

    private void updateHelper(int segStart, int segEnd, int pos, int value, int segIndex) {
        if (segStart > pos || segEnd < pos || segEnd < segStart) {
            return;
        }
        if (segEnd == segStart) {
            if (segEnd != (pos)) {
                throw new RuntimeException("Something is wrong");
            }
            //leaf
            System.out.println("Value is: " + value);
            segMentTree[segIndex] = value % 2;
            return;
        }

        int mid = segStart + (segEnd - segStart) / 2;
        updateHelper(segStart, mid, pos, value, 2 * segIndex + 1);
        updateHelper(mid + 1, segEnd, pos, value, 2 * segIndex + 2);

        segMentTree[segIndex] = segMentTree[2 * segIndex + 1] + segMentTree[2 * segIndex + 2];
    }

    public void print() {
        System.out.println("SegmentTreeSize: " + segMentTree.length);
        for (int i = 0; i < segMentTree.length; i++) {
            System.out.print(segMentTree[i] + " ");
        }
        System.out.println();
    }
}