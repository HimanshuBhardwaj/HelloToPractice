package com.himanshu.practice.Aug.aug26.codejam;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by himanshubhardwaj on 26/08/18.
 */
public class C {

    static String output[];
    static int total = 0;
    static int win = 0;

    public static void main(String[] args) throws IOException {
        readFile("/Users/himanshubhardwaj/Desktop/apac/c/C-small-attempt0.in");
        writeFile("/Users/himanshubhardwaj/Desktop/apac/c/hello.output");
    }


    static void readFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));

        int t = Integer.parseInt(br.readLine());
        output = new String[t];


        for (int j = 0; j < t; j++) {
            int n = Integer.parseInt(br.readLine());
            int a[] = new int[3 * n];
            int b[] = new int[3 * n];
            String str[] = br.readLine().split(" ");

            for (int i = 0; i < str.length; i++) {
                a[i] = Integer.parseInt(str[i]);
            }

            str = br.readLine().split(" ");

            for (int i = 0; i < str.length; i++) {
                b[i] = Integer.parseInt(str[i]);
            }
            output[j] = "Case #" + (j + 1) + ": " + getProbablity(a, b, n) + "\n";
            System.out.print(output[j]);
        }


        br.close();
    }

    private static double getProbablity(int[] a, int[] b, int n) {
        sortDesc(a);
        int[] subA = new int[2 * n];
        long sum = 0l;
        long diff = 0l;
        long totalSum = 0l;

        for (int x : a) {
            totalSum += x;
        }

        for (int i = 0; i < (2 * n); i++) {
            sum += a[i];
            subA[i] = a[i];
        }
        diff = findMin(subA, subA.length);

        long portion1 = (sum + diff) / 2;
        long portion2 = (sum - diff) / 2;
        long portion3 = totalSum - portion1 - portion2;

        long sumA[] = new long[3];
        long sumB[] = new long[3];

        sumA[0] = portion1;
        sumA[1] = portion2;
        sumA[2] = portion3;

        for (int i = 0; i < n; i++) {
            sumB[0] += b[i];
            sumB[1] += b[i + n];
            sumB[2] += b[i + n + n];
        }

        findPermutations(sumA, sumB, 0, 3);

        double probablity = (double) win / (double) total;
        System.out.println(win + "\t" + total + "\t" + (probablity));
        win = 0;
        total = 0;
        return probablity;
    }


    static void findPermutations(long strA[], long[] strB, int index, int n) {
        if (index >= n) {
            total++;
            win += (isWining(strA, strB) ? 1 : 0);
            return;
        }

        for (int i = index; i < n; i++) {
            boolean check = shouldSwap(strA, index, i);
            if (check) {
                swap(strA, index, i);
                findPermutations(strA, strB, index + 1, n);
                swap(strA, index, i);
            }
        }
    }

    private static void swap(long[] str, int index, int i) {
        long temp = str[i];
        str[i] = str[index];
        str[index] = temp;
    }

    private static boolean isWining(long[] strA, long[] strB) {
        int count = 0;
        if (strA.length != strB.length || (strA.length != 3)) {
            throw new RuntimeException("Smomething wrong");
        }

        for (int i = 0; i < strB.length; i++) {
            if (strA[i] > strB[i]) {
                count++;
            }
        }

        if (count >= 2) {
            return true;
        } else {
            return false;
        }
    }


    static boolean shouldSwap(long str[], int start, int curr) {
        for (int i = start; i < curr; i++) {
            if (str[i] == str[curr]) {
                return false;
            }
        }
        return true;
    }

    private static void sortDesc(int[] a) {
        ArrayList<Integer> list = new ArrayList<>(a.length);
        for (int i = 0; i < a.length; i++) {
            list.add(i, a[i]);
        }
        Collections.sort(list, Collections.<Integer>reverseOrder());
        for (int i = 0; i < a.length; i++) {
            a[i] = list.get(i);
        }
    }

    static void writeFile(String fileName) throws IOException {
        BufferedWriter br = new BufferedWriter(new FileWriter(fileName));

        for (int i = 0; i < output.length; i++) {
            br.write(output[i]);
        }
        br.flush();
        br.close();

    }

    static int findMin(int arr[], int n) {
        // Calculate sum of all elements
        int sum = 0;
        for (int i = 0; i < n; i++)
            sum += arr[i];

        // Create an array to store
        // results of subproblems
        boolean dp[][] = new boolean[n + 1][sum + 1];

        // Initialize first column as true.
        // 0 sum is possible  with all elements.
        for (int i = 0; i <= n; i++)
            dp[i][0] = true;

        // Initialize top row, except dp[0][0],
        // as false. With 0 elements, no other
        // sum except 0 is possible
        for (int i = 1; i <= sum; i++)
            dp[0][i] = false;

        // Fill the partition table
        // in bottom up manner
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                // If i'th element is excluded
                dp[i][j] = dp[i - 1][j];

                // If i'th element is included
                if (arr[i - 1] <= j)
                    dp[i][j] |= dp[i - 1][j - arr[i - 1]];
            }
        }

        // Initialize difference of two sums.
        int diff = Integer.MAX_VALUE;

        // Find the largest j such that dp[n][j]
        // is true where j loops from sum/2 t0 0
        for (int j = sum / 2; j >= 0; j--) {
            // Find the
            if (dp[n][j] == true) {
                diff = sum - 2 * j;
                break;
            }
        }
        return diff;
    }
}
