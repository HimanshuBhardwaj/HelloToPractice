//package com.himanshu.practice.july22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * Created by himanshubhardwaj on 22/07/18.
 * IMPORTANT: Problem set is not clear.
 */
//public class HappinessTour {
public class TestClass {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());

        for (int t = 0; t < tc; t++) {
            int n = Integer.parseInt(br.readLine());
            Tree tree = new Tree(n);
            String str[] = br.readLine().split(" ");

            for (int i = 0; i < n; i++) {
                tree.happiness[i] = Long.parseLong(str[i]);
            }
            int numRoads = Integer.parseInt(br.readLine());
            String[] source = br.readLine().split(" ");
            String[] destination = br.readLine().split(" ");

            if (numRoads > 0) {
                for (int i = 0; i < numRoads; i++) {
                    tree.insert(Integer.parseInt(source[i]) - 1, Integer.parseInt(destination[i]) - 1);
                }

                tree.computeMaximumHappiness(0, 0, new LinkedList<Long>(), new boolean[n]);
                if (t < (tc - 1)) {
                    System.out.println(tree.maximumHappiness);
                } else {
                    System.out.print(tree.maximumHappiness);
                }
            } else {
                if (t < (tc - 1)) {
                    System.out.println(Math.max(0, tree.happiness[0]));
                } else {
                    System.out.print(Math.max(0, tree.happiness[0]));
                }
            }
        }
    }
}


class Tree {
    LinkedList<Integer> edgeList[];
    long[] happiness;
    int nodesNum;
    long maximumHappiness = Long.MIN_VALUE;


    public Tree(int num) {
        edgeList = new LinkedList[num];
        nodesNum = num;
        happiness = new long[num];

        for (int i = 0; i < num; i++) {
            edgeList[i] = new LinkedList<>();
        }
    }

    public void insert(int source, int destination) {
        edgeList[source].addLast(destination);
        edgeList[destination].addLast(source);
    }

    public void computeMaximumHappiness(int root, int parent, LinkedList<Long> path, boolean[] visited) {
        if (visited[root]) {
            return;
        }

        if (isChild(root, parent)) {
            //System.out.println(path);
            updateMaximumHappiness(path);
            return;
        }

        if (root == 0) {
            path.addLast(happiness[0]);
        }

        //though I know it is a tree and this is not needed but there are so many gaps in problem statement. Can not assume anything.
        visited[root] = true;


        for (int neighbour : edgeList[root]) {
            if (neighbour != parent) {
                path.addLast(happiness[neighbour]);
                computeMaximumHappiness(neighbour, root, path, visited);
                path.removeLast();
            }
        }

        if (root == 0) {
            path.removeLast();
        }

    }

    private void updateMaximumHappiness(LinkedList<Long> path) {
        long sum = 0;

        //sum 0 means he will skip all cities
        for (long happiness : path) {
            if (sum + happiness > 0) {
                sum = sum + happiness;
            } else {
                sum = 0;
            }
            maximumHappiness = Math.max(maximumHappiness, sum);
        }
    }

    private boolean isChild(int root, int parent) {
        if (edgeList[root].size() == 1 & root != 0) {
            return true;
        }
        return false;
    }
}