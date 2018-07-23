package com.himanshu.practice.june.june20;

/**
 * Created by Himanshu Bhardwaj on 20/06/18.
 */
public class BalancedParanthesis {


    public static void main(String[] args) {
        print(0, 0, new char[8], 4);
    }


    public static void print(int right, int left, char par[], int max) {
        if (right == max) {
            for (int i = (right + left); i < par.length; i++) {
                par[i] = ')';
            }
            printString(par);
            return;
        }
        par[right + left] = '(';
        print(right + 1, left, par, max);
        if (right > left) {
            par[right + left] = ')';
            print(right , left+1, par, max);
        }

    }

    private static void printString(char[] string) {
        for(char c:string){
            System.out.print(c);
        }
        System.out.println();
    }
}
