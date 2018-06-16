package com.himanshu.practice.June13.hour4;

import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by himanshubhardwaj on 15/06/18.
 * 3:30 am
 * not implemented
 * //TODO: Complete it
 */
public class ConstructingTreeInorderAndPreOrder {
    public static void main(String[] args) {
        Node root = new Node(5);
        root = root.insert(root, 7);
        root = root.insert(root, 6);
        root = root.insert(root, 3);
        root = root.insert(root, 4);
        root = root.insert(root, 9);
        root = root.insert(root, 8);
        root = root.insert(root, 1);
        root = root.insert(root, 2);
        LinkedList<Integer> inorder = new LinkedList<>();

        LinkedList<Integer> preorder = new LinkedList<>();
        root.inorder(root, inorder);
        root.preorder(root, preorder);
        System.out.println();
        System.out.println("PreOrder:\t" + preorder.toString());
        System.out.println("InOrder:\t" + inorder.toString());
        Integer inorderArray[] = new Integer[inorder.size()];
        Integer preorderArray[] = new Integer[preorder.size()];


        for (int i = 0; i < inorder.size(); i++) {
            inorderArray[i] = inorder.get(i);
            preorderArray[i] = preorder.get(i);
        }

        System.out.print("Inorder:\t");
        for (int i : inorderArray) {
            System.out.print(i + " ");
        }
        System.out.println();

        System.out.print("Preorder:\t");
        for (int i : preorderArray) {
            System.out.print(i + " ");
        }
        System.out.println();


        System.out.println("Constructing tree");
        Node root2 = root.treConstruction(0, inorder.size() - 1, inorderArray, 0, preorder.size() - 1, preorderArray);
        System.out.println("Constructed");
        System.out.println();
        root2.inorder(root2, null);
        System.out.println();
        root2.preorder(root2, null);
    }
}


class Node {
    int value;
    Node right;
    Node left;

    public Node(int value) {
        this.value = value;
        this.right = null;
        this.left = null;
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


    public List<Integer> inorder(Node root, List<Integer> inorder) {

        if (root == null) {
            return inorder;
        }
        if (inorder == null) {
            inorder = new LinkedList<>();
        }

        inorder(root.left, inorder);
        inorder.add(inorder.size(), root.value);
        System.out.printf(" %d", root.value);
        inorder(root.right, inorder);
        return inorder;
    }


    public List<Integer> preorder(Node root, List<Integer> preorder) {
        if (root == null) {
            return preorder;
        }
        if (preorder == null) {
            preorder = new LinkedList<>();
        }

        System.out.printf(" %d", root.value);
        preorder.add(preorder.size(), root.value);
        preorder(root.left, preorder);
        preorder(root.right, preorder);
        return preorder;
    }

    public Node treConstruction(int inorderStart, int inorderEnd, Integer[] inorder, int preOrderStart, int preorderEnd, Integer[] preOrder) {
        if (inorderStart > inorderEnd || preOrderStart > preorderEnd || inorderStart < 0 || preOrderStart < 0 || preorderEnd >= preOrder.length || inorderEnd >= inorder.length) {
            return null;
        }
        if (inorderStart == inorderEnd) {
            if (preOrderStart == preorderEnd) {
                return new Node(inorder[inorderEnd]);
            } else {
                System.out.println("Kuch gadbad hai daya");
                return null;
            }
        }

        int rootV = preOrder[preOrderStart];
        int posInInorder = -1;


        preOrderStart++;

        for (int i = inorderStart; i <= inorderEnd; i++) {
            if (inorder[i].equals(rootV)) {
                posInInorder = i;
                break;
            }
        }
        System.out.println("Inorder[posInInorder]: " + inorder[posInInorder] + "\tposInInorder: " + posInInorder);
        if (posInInorder == -1) {
            System.out.println("inorder me kuch gabad hai");
            return null;
        }

        Node root = new Node(rootV);
        root.left = treConstruction(inorderStart, posInInorder - 1, inorder, preOrderStart, preOrderStart + posInInorder - 1, preOrder);
        root.right = treConstruction(posInInorder + 1, inorderEnd, inorder, posInInorder + 1, preorderEnd, preOrder); //unerstand it
        return root;

    }
}
