package com.himanshu.practice.y2018.nov.nov14.codeforces520;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 14/11/18.
 */
public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] str = br.readLine().split(" ");
        int arr[] = new int[1001];

        for (int i = 0; i < n; i++) {
            arr[Integer.parseInt(str[i])] = 1;
        }
        n = 1001;

        int count = 0;
        boolean flag = false;

        for (int i = 1; i < n; i++) {

            if (arr[i] == 1 && arr[i - 1] >= 1) {
                arr[i] += arr[i - 1];
                arr[i - 1] = 0;
            } else if (arr[i - 1] > 1) {
                count += (arr[i - 1] - 1);
                arr[i - 1] = 0;
                if (flag) {
                    count--;
                }

            }
            if (arr[i] == 0) {
                flag = true;
            }


            if ((i == (n - 1)) && (arr[i] != 0)) {
                count += (arr[i] - 1);
            }
        }

        System.out.print(count);


    }
}
