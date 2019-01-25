package com.himanshu.practice.y2018.Aug.Aug15;

/**
 * Created by himanshubhardwaj on 15/08/18.
 * TopCoder, Hello world
 * Link: https://arena.topcoder.com/#/u/practiceCode/17193/64276/14930/2/331413
 */
public class BinaryCalculator {
    public static void main(String[] args) {
        BinaryCalculator b = new BinaryCalculator();
        System.out.println(b.minimumSteps(23,62));
    }
    public int minimumSteps(int a, int b) {
        if (a > b) {
            int diff = a - b;
            if (diff % 2 == 0) {
                return diff / 2;
            } else {
                return (diff / 2) + (3);
            }

        } else if (a == b) {
            return 0;
        } else {
            //a < b

            int diff = b - a;

            if (diff % 3 == 0) {
                return diff / 3;
            } else if (diff % 3 == 1) {
                return (diff / 3) + 2;
            } else if (diff % 3 == 2) {
                return (diff / 3) + 4;
            }
        }
        return -1;
    }
}
