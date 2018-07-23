package com.himanshu.practice.july.july5.hour5;

import java.util.LinkedList;

/**
 * Created by Himanshu Bhardwaj on 05/07/18.
 */
public class InorderSuccessor {
    public static void main(String[] args) {
        Node root = new Node(11);
        root = root.insert(root, 12);
        root = root.insert(root, 13);
        root = root.insert(root, 11);
        root = root.insert(root, 15);
        root = root.insert(root, 14);
        root = root.insert(root, 9);
        root = root.insert(root, 10);
        root = root.insert(root, 8);
        //ee hai smar wala solution
        root.inorderSuccessor(root, null);


        Node root1 = new Node(11);
        root1 = root1.insert(root1, 12);
        root1 = root1.insert(root1, 13);
        root1 = root1.insert(root1, 11);
        root1 = root1.insert(root1, 15);
        root1 = root1.insert(root1, 14);
        root1 = root1.insert(root1, 9);
        root1 = root1.insert(root1, 10);
        root1 = root1.insert(root1, 8);
        //ee hai not so smart wala solution


        root1.inorderSuccessorwithLinkedlist(root1);


        root1.printAllSuccessor(root1);
        System.out.println();
        root.printAllSuccessor(root);



    }
}


class Node {
    int value;
    Node left = null;
    Node right = null;
    Node succ = null;


    public Node(int value) {
        this.value = value;
    }

    Node insert(Node root, int value) {
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

    public String toString() {
        return value + " ";
    }


    //will return minimum always
    Node inorderSuccessor(Node root, Node tempSucc) {
        if (root == null) {
            return null;
        }

        if (root.right != null) {
            root.succ = inorderSuccessor(root.right, tempSucc);
        } else {
            root.succ = tempSucc;
        }
        Node min = inorderSuccessor(root.left, root);

        if (min == null) {
            return root;
        } else {
            return min;
        }
    }


    //Baccho wala method
    void inorderSuccessorwithLinkedlist(Node root) {
        if (root == null) {
            return;
        }

        LinkedList<Node> inorderSuccList = new LinkedList<>();

        inorderSuccessorHelper(root, inorderSuccList);
        //System.out.println(inorderSuccList);
        Node first = inorderSuccList.pollFirst();

        while (!inorderSuccList.isEmpty()) {
            Node second = inorderSuccList.pollFirst();
            first.succ = second;
            first = second;
        }
    }

    private void inorderSuccessorHelper(Node root, LinkedList<Node> inorderSuccList) {
        if (root == null) {
            return;
        }

        inorderSuccessorHelper(root.left, inorderSuccList);
        inorderSuccList.addLast(root);
        inorderSuccessorHelper(root.right, inorderSuccList);
    }


    public void printAllSuccessor(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        printAllSuccHelper(node);
    }

    public void printAllSuccHelper(Node node) {
        if (node == null) {
            System.out.println();
            return;
        }
        System.out.print(node.value + " ");
        printAllSuccHelper(node.succ);
    }

    public void inorderTraversal(Node root) {
        if (root == null) {
            return;
        }
        inorderTraversal(root.left);
        System.out.print(root.value + " ");
        inorderTraversal(root.right);


    }
}

