package com.himanshu.practice.july21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 21/07/18.
 */
public class PermutuationOFStringWithoutRepeating {
    public static void main(String[] args) throws IOException {
        String str = new BufferedReader(new InputStreamReader(System.in)).readLine();
        char[] strArray = str.toCharArray();

        printAllPermutuations(0, strArray.length - 1, strArray);


    }

    private static void printAllPermutuations(int start, int end, char[] strArray) {
        //System.out.println(start);
        if (start == strArray.length) {
            for (char c : strArray) {
                System.out.print(c);
            }
            System.out.println();
            return;
        }

        for (int i = start; i <= end; i++) {
            if (isValidSwap(start, end, i, strArray)) {
                swap(start, i, strArray);
                printAllPermutuations(start + 1, end, strArray);
                swap(start, i, strArray);
            }
        }
    }

    private static void swap(int start, int i, char[] strArray) {
        char temp = strArray[start];
        strArray[start] = strArray[i];
        strArray[i] = temp;
    }

    private static boolean isValidSwap(int start, int end, int ii, char[] strArray) {

        for (int i = (ii + 1); i <= end; i++) {
            if (strArray[i] == strArray[ii]) {
                return false;
            }
        }
        return true;
    }
}
