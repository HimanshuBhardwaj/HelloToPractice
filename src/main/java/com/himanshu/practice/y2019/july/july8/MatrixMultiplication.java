package com.himanshu.practice.y2019.july.july8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 08/07/19.
 */
public class MatrixMultiplication {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);
        double a[][] = new double[n][m];

        for (int i = 0; i < n; i++) {
            str = br.readLine().split(" ");
            for (int j = 0; j < str.length; j++) {
                a[i][j] = Double.parseDouble(str[j]);
            }
        }

        str = br.readLine().split(" ");

        n = Integer.parseInt(str[0]);
        m = Integer.parseInt(str[1]);
        double b[][] = new double[n][m];

        for (int i = 0; i < n; i++) {
            str = br.readLine().split(" ");
            for (int j = 0; j < str.length; j++) {
                b[i][j] = Double.parseDouble(str[j]);
            }
        }

        MatrixUtils.printMatrix(MatrixUtils.matrixMultipy(a, b));

        long start = System.currentTimeMillis();
        double[][] temp = MatrixUtils.matrixpowerOptimised(a, 10000000);
        long end = System.currentTimeMillis();
        System.out.println(" Time matrixpowerOptimised:" + (end - start));

        double[][] temp1 = MatrixUtils.matrixpowerNormal(a, 10000000);


        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp[0].length; j++) {
                if (Double.compare(temp[i][j], temp1[i][j]) != 0) {
                    System.out.println("output not matching");
                    return;
                }
            }
        }
        System.out.println("output matching");


    }
}

class MatrixUtils {
    static double[][] matrixMultipy(double[][] a, double[][] b) {
        if (a == null || b == null || a.length == 0 || b.length == 0 || a[0].length == 0 || b[0].length == 0 || a[0].length != b.length) {
            return null;
        }

        double result[][] = new double[a.length][b[0].length];

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                for (int k = 0; k < b.length; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return result;
    }

    static void printMatrix(double[][] a) {
        if (a == null) {
            System.out.println("null matrix");
        }

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                System.out.print(a[i][j] + "\t");
            }
            System.out.println();
        }
    }


    static double[][] matrixpowerNormal(double[][] a, int n) {
        double[][] temp = a;
        long start = System.currentTimeMillis();
        if (n != 1) {
            temp = matrixMultipy(a, a);

            for (int i = 2; i < n; i++) {
                temp = matrixMultipy(temp, a);
            }
        }
        long end = System.currentTimeMillis();
        System.out.println(" Time matrixpowerNormal:" + (end - start));
        return temp;
    }

    static double[][] matrixpowerOptimised(double[][] a, int n) {
        if (n == 1) {
            return a;
        }

        double[][] temp = matrixpowerOptimised(a, n / 2);
        temp = matrixMultipy(temp, temp);
        if (n % 2 == 1) {
            temp = matrixMultipy(temp, a);
        }

        return temp;
    }


}


/*
5 5
1 2 3 4 5
5 6 3 1 2
1 2 34 5 6
1 2 3 1 2
0 1 2 30 0


 Time matrixpowerOptimised:1
 Time matrixpowerNormal:5330
* */