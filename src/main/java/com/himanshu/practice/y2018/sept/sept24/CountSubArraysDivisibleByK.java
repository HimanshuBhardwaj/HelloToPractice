package com.himanshu.practice.y2018.sept.sept24;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by himanshubhardwaj on 24/09/18.
 */
public class CountSubArraysDivisibleByK {
    public static void main(String[] args) {
        int n = 10;
        int k = 5;
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = i * (50 - i);
        }


        //computing prefix Sum
        long prefix[] = new long[arr.length];

        for (int i = 0; i < arr.length; i++) {
            prefix[i] = (i == 0) ? arr[i] : (arr[i] + prefix[i - 1]);
            prefix[i] = prefix[i] % k;
            System.out.print(prefix[i] + " ");
        }
        System.out.println();

        PersistantSegmentTree persistantSegmentTree = new PersistantSegmentTree(prefix, k);
        persistantSegmentTree.print();
        System.out.println();

        long count = 0;

        for (int i = 0; i < n; i++) {
            long t = prefix[i] - arr[i];
            while (t < 0) {
                t += k;
            }
            long tCount = persistantSegmentTree.computeSufixeswithSameModularSum(i, n - 1, t);
            System.out.println(i + "\t" + tCount + "\t" + t);
            count += tCount;
        }

        System.out.println("PersistantSegmentTree: " + count);
        System.out.println("Bruteforce" + bruteforce(arr, k));

    }


    static long bruteforce(int[] arr, int sumK) {
        long count = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                long sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += arr[k];
                }
                if (sum % sumK == 0) {
                    count++;
                }
            }
        }
        return count;
    }
}


class PersistantSegmentTree {
    ArrayList<Long>[] segmentTree;
    int k;
    int size;
    int end;
    int n;

    public PersistantSegmentTree(long[] prefix, int k) {
        int n = prefix.length;
        size = (int) Math.pow(2, Math.ceil(Math.log(prefix.length) / Math.log(2)));
        size = size + size - 1;

        segmentTree = new ArrayList[size];


        for (int i = 0; i <= size / 2; i++) {
            segmentTree[i + (size / 2)] = new ArrayList<>();
            if (i < prefix.length) {
                segmentTree[i + (size / 2)].add(prefix[i]);
                end = i + (size / 2);
            }
        }


        for (int i = (size / 2 - 1); i >= 0; i--) {
            //System.out.println((2 * i + 1) + "\t" + (2 * i + 1) + "\t" + segmentTree[2 * i + 1] + "\t" + segmentTree[2 * i + 2]);
            segmentTree[i] = new ArrayList<>();
            segmentTree[i].addAll(segmentTree[2 * i + 1]);
            segmentTree[i].addAll(segmentTree[2 * i + 2]);
            Collections.sort(segmentTree[i]);
        }


    }

    public long computeSufixeswithSameModularSum(int start, int end, long num) {
        if (start < 0 || end > this.n) {
            return 0;
        }
        return computeSufixeswithSameModularSumHelper(0, size / 2, start, end, num, 0);
    }

    private long computeSufixeswithSameModularSumHelper(int segIndexStart, int segIndexEnd, int start, int end, long num, int segIndex) {
        if (segIndexStart < 0 || segIndexEnd < segIndexStart || segIndexEnd < start || segIndexStart > end || segIndex > this.end) {
            return 0;
        }


        if (segIndexStart >= start && segIndexEnd <= end) {
            return numElementsWithValueEqualtoK(num, segmentTree[segIndex]);
        }


        int mid = (segIndexStart + segIndexEnd) / 2;

        return computeSufixeswithSameModularSumHelper(segIndexStart, mid, start, end, num, 2 * segIndex + 1) +
                computeSufixeswithSameModularSumHelper(mid + 1, segIndexEnd, start, end, num, 2 * segIndex + 2);
    }

    private long numElementsWithValueEqualtoK(long num, ArrayList<Long> list) {
        long count = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == num) {
                count++;
            }
        }
        System.out.println(count);
        return count;
    }

    public void print() {
        for (int i = 0; i < segmentTree.length; i++) {
            System.out.println(i + "\t" + segmentTree[i]);
        }
    }
}


