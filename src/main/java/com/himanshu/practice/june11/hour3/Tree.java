package com.himanshu.practice.june11.hour3;

/**
 * Created by himanshubhardwaj on 11/06/18.
 */
public class Tree {
    public static void main(String[] args) {
        int arrSize = 11;
        int[] arr = new int[arrSize];

        System.out.println("Printing Actual array");
        for (int i = 0; i < arrSize; i++) {
            arr[i] = (i * 8 * i + i * i + 29) % 17;
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        SegmentTree segmentTree = new SegmentTree(arr);
        System.out.print("Getting sum(3,4):\t ");
        System.out.println(segmentTree.getSum(5, 9));

        //segmentTree.printSegmentTre();
    }
}


class SegmentTree {
    int segmentArr[];
    int size;
    int endIndex;

    public SegmentTree(int arr[]) {
        int sizeA = arr.length;
        endIndex = arr.length - 1;


        double sizeSegT = Math.pow(2d, Math.ceil(Math.log(new Double(sizeA)) / Math.log(2)));
        sizeSegT = (2 * sizeSegT) - 1;
        segmentArr = new int[(int) sizeSegT];
        size = (int) sizeSegT;


        int pos = 0;
        for (int i = ((int) sizeSegT) / 2; i < sizeSegT; i++) {
            if (pos < arr.length) {
                segmentArr[i] = arr[pos];
            } else {
                segmentArr[i] = Integer.MIN_VALUE;
            }
            pos++;
        }

        int endPos = ((int) sizeSegT / 2) - 1 + arr.length;

        System.out.println("Segment Tree Array Size: " + sizeSegT);
        System.out.println("Array Size: " + arr.length);
        System.out.println("endPos: " + endPos);

        System.out.println("iterating Segment tree: ");
        for (int i = ((((int) sizeSegT) / 2) - 1); i >= 0; i--) {
            int parent1 = 2 * i + 1;
            int parent2 = 2 * i + 2;


            if (parent1 > endPos && parent2 > endPos) {
                continue;
            } else if (parent2 <= endPos && parent1 <= endPos) {
                segmentArr[i] = segmentArr[parent1] + segmentArr[parent2];
            } else if (parent1 <= endPos) {
                segmentArr[i] = segmentArr[parent1];
            }
            System.out.println("Index:" + i + "\tParent1: " + parent1 + "\tParent2: " + parent2 + "\tsegmentArr[index]:" + segmentArr[i]);
        }


        for (int i = 0; i < Math.min(segmentArr.length, endPos + 1); i++) {
            System.out.print(segmentArr[i] + " ");
        }
        System.out.println();
    }


    public int getSum(int start, int end) {
        if (start < 0 || end > endIndex || start > end || start > end) {
            return Integer.MIN_VALUE;
        }
        return getSumHelper(0, this.size / 2, start, end, 0);
    }

    private int getSumHelper(int segmentStart, int segmentEnd, int start, int end, int segmentPos) {

        //since validation on start, end has been checked before and we are not changing them here so do not check it here


        if ((segmentStart < start && segmentEnd < start) || (segmentStart > end && segmentEnd > end) || (segmentEnd < segmentStart)) { //refine it
            return 0;//no contribution in the sum
        }

        if (start <= segmentStart && end >= segmentEnd) {
            return segmentArr[segmentPos];
        }

        int mid = segmentStart + (segmentEnd - segmentStart) / 2;
        return getSumHelper(segmentStart, mid, start, end, 2 * segmentPos + 1) + getSumHelper(mid + 1,
                segmentEnd, start, end, 2 * segmentPos + 2);

    }

    public void printSegmentTre() {
        for (int i = 0; i < segmentArr.length; i++) {
            System.out.print(segmentArr[i] + " ");
        }
        System.out.println();
    }
}








