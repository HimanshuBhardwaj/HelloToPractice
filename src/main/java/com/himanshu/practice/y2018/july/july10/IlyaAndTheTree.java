package com.himanshu.practice.y2018.july.july10;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;


/**
 * Created by himanshubhardwaj on 10/07/18.
 * Problem statement: https://codeforces.com/contest/842/problem/C
 * Could not get an AC :-(
 */
public class IlyaAndTheTree {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long a[] = new long[n];
        String st[] = br.readLine().split(" ");


        for (int i = 0; i < st.length; i++) {
            a[i] = Long.parseLong(st[i]);
        }
        Tree tree = new Tree(n, a);

        //Inserting nodes in tree
        String[] stings = null;
        for (int i = 1; i <= (n - 1); i++) {
            String str = br.readLine();
            stings = str.split(" ");
            int source = Integer.parseInt(stings[0]) - 1;
            int destination = Integer.parseInt(stings[1]) - 1;
            tree.insert(source, destination);
        }


        tree.maximumGCDEditorialSolution();
//        System.out.println("");
//        tree.maximumGCDMySolution(0, 0, 0, 0);
//        tree.printGraph();
//        tree.print();

    }
}


class Tree {
    ArrayList<Integer>[] adjList;
    long number[];
    int n;
    long maximum[];
    long mySolutionWithoutRoot[];
    long mySolutionwithRoot[];
    TreeSet<Long> factorsOfRoot;
    long[] factors;
    int[] frequency;

    public Tree(int n, long[] a) {
        this.n = n;
        this.number = a;
        adjList = new ArrayList[n];
        mySolutionWithoutRoot = new long[n];
        mySolutionwithRoot = new long[n];
        maximum = new long[n];

        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<>();
            mySolutionWithoutRoot[i] = 1l;
            mySolutionwithRoot[i] = 1l;
            maximum[i] = 1l;
        }
    }

    void insert(int source, int destination) {
        adjList[source].add(destination);
    }

    void maximumGCDMySolution(int index, long gcdMaximumIncludingParent, long gcdMaximumExcludingParent, long continourGCD) {
        if (index == 0) {
            maximum[0] = number[0];
            for (int neightbour : adjList[index]) {
                maximumGCDMySolution(neightbour, number[0], 0, number[0]);
            }
        } else {

            maximum[index] = Math.max(maximum[index], gcd(number[index], gcdMaximumExcludingParent));
            maximum[index] = Math.max(maximum[index], gcd(number[index], gcdMaximumIncludingParent));
            long x = maximum[index];
            maximum[index] = Math.max(maximum[index], continourGCD);

            for (int neightbour : adjList[index]) {
                maximumGCDMySolution(neightbour, x, continourGCD, gcd(continourGCD, number[index]));
            }
        }
    }


    void maximumGCDEditorialSolution() {
        //System.out.println("@maximumGCDEditorialSolution");
        computeMaximumwithoutRoot(0, 0);
        computeFactorsMap(number[0]);
        System.out.println(factorsOfRoot);
        int s = factorsOfRoot.size();
        factors = new long[s];
        int pos = 0;
        for (long x : factorsOfRoot) {
            factors[pos] = x;
            pos++;

        }
        Arrays.sort(factors);
        frequency = new int[factors.length];


        computeMaximumwithRoot(0, 1);
        PrintWriter pr = new PrintWriter(System.out);


        //System.out.println(mySolutionwithRoot.length);
        for (int i = 0; i < mySolutionwithRoot.length; i++) {
            if (mySolutionWithoutRoot[i] <= 0 || mySolutionwithRoot[i] <= 0) {
                throw new RuntimeException("negative number" +
                        ((mySolutionWithoutRoot[i] <= 0) ? ("mySolutionWithoutRoot: " + mySolutionWithoutRoot[i]) : "mySolutionwithRoot: " + mySolutionwithRoot[i]) + "\t" + i + "Number: " + number[i] +
                        "\t GCD" + gcd(0, number[i]));
            }
            long n = Math.max(mySolutionWithoutRoot[i], mySolutionwithRoot[i]);
            pr.append(String.valueOf(n));
            pr.append(" ");
        }
        pr.flush();
        pr.close();
    }

    private void computeMaximumwithRoot(int node, int depth) {
        //System.out.println("LNode: " + node + "\tdepth: " + depth + "\t factoMap: " + factorsOfRoot + "\tFactors: " + factors + "\tadjList: " + adjList[node]);

        for (int i = 0; i < factors.length; i++) {
            if (number[node] % factors[i] == 0) {
                frequency[i]++;
            }
        }


        for (int i = 0; i < factors.length; i++) {
            if (frequency[i] >= (depth - 1)) {
                mySolutionwithRoot[node] = Math.max(mySolutionwithRoot[node], factors[i]);
            }
        }

        for (int neighboours : adjList[node]) {
            computeMaximumwithRoot(neighboours, depth + 1);
        }

        for (int i = 0; i < factors.length; i++) {
            if (number[node] % factors[i] == 0) {
                frequency[i]--;
            }
        }
    }


    private void computeFactorsMap(long number) {
        int sqrt = (int) Math.ceil(Math.sqrt(number));
        factorsOfRoot = new TreeSet<>();
        for (long i = 1; i <= sqrt; i++) {
            long fac = number / i;
            if ((fac * i) == number) {
                factorsOfRoot.add(i);
                factorsOfRoot.add(fac);
            }
        }
    }


    private void computeMaximumwithoutRoot(int index, long gcd) {
        if (index == 0) {
            mySolutionWithoutRoot[index] = number[index];
            for (int neighbour : adjList[index]) {
                computeMaximumwithoutRoot(neighbour, 0);
            }
        } else {
            mySolutionWithoutRoot[index] = gcd(gcd, number[index]);
            for (int neighbour : adjList[index]) {
                computeMaximumwithoutRoot(neighbour, mySolutionWithoutRoot[index]);
            }
        }
    }


    //assume a>=b
    public static long gcd(long a, long b) {
        if (a < b) {
            return gcd(b, a);
        }
        if (b == 1) {
            return 1l;
        }
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }


    void printGraph() {
        for (int i = 0; i < adjList.length; i++) {
            System.out.print(i + ": ");
            for (int j = 0; j < adjList[i].size(); j++) {
                System.out.print(adjList[i].get(j) + ",");
            }
            System.out.println();
        }
    }

    void print() {
        PrintWriter pr = new PrintWriter(System.out);
        for (long x : maximum) {
            pr.append(String.valueOf(x));
            pr.append(" ");
        }
        pr.flush();
        pr.close();
    }
}
