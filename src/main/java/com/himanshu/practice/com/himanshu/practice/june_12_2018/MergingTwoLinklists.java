package com.himanshu.practice.com.himanshu.practice.june_12_2018;

import com.sun.tools.javah.LLNI;

/**
 * Created by himanshubhardwaj on 12/06/18.
 */
public class MergingTwoLinklists {

    public static void main(String[] args) {
        LList list1 = null;
        LList list2 = null;
        LList list3 = null;


        list1 = LList.insert(list1, 3);
        list1 = LList.insert(list1, 11);
        list1 = LList.insert(list1, 33);
        list1 = LList.insert(list1, 33);
        list1 = LList.insert(list1, 43);

        list2 = LList.insert(list2, 4);
        list2 = LList.insert(list2, 14);
        list2 = LList.insert(list2, 24);
        list2 = LList.insert(list2, 34);

        LList.print(list1);
        LList.print(list2);
        list3 = LList.merge(list1, list2);
        System.out.println();
        LList.print(list3);



    }

}


class LList {
    NNode head;

    static public void print(LList lList) {
        if (lList == null) {
            System.out.println("Empty List...returning");
        }
        System.out.println("Printing list");
        NNode.print(lList.head);
        System.out.println();
    }

    static public LList insert(LList lList, int value) {
        if (lList == null) {
            lList = new LList();
        }
        NNode node = new NNode(value);
        node.next = lList.head;
        lList.head = node;
        return lList;
    }

    //Sorted in decreasing
    public static LList merge(LList list1, LList list2) {
        LList list3 = new LList();
        NNode current1, current2, current3;
        current1 = list1.head;
        current2 = list2.head;
        current3 = list3.head;


        while (current1 != null && current2 != null) {
            //both are not null here
            if (current1.value > current2.value) {
                if (current3 == null) {
                    list3.head = current1;
                    current3 = current1;
                } else {
                    current3.next = current1;
                    current3 = current3.next;
                }
                current1 = current1.next;
            } else {
                if (current3 == null) {
                    list3.head = current2;
                    current3 = current2;
                } else {
                    current3.next = current2;
                    current3 = current3.next;
                }
                current2 = current2.next;
            }
        }
        if (current2 == null) {
            if (current3 == null) {
                list3.head = current1;
            } else {
                current3.next = current1;
            }
        } else if (current1 == null) {
            if (current3 == null) {
                list3.head = current2;
            } else {
                current3.next = current2;
            }
        }
        //Both can not become null simultaniously

        return list3;
    }
}


class NNode {
    int value;
    NNode next;

    public NNode(int value) {
        this.value = value;
        next = null;
    }

    static NNode add(NNode node, int value) {
        NNode nNode = new NNode(value);
        if (node == null) {
            node = nNode;
        }
        nNode.next = node;
        node = nNode;
        return node;
    }

    static void print(NNode nNode) {
        if (nNode == null) {
            return;
        }
        System.out.print(nNode.value + "-->");
        print(nNode.next);
    }
}