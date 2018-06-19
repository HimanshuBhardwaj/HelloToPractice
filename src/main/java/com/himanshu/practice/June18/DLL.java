package com.himanshu.practice.June18;

/**
 * Created by himanshubhardwaj on 18/06/18.
 * 11:30
 */
public class DLL {
    public static void main(String[] args) {
        Node dll = new Node(5);
        dll = dll.insert(dll, 6);
        dll = dll.insert(dll, 7);
        dll = dll.insert(dll, 4);
        dll = dll.insert(dll, 1);
        dll = dll.insert(dll, 2);
        dll = dll.insert(dll, 99);
        dll = dll.insert(dll, -991);

        System.out.println("Printing");
        dll.print(dll);
        dll = dll.delete(dll, dll, dll.previous, -991);
        dll = dll.delete(dll, dll, dll.previous, 99);
        dll = dll.delete(dll, dll, dll.previous, 5);
        dll = dll.delete(dll, dll, dll.previous, 99999);
        dll = dll.delete(dll, dll, dll.previous, 2);
        dll = dll.delete(dll, dll, dll.previous, 1);
        dll = dll.delete(dll, dll, dll.previous, 6);
        dll = dll.delete(dll, dll, dll.previous, 7);


        System.out.println("Deleted");
        System.out.println("Head node\t" + dll.value);
        //System.out.println(dll.value);
        dll.print(dll);


    }
}


class Node {
    int value;
    Node next;
    Node previous;

    public Node(int value) {
        this.value = value;
        this.next = this;
        this.previous = this;
    }

    public String toString() {
        return Integer.toString(value);
    }


    //in sorted order
    public Node insert(Node node, int value) {
        if (node == null) {
            return new Node(value);
        }

        //only one node
        if (node.next == node.previous && node.next == node) {
            Node node1 = new Node(value);
            node1.next = node;
            node1.previous = node;
            node.next = node1;
            node.previous = node1;

            if (value < node.value) {
                return node1;
            } else {
                return node;
            }
        }

        //if it is the lowerst; here we have morethan one node in the list
        if (value < node.value) {
            Node node1 = new Node(value);
            node1.next = node;
            node1.previous = node.previous;
            node.previous.next = node1;
            node.previous = node1;
            return node1;
        }

        Node prev = node.previous;
        Node curr = node;

        while (curr.value < value) {
            curr = curr.next;
            prev = prev.next;
            if (curr == node) {
                break;
            }
        }

        Node node1 = new Node(value);
        node1.next = curr;
        node1.previous = previous;
        curr.previous = node1;
        prev.next = node1;

        return node;
    }


    void print(Node head) {
        Node temp = head;
        if (head == null) {
            return;
        }


        while (temp.next != head) {
            System.out.print(temp.value + " ");
            temp = temp.next;
        }
        System.out.println(temp.value + " ");
    }


    public Node delete(Node acualHead, Node headNode, Node Prev, int value) {
        if (headNode == null) {
            return null;
        }


        //when there is only one node
        if (headNode.next == headNode) {
            if (headNode.value == value) {
                return null;
            } else {
                return headNode;
            }

        }


        //this will wnd into loop
        //assume we have more than one node here
        if (headNode.value == value) {
            Prev.next = headNode.next;
            headNode.next.previous = Prev;
            return headNode.next;
        } else {
            if (headNode.next != acualHead) {
                delete(acualHead, headNode.next, headNode, value);
                return headNode;
            } else {
                return headNode;
            }

        }


    }
}
