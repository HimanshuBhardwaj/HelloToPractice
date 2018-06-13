package com.himanshu.practice.june_13_2018;

import java.util.*;

/**
 * Created by himanshubhardwaj on 13/06/18.
 */
public class GraphAdjecencyListTraversal {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //Get input number of nodes
        int nodes = Integer.parseInt(sc.nextLine());

        Graph graph = new Graph(nodes);

        //get input adjecency list value

        for (int i = 0; i < nodes; i++) {
            String line = sc.nextLine();

            StringTokenizer st = new StringTokenizer(line, ",");
            int currentNode = -1;
            if (st.hasMoreTokens()) {
                currentNode = Integer.parseInt(st.nextToken());
            }
            while (st.hasMoreTokens()) {
                if (!graph.addEdge(currentNode, Integer.parseInt(st.nextToken()))) {
                    System.out.println("could not add edge becaue of invalid source");
                }
            }
        }

        System.out.println("adj_matrix in question");
        graph.printAdjMatrix();
        graph.DFS(0,new int[nodes]);
        System.out.println("Going for BFS");
        System.out.println();
        System.out.println();

        Queue<Integer> bfsQueue = new LinkedList<Integer>();
        bfsQueue.add(0);//assuming 0 is the root
        graph.BFS(bfsQueue, new int[nodes], 0);



    }


}


class Graph {
    LinkedList<Integer> adj_matrix[];
    int nodeCount;

    public Graph(int nodeCount) {
        this.nodeCount = nodeCount;
        adj_matrix = new LinkedList[this.nodeCount];
        for (int i = 0; i < this.nodeCount; i++) {
            adj_matrix[i] = new LinkedList<Integer>();
        }
    }

    public boolean addEdge(int source, int destination) {
        if (source == -1) {
            return false;
        }
        this.adj_matrix[source].add(destination);
        return true;
    }

    public void printAdjMatrix() {
        for (int i = 0; i < this.adj_matrix.length; i++) {
            System.out.print(i + ": ");
            for (int j = 0; j < this.adj_matrix[i].size(); j++) {
                System.out.print(this.adj_matrix[i].get(j) + " ");
            }
            System.out.println();
        }
    }

    //Assumption is that this visited would be of the same size of number of nodes
    //through this topological sort is also done
    public void DFS (int root, int visited[]) {
        if(visited[root] == 1) {
            return;
            //Backtrack
        }
        visited[root] = 1;
        System.out.println("coming to node "+ root);
        for(int i=0;i<adj_matrix[root].size();i++) {
            DFS(adj_matrix[root].get(i), visited);
        }
        System.out.println(root + "explored");
    }


    //BFS is ging good but there is some problem in that level thing; correct that
    public void BFS(Queue<Integer> nodesInConsideration, int visited[], int level) {
        if(nodesInConsideration.isEmpty()) {
            System.out.println("complete graph traversed");
            return;
        }
        int root = nodesInConsideration.poll();
        if(visited[root] ==1) {
            System.out.println("root"+ root +"is already visited");
            BFS(nodesInConsideration, visited, level);
            return;
        }
        visited[root] = 1;
        System.out.println("node explored: "+root+"\t level: "+level);

        for(int i=0;i<adj_matrix[root].size();i++) {
            if(visited[adj_matrix[root].get(i)] ==0) {
                //means it is not visited
                nodesInConsideration.add(adj_matrix[root].get(i));
            }
        }
        BFS(nodesInConsideration, visited, level+1);
    }
}
