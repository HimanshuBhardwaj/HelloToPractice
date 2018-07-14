package com.himanshu.practice.july14;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 13/07/18.
 * https://codeforces.com/contest/1008/problem/B
 * Ad-hoc
 * https://codeforces.com/contest/1008/submission/40279044
 */
public class TurnTheRectangles {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Rect r = new Rect(0, 0);


        int currentHeight = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            String str[] = br.readLine().split(" ");
            r.width = Integer.parseInt(str[0]);
            r.height = Integer.parseInt(str[1]);
            currentHeight = r.diesSatisfy(r, currentHeight);

            if (currentHeight == -1) {
                currentHeight = Integer.MIN_VALUE;
            }
        }

        if (currentHeight != Integer.MIN_VALUE) {
            System.out.print("YES");
        } else {
            System.out.print("NO");
        }

    }
}

class Rect {
    int width;
    int height;

    public Rect(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int diesSatisfy(Rect a, int previousMaximumHeight) {
        int newPreviousMaximumHeight = -1;
        int small;
        int big;
        if (width < height) {
            small = width;
            big = height;
        } else {
            small = height;
            big = width;
        }


        if (big <= previousMaximumHeight) {
            return big;
        }


        if (small <= previousMaximumHeight) {
            return small;
        }


        return newPreviousMaximumHeight;
    }
}