//package com.himanshu.practice.july14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by himanshubhardwaj on 14/07/18.
 * TODO: Finish it later:
 * Link: http://codeforces.com/contest/1009/problem/C
 */
public class AnnoyingPresent {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);
        BigDecimal bigS = new BigDecimal(0);
        double sum = 0d;
        double tempMax = getMaximum(n);
        double tempMin = getMinimum(n);
        //System.out.println(tempMax);
        //System.out.println(tempMin);
        String[] temoS = new String[2];
        temoS[0] = "0";
        temoS[1] = "1000";
        double lS = 0;

        for (int i = 1; i <= m; i++) {
            str = br.readLine().split(" ");

            long x = Long.parseLong(str[0]);
            long d = Long.parseLong(str[1]);
            //System.out.println("..x=" + x + "\td=" + d);

            if (d >= 0) {
                sum += (x * n + (d * tempMax));
            } else {
                sum += (x * n + (d * tempMin));
            }
            if ((1 + i) % 1000 == 0) {
                lS += (sum / n);
                sum = 0;
            }
        }

        lS += (sum / n);

        BigDecimal bd = new BigDecimal(sum);
        System.out.printf("%.15f",lS);


    }

    private static double getMinimum(int n) {
        double pivot1 = 1;
        double sum = Double.MAX_VALUE;
        double tempSum = 0;

        if (isValidatedPivot(pivot1, n)) {
            for (int i = 1; i <= n; i++) {
                tempSum += Math.abs(i - pivot1);
            }
            sum = Math.min(sum, tempSum);
        }

        tempSum = 0;
        pivot1 = n;


        if (isValidatedPivot(pivot1, n)) {
            for (int i = 1; i <= n; i++) {
                tempSum += Math.abs(i - pivot1);
            }
            sum = Math.min(sum, tempSum);
        }
        tempSum = 0;
        pivot1 = Math.ceil((n + 1) / 2);

        if (isValidatedPivot(pivot1, n)) {
            for (int i = 1; i <= n; i++) {
                tempSum += Math.abs(i - pivot1);
            }

            sum = Math.min(sum, tempSum);
        }
        tempSum = 0;


        if (isValidatedPivot(pivot1, n) && (n % 2 == 0)) {
            pivot1 = Math.floor((n + 1) / 2);
            for (int i = 1; i <= n; i++) {
                tempSum += Math.abs(i - pivot1);
            }

            sum = Math.min(sum, tempSum);
        }
//        tempSum = 0;
//        pivot1 = Math.ceil((n - 1 / 2));
//
//        if (isValidatedPivot(pivot1, (int) n)) {
//            for (int i = 1; i <= n; i++) {
//                tempSum += Math.abs(i - pivot1);
//            }
//            sum = Math.min(sum, tempSum);
//        }
//        tempSum = 0;
//        pivot1 = Math.floor((n - 1) / 2);
//
//        if (isValidatedPivot(pivot1, (int) n)) {
//            for (int i = 1; i <= n; i++) {
//                tempSum += Math.abs(i - pivot1);
//            }
//            sum = Math.min(sum, tempSum);
//        }

        return sum;
    }

    private static double getMaximum(int n) {
        double pivot1 = 1;
        double sum = Double.MIN_VALUE;
        double tempSum = 0;

        if (isValidatedPivot(pivot1, n)) {
            for (int i = 1; i <= n; i++) {
                tempSum += Math.abs(i - pivot1);
            }
            sum = Math.max(sum, tempSum);
        }
        tempSum = 0;
        pivot1 = n;

        if (isValidatedPivot(pivot1, n)) {
            for (int i = 1; i <= n; i++) {
                tempSum += Math.abs(i - pivot1);
            }

            sum = Math.max(sum, tempSum);
        }
        tempSum = 0;
        pivot1 = Math.ceil((n + 1) / 2);

        if (isValidatedPivot(pivot1, n)) {
            for (int i = 1; i <= n; i++) {
                tempSum += Math.abs(i - pivot1);
            }

            sum = Math.max(sum, tempSum);
        }
        tempSum = 0;


        if (isValidatedPivot(pivot1, n) && (n % 2 == 0)) {
            pivot1 = Math.floor((n + 1) / 2);
            for (int i = 1; i <= n; i++) {
                tempSum += Math.abs(i - pivot1);
            }


            sum = Math.max(sum, tempSum);
        }
//        tempSum = 0;
//        pivot1 = Math.ceil((n - 1 / 2));
//
//        if (isValidatedPivot(pivot1, (int) n)) {
//            for (int i = 1; i <= n; i++) {
//                tempSum += Math.abs(i - pivot1);
//            }
//
//            sum = Math.max(sum, tempSum);
//        }
//        tempSum = 0;
//        pivot1 = Math.floor((n - 1) / 2);
//
//
//        if (isValidatedPivot(pivot1, (int) n)) {
//            for (int i = 1; i <= n; i++) {
//                tempSum += Math.abs(i - pivot1);
//            }
//
//            sum = Math.max(sum, tempSum);
//        }

        return sum;
    }

    static boolean isValidatedPivot(double p, int n) {
        if (p >= 1d && p <= (double) n) {
            return true;
        }
        return false;
    }
}
