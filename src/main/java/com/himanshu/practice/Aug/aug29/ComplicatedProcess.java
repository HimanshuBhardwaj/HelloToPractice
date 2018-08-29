package com.himanshu.practice.Aug.aug29;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by himanshubhardwaj on 29/08/18.
 * Statement: https://codeforces.com/contest/660/problem/C
 * Algo: two pointer
 * Submission: https://codeforces.com/contest/660/submission/42237689
 */
public class ComplicatedProcess {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int k = Integer.parseInt(str[1]);

        str = br.readLine().split(" ");
        int count = 0;


        Queue<String> queue = new LinkedList<>();

        int pos = -1;
        int numZero = 0;

        for (int i = 0; i < str.length; i++) {
            if (str[i].equals("1")) {
                queue.add("1");
                pos = (count < queue.size()) ? i : pos;
                count = (count < queue.size()) ? queue.size() : count;
            } else {
                while (numZero >= k && !queue.isEmpty()) {
                    String top = queue.poll();
                    if (top.equals("0")) {
                        numZero--;
                    }
                }
                if (numZero < k) {
                    numZero++;
                    queue.add("0");
                    pos = (count < queue.size()) ? i : pos;
                    count = (count < queue.size()) ? queue.size() : count;
                }
            }
        }

        while (pos >= 0 && k > 0) {
            if (str[pos].equals("0")) {
                k--;
                str[pos] = "1";
            }
            pos--;
        }


        PrintWriter pr = new PrintWriter(System.out);
        System.out.println(count);
        for (String x : str) {
            pr.append(x);
            pr.append(" ");
        }
        pr.flush();
        pr.close();
    }
}
