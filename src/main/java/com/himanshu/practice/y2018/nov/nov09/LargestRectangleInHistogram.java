package com.himanshu.practice.y2018.nov.nov09;

import lombok.AllArgsConstructor;

import java.util.Stack;

/**
 * Created by himanshubhardwaj on 12/11/18.
 */
public class LargestRectangleInHistogram {
    public static void main(String[] args) {
        int size = 1000000;
        int arr[] = new int[size];

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * 10000);

        }
        long endTime = System.currentTimeMillis();



        System.out.println(StackSolution.findArea(arr)+"\t\tTime: "+(endTime-startTime));


    }

}

class DivideAndConquer {
    int findAreaa(int[] arr, int start, int end) {
        return 0;
    }
}


class StackSolution {
    static int findArea(int[] arr) {
        int maxArea = Integer.MIN_VALUE;
        Stack<SNode> stack = new Stack<>();

        for (int i = 0; i < arr.length; i++) {
            if (stack.isEmpty()) {
                stack.push(new SNode(i, arr[i]));
            } else if (stack.peek().value <= arr[i]) {
                stack.push(new SNode(i, arr[i]));
            } else {
                //small than arr
                while (!stack.isEmpty() && stack.peek().value > arr[i]) {
                    SNode tS2 = stack.pop();

                    if (!stack.isEmpty()) {
                        maxArea = Math.max(maxArea, tS2.value * (i - stack.peek().index - 1));
                    } else {
                        maxArea = Math.max(maxArea, tS2.value * (i));
                    }

                }
                stack.push(new SNode(i, arr[i]));
            }
            //System.out.println(i + "\t" + stack + "\t\t\t\t" + maxArea);
        }

        if (!stack.isEmpty()) {
            SNode tS2 = stack.pop();

            if (!stack.isEmpty()) {
                maxArea = Math.max(maxArea, tS2.value * (arr.length - 1 - stack.peek().index));
            } else {
                maxArea = Math.max(maxArea, tS2.value * arr.length);
            }
        }

        return maxArea;
    }
}

@AllArgsConstructor
class SNode {
    int index;
    int value;

    public String toString() {
        return this.value + ",";
    }
}