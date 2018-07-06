package com.himanshu.practice.july5.hour8;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by himanshubhardwaj on 06/07/18.
 * TODO: Not working
 */
public class StringWrapping {
    static HashMap<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int l = Integer.parseInt(sc.nextLine());
        String str = sc.nextLine();
        str.trim();
        System.out.println(minLines(0, str, l));
    }

    private static int minLines(int start, String str, int wrapLength) {

        if (start >= str.length()) {
            return Integer.MAX_VALUE - 1000;
        }

        if (map.containsKey(start)) {
            return map.get(start);
        }

        System.out.println(start + "," + (str.length() - start));
        if ((str.length() - start) <= wrapLength) {
            map.put(start, 1);
            System.out.println(start + "...reaching here");
            return 1;
        }


        int minLine = Integer.MAX_VALUE - 1000;


        for (int i = start + 1; i <= Math.min(start + wrapLength - 1, str.length() - 1); i++) {
            if (str.charAt(i) == ' ') {
                minLine = Math.min(minLine, minLines(i, str, wrapLength));
                minLine = Math.min(minLine, minLines(i + 1, str, wrapLength));
            }
        }
        minLine++;
        System.out.println(start + "\t" + minLine);
        map.put(start, minLine);
        return minLine;
    }

}
