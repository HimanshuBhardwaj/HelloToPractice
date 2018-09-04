package com.himanshu.practice.sept.sept3;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.PriorityQueue;

/**
 * Created by himanshubhardwaj on 03/09/18.
 * Lesson learned: Priority queue doesnot support random access/deletion. Both operations are O(n)
 */
public class PriorityQueuePOC {
    public static void main(String[] args) {
        PriorityQueue<X> pq = new PriorityQueue<>();
        X a = new X(1,"Himanshu");
        X b = new X(2,"Himanshu");
        X c = new X(3,"Himanshu");
        X d = new X(-1,"Himanshu");
        pq.add(a);
        pq.add(b);
        pq.add(c);
        pq.add(d);

        while (!pq.isEmpty()) {
            System.out.println(pq.poll());
        }
        System.out.println(".......");

        pq.add(a);
        pq.add(b);
        pq.add(c);
        pq.add(d);


        c.value=-2;

        System.out.println(pq.poll());


        while (!pq.isEmpty()) {
            System.out.println(pq.poll());
        }
        System.out.println(".......");


    }
}


@ToString
@AllArgsConstructor
class X implements Comparable<X> {
    int value;
    String name;

    @Override
    public int compareTo(X o) {
        return this.value - o.value;
    }
}