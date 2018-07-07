package com.himanshu.practice.June18.hour1;

/**
 * Created by Himanshu Bhardwaj on 18/06/18.
 */
public class MatrixExpo {
    public static void main(String[] args) {
        int I[][] = {
                {1, 1}, {1, 0}
        };

        System.out.println(I.length);
        System.out.println(I[0].length);

        int mul[][] = Matrix.pow(I, 5);

        for (int i = 0; i < mul.length; i++) {
            for (int j = 0; j < mul[0].length; j++) {
                System.out.print(mul[i][j] + "\t");
            }
            System.out.println();
        }
    }
}


class Matrix {


    static int[][] pow(int A[][], int pow) {
        if (pow < 1 || A == null) {
            return null;
        }
        if (pow == 1) {
            return A;
        }

        //int temp[][] = matrixMul(A, A.length, A[0].length, A, A.length, A[0].length);
        int temp[][] = pow(A, pow / 2);
        temp = matrixMul(temp, temp.length, temp[0].length, temp, temp.length, temp[0].length);

        if (pow % 2 == 0) {
            return temp;
        } else {
            temp = matrixMul(temp, temp.length, temp[0].length, A, A.length, A[0].length);
            return temp;
        }
    }


    static int[][] matrixMul(int[][] A, int aRow, int aColumn, int B[][], int bRow, int bColumn) {
        if (aColumn != bRow) {
            System.out.println("matrix multiplication not possible");
            return null;
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