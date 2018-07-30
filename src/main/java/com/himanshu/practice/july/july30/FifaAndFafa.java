package com.himanshu.practice.july.july30;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 30/07/18.
 * Problem Statement: https://codeforces.com/contest/935/problem/C
 * Algo: Geometry
 * Submission: https://codeforces.com/contest/935/submission/40972907
 * In this problem i missed a lot of corner cases and it was only after looking them that I was being able to get it approved. Could have done better
 */
public class FifaAndFafa {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");
        double R = Double.parseDouble(str[0]);
        int x1 = Integer.parseInt(str[1]);
        int y1 = Integer.parseInt(str[2]);
        int x2 = Integer.parseInt(str[3]);
        int y2 = Integer.parseInt(str[4]);
        double absoluteDiff = Math.pow(0.1, 6);


        double D = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));

        if (x1 == x2 && y1 == y2) {
            double newX = x2 + (R / (2 * Math.sqrt(2)));
            double newY = y2 + (R / (2 * Math.sqrt(2)));
            print(newX, newY, R / 2 - (absoluteDiff * absoluteDiff));
            return;
        }
        if (Double.compare(D, R) > 0) {
            print(x1, y1, R);
            return;
        }


        //D is not zero


        double newx = x2 + (((x1 - x2) * (D + R)) / (2 * D));
        double newY = y2 + (((y1 - y2) * (R + D)) / (2 * D));
        double newRadius = (D + R) / 2;

        print(newx, newY, newRadius);


    }

    static void print(double x, double y, double r) {
        System.out.printf("%.12f %.12f %.12f", x, y, r);
    }
}
