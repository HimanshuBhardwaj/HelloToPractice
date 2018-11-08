package com.himanshu.practice.nov.nov07;

/**
 * Created by himanshubhardwaj on 07/11/18.
 */
//reversing linkedlist and also reversing it in pairs in pairs
public class LinkedlistP {
    public static void main(String[] args) {
        Node root = Node.insert(null, 5);
        root = Node.insert(root, 25);
        root = Node.insert(root, 53);
        root = Node.insert(root, 52);
        root = Node.insert(root, 54);
        root = Node.insert(root, 45);
        root = Node.insert(root, 55);
        root = Node.insert(root, 551);
        root = Node.insert(root, 5);
        root = Node.insert(root, 9);

        System.out.println(Node.size(root));
        Node.print(root);
        System.out.println();

        //Node reverseNode = Node.reverse(root);
        //Node.print(reverseNode);

        Node reverse = Node.reversePairs(root, 2);
        System.out.println(reverse);
        Node.print(reverse);


    }
}


class Node {
    int value;
    Node next;


    public Node(int value) {
        this.next = null;
        this.value = value;
    }

    static Node insert(Node root, int value) {
        Node newNode = new Node(value);
        newNode.next = root;
        return newNode;
    }

    static void print(Node root) {
        while (root != null) {
            System.out.print(root.value + "-->");
            root = root.next;
        }
        System.out.println();
    }

    static int size(Node root) {
        int count = 0;

        while (root != null) {
            count++;
            root = root.next;
        }

        return count;
    }


    static Node reversePairs(Node root, int k) {
        if (root == null) {
            return root;
        }

        Node current = root;
        Node currentEnd = root;
        Node nextStart = root;


        for (int i = 0; (nextStart != null) && (i < k); i++) {
            nextStart = nextStart.next;
            if (i != 0) {
                currentEnd = currentEnd.next;
            }
        }

        if (nextStart == null) {
            return reverse(current);
        }


        currentEnd.next = null;
        Node remainingReverse = reversePairs(nextStart, k);


        Node reverse = reverse(current);
        Node currentReverseEnd = endOfList(reverse);
        currentReverseEnd.next = remainingReverse;

        return reverse;
    }

    static Node endOfList(Node root) {
        if (root == null || root.next == null) {
            return root;
        }

        while (root.next != null) {
            root = root.next;
        }
        return root;
    }

    static Node reverse(Node root) {
        return reverseHelper(root, null);
    }

    private static Node reverseHelper(Node root, Node prev) {
        if (root == null) {
            return prev;
        }

        if (root.next == null) {
            root.next = prev;
            return root;
        }


        Node next = root.next;
        root.next = prev;
        return reverseHelper(next, root);
    }
}