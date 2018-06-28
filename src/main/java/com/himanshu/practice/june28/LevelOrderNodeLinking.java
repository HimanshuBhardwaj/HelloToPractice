package com.himanshu.practice.june28;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by himanshubhardwaj on 28/06/18.
 * 3:15 pm
 * 4:00 pm
 * could have improved
 */
public class LevelOrderNodeLinking {
    public static void main(String[] args) {
        Node root = new Node(10);
        root = root.insert(root, 11);
        root = root.insert(root, 9);
        root = root.insert(root, 13);
        root = root.insert(root, 13);
        root = root.insert(root, 12);
        root = root.insert(root, 8);
        root = root.insert(root, 8);
        root = root.insert(root, 6);
        root = root.insert(root, 6);
        root = root.insert(root, 5);
        root.inorder(root);
        System.out.println();

        System.out.println(root.height(root));
        Node ar[] = root.linkNodes(root);

        for (int i = 0; i < ar.length; i++) {
            System.out.print(i + ": ");
            Node node = ar[i];

            while (node != null) {
                System.out.print(node.value + " ");
                node = node.level;
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
        System.out.print(root.value + " ");
        inorder(root.right);
    }

    public Node[] linkNodes(Node node) {
        Node levels[] = new Node[node.height(node)];
        Queue<Node> queue = new LinkedList<>();
        LinkedList<Node> levelLinkedNodes = new LinkedList<>();
        queue.add(node);
        queue.add(new Node(-1));


        linkNodesHelper(queue, levels, levelLinkedNodes, 0);

        return levels;
    }

    private void linkNodesHelper(Queue<Node> queue, Node[] levels, LinkedList<Node> levelLinkedNodes, int level) {
        if (queue == null || queue.isEmpty()) {
            if (levelLinkedNodes.isEmpty()) {
                return;
            } else if (levelLinkedNodes.size() == 1) {
                levels[level] = levelLinkedNodes.poll();
            } else {
                Node current = levelLinkedNodes.poll();
                levels[level] = current;
                Node second;

                while (levelLinkedNodes.size() > 0) {
                    second = levelLinkedNodes.poll();
                    current.level = second;
                    current = second;
                }
            }
            return;
        }
        Node top = queue.poll();
        if (top.value == -1) {
            linkNodesHelper(null, levels, levelLinkedNodes, level);
            if (queue.isEmpty()) {
                return;
            } else {
                queue.add(top);
                if (levelLinkedNodes.size() != 0) {
                    System.out.println("Something wrong" + levelLinkedNodes.size() + "\treturning...");
                }
                linkNodesHelper(queue, levels, levelLinkedNodes, level + 1);
            }
        } else {
            if (top.left != null) {
                queue.add(top.left);
            }
            if (top.right != null) {
                queue.add(top.right);
            }
            levelLinkedNodes.addLast(top);
            linkNodesHelper(queue, levels, levelLinkedNodes, level);
        }
    }

    public int height(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(height(node.left), height(node.right));
    }


}