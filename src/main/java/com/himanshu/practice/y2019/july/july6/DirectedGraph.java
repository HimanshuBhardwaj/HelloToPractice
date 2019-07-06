package com.himanshu.practice.y2019.july.july6;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.TreeSet;

/**
 * Created by himanshubhardwaj on 06/07/19.
 */
public class DirectedGraph {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");

        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);

        Graph graph = new Graph(n);

        for (int i = 0; i < m; i++) {
            str = br.readLine().split(" ");
            int s = Integer.parseInt(str[0]);
            int d = Integer.parseInt(str[1]);
            graph.insert(s, d);
        }
        graph.printGraph();
        System.out.println();
        System.out.println("---------------");
        System.out.println();

        Graph transitiveClosire = GraphUtils.transitiveClosire(graph);
        transitiveClosire.printGraph();


        ArrayList<Integer> topologicalOrdering = GraphUtils.topologcalSortTraditional(graph);

        System.out.println();
        for (int x : topologicalOrdering) {
            System.out.print(x + ", ");
        }
        System.out.println();
        System.out.println("---------------");
        System.out.println();
        topologicalOrdering = GraphUtils.topologicalSortTimer(graph);
        System.out.println();
        for (int x : topologicalOrdering) {
            System.out.print(x + ", ");
        }


    }
}

class Graph {
    ArrayList<Integer>[] adjList;
    int size;

    public Graph(int n) {
        adjList = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<>();
        }
        this.size = n;
    }

    public Graph(Graph g) {
        this.size = g.size;
        this.adjList = g.adjList.clone();
    }


    public void insert(int s, int d) {
        adjList[s].add(d);
    }

    public void printGraph() {
        System.out.println("Printing graph");
        for (int i = 0; i < size; i++) {
            System.out.print(i + ": ");
            for (int neighbour : adjList[i]) {
                System.out.print(neighbour + ",");
            }
            System.out.println();
        }
    }
}

class GraphUtils {
    static Graph transitiveClosire(Graph graph) {
        boolean adjMatrix[][] = new boolean[graph.size][graph.size];
        for (int i = 0; i < graph.size; i++) {
            for (int n : graph.adjList[i]) {
                adjMatrix[i][n] = true;
            }
        }

        for (int k = 0; k < graph.size; k++) {
            for (int i = 0; i < graph.size; i++) {
                for (int j = 0; j < graph.size; j++) {
                    adjMatrix[i][j] = adjMatrix[i][j] || (adjMatrix[i][k] && adjMatrix[k][j]);
                }
            }
        }


        Graph transitiveClosureGrah = new Graph(graph.size);


        for (int i = 0; i < graph.size; i++) {
            for (int j = 0; j < graph.size; j++) {
                if (adjMatrix[i][j]) {
                    transitiveClosureGrah.insert(i, j);
                }
            }
        }
        return transitiveClosureGrah;
    }

    //1:40-->1:48
    public static ArrayList<Integer> topologcalSortTraditional(Graph graph) {
        if (graph == null) {
            return new ArrayList<>();
        }


        int graphS = graph.size;
        boolean[] isVisited = new boolean[graphS];
        ArrayList<Integer> topologicalOrdering = new ArrayList<>();

        for (int i = 0; i < isVisited.length; i++) {
            if (!isVisited[i]) {
                isVisited[i] = true;
                DFSTopologicalOrder(topologicalOrdering, i, isVisited, graph);
            }
        }

        return topologicalOrdering;
    }

    //DAG
    private static void DFSTopologicalOrder(ArrayList<Integer> topologicalOrdering, int index, boolean[] isVisited, Graph graph) {

        for (int neighbour : graph.adjList[index]) {
            if (!isVisited[neighbour]) {
                isVisited[neighbour] = true;
                DFSTopologicalOrder(topologicalOrdering, neighbour, isVisited, graph);
            }
        }

        topologicalOrdering.add(index);
    }

    public static ArrayList<Integer> topologicalSortTimer(Graph graph) {
        if (graph == null) {
            return null;
        }

        TreeSet<TopologicalClassNodeContext> graphNodes = new TreeSet<>();
        boolean[] isVisited = new boolean[graph.size];
        Timer timer = new Timer(0);

        for (int i = 0; i < isVisited.length; i++) {
            if (!isVisited[i]) {
                DFSHelperTimed(graphNodes, i, isVisited, timer, graph);
            }
        }

        ArrayList<TopologicalClassNodeContext> arrayList = new ArrayList<>(graphNodes);
        Comparator<TopologicalClassNodeContext> comparator = new Comparator<TopologicalClassNodeContext>() {
            @Override
            public int compare(TopologicalClassNodeContext o1, TopologicalClassNodeContext o2) {
                return (int) (o1.endTime - o2.endTime);
            }
        };

        Collections.sort(arrayList, comparator);
        ArrayList<Integer> topologicalOrdering = new ArrayList<>();
        for (int i = 0; i < arrayList.size(); i++) {
            topologicalOrdering.add(arrayList.get(i).index);
        }

        return topologicalOrdering;
    }

    private static void DFSHelperTimed(TreeSet<TopologicalClassNodeContext> graphNodes, int index, boolean[] isVisited, Timer timer, Graph graph) {
        timer.incrementTimer();

        isVisited[index] = true;
        graphNodes.add(new TopologicalClassNodeContext(index, timer.time));

        for (int neighbout : graph.adjList[index]) {
            if (!isVisited[neighbout]) {
                DFSHelperTimed(graphNodes, neighbout, isVisited, timer, graph);
            }
        }
        timer.incrementTimer();
        graphNodes.ceiling(new TopologicalClassNodeContext(index, -1)).endTime = timer.getTime();
    }


    @AllArgsConstructor
    @Getter
    private static class Timer {
        private long time;

        void incrementTimer() {
            time++;
        }
    }

    @ToString
    private static class TopologicalClassNodeContext implements Comparable<TopologicalClassNodeContext> {
        int index;
        long startTime;
        long endTime;

        public TopologicalClassNodeContext(int index, long startTime) {
            this.index = index;
            this.startTime = startTime;
        }


        @Override
        public int compareTo(TopologicalClassNodeContext o) {
            return this.index - o.index;
        }
    }

}
/*

5 8
0 1
0 2
1 4
2 3
2 4
3 1
4 3
4 0
 */