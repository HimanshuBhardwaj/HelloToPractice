package com.himanshu.practice.july.july14;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * Created by himanshubhardwaj on 14/07/18.
 * One of the most beautiful question I've ever solved.
 * <h1></>Algo: Tree Traversal, Binary raise</>
 * http://codeforces.com/contest/519/problem/E
 */
public class LCA {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String str[];
        Tree tree = new Tree(n);

        for (int i = 0; i < (n - 1); i++) {
            str = br.readLine().split(" ");
            tree.insert(Integer.parseInt(str[0]), Integer.parseInt(str[1]));
        }
        tree.DFS();

        int m = Integer.parseInt(br.readLine());
        for (int i = 1; i <= m; i++) {
            str = br.readLine().split(" ");
            System.out.println(tree.query(Integer.parseInt(str[0]), Integer.parseInt(str[1])));
        }
    }
}


class Tree {
    LinkedList<Integer> adjList[];
    int timer = 0;
    int[] timeIn;
    int[] timeOut;
    int LEVEL_ANC = 25;
    int numNodes;
    int[] parent;
    int[] depth;
    int[] size; //size of subtree rooted at a including root
    int[][] binaryRaise;


    public Tree(int numNodes) {
        numNodes++;
        this.numNodes = numNodes;
        adjList = new LinkedList[numNodes];
        timeIn = new int[numNodes];
        timeOut = new int[numNodes];
        binaryRaise = new int[numNodes][LEVEL_ANC];
        parent = new int[numNodes];
        depth = new int[numNodes];
        size = new int[numNodes];

        for (int i = 0; i < numNodes; i++) {
            adjList[i] = new LinkedList<>();
        }
    }

    public void insert(int source, int destination) {
        adjList[source].addLast(destination);
        adjList[destination].addLast(source);
    }

    //this is for tree
    //O(n)
    private int DFSHelper(int root, boolean[] visited, int par, int dept) {
        if (visited[root]) {
            return 0;
        }
        visited[root] = true;
        timer++;
        timeIn[root] = timer;
        depth[root] = dept;
        int count = 1;

        binaryRaise[root][0] = par;

        for (int i = 1; i < LEVEL_ANC; i++) {
            binaryRaise[root][i] = binaryRaise[binaryRaise[root][i - 1]][i - 1];
        }

        for (int neighbour : adjList[root]) {
            if (neighbour != par) {
                parent[neighbour] = root;
                count += DFSHelper(neighbour, visited, root, dept + 1);
            }
        }
        timer++;
        timeOut[root] = timer;
        size[root] = count;
        return count;
    }


    //O(n)
    public void DFS() {
        boolean visited[] = new boolean[numNodes];
        for (int i = 1; i < numNodes; i++) {
            if (!visited[i]) {
                DFSHelper(i, visited, 0, 0);
            }
        }
    }

    //would return LCA of both nodes in log n time
    //O(1)
    public int LCA(int a, int b) {
        if (isAncestor(a, b)) {
            return b;
        }
        if (isAncestor(b, a)) {
            return a;
        }
        return binaryRaise[goUp(a, b)][0];
    }


    private int firstNonZero(int[][] binaryRaise, int b) {
        for (int i = LEVEL_ANC - 1; i >= 0; i--) {
            if (binaryRaise[b][i] != 0) {
                return i;
            }
        }
        return 0;
    }

    //a will go up as long as it is not becoming ancestor of b
    //note: a should not be ancestor of b and b should not be ancestor of a
    public int goUp(int a, int b) {
        for (int i = LEVEL_ANC - 1; i >= 0; i--) {
            if (!isAncestor(b, binaryRaise[a][i])) {
                a = binaryRaise[a][i];
            }
//            if (binaryRaise[a][i] != 0) {
//                System.out.println(b + "\t" + a + "\t" + binaryRaise[a][i] + "\t" + isAncestor(b, binaryRaise[a][i]));
//            }
        }

        return a;
    }


    //will tell if b is ancestor of a; logn time
    //O(1)
    public boolean isAncestor(int a, int b) {
        if (a == 0 || b == 0) {
            return true;
        }
        if (timeIn[b] <= timeIn[a] && timeOut[b] >= timeOut[a]) {
            return true;
        }
        return false;
    }


    //distance between a and b
    //O(1)
    public int distance(int a, int b) {
        if (a == b) {
            return 0;
        }
        if (isAncestor(a, b)) {
            return depth[a] - depth[b];
        }
        if (isAncestor(b, a)) {
            return depth[b] - depth[a];
        }


        int lca = LCA(a, b);
        //System.out.println("lca of " + a + " " + b + "is: " + lca);
        return Math.abs((depth[a] - depth[lca]) + (depth[b] - depth[lca]));

    }

    public void print() {
        System.out.println("Node\tdepth");
        for (int i = 1; i < numNodes; i++) {
            System.out.println(i + "\t" + depth[i]);
        }
/*
        for (int i = 0; i < binaryRaise.length; i++) {
            System.out.println(i + ": ");
            for (int j = 0; j < binaryRaise[i].length; j++) {
                System.out.println("\t" + j + ": " + binaryRaise[i][j]);
                if (binaryRaise[i][j] == 0) {
                    break;
                }
            }
        }
        */
    }

    public int query(int a, int b) {
        int d = distance(a, b);
        if (d % 2 == 1) {
            return 0;
        }

        if (a == b) {
            return numNodes - 1;
        }

        int lca = LCA(a, b);

        if (distance(lca, a) == distance(lca, b)) {
            return ((numNodes - 1) - size[goUp(a, lca)] - size[goUp(b, lca)]);
        }

        if (depth[a] > depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }


        //depth[b] > dept[a];
        int expDis = d / 2;
        int to = b;

        for (int i = LEVEL_ANC - 1; i >= 0; i--) {
            if (distance(b, binaryRaise[to][i]) < expDis) {
                to = binaryRaise[to][i];
            }
        }


        int mid = binaryRaise[to][0];
        return size[mid] - size[to];
    }


}

/*
18
1 2
1 3
2 4
2 5
4 7
5 8
5 9
16 6
6 13
8 10
10 11
10 14
11 12
14 16
9 15
15 17
17 18
1
12 16



 */