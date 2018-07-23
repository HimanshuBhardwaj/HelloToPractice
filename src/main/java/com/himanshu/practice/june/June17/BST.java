package com.himanshu.practice.june.June17;

/**
 * Created by Himanshu Bhardwaj on 17/06/18.
 * 11:24
 */
public class BST {
    public static void main(String[] args) {
        Node root = new Node(9);
        root = root.insert(root, 11);
        root = root.insert(root, 7);
        root = root.insert(root, 13);
        root = root.insert(root, 13);
        root = root.insert(root, 15);
        root = root.insert(root, 5);
        root = root.insert(root, 17);
        root = root.insert(root, 3);

        root.inorder(root);
        System.out.println();
        System.out.println(root.height(root));
        System.out.println();
        System.out.println(root.nodeCount(root));
        root = root.delete(root, 9);
        root.inorder(root);
        System.out.println();
        root = root.delete(root, 3);
        root.inorder(root);
    }
}


class Node {
    int value;
    Node right;
    Node left;

    public String toString() {
        return Integer.toString(value);
    }

    public Node(int value) {
        this.value = value;
        right = null;
        left = null;
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

    public int nodeCount(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + nodeCount(node.left) + nodeCount(node.right);
    }

    public Node delete(Node root, int value) {
        if (root == null) {
            return null;
        }
        if (root.value == value) {
            if ((root.left == null) && (root.right == null)) {
                return null;
            }
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }
            Node successor = successor(root);
            swap(successor, root);
            root.right = delete(successor, value);
            return root;
        }
        if (root.value > value) {
            root.left = delete(root.left, value);
        }
        if (root.value < value) {
            root.right = delete(root.right, value);
        }

        return root;
    }

    private void swap(Node successor, Node root) {
        int temp = successor.value;
        successor.value = root.value;
        root.value = temp;
    }

    //assumption that both chil are notnull
    public Node successor(Node node) {
        node = node.right;
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }


}
