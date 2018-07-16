package com.himanshu.practice.july14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

/**
 * Created by himanshubhardwaj on 14/07/18.
 * Algo: MAthematics
 * Link: http://codeforces.com/contest/1009/problem/C
 */
public class AnnoyingPresent {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);
        BigInteger sumB = new BigInteger("0");
        long tempMax = getMaximum(n);
        long tempMin = getMinimum(n);
        //System.out.println(tempMax);
        //System.out.println(tempMin);
        double lS = 0;

        for (int i = 1; i <= m; i++) {
            str = br.readLine().split(" ");

            long x = Long.parseLong(str[0]);
            long d = Long.parseLong(str[1]);

            long sum = 0;

            if (d >= 0) {
                sum = (x * n + (d * tempMax));
            } else {
                sum = (x * n + (d * tempMin));
            }

            sumB = sumB.add(BigInteger.valueOf(sum));
        }
        BigDecimal bd = new BigDecimal(sumB);
        System.out.println(bd.divide(BigDecimal.valueOf(n),15,BigDecimal.ROUND_CEILING));

    }

    private static long getMinimum(int n) {
        double pivot1 = 1;
        long sum = Long.MAX_VALUE;
        long tempSum = 0;

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

    private static long getMaximum(int n) {
        double pivot1 = 1;
        long sum = Long.MIN_VALUE;
        long tempSum = 0;

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
