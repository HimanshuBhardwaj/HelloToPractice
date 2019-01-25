package com.himanshu.practice.y2018.june.June18.hour1;

import java.util.*;

/**
 * Created by Himanshu Bhardwaj on 18/06/18.
 */
public class BST {
    public static void main(String[] args) {
        Node root = new Node(10);
        root = root.insert(root, 13);
        root = root.insert(root, 15);
        root = root.insert(root, 17);
        root = root.insert(root, 14);
        root = root.insert(root, 16);
        root = root.insert(root, 8);
        root = root.insert(root, 9);
        root = root.insert(root, 6);
        root = root.insert(root, 7);
        LinkedList<Integer> inorder = new LinkedList<Integer>();
        LinkedList<Integer> levelOrder = new LinkedList<Integer>();
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);
        queue.add(new Node(-1));
        inorder = root.inorder(root, inorder);
        System.out.println();
        levelOrder = root.levelOrder(queue, levelOrder);
        System.out.println();

        int[] inorderArray = new int[inorder.size()];
        for (int i = 0; i < inorderArray.length; i++) {
            inorderArray[i] = inorder.get(i);
        }


        int[] levelOrderArray = new int[levelOrder.size()];
        for (int i = 0; i < levelOrder.size(); i++) {
            levelOrderArray[i] = levelOrder.get(i);
        }


        System.out.println("Lever ORder: " + levelOrder);
        System.out.println("Inorder: " + inorder);


        Node root2 = Node.inorderLevelOrderToTree(inorderArray, 0, inorderArray.length - 1, levelOrderArray);
        System.out.println("new inorder\t" + root2.inorder(root2, new LinkedList<Integer>()));
        Queue<Node> nQueue = new LinkedList<>();
        nQueue.add(root2);
        nQueue.add(new Node(-1));


        System.out.println("new levelorder\t" + root2.levelOrder(nQueue, new LinkedList<Integer>()));


    }
}


class Node {
    int value;
    Node left;
    Node right;


    public Node(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
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


    public LinkedList<Integer> inorder(Node node, LinkedList<Integer> inorder) {
        if (node == null) {
            return null;
        }
        if (inorder == null) {
            inorder = new LinkedList<Integer>();
        }

        inorder(node.left, inorder);
        inorder.addLast(node.value);
        inorder(node.right, inorder);
        return inorder;
    }

    //assume node -1 from starting
    public LinkedList<Integer> levelOrder(Queue<Node> queue, LinkedList<Integer> levelOrder) {
        if (queue.size() == 0) {
            System.out.println("Traversed\tlevelSize: " + levelOrder.size());
            return levelOrder;
        }
        if (levelOrder == null) {
            levelOrder = new LinkedList<Integer>();
        }

        Node node = queue.poll();
        if (node.value == -1) {
            System.out.print("new level reached\t");
            System.out.println("levelSize: " + levelOrder.size());
            if (!queue.isEmpty()) {
                queue.add(node);
            }

            return levelOrder(queue, levelOrder);
        } else {
//            remember it is tree, not graph
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }


            levelOrder.addLast(node.value);
            return levelOrder(queue, levelOrder);
        }
    }

    public static Node inorderLevelOrderToTree(int[] inorder, int start, int end, int[] levelOrder) {
        if (start > end || end >= inorder.length || start < 0) {
            return null;
        }
        Node root = new Node(levelOrder[0]);
        int indexOfRootInInorder = getPosOfElement(inorder, levelOrder[0]);
        int[] newLevelOrderLeft = getinorderIntersectionwithLevelOrder(inorder, start, indexOfRootInInorder - 1, levelOrder);
        int[] newLevelOrderRight = getinorderIntersectionwithLevelOrder(inorder, indexOfRootInInorder + 1, end, levelOrder);

        root.left = inorderLevelOrderToTree(inorder, start, indexOfRootInInorder - 1, newLevelOrderLeft);
        root.right = inorderLevelOrderToTree(inorder, indexOfRootInInorder + 1, end, newLevelOrderRight);

        return root;
    }

    public static int[] getinorderIntersectionwithLevelOrder(int[] inorder, int start, int end, int[] levelOrder) {
        Set<Integer> hashset = new HashSet<>();
        for (int i = start; i <= end; i++) {
            hashset.add(inorder[i]);
        }
        int[] newLevelOrder = new int[end - start + 1];
        int pos = 0;

        for (int i : levelOrder) {
            if (hashset.contains(i)) {
                newLevelOrder[pos++] = i;
            }
        }
        return newLevelOrder;
    }

    public static int getPosOfElement(int[] arr, int element) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == element) {
                return i;
            }
        }
        //this should never happen
        return -1;
    }

}
