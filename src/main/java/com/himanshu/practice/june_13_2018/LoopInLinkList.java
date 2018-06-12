package com.himanshu.practice.june_13_2018;

/**
 * Created by himanshubhardwaj on 13/06/18.
 */
public class LoopInLinkList {
    public static void main(String[] args) throws InterruptedException {
        Node head = new Node(5);

        head = head.insert(head, 152);
        head = head.insert(head, 53);
        head = head.insert(head, 54);
        Node temp = head;
        head = head.insert(head, 252);
        head = head.insert(head, 533);



        head.print(head,1);

        Node lastNode = head.lastNode(head);

        System.out.println();
        System.out.println(head.isCyclePresent(head));

        lastNode.next = temp;
        System.out.println(head.isCyclePresent(head));
        System.out.println("temp" + temp.value);

        head.print(head, 1000);








    }
}


class Node {
    int value;
    Node next;

    public Node(int value) {
        this.value = value;
    }

    public Node lastNode(Node node) {
        if(node == null || node.next == null) {
            return node;
        }
        return lastNode(node.next);
    }

    Node insert(Node node, int value) {
        Node node1 = new Node(value);
        node1.next = node;
        return node1;
    }

    void print(Node node, int time) throws InterruptedException {
        if (node == null) {
            return;
        }
        Thread.sleep(time);
        System.out.print(node.value + "-->");
        print(node.next, time);
    }

    boolean isCyclePresent(Node node) {
        if (node == null || node.next == null)
            return false;

        Node tortoise = node;
        Node hair = node;



        while (hair != null && hair.next != null) {
            tortoise = tortoise.next;
            hair = hair.next.next;
            if (hair == tortoise) {
                System.out.println("Hair: "+hair.value+"\tTortoise: "+tortoise.value);
                return true;
            }
        }
        return false;
    }


}