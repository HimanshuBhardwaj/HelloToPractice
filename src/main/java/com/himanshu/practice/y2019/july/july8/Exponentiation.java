package com.himanshu.practice.y2019.july.july8;

/**
 * Created by himanshubhardwaj on 08/07/19.
 */
public class Exponentiation {
    public static void main(String[] args) {
        double number = 1.000013;
        long startF = System.currentTimeMillis();
        double output11 = ExponentiationUtils.expo(number, 10000007);
        double output12 = ExponentiationUtils.expo(number, -10000007);
        long endF = System.currentTimeMillis();

        long startS = System.currentTimeMillis();
        double output21 = 1;
        double output22 = 1;
        for (int i = 0; i < 10000007; i++) {
            output21 *= number;
        }
        for (int i = 0; i < 10000007; i++) {
            output22 /= number;
        }
        long endS = System.currentTimeMillis();

        System.out.println(((endF - startF)) + "\t" + output11 + "," + output12);
        System.out.println(((endS - startS)) + "\t" + output21 + "," + output22);

    }
}


class ExponentiationUtils {
    public static double expo(double value, int pow) {
        if (pow > 0) {
            return expoPositive(value, pow);
        } else if (pow < 0) {
            return exponegative(value, Math.abs(pow));
        } else {
            return 1;
        }
    }

    private static double expoPositive(double value, int pow) {
        if (pow == 0) {
            return 1;
        }

        double halfPow = expoPositive(value, pow / 2);
        return halfPow * halfPow * ((pow % 2 == 1) ? value : 1);
    }

    private static double exponegative(double value, int pow) {
        if (pow == 0) {
            return 1;
        }

        double halfPow = exponegative(value, pow / 2);
        return halfPow * halfPow * ((pow % 2 == 1) ? 1 / value : 1);
    }


}