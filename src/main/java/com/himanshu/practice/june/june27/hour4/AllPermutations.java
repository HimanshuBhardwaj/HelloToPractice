package com.himanshu.practice.june.june27.hour4;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by Himanshu Bhardwaj on 27/06/18.
 */
public class AllPermutations {
    static HashSet<String> set = new HashSet<String>();


    public static void main(String[] args) {
        String string = "abbaa";
        char[] str = string.toCharArray();

        Arrays.sort(str);

        for (char i : str) {
            System.out.print(i);
        }
        System.out.println();
        System.out.println("Printing permutuation");
        System.out.println();

        Arrays.sort(str);
        printWithoutRepetations(str, 0);
        System.out.println(set.size());


        System.out.println("Second method");
        System.out.println();

        char[] str1 = string.toCharArray();
        Arrays.sort(str1);
        printAllPermutuations(str1, 0);


    }

    private static void printWithoutRepetations(char[] str, int pos) {
        if (pos == str.length) {
            String str1 = new String(str);
            if (!set.contains(str1)) {
                System.out.println(str1);
                set.add(str1);
            }
            return;
        }
        for (int i = pos; i < str.length; i++) {
            swap(str, pos, i);
            printWithoutRepetations(str, pos + 1);
            swap(str, pos, i);
        }
    }

    private static void printAllPermutuations(char[] str, int pos) {
        if (pos == str.length) {
            String str1 = new String(str);
            System.out.println(str1);
            return;
        }
        for (int i = pos; i < str.length; i++) {
            boolean shouldSwap = shouldSwap(str, pos, i);
            if (shouldSwap) {
                swap(str, pos, i);
                printAllPermutuations(str, pos + 1);
                swap(str, pos, i);
            }
        }
    }

    private static boolean shouldSwap(char[] str, int pos, int end) {

        for (int i = end + 1; i < str.length; i++) {
            if (str[i] == str[end]) {
                return false;
            }
        }
        return true;
    }

    private static void swap(char[] str, int pos, int i) {
        char temp = str[pos];
        str[pos] = str[i];
        str[i] = temp;
    }

}
