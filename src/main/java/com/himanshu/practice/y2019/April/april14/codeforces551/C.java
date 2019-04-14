package com.himanshu.practice.y2019.April.april14.codeforces551;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by himanshubhardwaj on 13/04/19.
 * TODO: To ask
 */
public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        ArrayList<Brac> bracs = new ArrayList<>();
        char[] str = br.readLine().toCharArray();
        Stack<Brac> stack = new Stack<>();


        for (int i = 0; i < str.length - 1; i++) {
            if (str[i] == '(') {
                Brac b = new Brac('(', i);
                bracs.add(b);
                stack.push(b);
            } else if (str[i] == ')') {
                Brac b = new Brac(')', i);
                bracs.add(b);

                if (!stack.isEmpty()) {
                    if (stack.peek().c == '?') {
                        if (stack.peek().index != 0) {
                            stack.peek().c = '(';
                            stack.pop();
                        } else {
                            System.out.print(":(");
                            return;
                        }
                    } else if (stack.peek().c == '(') {
                        stack.pop();
                    } else {
                        System.out.print(":(");
                        return;
                    }
                } else {
                    System.out.print(":(");
                    return;
                }
            } else {
                Brac b = new Brac('?', i);
                bracs.add(b);
                stack.push(b);
            }
        }




//
//        if (str[str.length - 1] == ')') {
//            if (stack.)
//
//
//        } else if (str[str.length - 1] == '(') {
//
//        } else if (str[str.length - 1] == '?') {
//
//        }


//        System.out.println(stack);
//        System.out.println("....");
//        System.out.println(bracs);
//        System.out.println("-----------");


        stack = new Stack<>();


        if (!stack.isEmpty()) {
            System.out.print(":(");
            return;
        }


        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(bracs.get(i).c);
        }

        System.out.print(sb.toString());
    }
}

class Brac {
    char c;
    int index;

    @java.beans.ConstructorProperties({"c", "index"})
    public Brac(char c, int index) {
        this.c = c;
        this.index = index;
    }

    public String toString() {
        return Character.toString(this.c);
    }
}