package com.himanshu.practice.y2018.Aug.aug14;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;


/**
 * Created by himanshubhardwaj on 14/08/18.
 * Grab Test
 */
public class B {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int A[] = new int[20];
        A[0] = 1;
        A[1] = 2;
        A[2] = 3;
        A[3] = 15;
        A[4] = 16;
        A[5] = 17;
        A[6] = 18;
        A[7] = 19;
        A[8] = 21;
        A[9] = 22;
        A[10] = 23;
        A[11] = 24;
        A[12] = 25;
        A[13] = 26;
        A[14] = 27;
        A[15] = 28;
        A[16] = 29;
        A[17] = 30;
        A[18] = 4;
        A[19] = 10;

        Arrays.sort(A);



        Solution s = new Solution();

        System.out.println(s.solution(A));
    }
}

class Solution {
    private int visit[] = new int[31];
    private int[] A;

    public int solution(int[] A) {
        // write your code in Java SE 8

        if (A.length == 0) {
            return 0;
        }

        this.A = A;

        for (int visitDay : A) {
            visit[visitDay] = 1;
        }


        int cost = getMincost(); //cost consisting only of one day and weekly tickets

        if (cost > 25) {
            return 25; //because int hat case person would prefer to buy monthly ticket
        } else {
            return cost;
        }
    }

    private int getMincost() {
        int DP[][] = new int[31][2];

        for (int i = 0; i < 31; i++) {
            DP[i][0] = Integer.MAX_VALUE;
            DP[i][1] = Integer.MAX_VALUE;
        }

        for (int i = (A.length - 1); i >= 0; i--) {
            DP[A[i]][0] = 2 + ((getNextVisitDay(A[i], 1) == -1) ? 0 : (Math.min(DP[getNextVisitDay(A[i], 1)][0], DP[getNextVisitDay(A[i], 1)][1])));
            DP[A[i]][1] = 7 + ((getNextVisitDay(A[i], 7) == -1) ? 0 : (Math.min(DP[getNextVisitDay(A[i], 7)][0], DP[getNextVisitDay(A[i], 7)][1])));
        }

        return Math.min(DP[A[0]][0], DP[A[0]][1]);
    }

    public int getNextVisitDay(int currentDay, int offset) {
        if ((currentDay + offset) > A[A.length - 1]) {
            return -1;
        } else {
            int nextDay = currentDay + offset;
            for (int i = nextDay; i <= 30; i++) {
                if (visit[i] == 1) {
                    return i;
                }
            }
        }

        //won't reach till here
        return -1;
    }


}
