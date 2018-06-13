package com.himanshu.practice.com.himanshu.practice.June13;

import lombok.NoArgsConstructor;

/**
 * Created by himanshubhardwaj on 13/06/18.
 * 6:09 pm
 */

public class BST {
    public static void main(String[] args) {
        Node root = new Node(0);
        root = root.insert(root, -1);
        root = root.insert(root, -1);
        root = root.insert(root, -2);
        root = root.insert(root, -3);
        root = root.insert(root, 3);
        root = root.insert(root, 4);
        root = root.insert(root, 5);

        root.inorder(root);
        System.out.println();
        System.out.println(root.height(root));
        root = root.delete(root, 0);
        root = root.delete(root, -3);
        root = root.delete(root, 5);
        root.inorder(root);

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

    Node insert(Node root, int value) {
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

    public void inorder(Node node) {
        if (node == null) {
            return;
        }
        inorder(node.left);
        System.out.print(node.value + " ");
        inorder(node.right);
    }

    public int height(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(height(node.left), height((node.right)));
    }

    public Node delete(Node node, int value) {
        if (node == null) {
            return null;
        }

        if (node.value == value) {
            if (node.left == null && node.right == null) {
                return null;
            } else if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                Node succ = successor(node.right);
                swapValues(node, succ);
                node.right =  delete(node.right, value);
            }
        } else if (node.value > value) {
            node.left= delete(node.left, value);
        } else {
            node.right =  delete(node.right, value);
        }
        return node;
    }


    private void swapValues(Node node, Node succ) {
        int temp = node.value;
        node.value = succ.value;
        succ.value = temp;
    }

    private Node successor(Node right) {
        while (right.left != null) {
            right = right.left;
        }
        return right;
    }

}
