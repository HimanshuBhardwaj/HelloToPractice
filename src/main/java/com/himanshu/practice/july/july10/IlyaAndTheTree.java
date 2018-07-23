package com.himanshu.practice.july.july10;

import java.io.*;
import java.util.*;

/**
 * Created by Himanshu Bhardwaj on 10/07/18.
 * TODO: Not working
 */
public class IlyaAndTheTree {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //int n = Integer.parseInt(br.readLine());
        int n = 200000;
        int a[] = new int[n + 1];

        //StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int pos = 1;
        while (pos<=n) {
            //a[pos] = Integer.parseInt(st.nextToken());
            a[pos] = 196560;
            pos++;
        }

        Tree tree = new Tree(n, a);

        String[] stings = null;
        for (int i = 1; i <= (n - 1); i++) {
            //String str = br.readLine();
            //stings = str.split(" ");
            //int source = Integer.parseInt(stings[0]);
            //int destination = Integer.parseInt(stings[1]);
            int source = i;
            int destination = i + 1;
            tree.insert(source, destination);
        }


        //thatsPrePRocesssing
        //tree.getMAxGCDBrute(1, 0);


        HashMap<Integer, Value> map = new HashMap<>();

        for (int key : tree.getAllDivisors(tree.a[1], null)) {
            map.put(key, new Value(0));
        }
        tree.setMap(map);

        long startTime = System.currentTimeMillis();
        tree.maximumGCDFunc(1, 0, 0);
        long end = System.currentTimeMillis();

        if (n != 200000) {
            tree.printResult();
        } else {
            System.out.print(end - startTime);
        }

//        System.out.println();
//        tree.printGraph();


// TODO: Correct it and make it run later
// int maxNumber[] = new int[n + 1];
//        maxNumber[1] = a[1];
//
//
//        tree.getMAxNumber(1, 0, 0, 0, maxNumber, a);
//
//        for (int i = 1; i < a.length; i++) {
//            System.out.print(maxNumber[i] + " ");
//        }
    }
}


class Tree {
    ArrayList<Integer>[] adjList;
    int numNodes; //1...n
    int[] maximumGCD; //1...i
    int[] a;
    HashMap<Integer, Value> map = null;


    public static void setDivisorsList() {

    }

    public Tree(int numNodes, int[] arr) {
        this.numNodes = numNodes;
        adjList = new ArrayList[numNodes + 1];

        for (int i = 0; i <= numNodes; i++) {
            adjList[i] = new ArrayList<>();
        }
        maximumGCD = new int[numNodes + 1];
        this.a = arr;
    }

    public void insert(int source, int destination) {
        adjList[source].add(destination);
    }


    public List<Integer> getAllDivisors(int n, Set<Integer> intersectionSet) {
        List list = new ArrayList<>();

        if (intersectionSet != null) {
            for (int i : intersectionSet) {
                if (n % i == 0) {
                    list.add(i);
                }
            }
        } else {
            for (int i = 2; i <= n / 2; i++) {
                if ((n % i) == 0) {
                    list.add(i);
                }
            }
            list.add(n);
        }
        return list;
    }


    //root not included
    public void getMAxGCDBrute(int node, int commulativeGCD) {
        if (node == 1) {
            maximumGCD[node] = 0;
        } else {
            maximumGCD[node] = gcd(commulativeGCD, a[node]);
        }
        for (int i = 0; i < adjList[node].size(); i++) {
            getMAxGCDBrute(adjList[node].get(i), maximumGCD[node]);
        }
    }


    //not working
    //TODO: Make it run
    public void getMAxNumber(int node, int maxExcluding, int maxIncluding, int maxIncludingPPRevious,
                             int[] maxNumber, int[] a) {
        int newMaxExcluding = -1;
        int newMaxIncluding = 1;
        if (node != 1) {
            newMaxExcluding = Math.max(Math.max(maxIncluding, gcd(maxExcluding, a[node])), gcd(maxIncludingPPRevious, a[node]));
            newMaxIncluding = gcd(maxIncluding, a[node]);
        } else {
            newMaxExcluding = 0;
            newMaxIncluding = a[node];
        }
        maxNumber[node] = Math.max(newMaxIncluding, newMaxExcluding);
//        System.out.println(node + ", " + newMaxExcluding + ", " + newMaxIncluding);

        for (int i = 0; i < adjList[node].size(); i++) {
            getMAxNumber(adjList[node].get(i), newMaxExcluding, newMaxIncluding, maxIncluding, maxNumber, a);
        }
    }

    public static int gcd(int a, int b) {
        if (a * b == 0) {
            return (a == 0) ? b : a;
        }
        return (a > b) ? gcd(a % b, b) : gcd(b % a, a);
    }

    public void maximumGCDFunc(int node, int depth, int commGCD) {
//        System.out.println(map.size());

        if (node == 1) {
            maximumGCD[1] = a[1];
            for (int i = 0; i < adjList[node].size(); i++) {
                maximumGCDFunc(adjList[node].get(i), depth + 1, commGCD);
            }
        } else {

            for (Map.Entry<Integer, Value> entry : map.entrySet()) {
                if (a[node] % entry.getKey() == 0) {
                    //System.out.println("Coming here:\t" + node);
                    entry.getValue().inc();
                }
            }

            int temp = gcd(commGCD, a[node]);
            maximumGCD[node] = temp;
            //System.out.println(maximumGCD[node] + "..." + commGCD + "..." + a[node]);
            for (Map.Entry<Integer, Value> entry : map.entrySet()) {
                if (entry.getValue().value == (depth - 1)) {
                    maximumGCD[node] = Math.max(maximumGCD[node], entry.getKey());
                }
            }

            for (int i = 0; i < adjList[node].size(); i++) {
                maximumGCDFunc(adjList[node].get(i), depth + 1, temp);
            }

            for (Map.Entry<Integer, Value> entry : map.entrySet()) {
                if (a[node] % entry.getKey() == 0) {
                    //System.out.println("Coming here Also:\t" + node);
                    entry.getValue().dec();
                }
            }
        }
    }


    void printResult() throws IOException {
        BufferedWriter br = new BufferedWriter(new OutputStreamWriter(System.out));


        for (int i = 1; i < maximumGCD.length; i++) {
            br.write(maximumGCD[i] + " ");
        }
        br.flush();

    }

    void printGraph() {
        for (int i = 1; i < adjList.length; i++) {
            System.out.print(i + ": ");
            for (int j = 0; j < adjList[i].size(); j++) {
                System.out.print(adjList[i].get(j) + ",");
            }
            System.out.println();
        }
    }

    public void setMap(HashMap<Integer, Value> map) {
        this.map = map;
    }
}


class Value {
    int value;

    public void inc() {
        value++;
    }

    public void dec() {
        value++;
    }

    @java.beans.ConstructorProperties({"value"})
    public Value(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Value)) return false;
        final Value other = (Value) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getValue() != other.getValue()) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getValue();
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof Value;
    }
}
