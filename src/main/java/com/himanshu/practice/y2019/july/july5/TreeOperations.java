package com.himanshu.practice.y2019.july.july5;

import com.himanshu.practice.y2018.nov.nov5.Tree;
import lombok.ToString;

/**
 * Created by himanshubhardwaj on 05/07/19.
 */
public class TreeOperations {
    public static void main(String[] args) {
        Node root = new Node(10);
        root.insert(root, 8);
        root.insert(root, 9);
        root.insert(root, 7);
        root.insert(root, 12);
        root.insert(root, 14);
        root.insert(root, 16);
        System.out.println(root.height(root));
        System.out.println(root.size(root));
        root.inorderTraversal(root);
        System.out.println();
//        root.preOrderTraversal(root);
//        System.out.println();
//        root.postOrderTraversal(root);
//        System.out.println();

        System.out.println();
        System.out.println("deleting 10");
        root = root.delete(root, 10);
        System.out.println("New root" + root);
        root.inorderTraversal(root );
        System.out.println();
        System.out.println(root.height(root));
        System.out.println(root.size(root));

    }
}


@ToString
class Node {
    int value;
    @ToString.Exclude
    Node left;
    @ToString.Exclude
    Node right;


    @java.beans.ConstructorProperties({"value", "left", "right"})
    public Node(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
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


    public int height(Node root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(height(root.left), height(root.right));
    }

    public int size(Node root) {
        if (root == null) {
            return 0;
        }
        return 1 + size(root.right) + size(root.left);
    }

    public void inorderTraversal(Node root) {
        if (root == null) {
            return;
        }

        inorderTraversal(root.left);
        System.out.print(root.value + ",");
        inorderTraversal(root.right);
    }

    public void preOrderTraversal(Node root) {
        if (root == null) {
            return;
        }
        System.out.println(root.left + "," + root.value + "," + root.right);
        preOrderTraversal(root.left);
        preOrderTraversal(root.right);

    }

    public void postOrderTraversal(Node root) {
        if (root == null) {
            return;
        }

        postOrderTraversal(root.left);
        postOrderTraversal(root.right);
        System.out.print(root.value + ",");
    }

    public Node delete(Node root, int value) {
        if (root == null) {
            return null;
        }

        if (root.value < value) {
            root.right = delete(root.right, value);
        } else if (root.value > value) {
            root.left = delete(root.left, value);
        } else {
            //equal
            if (root.left == null && root.right == null) {
                return null;
            } else if (root.right == null) {
                return root.left;
            } else if (root.left == null) {
                return root.right;
            } else {
                Node succ = getSuccessor(root);
                root.value = succ.value;
                root.right = delete(root.right, root.value);
            }
        }
        return root;
    }

    private Node getSuccessor(Node root) {
        root = root.right;

        while (root.left != null) {
            root = root.left;
        }
        return root;
    }


}
