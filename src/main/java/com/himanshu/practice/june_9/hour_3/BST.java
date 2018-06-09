package com.himanshu.practice.june_9.hour_3;

import com.sun.glass.events.mac.NpapiEvent;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Stack;

/**
 * Created by himanshubhardwaj on 09/06/18.
 */
public class BST {
    public static void main(String[] args) {
        Node root = new Node(5);
        root = Node.insert(root, 6);
        root = Node.insert(root, 16);
        root = Node.insert(root, 26);
        root = Node.insert(root, 11);
        root = Node.insert(root, 2);
        root = Node.insert(root, 27);
        root = Node.insert(root, 28);
        root = Node.insert(root, -29);

        Node.print(root);
        System.out.println();

        root = Node.delete(root, 5);
        System.out.println("root.value: " + root.getValue());
        root = Node.delete(root, 6);
        System.out.println("root.value: " + root.getValue());


        Node.inorderWithourRecursion(root);
        root = Node.delete(root, 11);
        System.out.println();
        Node.inorderWithourRecursion(root);

    }

}


@Data
class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    public static Node insert(Node node, int value) {

        if (node == null) {
            return new Node(value);
        }

        if (node.value > value) {
            node.left = insert(node.left, value);
        } else {
            node.right = insert(node.right, value);
        }

        return node;
    }

    public static Node delete(Node node, int value) {
        if (node == null) {
            return null;
        }

        if (node.value == value) {
            if (node.left == null && node.right == null) {
                return null;
            }
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }
            Node succ = getSuccessor(node);
            swap(succ, node);

            node.right = delete(node.right, value);

        } else if (node.value <= value) {
            node.right = delete(node.right, value);
        } else {
            node.left = delete(node.left, value);
        }


        return node;
    }

    private static void swap(Node succ, Node node) {
        int temp = succ.getValue();
        succ.setValue(node.getValue());
        node.setValue(temp);
    }

    //this assumes that both child are not null.
    //node is also not null
    public static Node getSuccessor(Node node) {
        Node temp = node;
        temp = temp.right;

        while (temp.left != null) {
            temp = temp.left;
        }
        return temp;
    }

    public static void print(Node node) {
        if (node == null) {
            return;
        }
        print(node.left);
        System.out.print(node.getValue() + " ");
        print(node.right);
    }


    public static void inorderWithourRecursion(Node node) {
        if (node == null) {
            return;
        }
        Stack<Node> frameStack = new Stack<>();

        while (node != null) {
            frameStack.push(node);
            node = node.left;
        }

        Node current = null;


        while (current == null && !frameStack.empty()) {
            current = frameStack.pop();
            System.out.print(current.getValue() + " ");
            current = current.right;
            while (current != null) {
                frameStack.push(current);
                current = current.left;
            }
        }
    }


}
