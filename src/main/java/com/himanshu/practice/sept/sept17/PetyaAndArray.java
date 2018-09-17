package com.himanshu.practice.sept.sept17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by himanshubhardwaj on 17/09/18.
 * Statement: https://codeforces.com/contest/1042/problem/D
 * Algo:
 *  1. Sqrt Decomposition
 *  2.
 */
public class PetyaAndArray {
    public static void main(String[] args) throws IOException {
        SqrtDecompositionSolution.solve();

//        ArrayList<Long> list = new ArrayList<>();
//        list.add(7l);
//        list.add(10l);
//        System.out.println(SqrtDecompositionSolution.countElement(0,list.size()-1,8,list));

    }
}


//Sqrt root  decomposition solution
//Time complexity: n^(1.5)logn
//Extra Space: O(n)
//AC
class SqrtDecompositionSolution {
    static ArrayList<Long>[] sqrt;
    static long pref[];
    static int sqrtN;


    static void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        long t = Long.parseLong(str[1]);

        int arr[] = new int[n];
        str = br.readLine().split(" ");
        pref = new long[n];


        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(str[i]);
            pref[i] = (i == 0) ? arr[i] : (arr[i] + pref[i - 1]);//prefix sum
        }


        sqrtN = (int) Math.sqrt(n);
        sqrt = new ArrayList[(int) Math.ceil(n / (double) sqrtN)];

        for (int i = 0; i < sqrt.length; i++) {
            sqrt[i] = new ArrayList<>(sqrtN);
        }

        for (int i = 0; i < n; i++) {
            sqrt[i / sqrtN].add(pref[i]);
        }


        for (int i = 0; i < sqrt.length; i++) {
            Collections.sort(sqrt[i]);
        }

        long count = 0;
        for (int i = 0; i < n; i++) {
            count += getNumElementsSqrtDecomposition(i, (i == 0) ? t : (t + pref[i - 1]));
        }
        System.out.print(count);
    }

    private static long getNumElementsBruteForce(int pos, long k) {
        long count = 0;
        for (int i = pos; i < pref.length; i++) {
            if (pref[i] < k) {
                count++;
            }
            System.out.print(pref[i] + ",");
        }

        System.out.println("\tBruteForce:\t" + pos + "\t" + k + "\t" + count);
        return count;
    }

    private static long getNumElementsSqrtDecomposition(int pos, long k) {
        int currentBucket = pos / sqrtN;
        long count = 0;

        for (int i = pos; i < Math.min(pref.length, sqrtN * (currentBucket + 1)); i++) {
            if (pref[i] < k) {
                count++;
            }
        }

        for (int i = currentBucket + 1; i < sqrt.length; i++) {
            count += countElement(0, sqrt[i].size() - 1, k, sqrt[i]);
        }

        return count;
    }

    //this will return number of elements from start to end are less than k
    static public int countElement(int start, int end, long k, ArrayList<Long> list) {
        if (start > end || start < 0 || end >= list.size() || list.get(start) >= k) {
            return 0;
        }

        if (list.get(end) < k) {
            return end - start + 1;
        }

        int mid = (end + start) / 2;

        if (list.get(mid) < k) {
            return mid - start + 1 + countElement(mid + 1, end, k, list);
        } else {
            return countElement(start, mid - 1, k, list);
        }
    }
}


