package com.himanshu.practice.y2019.April.april16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeSet;

/**
 * Created by himanshubhardwaj on 16/04/19.
 * 8:35 --> 11 pm
 * Beautiful
 * queue
 */
public class MasaoudLOVESPIZZAS {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());



        while (t-- > 0) {
            String str[] = br.readLine().split(" ");
            int n = Integer.parseInt(str[0]);
            int x = Integer.parseInt(str[1]);
            long arr[] = new long[n];
            str = br.readLine().split(" ");
            long sum = (long) 0;
            Queue<Long> queue = new LinkedList<>();
            long count = 0;

            for (int i = 0; i < n; i++) {
                arr[i] = Long.parseLong(str[i]);
            }

            for (int i = 0; i < n; i++) {
                if (sum + arr[i] < x) {
                    sum += arr[i];
                    queue.add(arr[i]);
                } else {
                    while (((arr[i] + sum >= x) && (!queue.isEmpty()))) {
                        count += queue.size();
                        sum -= queue.poll();
                    }

                    if ((arr[i] + sum) < x) {
                        sum += arr[i];
                        queue.add(arr[i]);
                    }
                }
            }


            //System.out.println(count + "\t" + queue);

            //System.out.println("Exit loop");

            count += ((long) queue.size() *  (long) (queue.size() + 1)) / 2;
            System.out.println(count);
        }

    }
}
