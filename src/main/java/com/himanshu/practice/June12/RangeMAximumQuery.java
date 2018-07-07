package com.himanshu.practice.June12;

/**
 * Created by Himanshu Bhardwaj on 12/06/18.
 * 7:04
 */
public class RangeMAximumQuery {
    public static void main(String[] args) {
        int size = 1;
        int arr[] = new int[size];

        System.out.print("Actual Array: ");
        for (int i = 0; i < size; i++) {
            arr[i] = (i * i * 222 + i) % 101;
            System.out.print(arr[i] + " ");
        }

        System.out.println();

        SegmentTree segmentTree = new SegmentTree(arr);
        System.out.println("Inserting 85 at position 3");
        segmentTree.insert(3, 85);

        segmentTree.printSegMentArray();
        System.out.println();
        System.out.println("Inserting 777 at position 4");
        segmentTree.insert(4, 777);
        segmentTree.printSegMentArray();
        System.out.printf("Max in 2,5: %d", segmentTree.getMaximum(2, 4));
    }
}


class SegmentTree {
    int segArr[];
    int segArrSize;
    int segArrEnd; //end index

    public SegmentTree(int arr[]) {
        int tempSize = (int) Math.pow(2, Math.ceil(Math.log(arr.length) / Math.log(2)));
        segArrSize = 2 * tempSize - 1;
        segArrEnd = arr.length + tempSize - 2;
        segArr = new int[segArrSize];

        System.out.println("arr.size: " + arr.length + "\tSegmentArrSize: " + segArrSize + "\tsegmentArrEnd: " + segArrEnd);

        for (int i = 0; (i + (tempSize - 1)) < segArr.length; i++) {
            //System.out.println("Position: " + (i + tempSize - 1));
            if (i < arr.length) {
                segArr[i + (tempSize - 1)] = arr[i];
            } else {
                segArr[i + tempSize - 1] = Integer.MIN_VALUE;
            }
        }

        for (int i = tempSize - 2; i >= 0; i--) {
            segArr[i] = Math.max(segArr[2 * i + 1], segArr[2 * i + 2]);
        }


        for (int i : segArr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }


    //this position is on 0...pos interval
    //this pos is unrotected, protect it
    public void insert(int pos, int value) {
        int diff = value - segArr[segArrSize / 2 + pos];
        insert(0, segArrSize / 2, pos, diff, 0);


    }

    private void insert(int startPosSegment, int endPosSegment, int pos, int diff, int segmentIndex) {
        if ((startPosSegment > endPosSegment) || (segmentIndex > segArrEnd) || pos > (segArrEnd - (segArrSize / 2))) {
            return;
        }

        if (pos >= startPosSegment && pos <= endPosSegment) {
            segArr[segmentIndex] += diff;
        }

        if (startPosSegment == endPosSegment) {
            return;//lef
        }

        int mid = startPosSegment + (endPosSegment - startPosSegment) / 2;
        insert(startPosSegment, mid, pos, diff, 2 * segmentIndex + 1);
        insert(mid + 1, endPosSegment, pos, diff, 2 * segmentIndex + 2);

        segArr[segmentIndex] = Math.max(segArr[2 * segmentIndex + 1], segArr[2 * segmentIndex + 2]);
    }

    public void printSegMentArray() {
        for (int i = 0; i < segArr.length; i++) {
            System.out.print(segArr[i] + " ");
        }
        System.out.println();
    }

    //get maximum in the range
    public int getMaximum(int start, int end) {
        if (end < start) {
            return Integer.MIN_VALUE;
        }

        return getMaximumHelper(0, segArrSize / 2, start, end, 0);
    }

    private int getMaximumHelper(int startSegmentRange, int endSegmentRange, int start, int end, int segmentIndex) {


        if ((startSegmentRange > endSegmentRange) || start > end || end < startSegmentRange || start > endSegmentRange || segmentIndex > segArrEnd) {
            return Integer.MIN_VALUE;
        }

        if (startSegmentRange >= start && endSegmentRange <= end) {
            return segArr[segmentIndex];
        }

        int mid = startSegmentRange + (endSegmentRange - startSegmentRange) / 2;

        return Math.max(getMaximumHelper(startSegmentRange, mid, start, end, 2 * segmentIndex + 1),
                getMaximumHelper(mid + 1, endSegmentRange, start, end, 2 * segmentIndex + 2));
    }
}