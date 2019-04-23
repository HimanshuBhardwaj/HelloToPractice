package com.himanshu.practice.y2019.April.april19;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by himanshubhardwaj on 18/04/19.
 * 11:30 -- 12:00 {both recursive and iterative}
 */
public class TreeTraversals {
    public static void main(String[] args) {
        Node tree = new Node(10);
        tree = tree.insert(tree, 11);
        tree = tree.insert(tree, 9);
        tree = tree.insert(tree, 12);
        tree = tree.insert(tree, 13);
        tree = tree.insert(tree, 7);
        tree = tree.insert(tree, 7);
        tree = tree.insert(tree, 8);
        ArrayList<ArrayList<Node>> list = new ArrayList<>();
        tree.levelOrderTraversalDFS(tree, 0, list);
        System.out.println(list);
        list = tree.levelOrderIterative(tree);
        System.out.println(list);


    }


}

class Node {
    int value;
    Node left = null;
    Node right = null;

    public Node(int v) {
        this.value = v;
    }

    public Node insert(Node root, int value) {
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

    public void inorder(Node root) {
        if (root == null) {
            return;
        }

        inorder(root.left);
        System.out.print(root.value + ", ");
        inorder(root.right);
    }

    public void levelOrderTraversalDFS(Node root, int level, ArrayList<ArrayList<Node>> lot) {
        if (root == null || lot == null) {
            return;
        }
        try {
            if (lot.get(level) == null) {

            }
        } catch (Exception e) {
            lot.add(level, new ArrayList<>());
        }


        lot.get(level).add(root);
        levelOrderTraversalDFS(root.left, level + 1, lot);
        levelOrderTraversalDFS(root.right, level + 1, lot);
    }

    public ArrayList<ArrayList<Node>> levelOrderIterative(Node root) {
        if (root == null) {
            return null;
        }

        Queue<Node> queue = new LinkedList<>();
        int level = 0;
        queue.add(root);
        ArrayList<ArrayList<Node>> levelNodes = new ArrayList<>();

        while (!queue.isEmpty()) {
            levelNodes.add(new ArrayList<Node>(queue));
            queue = new LinkedList<>();
            for (Node levelN : levelNodes.get(level)) {
                if (levelN.right != null) {
                    queue.add(levelN.right);
                }
                if (levelN.left != null) {
                    queue.add(levelN.left);
                }
            }
            level++;
        }


        return
        levelNodes;
    }

    public String toString() {
        return this.value + ", ";
    }
}
