package com.himanshu.practice.y2019.july.july5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 03/07/19.
 */
public class AllPermutuations {
    static long count = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char string[] = br.readLine().toCharArray();

        printAllPermutuationsWithRepetations(0, string);

    }

    private static void printAllPermutuationsWithRepetations(int index, char[] string) {
        if (index == string.length) {
            System.out.print(count + ": ");
            System.out.println(new String(string));
            count++;
            return;
        }

        for (int i = index; i < string.length; i++) {
            swap(i, index, string);
            printAllPermutuationsWithRepetations(index + 1, string);
            swap(i, index, string);
        }
    }

    private static void swap(int i, int index, char[] string) {
        char t = string[index];
        string[index] = string[i];
        string[i] = t;
    }
}
