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
//        dll.insert(12);
//        dll.insert(2);
//        dll.insert(5);
//        dll.insert(4);
//        dll.insert(6);
//        dll.insert(22);
//        dll.insert(1);
//        dll.print();
//        System.out.println();
//        System.out.println();
//
//
//        BST bst = new BST();
//        bst.root = bst.insert(bst.root, 12);
//        bst.root = bst.insert(bst.root, 2);
//        bst.root = bst.insert(bst.root, 5);
//        bst.root = bst.insert(bst.root, 4);
//        bst.root = bst.insert(bst.root, 6);
//        bst.root = bst.insert(bst.root, 22);
//        bst.root = bst.insert(bst.root, 1);
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
//    Node root;
//
//    public Node insert(Node root, int value) {
//
//
//        if (root == null) {
//            return new Node(value);
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
//    public void print(Node node) {
//        if (node == null) {
//            return;
//        }
//        print(node.left);
//        System.out.print(node.value + " --> ");
//        print(node.right);
//    }
//
//
//    public Node BSTToDLL(Node root, Node head) {
//        if (root == null) {
//            return head;
//        }
//
//        if (root.left == null && root.right == null) {
//            Node tempHead = DLL.simpleNodeToDoublyLinkedList(root);
//            head = DLL.mergeDLL(head, tempHead);
//            return head;
//        }
//
//        Node leftLinkedList = BST
//    }
//}
//
//
////5:20 am
//class DLL {
//    //assumption is that this head will always be sorted; and head will have minimum value
//    Node head = null;
//
//    public void insert(int value) {
//        Node node = new Node(value);
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
//    public static Node mergeDLL(Node head, Node node) {
//        Node prevHead = head.left;
//        Node current = head;
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
//    static Node simpleNodeToDoublyLinkedList(Node node) {
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
//        Node current = head.right;
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
//class Node {
//    int value;
//    Node left;
//    Node right;
//
//
//    public Node(int value) {
//        this.value = value;
//        this.left = null;
//        this.right = null;
//    }
//}
