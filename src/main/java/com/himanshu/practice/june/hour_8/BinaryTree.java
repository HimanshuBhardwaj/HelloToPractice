package com.himanshu.practice.june.hour_8;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Himanshu Bhardwaj on 08/06/18.
 */
public class BinaryTree {
    public static void main(String[] args) {
        Node root = new Node(5);
        root.left = new Node(9);
        root.left.left = new Node(11);
        root.left.right = new Node(111);
        root.right = new Node(33);
        root.right.left = new Node(44);


        root.right.left.left = new Node(244);
        root.right.left.left.right = new Node(449);
        root.right.left.right = new Node(44);


        Node.print(root);
        System.out.println();

        System.out.println("Height:\t" + Node.height(root));
        Info info = Node.diameter(root);
        System.out.println("Height: "+ info.getHeight());
        System.out.println("Diameter: "+ info.getDiameter());
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


    static void print(Node node) {
        if (node == null) {
            return;
        }
        print(node.left);
        System.out.println(node.value + " ");
        print(node.right);
    }

    static int height(Node node) {
        if (node == null) {
            return 0;
        }
        return (1 + Math.max(height(node.right), height(node.left)));
    }

    static Info diameter(Node node) {
        if (node == null) {
            return new Info(0, 0);
        }
        if (node.left == null && node.right == null) {
            return new Info(1, 1);
        }
        Info left = diameter(node.left);
        Info right = diameter(node.right);

        int rootHeight = 1 + Math.max(left.getHeight(), right.getHeight());
        int roorDiameter = Math.max(Math.max(left.getDiameter(), right.getDiameter()), 1 + left.getHeight() + right.getHeight());
        Info root = new Info(roorDiameter, rootHeight);
        return root;
    }
}


@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
class Info {
    private int diameter;
    private int height;
}