package com.himanshu.practice.y2018.Aug.aug23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * Created by himanshubhardwaj on 25/08/18.
 */
public class TestClassA {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");

        long n = Long.parseLong(str[0]);
        long k = Long.parseLong(str[1]);
        String s = br.readLine();
        int qS = Integer.parseInt(br.readLine());
        long Q[] = new long[qS];
        for (int i = 0; i < Q.length; i++) {
            Q[i] = Long.parseLong(br.readLine());
        }

        br.close();

        int[] q = solve(Q, (int) k, s, n);
        for (int x : q) {
            System.out.println(x);
        }


    }


    static int[] solve(long[] Q, int k, String s, long n) {
        long arr[] = new long[k];
        TreeMap<Long, Integer> treeMap = new TreeMap<>();


        PriorityQueue<Segment> segments = new PriorityQueue<>();
        segments.add(new Segment(0, n + 1));

        for (int i = 0; i < k; i++) {
            Segment segment = segments.poll();
            long numChairs = segment.end - segment.start - 1;
            if ((numChairs % 2) == 1) {
                Segment newSegment = new Segment(segment.start + (numChairs / 2) + 1, segment.end);
                segment.end = segment.start + (numChairs / 2) + 1;
                arr[i] = segment.start + (numChairs / 2) + 1;
                segments.add(newSegment);
                segments.add(segment);
            } else {
                long pos = 0;
                if (s.charAt(i) == 'L') {
                    pos = segment.start + (numChairs / 2);
                } else {
                    pos = segment.start + (numChairs / 2) + 1;
                }
                Segment newSegment = new Segment(pos, segment.end);
                segment.end = pos;
                segments.add(newSegment);
                segments.add(segment);
                arr[i] = pos;
            }
            treeMap.put(arr[i], i + 1);
        }

        int query[] = new int[Q.length];

        for (int i = 0; i < Q.length; i++) {
            long pos = Q[i];

            if (treeMap.containsKey(pos)) {
                query[i] = treeMap.get(pos);
            } else {
                query[i] = -1;
            }

        }
        return query;
    }
}

class Segment implements Comparable<Segment> {
    long start;
    long end;

    public Segment(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Segment o) {
        long thisL = this.end - this.start - 1;
        long oL = o.end - o.start - 1;

        if (oL != thisL) {
            return Long.compare(oL, thisL);
        } else {
            return Long.compare(this.start, o.start);
        }
    }
}