package com.himanshu.practice.y2018.july.july9;

import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by Himanshu Bhardwaj on 09/07/18.
 * CodeForces: https://codeforces.com/problemset/problem/407/B
 * Algo: DP
 * Submission: https://codeforces.com/contest/407/submission/40096950
 */
public class LongPath {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        int arr[] = new int[n + 1];//1...n
        StringTokenizer st = new StringTokenizer(sc.nextLine(), " ");

        for (int i = 1; i <= n; i++) {
            String str = st.nextToken();
            arr[i] = Integer.parseInt(str);
        }

//        System.out.print(getMinBruteforce(1, arr));
//        System.out.println();
        System.out.print(getMinDP(arr));
    }

    //forget DP for a second
    //and this is our bruteforce
    private static long getMinBruteforce(int pos, int[] direction) {
        int n = direction.length - 1;
        long sum = 0;
        int[] numCross = new int[n + 1];


        int sumA[][] = new int[n + 2][2];
        boolean isSetSumA[][] = new boolean[n + 2][2];
        sumA[n][0] = 1;


        while (pos != (n + 1)) {
            if (isSetSumA[pos][numCross[pos] % 2]) {
                sum = (sum + sumA[pos][numCross[pos] % 2]) % 1000000007;
                break;
            } else {

            }
            numCross[pos] = (numCross[pos] + 1) % 1000000007;
            sum = (sum + 1) % 1000000007;

            System.out.println(sum);

            if ((numCross[pos] % 2) == 0) {
                pos++;
            } else {
                pos = direction[pos];
            }
        }
        return sum;
    }

    //DP
    private static long getMinDP(int[] direction) {
        int n = direction.length - 1;

        int dp[] = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            dp[i] = 1;
            for (int j = direction[i]; j < i; j++) {
                dp[i] = (dp[i] + (1 + dp[j])) % 1000000007;
            }
        }

        long sum = 0;
        for (int i = 1; i <= n; i++) {
            sum = (sum + dp[i] + 1) % 1000000007;
        }
        return sum;
    }
}
