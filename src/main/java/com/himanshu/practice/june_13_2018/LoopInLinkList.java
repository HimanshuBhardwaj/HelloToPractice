package com.himanshu.practice.june_13_2018;
//
///**
// * Created by himanshubhardwaj on 13/06/18.
// */
//public class LoopInLinkList {
//    public static void main(String[] args) throws InterruptedException {
//        com.himanshu.practice.june_13_2018.com.himanshu.practice.june_13_2018.hour_01.Node head = new com.himanshu.practice.june_13_2018.com.himanshu.practice.june_13_2018.hour_01.Node(5);
//
//        head = head.insert(head, 152);
//        head = head.insert(head, 53);
//        head = head.insert(head, 54);
//        com.himanshu.practice.june_13_2018.com.himanshu.practice.june_13_2018.hour_01.Node temp = head;
//        head = head.insert(head, 252);
//        head = head.insert(head, 533);
//
//
//
//        head.print(head,1);
//
//        com.himanshu.practice.june_13_2018.com.himanshu.practice.june_13_2018.hour_01.Node lastNode = head.lastNode(head);
//
//        System.out.println();
//        System.out.println(head.isCyclePresent(head));
//
//        lastNode.next = temp;
//        System.out.println(head.isCyclePresent(head));
//        System.out.println("temp" + temp.value);
//
//        head.print(head, 1000);
//
//
//
//
//
//
//
//
//    }
//}
//
//
//class Node {
//    int value;
//    com.himanshu.practice.june_13_2018.com.himanshu.practice.june_13_2018.hour_01.Node next;
//
//    public Node(int value) {
//        this.value = value;
//    }
//
//    public com.himanshu.practice.june_13_2018.com.himanshu.practice.june_13_2018.hour_01.Node lastNode(com.himanshu.practice.june_13_2018.com.himanshu.practice.june_13_2018.hour_01.Node node) {
//        if(node == null || node.next == null) {
//            return node;
//        }
//        return lastNode(node.next);
//    }
//
//    com.himanshu.practice.june_13_2018.com.himanshu.practice.june_13_2018.hour_01.Node insert(com.himanshu.practice.june_13_2018.com.himanshu.practice.june_13_2018.hour_01.Node node, int value) {
//        com.himanshu.practice.june_13_2018.com.himanshu.practice.june_13_2018.hour_01.Node node1 = new com.himanshu.practice.june_13_2018.com.himanshu.practice.june_13_2018.hour_01.Node(value);
//        node1.next = node;
//        return node1;
//    }
//
//    void print(com.himanshu.practice.june_13_2018.com.himanshu.practice.june_13_2018.hour_01.Node node, int time) throws InterruptedException {
//        if (node == null) {
//            return;
//        }
//        Thread.sleep(time);
//        System.out.print(node.value + "-->");
//        print(node.next, time);
//    }
//
//    boolean isCyclePresent(com.himanshu.practice.june_13_2018.com.himanshu.practice.june_13_2018.hour_01.Node node) {
//        if (node == null || node.next == null)
//            return false;
//
//        com.himanshu.practice.june_13_2018.com.himanshu.practice.june_13_2018.hour_01.Node tortoise = node;
//        com.himanshu.practice.june_13_2018.com.himanshu.practice.june_13_2018.hour_01.Node hair = node;
//
//
//
//        while (hair != null && hair.next != null) {
//            tortoise = tortoise.next;
//            hair = hair.next.next;
//            if (hair == tortoise) {
//                System.out.println("Hair: "+hair.value+"\tTortoise: "+tortoise.value);
//                return true;
//            }
//        }
//        return false;
//    }
//
//
//}