package com.himanshu.practice.sept.sept17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by himanshubhardwaj on 17/09/18.
 * Statement: https://codeforces.com/contest/1042/problem/D
 * Algo:
 * 1. Sqrt Decomposition: Submission, https://codeforces.com/contest/1042/submission/43010464 time: 1747 ms
 * 2.Persistant segment tree decomposition, Submission: https://codeforces.com/contest/1042/submission/43025040 time: 795ms
 * 3. Normal Segment Tre gave TLE, Submission: https://codeforces.com/contest/1042/submission/43025346
 *
 */
public class PetyaAndArray {
    public static void main(String[] args) throws IOException {
        PersistandSegmentTreeSolution.solve();
    }
}


//Sqrt root  decomposition solution
//Time complexity: n^(1.5)logn
//Extra Space: O(n)
//AC: 1747ms
class SqrtDecompositionSolution {
    static ArrayList<Long>[] sqrt;
    static long pref[];
    static int sqrtN;


    static void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        long t = Long.parseLong(str[1]);

        int arr[] = new int[n];
        str = br.readLine().split(" ");
        pref = new long[n];


        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(str[i]);
            pref[i] = (i == 0) ? arr[i] : (arr[i] + pref[i - 1]);//prefix sum
        }


        sqrtN = (int) Math.sqrt(n);
        sqrt = new ArrayList[(int) Math.ceil(n / (double) sqrtN)];

        for (int i = 0; i < sqrt.length; i++) {
            sqrt[i] = new ArrayList<>(sqrtN);
        }

        for (int i = 0; i < n; i++) {
            sqrt[i / sqrtN].add(pref[i]);
        }


        for (int i = 0; i < sqrt.length; i++) {
            Collections.sort(sqrt[i]);
        }

        long count = 0;
        for (int i = 0; i < n; i++) {
            count += getNumElementsSqrtDecomposition(i, (i == 0) ? t : (t + pref[i - 1]));
        }
        System.out.print(count);
    }

    private static long getNumElementsBruteForce(int pos, long k) {
        long count = 0;
        for (int i = pos; i < pref.length; i++) {
            if (pref[i] < k) {
                count++;
            }
            System.out.print(pref[i] + ",");
        }

        System.out.println("\tBruteForce:\t" + pos + "\t" + k + "\t" + count);
        return count;
    }

    private static long getNumElementsSqrtDecomposition(int pos, long k) {
        int currentBucket = pos / sqrtN;
        long count = 0;

        for (int i = pos; i < Math.min(pref.length, sqrtN * (currentBucket + 1)); i++) {
            if (pref[i] < k) {
                count++;
            }
        }

        for (int i = currentBucket + 1; i < sqrt.length; i++) {
            count += countElement(0, sqrt[i].size() - 1, k, sqrt[i]);
        }

        return count;
    }

    //this will return number of elements from start to end are less than k
    static public int countElement(int start, int end, long k, ArrayList<Long> list) {
        if (start > end || start < 0 || end >= list.size() || list.get(start) >= k) {
            return 0;
        }

        if (list.get(end) < k) {
            return end - start + 1;
        }

        int mid = (end + start) / 2;

        if (list.get(mid) < k) {
            return mid - start + 1 + countElement(mid + 1, end, k, list);
        } else {
            return countElement(start, mid - 1, k, list);
        }
    }
}


//Time Complexity: nlog^2 n
//Extra Space: O(n logn)
//AC
//Time: 858
class PersistandSegmentTreeSolution {
    static long pref[];


    static void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        long t = Long.parseLong(str[1]);

        int arr[] = new int[n];
        str = br.readLine().split(" ");
        pref = new long[n];


        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(str[i]);
            pref[i] = (i == 0) ? arr[i] : (arr[i] + pref[i - 1]);//prefix sum
        }

        PersistentSegmentTree.constructPersistantSegmentTree(pref);


        long count = 0;
        for (int i = 0; i < n; i++) {
            count += PersistentSegmentTree.getNumElementsSegTree(i, (i == 0) ? t : (t + pref[i - 1]), n - 1);
        }
        System.out.print(count);


    }
}


//we can implement a simpler version only for suffix, but I want to get AC on generic so that it could be used further
class PersistentSegmentTree {
    static ArrayList<Long>[] persistentSegmentTree;
    static long[] segmentTree;
    static int size;
    static int end;

    public static void constructPersistantSegmentTree(long pref[]) {
        size = (int) Math.pow(2, Math.ceil(Math.log(pref.length) / Math.log(2)));
        size = size + size - 1;
        segmentTree = new long[size];
        persistentSegmentTree = new ArrayList[size];

        for (int i = 0; i <= (size / 2); i++) {
            persistentSegmentTree[(size / 2) + i] = new ArrayList<>();
            segmentTree[(size / 2) + i] = Long.MIN_VALUE;
            if (i < pref.length) {
                segmentTree[(size / 2) + i] = pref[i];
                persistentSegmentTree[(size / 2) + i].add(segmentTree[(size / 2) + i]);
                end = (size / 2) + i;
            }
        }

        for (int i = ((size / 2) - 1); i >= 0; i--) {
            segmentTree[i] = Math.max(segmentTree[2 * i + 1], segmentTree[2 * i + 2]);
            persistentSegmentTree[i] = new ArrayList<Long>(persistentSegmentTree[2 * i + 1]);
            persistentSegmentTree[i].addAll(persistentSegmentTree[2 * i + 2]);
            Collections.sort(persistentSegmentTree[i]);
        }
    }

