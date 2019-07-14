package com.himanshu.practice.y2019.july.july12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 12/07/19.
 */
public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Integer x = Integer.parseInt(br.readLine());
        x = x % 4;
        if (x==0) {
            System.out.print("1 A");
        } else if(x==1) {
            System.out.print("0 A");
        }else if (x==2) {
            System.out.print("1 B");
        } else {
            System.out.print("2 A");
        }


    }
}
