package com.himanshu.practice.june20;

/**
 * Created by Himanshu Bhardwaj on 20/06/18.
 */
public class Permutuation {
    public static void main(String[] args) {
        String string = "abcc";
        printPerm(0, string.length() - 1, string.toCharArray());
    }

    private static void printPerm(int start, int end, char[] string) {
        if (start == end) {
            for (int i = 0; i < string.length; i++) {
                System.out.print(string[i]);
                if (i == (string.length - 1)) {
                    System.out.println();
                }
            }
        }
        for (int i = start; i <= end; i++) {
            swap(start, i, string);
            printPerm(start + 1, end, string);
            swap(i, start, string);
        }
    }

    private static void swap(int start, int end, char[] string) {
        char temp = string[start];
        string[start] = string[end];
        string[end] = temp;
    }
}
