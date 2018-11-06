package com.himanshu.practice.nov.nov6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 06/11/18.
 * Algo: DP+Binary search
 * Submission: https://codeforces.com/contest/888/submission/45372773
 */
public class KDominantCharacter {
    static int[][] freq;
    static char[] str;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine().toCharArray();
        int n = str.length;
        freq = new int[str.length][26];

        freq[0][str[0] - 'a']++;


        for (int i = 1; i < str.length; i++) {
            for (int j = 0; j < 26; j++) {
                freq[i][j] = freq[i - 1][j];
            }
            freq[i][str[i] - 'a']++;
        }


        int ans = Integer.MAX_VALUE;

        int start = 1;
        int end = str.length;
        while (end >= start) {
            boolean result = false;


            if ((end - start) < 50) {
                for (int k = start; (!result) && (k <= end); k++) {
                    for (int i = 0; (!result) && (i < 26); i++) {
                        result = result || (isKDominantChar(i, k, str.length));
                    }
                    if (result) {
                        ans = Math.min(ans, k);
                    }
                }
                start = end+1;
            } else {
                int mid = (start + end) / 2;
                for (int i = 0; (!result) && (i < 26); i++) {
                    result = result || (isKDominantChar(i, mid, str.length));
                }
                if (result) {
                    ans = Math.min(ans, mid);
                    end = mid;
                } else {
                    start = mid;
                }
            }
        }


        System.out.print(ans);

    }


    static boolean isKDominantChar(int charIndex, int k, int n) {
        boolean result = true;

        for (int i = 0; result && ((i + k - 1) < n); i++) {
            int start = i;
            int end = i + k - 1;

            if (freq[end][charIndex] - freq[start][charIndex] + (((str[start] - 'a') == charIndex) ? 1 : 0) < 1) {
                result = false;
            }
        }


        return result;


    }
}
