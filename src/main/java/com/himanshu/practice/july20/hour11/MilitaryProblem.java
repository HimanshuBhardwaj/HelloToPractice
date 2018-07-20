package com.himanshu.practice.july20.hour11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

/**
 * Created by himanshubhardwaj on 20/07/18.
 * problem Statement: https://codeforces.com/contest/1006/problem/E
 * Algo: DFS
 * Submissions:
        * https://codeforces.com/contest/1006/submission/40573124
        * https://codeforces.com/contest/1006/submission/40573088
        * https://codeforces.com/contest/1006/submission/40573019
 *
 */
public class MilitaryProblem {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int q = Integer.parseInt(str[1]);
        Tree tree = new Tree(n);
        str = br.readLine().split(" ");

        for (int i = 1; i <= (n - 1); i++) {
            tree.insert(Integer.parseInt(str[i - 1]), i + 1);
        }


        //tree.printInorder();
        tree.DFS(0);
        //tree.printChildren();
        // tree.printInorder();
        // tree.printPositionInInorderTraversal();

        for (int i = 0; i < q; i++) {
            str = br.readLine().split(" ");
            int node = Integer.parseInt(str[0]);
            int distance = Integer.parseInt(str[1]);
            tree.printRequiredNode(node, distance);
        }


    }
}


class Tree {
    LinkedList<Integer> edj[];
    ArrayList<Integer> aEdj[];
    int numNod;
    int numChild[];
    int inorderTraversal[];
    int positionInInorderTraversal[];
    int pos = 0;

    public Tree(int n) {
        numNod = n;
        edj = new LinkedList[numNod];
        for (int i = 0; i < numNod; i++) {
            edj[i] = new LinkedList<>();
        }
        numChild = new int[n];
        inorderTraversal = new int[n];
        positionInInorderTraversal = new int[n];
    }



    public void insert(int source, int destination) {
        source--;
        destination--;
        edj[source].addLast(destination);
    }

    public void DFS(int node) {
        //System.out.println(node);
        positionInInorderTraversal[node] = pos;
        inorderTraversal[pos] = node;
        pos++;
        int child = 1;

        for (int neighbour : edj[node]) {
            DFS(neighbour);
            child += numChild[neighbour];
        }
        numChild[node] = child;
    }

    public void print() {
        for (int i = 0; i < edj.length; i++) {
            System.out.print(i + ": ");
            for (int j = 0; j < edj[i].size(); j++) {
                System.out.print(edj[i].get(j) + " ");
            }
            System.out.println();
        }
    }

    public void printInorder() {
        System.out.println("@printInorder");
        for (int i = 0; i < inorderTraversal.length; i++) {
            System.out.print("(" + (i + 1) + "," + (inorderTraversal[i] + 1) + ")\t\t");
        }
        System.out.println();
    }


    public void printChildren() {
        System.out.println("@printChildren");
        for (int i = 0; i < numChild.length; i++) {
            System.out.print("(" + (i + 1) + "," + numChild[i] + ")\t\t");
        }
        System.out.println();
    }


    public void printPositionInInorderTraversal() {
        System.out.println("@positionInInorderTraversal");
        for (int i = 0; i < positionInInorderTraversal.length; i++) {
            System.out.print("(" + (i + 1) + "," + (positionInInorderTraversal[i] + 1) + ")\t\t");
        }
        System.out.println();
    }

    public void printRequiredNode(int node, int distance) {
        node--;
        if (distance > numChild[node]) {
            System.out.println("-1");
            return;
        }
        //System.out.println((node + 1) + ", " + positionInInorderTraversal[node] + "\t" +
        //      (positionInInorderTraversal[node] + distance - 1) + "\t" + (inorderTraversal[positionInInorderTraversal[node] + distance - 1] + 1));

        System.out.println(inorderTraversal[positionInInorderTraversal[node] + distance - 1] + 1);
    }
}