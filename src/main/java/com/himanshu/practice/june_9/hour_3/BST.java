package com.himanshu.practice.june_9.hour_3;

import com.sun.glass.events.mac.NpapiEvent;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sun.awt.image.ImageWatched;

import java.util.LinkedList;
import java.util.List;
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

        System.out.println(Node.inorder(root, null));
        System.out.println(Node.preOrder(root, null));
        Node newRoot = Node.constructTree(null, Node.inorder(root, null), Node.preOrder(root, null));
        System.out.print("Constructing tree: ");
        System.out.println(Node.inorder(newRoot, null));


        // Deletion wala code
//        root = Node.delete(root, 5);
//        System.out.println("root.value: " + root.getValue());
//        root = Node.delete(root, 6);
//        System.out.println("root.value: " + root.getValue());
//
//
//        Node.inorderWithourRecursion(root);
//        root = Node.delete(root, 11);
//        System.out.println();
//        Node.inorderWithourRecursion(root);


//        LinkedList<Integer> linkedList = new LinkedList<Integer>();
//        linkedList.add(5);
//        System.out.println(linkedList.subList(0, 0));
//        System.out.println(linkedList.subList(1, 0));

//        linkedList.add(25);
//        linkedList.add(53);
//        linkedList.add(51);
//        linkedList.add(27);
//        System.out.println(linkedList.toString());
//        System.out.println(linkedList.subList(1, 3 + 1));
//        System.out.println(linkedList.indexOf(51));
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

    public static LinkedList<Integer> inorder(Node node, LinkedList<Integer> inorderT) {
        if (node == null) {
            return inorderT;
        }

        if (inorderT == null) {
            inorderT = new LinkedList<Integer>();
        }


        inorder(node.left, inorderT);
        inorderT.add(node.getValue());
        inorder(node.right, inorderT);
        return inorderT;
    }

    public static LinkedList<Integer> preOrder(Node node, LinkedList<Integer> preOrderT) {
        if (node == null) {
            return preOrderT;
        }
        if (preOrderT == null) {
            preOrderT = new LinkedList<>();
        }
        preOrderT.add(node.getValue());
        preOrder(node.left, preOrderT);
        preOrder(node.right, preOrderT);
        return preOrderT;
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


    //Inorder and pre order se tree construct karo
    //6 00 PM --> 6:10 PM
    //6:30 -->

    //Assume no repetation
    public static Node constructTree(Node node, List<Integer> inorder, List<Integer> preorder) {
        if (inorder.size() == 0 || preorder.size() == 0) {
            return null;
        }

        int value = preorder.get(0);
        preorder.remove(0);
        int index = inorder.indexOf(value);

        Node node1 = new Node(value);
        //preorder.remove(0);


        if (inorder.size() >= 0) {
            node1.left = constructTree(node1, inorder.subList(0, index), preorder);

        } else {
            node1.left = null;
        }


        if ((index + 1) < inorder.size()) {
            node1.right = constructTree(node1, inorder.subList(index + 1, inorder.size()), preorder);
        } else {
            node1.right = null;
        }

        return node1;
    }

}
