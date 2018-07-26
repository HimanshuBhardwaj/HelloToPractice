//package com.himanshu.practice.july.july26;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;

/**
 * Created by himanshubhardwaj on 23/07/18.
 */
public class ChainReaction {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        Bomb[] b = new Bomb[n];
        TreeSet<Bomb> bT = new TreeSet<>();
        int DP[] = new int[n];


        for (int i = 0; i < n; i++) {
            String str[] = br.readLine().split(" ");
            b[i] = new Bomb(i, Integer.parseInt(str[0]), Integer.parseInt(str[1]));
            bT.add(b[i]);
        }

        //DP[i] --> maximum bombs which could be saved by activating ith bomb
        DP[0] = 1;

        for (int i = 1; i < n; i++) {
            Bomb justMinBomb = bT.lower(new Bomb(i, b[i].pos - b[i].power, 0));
            //System.out.println(i + " " + (b[i].pos - b[i].power) + " " + justMinBomb);
            if (justMinBomb == null) {
                DP[i] = 1;
            } else {
                DP[i] = 1 + DP[justMinBomb.index];
            }
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, DP[i]);
        }

        System.out.print((n-max));
    }
}

class Bomb implements Comparable<Bomb> {
    int index;
    int pos;
    int power;

    @java.beans.ConstructorProperties({"index", "pos", "power"})
    public Bomb(int index, int pos, int power) {
        this.index = index;
        this.pos = pos;
        this.power = power;
    }

    @Override
    public int compareTo(Bomb o) {
        return this.pos - o.pos;
    }

    public String toString() {
        return "Bomb(index=" + this.index + ", pos=" + this.pos + ", power=" + this.power + ")";
    }
}