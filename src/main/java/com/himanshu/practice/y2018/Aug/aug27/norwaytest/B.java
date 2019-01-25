package com.himanshu.practice.y2018.Aug.aug27.norwaytest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * Created by himanshubhardwaj on 27/08/18.
 */
public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        String st[];
        int count = 1;

        while (s != null && s.length() != 0) {
            st = s.split(" ");
            long a = Long.parseLong(st[0]);
            long b = Long.parseLong(st[1]);
            s = br.readLine();
            st = s.split(" ");
            long c = Long.parseLong(st[0]);
            long d = Long.parseLong(st[1]);
            long det = (a * d - (b * c));
            long x = d / det;
            long y = (-b) / det;
            long z = (-c) / det;
            long t = a / det;
            System.out.println("Case " + count+":");
            System.out.println(x + " " + y);
            System.out.println(z + " " + t);
            br.readLine();
            s = br.readLine();
            count++;
        }
    }
}
