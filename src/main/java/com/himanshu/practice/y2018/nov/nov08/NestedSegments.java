package com.himanshu.practice.y2018.nov.nov08;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Created by himanshubhardwaj on 08/11/18.
 * 4:41 (20 mins)
 * Statement: https://codeforces.com/contest/976/problem/C
 * Algo: Sorting
 * Submission: https://codeforces.com/contest/976/submission/45441358
 */
public class NestedSegments {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Segment[] seg = new Segment[n];


        for (int i = 0; i < n; i++) {
            String str[] = br.readLine().split(" ");
            seg[i] = new Segment(Integer.parseInt(str[0]), Integer.parseInt(str[1]), i + 1);
        }

        Arrays.sort(seg);
        int segIndex = seg[0].index;
        int end = seg[0].end;

        for (int i = 1; i < n; i++) {
            if (seg[i].end <= end) {
                System.out.print((seg[i].index) + " " + (segIndex));
                return;
            } else {
                end = seg[i].end;
                segIndex = seg[i].index;
            }
        }
        System.out.print(-1 + " " + -1);


    }
}


class Segment implements Comparable<Segment> {
    int start;
    int end;
    int index;

    public Segment(int start, int end, int index) {
        this.start = start;
        this.end = end;
        this.index = index;
    }

    @Override
    public int compareTo(Segment o) {
        if (this.start == o.start) {
            return o.end - this.end;
        } else {
            return this.start - o.start;
        }
    }
}