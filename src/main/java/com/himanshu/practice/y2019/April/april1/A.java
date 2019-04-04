package com.himanshu.practice.y2019.April.april1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 01/04/19.
 */
public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        String[] str = br.readLine().split(" ");

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(str[i]);
        }

        int day = 0;
        int maxPageRead = -1;

        while (maxPageRead < (n-1)) {
            int tempMaxPageRead = maxPageRead;
            for (int i = maxPageRead + 1; i<n; i++) {
                if ( (arr[i]-1) > tempMaxPageRead) {
                    tempMaxPageRead = arr[i]-1;
                }
                if (tempMaxPageRead==i) {
                    break;
                }
            }
            maxPageRead = tempMaxPageRead;
            day++;
        }

        System.out.print(day);
    }
}
