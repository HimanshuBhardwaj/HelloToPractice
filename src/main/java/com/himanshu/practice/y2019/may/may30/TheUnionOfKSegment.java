package com.himanshu.practice.y2019.may.may30;

import lombok.Getter;
import lombok.ToString;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

/**
 * Created by himanshubhardwaj on 30/05/19.
 * //TODO: Complete it
 */
public class TheUnionOfKSegment {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int k = Integer.parseInt(str[1]);

        ArrayList<SegmentP> segments = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            str = br.readLine().split(" ");
            SegmentP segment = new SegmentP(Long.parseLong(str[0]), true);
            segments.add(segment);
            segment = new SegmentP(Long.parseLong(str[1]), false);
            segments.add(segment);
        }


        segments = reformSegment(segments);

        Collections.sort(segments);

        ArrayList<Segment> segmentList = new ArrayList<>();
        long start = -1;
        long end;

        int currentParallelSegment = 0;
        for (SegmentP s : segments) {
            if (s.isStart()) {
                currentParallelSegment++;
                if (currentParallelSegment == k) {
                    start = s.getIndex();
                }
            } else {
                currentParallelSegment--;

                if (currentParallelSegment == (k - 1)) {
                    end = s.getIndex();
                    segmentList.add(new Segment(start, end));
                }
            }
        }

        Collections.sort(segmentList);
        int indexEnd = 0;

        for (int i = 1; i < segmentList.size(); i++) {
            if (segmentList.get(i).getLength() == segmentList.get(0).getLength()) {
                indexEnd = i;
            }
        }
        System.out.println(segmentList);

        PrintWriter pw = new PrintWriter(System.out);

        for (int i = 0; i <= indexEnd; i++) {
            pw.append(segmentList.get(i).getStart() + " " + segmentList.get(i).getEnd());
            pw.append("\n");
        }

        pw.flush();
    }

    private static ArrayList<SegmentP> reformSegment(ArrayList<SegmentP> segments) {
//        HashMap<Long, SegmentP> StarthashMap = new HashMap<>();
//
//        for (SegmentP segmentP : segments) {
//            if (segmentP.isStart()) {
//                if (hashMap.get())
//            }
//
//        }
        return null;
    }
}


class SegmentP implements Comparable<SegmentP> {
    private final long index;
    private final boolean isStart;

    public SegmentP(long index, boolean isStart) {
        this.index = index;
        this.isStart = isStart;
    }

    @Override
    public int compareTo(SegmentP o) {
        return (int) (this.index - o.index);
    }

    public String toString() {
        return "SegmentP(index=" + this.index + ", isStart=" + this.isStart + ")\n";
    }

    public long getIndex() {
        return this.index;
    }

    public boolean isStart() {
        return this.isStart;
    }

}

@Getter
@ToString
class Segment implements Comparable<Segment> {
    private final long start;
    private final long end;
    private final long length;

    @java.beans.ConstructorProperties({"start", "end"})
    public Segment(long start, long end) {
        this.start = start;
        this.end = end;
        length = Math.abs(start - end);
    }

    @Override
    public int compareTo(Segment o) {
        return (int) (this.length - o.length);
    }


    public ArrayList<Segment> mergeSegment(ArrayList<Segment> segments) {
        Comparator<Segment> comparator = new Comparator<Segment>() {
            @Override
            public int compare(Segment o1, Segment o2) {
                return (int) (o1.end - o2.end);
            }
        };
        return null;
    }
}