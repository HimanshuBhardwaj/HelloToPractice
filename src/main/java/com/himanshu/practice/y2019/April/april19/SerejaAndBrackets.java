package com.himanshu.practice.y2019.April.april19;

import lombok.AllArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * Created by himanshubhardwaj on 19/04/19.
 * 4:17
 */
public class SerejaAndBrackets {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] c = br.readLine().toCharArray();
        Brac[] bracs = new Brac[c.length];


        for (int i = 0; i < bracs.length; i++) {
            bracs[i] = new Brac((c[i] == '('), i, null, null);
        }

        Stack<Brac> stack = new Stack<Brac>();
        for (int i = 0; i < bracs.length; i++) {
            if (bracs[i].isOpen) {
                stack.push(bracs[i]);
            } else {
                if (!stack.isEmpty() && stack.peek().isOpen) {
                    stack.peek().matchingIndex = i;
                    bracs[i].matchingIndex = stack.peek().index;
                    stack.pop();
                } else {
                    stack.push(bracs[i]);
                }
            }
        }


        for (Brac b : bracs) {
            System.out.println(b);
        }


    }


}

@AllArgsConstructor
class Brac {
    boolean isOpen;
    int index;
    Integer matchingIndex;
    Integer commulativeMAtched;

    public String toString() {
        return "Brac(isOpen=" + this.isOpen + ", index=" + this.index + ", matchingIndex=" + this.matchingIndex + ", commulativeMAtched=" + this.commulativeMAtched + ")";
    }
}
