package com.himanshu.practice.june.june21.hour2;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by Himanshu Bhardwaj on 21/06/18.
 */
public class KColouring {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int node = Integer.parseInt(sc.next());
        Graph graph = new Graph(node);

        for (int i = 0; i < node; i++) {
            StringTokenizer st = new StringTokenizer(sc.next(), ",");
            int source = Integer.parseInt(st.nextToken());

            while (st.hasMoreTokens()) {
                graph.insert(source, Integer.parseInt(st.nextToken()));
            }
        }
        graph.print();

        System.out.println("testing");
        int k = 5;
        int[] result = graph.isKColoured(k);


        if (result != null) {
            System.out.println("Tree\t\tColour");
            for (int i = 0; i < result.length; i++) {
                System.out.println(i + "\t\t" + result[i]);
            }
        } else {
            System.out.println("not " + k + " colourable");
        }

        System.out.println();
        System.out.println("Minimal colours required:  "+graph.getminiumColourable());

    }
}


class Graph {
    LinkedList<Integer> adj[];
    int numNode;


    public Graph(int n) {
        adj = new LinkedList[n];
        this.numNode = n;
        for (int i = 0; i < n; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    public int getminiumColourable() {
        return getMinimumColourablehelper(0, adj.length);
    }


    private int getMinimumColourablehelper(int start, int end) {
        if (start == end) {
            return start;
        }
        if (start > end) {
            return end;//end is always colourable
        }

        int mid = start + (end - start) / 2;

        if (isKColoured(mid) != null) {
            return getMinimumColourablehelper(start, mid);
        } else {
            return getMinimumColourablehelper(mid+1, end);
        }
    }

    public void insert(int source, int destination) {
        adj[source].add(destination);
        adj[destination].add(source);

    }

    public void print() {
        for (int i = 0; i < numNode; i++) {
            System.out.print(i + ": ");
            for (int j = 0; j < adj[i].size(); j++) {
                System.out.print(adj[i].get(j) + ", ");
            }
            System.out.println();
        }
    }

    //1...k
    public int[] isKColoured(int k) {
        int colours[] = new int[numNode];
        return isKColouredHelper(0, k, colours);
    }

    private int[] isKColouredHelper(int node, int colour, int[] colours) {
        if (node == numNode) {
            return colours;
        }

        int[] result = null;

        for (int j = 1; j <= colour && (result == null); j++) {
            if (isFiesable(j, node, colours)) {
                colours[node] = j;
                result = isKColouredHelper(node + 1, colour, colours);
            }
        }

        return result;
    }

    public boolean isFiesable(int colour, int node, int[] colourmatrix) {
        boolean fiesablity = true;
        for (int i = 0; i < adj[node].size(); i++) {
            if (colourmatrix[adj[node].get(i)] == colour) {
                fiesablity = false;
                break;
            }
        }
        return fiesablity;
    }

    public boolean canUsethisColour(int colour, int[] colours) {
        for (int i : colours) {
            if (i == colour) {
                return false;
            }
        }
        return true;
    }
}