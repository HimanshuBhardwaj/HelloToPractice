package com.himanshu.practice.june27.hour4;

/**
 * Created by Himanshu Bhardwaj on 27/06/18.
 */
public class EditDistance {
    static String a = "abcd";
    static String b = "cbdfd";

    public static void main(String[] args) {
        System.out.println(editDistance(a.toCharArray(), b.toCharArray()));
    }

    private static int editDistance(char[] string1, char[] string2) {
        int[][] DP = new int[string1.length + 1][string2.length + 1];
        for (int i = 0; i < string1.length + 1; i++) {
            for (int j = 0; j < string2.length + 1; j++) {
                if (i == 0) {
                    DP[i][j] = j;
                } else if (j == 0) {
                    DP[i][j] = i;
                } else {
                    DP[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        editDistanceHelper(string1, string1.length, string2, string2.length, DP);
        //editDistanceHelper(string1,start1,end1,string2,start2,end2,DP);

        System.out.println();
        for (int i = 0; i <= string1.length; i++) {
            for (int j = 0; j <= string2.length; j++) {
                System.out.print(DP[i][j] + " ");
            }
            System.out.println();
        }

        return DP[string1.length][string2.length];

    }

    private static void editDistanceHelper(char[] string1, int end1, char[] string2, int end2, int[][] DP) {

        for (int i = 1; i <= string1.length; i++) {
            for (int j = 1; j <= string2.length; j++) {
                DP[i][j] = (string1[i - 1] == string2[j - 1]) ? DP[i - 1][j - 1] : (1 + Math.min(Math.min(DP[i - 1][j], DP[i][j - 1]), DP[i - 1][j - 1]));
            }
        }
    }


}
