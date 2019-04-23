package com.himanshu.practice.y2018.nov.nov09;

/**
 * Created by himanshubhardwaj on 12/11/18.
 * 1. Prepare: 6:20-6:33
 * 2. Return Range Minimum: 6:33-6:43
 * 3. Update PTree: 6:35--7 pm; 10 mins could been saved
 */
public class RangeMinimum {
    public static void main(String[] args) {
        int size = 6;
        int[] arr = new int[size];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = i * i * i + (i) - 4 * i * i + 7;
            System.out.print(arr[i] + ", ");
        }
        System.out.println();

        SegmentTree seg = new SegmentTree(arr);

        System.out.println();
        seg.print();
        System.out.println();
        System.out.println(seg.rangeMinimum(0, seg.segmentTree.length / 2, 3, 5, 0));
        seg.update(5, -2);
        seg.print();
        System.out.println(seg.rangeMinimum(0, seg.segmentTree.length / 2, 3, 5, 0));

    }

}

class SegmentTree {
    int[] segmentTree;
    int end;
    int size;


    public SegmentTree(int[] arr) {
        int size = (int) Math.pow(2, Math.ceil(Math.log(arr.length) / Math.log(2)));
        size = size + size - 1;
        this.size = size;

        segmentTree = new int[size];

        for (int i = 0; i <= size / 2; i++) {
            if (i < arr.length) {
                segmentTree[i + (size / 2)] = arr[i];
                end = i + (size / 2);
            } else {
                segmentTree[i + (size / 2)] = Integer.MAX_VALUE;
            }
        }

        for (int i = (size / 2) - 1; i >= 0; i--) {
            segmentTree[i] = Math.min(segmentTree[2 * i + 1], segmentTree[2 * i + 2]);
        }
    }


    public void print() {
        for (int i = 0; i < segmentTree.length; i++) {
            System.out.print(segmentTree[i] + " ");
        }
        System.out.println();
    }


    public int rangeMinimum(int segS, int segE, int s, int e, int index) {
        if (segS < 0 || segE >= size || segS > segE || index > end || (segS > e) || segE < s) {
            return Integer.MAX_VALUE;
        }

        if (s <= segS && e >= segE) {
            return segmentTree[index];
        }
        int mid = (segE + segS) / 2;

        return Math.min(rangeMinimum(segS, mid, s, e, 2 * index + 1), rangeMinimum(mid + 1, segE, s, e, 2 * index + 2));
    }


    public void update(int position, int newValue) {
        int normalisedPosition = (segmentTree.length / 2) + position;
        segmentTree[normalisedPosition] = newValue;
        normalisedPosition = (normalisedPosition - 1) / 2;

        while (normalisedPosition >= 0) {
            if (2 * normalisedPosition + 2 >= segmentTree.length) {
                segmentTree[normalisedPosition] = segmentTree[2 * normalisedPosition + 1];
            } else {
                segmentTree[normalisedPosition] = Math.min(segmentTree[2 * normalisedPosition + 1], segmentTree[2 * normalisedPosition + 2]);
            }
            if (normalisedPosition == 0) {
                break;
            } else {
                normalisedPosition = (normalisedPosition-1) / 2;
            }
        }
    }

}