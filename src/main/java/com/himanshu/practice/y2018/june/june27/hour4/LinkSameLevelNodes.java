package com.himanshu.practice.y2018.june.june27.hour4;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Himanshu Bhardwaj on 27/06/18.
 */
public class LinkSameLevelNodes {
    public static void main(String[] args) {
        Node node = new Node(10);
        node = node.insert(node, 12);
        node = node.insert(node, 11);
        node = node.insert(node, 13);
        node = node.insert(node, 8);
        node = node.insert(node, 9);
        node = node.insert(node, 10);
        node = node.insert(node, 6);
        node = node.insert(node, 5);
        node = node.insert(node, 7);
        node = node.insert(node, 15);
        node = node.insert(node, 14);
        node = node.insert(node, 16);
        node = node.insert(node, 13);
        node = node.insert(node, 4);
        node.inorder(node);
        System.out.println();

        Node[] levelNodes = node.linklevelNodes(node);

        System.out.println(levelNodes.length);
        System.out.println();


        for (int i = 0; i < levelNodes.length; i++) {
            System.out.print(i + ": ");
            Node tleve = levelNodes[i];
            while (tleve != null) {
                System.out.print(tleve.value + "\t");
                tleve = tleve.level;
            }
            System.out.println();
        }

    }
}


class Node {
    int value;
    Node left;
    Node right;
    Node level;

    public Node(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
        this.level = null;
    }

    public String toString() {
        return "(" + value + ")";
    }


    public Node insert(Node root, int value) {
        if (root == null) {
            return new Node(value);
        }
        if (value < root.value) {
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
        System.out.print(root.value + " ");
        inorder(root.right);
    }

    //Assume that -1 will not be there
    public Node[] linklevelNodes(Node node) {
        if (node == null) {
            return null;
        }

        int height = node.height(node);
        Node levels[] = new Node[height];
        Queue<Node> queue = new LinkedList<>();
        LinkedList<Node> levelNodes = new LinkedList<>();
        queue.add(node);
        queue.add(new Node(-1));
        levelOrderTraversalHelper(queue, levelNodes, levels, 0);
        return levels;
    }

    private void levelOrderTraversalHelper(Queue<Node> queue, LinkedList<Node> levelNode, Node levelNodes[], int currentLevel) {
        if (queue.isEmpty()) {
            if (levelNode.isEmpty()) {
                return;
            }
            levelNodes[currentLevel] = levelNode.getFirst();
            if (levelNode.size() == 1) {
                levelNode.removeAll(levelNode);
                return;
            }
            Node current = levelNode.getFirst();
            Node next = levelNode.get(1);
            int pos = 2;

            while (current != null) {
                current.level = next;
                current = next;
                next = levelNode.get(pos++);
            }
            levelNode.removeAll(levelNode);
            return;
        }
        Node node = queue.poll();
        if (node.value == -1) {
            if (levelNode.size() >= 1) {
                levelNodes[currentLevel] = levelNode.getFirst();
                if (levelNode.size() > 1) {
                    Node current = levelNode.getFirst();
                    Node next = levelNode.get(1);
                    int pos = 2;

                    while (current != null) {
                        current.level = next;
                        current = next;
                        next = (levelNode.size() > pos) ? levelNode.get(pos++) : null;
                    }
                }
            }
            if (!queue.isEmpty()) {
                queue.add(node);
                levelNode.removeAll(levelNode);
                levelOrderTraversalHelper(queue, levelNode, levelNodes, currentLevel + 1);
            }
        } else {
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
            levelNode.addLast(node);
            levelOrderTraversalHelper(queue, levelNode, levelNodes, currentLevel);
        }

    }


    public int height(Node root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(height(root.left), height(root.right));
    }

}
