package com.himanshu.practice.june_9.hour_3;

import com.sun.glass.events.mac.NpapiEvent;
import lombok.*;
import sun.awt.TracedEventQueue;
import sun.awt.image.ImageWatched;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
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
        root = Node.insert(root, -31);
        root = Node.insert(root, -28);

        Node root2 = new Node(6);
        root2 = Node.insert(root2, 16);
        root2 = Node.insert(root2, 11);
        root2 = Node.insert(root2, 26);
        root2 = Node.insert(root2, 27);
        root2 = Node.insert(root2, 28);


        Node.print(root);
        System.out.println();

        System.out.println(Node.inorder(root, null));
        System.out.println(Node.preOrder(root, null));
        Node newRoot = Node.constructTree(null, Node.inorder(root, null), Node.preOrder(root, null));
        System.out.print("Constructing tree: ");
        System.out.println(Node.inorder(newRoot, null));
        Node.width(root);
        Node.print(root);
        Queue<Node> rooot = new LinkedList<Node>();
        rooot.add(root);

        System.out.println("Level order:");
        Node.levelOrderTraversal(rooot);
        System.out.println("Printing nodes at distance 2 from root");
        Node.printNodeLevelwise(root, 3);

        System.out.println("Path from root to node");
        Node.pathFromRoot(root, new LinkedList<Node>(), 11);


        System.out.println("Checking identicality");
        System.out.println(Node.areBinaryTreesIdentical(root, root));


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

        System.out.println("Checking for subtree: " + Node.isSubtree(root, root2));
    }

}


@Data
class Node {
    int value;
    Node left;
    Node right;
    int widthSlot;
    int level;

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
        System.out.print("(" + node.getValue() + ", " + node.getWidthSlot() + ")\t");
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
    //6:30 -->7:20


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


    //left, right width will not be null
    //widthSlot is the width slot of node
    public static void width(Node node) {
        if (node == null) {
            return;
        }

        if (node.left == null && node.right == null) {
            return;
        }

        if (node.left != null) {
            node.left.setWidthSlot(node.getWidthSlot() - 1);
            width(node.left);
        }

        if (node.right != null) {
            node.right.setWidthSlot(node.getWidthSlot() + 1);
            width(node.right);
        }
    }

    public static void levelOrderTraversal(Queue<Node> levelOrder) {
        if (levelOrder.isEmpty()) {
            return;
        }

        Node node = levelOrder.poll();
        int nodeLevel = node.getLevel();
        System.out.println("<" + node.value + ", " + nodeLevel + ">");

        if (node.left != null) {
            node.left.setLevel(nodeLevel + 1);
            levelOrder.add(node.left);
        }

        if (node.right != null) {
            node.right.setLevel(nodeLevel + 1);
            levelOrder.add(node.right);
        }
        levelOrderTraversal(levelOrder);
    }

    public static void printNodeLevelwise(Node node, int distance) {
        if (node == null) {
            return;
        }

        if (distance == 0) {
            System.out.println(node.getValue());
            return;
        } else {
            printNodeLevelwise(node.left, distance - 1);
            printNodeLevelwise(node.right, distance - 1);

        }

    }

    public static void pathFromRoot(Node node, LinkedList<Node> path, int requiredNode) {
        if (node == null) {
            return;
        }
        if (node.value == requiredNode) {
            for (Node node1 : path) {
                System.out.print(node1.getValue() + "-->");
            }
            System.out.println(requiredNode);
        }
        path.add(node);
        if (node.left != null) {
            pathFromRoot(node.left, path, requiredNode);
        }
        if (node.right != null) {
            pathFromRoot(node.right, path, requiredNode);
        }
        path.remove(node);
    }

    public static boolean areBinaryTreesIdentical(Node node1, Node node2) {
        if (node1 == null && node2 == null) {
            return true;
        }
        if ((node1 == null && node2 != null) || (node2 == null && node1 != null) || (node1.value != node2.value)) {
            return false;
        }
        return areBinaryTreesIdentical(node1.right, node2.right) && areBinaryTreesIdentical(node1.left, node2.left);
    }

    public static boolean isSubtree(Node root1, Node root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        if ((root1 == null && root2 != null) || (root2 == null && root1 != null)) {
            return false;
        }

        return areBinaryTreesIdentical(root1, root2) || isSubtree(root1.getLeft(), root2) || isSubtree(root1.getRight(), root2);


    }
}

@AllArgsConstructor
@Getter
@Setter
class Width {
    int maxLeft = 0;
    int maxRight = 0;
    int widthSlot = 0;

}
