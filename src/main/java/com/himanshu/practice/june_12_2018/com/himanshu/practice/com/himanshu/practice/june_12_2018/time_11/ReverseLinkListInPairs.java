package com.himanshu.practice.june_12_2018.com.himanshu.practice.com.himanshu.practice.june_12_2018.time_11;

import java.util.Scanner;

/**
 * Created by himanshubhardwaj on 12/06/18.
 */
public class ReverseLinkListInPairs {
    public static void main(String[] args) {
        List list = new List();
        list = List.insert(list, 5);
        list = List.insert(list, 135);
        list = List.insert(list, 25);
        list = List.insert(list, 35);
        list = List.insert(list, 54);
//
        list = List.insert(list, 15);
        list = List.insert(list, 1135);
        list = List.insert(list, 125);
        list = List.insert(list, 135);
        list = List.insert(list, 154);

        list = List.insert(list, 15);
        list = List.insert(list, 1135);
        list = List.insert(list, 125);
        list = List.insert(list, 135);

        List.print(list);

        System.out.println("Enter the pair in which you want to reverse");
        Scanner scan = new Scanner(System.in);
        int number = scan.nextInt();

        list = List.reverseInPair(list, number);
        List.print(list);

    }

}


class List {
    Node head = null;

    static List insert(List list, int value) {
        if (list == null) {
            list = new List();
        }
        list.head = Node.insert(list.head, value);
        return list;
    }

    static void print(List list) {
        Node.print(list.head);
    }

    public static List reverseInPair(List list, int number) {
        if (list == null) {
            return null;
        }
        List list1 = new List();
        list1.head = Node.reverseInPair(list.head, number);

        return list1;
    }
}


class Node {
    int value;
    Node next;


    public Node(int value) {
        this.value = value;
        next = null;
    }

    static Node insert(Node lNode, int value) {
        Node lNode1 = new Node(value);
        lNode1.next = lNode;
        return lNode1;
    }


    static void print(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.value + " ");
        print(node.next);
    }

    static Node reverseInPair(Node node, int number) {


        Node end = null;
        Node pHead = null;
        Node nHead = null;
        Node nEnd;
        Node pEnd = null;
        Node  globalHead = null;

        while (node != null) {

            Node start = node;
            for (int i = 0; i < number && (node != null); i++) {
                if (node != null) {
                    end = node;
                }
                node = node.next;
            }
            end.next = null;
            nHead = reverse(start, end);

            nEnd = getEnd(nHead); // it assumes that nEnd wont be null


            if(pEnd != null) {
                pEnd.next = nHead;
            } else {
                globalHead = nHead;
            }


            pEnd = nEnd;
            pHead = nHead;


        }
        System.out.println("returning pHead Value" + pHead.value);
        return globalHead;

        //now we have start and end;


    }

    static Node getEnd(Node node) {
        if (node == null || node.next == null) {
            return node;
        }
        return getEnd(node.next);
    }

    //Assumption: end.next == null
    //it will return head of new list and end will be null
    //and no cycle
    static Node reverse(Node start, Node end) {

        if (start == null || start == end) {
            return start;
        }

        Node previous = null;
        Node current = start;
        Node next = start.next;


        while (current != null) {
            current.next = previous;
            previous = current;
            current = next;
            if (next != null)
                next = next.next;
            else
                return previous;
        }
        return null;
    }


}