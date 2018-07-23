package com.himanshu.practice.july.july4.hour4;

import java.util.LinkedList;

/**
 * Created by Himanshu Bhardwaj on 04/07/18.
 * 4:07
 * 4:45
 * could have improved by 13 mins
 */
public class PreOrderToBST {
    public static void main(String[] args) {
        Node root = new Node(10);
        root = root.insert(root, 12);
        root = root.insert(root, 11);
        root = root.insert(root, 13);
        root = root.insert(root, 8);
        root = root.insert(root, 8);
        root = root.insert(root, 9);
        root = root.insert(root, 6);
        root = root.insert(root, 7);
        root = root.insert(root, 5);

        LinkedList<Integer> inorder = new LinkedList<>();
        LinkedList<Integer> postOrder = new LinkedList<>();

        root.inorder(root, inorder);
        root.postOrder(root, postOrder);

        int[] inorderArray = new int[inorder.size()];
        int[] postOrderArray = new int[postOrder.size()];

        for (int i = 0; i < inorderArray.length; i++) {
            inorderArray[i] = inorder.get(i);
            postOrderArray[i] = postOrder.get(i);
        }

        System.out.println(inorder);
        System.out.println(postOrder);


        Node.postOrderEndPosition = inorderArray.length - 1;
        Node newRoot = null;
        newRoot = Node.constructTree(0, inorderArray.length - 1, inorderArray, postOrderArray);
        Node.postOrderEndPosition = -1;
        postOrder = new LinkedList<>();
        inorder = new LinkedList<>();

        newRoot.postOrder(newRoot, postOrder);
        newRoot.inorder(newRoot, inorder);
        System.out.println();

        System.out.println(inorder);
        System.out.println(postOrder);
        System.out.println(Node.areTwoBinaryTreeIdentical(root, newRoot));

        LinkedList<Integer> levelOrder[] = new LinkedList[Node.height(newRoot)];
        Node.levelOrder(newRoot, 0, levelOrder);

        for (int i = 0; i < levelOrder.length; i++) {
            System.out.print(i + ": ");
            for (int j = 0; j < levelOrder[i].size(); j++) {
                System.out.print(levelOrder[i].get(j) + ", ");
            }
            System.out.println();
        }

    }
}


class Node {
    int value;
    Node left = null;
    Node right = null;
    static int postOrderEndPosition;

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

    public void inorder(Node node, LinkedList<Integer> inorder) {
        if (node == null) {
            return;
        }

        inorder(node.left, inorder);
        inorder.addLast(node.value);
        inorder(node.right, inorder);
    }

    public void postOrder(Node node, LinkedList<Integer> postOrder) {
        if (node == null) {
            return;
        }
        postOrder(node.left, postOrder);
        postOrder(node.right, postOrder);
        postOrder.addLast(node.value);
    }


    public static Node constructTree(int inorderStart, int inorderEnd, int[] inorderArray, int[] postOrderArray) {

        if (inorderStart > inorderEnd || postOrderEndPosition < 0) {
            return null;
        }

        Node root = new Node(postOrderArray[postOrderEndPosition]);
        postOrderEndPosition--;
        int inOrderPosition = findPositionInInorderTraversal(inorderStart, inorderEnd, inorderArray, root.value);
        root.right = constructTree(inOrderPosition + 1, inorderEnd, inorderArray, postOrderArray);
        root.left = constructTree(inorderStart, inOrderPosition - 1, inorderArray, postOrderArray);
        return root;
    }

    private static int findPositionInInorderTraversal(int inorderStart, int inorderEnd, int[] inorderArray, int value) {
        for (int i = inorderStart; i <= inorderEnd; i++) {
            if (value == inorderArray[i]) {
                return i;
            }
        }
        return -1;
    }


    public static boolean areTwoBinaryTreeIdentical(Node root1, Node root2) {
        if (root1 == null && root2 == null) {
            return true;
        }

        if (root1 == null || root2 == null) {
            return false;
        }

        return (root1.value == root2.value) && areTwoBinaryTreeIdentical(root1.left, root2.left) && areTwoBinaryTreeIdentical(root1.right, root2.right);
    }

    //4:47
    public static void levelOrder(Node root, int level, LinkedList<Integer>[] levels) {
        if (root == null) {
            return;
        }
        if (levels[level] == null) {
            levels[level] = new LinkedList<>();
        }
        levels[level].addLast(root.value);
        levelOrder(root.left, level + 1, levels);
        levelOrder(root.right, level + 1, levels);
    }

    public static int height(Node newRoot) {
        if (newRoot == null) {
            return 0;
        }
        return 1 + Math.max(height(newRoot.left), height(newRoot.right));

    }
}
