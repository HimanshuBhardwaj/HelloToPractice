package com.himanshu.practice.y2019.july.july11;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by himanshubhardwaj on 11/07/19.
 */
public class RangeXorWithUodates {
    public static void main(String[] args) {
        int size = 200000;
//        int size=5;
        int arr[] = new int[size];
//        arr[0] = 75;
//        arr[1] = 761;
//        arr[2] = 884;
//        arr[3] = 305;
//        arr[4] = 421;

        for (int i = 0; i < size; i++) {
            arr[i] = (int) (1000 * Math.random());
//            System.out.print(arr[i] + ",");
        }
        System.out.println();

        BruteForce bruteForce = new BruteForce(arr);
        SegmentTree segmentTree = new SegmentTree(arr);
        SqrtDecomposition sqrtDecomposition = new SqrtDecomposition(arr);

        int queries = 10;
        int l, r;

        long sum1=0;
        long sum2=0;
        long sum3=0;

        for (int i = 0; i < 100000; i++) {
            l = (int) (Math.random() * (size-1) );
            r = l + (int) (Math.random() * size);
            if (r >= arr.length) {
                r = arr.length - 1;
            }
            if (r<l) {
                l=r;
            }

            int result1 = bruteForce.findXor(l, r);
            int result2 = segmentTree.findXor(l, r);
            int result3 = sqrtDecomposition.findXor(l, r);

            if (result1 != result2 || result2 != result3) {
                System.out.println("wrong result" + result1 + "\t" + result2 + "\t" + result3);
                System.out.println(l+"\t"+r);
            }
            sum1+=bruteForce.time.getLast();
            sum2+=segmentTree.time.getLast();
            sum3+=sqrtDecomposition.time.getLast();
        }

        System.out.println(sum1+"\t"+sum2+"\t"+sum3);




//        System.out.println(bruteForce.findXor(4, 4));
//        System.out.println(segmentTree.findXor(4, 4));



        bruteForce.update(3, 3);
        segmentTree.update(3, 3);
        sqrtDecomposition.update(3, 3);
        bruteForce.update(4, 111);
        segmentTree.update(4, 111);
        sqrtDecomposition.update(4, 111);

        System.out.println(bruteForce.findXor(2, 400));
        System.out.println(segmentTree.findXor(2, 400));
                System.out.println(sqrtDecomposition.findXor(2, 400));


    }
}

class BruteForce {
    int[] arr;
    int size;
    LinkedList<Long> time = new LinkedList<>();

    public BruteForce(int[] arr) {
        this.arr = arr;
        this.size = arr.length;
    }

    int findXor(int l, int r) {
        long start = System.currentTimeMillis();
        int result = 0;
        for (int i = l; i <= r; i++) {
            result ^= arr[i];
        }
        long end = System.currentTimeMillis();
        time.add(end - start);


        return result;
    }

    void update(int pos, int value) {
        arr[pos] = value;
    }

}


//10:00pm--10:41pm
class SegmentTree {
    int[] segTree;
    int size;
    int numElements;
    int lastIndex;
    LinkedList<Long> time = new LinkedList<>();

    public SegmentTree(int[] arr) {
        this.size = (int) Math.pow(2, Math.ceil(Math.log(arr.length) / Math.log(2)));
        this.size = 2 * size - 1;
        segTree = new int[size];
        int index = 0;
        this.numElements = arr.length;

        for (int i = size / 2; i < size; i++) {
            if (index < arr.length) {
                segTree[i] = arr[index++];
                this.lastIndex = i;
            }
        }

        for (int i = (size / 2) - 1; i >= 0; i--) {
            segTree[i] = segTree[2 * i + 2] ^ segTree[2 * i + 1];
        }

//        System.out.println("Printing");
//        for (int i=0;i<segTree.length;i++) {
//            System.out.print(segTree[i]+",");
//        }
//        System.out.println();

    }

    //36 mins
    int findXor(int l, int r) {
        long start = System.currentTimeMillis();
        int result = findXorHelper(0, size / 2, l, r, 0);
        long end = System.currentTimeMillis();
        time.add(end - start);
        return result;
    }

    private int findXorHelper(int start, int end, int l, int r, int index) {
        if (start < 0 || end < start || start > r || end < l || index >= segTree.length) {
            return 0;
        }
        if (start >= l && end <= r) {
            return segTree[index];
        }
        int mid = start + (end - start) / 2;
        return findXorHelper(start, mid, l, r, 2 * index + 1) ^ findXorHelper(mid + 1, end, l, r, 2 * index + 2);
    }

    //5 mins
    void update(int pos, int value) {
        int posInSegTree = (size / 2) + pos;
        segTree[posInSegTree] = value;

        while (posInSegTree >= 0) {
            posInSegTree = (posInSegTree - 1) / 2;
            segTree[posInSegTree] = segTree[2 * posInSegTree + 1] ^ segTree[2 * posInSegTree + 2];
            if (posInSegTree == 0) {
                break;
            }
        }
    }
}

//11:26pm
class SqrtDecomposition {
    int segmentSize;
    int numSegMent;
    int[] segmentXOR;
    int[] arr;
    LinkedList<Long> time = new LinkedList<>();


    //beaware of small arr
    public SqrtDecomposition(int[] arr) {
        this.segmentSize = (int) Math.sqrt(arr.length);
        this.numSegMent = (arr.length / segmentSize) + ((arr.length % segmentSize == 0) ? 0 : 1);
        segmentXOR = new int[numSegMent];
        for (int i = 0; i < arr.length; i++) {
            int segment = getSegMEent(i);
            segmentXOR[segment] = segmentXOR[segment] ^ arr[i];
        }
        this.arr = arr;
    }


    int getSegMEent(int index) {
        return index / segmentSize;
    }

    int findXor(int l, int r) {
        long start = System.currentTimeMillis();
        int startSegment = getSegMEent(l);
        int endSegment = getSegMEent(r);
        int result = 0;

        for (int i = startSegment + 1; i <= endSegment - 1; i++) {
            result ^= segmentXOR[i];
        }
        int index = l;

        while (getSegMEent(index) == startSegment && index >= 0 && index < arr.length && index<=r) {
            result ^= arr[index++];
        }

        index = r;

        while ((startSegment != endSegment) && (getSegMEent(index) == endSegment) && index >= 0 && index < arr.length && index>=l) {

            result ^= arr[index--];
        }

        long end = System.currentTimeMillis();

        time.add(end - start);


        return result;
    }

    void update(int pos, int value) {
        int getSegment = getSegMEent(pos);
        int oldValue = arr[pos];
        segmentXOR[getSegment] = segmentXOR[getSegment] ^ oldValue ^ value;
        arr[pos] = value;
    }


}
//0,968,761,761,305,0,0,75,761,884,305,421,0,0,0,
