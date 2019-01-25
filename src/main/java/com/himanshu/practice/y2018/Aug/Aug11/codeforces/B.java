package com.himanshu.practice.y2018.Aug.Aug11.codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 11/08/18.
 */
public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int p[] = new int[n + 1];


        String str[] = br.readLine().split(" ");
        for (int i = 0; i < str.length; i++) {
            p[i + 1] = Integer.parseInt(str[i]);
        }


        for (int i = 1; i <= n; i++) {
            int[] visited = new int[n + 1];
            System.out.print(getCulpit(p, i, visited) + " ");
        }


    }

    private static int getCulpit(int[] p, int i, int[] visited) {
        if (visited[i] == 1) {
            return i;
        }
        visited[i] = 1;
        return getCulpit(p, p[i], visited);
    }
}
