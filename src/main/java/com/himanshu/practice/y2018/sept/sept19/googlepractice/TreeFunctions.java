package com.himanshu.practice.y2018.sept.sept19.googlepractice;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by himanshubhardwaj on 19/09/18.
 */

public class TreeFunctions {
    public static void main(String[] args) {
        Node root = new Node(10);
        root = Tree.insert(root, 13);
        root = Tree.insert(root, 11);
        root = Tree.insert(root, 13);
        root = Tree.insert(root, 8);
        root = Tree.insert(root, 9);
        root = Tree.insert(root, 7);
        root = Tree.insert(root, 6);
        root = Tree.insert(root, 5);
        root = Tree.insert(root, 2);

        Tree.printBoundary(root);


    }
}


//3:20 -- 3:33 : Finished in 13 mins
//ETA: 25 mins+7 mins to test
class Tree {
    static Queue<Node> queue = new LinkedList<Node>();

    static Node insert(Node root, int value) {
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

    static int height(Node root) {
        if (root == null) {
            return 0;
        } else {
            return 1 + Math.max(height(root.left), height(root.right));
        }
    }

    static void inorderTraversal(Node root) {
        if (root == null) {
            return;
        }
        inorderTraversal(root.left);
        System.out.print(root.value + ", ");
        inorderTraversal(root.right);
    }


    static Node delete(Node root, int value) {
        if (root == null) {
            return root;
        }

        if (root.value > value) {
            root.left = delete(root.left, value);
        } else if (root.value < value) {
            root.right = delete(root.right, value);
        } else {
            if (root.left == null && root.right == null) {
                return null;
            } else if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            } else {
                Node succ = getSuccesor(root);
                root.value = succ.value;
                succ.value = value;
                root.right = delete(root.right, value);
            }
        }
        return root;
    }

    //this root will always have atlease one left children
    static Node getSuccesor(Node root) {
        root = root.right;

        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    static void printBoundary(Node root) {
        if (root == null) {
            return;
        }


        Node marker = new Node(-1, null, null);
        queue.add(root);
        queue.add(marker);
        LinkedList<Node> front = new LinkedList<Node>();
        LinkedList<Node> leaf = new LinkedList<Node>();
        LinkedList<Node> back = new LinkedList<Node>();
        int round = 0;

        while (!queue.isEmpty()) {
            round++;
            ArrayList<Node> levelNodes = getLevelNodes(queue);

            for (int i = 0; i < levelNodes.size(); i++) {
                Node currentNode = levelNodes.get(i);
                if (i == 0) {
                    front.add(currentNode);
                }else if (isLeaf(currentNode)) {
                    leaf.addLast(currentNode);
                } else if (i == (levelNodes.size() - 2)) {
                    back.add(currentNode);
                } else if (i == (levelNodes.size() - 1)) {
                    System.out.println("Marker: " + queue + "\t" + marker);
                    if (queue.size() > 0) {
                        queue.add(currentNode);
                    }
                }

                if (currentNode.left != null) {
                    queue.add(currentNode.left);
                }

                if (currentNode.right != null) {
                    queue.add(currentNode.right);
                }

            }
        }
        System.out.println(front + "\t" + leaf + "\t" + back);


    }

    private static ArrayList<Node> getLevelNodes(Queue<Node> queue) {
        System.out.println(queue);
        ArrayList<Node> list = new ArrayList<>();

        while (queue.peek().value != -1) {
            list.add(queue.poll());
        }
        list.add(queue.poll());
        return list;
    }

    static private boolean isLeaf(Node currentNode) {
        if (currentNode == null) {
            return true;
        }
        if (currentNode.left == null && currentNode.right == null && currentNode.value != -1) {
            return true;
        }

        return false;
    }
}

@AllArgsConstructor
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
}
