package com.himanshu.practice.y2019.june.june7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by himanshubhardwaj on 07/06/19.
 */
public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        int n = Integer.parseInt(br.readLine());
        int[] a = new int[n];
        int[] b = new int[n];

        PriorityQueue<Integer> weOwned = new PriorityQueue<>();
        int numberZeros = 0;


        String[] str = br.readLine().split(" ");


        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(str[i]);

            if (a[i] == 0) {
                numberZeros++;
            } else {
                weOwned.add(a[i]);
            }
        }

        Queue<Integer> queue = new LinkedList<>();

        str = br.readLine().split(" ");


        int onePos = -1;
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(str[i]);
            b[i] = num;
            queue.add(num);
            if (num == 1) {
                onePos = i;
            }
        }


        int tempOnePos = onePos;
        int lastDigit = -1;
        if (tempOnePos != -1) {
            for (int i = onePos + 1; i < n; i++) {
                if (b[i] != 0) {
                    if (b[i] > b[tempOnePos]) {
                        tempOnePos = i;
                        lastDigit = b[i];
                    } else {
                        tempOnePos = -1;
                        break;
                    }
                }
            }
        }

        int count = 0;
        int nextExpectedNumber = (tempOnePos != -1) ? lastDigit + 1 : 1;

        while (nextExpectedNumber <= n) {
            System.out.println(queue + "\t\t" + weOwned);
            count++;
            if (weOwned.isEmpty()) {
                queue.add(0);
                numberZeros--;
                if (queue.peek() == 0) {
                    numberZeros++;
                    queue.poll();
                } else {
                    weOwned.add(queue.poll());
                }
            } else if (weOwned.peek() == nextExpectedNumber) {
                queue.add(weOwned.poll());
                nextExpectedNumber++;
            } else {
                queue.add(0);
                numberZeros--;
                if (queue.peek() == 0) {
                    numberZeros++;
                    queue.poll();
                } else {
                    weOwned.add(queue.poll());
                }
            }


        }

        System.out.println(count);
        System.out.println(queue);


    }
}
