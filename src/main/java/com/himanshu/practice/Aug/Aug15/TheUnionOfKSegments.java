package com.himanshu.practice.Aug.Aug15;

import lombok.AllArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by himanshubhardwaj on 16/08/18.
 * Problem Statement: https://codeforces.com/contest/612/problem/D
 * TODO: know solution but too lazy to implement; Imlement it later
 *
 */
public class TheUnionOfKSegments {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");

        int n = Integer.parseInt(str[0]);
        int k = Integer.parseInt(str[1]);

        ArrayList<Segment> segments = new ArrayList<>(n);

        for (int i = 0; i < n; i++) {
            str = br.readLine().split(" ");
            Segment segment = new Segment(Long.parseLong(str[0]), true, Long.parseLong(str[1]), true);
            segments.add(segment);
        }


    }
}


@AllArgsConstructor
class Segment implements Comparable<Segment> {
    long start;
    boolean startIncluded;
    long end;
    boolean endIncluded;
    static ArrayList<Segment> newSegmentsList = new ArrayList<>();

    @Override
    public int compareTo(Segment o) {
        return (int) (this.start - o.start);
    }


    //start.s1 <= start.s2
    //assume both segments exist int he list
    static Segment split(Segment s1, Segment s2, ArrayList<Segment> segments) {
        if (s1.start < s2.start) {
            if (s1.end < s2.end) {
                Segment segment1 = new Segment(s2.start, s2.startIncluded, s1.end, s1.endIncluded);
                Segment segment2 = new Segment(s2.start, s2.startIncluded, s1.end, s1.endIncluded);
                s1.end = s2.start;
                s1.endIncluded = (s2.startIncluded) ? false : true;
                s2.start = s1.end;
                s2.startIncluded = !s1.endIncluded;
                newSegmentsList.add(segment1);
                newSegmentsList.add(segment2);
            } else if (s1.end == s2.end) {
                if (s1.endIncluded && s2.endIncluded) {
                    Segment segment1 = new Segment(s2.start, s2.startIncluded, s1.end, s1.endIncluded);
                    s1.end = s2.start;
                    s1.endIncluded = !s2.startIncluded;
                    newSegmentsList.add(segment1);
                } else if (s1.endIncluded && !s2.endIncluded) {
                    Segment segment1 = new Segment(s1.end, s1.endIncluded, s1.end, s1.endIncluded);
                    s1.endIncluded = false;
                    newSegmentsList.add(segment1);
                    return split(s2, s1, segments);
                } else if (!s1.endIncluded && s2.endIncluded) {
                    return split(s2, s1, segments);
                } else if (!s1.endIncluded && !s1.endIncluded) {
                    Segment segment1 = new Segment(s2.start, s2.startIncluded, s2.end, s2.endIncluded);
                    newSegmentsList.add(segment1);
                    s1.end = s2.start;
                    s1.endIncluded = !s2.startIncluded;
                }
            } else if (s1.end > s2.end) {
                return split(s2, s1, segments);
            }
        } else if (s1.start == s2.start) {
            if (s1.end < s2.end) {
                if (s1.startIncluded && s2.startIncluded) {
                    Segment segment1 = new Segment(s2.start, s2.startIncluded, s2.end, s2.endIncluded);

                } else if (s1.startIncluded && !s2.startIncluded) {

                } else if (!s1.startIncluded && s2.startIncluded) {

                } else if (!s1.startIncluded && !s2.startIncluded) {

                }
            } else if (s1.end == s2.end) {
                if (s1.startIncluded && s2.startIncluded) {

                } else if (s1.startIncluded && !s2.startIncluded) {

                } else if (!s1.startIncluded && s2.startIncluded) {

                } else if (!s1.startIncluded && !s2.startIncluded) {

                }

            } else if (s1.end > s2.end) {
                if (s1.startIncluded && s2.startIncluded) {

                } else if (s1.startIncluded && !s2.startIncluded) {

                } else if (!s1.startIncluded && s2.startIncluded) {

                } else if (!s1.startIncluded && !s2.startIncluded) {

                }

            }
        } else if (s1.start > s2.start) {
            return split(s2, s1, segments);
        }

        //wont reach till here
        return null;


    }
}
