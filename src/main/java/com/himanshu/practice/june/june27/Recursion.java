package com.himanshu.practice.june.june27;

import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by Himanshu Bhardwaj on 27/06/18.
 */
public class Recursion {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringTokenizer st = new StringTokenizer(sc.next(), ",");
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] A = new int[1][k];
        st = new StringTokenizer(sc.next(), ",");
        for (int i = 0; i < k; i++) {
            A[0][k - i - 1] = Integer.parseInt(st.nextToken());
        }


        int C[][] = new int[k][k];
        st = new StringTokenizer(sc.next(), ",");

        for (int i = 0; i < k; i++) {
            C[i][0] = Integer.parseInt(st.nextToken());
            if (i != (k - 1)) {
                C[i][i + 1] = 1;
            }
        }

        for (int i = 0; i < k; i++) {
            System.out.print(A[0][i] + " ");
        }
        System.out.println();

        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                System.out.print(C[i][j] + "\t");
            }
            System.out.println();
        }


        int[][] exp = Matrix.pow(C, n - k+1);
        int[][] result = Matrix.matMuul(A, exp);

        System.out.println();
        System.out.println();
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                System.out.print(result[i][j] + "\t");
            }
            System.out.println();
        }

    }
}


class Matrix {
    static int[][] matMuul(int A[][], int B[][]) {
        int aRow = A.length;
        int aCol = A[0].length;
        int bRow = B.length;
        int bCol = B[0].length;
        int[][] result = new int[aRow][bCol];

        if (aCol != bRow) {
            return null;
        }

        for (int i = 0; i < aRow; i++) {
            for (int j = 0; j < bCol; j++) {
                for (int k = 0; k < aCol; k++) {
                    result[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return result;
    }

    //A0Paper^n
    static int[][] pow(int[][] A, int n) {
        if (n == 1) {
            return A;
        }

        int[][] temp = pow(A, n / 2);
        temp = matMuul(temp, temp);
        if (n % 2 == 0) {
            return temp;
        }

        return matMuul(temp, A);

    }
}
