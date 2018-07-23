package com.himanshu.practice.june.June13;

/**
 * Created by Himanshu Bhardwaj on 13/06/18.
 * 7:55pm
 * --20 min dinner
 */
public class RangeMinimumQuery {
    public static void main(String[] args) {
        int size = 5;
        int arr[] = new int[size];

        System.out.print("Orignal Array: ");
        for (int i = 0; i < size; i++) {
            arr[i] = ((i + i + 1) * i + 99) % 71;
            System.out.printf("%d ", arr[i]);
        }
        System.out.println();

        SegmentTree segmentTree = new SegmentTree(arr);
        segmentTree.change(27, 3);
        System.out.println("Index Tree after changing 3rd index to 44");
        segmentTree.printSegmentTree();

    }
}

//tested
class SegmentTree {
    int[] segTree;
    int segTreeEnd = -1;


    //tested
    public SegmentTree(int arr[]) {
        int segTreeLeaf = (int) Math.pow(2d, Math.ceil(Math.log(arr.length) / Math.log(2)));
        segTree = new int[2 * segTreeLeaf - 1];


        for (int i = 0; i < segTreeLeaf; i++) {
            if (i < arr.length) {
                segTree[segTreeLeaf - 1 + i] = arr[i];
                segTreeEnd = segTreeLeaf - 1 + i;
            } else {
                segTree[segTreeLeaf - 1 + i] = Integer.MAX_VALUE;
            }
        }

        for (int i = (segTree.length / 2 - 1); i >= 0; i--) {
            segTree[i] = Math.min(segTree[2 * i + 1], segTree[2 * i + 2]);
        }
        printSegmentTree();
    }


    public void change(int value, int index) {
        if (index > (segTreeEnd - (segTree.length / 2))) {
            System.out.println("invalid index");
            return;
        }
        System.out.printf("Changing index orignal value %d\n", segTree[(segTree.length / 2) + index]);
        int diff = value - segTree[(segTree.length / 2) + index];
        changeHelper(0, segTree.length / 2, diff, index, 0);
    }

    private void changeHelper(int segRangeStart, int segRangeEnd, int diff, int index, int segTreeIndex) {
        if (segRangeStart > segRangeEnd || segRangeEnd > segTreeEnd || index < segRangeStart || index > segRangeEnd || segTreeIndex > segTreeEnd) {
            return;
        }

        if (index >= segRangeStart && index <= segRangeEnd) {
            segTree[segTreeIndex] += diff;
        }

        if (segRangeStart == segRangeEnd) {
            return; //leaf
        }

        int mid = segRangeStart + (segRangeEnd - segRangeStart) / 2;

        changeHelper(segRangeStart, mid, diff, index, 2 * segTreeIndex + 1);
        changeHelper(mid + 1, segRangeEnd, diff, index, 2 * segTreeIndex + 2);
        segTree[segTreeIndex] = Math.min(segTree[2 * segTreeIndex + 1], segTree[2 * segTreeIndex + 2]);
    }


    //tested
    public void printSegmentTree() {
        System.out.println();
        for (int i = 0; i <= segTreeEnd; i++) {
            System.out.printf(" %d", segTree[i]);
        }
        System.out.println();
    }
}