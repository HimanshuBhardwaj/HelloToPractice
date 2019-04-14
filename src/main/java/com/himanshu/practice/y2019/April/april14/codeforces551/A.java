package com.himanshu.practice.y2019.April.april14.codeforces551;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by himanshubhardwaj on 13/04/19.
 */
public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int t = Integer.parseInt(str[1]);
        ArrayList<Bus> buses = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            str = br.readLine().split(" ");
            int start = Integer.parseInt(str[0]);
            int freq = Integer.parseInt(str[1]);

            int index = (int) Math.ceil(((double) t - (double) start) / freq);


            //System.out.print("index: " + index + "\tstart: " + start + "freq: " + freq + "\t\t");

            int nearest = start + (index * freq) - t;
            if (index < 0) {
                nearest = start;
            }

            Bus b = new Bus(i + 1, start, freq, nearest);
            buses.add(b);
            //System.out.println(b);
        }

        Collections.sort(buses);
        System.out.print(buses.get(0).index);
    }
}


class Bus implements Comparable<Bus> {
    int index;
    int start;
    int freq;
    int nearestTime;

    @java.beans.ConstructorProperties({"index", "start", "freq", "nearestTime"})
    public Bus(int index, int start, int freq, int nearestTime) {
        this.index = index;
        this.start = start;
        this.freq = freq;
        this.nearestTime = nearestTime;
    }

    @Override
    public int compareTo(Bus o) {
        return this.nearestTime - o.nearestTime;
    }

    public String toString() {
        return "Bus(index=" + this.index + ", start=" + this.start + ", freq=" + this.freq + ", nearestTime=" + this.nearestTime + ")";
    }
}
