package com.himanshu.practice.june28;

/**
 * Created by Himanshu Bhardwaj on 28/06/18.
 */

//fastest way to reach a position from 0,0
    //BFS
public class KnightTour {

    public static void main(String[] args) {
        int n = 4;
        int m = 4;
        int mat[][] = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                mat[i][j] = Integer.MAX_VALUE;
            }
        }

        BFS(mat, 0, 0, 0);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(mat[i][j] + "\t");
            }
            System.out.println();
        }

    }

    private static void BFS(int[][] mat, int row, int column, int level) {

        if (row >= mat.length || row < 0 || column < 0 || column >= mat[0].length || level > mat[row][column]) {
            return;
        }

        mat[row][column] = level;


        for (int i = row - 2; i <= row + 2; i++) {
            for (int j = column - 2; j <= column + 2; j++) {
                int rowDif = Math.abs(i - row);
                int colDif = Math.abs(j - column);
                boolean expression = (rowDif == 2 && colDif == 1) || (rowDif == 1 && colDif == 2);

                if (expression) {
                    BFS(mat, i, j, level + 1);
                }
            }
        }
    }


}
