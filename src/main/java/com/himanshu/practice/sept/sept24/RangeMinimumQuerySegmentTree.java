package com.himanshu.practice.sept.sept24;


/**
 * Created by himanshubhardwaj on 24/09/18.
 */
public class RangeMinimumQuerySegmentTree {
    public static void main(String[] args) {
        int arr[] = new int[6];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = i * (2-i)   ;
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        SegmentTree segmentTree = new SegmentTree(arr);
        segmentTree.printTree();

        System.out.println(segmentTree.getRangeMinimum(1,4));
        System.out.println(segmentTree.getRangeMinimum(1,1));
        System.out.println(segmentTree.getRangeMinimum(1,3));
        System.out.println(segmentTree.getRangeMinimum(1,6));
    }
}


class SegmentTree {
    int[] tree;
    int endPointSegmentTree;
    int size;
    int endIndex;


    public SegmentTree(int[] arr) {
        size = (int) Math.pow(2, Math.ceil(Math.log(arr.length) / Math.log(2)));
        size = size + size - 1;
        endIndex = arr.length - 1;
        tree = new int[size];


        for (int i = 0; i <= (size / 2); i++) {
            if (i < arr.length) {
                tree[(size / 2) + i] = arr[i];
                endPointSegmentTree = (size / 2) + i;
            } else {
                tree[(size / 2) + i] = Integer.MAX_VALUE;
            }
        }

        for (int i = ((size / 2) - 1); i >= 0; i--) {
            tree[i] = Math.min(tree[(2 * i) + 1], tree[(2 * i) + 2]);
        }
    }


    public int getRangeMinimum(int start, int end) {
        if (start < 0 || end > endIndex) {
            throw new RuntimeException("Index Out of Bound");
            //or we can make something ad-hoc for this
        }

        return getRangeMinimumHelper(0, size / 2, start, end, 0);


    }

    private int getRangeMinimumHelper(int segIndexStart, int segIndexEnd, int start, int end, int segIndex) {
        if (segIndexStart < 0 || segIndexEnd < segIndexStart || segIndexEnd < start || segIndexStart > end || segIndex > endPointSegmentTree) {
            return Integer.MAX_VALUE;
        }

        if (segIndexStart >= start && segIndexEnd <= end) {
            return tree[segIndex];
        }


        int mid = (segIndexStart + segIndexEnd) / 2;

        return Math.min(getRangeMinimumHelper(segIndexStart, mid, start, end, (2 * segIndex) + 1), getRangeMinimumHelper(mid + 1, segIndexEnd, start, end, (2 * segIndex) + 2));
    }

    public void printTree() {
        System.out.println("Size: " + size);
        System.out.println("End Index: " + endIndex);
        System.out.println("endPointSegmentTree " + endPointSegmentTree);

        for (int i = 0; i <= endPointSegmentTree; i++) {
            System.out.print(tree[i] + " ");
        }
        System.out.println();
    }
}
