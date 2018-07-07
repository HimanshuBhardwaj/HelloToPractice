package com.himanshu.practice.june11.hour9;

/**
 * Created by Himanshu Bhardwaj on 11/06/18.
 * 10.04 am
 * completed 10:50
 */
public class RangeMinimumQuery {
    public static void main(String[] args) {
        int size = 15;
        int arr[] = new int[size];


        for (int i = 0; i < size; i++) {
            arr[i] = ((i + 1) * i + 3 * i + 19) % (2 * size + 3);
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        SegmentTree segmentTree = new SegmentTree(arr);
        System.out.println("Minimum value: " + segmentTree.getMinimum(5, 5));
    }


}


class SegmentTree {
    int segMentArr[];
    int segmentArrSize;
    int endPos = -1; //this endpos in segmentArr is included
    int normalArrayendPos; //this is from [0,normalArrayendPos], both included


    public SegmentTree(int arr[]) {
        normalArrayendPos = arr.length;
        segmentArrSize = (int) Math.pow(2d, Math.ceil(Math.log(arr.length) / Math.log(2)));
        segmentArrSize = 2 * segmentArrSize - 1;
        segMentArr = new int[segmentArrSize];

        for (int i = 0; i < (segmentArrSize + 1) / 2; i++) {
            int offSet = (segmentArrSize + 1) / 2 - 1;
            if (i < arr.length) {
                segMentArr[offSet + i] = arr[i];
            } else {
                if (endPos == -1) {
                    endPos = offSet + i - 1;
                }
                segMentArr[offSet + i] = Integer.MAX_VALUE; //because this is rance minimum query
            }
        }
        for (int i = segmentArrSize / 2 - 1; i >= 0; i--) {
            segMentArr[i] = Math.min(segMentArr[2 * i + 1], segMentArr[2 * i + 2]);
        }
        System.out.println("Array Size: " + arr.length);
        System.out.println("Segment Array Size:" + segmentArrSize);
        for (int i : segMentArr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public int getMinimum(int start, int end) {
        if (start > end || start < 0 || end > normalArrayendPos) {
            System.out.println("wrong range");
            return Integer.MAX_VALUE;
        }
        return getMinimumHelper(0, segmentArrSize / 2, start, end, 0);
    }

    private int getMinimumHelper(int segmentRangStart, int segmentRangeEnd, int start, int end, int segmentTreeIndex) {
        if (segmentRangStart < 0 || segmentRangeEnd > segmentArrSize || segmentRangStart > segmentRangeEnd ||
                segmentRangStart > end || segmentRangeEnd < start || segmentTreeIndex > endPos) {
            return Integer.MAX_VALUE;
        }

        if (start <= segmentRangStart && end >= segmentRangeEnd) {
            return segMentArr[segmentTreeIndex];
        }

        int mid = segmentRangStart + (segmentRangeEnd - segmentRangStart) / 2;

        return Math.min(getMinimumHelper(segmentRangStart, mid, start, end, 2 * segmentTreeIndex + 1),
                getMinimumHelper(mid + 1, segmentRangeEnd, start, end, 2 * segmentTreeIndex + 2));
    }
}