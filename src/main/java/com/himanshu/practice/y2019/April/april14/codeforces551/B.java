package com.himanshu.practice.y2019.April.april14.codeforces551;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 13/04/19.
 */
public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);
        int h = Integer.parseInt(str[2]);

        boolean[] doesColumnHasElement = new boolean[m];
        boolean[] doesRowHasElement = new boolean[n];
        int[] maxColumHeight = new int[m];
        int[] maxRowHeight = new int[n];

        str = br.readLine().split(" ");
        for (int i = 0; i < str.length; i++) {
            maxColumHeight[i] = Integer.parseInt(str[i]);
        }

        str = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            maxRowHeight[i] = Integer.parseInt(str[i]);
        }

        int height[][] = new int[n][m];

        for (int i = 0; i < n; i++) {
            str = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                height[i][j] = Integer.parseInt(str[j]);
            }
        }

        boolean[] isRowMAxEntered = new boolean[n];
        boolean[] isColumnMaxEntered = new boolean[m];
        int[][] orignalArray = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (height[i][j] > 0) {
                    if (isRowMAxEntered[i] == false) {
                        if (maxRowHeight[i] > maxColumHeight[j]) {
                            orignalArray[i][j] = maxColumHeight[j];
                            isColumnMaxEntered[j] = true;
                        } else {
                            orignalArray[i][j] = maxRowHeight[i];
                            isRowMAxEntered[i] = true;
                        }
                    } else {
                        if (isColumnMaxEntered[j] == false) {
                            if (maxColumHeight[j] > maxRowHeight[i]) {
                                orignalArray[i][j] = maxRowHeight[i];
                            } else {
                                orignalArray[i][j] = maxColumHeight[j];
                                isColumnMaxEntered[j] = true;
                            }
                        } else {
                            orignalArray[i][j] = Math.min(maxColumHeight[j], maxRowHeight[i]);
                        }
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(orignalArray[i][j] + " ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());


    }
}
