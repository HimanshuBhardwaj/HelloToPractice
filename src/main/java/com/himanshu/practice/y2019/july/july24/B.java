package com.himanshu.practice.y2019.july.july24;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Created by himanshubhardwaj on 14/07/19.
 */
public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int q = Integer.parseInt(br.readLine());
        char[] chars;
        PrintWriter pw = new PrintWriter(System.out);

        while (q-- > 0) {
            String str[] = br.readLine().split(" ");
            int n = Integer.parseInt(str[0]);
            int m = Integer.parseInt(str[1]);
            boolean[][] mat = new boolean[n][m];
            int rowXount[] = new int[n];
            int columnCount[] = new int[m];


            for (int i = 0; i < n; i++) {
                chars = br.readLine().toCharArray();
                for (int j = 0; j < chars.length; j++) {
                    if (chars[j] == '*') {
                        rowXount[i]++;
                        columnCount[j]++;
                        mat[i][j] = true;
                    }
                }
            }

            int maxCount = Integer.MAX_VALUE;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    maxCount = Math.min(maxCount, (m + n - 1 - rowXount[i] - columnCount[j] + (mat[i][j] ? 1 : 0)));
                }
            }
            pw.append(maxCount + "\n");
        }
        pw.flush();

    }
}
