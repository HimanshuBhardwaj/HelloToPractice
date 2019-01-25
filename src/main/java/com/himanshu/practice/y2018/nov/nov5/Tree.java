package com.himanshu.practice.y2018.nov.nov5;

import lombok.AllArgsConstructor;

/**
 * Created by himanshubhardwaj on 05/11/18.
 */
public class Tree {
    public static void main(String[] args) {
        TNode root = TNode.insert(null, 5);
        root = TNode.insert(root, 6);
        root = TNode.insert(root, 4);
        root = TNode.insert(root, 7);
        root = TNode.insert(root, 3);
        root = TNode.insert(root, 8);
        root = TNode.insert(root, 2);
        root = TNode.insert(root, 1);
        root = TNode.insert(root, 100);
        TNode.inorder(root);
        root = TNode.delete(root, 100);
        System.out.println();
        TNode.inorder(root);

    }
}

@AllArgsConstructor
class TNode {
    TNode left;
    TNode right;
    int value;

    public TNode(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }


    static TNode insert(TNode root, int value) {
        if (root == null) {
            return new TNode(value);
        }

        if (root.value > value) {
            root.left = TNode.insert(root.left, value);
        } else {
            root.right = TNode.insert(root.right, value);
        }

        return root;
    }

    static void inorder(TNode root) {
        if (root != null) {
            TNode.inorder(root.left);
            System.out.print(root.value + " ");
            TNode.inorder(root.right);

        }
    }

    static TNode delete(TNode root, int value) {

        if (root == null) {
            return null;
        }

        if (value == root.value) {
            if (root.left == null && root.right == null) {
                return null;
            } else if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            } else {
                TNode succ = TNode.getSuccessor(root);
                root.value = succ.value;
                succ.value = value;
                root.right = TNode.delete(root.right, value);
                return root;
            }
        } else if (value >= root.value) {
            root.right = TNode.delete(root.right, value);
            return root;
        } else {
            root.left = TNode.delete(root.left, value);
            return root;
        }
    }

    private static TNode getSuccessor(TNode root) {
        root = root.right;

        while (root.left != null) {
            root = root.left;
        }
        return root;
    }
}
