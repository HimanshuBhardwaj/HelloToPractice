package com.himanshu.practice.june.June12;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by Himanshu Bhardwaj on 12/06/18.
 * 11:18 --> 11:40
 * 12:40 -->
 */

//tested
public class GraphBFS {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int numberNodes = sc.nextInt();
        Graph graph = new Graph(numberNodes);

        for (int i = 0; i < numberNodes; i++) {
            StringTokenizer st = new StringTokenizer(sc.next(), ",");
            int source = Integer.parseInt(st.nextToken());
            int destination;

            while (st.hasMoreTokens()) {
                destination = Integer.parseInt(st.nextToken());
                graph.insert(source, destination);
            }
        }

        graph.printAdjMatrix();

        Queue<Integer> levelOrder = new LinkedList<>();
        levelOrder.add(0);
        levelOrder.add(-1);
        //graph.levelOrderTraversal(levelOrder, new LLinkedList<Integer>(), 0, new int[numberNodes]);



        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        //graph.bfs(queue, new int[numberNodes]);

        graph.dfs(0,new int[numberNodes]);
    }
}


class Graph {
    LinkedList<Integer> adjMatrix[];
    int numberNode;


    public Graph(int nodeCount) {
        numberNode = nodeCount;
        adjMatrix = new LinkedList[numberNode];

        for (int i = 0; i < nodeCount; i++) {
            adjMatrix[i] = new LinkedList<Integer>();
        }
    }

    public void insert(int source, int destination) {
        if (source >= numberNode || destination >= numberNode) {
            return;
        }
        adjMatrix[source].add(destination);

    }

    public void printAdjMatrix() {
        for (int i = 0; i < numberNode; i++) {
            System.out.print(i + ": ");
            for (int j = 0; j < adjMatrix[i].size(); j++) {
                System.out.print(adjMatrix[i].get(j) + " ");
            }
            System.out.println();
        }
    }

    //visited: 1 ==> underconsideration; 2==? explored
    public void dfs(int node, int[] visited) {
        if (visited[node] == 1) {
            System.out.println("Cycle");
            return;
        }
        if (visited[node] == 2) {
            return;//already vicited
        }
        System.out.printf("node visiting %d\n",node);
        visited[node] = 1;
        for (int i = 0; i < adjMatrix[node].size(); i++) {
            dfs(adjMatrix[node].get(i), visited);
        }
        System.out.printf("node visited %d\n",node);
        visited[node] = 2;
    }

    //visited: 1 ==> underconsideration; 2==? explored
    //can be used to check bipartiteness of graph
    //tested
    public void bfs(Queue<Integer> queue, int visited[]) {
        if (queue.isEmpty()) {
            return;
        }

        int root = queue.poll();

        if (visited[root] == 1) {
            bfs(queue, visited);
            return;//alreadu into consideration
        }


        System.out.printf("Exploring of node %d has started\n", root);
        visited[root] = 1;


        for (int i = 0; i < adjMatrix[root].size(); i++) {
            if (visited[adjMatrix[root].get(i)] == 1 || visited[adjMatrix[root].get(i)] == 2) {
                continue;
            }
            queue.add(adjMatrix[root].get(i));
        }
        bfs(queue, visited);
        System.out.printf("Exploring of node %d has end\n", root);
        visited[root] = 2;
    }


    //tested
    public void levelOrderTraversal(Queue<Integer> queue, LinkedList<Integer> levellist, int level, int[] visited) {
        //System.out.printf("Visiting level order traversal: queuesize: %d\tlevel: %d\n", queue.size(), level);
        if (queue.isEmpty()) {
            return;
        }

        int root = queue.poll();


        if (root == -1) {
            System.out.printf("Level_: %d\n", level, levellist.size());
            for (int i : levellist) {
                System.out.print(i + " ");
            }
            System.out.println();
            levellist.removeAll(levellist);


            if (queue.isEmpty()) {
                return;
            } else {
                queue.add(root);
                level++;
            }
        } else {
            visited[root] = 2;
            levellist.add(root);
            for (int j = 0; j < adjMatrix[root].size(); j++) {
                if (visited[adjMatrix[root].get(j)] == 0) {
                    visited[adjMatrix[root].get(j)] = 1;
                    queue.add(adjMatrix[root].get(j));
                }
            }
        }
        levelOrderTraversal(queue, levellist, level, visited);
    }
}
