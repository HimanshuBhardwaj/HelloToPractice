package com.himanshu.practice.y2018.july.july24;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Stack;

/**
 * Created by himanshubhardwaj on 24/07/18.
 * Problem Statement: https://codeforces.com/problemset/problem/935/E
 * TODO: Make it work, currently i am getting TLE onto it.
 * https://codeforces.com/contest/935/submission/40734909
 *
 */
public class FafaAndAncientMathematics {
    static Stack<Node> stack = new Stack<>();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] str = br.readLine().toCharArray();

        for (int i = 0; i < str.length; i++) {
            stackOperation(i, str);
        }

        String str1[] = br.readLine().split(" ");
        int maxP = Integer.parseInt(str1[0]);
        int maxM = Integer.parseInt(str1[1]);


        Node root = stack.pop();
        root.setNumChildren(root);
        if (!stack.isEmpty() || root == null) {
            System.out.println("Something wrong");
        } else {
            System.out.print(root.getMaxSum(root, maxP, maxM));
//            System.out.print("Printing PTree:\t");
//            root.print(root);
//            System.out.println();
        }


    }

    private static void stackOperation(int pos, char[] str) {
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
                newNode.number = Integer.parseInt(String.valueOf(str[pos]));
                stack.push(newNode);
        }
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
    int numOperators; //this will contains the number of non root children in the subtree starting from the root
    Node left = null;
    Node right = null;
    HashMap<String, Long> maxMap = new HashMap<>();
    HashMap<String, Long> minMap = new HashMap<>();


    public long getMaxSum(Node root, int plus, int minus) {
        if (root == null || (plus < 0 || minus < 0) || (plus + minus != root.numOperators)) {
            return Long.MIN_VALUE;
        }

        if (root.function == 0) {
            return root.number;
        }


        if (root.maxMap.containsKey(getKey(plus, minus))) {
            return root.maxMap.get(getKey(plus, minus));
        }

        long tempSum = Long.MIN_VALUE;

        for (int i = 0; i <= root.left.numOperators; i++) {

            long maxL = getMaxSum(root.left, i, root.left.numOperators - i);
            long maxR = getMaxSum(root.right, plus - i - 1, root.right.numOperators - (plus - i - 1));

            if (maxL != Long.MIN_VALUE && maxR != Long.MIN_VALUE) {
                tempSum = Math.max(maxL + maxR, tempSum);
            }

            maxR = getMinSum(root.right, plus - i, root.right.numOperators - (plus - i));

            if (maxL != Long.MIN_VALUE && maxR != Long.MAX_VALUE) {
                tempSum = Math.max(maxL - maxR, tempSum);
            }

        }

//        System.out.print("Max: (+)" + plus + " " + " (-)" + minus);
//        root.print(root);
//        System.out.println("\t" + tempSum);
        root.maxMap.put(getKey(plus, minus), tempSum);
        return tempSum;
    }


    private long getMinSum(Node root, int plus, int minus) {
        if (root == null || (plus < 0 || minus < 0) || (plus + minus != root.numOperators)) {
            return Long.MAX_VALUE;
        }

        if (root.function == 0) {
            return root.number;
        }

        if (root.minMap.containsKey(getKey(plus, minus))) {
            return root.minMap.get(getKey(plus, minus));
        }

        long tempSum = Long.MAX_VALUE;

        for (int i = 0; i <= root.left.numOperators; i++) {
            long maxL = getMinSum(root.left, i, root.left.numOperators - i);
            long maxR = getMinSum(root.right, plus - i - 1, root.right.numOperators - (plus - i - 1));
            if (maxL != Long.MAX_VALUE && maxR != Long.MAX_VALUE) {
                tempSum = Math.min(maxL + maxR, tempSum);
            }

            maxR = getMaxSum(root.right, plus - i, root.right.numOperators - (plus - i));
            if (maxL != Long.MAX_VALUE && maxR != Long.MIN_VALUE) {
                tempSum = Math.min(maxL - maxR, tempSum);
            }

        }

//        System.out.print("Max: (+)" + plus + " " + " (-)" + minus);
//        root.print(root);
//        System.out.println("\t" + tempSum);

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

        System.out.print("(");
        print(root.left);
        switch (root.function) {
            case 0:
                System.out.print(root.number + ":" + root.numOperators);
                break;
            case 1:
                System.out.print(" " + "?:" + root.numOperators + " ");
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

    public int setNumChildren(Node root) {
        if (root == null || root.function == 0) {
            return 0;
        }

        int sum = 1;
        sum += setNumChildren(root.left) + setNumChildren(root.right);
        root.numOperators = sum;
        //System.out.println(root + " " + sum);

        return sum;
    }
}
/*
*

(12?1)
0 1


((6?2)?7)
0 2


* */