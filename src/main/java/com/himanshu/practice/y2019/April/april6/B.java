package com.himanshu.practice.y2019.April.april6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by himanshubhardwaj on 06/04/19.
 */
public class B {
    static ArrayList<Long>[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        long h = Long.parseLong(str[1]);
        str = br.readLine().split(" ");
        arr = new ArrayList[n];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = new ArrayList<>();
            if (i == 0) {
                arr[i].add(Long.parseLong(str[i]));
            } else {
                arr[i].add(Long.parseLong(str[i]));
                arr[i].addAll(arr[i - 1]);
                Collections.sort(arr[i], Collections.reverseOrder());
            }
        }

        for (int i = 0; i < n; i++) {
            if (maximumHeightReq(arr[i]) <= h) {
                continue;
            } else {
                System.out.print(i);
                return;
            }
        }

        System.out.print(n);
    }

    private static long maximumHeightReq(ArrayList<Long> arr) {
        long height = 0l;
        for (int i = 0; i < arr.size(); i++) {
            if (i % 2 == 0) {
                height += arr.get(i);
            }
        }
        return height;
    }
}