    public static long getNumElementsSegTree(int pos, long k, int end) {
        return getNumElementsLessThanK(0, size / 2, pos, end, k, 0);
    }


    public static long getNumElementsLessThanK(int segTStart, int segTEnd, int rangeStart, int rangeEnd, long k, int segTIndex) {
        if (segTEnd < segTStart || segTStart < 0 || segTStart > rangeEnd || segTEnd < rangeStart || segTIndex > end) {
            return 0;
        }

        if (segTStart >= rangeStart && segTEnd <= rangeEnd) {
            if (segmentTree[segTIndex] < k) {
                return segTEnd - segTStart + 1;
            } else {
                return countElement(0, persistentSegmentTree[segTIndex].size() - 1, k, persistentSegmentTree[segTIndex]);
            }
        }

        int mid = (segTEnd + segTStart) / 2;

        return getNumElementsLessThanK(segTStart, mid, rangeStart, rangeEnd, k, 2 * segTIndex + 1) +
                getNumElementsLessThanK(mid + 1, segTEnd, rangeStart, rangeEnd, k, 2 * segTIndex + 2);
    }

    //this will return number of elements from start to end are less than k
    static public int countElement(int start, int end, long k, ArrayList<Long> list) {
        if (start > end || start < 0 || end >= list.size() || list.get(start) >= k) {
            return 0;
        }

        if (list.get(end) < k) {
            return end - start + 1;
        }

        int mid = (end + start) / 2;

        if (list.get(mid) < k) {
            return mid - start + 1 + countElement(mid + 1, end, k, list);
        } else {
            return countElement(start, mid - 1, k, list);
        }
    }

}



//This gave TLE
class SegmentTreeSolution {
    static long pref[];

    static void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        long t = Long.parseLong(str[1]);

        int arr[] = new int[n];
        str = br.readLine().split(" ");
        pref = new long[n];


        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(str[i]);
            pref[i] = (i == 0) ? arr[i] : (arr[i] + pref[i - 1]);//prefix sum
        }

        SegmentTree.constructPersistantSegmentTree(pref);

        long count = 0;
        for (int i = 0; i < n; i++) {
            count += SegmentTree.getNumElementsSegTree(i, (i == 0) ? t : (t + pref[i - 1]), n - 1);
        }
        System.out.print(count);
    }
}

//Simpiler segment tree just to test speed
class SegmentTree {
    static long[] segmentTree;
    static int size;
    static int end;

    public static void constructPersistantSegmentTree(long pref[]) {
        size = (int) Math.pow(2, Math.ceil(Math.log(pref.length) / Math.log(2)));
        size = size + size - 1;
        segmentTree = new long[size];

        for (int i = 0; i <= (size / 2); i++) {
            segmentTree[(size / 2) + i] = Long.MIN_VALUE;
            if (i < pref.length) {
                segmentTree[(size / 2) + i] = pref[i];
                end = (size / 2) + i;
            }
        }

        for (int i = ((size / 2) - 1); i >= 0; i--) {
            segmentTree[i] = Math.max(segmentTree[2 * i + 1], segmentTree[2 * i + 2]);
        }
    }

    public static long getNumElementsSegTree(int pos, long k, int end) {
        return getNumElementsLessThanK(0, size / 2, pos, end, k, 0);
    }


    public static long getNumElementsLessThanK(int segTStart, int segTEnd, int rangeStart, int rangeEnd, long k, int segTIndex) {
        if (segTEnd < segTStart || segTStart < 0 || segTStart > rangeEnd || segTEnd < rangeStart || segTIndex > end) {
            return 0;
        }

        if (segTStart >= rangeStart && segTEnd <= rangeEnd) {
            if (segmentTree[segTIndex] < k) {
                return segTEnd - segTStart + 1;
            } else {
                if (segTStart == segTEnd) {
                    return 0;
                }
            }
        }

        int mid = (segTEnd + segTStart) / 2;

        return getNumElementsLessThanK(segTStart, mid, rangeStart, rangeEnd, k, 2 * segTIndex + 1) +
                getNumElementsLessThanK(mid + 1, segTEnd, rangeStart, rangeEnd, k, 2 * segTIndex + 2);
    }

    //this will return number of elements from start to end are less than k
    static public int countElement(int start, int end, long k, ArrayList<Long> list) {
        if (start > end || start < 0 || end >= list.size() || list.get(start) >= k) {
            return 0;
        }

        if (list.get(end) < k) {
            return end - start + 1;
        }

        int mid = (end + start) / 2;

        if (list.get(mid) < k) {
            return mid - start + 1 + countElement(mid + 1, end, k, list);
        } else {
            return countElement(start, mid - 1, k, list);
        }
    }

}