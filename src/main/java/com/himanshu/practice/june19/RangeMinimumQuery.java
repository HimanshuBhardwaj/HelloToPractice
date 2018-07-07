package com.himanshu.practice.june19;

/**
 * Created by Himanshu Bhardwaj on 19/06/18.
 * 4:57-5:13
 * 5:23-6:00
 * ~53 mins: Brng it to halp hour
 */
public class RangeMinimumQuery {
    public static void main(String[] args) {
        int size = 6;
        int[] arr = new int[size];

        for (int i = 0; i < size; i++) {
            arr[i] = (3 * i + 2 * (i + 1) + 331) % (4 * size + 1);
        }

        System.out.print("Actual array:\t");
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println("Segment Tree:");
        SegmentTre segmentTre = new SegmentTre(arr);
        segmentTre.printSegmentArray();
        System.out.println();
        System.out.println("GetRangeMinimum 2,4:\t" + segmentTre.getRangeMinimum(2, 5));
        segmentTre.update(5, 2);

        System.out.println();
        System.out.println("New Segment Tree");
        segmentTre.printSegmentArray();
    }
}


class SegmentTre {
    int segmentArray[];
    int segmentArrayend = -1;


    public SegmentTre(int arr[]) {
        int segmentArraySize = (int) Math.pow(2d, (Math.ceil(Math.log(arr.length) / Math.log(2))));
        segmentArraySize = 2 * segmentArraySize - 1;

        segmentArray = new int[segmentArraySize];


        for (int i = 0; i < (segmentArray.length + 1) / 2; i++) {
            if (i < arr.length) {
                segmentArray[i + (segmentArray.length / 2)] = arr[i];
                segmentArrayend = i + (segmentArray.length / 2);
            } else {
                segmentArray[i + (segmentArray.length / 2)] = Integer.MAX_VALUE;
            }
        }

        for (int i = (segmentArraySize / 2) - 1; i >= 0; i--) {
            segmentArray[i] = Math.min(segmentArray[2 * i + 1], segmentArray[2 * i + 2]);
        }
    }


    public void printSegmentArray() {
        for (int i : segmentArray) {
            System.out.print(i + " ");
        }
    }


    public void update(int pos, int newValue) {
        if (pos > (segmentArrayend - segmentArray.length / 2)) {
            System.out.println("invalid");
            return;
        }
        int diff = newValue - segmentArray[segmentArray.length / 2 + pos];
        updateHelper(0, segmentArray.length / 2, pos, diff, 0);
    }

    private void updateHelper(int segTRangeStart, int segTRangeEnd, int pos, int diff, int segmentTreeIndex) {
        if (segTRangeStart < 0 || segTRangeEnd > (segmentArray.length / 2) || segmentTreeIndex >= segmentArray.length || segTRangeEnd < segTRangeStart) {
            return;
        }
        if (pos >= segTRangeStart && pos <= segTRangeEnd) {
            segmentArray[segmentTreeIndex] += diff;
        }
        if (segTRangeStart == segTRangeEnd) {
            return;
        }

        int mid = segTRangeStart + (segTRangeEnd - segTRangeStart) / 2;
        updateHelper(segTRangeStart, mid, pos, diff, 2 * segmentTreeIndex + 1);
        updateHelper(mid + 1, segTRangeEnd, pos, diff, 2 * segmentTreeIndex + 2);
        segmentArray[segmentTreeIndex] = Math.min(segmentArray[2 * segmentTreeIndex + 1], segmentArray[2 * segmentTreeIndex + 2]);
    }


    public int getRangeMinimum(int start, int end) {
        if (start < 0 || end > (segmentArrayend - (segmentArray.length / 2)) || start > end) {
            System.out.println("Invalid range");
            return Integer.MAX_VALUE;
        }
        return getRangeMinimumHelper(0, segmentArray.length / 2, start, end, 0);

    }

    private int getRangeMinimumHelper(int segmentTreeRangeStart, int segmentTreeRangeEnd, int startQ, int endQ, int segmentIndex) {
        if (segmentTreeRangeStart > segmentTreeRangeEnd || segmentTreeRangeStart < 0 || segmentTreeRangeEnd > (segmentArray.length / 2) || segmentIndex >= segmentArray.length) {
            return Integer.MAX_VALUE;
        }

        if (segmentTreeRangeStart >= startQ && segmentTreeRangeEnd <= endQ) {
            return segmentArray[segmentIndex];
        }

        int mid = segmentTreeRangeStart + (segmentTreeRangeEnd - segmentTreeRangeStart) / 2;

        return Math.min(getRangeMinimumHelper(segmentTreeRangeStart, mid, startQ, endQ, 2 * segmentIndex + 1),
                getRangeMinimumHelper(mid + 1, segmentTreeRangeEnd, startQ, endQ, 2 * segmentIndex + 2));
    }


    public void deleteElement(int index) {
        update(index, Integer.MAX_VALUE);

    }

}
