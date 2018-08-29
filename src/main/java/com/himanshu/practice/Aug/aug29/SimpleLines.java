package com.himanshu.practice.Aug.aug29;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by himanshubhardwaj on 29/08/18.
 * Statement: https://codeforces.com/contest/665/problem/C
 * Alg: DP, Stack, Fastoutput
 * Submission: https://codeforces.com/contest/665/submission/42240323
 */
public class SimpleLines {
    public static void main(String[] args) throws IOException {
        //mySolution();
        greedySolution();
    }

    //greedy tok 171 ms
    private static void greedySolution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char str[] = br.readLine().toCharArray();
        int ch = str[0] - 'a';
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);


        for (int i = 1; i < str.length; i++) {
            if ((str[i] - 'a') == ch) {
                queue.add(i);
                continue;
            } else {
                changeGreedy(str, queue, i);
            }
            ch = str[i] - 'a';
            queue.add(i);
        }

        changeGreedy(str, queue, str.length);
        PrintWriter pr = new PrintWriter(System.out);

        for (char c : str) {
            pr.append(c);
        }
        pr.flush();
    }


    static void changeGreedy(char str[], Queue<Integer> queue, int i) {
        if (queue.size() <= 1) {
            queue.poll();
            return;
        }

        System.out.println(queue);

        int diffCh = -1;

        for (int j = 0; j < 26; j++) {
            if (j == (str[queue.peek()] - 'a')) {
                continue;
            }
            if (queue.peek() == 0 && i < str.length) {
                if ((str[i] - 'a') != j) {
                    diffCh = j;
                    break;
                }
            } else if (queue.peek() == 0 && i == str.length) {
                if (j != (str[queue.peek()] - 'a')) {
                    diffCh = j;
                    break;
                }
            } else if (queue.peek() != 0 && (i == str.length)) {
                if (j != (str[queue.peek() - 1] - 'a')) {
                    diffCh = j;
                    break;
                }
            } else {
                if (((str[i] - 'a') != j) && ((str[queue.peek() - 1] - 'a') != j)) {
                    diffCh = j;
                    break;
                }
            }
        }

        //System.out.println("Diff:\t" + diffCh + "\t" + str[i]);

        if (queue.size() % 2 == 1) {
            queue.poll();
        }

        while (!queue.isEmpty()) {
            str[queue.peek()] = (char) (diffCh + 'a');
            //System.out.println(((char) (diffCh + 'a')) + "\t" + queue.peek());
            queue.poll();
            if (!queue.isEmpty()) {
                queue.poll();
            }
        }

    }


    //took 810ms
    private static void mySolution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char str[] = br.readLine().toCharArray();
        int arr[][] = new int[str.length][26];


        for (int i = 0; i < str.length; i++) {
            for (int j = 0; j < 26; j++) {
                arr[i][j] = Integer.MAX_VALUE;
                if (i == 0) {
                    if (j == (str[i] - 'a')) {
                        arr[i][j] = 0;
                    } else {
                        arr[i][j] = 1;
                    }

                } else {
                    for (int k = 0; k < 26; k++) {
                        if (k != j && arr[i - 1][k] != Integer.MAX_VALUE) {
                            arr[i][j] = Math.min(arr[i][j], arr[i - 1][k] + ((j == (str[i] - 'a')) ? 0 : 1));
                        }
                    }
                }
            }
        }

        int min = Integer.MAX_VALUE;
        int ch = -1;
        String stri = "";

        for (int j = 0; j < 26; j++) {
            if (min > arr[str.length - 1][j]) {
                min = arr[str.length - 1][j];
                ch = -1;
            }
        }
//        System.out.println(min);

        Stack<Character> stack = new Stack<>();

        for (int i = str.length - 1; i >= 0; i--) {
            for (int j = 0; j < 26; j++) {
                if (min == arr[i][j] && (j != ch)) {
                    stack.push((char) ('a' + j));
                    //stri = (char) ('a' + j) + stri;
                    min = (j == (str[i] - 'a')) ? min : min - 1;
                    ch = j;
                    break;
                }
            }
        }

        PrintWriter pe = new PrintWriter(System.out);

        while (!stack.empty()) {
            pe.append(stack.pop());
        }
        pe.flush();
    }
}
