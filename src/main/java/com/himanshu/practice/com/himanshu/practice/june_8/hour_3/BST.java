package com.himanshu.practice.com.himanshu.practice.june_8.hour_3;

/**
 * Created by himanshubhardwaj on 08/06/18.
 */
public class BST {
    public static void main(String[] args) {
        Node node = Node.insert(null, 4);
        node = Node.insert(node, 4);
        node = Node.insert(node, -14);
        node = Node.insert(node, 24);
        node = Node.insert(node, 43);
        node = Node.insert(node, -214);
        node = Node.insert(node, -13);
        node = Node.insert(node, -133);
        node = Node.insert(node, 133);
        node = Node.insert(node, 5);


        Node.inorder(node);
        System.out.println();

        node = Node.delete(node, 4);
        Node.inorder(node);
        System.out.println();
        node = Node.delete(node, 4);

        System.out.println("Root: "+node.value);
        System.out.println();
        Node.inorder(node);
        node = Node.delete(node, 5);
        System.out.println();
        Node.inorder(node);

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

    static Node insert(Node root, int value) {
        Node node = new Node(value);
        if (root == null) {
            return node;
        }

        if (value >= root.value) {
            root.right = insert(root.right, value);
        } else {
            root.left = insert(root.left, value);
        }
        return root;
    }


    static void inorder(Node bst) {
        if (bst == null) {
            return;
        }
        inorder(bst.left);
        System.out.print(bst.value + " ");
        inorder(bst.right);
    }

    static int height(Node node) {
        if (node == null) {
            return 0;
        }
        int leftH = height(node.left);
        int rightH = height(node.right);
        int k = 1 + Math.max(leftH, rightH);
        return k;
    }

    static Node delete(Node node, int value) {
        if (node == null) {
            return null;
        }
        if (node.value == value) {
            if (node.left == null && node.right == null) {
                return null;
            }
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }
            //nothing is null
            Node succ = successor(node);
            int temp = node.value;
            node.value = succ.value;
            succ.value = temp;
            node.right = delete(node.right, value);
        } else if (node.value < value) {
            node.right = delete(node.right, value);
        } else {
            node.left = delete(node.left, value);
        }
        return node;
    }

    //assuming that node is not root node
    private static Node successor(Node node) {
        node = node.right;

        while (node.left != null) {
            node = node.left;
        }

        return node;
    }
}