package com.himanshu.practice.y2019.june.june7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 07/06/19.
 */
public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");
        int x = Integer.parseInt(str[0]);
        int y = Integer.parseInt(str[1]);
        int z = Integer.parseInt(str[2]);

        int result1 = x-y-z;
        int result2 = x-y+z;

        if (result1 >0 &&  result2>0) {
            System.out.print("+");
        } else if(result1 <0 && result2 < 0) {
            System.out.print("-");
        } else if (result1==0 && result2==0) {
            System.out.print("0");
        } else {
            System.out.print("?");
        }

    }
}
