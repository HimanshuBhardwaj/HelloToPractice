package com.himanshu.practice.hour_3;

import lombok.NoArgsConstructor;

/**
 * Created by Himanshu Bhardwaj on 10/06/18.
 * 12:52 am
 */
public class BSTMerge {
}


class Node {
    int value;
    Node left;
    Node right;

    //a is heaver side
    public static Node merge(Node node1, Node node2) {
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

    public static Node insert(Node a, Node b) {
        return null;
    }
}
