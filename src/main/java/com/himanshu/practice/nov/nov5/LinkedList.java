package com.himanshu.practice.nov.nov5;

/**
 * Created by himanshubhardwaj on 05/11/18.
 */
public class LinkedList {
    public static void main(String[] args) {
        Node root = Node.insert(null, 1);
        root = Node.insert(root, 1);
        root = Node.insert(root, 21);
        root = Node.insert(root, 31);
        root = Node.insert(root, 1);
        root = Node.insert(root, 31);
        root = Node.insert(root, 41);

        root.print(root);
    }

}


class Node {
    int value;
    Node next;
    Node prev;


    public Node(int value) {
        this.value = value;
        this.next = null;
        this.prev = null;
    }

    static Node insert(Node root, int value) {
        if (root == null) {
            return new Node(value);
        }

        Node newNode = new Node(value);

        newNode.next = root;
        root.prev = newNode;
        return newNode;
    }


    public String toString() {
        if (this.prev != null) {
            return "<==>" + Integer.toString(this.value);
        }
        return Integer.toString(this.value);
    }

    public void print(Node root) {
        while (root != null) {
            System.out.print(root+" ");
            root = root.next;
        }
    }
}
