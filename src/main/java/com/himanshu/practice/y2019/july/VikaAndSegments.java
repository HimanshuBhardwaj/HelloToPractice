package com.himanshu.practice.y2019.july;

import com.himanshu.practice.y2018.nov.nov5.Tree;
import lombok.AllArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Created by himanshubhardwaj on 03/07/19.
 * statement: https://codeforces.com/contest/610/problem/D
 * Time: 12:37
 * TODO: Implement it; tough to implement and might take one day.
 */
public class VikaAndSegments {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String str[];

        long count = 0;
        ArrayList<Segment> columnSegment = new ArrayList<>();
        TreeSet<ColumnIndex> columnIndices = new TreeSet<>();

        for (int i = 0; i < n; i++) {
            str = br.readLine().split(" ");
            int x1 = Integer.parseInt(str[0]);
            int y1 = Integer.parseInt(str[1]);
            int x2 = Integer.parseInt(str[2]);
            int y2 = Integer.parseInt(str[3]);

            if (x1 == x2) {
                count += 1 + Math.abs(y1 - y2);
                //columnIndices.add()
            } else {
                columnSegment.add(new Segment(x1, y1, x2, y2));
            }
        }


    }
}

class ColumnIndex implements Comparable<ColumnIndex> {
    int position;
    int index;

    @Override
    public int compareTo(ColumnIndex o) {
        return (this.position - o.position);
    }
}


@AllArgsConstructor
class Segment {
    int x1;
    int y1;
    int x2;
    int y2;
}
