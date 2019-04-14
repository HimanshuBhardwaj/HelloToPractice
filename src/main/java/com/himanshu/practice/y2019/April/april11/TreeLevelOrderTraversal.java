package com.himanshu.practice.y2019.April.april11;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by himanshubhardwaj on 11/04/19.
 * Started: 10:16 --> 10:30
 * PTree Construction: LGTM
 */
public class TreeLevelOrderTraversal {
    public static void main(String[] args) {
        Node root = new Node(10);
        root = root.insert(root, 9);
        root = root.insert(root, 11);
        root = root.insert(root, 12);
        root = root.insert(root, 8);
        root = root.insert(root, 16);
        root = root.insert(root, 9);
        root = root.insert(root, 7);

        root.inorder(root);
        System.out.println();
        System.out.println(root.height(root));
        System.out.println();
        root.printLevelOrder(root);

    }
}


class Node {
    int value;
    Node left;
    Node right;


    public Node(int value) {
        this.value = value;
    }

    public Node insert(Node root, int value) {
        if (root == null) {
            return new Node(value);
        }

        if (root.value <= value) {
            root.right = insert(root.right, value);
        } else {
            root.left = insert(root.left, value);
        }
        return root;
    }


    public int height(Node root) {
        if (root == null) {
            return 0;
        } else {
            return 1 + Math.max(height(root.left), height(root.right));
        }
    }

    public void inorder(Node root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.print(root.value + ", ");
        inorder(root.right);
    }

    public void printLevelOrder(Node root) {
        if (root == null) {
            return;
        }
        Queue<Node> level = new LinkedList<>();
        level.add(root);
        int l = 0;


        while (!level.isEmpty()) {
            LinkedList<Node> levels = new LinkedList<>(level);
            level = new LinkedList<>();
            System.out.print(l + ": ");
            for (Node n : levels) {
                System.out.print(n.value + ", ");
                if (n.left != null) {
                    level.add(n.left);
                }
                if (n.right != null) {
                    level.add(n.right);
                }
            }
            l++;
            System.out.println();
        }
    }


}
