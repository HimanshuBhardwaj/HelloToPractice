package com.himanshu.practice.nov.nov5;

/**
 * Created by himanshubhardwaj on 05/11/18.
 */
public class LinkedListP {
    public static void main(String[] args) {
        LNode root = LNode.insert(null, 1);
        root = LNode.insert(root, 1);
        root = LNode.insert(root, 21);
        root = LNode.insert(root, 31);
        root = LNode.insert(root, 1);
        root = LNode.insert(root, 31);
        root = LNode.insert(root, 41);

        root.print(root);
    }

}


class LNode {
    int value;
    LNode next;
    LNode prev;


    public LNode(int value) {
        this.value = value;
        this.next = null;
        this.prev = null;
    }

    static LNode insert(LNode root, int value) {
        if (root == null) {
            return new LNode(value);
        }

        LNode newLNode = new LNode(value);

        newLNode.next = root;
        root.prev = newLNode;
        return newLNode;
    }


    public String toString() {
        if (this.prev != null) {
            return "<==>" + Integer.toString(this.value);
        }
        return Integer.toString(this.value);
    }

    public void print(LNode root) {
        while (root != null) {
            System.out.print(root+" ");
            root = root.next;
        }
    }
}
