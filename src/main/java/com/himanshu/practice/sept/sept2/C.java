package com.himanshu.practice.sept.sept2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * Created by himanshubhardwaj on 02/09/18.
 Statement: https://codeforces.com/contest/1037/problem/C
 Algo: DP
 Submission: https://codeforces.com/contest/1037/submission/42396432
 */

public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int dp[] = new int[n];
        String a = br.readLine();
        String b = br.readLine();


        dp[0] = (a.charAt(0) == b.charAt(0)) ? 0 : 1;

        int flag = -1;

        for (int i = 1; i < n; i++) {
            if (a.charAt(i) == b.charAt(i)) {
                dp[i] = dp[i - 1];
            } else {
                if (a.charAt(i - 1) == b.charAt(i - 1)) {
                    dp[i] = dp[i - 1] + 1;
                } else {
                    if (( (i-1) != flag) && ((int) a.charAt(i - 1) + (int) a.charAt(i)) == ((int) b.charAt(i - 1) + (int) b.charAt(i))) {
                        dp[i] = dp[i - 1];
                        flag = i;
                    } else {
                        dp[i] = dp[i - 1] + 1;
                    }
                }
            }
        }

        System.out.print(dp[n - 1]);
    }
}
