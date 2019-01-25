package com.himanshu.practice.y2018.july.july30;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * Created by himanshubhardwaj on 30/07/18.
 * Statement: https://codeforces.com/contest/908/problem/C
 * Algo: Geometry
 * Submission: https://codeforces.com/contest/908/submission/40977495
 */
public class NewYearAndCurling {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int r = Integer.parseInt((str[1]));
        LinkedList<Disk> ds = new LinkedList<>();
        str = br.readLine().split(" ");

        for (String xS : str) {
            Disk d = new Disk();
            d.x = Double.valueOf(xS);
            d.y = computeY(ds, d.x, r);
            ds.addLast(d);
        }

        for (Disk d : ds) {
            System.out.printf("%.12f ", d.y);
        }

    }

    private static double computeY(LinkedList<Disk> ds, double x, int r) {
        double yD = (double) r;

        for (Disk d : ds) {
            int distanceR = Double.compare(distance(d, x), 2 * r);
            if (distanceR < 0) {
                yD = Math.max(yD, newYCoordinate(d, x, r));
            } else if (distanceR == 0) {
                yD = Math.max(yD, d.y);
            }
        }
        return yD;
    }

    //you can assume that they will touch
    private static double newYCoordinate(Disk d, double x, int r) {
        return d.y + Math.sqrt((4 * r * r) - ((x - d.x) * (x - d.x)));
    }

    private static double distance(Disk d, double x) {
        return Math.abs(d.x - x);
    }
}


class Disk {
    double x;
    double y;

    public Disk() {
    }
}