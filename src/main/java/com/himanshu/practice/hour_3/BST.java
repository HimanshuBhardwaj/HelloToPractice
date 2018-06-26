//package com.himanshu.practice.hour_3;
//
///**
// * Created by himanshubhardwaj on 08/06/18.
// */
//public class BST {
//    public static void main(String[] args) {
//        june20.himanshu.practice.june_8.hour_8.GraphOp node = june20.himanshu.practice.june_8.hour_8.GraphOp.insertHelper(null, 4);
//        node = june20.himanshu.practice.june_8.hour_8.GraphOp.insertHelper(node, 4);
//        node = june20.himanshu.practice.june_8.hour_8.GraphOp.insertHelper(node, -14);
//        node = june20.himanshu.practice.june_8.hour_8.GraphOp.insertHelper(node, 24);
//        node = june20.himanshu.practice.june_8.hour_8.GraphOp.insertHelper(node, 43);
//        node = june20.himanshu.practice.june_8.hour_8.GraphOp.insertHelper(node, -214);
//        node = june20.himanshu.practice.june_8.hour_8.GraphOp.insertHelper(node, -13);
//        node = june20.himanshu.practice.june_8.hour_8.GraphOp.insertHelper(node, -133);
//        node = june20.himanshu.practice.june_8.hour_8.GraphOp.insertHelper(node, 133);
//        node = june20.himanshu.practice.june_8.hour_8.GraphOp.insertHelper(node, 5);
//
//
//        june20.himanshu.practice.june_8.hour_8.GraphOp.inorder(node);
//        System.out.println();
//
//        node = june20.himanshu.practice.june_8.hour_8.GraphOp.delete(node, 4);
//        june20.himanshu.practice.june_8.hour_8.GraphOp.inorder(node);
//        System.out.println();
//        node = june20.himanshu.practice.june_8.hour_8.GraphOp.delete(node, 4);
//
//        System.out.println("Root: "+node.value);
//        System.out.println();
//        june20.himanshu.practice.june_8.hour_8.GraphOp.inorder(node);
//        node = june20.himanshu.practice.june_8.hour_8.GraphOp.delete(node, 5);
//        System.out.println();
//        june20.himanshu.practice.june_8.hour_8.GraphOp.inorder(node);
//
//    }
//}
//
//
//class GraphOp {
//    int value;
//    june20.himanshu.practice.june_8.hour_8.GraphOp left;
//    june20.himanshu.practice.june_8.hour_8.GraphOp right;
//
//    public GraphOp(int value) {
//        this.value = value;
//        this.left = null;
//        this.right = null;
//    }
//
//    static june20.himanshu.practice.june_8.hour_8.GraphOp insertHelper(june20.himanshu.practice.june_8.hour_8.GraphOp root, int value) {
//        june20.himanshu.practice.june_8.hour_8.GraphOp node = new june20.himanshu.practice.june_8.hour_8.GraphOp(value);
//        if (root == null) {
//            return node;
//        }
//
//        if (value >= root.value) {
//            root.right = insertHelper(root.right, value);
//        } else {
//            root.left = insertHelper(root.left, value);
//        }
//        return root;
//    }
//
//
//    static void inorder(june20.himanshu.practice.june_8.hour_8.GraphOp bst) {
//        if (bst == null) {
//            return;
//        }
//        inorder(bst.left);
//        System.out.print(bst.value + " ");
//        inorder(bst.right);
//    }
//
//    static int height(june20.himanshu.practice.june_8.hour_8.GraphOp node) {
//        if (node == null) {
//            return 0;
//        }
//        int leftH = height(node.left);
//        int rightH = height(node.right);
//        int k = 1 + Math.max(leftH, rightH);
//        return k;
//    }
//
//    static june20.himanshu.practice.june_8.hour_8.GraphOp delete(june20.himanshu.practice.june_8.hour_8.GraphOp node, int value) {
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
//            june20.himanshu.practice.june_8.hour_8.GraphOp succ = successor(node);
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
//    private static june20.himanshu.practice.june_8.hour_8.GraphOp successor(june20.himanshu.practice.june_8.hour_8.GraphOp node) {
//        node = node.right;
//
//        while (node.left != null) {
//            node = node.left;
//        }
//
//        return node;
//    }
//}