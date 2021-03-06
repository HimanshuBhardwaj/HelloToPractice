package com.himanshu.practice.y2018.june.june20;

/**
 * Created by Himanshu Bhardwaj on 20/06/18.
 */
public class TowerOfHanoi {
    public static void main(String[] args) {
        solveHanoi("A0Paper", 3, "MazeWithKeys", "Reroll");
    }


    static void solveHanoi(String source, int count, String destination, String intermediate) {
        if (count <= 0) {
            return;
        }
        solveHanoi(source, count - 1, intermediate, destination);
        System.out.println("moving " + count + " hop from " + source + " to " + destination);
        solveHanoi(intermediate, count - 1, destination, source);
    }
}
