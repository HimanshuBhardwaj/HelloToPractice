package com.himanshu.practice.y2018.nov.nov13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 13/11/18.
 */
public class VasyaAndTheMushrooms {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        long speed[][] = new long[2][n];
        long preFixSum[][] = new long[2][n];
        long suffixSumTimeIndependent[][] = new long[2][n];
        long suffixSumTimeDependent[][] = new long[2][n];

        String str[] = br.readLine().split(" ");

        for (int i = 0; i < str.length; i++) {
            speed[0][i] = Long.parseLong(str[i]);
        }

        str = br.readLine().split(" ");
        for (int i = 0; i < str.length; i++) {
            speed[1][i] = Long.parseLong(str[i]);
        }


        //computing prefix i*speed[i]
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < 2; j++) {
                    preFixSum[j][i] = count * speed[j][i];
                    count++;
                }
            } else {
                for (int j = 1; j >= 0; j--) {
                    preFixSum[j][i] = count * speed[j][i];
                    count++;
                }
            }
        }

        //computing suffix sum
        long commulativeSumIndependent = 0;
        long commulativeSumTimeDepenent = 0;
        count = 0;
        for (int i = 1; i >= 0; i--) {
            if (i == 1) {
                for (int j = 0; j < n; j++) {
                    commulativeSumIndependent += speed[i][j];
                    commulativeSumTimeDepenent += count * speed[i][j];

                    suffixSumTimeIndependent[i][j] = commulativeSumIndependent;
                    suffixSumTimeDependent[i][j] = commulativeSumTimeDepenent;
                    count++;
                }
            } else {
                for (int j = n; j >= 0; j--) {
                    commulativeSumIndependent += speed[i][j];
                    commulativeSumTimeDepenent += count * speed[i][j];
                    suffixSumTimeIndependent[i][j] = commulativeSumIndependent;
                    suffixSumTimeDependent[i][j] = commulativeSumTimeDepenent;
                    count++;
                }
            }
        }


        long max = Long.MIN_VALUE;

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < n; j++) {


            }
        }


    }
}


