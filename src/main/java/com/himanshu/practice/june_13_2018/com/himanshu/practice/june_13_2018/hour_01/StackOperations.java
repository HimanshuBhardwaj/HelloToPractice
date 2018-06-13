package com.himanshu.practice.june_13_2018.com.himanshu.practice.june_13_2018.hour_01;

/**
 * Created by himanshubhardwaj on 13/06/18.
 */


//push, pop, size
//we will implement it using linkedlist

public class StackOperations {
    public static void main(String[] args) {
        Stack stack = new Stack();
        Queue queue = new Queue();

        stack.push(3);
        stack.push(5);
        stack.push(1);
        stack.push(33);
        stack.push(444);
        stack.push(133);
        stack.push(1444);

        stack.print();


        System.out.println();
        System.out.println(stack.pop().value);


        System.out.println();
        System.out.println(stack.pop().value);
        stack.print();
        System.out.println("stack size: \t"+stack.size());


        System.out.println("Queue ka time aya");
        System.out.println();
        System.out.println();

        queue.push(5533);
        queue.push(233);
        queue.push(333);
        queue.push(3333);
        queue.push(33);
        queue.push(233);

        queue.print();

        System.out.println(queue.pull().value);
        queue.push(2393);
        queue.print();

        System.out.println(queue.pull().value);
        queue.print();
        System.out.println("queue size:\t"+queue.size());



    }
}


class Node {
    int value;
    Node next;

    public Node(int value) {
        this.value = value;
        next = null;
    }

}


class Stack {
    Node head = null;

    Node push(int value) {
        Node node1 = new Node(value);
        node1.next = head;
        head = node1;
        return node1;
    }

    Node pop() {
        if (head == null) {
            return null;
        }
        Node node = head;
        head = head.next;
        return node;
    }

    int size() {
        if (head == null) {
            return 0;
        }
        int count = 0;
        Node temp = head;

        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }


    void print() {
        if(head == null) {
            return;
        }
        Node temp = head;
        while (temp != null) {
            System.out.print( temp.value + " <-- ");
            temp = temp.next;
        }
        System.out.println();
    }
}


class Queue {
    Node head;
    Node rear;


    //return node corresponding to the value inserted
    Node push(int value) {
        Node node = new Node(value);
        if (head == null && rear == null) {
            head = node;
            rear = node;
        } else {
            rear.next = node;
            rear = node;
        }
        return node;
    }


    Node pull() {
        if (head == null) {
            return null;
        }
        Node node = head;
        head = head.next;

        if (head == null) {
            rear = null;
        }
        return node;
    }

    int size() {
        if (head == null) {
            return 0;
        }
        int size = 0;
        Node temp = head;

        while (temp != null) {
            size++;
            temp = temp.next;
        }
        return size;
    }

    void print() {
        if(head == null) {
            return;
        }
        Node temp = head;
        while (temp != null) {
            System.out.print( temp.value + " <-- ");
            temp = temp.next;
        }
        System.out.println();
    }

}
