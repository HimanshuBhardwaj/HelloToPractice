package com.himanshu.practice.june19;

import java.util.Scanner;

/**
 * Created by himanshubhardwaj on 19/06/18.
 */

//An = An-1 + An-2 + ... +An-k

//lets assume A0 = A1 = A2 ... Am-1
public class LinearRecursion {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.next());
        int m = Integer.parseInt(sc.next());


        int[][] mat = new int[m][m];

        for (int j = 0; j < m - 1; j++) {
            mat[j][j + 1] = 1;
            mat[j][0] = 1;
        }
        mat[m - 1][0] = 1;


        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(mat[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();

        int[][] rowMat = new int[1][m];

        for (int i = 0; i < m; i++) {
            rowMat[0][i] = 1;
        }


        int result[][] = pow(mat, n - m + 1);
        int newResult[][] = matMul(rowMat, result);

        System.out.println("Printing result");

        for (int i = 0; i < newResult.length; i++) {
            for (int j = 0; j < newResult[0].length; j++) {
                System.out.print(newResult[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();

    }


    static int[][] pow(int[][] A, int pow) {
        if (pow == 1) {
            return A;
        }
        if (pow == 2) {
            return matMul(A, A);
        }
        int[][] temp = pow(A, pow / 2);
        temp = matMul(temp, temp);

        if (pow % 2 == 0) {
            return temp;
        } else {
            return matMul(temp, A);
        }
    }


    static int[][] matMul(int A[][], int B[][]) {
        int aRow = A.length;
        int aColumn = A[0].length;
        int bRow = B.length;
        int bColumn = B[0].length;

        if (aColumn != bRow) {
            System.out.println("multiplication not possible");
        }


        int[][] result = new int[aRow][bColumn];

        for (int i = 0; i < aRow; i++) {
            for (int j = 0; j < bColumn; j++) {
                for (int k = 0; k < aColumn; k++) {
                    result[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return result;
    }
}




