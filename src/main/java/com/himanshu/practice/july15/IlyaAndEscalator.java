package com.himanshu.practice.july15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 16/07/18.
 * TODO: Finish it and understand
 * Submitting a wring anser so that I could teack it in future.
 */
public class IlyaAndEscalator {
    public static void main(String[] args) throws IOException {
        int n, t;
        double p;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");

        n = Integer.parseInt(str[0]);
        p = Double.parseDouble(str[1]);
        t = Integer.parseInt(str[2]);
        //System.out.println(n + " " + p + " " + t);
        getExpectedPeopleDP(n, p, t);
    }


    //this method is mentioned int he tutorial
    public static void getExpectedPeopleDP(int n, double p, int t) {
        double DP[][] = new double[n + 1][t + 1];//DP[i][j]:--> probablity of having ith person after j second

        DP[0][0] = 1;
        double prb = 1d - p;
        //this is absolutely correct

        for (int i = 1; i <= t; i++) {
            DP[0][i] = DP[0][i - 1] * (1d - p);
        }

        for (int person = 1; person <= n; person++) {
            for (int time = 1; time <= t; time++) {
                if (person <= time) {
                    DP[person][time] = (((1 - p) * DP[person][time - 1]) + (p * DP[person - 1][time - 1]));
                } else {
                    DP[person][time] = 0;
                }
            }
        }

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= t; j++) {
                System.out.print(DP[i][j] + "\t\t");
            }
            System.out.println();
        }


        double sum = 0;

        for (int i = 0; i <= t; i++) {
            //    System.out.println(DP[i][t]);
            sum += (i * DP[n][i]);
        }

        System.out.println(sum);
    }
}
