package com.himanshu.practice.july5.hour8;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by himanshubhardwaj on 06/07/18.
 * working :-)
 */
public class StringWrapping {
    static HashMap<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) {
        //Scanner sc = new Scanner(System.in);
        //int l = Integer.parseInt(sc.nextLine());
        //String str = sc.nextLine();
        int l = 9;
        String str = "I am    Himansu Bhardwaj. An idiot, a Gadha, moron";
        str.trim();
        System.out.println(minLines(0, str, l));


        str = "I am Himansu Bhardwaj";
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

        if ((str.length() - start) <= wrapLength) {
            map.put(start, 1);
            return 1;
        }


        int minLine = Integer.MAX_VALUE - 1000;


        for (int i = start; i <= Math.min(start + wrapLength - 1, str.length() - 1); i++) {
            if (couldBreakedHere(i, str)) {
                minLine = Math.min(minLine, minLines(i + 1, str, wrapLength));
            }
        }
        minLine++;
        map.put(start, minLine);
        return minLine;
    }

    private static boolean couldBreakedHere(int i, String str) {
        if ((str.charAt(i) == ' ') || (i < (str.length() - 1) && str.charAt(i + 1) == ' ') || i == (str.length() - 1)) {
            return true;
        } else {
            return false;
        }

    }

}
