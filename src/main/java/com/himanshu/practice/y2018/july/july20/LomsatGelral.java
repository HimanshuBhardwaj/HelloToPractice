package com.himanshu.practice.y2018.july.july20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by himanshubhardwaj on 20/07/18.
 * Statement: https://codeforces.com/contest/600/problem/E
 * Algo: Disjoint set union,
 * TODO: Run it
 * I'm 100% sure that this algotithm is correct
 *
 */
public class LomsatGelral {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String colours[] = br.readLine().split(" ");
        Tree tree = new Tree(n);
        String edge[];

        //make tree
        for (int i = 0; i < (n - 1); i++) {
            edge = br.readLine().split(" ");
            tree.insert(Integer.parseInt(edge[0]), Integer.parseInt(edge[1]));
        }

        //insert colours
        for (int i = 0; i < n; i++) {
            tree.insertColour(i, Integer.parseInt(colours[i]));
        }

        //tree.printGraph();
        //System.out.println();

        tree.DFS(0);
        tree.printDominantColour();


    }
}

class Tree {
    LinkedList<Node> edjList[];
    int numNodes;
    Node indexToNodeMApping[];
    int colour[];
    int childFreq[]; //number of returned values
    long dominantColour[];
    TreeMap<Integer, Integer> maps[]; //colour--> frequency

    public Tree(int n) {
        this.numNodes = n;
        edjList = new LinkedList[n];
        indexToNodeMApping = new Node[n];
        colour = new int[n];
        dominantColour = new long[n]; //this we have to print
        maps = new TreeMap[n];

        for (int i = 0; i < n; i++) {
            edjList[i] = new LinkedList<Node>();
        }
    }


    public void insert(int source, int destination) {
        source--;
        destination--;
        if (indexToNodeMApping[source] == null) {
            indexToNodeMApping[source] = new Node();
            indexToNodeMApping[source].index = source;
        }
        if (indexToNodeMApping[destination] == null) {
            indexToNodeMApping[destination] = new Node();
            indexToNodeMApping[destination].index = destination;
        }
        edjList[source].addLast(indexToNodeMApping[destination]);
    }

    public void insertColour(int node, int colour) {
        if (indexToNodeMApping[node] == null) {
            indexToNodeMApping[node] = new Node();
            indexToNodeMApping[node].index = node;
        }
        indexToNodeMApping[node].colour = colour;
        this.colour[node] = colour;
    }


    //frequencyNodeMapping: frequency --> <>
    public void DFS(int node) {
        if (isChildren(node)) {
            TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
            map.put(indexToNodeMApping[node].colour, 1);
            dominantColour[node] = indexToNodeMApping[node].colour;
            maps[node] = map;
            System.out.println((node + 1) + "\t" + maps[node]);
            return;
        }

        int bigNode = -1;
        int bigNodeCount = -1;


        for (Node children : edjList[node]) {
            DFS(children.index);
            if (maps[children.index].size() > bigNodeCount) {
                bigNode = children.index;
                bigNodeCount = maps[children.index].size();
            }
        }

        if (bigNode == -1) {
            throw new RuntimeException("Something is wrong");
        }

        maps[node] = maps[bigNode];
        //because we have already set children's dominant colour

        if (maps[node].containsKey(indexToNodeMApping[node].colour)) {
            maps[node].put(indexToNodeMApping[node].colour, maps[node].get(indexToNodeMApping[node].colour) + 1);
        } else {
            maps[node].put(indexToNodeMApping[node].colour, 1);
        }

        for (Node children : edjList[node]) {
            if (children.index != bigNode) {
                TreeMap<Integer, Integer> childMap = maps[children.index]; //colour, frequency
                for (Map.Entry<Integer, Integer> entry : childMap.entrySet()) {
                    if (maps[node].containsKey(entry.getKey())) {
                        maps[node].put(entry.getKey(), maps[node].get(entry.getKey()) + entry.getValue());
                    } else {
                        maps[node].put(entry.getKey(), entry.getValue());
                    }
                }
            }
        }

        long maxCol = 0;
        int maxColFreq = 0;
        for (Map.Entry<Integer, Integer> entry : maps[node].entrySet()) {
            if (entry.getValue() == maxColFreq) {
                maxCol = maxCol + ((long) entry.getKey());
            } else if (entry.getValue() >= maxColFreq) {
                maxColFreq = entry.getValue();
                maxCol = entry.getKey();
            }
        }


        System.out.println((node + 1) + "\t" + maps[node]);

        dominantColour[node] = maxCol;
    }

    public boolean isChildren(int node) {
        if (edjList[node].size() == 0) {
            return true;
        }
        return false;
    }


    public void printDominantColour() {
        for (long i : dominantColour) {
            System.out.print(i + " ");
        }
    }

    public void printGraph() {
        for (int i = 0; i < edjList.length; i++) {
            System.out.print(i + ": ");
            for (int j = 0; j < edjList[i].size(); j++) {
                System.out.print(edjList[i].get(j) + "\t");
            }
            System.out.println();
        }
    }
}


//index is from 0..n-1
class Node {
    int index;
    int colour;

    public Node() {
    }

    public String toString() {
        return "DNode(index=" + this.index + ", colour=" + this.colour + ")";
    }
}