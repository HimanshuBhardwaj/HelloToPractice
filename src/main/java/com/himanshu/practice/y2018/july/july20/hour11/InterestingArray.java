package com.himanshu.practice.y2018.july.july20.hour11;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 20/07/18.
 * Problem Set: http://codeforces.com/contest/482/problem/B
 * Algo: DP and Segment Tree construction
 * Submission: http://codeforces.com/contest/482/submission/40579144
 */
public class InterestingArray {


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n, m;
        String str[] = br.readLine().split(" ");
        n = Integer.parseInt(str[0]);
        m = Integer.parseInt(str[1]);
        int[] l = new int[m];
        int[] r = new int[m];
        int[] q = new int[m];
        int[] arr = new int[n];
        constructArray(n, m, br, arr, l, r, q);
        SegentTree st = new SegentTree(arr);

        for (int i = 0; i < m; i++) {
            if (!validate(st, l[i], r[i], q[i])) {
                System.out.println("NO");
                return;
            }
        }

        System.out.println("YES");
        for (int x : arr) {
            System.out.print(x + " ");
        }


    }

    private static boolean validate(SegentTree st, int l, int r, int q) {
        int sN = st.getAndedNumber(l, r);
        //System.out.println(sN + "\t\t" + q);
        if (sN == q) {
            return true;
        }
        return false;
    }

    private static void constructArray(int n, int m, BufferedReader br, int[] arr, int[] l, int[] r, int[] q) throws IOException {
        int count[];
        getInput(l, r, q, m, br);
        int bit = 0;

        for (int bitN = 0; bitN < 31; bitN++) {
            count = new int[n];
            bit = (1 << bitN);
            for (int i = 0; i < m; i++) {

                if ((q[i] & bit) > 0) {
                    count[l[i]]++;
                    if (r[i] < (n - 1)) {
                        count[r[i] + 1]--;
                    }
                }
            }

            for (int i = 1; i < n; i++) {
                count[i] = count[i] + count[i - 1];
            }

            for (int i = 0; i < n; i++) {
                if (count[i] > 0) {
                    arr[i] = arr[i] | bit;
                }
            }
//            if (bitN < 3) {
//                System.out.print("BIT: " + bitN + "\t\t");
//                for (int i = 0; i < n; i++) {
//                    System.out.print(count[i] + " ");
//                }
//                System.out.println();
//            }

        }

//        for (int x : arr) {
//            System.out.print(x + " ");
//        }
//        System.out.println();


    }

    private static void getInput(int[] l, int[] r, int[] q, int m, BufferedReader br) throws IOException {
        for (int i = 0; i < m; i++) {
            String st[] = br.readLine().split(" ");
            l[i] = Integer.parseInt(st[0]);
            r[i] = Integer.parseInt(st[1]);
            q[i] = Integer.parseInt(st[2]);
            l[i]--;
            r[i]--;
        }


    }
}

class SegentTree {
    int segmentTree[];
    int lastPos;

    public SegentTree(int[] arr) {

        int size = (int) Math.pow(2, Math.ceil(Math.log(arr.length) / Math.log(2)));
        size = 2 * size - 1;
        segmentTree = new int[size];


        for (int i = 0; i <= segmentTree.length / 2; i++) {
            if (i < arr.length) {
                segmentTree[i + segmentTree.length / 2] = arr[i];
                lastPos = i + segmentTree.length / 2;
            } else {
                segmentTree[i + segmentTree.length / 2] = Integer.MAX_VALUE;
            }
        }

        for (int i = segmentTree.length / 2 - 1; i >= 0; i--) {
            segmentTree[i] = segmentTree[2 * i + 1] & segmentTree[2 * i + 2];
        }
        //printSegmentTree();
    }


    //both l and r included
    public int getAndedNumber(int l, int r) {
        return getAndedNumberHelper(0, segmentTree.length / 2, l, r, 0);
    }

    private int getAndedNumberHelper(int segTreeStart, int segTreeEnd, int l, int r, int segTreeIndex) {
        if (segTreeStart < 0 || segTreeEnd < segTreeStart || l > segTreeEnd || r < segTreeStart || r < l || segTreeIndex > lastPos || segTreeEnd < 0) {
            return Integer.MAX_VALUE; //FFFF...F
        }
        if (l <= segTreeStart && r >= segTreeEnd) {
            return segmentTree[segTreeIndex];
        }

        int mid = segTreeStart + (segTreeEnd - segTreeStart) / 2;


        return getAndedNumberHelper(segTreeStart, mid, l, r, 2 * segTreeIndex + 1) &
                getAndedNumberHelper(mid + 1, segTreeEnd, l, r, 2 * segTreeIndex + 2);
    }

    public void printSegmentTree() {
        System.out.println("SEgment Tree Size: " + segmentTree.length);
        for (int i = 0; i < segmentTree.length; i++) {
            System.out.print(segmentTree[i] + "  ");
        }
        System.out.println();
    }
}
/*
*


1075 25
421 597 277086208
769 973 277089792
66 506 277090304
540 762 277090048
201 809 277086208
512 561 277086208
222 726 277086208
728 1036 277089280
506 659 277086208
396 859 277086208
562 663 277090048
618 675 277090048
846 884 277089920
618 1055 277089280
1047 1057 277089760
423 956 277086208
783 1062 277089280
1000 1021 277089792
565 1027 277089792
252 1003 277086208
11 878 277086208
577 676 277090048
569 1054 277089280
562 1014 277089792
859 945 277089792


*
* */