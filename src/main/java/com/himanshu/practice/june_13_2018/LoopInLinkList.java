package com.himanshu.practice.june_13_2018;
//
///**
// * Created by himanshubhardwaj on 13/06/18.
// */
//public class LoopInLinkList {
//    public static void main(String[] args) throws InterruptedException {
//        june20.himanshu.practice.june_13_2018.june20.himanshu.practice.june_13_2018.hour_01.GraphOp head = new june20.himanshu.practice.june_13_2018.june20.himanshu.practice.june_13_2018.hour_01.GraphOp(5);
//
//        head = head.insertHelper(head, 152);
//        head = head.insertHelper(head, 53);
//        head = head.insertHelper(head, 54);
//        june20.himanshu.practice.june_13_2018.june20.himanshu.practice.june_13_2018.hour_01.GraphOp temp = head;
//        head = head.insertHelper(head, 252);
//        head = head.insertHelper(head, 533);
//
//
//
//        head.print(head,1);
//
//        june20.himanshu.practice.june_13_2018.june20.himanshu.practice.june_13_2018.hour_01.GraphOp lastNode = head.lastNode(head);
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
//class GraphOp {
//    int value;
//    june20.himanshu.practice.june_13_2018.june20.himanshu.practice.june_13_2018.hour_01.GraphOp next;
//
//    public GraphOp(int value) {
//        this.value = value;
//    }
//
//    public june20.himanshu.practice.june_13_2018.june20.himanshu.practice.june_13_2018.hour_01.GraphOp lastNode(june20.himanshu.practice.june_13_2018.june20.himanshu.practice.june_13_2018.hour_01.GraphOp node) {
//        if(node == null || node.next == null) {
//            return node;
//        }
//        return lastNode(node.next);
//    }
//
//    june20.himanshu.practice.june_13_2018.june20.himanshu.practice.june_13_2018.hour_01.GraphOp insertHelper(june20.himanshu.practice.june_13_2018.june20.himanshu.practice.june_13_2018.hour_01.GraphOp node, int value) {
//        june20.himanshu.practice.june_13_2018.june20.himanshu.practice.june_13_2018.hour_01.GraphOp node1 = new june20.himanshu.practice.june_13_2018.june20.himanshu.practice.june_13_2018.hour_01.GraphOp(value);
//        node1.next = node;
//        return node1;
//    }
//
//    void print(june20.himanshu.practice.june_13_2018.june20.himanshu.practice.june_13_2018.hour_01.GraphOp node, int time) throws InterruptedException {
//        if (node == null) {
//            return;
//        }
//        Thread.sleep(time);
//        System.out.print(node.value + "-->");
//        print(node.next, time);
//    }
//
//    boolean isCyclePresent(june20.himanshu.practice.june_13_2018.june20.himanshu.practice.june_13_2018.hour_01.GraphOp node) {
//        if (node == null || node.next == null)
//            return false;
//
//        june20.himanshu.practice.june_13_2018.june20.himanshu.practice.june_13_2018.hour_01.GraphOp tortoise = node;
//        june20.himanshu.practice.june_13_2018.june20.himanshu.practice.june_13_2018.hour_01.GraphOp hair = node;
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