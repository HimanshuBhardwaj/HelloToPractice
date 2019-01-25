package com.himanshu.practice.y2018.july.july10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Himanshu Bhardwaj on 10/07/18.
 * Algo: Transitive Closure
 * Submission: https://codeforces.com/contest/27/submission/40157471
 */
public class Tournament {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int adj[][] = new int[n][n];
        int count[] = new int[n];

        for (int i = 1; i < ((n - 1) * n) / 2; i++) {
            String string[] = br.readLine().split(" ");
            int source = Integer.parseInt(string[0]);
            int destination = Integer.parseInt(string[1]);
            source--;
            destination--;
            count[source]++;
            count[destination]++;
            adj[source][destination] = 1;
            //System.out.println(source + " " + destination);
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (adj[i][k] == 1 && adj[k][j] == 1) {
                        adj[i][j] = 1;
                    }
                }
            }
        }

//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                System.out.print(adj[i][j] + " ");
//            }
//            System.out.println();
//        }
//        System.out.println();
        int exp = n - 1;
        int souDest[] = new int[2];
        int pos = 0;
        for (int i = 0; i < n; i++) {
            if (count[i] != exp) {
                souDest[pos] = i;
                pos++;
            }
        }

        if (adj[souDest[0]][souDest[1]] == 1) {
            System.out.println((souDest[0] + 1) + " " + (souDest[1] + 1));
        } else {
            System.out.println((souDest[1] + 1) + " " + (souDest[0] + 1));
        }

    }
}
