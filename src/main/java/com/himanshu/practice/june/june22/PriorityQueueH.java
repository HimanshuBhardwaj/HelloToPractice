package com.himanshu.practice.june.june22;

import lombok.AllArgsConstructor;

import java.util.PriorityQueue;

/**
 * Created by Himanshu Bhardwaj on 22/06/18.
 */
public class PriorityQueueH {
    public static void main(String[] args) {
        PriorityQueue<PNode> pNodes1 = new PriorityQueue<PNode>();


        //10 elements if first
        System.out.println("First PQueue:");
        for (int i = 0; i < 10; i++) {
            pNodes1.add(new PNode((i * i + 3) % 51));
            System.out.print(((i * i + 3) % 51) + " ");
        }
        System.out.println();
        System.out.println();

        while (pNodes1.size() > 0) {
            System.out.print(pNodes1.poll().value+" ");
        }


//
//        PriorityQueue<PNodeNew> pNodes2 = new PriorityQueue<PNodeNew>();
//        //10 elements if first
//        System.out.println("Second PQueue:");
//        for (int i = 0; i < 10; i++) {
//            pNodes2.add(new PNodeNew((i * i + 3) % 31));
//            System.out.print(((i * i + 3) % 31) + " ");
//        }
//        System.out.println();


    }
}


@AllArgsConstructor
class PNode implements Comparable<PNode> {
    int value;

    @Override
    public int compareTo(PNode o) {
        return this.value - o.value;
    }
}


//Lesson of the day: Priority Queue me Comparator nahi hota hai but comparable hota hai
//
//
// @Getter
//@AllArgsConstructor
//class PNodeNew implements Comparator<PNodeNew> {
//    int value;
//
//    @Override
//    public int compare(PNodeNew first, PNodeNew second) {
//        return first.getValue() - second.getValue();
//    }
//}