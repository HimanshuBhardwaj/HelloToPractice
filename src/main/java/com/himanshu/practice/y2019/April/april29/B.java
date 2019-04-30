package com.himanshu.practice.y2019.April.april29;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Created by himanshubhardwaj on 29/04/19.
 */
public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int arr[][] = new int[n][n];


        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < n; j++) {
                if (str.charAt(j) != '.') {
                    arr[i][j] = 1;
                }
            }
        }


        boolean isPossible = true;

        for (int i = 0; isPossible && (i <= n / 2); i++) {
            for (int j = i; isPossible && (j < (n - i)); j++) {
                int row = i;
                int column = j;
                //System.out.print("(" + row + "," + column + ") ");

                if (arr[row][column] == 0) {
                    if (isValid(n, row + 1, column) && isValid(n, row + 2, column) && isValid(n, row + 1, column - 1) && isValid(n, row + 1, column + 1)) {
                        if (arr[row + 1][column] == 0 && arr[row + 2][column] == 0 && arr[row + 1][column - 1] == 0 && arr[row + 1][column + 1] == 0) {
                            arr[row + 1][column] = 1;
                            arr[row + 2][column] = 1;
                            arr[row + 1][column - 1] = 1;
                            arr[row + 1][column + 1] = 1;
                            arr[row][column] = 1;
                        } else {
                            isPossible = false;
                        }
                    } else {
                        isPossible = false;
                    }
                }
            }


            for (int j = i + 1; isPossible && (j < (n - i)); j++) {
                int row = j;
                int column = n - i - 1;

                if (arr[row][column] == 0) {
                    if (isValid(n, row - 1, column - 1) && isValid(n, row, column - 1) && isValid(n, row, column - 2) && isValid(n, row + 1, column - 1)) {
                        if (arr[row - 1][column - 1] == 0 && arr[row][column - 1] == 0 && arr[row][column - 2] == 0 && arr[row + 1][column - 1] == 0) {
                            arr[row - 1][column - 1] = 1;
                            arr[row][column - 1] = 1;
                            arr[row][column - 2] = 1;
                            arr[row + 1][column - 1] = 1;
                            arr[row][column] = 1;
                        } else {
                            isPossible = false;
                        }
                    } else {
                        isPossible = false;
                    }

                }


            }


            for (int j = (n - i - 2); isPossible && (j >= i); j--) {
                int row = n - i - 1;
                int column = j;
                if (arr[row][column] == 0) {
                    if (isValid(n, row - 1, column) && isValid(n, row - 1, column - 1) && isValid(n, row - 1, column + 1) && isValid(n, row - 2, column)) {
                        if (arr[row - 1][column] == 0 && arr[row - 1][column - 1] == 0 && arr[row - 1][column + 1] == 0 && arr[row - 2][column] == 0) {
                            arr[row - 1][column] = 1;
                            arr[row - 1][column - 1] = 1;
                            arr[row - 1][column + 1] = 1;
                            arr[row - 2][column] = 1;
                            arr[row][column] = 1;
                        } else {
                            isPossible = false;
                        }
                    } else {
                        isPossible = false;
                    }

                }

            }


            for (int j = (n - i - 2); isPossible && (j > i); j--) {
                int row = j;
                int column = i;
                if (arr[row][column] == 0) {
                    if (isValid(n, row, column + 1) && isValid(n, row, column + 2) && isValid(n, row - 1, column + 1) && isValid(n, row + 1, column + 1)) {
                        if (arr[row][column + 1] == 0 && arr[row][column + 2] == 0 && arr[row - 1][column + 1] == 0 && arr[row + 1][column + 1] == 0) {
                            arr[row][column + 1] = 1;
                            arr[row][column + 2] = 1;
                            arr[row - 1][column + 1] = 1;
                            arr[row + 1][column + 1] = 1;
                            arr[row][column] = 1;
                        } else {
                            isPossible = false;
                        }
                    } else {
                        isPossible = false;
                    }
                }
            }


        }

        if (isPossible) {
            System.out.print("YES");
        } else {
            System.out.print("NO");
        }
    }

    static boolean isValid(int n, int row, int colomn) {
        if (row >= 0 && colomn >= 0 && row < n && colomn < n) {
            return true;
        }
        return false;
    }
}
