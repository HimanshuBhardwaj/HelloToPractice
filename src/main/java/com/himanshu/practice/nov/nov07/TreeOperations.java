package com.himanshu.practice.nov.nov07;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by himanshubhardwaj on 07/11/18.
 * Linking nodes of same level: 8:55--9:11
 * Diameter: 10:35-10:40
 */
/*
* Linking nodes of same level. diameter of tree
* */
public class TreeOperations {
    public static void main(String[] args) {
        TNode root = TNode.insert(null, 5);
        root = TNode.insert(root, 6);
        root = TNode.insert(root, 7);
        root = TNode.insert(root, 8);
        root = TNode.insert(root, 5);
        root = TNode.insert(root, 4);
        root = TNode.insert(root, 9);
        root = TNode.insert(root, 1);
        root = TNode.insert(root, 3);
        root = TNode.insert(root, 4);

        TNode.inorder(root);
        TNode.computeLevelInfo(root);

        System.out.println();
        for (TNode firstLEvelNode : TNode.firstNode) {
            TNode temp = firstLEvelNode;

            while (temp != null) {
                System.out.print(temp.value + "--");
                temp = temp.level;
            }
            System.out.println();
        }
        TNode.diameter(root);
        System.out.println(TNode.diameter);


    }


}


class TNode {
    int value;
    TNode left, right, level;
    static ArrayList<TNode> firstNode = new ArrayList<>();
    static int diameter = 0;

    public TNode(int value) {
        this.value = value;
        left = null;
        right = null;
        level = null;
    }


    static public TNode insert(TNode root, int value) {
        if (root == null) {
            return new TNode(value);
        }

        if (root.value > value) {
            root.left = insert(root.left, value);
            return root;
        } else {
            root.right = insert(root.right, value);
            return root;
        }
    }


    static public void inorder(TNode root) {
        if (root == null) {
            return;
        }

        inorder(root.left);
        System.out.print(" " + root.value);
        inorder(root.right);
    }


    static public void computeLevelInfo(TNode root) {
        if (root == null) {
            return;
        }

        Queue<TNode> levelQueue = new LinkedList<>();
        levelQueue.add(root);


        while (!levelQueue.isEmpty()) {
            ArrayList<TNode> levelNodes = new ArrayList<>(levelQueue);
            levelQueue = new LinkedList<>();

            for (int i = 0; i < levelNodes.size(); i++) {
                if (i == 0) {
                    firstNode.add(levelNodes.get(i));
                }

                if (levelNodes.get(i).left != null) {
                    levelQueue.add(levelNodes.get(i).left);
                }
                if (levelNodes.get(i).right != null) {
                    levelQueue.add(levelNodes.get(i).right);
                }
            }

            for (int i = 0; (i + 1) < levelNodes.size(); i++) {
                levelNodes.get(i).level = levelNodes.get(i + 1);
            }
        }
    }

    static int diameter(TNode root) {
        if (root == null) {
            return 0;
        }

        int heightR = diameter(root.right);
        int heightL = diameter(root.left);
        diameter = Math.max(diameter, heightL + heightR);

        return Math.max(heightR, heightL) + 1;
    }

}
