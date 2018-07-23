package com.himanshu.practice.june.June25.hour5;


/**
 * Created by Himanshu Bhardwaj on 25/06/18.
 */
public class LLinkedList {
    public static void main(String[] args) {
        int size = 10;
        Node head = new Node(5);
        for (int i = 1; i < size; i++) {
            head = head.insert(head, (i * i + 31 + i) % 51);
        }
        head.print(head);
        System.out.println();
        Node newHead = head.reverseInPairs(head, 3);
        newHead.print(newHead);
    }
}


class Node {
    int value;
    Node next;

    public Node(int value) {
        this.value = value;
        this.next = null;
    }


    public Node insert(Node head, int value) {
        if (head == null) {
            return new Node(value);
        }
        head.next = insert(head.next, value);
        return head;
    }

    public void print(Node head) {
        if (head == null) {
            System.out.println();
            return;
        }
        System.out.print(head.value + " ");
        print(head.next);
    }

    public Node reverseInPairs(Node head, int k) {
        Node curr = head;
        Node prevHead = null;
        Node newHead = null;
        Node end = null;


        while (curr != null) {
            System.out.println("curr.value: " + curr.value);
            Node start = curr;
            prevHead = newHead;

            for (int i = 0; i < k; i++) {
                end = curr;
                curr = curr.next;
                if (curr == null) {
                    break;
                }
            }
            end.next = null;//break linkedlist
            newHead = reverseLL(start, end);

            //validate it
            newHead = merge(prevHead, getTail(prevHead), newHead);
        }
        return newHead;
    }

    private Node getTail(Node newHead) {
        if (newHead == null) {
            return null;
        }
        while (newHead.next != null) {
            newHead = newHead.next;
        }
        return newHead;
    }

    //start->...end->null
    private Node reverseLL(Node start, Node end) {
        if (start == null || start == end) {
            return start;
        }
        //atleast two nodes.

        Node prev = null;
        Node curr = start;
        Node next = start.next;

        while (next != null) {
            curr.next = prev;
            prev = curr;
            curr = next;
            next = next.next;
            if (next == null) {
                curr.next = prev;
                return curr;
            }
        }
        return null;
    }

    private Node merge(Node prevHead, Node prevTail, Node newHead) {
        if (prevTail == null) {
            return newHead;
        }
        prevTail.next = newHead;
        return prevHead;
    }
}
