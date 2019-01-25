package com.himanshu.practice.y2018.june.june10.hour3;

import java.util.Arrays;

/**
 * Created by Himanshu Bhardwaj on 10/06/18.
 */
public class SortedArrayToBalancedBST {
    public static void main(String[] args) {
        int size = 110;
        int arr[] = new int[size];

        for (int i = 0; i < size; i++) {
            arr[i] = (i * i + 37 * i + 23) % 1001;
        }

        Arrays.sort(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
        Node node = Node.formBST(arr, 0, arr.length - 1);
        System.out.println(Node.isbalanced(node));

        Node node2 = Node.insert(null, 5);
        node2 = Node.insert(node2, 15);
        node2 = Node.insert(node2, 52);
        node2 = Node.insert(node2, 152);
        node2 = Node.insert(node2, 352);
        node2 = Node.insert(node2, 252);
        node2 = Node.insert(node2, 51);

        System.out.println(Node.isbalanced(node2));


    }
}


class Node {
    int value;
    Node left;
    Node right;

    public Node(int a) {
        value = a;
        this.left = null;
        this.right = null;
    }

    //Both start and end are included
    public static Node formBST(int[] arr, int start, int end) {
        if (start > end) {
            return null;
        }
        if (start == end) {
            Node newNode = new Node(arr[start]);
            return newNode;
        }
        int mid = (start + end) / 2;

        Node newNode = new Node(mid);
        newNode.left = formBST(arr, start, mid - 1);
        newNode.right = formBST(arr, mid + 1, end);
        return newNode;
    }

    public static boolean isbalanced(Node node) {
        if (node == null) {
            return true;
        }

        if (Math.abs(height(node.left) - height(node.right)) <= 1) {
            return isbalanced(node.left) && isbalanced(node.right);
        } else {
            return false;
        }
    }

    public static Node insert(Node node, int value) {

        if (node == null) {
            Node newNode = new Node(value);
            return newNode;
        }
        if (node.value > value) {
            node.left = insert(node.left, value);
        } else {
            node.right = insert(node.right, value);
        }
        return node;
    }

    public static int height(Node node) {
        if (node == null) {
            return 0;
        }
        return (1 + Math.max(height(node.left), height(node.right)));
    }
}