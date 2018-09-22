package com.himanshu.practice.sept.sept22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

/**
 * Created by himanshubhardwaj on 22/09/18.
 * prefixToPostFix conversion
 */
public class PrefixToPostfix {
    static HashSet<Character> operators = new HashSet<>();
    private static Stack<Op> tleStack;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        operators.add('+');
        operators.add('*');
        operators.add('/');
        operators.add('-');

        int t = Integer.parseInt(br.readLine());


    }


    //I was supposed to impleement this function
    public static List<String> prefixToPostfix(List<String> prefixes) {
        List<String> postFix = new ArrayList<>();

        for (String s : prefixes) {
            postFix.add(prefixToPostfixHelper(s));
        }

        return postFix;
    }


    static String prefixToPostfixHelper(String prefix) {
        Op[] results = new Op[prefix.length()];

        for (int i = 0; i < prefix.length(); i++) {
            results[i] = new Op(Character.toString(prefix.charAt(i)), operators.contains(prefix.charAt(i)));
            System.out.println(results[i]);
        }
        Stack<Op> stack = new Stack<>();

        for (int i = 0; i < results.length; i++) {
            if (results[i].isOperator) {
                stack.push(results[i]);
            } else {
                if (stack.isEmpty() || stack.peek().isOperator) {
                    stack.push(results[i]);
                } else {
                    stack.push(results[i]);
                    settleStack(stack);
                }
            }
        }
        return stack.peek().value;
    }


    public static void settleStack(Stack<Op> stack) {
        if (stack.size() < 3 || stack.peek().isOperator) {
            return;
        }

        Op top = stack.pop();

        if (stack.peek().isOperator) {
            stack.push(top);
            return;
        } else {
            Op top1 = stack.pop();
            Op operator = stack.pop();
            Op newResult = new Op(top1.value + top.value + operator.value, false);
            stack.push(newResult);
            settleStack(stack);
        }
    }
}


class Op {
    String value;
    boolean isOperator; //true if it is an operator, falseotherwise

    @java.beans.ConstructorProperties({"value", "isOperator"})
    public Op(String value, boolean isOperator) {
        this.value = value;
        this.isOperator = isOperator;
    }

    public String toString() {
        return "Op(value=" + this.value + ", isOperator=" + this.isOperator + ")";
    }
}
