package com.himanshu.practice.july.july29.codejam;

import lombok.ToString;

import java.io.*;
import java.util.Arrays;
import java.util.TreeSet;

/**
 * Created by himanshubhardwaj on 29/07/18.
 */
public class Paragliding {

    public static void main(String[] args) throws IOException {
        File file = new File("/Users/himanshubhardwaj/Desktop/B-small-attempt1.in");
        FileWriter fw = new FileWriter("/Users/himanshubhardwaj/Desktop/output.out");
        BufferedReader br = new BufferedReader(new FileReader(file));
        int t = Integer.parseInt(br.readLine());
        String str[];


        for (int testCase = 1; testCase <= t; testCase++) {
            str = br.readLine().split(" ");
            int n = Integer.parseInt(str[0]);
            int k = Integer.parseInt(str[1]);
            Point poi[] = new Point[k + 1];
            Tower toi[] = new Tower[n + 1];

            for (int i = 1; i <= n; i++) {
                toi[i] = new Tower();
            }

            for (int i = 1; i <= k; i++) {
                poi[i] = new Point();
            }


            long p[] = new long[n + 1];
            long h[] = new long[n + 1];
            long x[] = new long[k + 1];
            long y[] = new long[k + 1];


            //-----p
            str = br.readLine().split(" ");
            p[1] = Long.parseLong(str[0]);
            p[2] = Long.parseLong(str[1]);
            toi[1].p = p[1];
            toi[2].p = p[2];
            long A = Long.parseLong(str[2]);
            long B = Long.parseLong(str[3]);
            long C = Long.parseLong(str[4]);
            long M = Long.parseLong(str[5]);


            for (int i = 3; i <= n; i++) {
                p[i] = (((A * p[i - 1]) + (B * p[i - 2]) + C) % (M)) + 1;
                toi[i].p = p[i];
            }


            //-------h

            str = br.readLine().split(" ");
            h[1] = Long.parseLong(str[0]);
            h[2] = Long.parseLong(str[1]);
            toi[1].h = h[1];
            toi[2].h = h[2];
            A = Long.parseLong(str[2]);
            B = Long.parseLong(str[3]);
            C = Long.parseLong(str[4]);
            M = Long.parseLong(str[5]);


            for (int i = 3; i <= n; i++) {
                h[i] = (((A * h[i - 1]) + (B * h[i - 2]) + C) % (M)) + 1;
                toi[i].h = h[i];
            }


            //x

            str = br.readLine().split(" ");
            x[1] = Long.parseLong(str[0]);
            x[2] = Long.parseLong(str[1]);
            poi[1].x = x[1];
            poi[2].x = x[2];
            A = Long.parseLong(str[2]);
            B = Long.parseLong(str[3]);
            C = Long.parseLong(str[4]);
            M = Long.parseLong(str[5]);


            for (int i = 3; i <= k; i++) {
                x[i] = (((A * x[i - 1]) + (B * x[i - 2]) + C) % (M)) + 1;
                poi[i].x = x[i];
            }


            //y


            str = br.readLine().split(" ");
            y[1] = Long.parseLong(str[0]);
            y[2] = Long.parseLong(str[1]);
            poi[1].y = y[1];
            poi[2].y = y[2];
            A = Long.parseLong(str[2]);
            B = Long.parseLong(str[3]);
            C = Long.parseLong(str[4]);
            M = Long.parseLong(str[5]);


            for (int i = 3; i <= k; i++) {
                y[i] = (((A * y[i - 1]) + (B * y[i - 2]) + C) % (M)) + 1;
                poi[i].y = y[i];
            }


            fw.write("Case #" + testCase + ": " + getCollectedBallons(toi, poi) + "\n");
            System.out.println("Case #" + testCase + ": " + getCollectedBallons(toi, poi));
        }

        fw.close();

    }

    private static int getCollectedBallons(Tower[] towers, Point[] points) {
        int count = 0;
        for (Tower t : towers) {
            for (Point p : points) {
                if ((p != null && t != null) && (p.isCollected == false)) {
                    if (couldCollected(t, p)) {
                        p.isCollected = true;
                        count++;
                    }
                }
            }
        }
        return count;
    }

    private static boolean couldCollected(Tower t, Point p) {
        boolean result = false;
        if (t.p == p.x) {
            if (t.h >= p.y) {
                result = true;
            }
        } else if (t.p > p.x) {
            if ((p.y - p.x - t.h + t.p) <= 0) {
                result = true;
            }
        } else {
            if ((p.y + p.x - t.p - t.h) <= 0) {
                result = true;

            }
        }
        return result;
    }
}

@ToString
class Tower implements Comparable<Tower> {
    long p;
    long h;

    @Override
    public int compareTo(Tower o) {
        return (int) ((this.p - this.h) - (o.p - o.h));
    }
}


@ToString
class Point implements Comparable<Point> {
    long x;
    long y;
    boolean isCollected = false;

    @Override
    public int compareTo(Point o) {
        return (int) (this.x - o.x);
    }
}
