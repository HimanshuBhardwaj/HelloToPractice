package com.himanshu.practice.july.july4;

import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by Himanshu Bhardwaj on 04/07/18.
 * 2:12 am
 */
public class RecursionsMatricExpo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringTokenizer st = new StringTokenizer(sc.next(), ",");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] a = new int[1][m];


        int pos = 0;
        st = new StringTokenizer(sc.next(), ",");
        while (st.hasMoreTokens()) {
            a[0][m - 1 - pos++] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < m; i++) {
            System.out.print(a[0][i] + ", ");
        }
        System.out.println();
        System.out.println();
        System.out.println();


        int[][] transition = new int[m][m];

        for (int i = 0; i < m; i++) {
            transition[i][0] = 1;
            if (i > 0) {
                transition[i - 1][i] = 1;
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(transition[i][j] + "\t");
            }
            System.out.println();
        }

        int temp[][] = MatMul.matMul(a, MatMul.pow(transition, n - m + 1));

        System.out.println();
        System.out.println();
        System.out.println(temp[0][0]);

    }

}


class MatMul {

    public static int[][] pow(int[][] A, int pow) {
        if (pow == 1) {
            return A;
        }

        int[][] temp = pow(A, pow / 2);
        temp = matMul(temp, temp);
        if (pow % 2 == 0) {
            return temp;
        } else {
            return matMul(temp, A);
        }
    }

    public static int[][] matMul(int[][] A, int[][] B) {
        int aRow = A.length;
        int aCol = A[0].length;
        int bRow = B.length;
        int bCo1 = B[0].length;

        if (aCol != bRow) {
            return null;
        }

        int result[][] = new int[aRow][bCo1];

        for (int i = 0; i < aRow; i++) {
            for (int j = 0; j < bCo1; j++) {
                for (int k = 0; k < aCol; k++) {
                    result[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return result;
    }
}


