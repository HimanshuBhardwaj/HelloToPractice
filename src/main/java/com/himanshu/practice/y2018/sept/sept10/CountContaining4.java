package com.himanshu.practice.y2018.sept.sept10;

/**
 * Created by himanshubhardwaj on 11/09/18.
 */
public class CountContaining4 {
    public static void main(String[] args) {
        int count = 0;

        for (int i = 0; i <= 10000000; i++) {
            if (constains4(i)) {
                count++;
            }
            if (i != 0) {
                if (i == 10000000) {
                    System.out.println(i + "\t" + count);
                } else if (i == 1000000) {
                    System.out.println(i + "\t" + count);
                } else if (i == 100000) {
                    System.out.println(i + "\t" + count);
                } else if (i == 10000) {
                    System.out.println(i + "\t" + count);
                } else if (i == 1000) {
                    System.out.println(i + "\t" + count);
                } else if (i == 100) {
                    System.out.println(i + "\t" + count);
                } else if (i == 10) {
                    System.out.println(i + "\t" + count);
                }
            }
        }

    }

    static boolean constains4(int x) {
        while (x > 0) {
            if (x % 10 == 4) {
                return true;
            }
            x = x / 10;
        }
        return false;
    }
}
