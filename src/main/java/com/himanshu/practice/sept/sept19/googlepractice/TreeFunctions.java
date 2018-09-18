package com.himanshu.practice.sept.sept19.googlepractice;

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

        System.out.println(Tree.height(root));
        Tree.inorderTraversal(root);
        System.out.println();
        System.out.println(Tree.getSuccesor(root).value);
        root = Tree.delete(root,10);
        root = Tree.delete(root,11);
        root = Tree.delete(root,13);
        root = Tree.delete(root,13);
        root = Tree.delete(root,8);
        root = Tree.delete(root,7);
        root = Tree.delete(root,13);
        root = Tree.delete(root,9);
        Tree.inorderTraversal(root);
        System.out.println();
        System.out.println((root!=null)?root.value:root);


    }
}


//3:20 -- 3:33 : Finished in 13 mins

//ETA: 25 mins+7 mins to test
class Tree {
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
}
