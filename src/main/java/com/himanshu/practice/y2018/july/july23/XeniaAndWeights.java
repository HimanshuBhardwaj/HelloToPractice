package com.himanshu.practice.y2018.july.july23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.TreeSet;

/**
 * Created by himanshubhardwaj on 23/07/18.
 * Problem Set: https://codeforces.com/contest/339/problem/C
 * Algo: DFS, DP
 * Submission: https://codeforces.com/contest/339/submission/40668499
 */
public class XeniaAndWeights {
    static TreeSet<String> DP = new TreeSet<>();
    static int m;
    static boolean hasPrinted = false;
    static ArrayList<Integer> weights = new ArrayList<>();


    public static void main(String[] args) throws IOException {
        //greedy();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] sWeights = br.readLine().toCharArray();


        for (int i = 0; i < sWeights.length; i++) {
            if (sWeights[i] == '1')
                weights.add(i + 1);
        }

        m = Integer.parseInt(br.readLine());

        graphSolution(0, 0, 1, new LinkedList<Integer>());

        if (!hasPrinted) {
            System.out.print("NO");
        }


    }


    static void graphSolution(int presentBlance, int previousWeight, int state, LinkedList<Integer> path) {
        if (DP.contains(mapState(presentBlance, previousWeight, state)) || hasPrinted) {
            return;
        }

        DP.add(mapState(presentBlance, previousWeight, state));
        if (state == (m + 1)) {
            hasPrinted = true;
            printPAth(path);
        }

        for (int w : weights) {
            if (w != previousWeight) {
                if (presentBlance > 0) {
                    if ((presentBlance - w) < 0) {
                        path.addLast(w);
                        graphSolution(presentBlance - w, w, state + 1, path);
                        path.removeLast();
                    }
                } else {
                    if (presentBlance + w > 0) {
                        path.addLast(w);
                        graphSolution(presentBlance + w, w, state + 1, path);
                        path.removeLast();
                    }
                }
            }
        }


    }

    private static void printPAth(LinkedList<Integer> path) {
        System.out.println("YES");
        for (int x : path) {
            System.out.print(x + " ");
        }
    }

    private static String mapState(int presentBlance, int previousWeight, int state) {
        return String.valueOf(presentBlance) + "|" + previousWeight + "|" + state;
    }


    //not giving correct result
    static void greedy() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] sWeights = br.readLine().toCharArray();
        ArrayList<Integer> weights = new ArrayList<>();


        for (int i = 0; i < sWeights.length; i++) {
            if (sWeights[i] == '1')
                weights.add(i + 1);
        }

        int m = Integer.parseInt(br.readLine());

        if (weights.size() == 0) {
            System.out.println("NO");
            return;
        }


        Collections.sort(weights);


        int printed = 0;


        for (int ii = 0; ii < weights.size() && printed == 0; ii++) {
            boolean flag = true;
            LinkedList<Integer> list = new LinkedList<>();

            int scales[] = new int[2];
            int previousWeight = weights.get(ii);
            scales[0] = weights.get(ii);
            list.addLast(previousWeight);

            for (int i = 1; i < m; i++) {
                int put = 0;
                int minWeight = -1;
                int minDiff = Integer.MAX_VALUE;
                for (int w : weights) {
                    if (w != previousWeight && (scales[i % 2] + w) > scales[(i - 1) % 2]) {
                        if ((scales[i % 2] + w - scales[(i - 1) % 2]) < minDiff) {
                            minDiff = scales[i % 2] + w - scales[(i - 1) % 2];
                            minWeight = w;
                        }
                    }
                }
                //  System.out.println(previousWeight + "\t" + minWeight + "\t" + scales[i % 2] + "\t" + scales[(i - 1) % 2]);
                if (minDiff == Integer.MAX_VALUE) {
                    flag = false;
                    break;
                }
                previousWeight = minWeight;
                list.addLast(previousWeight);
                scales[i % 2] += previousWeight;
            }


            if (flag) {
                System.out.println("YES");
                printed = 1;
                for (int x : list) {
                    System.out.print(x + " ");
                }
            }
        }


        if (printed == 0) {
            System.out.println("NO");
        }


    }
}
