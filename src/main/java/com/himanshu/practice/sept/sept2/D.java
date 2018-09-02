package com.himanshu.practice.sept.sept2;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by himanshubhardwaj on 02/09/18.
 * Statement:https://codeforces.com/contest/1037/problem/D
 * Algo: BFS
 * Submission: https://codeforces.com/contest/1037/submission/42408164
 * 12:05
 */
public class D {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Tree t = new Tree(n);

        for (int i = 0; i < (n - 1); i++) {
            String str[] = br.readLine().split(" ");
            t.insert(Integer.parseInt(str[0]) - 1, Integer.parseInt(str[1]) - 1);
        }


        String str[] = br.readLine().split(" ");
        int bfs[] = new int[str.length];


        for (int i = 0; i < str.length; i++) {
            bfs[i] = Integer.parseInt(str[i]) - 1;
        }
        t.build(bfs);
        System.out.print((t.BFS(0, bfs) ? "Yes" : "No"));
    }
}


class Tree {
    ArrayList<Number> adj[];
    TreeMap<Integer, Integer> bfsOrder = new TreeMap<>();


    public Tree(int n) {
        adj = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    void insert(int s, int d) {
        adj[s].add(new Number(d));
        adj[d].add(new Number(s));
    }


    void build(int[] bfs) {
        for (int i = 0; i < bfs.length; i++) {
            bfsOrder.put(bfs[i], i);
        }
        Number.bfsOrder = bfsOrder;

        for (int i = 0; i < adj.length; i++) {
            Collections.sort(adj[i]);
        }
    }


    boolean BFS(int node, int[] bfs) {

        int level = 1;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(node);
        LinkedList<Integer> list = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();


        while (!queue.isEmpty()) {
            //System.out.println(queue);
            int top = queue.poll();
            visited.add(top);
            list.addLast(top);
            for (Number x : adj[top]) {
                if (!visited.contains(x.number)) {
                    queue.add(x.number);
                }
            }
        }

        boolean isTrue = true;
        int pos = 0;

        for (int x : list) {
            if (bfs[pos++] != x) {
                isTrue = false;
                break;
            }
        }
        return isTrue;
    }


}


class Number implements Comparable<Number> {
    int number;
    static TreeMap<Integer, Integer> bfsOrder;

    @java.beans.ConstructorProperties({"number"})
    public Number(int number) {
        this.number = number;
    }

    @Override
    public int compareTo(Number o) {
        return bfsOrder.get(this.number) - bfsOrder.get(o.number);
    }

    public String toString() {
        return "Number(number=" + this.number + ")";
    }
}

/*
10
1 2
1 3
2 4
2 5
5 6
5 7
6 9
6 10
7 8
1 2 3 4 5 6 7 8 9 10
*/