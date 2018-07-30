package com.himanshu.practice.july.july30.codeforces;//package com.himanshu.practice.july.july30.codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Created by himanshubhardwaj on 30/07/18.
 */
public class C {
    static int rowStart = Integer.MAX_VALUE;
    static int rowEnd = Integer.MAX_VALUE;
    static int columnStart = Integer.MAX_VALUE;
    static int columnEnd = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] str = br.readLine().split(" ");

        int arr[] = new int[str.length];


        for (int i = 0; i < str.length; i++) {
            arr[i] = Integer.parseInt(str[i]);
        }

        Arrays.sort(arr);
//        for (int i = 0; i < str.length; i++) {
//            System.out.print(arr[i] + " ");
//        }
//        System.out.println();


        long area = Long.MAX_VALUE;

        area = Math.abs((((long) arr[(arr.length / 2) - 1]) - ((long) arr[0])) *
                (((long) arr[arr.length - 1]) - ((long) arr[(arr.length / 2)]))
        );


        System.out.print(area);
    }

    private static void updateDimentions(int row, int column) {

        if (((rowStart <= row) && (row <= rowEnd) && (columnStart <= column) && (column <= columnEnd))
                || ((rowStart <= column) && (column <= rowEnd) && (columnStart <= row) && (row <= columnEnd))) {
            return;
        }

        if (rowStart == Integer.MAX_VALUE) {
            rowStart = row;
            rowEnd = row;
        } else if (row < rowStart) {
            rowStart = Math.min(rowStart, row);
        } else if (row > rowEnd) {
            rowEnd = Math.max(rowEnd, row);
        }

        if (columnStart == Integer.MAX_VALUE) {
            columnStart = column;
            columnEnd = column;
        } else if (column > columnEnd) {
            columnEnd = Math.max(columnEnd, column);
        } else if (column < columnStart) {
            columnStart = Math.min(columnStart, column);
        }

//        System.out.println(row + " " + column + "\t\t" + rowStart + " " + rowEnd + "\t\t" + columnStart + " " + columnEnd);

    }
}
