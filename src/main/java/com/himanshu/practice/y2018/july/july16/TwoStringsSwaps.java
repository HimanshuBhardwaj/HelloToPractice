//package com.himanshu.practice.y2018.july.july16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * Created by himanshubhardwaj on 16/07/18.
 TODO: Complete it

 */
public class TwoStringsSwaps {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        char[] a = br.readLine().toCharArray();
        char[] b = br.readLine().toCharArray();
        int count[] = new int[a.length];

        for (int i = 0; i < a.length / 2; i++) {
            if (a[i] == b[i] || a[n - 1 - i] == b[n - 1 - i] || a[i] == b[n - 1 - i] || a[n - 1 - i] == b[i] || a[i] == a[n - 1 - i] || b[i] == b[n - 1 - i]) {
                //when it has come inside means something is equal
                if ((a[i] == b[i] && a[n - 1 - i] == b[n - i - 1]) ||
                        (a[i] == b[n - i - 1] && a[n - i - 1] == b[i])) {
                    count[i] = (i == 0) ? 0 : count[i - 1];
                } else {
                    count[i] = (i == 0) ? 1 : 1 + count[i - 1];
                }
            } else {
                count[i] = (i == 0) ? 2 : 2 + count[i - 1];
            }
//            System.out.println(i + "\t" + count[i]);
        }

        if (a.length % 2 == 1) {
            if (a[a.length / 2] == b[b.length / 2]) {
                count[a.length / 2] = (a.length == 1) ? 0 : count[a.length / 2 - 1];
            } else {
                count[a.length / 2] = (a.length == 1) ? 1 : 1 + count[a.length / 2 - 1];
            }
        }

        if (a.length % 2 == 1) {
            System.out.print(count[a.length / 2]);
        } else {
            System.out.print(count[(a.length / 2) - 1]);
        }
    }
}
