package com.himanshu.practice.y2018.june.june22;

/**
 * Created by Himanshu Bhardwaj on 22/06/18.
 */
public class RangeMinimumQuery {
    public static void main(String[] args) {
        int size = 7;
        int arr[] = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (9 * i + (i + 1) * (i - 1) + 343) % (2 * (size + 1) + 9);
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        System.out.println("Preparing Sgment PTree");
        SegTree segTree = new SegTree(arr);
        System.out.println("Segment tree minimum: (3,5):\t" + segTree.getRangeMinimum(3, 5));
        segTree.update(5, 12);
        segTree.update(4, 11);
        segTree.update(7, 1);
        System.out.println("Segment tree minimum: (3,5):\t" + segTree.getRangeMinimum(3, 5));
        segTree.print();
    }
}


class SegTree {
    int[] segTree;
    int endPos;

    public SegTree(int arr[]) {
        int segTreeSize = (int) Math.pow(2d, Math.ceil(Math.log(arr.length) / Math.log(2)));
        segTreeSize = 2 * segTreeSize - 1;
        System.out.println("Segment tree Size:\t" + segTreeSize);

        segTree = new int[segTreeSize];

        for (int i = 0; i <= segTreeSize / 2; i++) {
            if (i < arr.length) {
                segTree[i + segTreeSize / 2] = arr[i];
                endPos = i + (segTreeSize / 2);
            } else {
                segTree[i + (segTreeSize / 2)] = Integer.MAX_VALUE;
            }
        }

        for (int i = (segTree.length / 2) - 1; i >= 0; i--) {
            segTree[i] = Math.min(segTree[2 * i + 2], segTree[2 * i + 1]);
        }

        System.out.println();
        for (int i : segTree) {
            System.out.print(i + " ");
        }
        System.out.println();
    }


    public int getRangeMinimum(int start, int end) {
        return getRangeMinimumHelper(0, segTree.length / 2, start, end, 0);
    }

    private int getRangeMinimumHelper(int segTStart, int segTEnd, int start, int end, int segTreeIndex) {
        if (segTStart > segTEnd || segTStart < 0 || segTEnd > (segTree.length / 2) || segTEnd < start || segTStart > end) {
            return Integer.MAX_VALUE;
        }

        if (segTStart >= start && segTEnd <= end) {
            return segTree[segTreeIndex];
        }

        int mid = segTStart + (segTEnd - segTStart) / 2;

        return Math.min(getRangeMinimumHelper(segTStart, mid, start, end, 2 * segTreeIndex + 1),
                getRangeMinimumHelper(mid + 1, segTEnd, start, end, 2 * segTreeIndex + 2));

    }

    public void update(int pos, int newValue) {
        int diff = newValue - segTree[(segTree.length / 2)  + pos];
        updateHelper(0, segTree.length / 2, diff, pos, 0);
    }

    private void updateHelper(int segStart, int segEnd, int diff, int pos, int segIndex) {
        if (segStart > segEnd || segEnd > segTree.length / 2 || segEnd < pos || segStart > pos || segIndex >= segTree.length) {
            return;
        }
        if (segEnd == segStart) {
            //leaf
            if (segEnd == pos) {
                segTree[segIndex] += diff;
            } else {
                System.out.println("Something wrong has happened: " + segEnd);
            }
            return;
        }
        if (segStart <= pos && pos <= segEnd) {
            segTree[segIndex] += diff;
        }

        int mid = segStart + (segEnd - segStart) / 2;


        updateHelper(segStart, mid, diff, pos, (2 * segIndex) + 1);
        updateHelper(mid + 1, segEnd, diff, pos, (2 * segIndex) + 2);
        segTree[segIndex] = Math.min(segTree[2 * segIndex + 1], segTree[2 * segIndex + 2]);
    }


    public void print() {
        for (int i : segTree) {
            System.out.print(i + " ");
        }
        System.out.println();
    }


}



