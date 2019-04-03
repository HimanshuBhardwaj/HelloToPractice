package com.himanshu.practice.y2019.march.march31;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Created by himanshubhardwaj on 31/03/19.
 */
public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            char[] charA = br.readLine().toCharArray();
            Arrays.sort(charA);
            boolean isValid = true;
            for (int i = 1; i < charA.length; i++) {
                if (((int) (charA[i] - charA[i - 1])) != 1) {
                    isValid = false;
                }
            }

            if (isValid) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }


    }
}
