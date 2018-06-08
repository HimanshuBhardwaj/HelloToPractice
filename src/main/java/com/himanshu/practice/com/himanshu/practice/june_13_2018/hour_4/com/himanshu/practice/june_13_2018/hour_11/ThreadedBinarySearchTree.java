package com.himanshu.practice.com.himanshu.practice.june_13_2018.hour_4.com.himanshu.practice.june_13_2018.hour_11;

/**
 * Created by himanshubhardwaj on 13/06/18.
 * 11:35 PM
 */

//TODO: Complete it
public class ThreadedBinarySearchTree {
    public static void main(String[] args) {
//        DoublyCircularLinkedList doublyCircularLinkedList = new DoublyCircularLinkedList();
//        DoublyCircularLinkedList.insertValue(doublyCircularLinkedList, 5);
//        DoublyCircularLinkedList.insertValue(doublyCircularLinkedList, 51);
//        DoublyCircularLinkedList.insertValue(doublyCircularLinkedList, -511);
//        DoublyCircularLinkedList.insertValue(doublyCircularLinkedList, -512);
//        DoublyCircularLinkedList.insertValue(doublyCircularLinkedList, 512);
//        DoublyCircularLinkedList.printDoublyCircularLinkedList(doublyCircularLinkedList);


        BST bst = new BST();
        bst = bst.insert(bst, 3);
        bst = bst.insert(bst, -13);
        bst = bst.insert(bst, 23);
        bst = bst.insert(bst, -33);
        bst = bst.insert(bst, 43);
        bst = bst.insert(bst, 44);
        bst = bst.insert(bst, 45);
        bst = bst.insert(bst, 46);

        bst.inorderPrint(bst);


    }
}


class BST {
    Node root;

    BST insert(BST bst, int value) {
        if (bst == null) {
            bst = new BST();
        }

        Node root = bst.root;

        if (root == null) {
            Node valueNode = new Node(value);
            bst.root = valueNode;
            return bst;
        }
        if (root.value > value) {
            BST tempBST = new BST();
            tempBST.root = bst.root.left;
            bst.root.left = insert(tempBST, value).root;
        } else {
            BST tempBST = new BST();
            tempBST.root = bst.root.right;
            bst.root.right = insert(tempBST, value).root;
        }
        return bst;
    }

    void inorderPrint(BST bst) {
        if (bst == null || bst.root == null) {
            return;
        }
        int value = bst.root.value;
        BST tempBST = new BST();
        tempBST.root = bst.root.left;
        inorderPrint(tempBST);
        System.out.println(" " + value + " ");
        tempBST.root = bst.root.right;
        inorderPrint(tempBST);
    }

    DoublyCircularLinkedList bstToDoublyCircularLinkedlist(BST bst) {
        if (bst == null || bst.root == null) {
            return new DoublyCircularLinkedList();
        }
        return null;


    }
}


class DoublyCircularLinkedList {
    Node head;

    //there will be only two extreme case, either value is greater or equal to all double linked list values or it will be less than all values
    //head will always be the minimum vale of linked list
    static DoublyCircularLinkedList insertValue(DoublyCircularLinkedList doublyCircularLinkedList, int value) {
        if (doublyCircularLinkedList == null) {
            doublyCircularLinkedList = new DoublyCircularLinkedList();
        }

        Node node = new Node(value);
        Node head = doublyCircularLinkedList.head;

        //head is null
        if (head == null) {
            node.right = node;
            node.left = node;
            doublyCircularLinkedList.head = node;
            return doublyCircularLinkedList;
        }

        //when there is only one node
        if (head.left == head.right && head.left == head) {
            if (head.value < node.value) {
                head.right = node;
                node.left = head;
                node.right = head;
                head.left = node;
                doublyCircularLinkedList.head = head;
                return doublyCircularLinkedList;
            }
        }

        //Now, from here we have more than two nodes in the doubly circular linked list

        node.right = head;
        node.left = head.left;
        head.left.right = node;
        head.left = node;

        if (head.value >= value) {
            head = head.left;
        }
        doublyCircularLinkedList.head = head;
        return doublyCircularLinkedList;
    }

    //All value of doublyCircularLinkedList1 <= doublyCircularLinkedList2
    //head will have the minimum value
    static DoublyCircularLinkedList insertValue(DoublyCircularLinkedList doublyCircularLinkedList1, DoublyCircularLinkedList doublyCircularLinkedList2) {
        if(doublyCircularLinkedList1 == null || doublyCircularLinkedList1.head == null) {
            return doublyCircularLinkedList2;
        }
        if(doublyCircularLinkedList2 == null || doublyCircularLinkedList2.head == null) {
            return doublyCircularLinkedList1;
        }
        //now both are not null;
        return null;



    }

    static void printDoublyCircularLinkedList(DoublyCircularLinkedList doublyCircularLinkedList) {
        if (doublyCircularLinkedList == null || doublyCircularLinkedList.head == null) {
            System.out.println("Empty doublyCircularLinkedList");
        }
        Node temp = doublyCircularLinkedList.head;

        while (temp.right != doublyCircularLinkedList.head) {
            System.out.print(temp.value + "-->");
            temp = temp.right;
        }
        System.out.println(temp.value);
    }

    DoublyCircularLinkedList nodeToDoublyCircularLinkedList(Node node) {
        DoublyCircularLinkedList doublyCircularLinkedList = new DoublyCircularLinkedList();
        if (node == null) {
            return doublyCircularLinkedList;
        }
        node.right = node;
        node.left = node;
        doublyCircularLinkedList.head = node;
        return doublyCircularLinkedList;
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


    static Node insert(Node head, int value) {
        return null;
    }
}
