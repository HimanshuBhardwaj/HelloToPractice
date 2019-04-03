package com.himanshu.practice.y2019.march.march30;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by himanshubhardwaj on 30/03/19.
 */
public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());


        ArrayList<Node> nodes = new ArrayList<Node>(n);

        for (int i = 0; i < n; i++) {
            nodes.add(i, new Node(i));
        }


        Node root;
        for (int i = 0; i < n; i++) {
            String[] str = br.readLine().split(" ");
            int parentNode = Integer.parseInt(str[0]) - 1;
            int behaved = Integer.parseInt(str[1]);
            if (parentNode != -2) {
                nodes.get(i).parent = nodes.get(parentNode);
                nodes.get(parentNode).children.add(nodes.get(i));
                root = nodes.get(i);
            }
            nodes.get(i).behaved = behaved;
        }


    }
}


class Node implements Comparable<Node> {
    int nodeNumber;
    Node parent;
    int behaved;
    ArrayList<Node> children;
    boolean isDeleted;

    public Node(int number) {
        nodeNumber = number;
        children = new ArrayList<>();
        isDeleted = false;
        parent=null;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Node)) return false;
        final Node other = (Node) o;
        if (this.nodeNumber != other.nodeNumber) return false;
        return true;
    }




    @Override
    public int compareTo(Node o) {
        return this.nodeNumber - o.nodeNumber;
    }


    boolean doDFS(Node root) {
        boolean areChildrenBehaved = true;


        for (Node child : root.children) {
            areChildrenBehaved = areChildrenBehaved&&doDFS(child);
        }

        //TODO: Complete it
        //if (root.)


        return areChildrenBehaved;


    }
}