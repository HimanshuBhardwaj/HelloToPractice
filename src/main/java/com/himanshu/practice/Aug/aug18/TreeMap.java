package com.himanshu.practice.Aug.aug18;

import lombok.AllArgsConstructor;

import java.util.TreeSet;

/**
 * Created by himanshubhardwaj on 19/08/18.
 */
public class TreeMap {
    public static void main(String[] args) {
        TreeSet<A> treeMap = new TreeSet<A>();
        A a1 = new A(2);
        A a2 = new A(3);

        treeMap.add(a1);
        treeMap.add(a2);
        System.out.println(treeMap.size());
        System.out.println(a1.equals(a2));

    }
}


@AllArgsConstructor
class A implements Comparable<A> {
    int x;

    @Override
    public int compareTo(A o) {
        return 0;
    }
}