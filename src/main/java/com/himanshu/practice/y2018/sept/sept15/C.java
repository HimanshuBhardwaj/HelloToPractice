package com.himanshu.practice.y2018.sept.sept15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Created by himanshubhardwaj on 15/09/18.
 * q: number of queries
 * a l r; given a and l r, find y E [l,r], such that xor of a,y is maximum
 */
public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pr = new PrintWriter(System.out);


        int q = Integer.parseInt(br.readLine());


        while (q-- > 0) {
            long result = 0;
            String str[] = br.readLine().split(" ");

            int x = Integer.parseInt(str[0]);
            int l = Integer.parseInt(str[1]);
            int r = Integer.parseInt(str[2]);
            boolean flag = true;


            for (int i = 30; i >= 0; i--) {
                if ((x & (1 << i)) != 0) {
                    if (flag) {
                        long tempR = result;

                        if (l <= tempR && tempR >= r) {

                        } else {

                        }
                    }

                } else {
                    long tempR = result | (1 << i);
                    if (tempR <= r) {
                        result = tempR;
                    }
                }
            }


            pr.append(String.valueOf(String.valueOf(result)));
            pr.append("\n");
        }
        pr.flush();

    }
}
/*
*
*
5
2 1 10
3 5 6
6 3 10
4 2 8
7 10 15
* */