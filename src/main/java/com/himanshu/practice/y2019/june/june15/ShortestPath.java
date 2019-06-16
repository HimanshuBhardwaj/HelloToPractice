package com.himanshu.practice.y2019.june.june15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Stack;

/**
 * Created by himanshubhardwaj on 15/06/19.
 */
public class ShortestPath {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);
        int kk = Integer.parseInt(str[2]);

        long DP[][] = new long[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    DP[i][j] = 0;
                } else {
                    DP[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            str = br.readLine().split(" ");
            int s = Integer.parseInt(str[0]) - 1;
            int d = Integer.parseInt(str[1]) - 1;
            DP[s][d] = 1;
            DP[d][s] = 1;
        }

        HashSet<String> forbiddenTriplet = new HashSet<>();

        for (int i = 0; i < kk; i++) {
            str = br.readLine().split(" ");
            String s = (Integer.parseInt(str[0]) - 1) + " ";
            s = s + (Integer.parseInt(str[1]) - 1) + " ";
            s = s + (Integer.parseInt(str[2]) - 1);
            forbiddenTriplet.add(s);
        }

        System.out.println(forbiddenTriplet);

        int[] parent = new int[n];


        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    String s = i + " " + k + " " + j;
                    if (forbiddenTriplet.contains(s)) {
                        System.out.println(s);
                    }
                    if (!forbiddenTriplet.contains(s)) {
                        if (DP[i][j] > DP[i][k] + DP[k][j]) {
                            DP[i][j] = DP[i][k] + DP[k][j];
                            parent[k] = i;
                            parent[j] = k;
                        }
                    }
                }
            }
        }

        System.out.println(" ... ");

        if (DP[0][n - 1] == Integer.MAX_VALUE) {
            System.out.print(-1);
        } else {
            System.out.println(DP[0][n - 1] + "  ... " + n);
            for (int i = 0; i < n; i++) {
                System.out.println(i + "\t" + parent[i]);
            }

//            Stack<Integer> stack = new Stack<>();
//            int node = n - 1;
//            stack.push(node);
//
//            while (node != 0) {
//                node = parent[node];
//                stack.push(node);
//                System.out.println(node);
//            }
//
//            StringBuffer sb = new StringBuffer();
//
//            while (!stack.isEmpty()) {
//                sb.append(stack.pop() + " ");
//            }
//            System.out.println(sb.toString());
        }

    }
}


