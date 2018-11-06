package com.himanshu.practice.nov.nov6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;

/**
 * Created by himanshubhardwaj on 06/11/18.
 */
//TODO: Complete it

public class PermuteDigits {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        TreeSet<Integer> set = new TreeSet<>();

        for (int i = 0; i < s.length(); i++) {
            set.add(s.charAt(i) - '0');
        }

        s = br.readLine();
        long number = 0;
        boolean flag = false;
        int pos = 0;



    }
}
