package com.himanshu.practice.y2019.june.june8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;


/**
 * Created by himanshubhardwaj on 08/06/19.
 * Problem Statement: https://codeforces.com/contest/577/problem/B
 * Algo: DP, Pigenhole
 * Submission: https://codeforces.com/contest/577/submission/55313891
 */
public class ModuloSum {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");

        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);

        boolean presentMod[] = new boolean[m];

        for (int i = 0; i < m; i++) {
            presentMod[i] = false;
        }


        str = br.readLine().split(" ");
        Queue<Integer> added[] = new Queue[n];
        for (int i = 0; i < n; i++) {
            added[i] = new LinkedList<>();
        }

        if (n > m) {
            System.out.print("YES");
            return;
        }


        for (int i = 0; i < n; i++) {
            int num = (Integer.parseInt(str[i])) % m;
            if (!presentMod[num]) {
                added[i].add(num);
            }


            for (int j = 0; j < m; j++) {
                if (presentMod[j]) {
                    int pos = (j + num) % m;
                    if (presentMod[pos] == false) {
                        added[i].add(pos);
                    }
                }
            }

            while (!added[i].isEmpty()) {
                presentMod[added[i].poll()] = true;
            }

        }


        if (presentMod[0]) {
            System.out.print("YES");
        } else {
            System.out.print("NO");
        }
    }
}
