package com.himanshu.practice.y2018.Aug.aug13.educationalround;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by himanshubhardwaj on 13/08/18.
 * Statement: https://codeforces.com/contest/598/problem/C
 * TODO: Correct it, Getting wrong answer
 */
public class NearestVectors {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        String[] str;
        List<Point> points = new ArrayList<>(n);

        for (int i = 0; i < n; i++) {
            str = br.readLine().split(" ");
            points.add(i, new Point(i + 1, Integer.parseInt(str[0]), Integer.parseInt(str[1])));
        }
        Collections.sort(points);

        double currentMinAngleDiff = Double.MAX_VALUE;
        int indexA = 0;
        int indexB = 0;

        for (int i = 1; i < points.size(); i++) {
            double angleDiff = computeAngle(points.get(i), points.get(i - 1));
            if (Double.compare(currentMinAngleDiff, angleDiff) == 1) {
                indexA = points.get(i - 1).index;
                indexB = points.get(i).index;
                currentMinAngleDiff = angleDiff;
            }
        }
        System.out.print(indexA + " " + indexB);
    }

    //returns angle in double
    static public double computeAngle(Point aa, Point bb) {
        if (aa.x == bb.x && aa.y == bb.y) {
            return 0d;
        }

        double a = Math.sqrt(Math.pow(aa.x - bb.x, 2) + Math.pow(aa.y - bb.y, 2));
        double b = Math.sqrt(Math.pow(aa.x, 2) + Math.pow(aa.y, 2));
        double c = Math.sqrt(Math.pow(bb.x, 2) + Math.pow(bb.y, 2));
        double comp = (b * b + c * c - a * a) / (2 * b * c);
        double angle = (Math.acos(comp) * 180) / Math.PI;
        return Math.min(angle, 360 - angle);
    }
}

class Point implements Comparable<Point> {
    int index;
    long x;
    long y;
    double angle;
    static Point bb = new Point(-1, 1, 0);

    public Point(int index, int x, int y) {
        this.index = index;
        this.x = x;
        this.y = y;
        if (index >= 0) {
            this.angle = computeAngle();
        }
    }

    public double computeAngle() {
        Point aa = this;

        double a = Math.sqrt(Math.pow(aa.x - bb.x, 2) + Math.pow(aa.y - bb.y, 2));
        double b = Math.sqrt(Math.pow(aa.x, 2) + Math.pow(aa.y, 2));
        double c = Math.sqrt(Math.pow(bb.x, 2) + Math.pow(bb.y, 2));
        double comp = ((b * b) + (c * c) - (a * a)) / (2 * b * c);

        if (aa.y >= 0) {
            return (Math.acos(comp) * 180) / Math.PI;
        } else {
            return (360 - ((Math.acos(comp) * 180) / Math.PI));
        }
    }

    @Override
    public int compareTo(Point o) {
        return Double.compare(this.angle, o.angle);
    }

    public String toString() {
        return "Point(index=" + this.index + ", x=" + this.x + ", y=" + this.y + ", angle=" + this.angle + ")";
    }
}