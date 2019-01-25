package com.himanshu.practice.y2018.june.hour_3;

/**
 * Created by Himanshu Bhardwaj on 10/06/18.
 * 12:52 am
 */
public class BSTMerge {
}


class Node {
    int value;
    DNode left;
    DNode right;

    //a is heaver side
    public static DNode merge(DNode node1, DNode node2) {
        if (node1 == null && node2 == null) {
            return null;
        }
        if (node1 != null && node2 == null) {
            return node1;
        }
        if (node1 == null && node2 != null) {
            return node2;
        }

        //now both are not null

        if(node1.value < node2.value) {
            node1.right = merge(node1.right, node2);
        }


        return null;

    }

    public static DNode insert(DNode a, DNode b) {
        return null;
    }
}
