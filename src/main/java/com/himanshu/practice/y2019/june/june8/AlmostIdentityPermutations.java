package com.himanshu.practice.y2019.june.june8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 08/06/19.
 * Statement: https://codeforces.com/contest/888/problem/D
 * Algo: MAth
 * Submission: https://codeforces.com/contest/888/submission/55293635
 */
public class AlmostIdentityPermutations {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");

        int n = Integer.parseInt(str[0]);
        int k = Integer.parseInt(str[1]);

        long answer = 0l;

        for (int i = 0; i <= k; i++) {
            if (i==0) {
                answer += choose(n,i);
            } else if (i==1) {
                continue;
            } else if (i==2) {
                answer += choose(n,i);
            } else if (i==3) {
                answer += (choose(n,i)*2);
            } else if (i==4) {
                answer += (choose(n,i)*9);
            }
        }

        System.out.print(answer);


    }

//k is very small
    static long choose(int n, int k) {
        long answer = 1;

        if (k == 0) {
            return 1;
        }


        for (int i = (n-k)+1; i <= n; i++) {
            answer *= i;
        }

        for (int i = 2; i <= k; i++) {
            answer /= i;
        }

        return answer;
    }

}
