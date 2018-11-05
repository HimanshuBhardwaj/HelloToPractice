package com.himanshu.practice.nov.nov5;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by himanshubhardwaj on 06/11/18.
 * Statement: https://codeforces.com/contest/837/problem/C
 * Algo: Bruteforce; Geometry
 * Submission: https://codeforces.com/contest/837/submission/45340169
 */
public class TwoSeals {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");

        int n = Integer.parseInt(str[0]);
        int a = Integer.parseInt(str[1]);
        int b = Integer.parseInt(str[2]);

        ArrayList<Seal> seals = new ArrayList();

        for (int i = 0; i < n; i++) {
            str = br.readLine().split(" ");
            seals.add(new Seal(Integer.parseInt(str[0]), Integer.parseInt(str[1])));
        }


        int max_area = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                max_area = Math.max(max_area, getSealStampArea(seals.get(i), seals.get(j), a, b, 0));
            }
        }

        System.out.print(max_area);

    }

    private static int getSealStampArea(Seal sealA, Seal sealB, int a, int b, int pos) {
        if (pos == 2) {
            return 0;
        }
        int max_area = 0;
        boolean exp = ((sealA.x + sealB.x) <= a && Math.max(sealA.y, sealB.y) <= b) ||
                ((sealA.x + sealB.y) <= a && Math.max(sealA.y, sealB.x) <= b) ||
                ((sealA.y + sealB.x) <= a && Math.max(sealA.x, sealB.y) <= b) ||
                ((sealA.y + sealB.y) <= a && Math.max(sealA.x, sealB.x) <= b);
        if (exp) {
            max_area = Math.max(max_area, Seal.getArea(sealA) + Seal.getArea(sealB));
        }
        return Math.max(max_area, getSealStampArea(sealA, sealB, b, a, pos + 1));
    }
}


class Seal {
    int x;
    int y;

    public Seal(int x, int y) {
        this.x = x;
        this.y = y;
    }

    static int getArea(Seal s) {
        return s.x * s.y;
    }

}