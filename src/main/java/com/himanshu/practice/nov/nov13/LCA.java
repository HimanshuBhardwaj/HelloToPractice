package com.himanshu.practice.nov.nov13;

import lombok.ToString;

import java.util.LinkedList;

/**
 * Created by himanshubhardwaj on 13/11/18.
 * 4:40
 */
public class LCA {
    public static void main(String[] args) {
        Node root = new Node(5);
        root = root.insert(root, 7);
        root = root.insert(root, 9);
        root = root.insert(root, 6);
        root = root.insert(root, 8);

        root = root.insert(root, 3);
        root = root.insert(root, 1);
        root = root.insert(root, 4);
        root = root.insert(root, 2);

        root = root.insert(root, 10);
        root = root.insert(root, 11);
        root = root.insert(root, 12);
        root = root.insert(root, 13);


        System.out.println(root.find(root, 1));


        //root.lca(root, new Node(112), root.find(root, 8));
        root.lca(root, root.find(root, 6), root.find(root, 13));


    }
}


@ToString
class Node {
    int value;
    @ToString.Exclude
    Node left;
    @ToString.Exclude
    Node right;

    public Node(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    public Node insert(Node root, int value) {
        if (root == null) {
            return new Node(value);
        }

        if (root.value < value) {
            root.right = insert(root.right, value);
        } else {
            root.left = insert(root.left, value);
        }

        return root;
    }

    public Node find(Node root, int value) {
        if (root == null) {
            return null;
        }

        if (root.value == value) {
            return root;
        } else if (root.value > value) {
            return find(root.left, value);
        } else {
            return find(root.right, value);
        }
    }


    public Node lca(Node root, Node left, Node right) {
        LinkedList<Node> pathToLeft = path(root, left, new LinkedList<Node>());
        LinkedList<Node> pathToRight = path(root, right, new LinkedList<Node>());
        System.out.println(left + "\t\t" + pathToLeft);
        System.out.println(right + "\t\t" + pathToRight);

        return null;
    }

    private LinkedList<Node> path(Node root, Node desNode, LinkedList<Node> list) {
        if (root == null) {
            return null;
        }


        if (root == desNode) {
            return list;
        }

        list.addLast(root);

        LinkedList<Node> left = path(root.left, desNode, list);
        LinkedList<Node> right = path(root.right, desNode, list);

        if (left == null && right == null) {
            list.removeLast();
            return null;
        }
        if (left == null) {
            return right;
        } else {
            return left;
        }
    }
}
