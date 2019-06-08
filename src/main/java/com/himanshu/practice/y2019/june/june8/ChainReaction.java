package com.himanshu.practice.y2019.june.june8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

/**
 * Created by himanshubhardwaj on 08/06/19.
 * Statement: https://codeforces.com/contest/607/problem/A
 * Algo: DP; I assumed that input is sorted by position but in actual it was not
 * Submission1: https://codeforces.com/contest/607/submission/55303242
 * Parallel sort is a little faster than normal sort of Arrays
 */
public class ChainReaction {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Beacon[] beacons = new Beacon[n];
        TreeSet<Beacon> treeBeacon = new TreeSet<>();

        for (int i = 0; i < n; i++) {
            String[] str = br.readLine().split(" ");
            int a = Integer.parseInt(str[0]);
            int b = Integer.parseInt(str[1]);
            beacons[i] = new Beacon(i, a, b);
            treeBeacon.add(beacons[i]);
        }

        //hurstic
        Comparator<Beacon> comparator = new Comparator<Beacon>() {
            @Override
            public int compare(Beacon o1, Beacon o2) {
                return o1.position - o2.position;
            }
        };

        Arrays.parallelSort(beacons, comparator);
        for (int i = 0; i < n; i++) {
            beacons[i].index = i;
        }


        //-------------------------

        beacons[0].maxActiveIfActivated = 1;
        beacons[0].maxDestroyedIfActivated = n - beacons[0].maxActiveIfActivated;
        Beacon tempBeacon = new Beacon(-1, -1, -1);
        int max = beacons[0].maxActiveIfActivated;

        for (int i = 1; i < n; i++) {
            tempBeacon.position = beacons[i].position - beacons[i].power;
            Beacon beacon = treeBeacon.lower(tempBeacon);

            if (beacon == null) {
                beacons[i].maxActiveIfActivated = 1;
                beacons[i].maxDestroyedIfActivated = n - beacons[i].maxActiveIfActivated;
            } else {
                beacons[i].maxActiveIfActivated = 1 + beacon.maxActiveIfActivated;
                beacons[i].maxDestroyedIfActivated = n - beacons[i].maxActiveIfActivated;
            }
            //System.out.println(i + "\t" + beacons[i].maxActiveIfActivated + "\t" + beacons[i].maxDestroyedIfActivated);
        }

        int minDestroyed = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            minDestroyed = Math.min(minDestroyed, beacons[i].maxDestroyedIfActivated);
        }

        System.out.print(minDestroyed);

    }
}


class Beacon implements Comparable<Beacon> {
    int index;
    int position;
    int power;
    int maxActiveIfActivated;
    int maxDestroyedIfActivated;

    @java.beans.ConstructorProperties({"index", "position", "power"})
    public Beacon(int index, int position, int power) {
        this.index = index;
        this.position = position;
        this.power = power;
    }

    @Override
    public int compareTo(Beacon o) {
        return this.position - o.position;
    }
}

/*
7
1 1
2 1
3 1
4 1
5 1
6 6
7 7
* */