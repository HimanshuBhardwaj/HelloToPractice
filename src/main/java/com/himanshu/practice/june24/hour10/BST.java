package com.himanshu.practice.june24.hour10;

import lombok.NoArgsConstructor;

/**
 * Created by himanshubhardwaj on 25/06/18.
 */
public class BST {
    public static void main(String[] args) {
        Node root = new Node(7);
        root = root.insert(root, 8);
        root = root.insert(root, 6);
        root = root.insert(root, 3);
        root = root.insert(root, 4);
        root = root.insert(root, 9);
        root = root.insert(root, 8);


        System.out.println();
        root.inorder(root);
        System.out.println();
        System.out.println("Size: " + root.size(root));
        System.out.println("Height: " + root.height(root));
        System.out.println("diameter: " + root.diameter(root));
        root = root.delete(root, 7);
        root.inorder(root);
        System.out.println();
        System.out.println(root.value);
        System.out.println("Size: " + root.size(root));
        System.out.println("Height: " + root.height(root));
        System.out.println("diameter: " + root.diameter(root));


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
        System.out.print(root.value + " ");
        inorder(root.right);
    }

    public int height(Node root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(height(root.left), height(root.right));
    }


    public int size(Node root) {
        if (root == null) {
            return 0;
        }
        return 1 + size(root.left) + size(root.right);
    }


    public int diameter(Node root) {
        if (root == null) {
            return 0;
        }
        return Math.max(Math.max(diameter(root.left), diameter(root.right)), 1 + height(root.left) + height(root.right));
    }


    public Node delete(Node root, int value) {
        if (root == null) {
            return null;
        }
        if (root.value == value) {
            if (root.left == null && root.right == null) {
                return null;
            } else if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            } else {
                //both children are not null;
                Node succ = successor(root.right);
                int temp = succ.value;
                succ.value = root.value;
                root.value = temp;
                root.right = delete(root.right, value);
            }
        } else if (root.value > value) {
            root.left = delete(root.left, value);
        } else {
            root.right = delete(root.right, value);
        }
        return root;
    }

    private Node successor(Node rootRight) {
        while (rootRight.left != null) {
            rootRight = rootRight.left;
        }
        return rootRight;
    }

}