package com.himanshu.practice.Aug.aug17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * Created by himanshubhardwaj on 16/08/18.
 * Statement: https://codeforces.com/contest/620/problem/C
 * Algo: Greedy PArtition of array
 * Submission: https://codeforces.com/contest/620/submission/41655757
 */
public class PearlsInARow {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());


        String[] str = br.readLine().split(" ");
        int[] arr = new int[n];
        ArrayList<Segment> segments = new ArrayList<>();
        HashSet<Integer> set = new HashSet<>();

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(str[i]);
        }


        int start = 0;

        for (int i = 0; i < n; i++) {
            if (set.isEmpty()) {
                start = i;
            }

            if (set.contains(arr[i])) {
                Segment segment = new Segment(start + 1, i + 1);
                set = new HashSet<>();
                segments.add(segment);
            } else {
                set.add(arr[i]);
            }
        }

        System.out.println((segments.size() == 0) ? -1 : segments.size());
        for (int i = 0; i < segments.size(); i++) {
            if (i == (segments.size() - 1)) {
                System.out.print(segments.get(i).start + " " + n);
            } else {
                System.out.println(segments.get(i));
            }
        }

    }
}

class Segment {
    int start;
    int end;


    public Segment(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public String toString() {
        return start + " " + end;
    }
}