package com.himanshu.practice.y2018.nov.nov17;

import java.io.IOException;

/**
 * Created by himanshubhardwaj on 16/11/18.
 */
public class B {
    public static void main(String[] args) throws IOException {
        B b = new B();
        System.out.println(b.solve(5, 10, 2, 1));
        System.out.println(b.solve(3, 10, 4, 2));
    }

    public int solve(int n, int m, int x, int y) {
        long lx = x;
        long ln = n;
        long lm = m;
        long ly = y;
        long chockTosell = (long) Math.ceil(((double) ((lx * ln) - lm)) / ((double) (lx + ly)));
        return (int) (ln - chockTosell);
    }
}
