//package com.himanshu.practice.hour_3;
//
///**
// * Created by himanshubhardwaj on 08/06/18.
// */
//public class BST {
//    public static void main(String[] args) {
//        com.himanshu.practice.june_8.hour_8.Node node = com.himanshu.practice.june_8.hour_8.Node.insert(null, 4);
//        node = com.himanshu.practice.june_8.hour_8.Node.insert(node, 4);
//        node = com.himanshu.practice.june_8.hour_8.Node.insert(node, -14);
//        node = com.himanshu.practice.june_8.hour_8.Node.insert(node, 24);
//        node = com.himanshu.practice.june_8.hour_8.Node.insert(node, 43);
//        node = com.himanshu.practice.june_8.hour_8.Node.insert(node, -214);
//        node = com.himanshu.practice.june_8.hour_8.Node.insert(node, -13);
//        node = com.himanshu.practice.june_8.hour_8.Node.insert(node, -133);
//        node = com.himanshu.practice.june_8.hour_8.Node.insert(node, 133);
//        node = com.himanshu.practice.june_8.hour_8.Node.insert(node, 5);
//
//
//        com.himanshu.practice.june_8.hour_8.Node.inorder(node);
//        System.out.println();
//
//        node = com.himanshu.practice.june_8.hour_8.Node.delete(node, 4);
//        com.himanshu.practice.june_8.hour_8.Node.inorder(node);
//        System.out.println();
//        node = com.himanshu.practice.june_8.hour_8.Node.delete(node, 4);
//
//        System.out.println("Root: "+node.value);
//        System.out.println();
//        com.himanshu.practice.june_8.hour_8.Node.inorder(node);
//        node = com.himanshu.practice.june_8.hour_8.Node.delete(node, 5);
//        System.out.println();
//        com.himanshu.practice.june_8.hour_8.Node.inorder(node);
//
//    }
//}
//
//
//class Node {
//    int value;
//    com.himanshu.practice.june_8.hour_8.Node left;
//    com.himanshu.practice.june_8.hour_8.Node right;
//
//    public Node(int value) {
//        this.value = value;
//        this.left = null;
//        this.right = null;
//    }
//
//    static com.himanshu.practice.june_8.hour_8.Node insert(com.himanshu.practice.june_8.hour_8.Node root, int value) {
//        com.himanshu.practice.june_8.hour_8.Node node = new com.himanshu.practice.june_8.hour_8.Node(value);
//        if (root == null) {
//            return node;
//        }
//
//        if (value >= root.value) {
//            root.right = insert(root.right, value);
//        } else {
//            root.left = insert(root.left, value);
//        }
//        return root;
//    }
//
//
//    static void inorder(com.himanshu.practice.june_8.hour_8.Node bst) {
//        if (bst == null) {
//            return;
//        }
//        inorder(bst.left);
//        System.out.print(bst.value + " ");
//        inorder(bst.right);
//    }
//
//    static int height(com.himanshu.practice.june_8.hour_8.Node node) {
//        if (node == null) {
//            return 0;
//        }
//        int leftH = height(node.left);
//        int rightH = height(node.right);
//        int k = 1 + Math.max(leftH, rightH);
//        return k;
//    }
//
//    static com.himanshu.practice.june_8.hour_8.Node delete(com.himanshu.practice.june_8.hour_8.Node node, int value) {
//        if (node == null) {
//            return null;
//        }
//        if (node.value == value) {
//            if (node.left == null && node.right == null) {
//                return null;
//            }
//            if (node.left == null) {
//                return node.right;
//            }
//            if (node.right == null) {
//                return node.left;
//            }
//            //nothing is null
//            com.himanshu.practice.june_8.hour_8.Node succ = successor(node);
//            int temp = node.value;
//            node.value = succ.value;
//            succ.value = temp;
//            node.right = delete(node.right, value);
//        } else if (node.value < value) {
//            node.right = delete(node.right, value);
//        } else {
//            node.left = delete(node.left, value);
//        }
//        return node;
//    }
//
//    //assuming that node is not root node
//    private static com.himanshu.practice.june_8.hour_8.Node successor(com.himanshu.practice.june_8.hour_8.Node node) {
//        node = node.right;
//
//        while (node.left != null) {
//            node = node.left;
//        }
//
//        return node;
//    }
//}