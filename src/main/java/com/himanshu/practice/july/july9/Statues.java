package com.himanshu.practice.july.july9;


import java.util.Scanner;

/**
 * Created by Himanshu Bhardwaj on 09/07/18.
 * Codeforces Problem: https://codeforces.com/problemset/problem/128/A
 * //Shortcut is evil
 * TODO: Not working
 */
public class Statues {
    public static void main(String[] args) {
        int[][] grid = new int[8][8];
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < 8; i++) {
            String str = scanner.nextLine();
            for (int j = 0; j < 8; j++) {
                if (str.charAt(j) == '.') {
                    grid[i][j] = 0;
                } else if (str.charAt(j) == 'A') {
                    grid[i][j] = 3;
                } else if (str.charAt(j) == 'M') {
                    grid[i][j] = 1;
                } else if (str.charAt(j) == 'S') {
                    grid[i][j] = 2;
                }
            }
        }

//        for (int i = 0; i < 8; i++) {
//            for (int j = 0; j < 8; j++) {
//                System.out.print(grid[i][j]);
//            }
//            System.out.println();
//        }

        int[][] visited = new int[8][8];
        System.out.print((DFS(grid, 7, 0, 0, visited) == 1) ? "WIN" : "LOSE");
        System.out.println();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(visited[i][j] + " ");
            }
            System.out.println();
        }


    }

    private static int DFS(int[][] grid, int row, int column, int level, int visited[][]) {
        if (!valid(row, column, grid, 1)) {
            return -1;
        }
        if (row == 0) {
            return 1;
        }


        if (visited[row][column] != 0) {
            return visited[row][column];
        }


        visited[row][column] = -1;


        for (int i = 1; i <= 2; i++) {
            for (int j = -1; j <= 1; j++) {
                boolean result1 = false;

                switch (i) {
                    case 1: {
                        if (row != 1) {
                            result1 = valid(row - i, column + j, grid, 1) && valid(row - i - 1, column + j, grid, 1);
                        } else {
                            result1 = valid(row - i, column + j, grid, 1);
                        }
                    }
                    break;
                    case 2: {
                        if (row != 1) {
                            result1 = valid(row - i + 1, column + j, grid, 1) && valid(row - i, column + j, grid, 1);
                        }
                    }
                    break;
                }

                //if ((row - i) == 5)
                //System.out.println((row - i) + ", " + (column + j) + ": " + result1 + ", " + i);
                if (result1) {
                    visited[row][column] = maximum(visited[row][column], DFS(grid, row - i, column + j, level + 1, visited));
                }
            }
        }

        //System.out.println(row + ", " + column + ": " + visited[row][column]);
        return visited[row][column];
    }

    private static boolean valid(int row, int column, int[][] grid, int flag) {
        boolean result = (row >= 0 && row <= 7 && column >= 0 && column <= 7 && ((flag == 1) ? grid[row][column] != 2 : true));
        return result;
    }

    private static int maximum(int... a) {

        int max = Integer.MIN_VALUE;

        for (int k : a) {
//            System.out.print(k+",");
            if (k > max) {
                max = k;
            }
        }
        //System.out.println();
        return max;
    }
}
