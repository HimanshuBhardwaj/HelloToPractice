package com.himanshu.practice.y2019.April.april3;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * Created by himanshubhardwaj on 03/04/19.
 * Statement: https://codeforces.com/contest/1143/problem/C
 * Time Start: 9:44pm --> 10:11
 * Submission: https://codeforces.com/contest/1143/submission/52280303
 */
public class Queen {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Node[] tree = new Node[n];
        String[] str;

        for (int i = 0; i < n; i++) {
            tree[i] = new Node(i + 1);
        }

        for (int i = 0; i < n; i++) {
            str = br.readLine().split(" ");
            tree[i].parent = (Integer.parseInt(str[0]) != -1) ? tree[Integer.parseInt(str[0]) - 1] : null;
            if (Integer.parseInt(str[1]) == 1) {
                tree[i].isWellBehaved = false;
            } else {
                tree[i].isWellBehaved = true;
            }
            if (tree[i].parent != null) {
                tree[i].parent.children.add(tree[i]);
            }
        }


//        System.out.println("-----");
//        for (Node nn : tree) {
//            //  System.out.println(nn);
//        }
//        System.out.println("-----");


        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; i++) {
            if (!isWellBehavedFamily(tree[i])) {
                sb.append(i + 1);
                sb.append(" ");
            }
        }

//        System.out.print(sb.length());
//        System.out.println(sb.toString().equals(""));


        if (!sb.toString().equalsIgnoreCase("")) {
            System.out.print(sb.toString());
        } else {
            System.out.print(-1);
        }


    }

    private static boolean isWellBehavedFamily(Node node) {
        if (node == null || node.isWellBehaved) {
            return true;
        }

        for (Node child : node.children) {
            if (child.isWellBehaved) {
                return true;
            }
        }
        return false;
    }
}



class Node {
    int index;
    Node parent;
    boolean isWellBehaved;
    LinkedList<Node> children = new LinkedList<>();

    public Node(int index) {
        this.index = index;
    }


    public void setParent(Node parent) {
        this.parent = parent;

    }

    public void addChild(Node child) {
        this.children.addLast(child);
    }


}