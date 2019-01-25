package com.himanshu.practice.y2018.july.july25;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Created by himanshubhardwaj on 25/07/18.
 * ProblemSet: https://codeforces.com/contest/559/problem/B
 * Algo: Similar to strassen matrix multiplication
 * Submission: https://codeforces.com/contest/559/submission/40740319
 */
public class EquivalentStrings {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] str1 = br.readLine().toCharArray();
        char[] str2 = br.readLine().toCharArray();
        boolean areEqual = checkEqual(str1, str2);
        System.out.print((areEqual) ? "YES" : "NO");
    }

    private static boolean checkEqual(char[] str1, char[] str2) {
        if (str1.length % 2 == 1) {
//            System.out.println(new String(str1) + "\t" + new String(str2) + "\t" + Arrays.equals(str1, str2));
            return Arrays.equals(str1, str2);
        }


        //only even leangth allowed

        int mid = str1.length / 2;
        char firstHalf1[] = Arrays.copyOfRange(str1, 0, mid);
        char secondHalf1[] = Arrays.copyOfRange(str1, mid, str1.length);
//        System.out.println("Splitting: " + new String(str1) + "\t" + new String(firstHalf1) + " " + new String(secondHalf1));
        char firstHalf2[] = Arrays.copyOfRange(str2, 0, mid);
        char secondHalf2[] = Arrays.copyOfRange(str2, mid, str2.length);


       // System.out.println("Splitting: " + new String(str1) + "\t" + new String(firstHalf1) + " " + new String(secondHalf1));
        //System.out.println("Splitting: " + new String(str2) + "\t" + new String(firstHalf2) + " " + new String(secondHalf2));


        boolean firstHalfEquals = Arrays.equals(firstHalf1, firstHalf2);
        boolean secondHalfEquals = Arrays.equals(secondHalf1, secondHalf2);


        if (firstHalfEquals) {
            if (secondHalfEquals) {
                return true;
            } else {
                return checkEqual(secondHalf1, secondHalf2);
            }
        }


        if (secondHalfEquals) {
            if (!firstHalfEquals) {
                return checkEqual(firstHalf1, firstHalf2);
            }
        }

        return (checkEqual(firstHalf1, secondHalf2) && checkEqual(secondHalf1, firstHalf2)) ||
                (checkEqual(firstHalf1, firstHalf2) && checkEqual(secondHalf1, secondHalf2));
    }
}
