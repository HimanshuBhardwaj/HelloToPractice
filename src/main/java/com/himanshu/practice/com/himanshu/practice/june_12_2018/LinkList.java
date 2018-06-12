package com.himanshu.practice.com.himanshu.practice.june_12_2018;


class Node {
    int value;
    Node next;

    public Node(int value) {
        this.value = value;
        next = null;
    }
}

class List {
    private Node head;
    private int size;

    List() {
        head = null;
        size = 0;
    }

    List add(List list, int value) {
        if (list == null) {
            list = new List();
        }

        Node node = new Node(value);
        node.next = list.head;
        list.head = node;
        this.size++;
        return list;
    }

    void print(List list) {
        Node node = list.head;
        System.out.print("Size:" + this.size + " Value: ");

        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
    }


    //Delete all instances of this calue from the list
    List delete(List list, int value) {
        if (list == null) {
            return null;
        }

        list.head = deleteNode(list.head, value);
        return list;
        //sizing update could be seen later
    }

    //delete only one occurance
    private Node deleteNode(Node node, int value) {
        if (node == null) {
            return null;
        }

        if (node.value == value) {
            this.size--;
            return node.next;
        }

        Node node1 = node;
        Node node2 = deleteNode(node.next, value);
        node1.next = node2;
        return node1;
    }

}


class LinkList {
    public static void main(String[] args) {
        List list = new List();
        list.add(list, 5);
        list.add(list, 15);
        list.add(list, 12);
        list.add(list, 35);
        list.add(list, 45);
        list.print(list);

        System.out.println();

        list = list.delete(list, 12);
        list.print(list);

        System.out.println();

        list = list.delete(list, 32);
        list.print(list);


        System.out.println();
        list.add(list, 450);
        list.print(list);

        System.out.println();

        list = list.delete(list, 35);
        list.print(list);





    }
}



