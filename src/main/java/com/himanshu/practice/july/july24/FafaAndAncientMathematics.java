package com.himanshu.practice.july.july24;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.TreeMap;

/**
 * Created by himanshubhardwaj on 24/07/18.
 * Problem Statement: https://codeforces.com/problemset/problem/935/E
 * TODO: Make it work
 */
public class FafaAndAncientMathematics {
    static Stack<Node> stack = new Stack<>();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] str = br.readLine().toCharArray();

        for (int i = 0; i < str.length; ) {
            i = stackOperation(i, str);
        }

        String str1[] = br.readLine().split(" ");
        int maxP = Integer.parseInt(str1[0]);
        int maxM = Integer.parseInt(str1[1]);

        Node root = stack.pop();
        if (!stack.isEmpty() || root == null) {
            System.out.println("Something wrong");
        } else {
            root.print(root);
        }

        System.out.println();
        System.out.println("------");
        System.out.println(root.getMaxSum(root, maxP, maxM));
        System.out.println("------");
        System.out.println(root.maxMap);
        System.out.println(root.minMap);
        System.out.println(Node.flagValidity);
    }

    private static int stackOperation(int pos, char[] str) {
        char c = str[pos];
        Node newNode = new Node();
        switch (c) {
            case '(':
                newNode.function = 2;
                stack.push(newNode);
                break;
            case ')':
                doCloseBracketOperations();
                break;
            case '?':
                newNode.function = 1;
                stack.push(newNode);
                break;
            default:
                int offset = 0;
                while (isNumber(str, pos, offset)) {
                    offset++;
                }
                newNode.number = getNumber(str, pos, offset - 1);
                stack.push(newNode);
                return pos + offset;
        }
        return ++pos;
    }

    //it is assumed that if this function is called, substring will form a number
    private static int getNumber(char[] str, int pos, int off) {
        String st = "";
        for (int i = pos; i <= (pos + off); i++) {
            st += String.valueOf(str[i]);
        }
        System.out.println(st + "\t" + Integer.parseInt(st));
        return Integer.parseInt(st);
    }

    private static boolean isNumber(char[] str, int pos, int offset) {
        String st = "";
        for (int i = pos; i <= (pos + offset); i++) {
            st += String.valueOf(str[i]);
        }

        try {
            Integer.parseInt(st);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    private static void doCloseBracketOperations() {
        Node secondOperand = stack.pop();
        Node operator = stack.pop();
        Node firstOperand = stack.pop();
        stack.pop();
        operator.left = firstOperand;
        operator.right = secondOperand;
        stack.push(operator);
    }
}


//function, {0, operand}, {1, operator}, {2, open bracket}, {3, close bracket}
class Node {
    int function;
    int number;
    Node left = null;
    Node right = null;
    TreeMap<String, Long> maxMap = new TreeMap<>();
    TreeMap<String, Long> minMap = new TreeMap<>();
    static boolean flagValidity = true;


    public long getMaxSum(Node root, int plus, int minus) {
        System.out.println(plus + "\t" + minus + "......");
        if (root == null || (plus < 0 && minus < 0)) {
            System.out.println("Something wrong...");
            return Long.MIN_VALUE;
        }


        if (plus == 0 && minus == 0) {
            if (root.function == 0) {
                return root.number;
            } else {
                return Long.MIN_VALUE;
            }
        }


        if (root.function == 0) {
            //inconsistent state
            return Long.MIN_VALUE;
        }


        if (root.maxMap.containsKey(getKey(plus, minus))) {
            return root.maxMap.get(getKey(plus, minus));
        }

        long tempSum = Long.MIN_VALUE;

        if (plus > 0) {
            for (int i = 0; i < plus; i++) {
                long sumL = getMaxSum(root.left, i, minus);
                long sumR = getMaxSum(root.right, plus - 1 - i, minus);
                tempSum = Math.max(sumL + sumR, tempSum);
            }
        }

        if (minus > 0) {
            for (int i = 0; i < plus; i++) {
                long sumL = getMaxSum(root.left, plus, i);
                long sumR = getMinSum(root.right, plus, minus - 1 - i);
                tempSum = Math.max(sumL - sumR, tempSum);
            }
        }

        System.out.print("Max: ");
        root.print(root);
        System.out.println("\t" + tempSum);

        root.maxMap.put(getKey(plus, minus), tempSum);
        return tempSum;
    }


    private long getMinSum(Node root, int plus, int minus) {
        if (root == null || (plus < 0 && minus < 0)) {
            return Long.MAX_VALUE;
        }

        if (root.function == 0) {
            if (plus == 0 && minus == 0) {
                return root.number;
            } else {
                return Long.MIN_VALUE;
            }
        }

        if (plus == 0 && minus == 0) {
            return Long.MIN_VALUE;
        }


        if (root.minMap.containsKey(getKey(plus, minus))) {
            return root.minMap.get(getKey(plus, minus));
        }

        long tempSum = Long.MAX_VALUE;

        if (plus > 0) {
            for (int i = 0; i < plus; i++) {
                long sumL = getMinSum(root.left, i, minus);
                long sumR = getMinSum(root.right, plus - 1 - i, minus);
                tempSum = Math.min(sumL + sumR, tempSum);
            }
        }
        if (minus > 0) {
            for (int i = 0; i < minus; i++) {
                long sumL = getMinSum(root.left, plus, i);
                long sumR = getMaxSum(root.right, plus, minus - 1 - i);
                tempSum = Math.max(sumL - sumR, tempSum);
            }
        }

        root.minMap.put(getKey(plus, minus), tempSum);
        return tempSum;
    }

    private String getKey(int plus, int minus) {
        return plus + "|" + minus;
    }

    //print inorder traversal
    void print(Node root) {
        if (root == null) {
            return;
        }

        if (root.function == 0) {
            if (root.number == 0) {
                flagValidity = false;
            }
        }

        System.out.print("(");
        print(root.left);
        switch (root.function) {
            case 0:
                System.out.print(" " + root.number + " ");
                break;
            case 1:
                System.out.print(" " + "?" + " ");
                break;
            case 2:
                System.out.print("Something Wrong");
                break;
            case 3:
                System.out.print("Something Wrong");
                break;
            default:
                System.out.print("Something Wrong");
        }

        print(root.right);
        System.out.print(")");
    }
}
/*
*

(12?1)
0 1



* */