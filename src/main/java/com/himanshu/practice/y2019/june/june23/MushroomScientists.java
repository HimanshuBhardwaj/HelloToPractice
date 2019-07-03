package com.himanshu.practice.y2019.june.june23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 23/06/19.
 */
public class MushroomScientists {
    static double logValue[] = new double[1001];

    public static void main(String[] args) throws IOException {

        //test if this can be done via another thread and if that reduces complexity
        for (int i = 2; i < logValue.length; i++) {
            logValue[i] = Math.log10(i);

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String st[] = br.readLine().split(" ");
            int s = Integer.parseInt(st[0]);

            st = br.readLine().split(" ");
            int a = Integer.parseInt(st[0]);
            int b = Integer.parseInt(st[1]);
            int c = Integer.parseInt(st[2]);

            double currentMax = Double.MIN_VALUE;


            for (int pX = 1; pX < s; pX++) {
                for (int pY = 1; pY + pX < s; pY++) {
                    int pZ = s - pX + pY;
                    double distance = a * logValue[pX] + b * logValue[pY] + c * logValue[pZ];
                    if (distance > currentMax) {

                    }
                }
            }


        }

    }
}

