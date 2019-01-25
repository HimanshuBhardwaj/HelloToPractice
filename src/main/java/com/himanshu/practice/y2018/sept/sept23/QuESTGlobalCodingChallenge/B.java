package com.himanshu.practice.y2018.sept.sept23.QuESTGlobalCodingChallenge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by himanshubhardwaj on 23/09/18.
 * 0. I strongly believe that there is some issue with the test cases as my solution is correct;
 * 1. Algo problem statement is not clear as it doesnot tell about what to do when start of one it equal to end of other
 * 2. It is not clear is the output we have to print in new line or in same line??
 * 3. In question it is written as tie, but in outpurformat it is written as Tie??
 * 4.
 */


public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            String str[];
            int n = Integer.parseInt(br.readLine());
            ArrayList<Integer> alice = new ArrayList<>();
            ArrayList<Integer> bob = new ArrayList<>();

            ArrayList<Segment>[] disjointSegments = new ArrayList[n];
            ArrayList<Segment> segmentList = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                disjointSegments[i] = new ArrayList<>();
            }

            for (int i = 0; i < n; i++) {
                str = br.readLine().split(" ");
                Segment s = new Segment(Integer.parseInt(str[0]), Integer.parseInt(str[1]));
                segmentList.add(s);
            }


            Comparator<Segment> comparator = new Comparator<Segment>() {
                @Override
                public int compare(Segment o1, Segment o2) {
                    return (int) (o1.end - o2.end);
                }
            };

            Collections.sort(segmentList, comparator);
            //System.out.println("Segment List:\t" + segmentList);

            for (int i = 0; i < segmentList.size(); i++) {
                boolean listFound = false;
                for (int j = 0; (j < disjointSegments.length) && (!listFound); j++) {
                    if (Segment.isDisjoint(disjointSegments[j], segmentList.get(i))) {
                        listFound = true;
                        disjointSegments[j].add(segmentList.get(i));
                    }
                }
            }

            PriorityQueue<SegmentList> priorityQueue = new PriorityQueue<>();



            for (int i = 0; i < disjointSegments.length; i++) {
                if (disjointSegments[i].size() > 0) {
                    Collections.sort(disjointSegments[i], comparator);
                    priorityQueue.add(new SegmentList(disjointSegments[i]));
                }
            }


            int turn = 0;

            while (!priorityQueue.isEmpty()) {
                SegmentList seg = priorityQueue.poll();
                //System.out.println(seg);
                if (turn % 2 == 0) {
                    //alice
                    SegmentList aliceSegment = seg;
                    alice.add(aliceSegment.list.size());
                } else {
                    //bob
                    SegmentList bobSegment = seg;
                    bob.add(bobSegment.list.size());
                }
                turn++;
            }


            int B = (bob.size() > 0) ? bob.get(0) : 0;
            for (int i = 1; i < bob.size(); i++) {
                B ^= bob.get(i);
            }

            int A = (alice.size() > 0) ? alice.get(0) : 0;
            for (int i = 1; i < alice.size(); i++) {
                A ^= alice.get(i);
            }

            //System.out.println(A + "\t" + B);
            //System.out.println(alice + "\t" + bob);

            if (A > B) {
                System.out.print("Alice");
            } else if (B > A) {
                System.out.print("Bob");
            } else {
                System.out.print("tie");
            }

            if (t != 0) {
                System.out.println();
            }
        }


    }
}

class SegmentList implements Comparable<SegmentList> {
    ArrayList<Segment> list; //this list will always have atleast one segment, and are always in sorted format

    @java.beans.ConstructorProperties({"list"})
    public SegmentList(ArrayList<Segment> list) {
        this.list = list;
    }


    @Override
    public int compareTo(SegmentList o) {
        if (this.list.size() == o.list.size()) {
            return (int) (this.list.get(this.list.size() - 1).end - o.list.get(o.list.size() - 1).end);
        } else {
            return o.list.size() - this.list.size();//Assuming this will give us the list with maximum size firse;
        }
    }

    public String toString() {
        return "SegmentList(list=" + this.list + ")";
    }
}


class Segment implements Comparable<Segment> {
    long start;
    long end;

    @java.beans.ConstructorProperties({"start", "end"})
    public Segment(int start, int end) {
        this.start = start;
        this.end = end;
    }

    static boolean isDisjoint(ArrayList<Segment> list, Segment s) {
        for (Segment seg : list) {
            if (areOverlapping(seg, s)) {
                return false;
            }
        }
        return true;
    }

    private static boolean areOverlapping(Segment seg, Segment s) {
        ArrayList<Segment> tempSeg = new ArrayList<>();
        tempSeg.add(seg);
        tempSeg.add(s);
        Collections.sort(tempSeg);

        //TODO: Validate for equality
        if (tempSeg.get(1).start > tempSeg.get(0).end) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Segment o) {
        if (this.start != o.start) {
            return (int) (this.start - o.start);
        } else {
            return (int) (this.end - o.end);
        }
    }

    public String toString() {
        return "Segment(start=" + this.start + ", end=" + this.end + ")";
    }
}



/*
2
5
1 2
1 3
4 5
3 4
1 5
5
1 2
1 3
4 5
3 4
1 5
* */

/*

1
5
1 5
3 8
6 10
9 9
1 9

* */