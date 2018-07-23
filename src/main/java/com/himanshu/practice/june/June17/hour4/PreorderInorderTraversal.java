package com.himanshu.practice.june.June17.hour4;

import java.util.LinkedList;

/**
 * Created by Himanshu Bhardwaj on 17/06/18.
 * 3.36
 * Tree insertion: 18 mins
 * treeConstruction: 20 mins
 */
public class PreorderInorderTraversal {
    public static void main(String[] args) {
        Node root = new Node(7);
        root = root.insert(root, 9);
        root = root.insert(root, 11);
        root = root.insert(root, 13);
        root = root.insert(root, 8);
        root = root.insert(root, 10);
        root = root.insert(root, 12);
        root = root.insert(root, 5);
        root = root.insert(root, 6);

        LinkedList<Integer> inorder = new LinkedList<Integer>();
        LinkedList<Integer> preorder = new LinkedList<Integer>();

        System.out.println("inorder:");
        root.inorder(root, inorder);
        System.out.println();
        System.out.println();
        System.out.println("Preorder:");

        root.preorder(root, preorder);
        System.out.println();

        System.out.println("Inotder:\t" + inorder);
        System.out.println("Inotder:\t" + preorder);
        System.out.println("\n\nConstructing tree");
        Node root1 = Node.constructTree(inorder, preorder);
        System.out.println();
        System.out.println();
        System.out.println("Inorder");
        root1.inorder(root1, new LinkedList<Integer>());
        System.out.println();
        System.out.println("PreOrder");
        root1.preorder(root1, new LinkedList<Integer>());
    }
}


class Node {
    int value;
    Node left;
    Node right;
    static int preorderIndex = 0;

    public Node(int value) {
        this.value = value;
        left = null;
        right = null;
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


    public void inorder(Node root, LinkedList<Integer> traversal) {
        if (root == null) {
            return;
        }
        inorder(root.left, traversal);
        System.out.print(root.value + " ");
        traversal.addLast(root.value);
        inorder(root.right, traversal);
    }

    public void preorder(Node root, LinkedList<Integer> traversal) {
        if (root == null) {
            return;
        }
        traversal.addLast(root.value);
        System.out.print(root.value + " ");
        preorder(root.left, traversal);
        preorder(root.right, traversal);
    }


    public static Node constructTree(LinkedList<Integer> inorder, LinkedList<Integer> preorder) {
        preorderIndex = 0;
        int[] inorderArr = new int[inorder.size()];
        int[] preorderArr = new int[preorder.size()];


        for (int i = 0; i < inorderArr.length; i++) {
            inorderArr[i] = inorder.get(i);
            preorderArr[i] = preorder.get(i);
        }


        Node rootC = constructTreeHelper(0, inorderArr.length - 1, inorderArr, preorderArr);


        preorderIndex = 0;
        return rootC;
    }

    //assume norepetatin
    private static Node constructTreeHelper(int inorderStart, int inorderEnd, int[] inorderArr, int[] preorderArr) {
        if (inorderStart > inorderEnd || inorderEnd > preorderArr.length) {
            return null;
        }

        Node node = new Node(preorderArr[preorderIndex++]);
        int posrRootInInorder = -1;
        for (int i = inorderStart; i <= inorderEnd; i++) {
            if (inorderArr[i] == node.value) {
                posrRootInInorder = i;
                break;
            }
        }

        if (posrRootInInorder == -1) {
            System.out.println("Something wrong has happened");
            return null;
        }
        node.left = constructTreeHelper(inorderStart, posrRootInInorder - 1, inorderArr, preorderArr);
        node.right = constructTreeHelper(posrRootInInorder + 1, inorderEnd, inorderArr, preorderArr);

        return node;
    }


}
