//package com.himanshu.practice.Aug.Aug15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by himanshubhardwaj on 16/08/18.
 * Problem statement: https://codeforces.com/contest/1020/problem/C
 * Algo: Ad-hoc, BruteForce
 * Submission: https://codeforces.com/contest/1020/problem/C
 * Though this is nmlongn, but actual nlongn solution exists and that solution I will have to implement for https://codeforces.com/contest/458/problem/C problem
 */
public class Elections {
    static ArrayList<Integer>[] partyVoter;
    static int[] numVoters;
    static int[] ar;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");

        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);

        partyVoter = new ArrayList[m];
        numVoters = new int[m];

        for (int i = 0; i < m; i++) {
            partyVoter[i] = new ArrayList<>();
        }


        for (int i = 0; i < n; i++) {
            str = br.readLine().split(" ");
            partyVoter[Integer.parseInt(str[0]) - 1].add(Integer.parseInt(str[1]));
        }


        ar = new int[m];
        for (int i = 0; i < partyVoter.length; i++) {
            numVoters[i] = partyVoter[i].size();
            Collections.sort(partyVoter[i], Collections.<Integer>reverseOrder());
        }


        long cost = Long.MAX_VALUE;
        //for (int i = Math.min((int) Math.floor(((double) n / (double) m)), partyVoter[0].size()); i <= n; i++) {
        for (int i = 1; i <= n; i++) {
            cost = Math.min(cost, winElection(i));
        }


        System.out.print(cost);
    }

    //you have to win elections with this number of votes
    private static long winElection(int numVotes) {
        int votesWehave = numVoters[0];
        int[] voterPattern = getClone();
        long cost = 0;

        int votesWeneeded = numVotes - votesWehave;
        for (int i = 1; (i < voterPattern.length) && (votesWeneeded >= 0); i++) {
            if (numVotes <= voterPattern[i]) {
                for (int j = numVotes - 1; j < voterPattern[i]; j++) {
                    votesWeneeded--;
                    cost += partyVoter[i].get(j);
                }
            }
        }

        if (votesWeneeded == 0) {
            return cost;
        }

        if (votesWeneeded < 0) {
            return Long.MAX_VALUE;
        }

        List<Integer> voterCost = new ArrayList<>();

        for (int i = 1; i < voterPattern.length; i++) {
            for (int j = 0; j < Math.min(numVotes - 1, partyVoter[i].size()); j++) {
                voterCost.add(partyVoter[i].get(j));
            }
        }

        Collections.sort(voterCost);

        for (int i = 0; i < votesWeneeded; i++) {
            cost += voterCost.get(i);
        }


        return cost;
    }

    private static int[] getClone() {
        for (int i = 0; i < ar.length; i++) {
            ar[i] = numVoters[i];
        }

        return ar;
    }
}
