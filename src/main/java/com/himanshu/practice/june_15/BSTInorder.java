package com.himanshu.practice.june_15;

import java.util.Stack;

/**
 * Created by himanshubhardwaj on 15/06/18.
 * 2:20am
 * 3:00
 */
public class BSTInorder {
    public static void main(String[] args) {
        Node root = new Node(8);
        root = root.insert(root, 6);
        root = root.insert(root, 10);
        root = root.insert(root, 7);
        root = root.insert(root, 9);
        root = root.insert(root, 4);
        root = root.insert(root, 5);
        root = root.insert(root, 11);
        root.inorder(root);
        System.out.println();
        root.inorderWithourRecursion(root);
        System.out.println();
        root.twoNodesWithGivenSum(root, 16);

    }
}


class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    public Node insert(Node root, int value) {
        if (root == null) {
            return new Node(value);
        }
        if (root.value > value) {
            root.left = insert(root.left, value);
        } else {
            root.right = insert(root.right, value);
        }
        return root;
    }


    public void inorder(Node root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.printf("%d ", root.value);
        inorder(root.right);
    }


    public void inorderWithourRecursion(Node node) {
        System.out.println("Reached");
        if (node == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        Node temp = node;

        while (temp != null) {
            stack.push(temp);
            temp = temp.left;
        }
        System.out.println("stack size before entering while: " + stack.size());

        while (temp != null || !stack.empty()) {
            temp = stack.pop();
            System.out.printf("%d ", temp.value);
            temp = temp.right;
            while (temp != null) {
                stack.push(temp);
                temp = temp.left;
            }
        }

    }


    public void twoNodesWithGivenSum(Node node, int sum) {
        System.out.printf("@twoNodesWithGivenSum sum:%d\n", sum);
        if (node == null || (node.left == null && node.right == null)) {
            System.out.println("no two nodes available");
            return;
        }

        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();

        Node temp1 = node; //left se ayega
        Node temp2 = node;//right se ayega

        while (temp1 != null) {
            stack1.push(temp1);
            temp1 = temp1.left;
        }

        while (temp2 != null) {
            stack2.push(temp2);
            temp2 = temp2.right;
        }

        System.out.printf("stack1Size %d stack2Size%d\n", stack1.size(), stack2.size());
        while (!stack1.empty() && !stack2.empty()) {
            if (temp1 == null) {
                temp1 = stack1.pop();
            }
            if (temp2 == null) {
                temp2 = stack2.pop();
            }
            if (temp2 == temp1) {
                System.out.println("Reached @ middle but two elements were not found; returning");
                return;
            }
            int tempSum = temp1.value + temp2.value;
            if (tempSum == sum) {
                System.out.printf("Sum found First:%d\tsecond:%d", temp1.value, temp2.value);
                return;
            }
            if (tempSum > sum) {
                temp2 = temp2.left;

                while (temp2 != null) {
                    stack2.push(temp2);
                    temp2 = temp2.right;
                }
            } else if (tempSum < sum) {
                temp1 = temp1.right;
                while (temp1 != null) {
                    stack1.push(temp1);
                    temp1 = temp1.left;
                }
            }
        }
        System.out.printf("No two node having sum equal to %d found\n", sum);
    }


    public String toString() {
        return String.valueOf(this.value);
    }
}

