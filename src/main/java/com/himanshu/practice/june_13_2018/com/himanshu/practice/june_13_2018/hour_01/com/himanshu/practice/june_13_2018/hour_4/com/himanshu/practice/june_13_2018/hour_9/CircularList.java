package com.himanshu.practice.june_13_2018.com.himanshu.practice.june_13_2018.hour_01.com.himanshu.practice.june_13_2018.hour_4.com.himanshu.practice.june_13_2018.hour_9;

import lombok.Data;
import lombok.Getter;

/**
 * Created by himanshubhardwaj on 13/06/18.
 */

//Add(keep sorting), remove, size, inorder,

public class CircularList {
    public static void main(String[] args) {
        CircularLinkList circularLinkList = new CircularLinkList();
        circularLinkList.insert(5);
        CircularLinkList.print(circularLinkList);
        System.out.println("size: " + CircularLinkList.size(circularLinkList));
        circularLinkList.insert(-5);
        circularLinkList.insert(15);
        circularLinkList.insert(12);
        circularLinkList.insert(14);
        circularLinkList.insert(-14);
        System.out.println();
        System.out.println();
        CircularLinkList.print(circularLinkList);
        System.out.println("size: " + CircularLinkList.size(circularLinkList));

        System.out.println("Removing wala time coming");
        CircularLinkList.print(CircularLinkList.remove(circularLinkList, 5));
        System.out.println("size: " + CircularLinkList.size(circularLinkList));

        ;


    }
}


@Getter
class CircularLinkList {
    Node head;

    //we have to keep it sorted in inceasing order
    Node insert(int value) {
        Node newNode = new Node(value);

        if (head == null) {
            head = new Node(value);
            head.next = head;
            return head;
        }

        //only one node
        if (head.getNext() == head) {
            newNode.setNext(head);
            head.setNext(newNode);
            if (head.getValue() > newNode.getValue()) {
                head = newNode;
            }
            return head;
        }

        //value is minimum than temp itself
        if (value < head.getValue()) {
            Node lastNode = CircularLinkList.getLastNode(head);
            lastNode.next = newNode;
            newNode.next = head;
            head = newNode;
            return head;
        }


        Node pTemp = head;
        Node temp = head.next;

        while (temp.getValue() < value && temp != head) {
            temp = temp.next;
            pTemp = pTemp.next;
            //TODO: Complete it
        }

        pTemp.next = newNode;
        newNode.next = temp;
        return head;

    }

    private static Node getLastNode(Node head) {
        if (head == null || head.next == head) {
            return head;
        }
        Node temp = head.next;

        while (temp.next != head) {
            temp = temp.next;
        }


        return temp;


    }


    //Assumes that CircularLinkList is not null
    static void print(CircularLinkList list) {
        Node temp = list.head;

        if (temp == null) {
            System.out.println("null list; nothing to inorder");

        }

        while (temp.next != list.head) {
            System.out.print(temp.getValue() + "-->");
            temp = temp.next;
        }
        System.out.println(temp.getValue());

    }

    static int size(CircularLinkList circularLinkList) {
        if (circularLinkList == null || circularLinkList.head == null) {
            return 0;
        }
        int size = 1;
        Node temp = circularLinkList.head;
        while (temp.next != circularLinkList.head) {
            size++;
            temp = temp.next;
        }
        return size;
    }

    //assume we are intended to remove only one value
    static CircularLinkList remove(CircularLinkList circularLinkList, int value) {
        if (circularLinkList == null || circularLinkList.head == null) {
            return null;
        }

        Node head = circularLinkList.head;
        Node tail = CircularLinkList.getLastNode(circularLinkList.head);

        //there is onne element only
        if (head == tail) {
            if (head.getValue() == tail.getValue()) {
                circularLinkList.head = null;
                return null;
            }
        }

        //We have to remove first element

        if (head.getValue() == value) {
            tail.next = head.next;
            circularLinkList.head = head.next;
            return circularLinkList;
        }

        Node pTemp = head;
        Node temp = head.next;
        int valuePresent = 1;

        while (temp.value != value) {
            temp = temp.next;
            pTemp = pTemp.next;
            if (temp == circularLinkList.head) {
                valuePresent = 0;
                break;
            }
        }

        if (valuePresent == 1) {
            pTemp.next = temp.next;
        }
        return circularLinkList;
    }


}


@Data
@lombok.Setter
class Node {
    final int value;
    Node next;

    public Node(int value) {
        this.value = value;
        this.next = null;
    }

}