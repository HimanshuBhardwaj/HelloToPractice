//package com.himanshu.practice.y2018.Aug.Aug11.codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Created by himanshubhardwaj on 11/08/18.
 */
public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");

        int n = Integer.parseInt(str[0]);
        int h = Integer.parseInt(str[1]);

        int a = Integer.parseInt(str[2]);
        int b = Integer.parseInt(str[3]);
        int k = Integer.parseInt(str[4]);
        Point[] p = new Point[2];

        for (int i = 0; i < k; i++) {
            str = br.readLine().split(" ");
            p[0] = new Point(Integer.parseInt(str[0]), Integer.parseInt(str[1]));
            p[1] = new Point(Integer.parseInt(str[2]), Integer.parseInt(str[3]));
            if (p[0].t == p[1].t) {
                System.out.println(Math.abs(p[0].h - p[1].h));
                continue;
            }

            if ((p[0].h >= a && p[0].h <= b) || (p[1].h >= a && p[1].h <= b)) {
                System.out.println((Math.abs(p[0].t - p[1].t) + Math.abs(p[0].h - p[1].h)));
            } else if (p[1].h < a) {
                System.out.println((Math.abs(p[0].t - p[1].t) + Math.abs(p[0].h - a) + Math.abs(p[1].h - a)));
            } else if (p[1].h > b) {
                System.out.println((Math.abs(p[0].t - p[1].t) + Math.abs(p[0].h - b) + Math.abs(p[1].h - b)));
            }
        }
    }
}


class Point implements Comparable<Point> {
    long t;
    long h;

    @java.beans.ConstructorProperties({"t", "h"})
    public Point(int t, int h) {
        this.t = (long) t;
        this.h = (long) h;
    }

    public String toString() {
        return "Point(t=" + this.t + ", h=" + this.h + ")";
    }

    @Override
    public int compareTo(Point o) {
        return (int) (this.t - o.t);
    }
}