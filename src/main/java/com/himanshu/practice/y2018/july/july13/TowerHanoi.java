package com.himanshu.practice.y2018.july.july13;

/**
 * Created by himanshubhardwaj on 13/07/18.
 * Tower of Hanoi recursion
 */
public class TowerHanoi {
    public static void main(String[] args) {
        towerOfHanoi('A', 'B', 'C', 3);

    }

    public static void towerOfHanoi(char source, char intermediate, char destination, int num) {
        if (num == 0) {
            return;
        }
        if (num == 1) {
            System.out.println(source + " --> " + destination);
            return;
        }

        if (num == 2) {
            System.out.println(source + " --> " + intermediate);
            System.out.println(source + " --> " + destination);
            System.out.println(intermediate + " --> " + destination);
            return;
        }
        towerOfHanoi(source, destination, intermediate, num - 1);
        System.out.println(source + " --> " + destination);
        towerOfHanoi(intermediate, source, destination, num - 1);
    }
}
