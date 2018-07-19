package com.himanshu.practice.july19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 19/07/18.
 * //Not working :-(
 */
public class TwoStringsSwaps {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        char[] str1 = br.readLine().toCharArray();
        char[] str2 = br.readLine().toCharArray();

        if (str1.length != str2.length || (str1.length != n)) {
            System.out.println("0");
            return;
        }

        int count = 0;

        for (int i = 0; i < n / 2; i++) {
            int changesReq = requiredChanges(100000, str1[i], str1[n - 1 - i], str2[i], str2[n - 1 - i]);
            count += changesReq;
        }

        if (n % 2 == 1) {
            count += requiredChanges(100000, str1[n / 2], str2[n / 2]);
        }
        System.out.print(count);

    }

    private static int requiredChanges(int n, char... c) {


        int returnV = -1;

        if (c.length == 2) {
            if (c[0] == c[1]) {
                returnV = 0;
            } else {
                returnV = 1;
            }
        } else {

            if ((c[0] == c[1] && c[2] == c[3]) ||
                    (c[0] == c[3] && c[1] == c[2]) || (c[0] == c[2] && c[1] == c[3])) {
                returnV = 0;
            } else if (c[0] == c[1]) {
                if (c[2] == c[0] || c[3] == c[1]) {
                    returnV = 1;
                } else {
                    returnV = 2;
                }
            } else if (c[2] == c[3]) {
                if (c[0] == c[2] || c[1] == c[2]) {
                    returnV = 1;
                } else {
                    returnV = 2;
                }
            } else if (c[0] == c[2]) {
                if (c[1] == c[0] || c[3] == c[0]) {
                    returnV = 1;
                } else {
                    returnV = 2;
                }
            } else if (c[1] == c[3]) {
                if (c[1] == c[0] || c[2] == c[0]) {
                    returnV = 1;
                } else {
                    returnV = 2;
                }
            } else {
                returnV = 2;
            }
        }


        if (n == 100000 && returnV < 0) {
            for (char cc : c) {
                System.out.print(cc);
            }
            System.out.println("\t" + returnV);
        }

        return returnV;
    }
}
