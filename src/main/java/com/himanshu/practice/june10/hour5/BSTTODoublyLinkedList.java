//package com.himanshu.practice.june10.hour5;
//
//import lombok.NoArgsConstructor;
//
///**
// * Created by himanshubhardwaj on 10/06/18.
// * //TODO complete it
// */
//public class BSTTODoublyLinkedList {
//    public static void main(String[] args) {
//        DLL dll = new DLL();
//        dll.insertHelper(12);
//        dll.insertHelper(2);
//        dll.insertHelper(5);
//        dll.insertHelper(4);
//        dll.insertHelper(6);
//        dll.insertHelper(22);
//        dll.insertHelper(1);
//        dll.print();
//        System.out.println();
//        System.out.println();
//
//
//        BST bst = new BST();
//        bst.root = bst.insertHelper(bst.root, 12);
//        bst.root = bst.insertHelper(bst.root, 2);
//        bst.root = bst.insertHelper(bst.root, 5);
//        bst.root = bst.insertHelper(bst.root, 4);
//        bst.root = bst.insertHelper(bst.root, 6);
//        bst.root = bst.insertHelper(bst.root, 22);
//        bst.root = bst.insertHelper(bst.root, 1);
//        bst.print(bst.root);
//
//
//    }
//
//}
//
//
////7:27
//class BST {
//    GraphOp root;
//
//    public GraphOp insertHelper(GraphOp root, int value) {
//
//
//        if (root == null) {
//            return new GraphOp(value);
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
//    public void print(GraphOp node) {
//        if (node == null) {
//            return;
//        }
//        print(node.left);
//        System.out.print(node.value + " --> ");
//        print(node.right);
//    }
//
//
//    public GraphOp BSTToDLL(GraphOp root, GraphOp head) {
//        if (root == null) {
//            return head;
//        }
//
//        if (root.left == null && root.right == null) {
//            GraphOp tempHead = DLL.simpleNodeToDoublyLinkedList(root);
//            head = DLL.mergeDLL(head, tempHead);
//            return head;
//        }
//
//        GraphOp leftLinkedList = BST
//    }
//}
//
//
////5:20 am
//class DLL {
//    //assumption is that this head will always be sorted; and head will have minimum value
//    GraphOp head = null;
//
//    public void insertHelper(int value) {
//        GraphOp node = new GraphOp(value);
//        node = simpleNodeToDoublyLinkedList(node);
//
//        if (head == null) {
//            head = node;
//            return;
//        }
//        head = mergeDLL(head, node);
//    }
//
//    //Both head and node can not be null
//    public static GraphOp mergeDLL(GraphOp head, GraphOp node) {
//        GraphOp prevHead = head.left;
//        GraphOp current = head;
//
//        //Case 1: only one node in head
//        if (prevHead == current) {
//            head.right = node;
//            node.left = head;
//            head.left = node;
//            node.right = head;
//            if (node.value < current.value) {
//                head = node;
//            }
//            return;
//        }
//
//
//        //now we have more than one noad
//        //prevHead having maximum value
//        while (current.value < node.value) {
//            current = current.right;
//            prevHead = prevHead.right;
//            if (current == head) {
//                break;
//            }
//        }
//
//        prevHead.right = node;
//        node.left = prevHead;
//        current.left = node;
//        node.right = current;
//
//        if (node.value < head.value) {
//            head = node;
//        }
//
//        return head;
//
//    }
//
//    static GraphOp simpleNodeToDoublyLinkedList(GraphOp node) {
//        if (node == null) {
//            return null;
//        }
//        node.left = node;
//        node.right = node;
//        return node;
//    }
//
//    void print() {
//        if (head == null) {
//            System.out.println("No node");
//        }
//
//        System.out.print(head.value);
//        if (head.right == head) {
//            return;
//        }
//
//        GraphOp current = head.right;
//
//        while (current != head) {
//            System.out.print(" --> " + current.value);
//            current = current.right;
//        }
//        System.out.println();
//    }
//}
//
//
//class GraphOp {
//    int value;
//    GraphOp left;
//    GraphOp right;
//
//
//    public GraphOp(int value) {
//        this.value = value;
//        this.left = null;
//        this.right = null;
//    }
//}
