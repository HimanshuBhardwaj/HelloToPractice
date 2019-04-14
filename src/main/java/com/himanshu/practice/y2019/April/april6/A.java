package com.himanshu.practice.y2019.April.april6;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 06/04/19.
 */
public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
//
//        int colourPMax[] = new int[300001];
//        int colourPMin[] = new int[300001];
//
//        for (int i = 0; i < colourPMin.length; i++) {
//            colourPMin[i] = Integer.MAX_VALUE;
//        }
//
//        String[] str = br.readLine().split(" ");
//
//        for (int i = 0; i < str.length; i++) {
//            int colour = Integer.parseInt(str[i]);
//            colourPMax[colour] = Math.max(colourPMax[colour], 1 + i); // this is last position of same colour
//            colourPMin[colour] = Math.min(colourPMax[colour], 1 + i); // this is first position of colour
//        }
//
//        int minPos = Integer.MAX_VALUE;
//        int maxPos = Integer.MIN_VALUE;
//        int colour = -1;
//
//        for (int i = 0; i < colourPMax.length; i++) {
//            if (colourPMax[i] != 0) {
//                if (maxPos < colourPMax[i]) {
//                    maxPos = colourPMax[i];
//                    colour = i;
//                }
//            }
//        }
//
//        for (int i = 0; i < colourPMin.length; i++) {
//            if (colourPMin[i] != Integer.MAX_VALUE && i != colour) {
//                if (minPos > colourPMin[i]) {
//                    minPos = colourPMin[i];
//                }
//            }
//        }
//
////        System.out.println(colour);
////        System.out.println(maxPos);
////        System.out.println(minPos);
//        System.out.print(Math.abs(minPos - maxPos));

        String[] str = br.readLine().split(" ");

        int firstCPOS[] = new int[2];
        int[] firstColNum = new int[2];
        int[] lastColPos = new int[2];
        int[] lostcolNum = new int[2];

        for (int i = 0; i < 2; i++) {
            firstColNum[i] = -1;
            lastColPos[i] = -1;
            lostcolNum[i] = -1;
            firstCPOS[i] = -1;
        }


        for (int i = 0; i < str.length; i++) {
            int colour = Integer.parseInt(str[i]);
            if (firstColNum[0] == -1) {
                firstColNum[0] = colour;
                firstCPOS[0] = i;
            } else if (firstColNum[1] == -1 && colour != firstColNum[0]) {
                firstColNum[1] = colour;
                firstCPOS[1] = i;
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            int colour = Integer.parseInt(str[i]);
            if (lastColPos[0] == -1) {
                lastColPos[0] = i;
                lostcolNum[0] = colour;
            } else if (lastColPos[1] == -1 && colour != lostcolNum[0]) {
                lastColPos[1] = i;
                lostcolNum[1] = colour;
            }
        }


        if (firstColNum[0] != lostcolNum[0]) {
            System.out.println(Math.abs(firstCPOS[0] - lastColPos[0]));
        } else {
            int p1 = Math.abs(firstCPOS[0] - lastColPos[1]);
            int p2 = Math.abs(firstCPOS[1] - lastColPos[0]);
            System.out.println(Math.max(p1,p2));
        }


    }
}

