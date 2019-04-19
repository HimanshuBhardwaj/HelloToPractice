package com.himanshu.practice.y2019.April.april19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by himanshubhardwaj on 18/04/19.
 * Statement:https://codeforces.com/contest/1151/problem/B
 * TODO: Finish it
 */
public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);
        int arr[][] = new int[n][m];

        for (int i = 0; i < n; i++) {
            str = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(str[j]);
            }
        }

        for (int bitIndex = 0; bitIndex <= 10; bitIndex++) {

            HashMap<Integer, Integer>[] listNumbers = new HashMap[n];
            for (int j = 0; j < n; j++) {
                listNumbers[j] = new HashMap<>();
                for (int k = 0; k < m; k++) {
                    if ((!listNumbers[j].containsKey(1)) && isSetBit(arr[j][k], bitIndex)) {
                        listNumbers[j].put(1, k + 1);
                    }
                    if ((!listNumbers[j].containsKey(0)) && !isSetBit(arr[j][k], bitIndex)) {
                        listNumbers[j].put(0, k + 1);
                    }
                }
            }


            int count = 0;

            for (int i = 0; i < n; i++) {
                if (listNumbers[i].containsKey(1)) {
                    count++;
                }
            }

            if (count % 2 == 1) {
                System.out.println("TAK");
                for (int i = 0; i < n; i++) {
                    if (listNumbers[i].containsKey(1)) {
                        System.out.print(listNumbers[i].get(1) + " ");
                    } else {
                        System.out.print(listNumbers[i].get(0) + " ");
                    }
                }
                return;
            }
        }
        System.out.print("NIE");
        return;


    }

    //tellis if bitNum is set in num
    static boolean isSetBit(int num, int bitNum) {
        if (((1 << bitNum) & (num)) == 0) {
            return false;
        }
        return true;
    }
}
