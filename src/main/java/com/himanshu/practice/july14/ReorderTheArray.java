package com.himanshu.practice.july14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by himanshubhardwaj on 13/07/18.
 * TODO: Finish it
 */
public class ReorderTheArray {

}

class Node {

    int data;
    Node left, right, parent;

    Node(int d) {
        data = d;
        left = right = parent = null;
    }
}

class BinaryTree {

    static Node root;

    /* A function that constructs Balanced Binary Search Tree
     from a sorted array */
    Node sortedArrayToBST(int arr[], int start, int end, Node parent) {

        /* Base Case */
        if (start > end) {
            return null;
        }

        /* Get the middle element and make it root */
        int mid = (start + end) / 2;
        Node node = new Node(arr[mid]);
        node.parent = parent;

        /* Recursively construct the left subtree and make it
         left child of root */
        node.left = sortedArrayToBST(arr, start, mid - 1, node);

        /* Recursively construct the right subtree and make it
         right child of root */
        node.right = sortedArrayToBST(arr, mid + 1, end, node);

        return node;
    }

    Node minValue(Node node) {
        Node current = node;

        /* loop down to find the leftmost leaf */
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }


    Node inOrderSuccessor(Node root, Node n) {

        // step 1 of the above algorithm
        if (n.right != null) {
            return minValue(n.right);
        }

        // step 2 of the above algorithm
        Node p = n.parent;
        while (p != null && n == p.right) {
            n = p;
            p = p.parent;
        }
        return p;
    }


    Node deleteNode(Node root) {
        if (root.left == null && root.right == null) {
            //leaf
            return null;
        }

        if (root.left == null) {
            root.right.parent = root.parent;
            if (root.parent != null) {
                root.parent = root.right;
            } else {
                return root.right;
            }
            return root;
        }
        if (root.right == null) {
            if (root.parent != null) {
                root.parent = root.left;
            } else {
                return root.left;
            }
            return root;
        }

        Node succe = root.right;

        while (succe.left != null) {
            succe = succe.left;
        }

        root.data = succe.data;

        deleteNode(succe);
        return root;
    }

    /* A utility function to print preorder traversal of BST */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String str[] = br.readLine().split(" ");
        int[] arr = new int[n];
        int[] sArr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(str[i]);
            sArr[i] = arr[i];
        }
        Arrays.sort(sArr);
        BinaryTree tree = new BinaryTree();
        root = tree.sortedArrayToBST(sArr, 0, n - 1, null);


    }
}
