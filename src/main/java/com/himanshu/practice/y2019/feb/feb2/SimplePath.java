package com.himanshu.practice.y2019.feb.feb2;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by himanshubhardwaj on 02/02/19.
 */
public class SimplePath {
    public static void main(String[] args) {
        SimplePath simplePath = new SimplePath();
        int[] length = {2,2,1,2};
        System.out.println(simplePath.trouble("NWES", length));
    }

    public int trouble(String direction, int[] length) {
        Segment segments[] = new Segment[length.length];


        for (int i = 0; i < length.length; i++) {
            segments[i] = Segment.newSegment(direction.charAt(i), (i == 0) ? null : segments[i - 1], length[i]);
            System.out.println(segments[i]);
            for (int j = 0; j < i; j++) {
                if (Segment.isInterSecting(segments[i], segments[j])) {
                    System.out.println();
                    return j;
                }
            }
        }
        return -1;
    }
}


@ToString
@AllArgsConstructor
class Segment {
    int startX;
    int startY;
    int endX;
    int endY;


    static boolean isInterSecting(Segment segment1, Segment segment2) {
        return (isLineSegmentIntersecting(segment1.startX, segment1.endX, segment2.startX, segment2.endX) &&
                isLineSegmentIntersecting(segment1.startY, segment1.endY, segment2.startY, segment2.endY));
    }


    static boolean isLineSegmentIntersecting(int startA, int endA, int startB, int endB) {


        if (startA > endA) {
            return isLineSegmentIntersecting(endA,startA,startB,endB);
        }

        if (endB < startB) {
            return isLineSegmentIntersecting(startA,endA,endB,startB);
        }


        if (startA > startB) {
            return isLineSegmentIntersecting(startB, endB, startA, endA);
        }
        return endA >= startB;
    }

    static Segment newSegment(char direction, Segment oldSegment, int length) {
        int startX = 0;
        int startY = 0;

        if (oldSegment != null) {
            startX = oldSegment.endX;
            startY = oldSegment.endY;
        }

        int endX = startX;
        int endY = startY;

        switch (direction) {
            case 'N':
                endY += length;
                break;
            case 'E':
                endX += length;
                break;
            case 'S':
                endY -= length;
                break;
            case 'W':
                endX -= length;
                break;
        }
        return new Segment(startX,startY, endX, endY);
    }
}
