package com.himanshu.practice.y2018.july.july10;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Created by Himanshu Bhardwaj on 10/07/18.
 * ProblemSet: https://codeforces.com/contest/339/problem/D
 * Algo: SegmentTree
 * Submission: https://codeforces.com/contest/339/submission/40156931

 */
public class XeniaAndBitOperations {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        n = (int) Math.pow(2d, n);
        int m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine(), " ");
        int arr[] = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        SegmentTree str = new SegmentTree(arr);
        //str.print();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int newPos = Integer.parseInt(st.nextToken()) - 1;
            int newValue = Integer.parseInt(st.nextToken());
            str.change(0, str.segmentTree.length / 2, newPos, newValue, 0);
//            str.print();
            System.out.println(str.segmentTree[0]);
        }

//        System.out.println(str.segmentTree[0]);


    }

}


class SegmentTree {
    int numNodes;
    int segmentTree[];
    int height;


    public SegmentTree(int arr[]) {
        numNodes = 2 * arr.length - 1;
        segmentTree = new int[numNodes];
        height = (int) (Math.log(arr.length) / Math.log(2));

        for (int i = 0; i <= segmentTree.length / 2; i++) {
            segmentTree[segmentTree.length / 2 + i] = arr[i];
        }

        for (int i = segmentTree.length / 2 - 1; i >= 0; i--) {
            segmentTree[i] = operation(i, segmentTree[2 * i + 1], segmentTree[2 * i + 2]);
        }
    }

    public void change(int startS, int endS, int pos, int newValue, int segI) {
        if (startS > endS || pos < startS || pos > endS) {
            return;
        }

        if (startS == endS) {
            segmentTree[segI] = newValue;
            return;
        }

        int mid = startS + (endS - startS) / 2;
        change(startS, mid, pos, newValue, 2 * segI + 1);
        change(mid + 1, endS, pos, newValue, 2 * segI + 2);
        segmentTree[segI] = operation(segI, segmentTree[2 * segI + 1], segmentTree[2 * segI + 2]);
    }

    public void print() {
        System.out.print("Height: " + height + "\t");
        for (int i : segmentTree) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public int operation(int pos, int opr1, int opr2) {
        int currentHeight = (int) (Math.log(pos + 1) / Math.log(2));

        if ((height - currentHeight) % 2 == 1) {
            return opr1 | opr2;
        } else {
            return opr1 ^ opr2;
        }
    }

}
