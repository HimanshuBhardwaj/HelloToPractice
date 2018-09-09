package com.himanshu.practice.sept.sept9.jiva;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by himanshubhardwaj on 09/09/18.
 */
public class FindTheNext {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");
        PrintWriter pr = new PrintWriter(System.out);
        int n = Integer.parseInt(str[0]);
        int q = Integer.parseInt(str[1]);
        int arr[] = new int[n];
        int next[] = new int[n];


        str = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(str[i]);
        }

        Arrays.sort(arr);

        next[n - 1] = arr[n - 1] + 1;

        for (int i = n - 2; i >= 0; i--) {
            if ((arr[i + 1] - arr[i]) <= 1) {
                next[i] = next[i + 1];
            } else {
                next[i] = arr[i] + 1;
            }
        }

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            map.put(arr[i], next[i]);
        }

        for (int i = 0; i < q; i++) {
            int x = Integer.parseInt(br.readLine());

            if (!map.containsKey(x + 1)) {
                pr.append(String.valueOf(x + 1));
            } else {
                pr.append(String.valueOf(map.get(x + 1)));
            }
            pr.append("\n");
        }

        pr.flush();
        pr.close();
        br.close();
    }
}
