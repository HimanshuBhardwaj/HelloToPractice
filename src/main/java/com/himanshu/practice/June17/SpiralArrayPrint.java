package com.himanshu.practice.June17;

/**
 * Created by Himanshu Bhardwaj on 17/06/18.
 */
public class SpiralArrayPrint {

    public static void main(String[] args) {
        int row = 4;
        int column = 6;

        int arr[][] = new int[4][6];

        System.out.println("Actual Array:");

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                arr[i][j] = (i + j) * (i - j + 1);
                System.out.print(arr[i][j] + "\t");
            }
            System.out.println();

        }

        System.out.println();
        System.out.println();
        Print.printApiralArray(arr, 0, row - 1, 0, column - 1);
    }
}


class Print {


    public static void printApiralArray(int[][] arr, int rowStart, int rowEnd, int columnStart, int columnEnd) {
        if ((rowStart > rowEnd) || (columnStart > columnEnd)) {
            return;
        }

        for (int i = columnStart; i < columnEnd; i++) {
            System.out.print(arr[rowStart][i] + " ");
        }

        for (int i = rowStart; i < rowEnd; i++) {
            System.out.print(arr[i][columnEnd] + " ");
        }

        for (int i = columnEnd; i > columnStart; i--) {
            System.out.print(arr[rowEnd][i] + " ");
        }

        for (int i = rowEnd; i > rowStart; i--) {
            System.out.print(arr[i][columnStart] + " ");
        }
        System.out.println();

        printApiralArray(arr, rowStart + 1, rowEnd - 1, columnStart + 1, columnEnd - 1);
    }

}