package com.himanshu.practice.june.june21.hour1;

import lombok.Getter;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Himanshu Bhardwaj on 21/06/18.
 * TODO: Complete it
 */

public class TreeConstruction {
    public static void main(String[] args) {
        Node node = new Node(9);
        node = node.insert(node, 10);
        node = node.insert(node, 8);
        node = node.insert(node, 11);
        node = node.insert(node, 7);
        node = node.insert(node, 8);
        node = node.insert(node, 6);
        node = node.insert(node, 5);

        System.out.print("Inorder: ");
        node.inorder(node);
        System.out.println();
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        LinkedList<Integer> levelOrder = new LinkedList<Integer>();
        System.out.print("Level Order: ");
        node.levelOrder(queue, levelOrder);
        System.out.println();

        LinkedList<Integer> postOrder = new LinkedList<Integer>();
        System.out.print("PostORder: ");
        node.postOrder(node, postOrder);
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();


        System.out.println("Printing lists");
        System.out.print("LevelORder: " + levelOrder);
        System.out.println();
        System.out.print("PostORder: " + postOrder);
    }
}


@Getter
class Node {
    int value;
    Node left;
    Node right;

    //public boolean equals


    public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        }
        if (anObject instanceof Node) {
            Node anotherNode = (Node) anObject;
            if ((anotherNode.value == this.value) && (this.getRight() == ((Node) anObject).getRight()) && (this.getLeft() == ((Node) anObject).getLeft())) {
                return true;
            }
        }
        return false;
    }


    public Node(int value) {
        this.value = value;
        left = null;
        right = null;
    }

    public Node insert(Node node, int value) {
        if (node == null) {
            return new Node(value);
        }

        if (node.value > value) {
            node.left = insert(node.left, value);
        } else {
            node.right = insert(node.right, value);
        }
        return node;
    }


    public void postOrder(Node root, LinkedList<Integer> postOrder) {
        if (root == null) {
            return;
        }
        postOrder(root.left, postOrder);
        postOrder(root.right, postOrder);
        System.out.print(root.value + " ");
        postOrder.addLast(root.value);
        return;
    }


    public void levelOrder(Queue<Node> queue, LinkedList<Integer> levelOrder) {
        if (queue.isEmpty()) {
            return;
        }
        Node root = queue.poll();
        levelOrder.addLast(root.value);
        System.out.print(root.value + " ");
        if (root.left != null) {
            queue.add(root.left);
        }
        if (root.right != null) {
            queue.add(root.right);
        }
        levelOrder(queue,levelOrder);
    }

    public void inorder(Node tree) {
        if (tree == null) {
            return;
        }
        inorder(tree.left);
        System.out.print(tree.value + " ");
        inorder(tree.right);
    }
}
