//package com.himanshu.practice.july.july17;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by himanshubhardwaj on 18/07/18.
 * Statement: http://codeforces.com/contest/14/problem/C
 * Algo: Timepass
 * http://codeforces.com/contest/14/submission/40495651
 */
public class FourSegments {
    public static void main(String[] args) throws IOException {
        int x1, y1, x2, y2;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Line> lines = new ArrayList<>();
        String str[];
        ArrayList<Point> points = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            str = br.readLine().split(" ");
            x1 = Integer.parseInt(str[0]);
            y1 = Integer.parseInt(str[1]);
            x2 = Integer.parseInt(str[2]);
            y2 = Integer.parseInt(str[3]);
            Line line = new Line(x1, y1, x2, y2);
            lines.add(line);
        }

        if (Line.isRectangle(lines)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}


class Line {
    Point p1;
    Point p2;
    float slope;
    float intercept;

    public Line(float slope, float intercept) {
        this.slope = slope;
        this.intercept = intercept;
    }


    public Line(int x1, int y1, int x2, int y2) {
        p1 = new Point(x1, y1);
        p2 = new Point(x2, y2);
        setSlopeAndInterSection();
    }


//    public boolean equals(Object anObject) {
//        boolean result = false;
//        if (anObject == this) {
//            result = true;
//        }
//
//        if (anObject instanceof Line) {
//            Line newLine = (Line) anObject;
//            if (newLine.intercept == this.intercept && newLine.slope == this.slope) {
//                result = true;
//            }
//
//        }
//
//        System.out.println(this + "\t" + anObject + "\t" + result);
//        return result;
//    }

    public void setSlopeAndInterSection() {
        if (this.p2.x != this.p1.x) {
            this.slope = (this.p2.y - this.p1.y) / (this.p2.x - this.p1.x);
            this.intercept = this.p1.y - slope * this.p1.x;
        } else {
            this.intercept = this.p1.x;
            this.slope = -1;
        }
    }

    //returns trye is line intersection forms a rectangle
    public static boolean isRectangle(ArrayList<Line> lines) {
        if (!checkIfParallelToAxis(lines)) {
            return false;
        }

        double d[] = new double[2];
        d[0] = -1;
        d[1] = -1;
        int count = 0;
        for (int i = 0; i < lines.size(); i++) {
            for (int j = i + 1; j < lines.size(); j++) {
                if (lines.get(i).slope == lines.get(j).slope) {
                    if (count == 2) {
                        return false;
                    }
                    d[count] = Math.abs(lines.get(i).intercept - lines.get(j).intercept);
                    count++;
                }
            }
        }

        if (d[0] > 0 || d[1] > 0) {
            return true;
        }
        return false;
    }

    private static boolean checkIfParallelToAxis(ArrayList<Line> lines) {
        int slopeZero = 0;
        int slopeINF = 0;

        if (lines.size() != 4) {
            return false;
        }
        HashMap<Point, Integer> occurence = new HashMap<>();


        for (int i = 0; i < lines.size(); i++) {
            //testHashMap(occurence, lines.get(i).p1);
            if (occurence.containsKey(lines.get(i).p1)) {
                occurence.put(lines.get(i).p1, 1 + occurence.get(lines.get(i).p1));
            } else {
                occurence.put(lines.get(i).p1, 1);
            }


            //testHashMap(occurence, lines.get(i).p2);

            if (occurence.containsKey(lines.get(i).p2)) {
                occurence.put(lines.get(i).p2, 1 + occurence.get(lines.get(i).p2));
            } else {
                occurence.put(lines.get(i).p2, 1);
            }


            for (int j = i + 1; j < lines.size(); j++) {
                if (lines.get(i).equals(lines.get(j))) {
                    return false;
                }
            }
        }

//        System.out.println(occurence);

        for (Map.Entry<Point, Integer> entry : occurence.entrySet()) {
            if (entry.getValue() != 2) {
                return false;
            }
        }

        for (Line l : lines) {
            //System.out.println(l + "..");
            if (l.slope == 0) {
                slopeZero++;
            } else if (l.slope == -1) {
                slopeINF++;
            } else {
                return false;
            }
        }

        if (slopeINF == 2 && slopeZero == 2) {
            return true;
        }

        return false;
    }


    public static void testHashMap(HashMap<Point, Integer> occurence, Point p1) {
        System.out.println(occurence + "\t\t" + p1 + "\t\t" + occurence.containsKey(p1));
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Line)) return false;
        final Line other = (Line) o;
        if (!other.canEqual((Object) this)) return false;
        if (Float.compare(this.slope, other.slope) != 0) return false;
        if (Float.compare(this.intercept, other.intercept) != 0) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + Float.floatToIntBits(this.slope);
        result = result * PRIME + Float.floatToIntBits(this.intercept);
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof Line;
    }

    public String toString() {
        return "Line(p1=" + this.p1 + ", p2=" + this.p2 + ", slope=" + this.slope + ", intercept=" + this.intercept + ")";
    }
}


class Point {
    float x;
    float y;

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "Point(x=" + this.x + ", y=" + this.y + ")";
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Point)) return false;
        final Point other = (Point) o;
        if (!other.canEqual((Object) this)) return false;
        if (Float.compare(this.x, other.x) != 0) return false;
        if (Float.compare(this.y, other.y) != 0) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + Float.floatToIntBits(this.x);
        result = result * PRIME + Float.floatToIntBits(this.y);
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof Point;
    }

//    public boolean equals(Object anObject) {
//        boolean result = false;
//        if (this == anObject) {
//            result = true;
//        }
//
//        if (anObject instanceof Point) {
//            Point newPoint = (Point) anObject;
//
//            if (Float.valueOf(newPoint.x).equals(this.x) && Float.valueOf(newPoint.y).equals(this.y)) {
//                result = true;
//            }
//        }
//
//        System.out.println(this + "\t" + anObject + "\t" + result);
//        return false;
//    }
//

}