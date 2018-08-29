package com.himanshu.practice.Aug.aug29;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;

/**
 * Created by himanshubhardwaj on 29/08/18.
 * ProblemSet: https://codeforces.com/contest/702/problem/C
 * Algo: Binary search
 * Submission: https://codeforces.com/contest/702/submission/42248265
 */
public class CellularCommunication {
    static int[] re;
    static int[] tow;
    static TreeSet<Integer> towers = new TreeSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");

        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);

        re = new int[n];
        tow = new int[m];

        str = br.readLine().split(" ");

        for (int i = 0; i < str.length; i++) {
            re[i] = Integer.parseInt(str[i]);
        }

        str = br.readLine().split(" ");

        for (int i = 0; i < str.length; i++) {
            tow[i] = Integer.parseInt(str[i]);
            towers.add(tow[i]);
        }

        System.out.print(getMaximumDis());

    }


    public static int getMaximumDis() {
        int dis = 0;

        for (int i = 0; i < re.length; i++) {
            Integer floorO = towers.floor(re[i]);
            Integer ceilO = towers.ceiling(re[i]);

            if (floorO == null && ceilO != null) {
                dis = Math.max(dis, Math.abs(re[i] - ceilO));
            } else if (floorO != null && ceilO == null) {
                dis = Math.max(dis, Math.abs(re[i] - floorO));
            } else {
                dis = Math.max(dis, Math.min(Math.abs(re[i] - floorO), Math.abs(re[i] - ceilO)));
            }
        }
        return dis;
    }
}
