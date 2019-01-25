package com.himanshu.practice.y2018.july.july5.hour8;

import lombok.AllArgsConstructor;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Himanshu Bhardwaj on 06/07/18.
 */


public class LexographicalSortingExample {
    public static void main(String[] args) {
        List<Edge> lists = new LinkedList<>();
        lists.add(new Edge(1, 3, 3, 1111));
        lists.add(new Edge(11, 3, 1, 3));
        lists.add(new Edge(1, 3, 3, 1));
        lists.add(new Edge(0, 1, 2, 1));
        lists.add(new Edge(0, 1, 1, 1));
        lists.add(new Edge(0, 1, 2, 0));


        for(Edge e: lists) {
            System.out.println(e);
        }
        System.out.println();System.out.println();
        Collections.sort(lists);


        for(Edge e: lists) {
            System.out.println(e);
        }

    }
}

@AllArgsConstructor
class Edge implements Comparable<Edge> {
    int vertex1;
    int vertex2;
    int vertex3;
    int vertex4;


    @Override
    public int compareTo(Edge o) {
        if (this.vertex1 == o.vertex1) {
            if (this.vertex2 == o.vertex2) {
                if (this.vertex3 == o.vertex3) {
                    return this.vertex4 - o.vertex4;
                } else {
                    return this.vertex3 - o.vertex3;
                }
            } else {
                return this.vertex2 - o.vertex2;
            }
        } else {
            return this.vertex1 - o.vertex1;
        }
    }

    public String toString() {
        return "(" + vertex1 + ", " + vertex2 + ", " + vertex3 + ", " + vertex4 + ")";
    }
}