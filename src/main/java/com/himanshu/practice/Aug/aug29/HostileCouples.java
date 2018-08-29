package com.himanshu.practice.Aug.aug29;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by himanshubhardwaj on 29/08/18.
 * TODO: https://codeforces.com/contest/652/problem/C
 TODO:  Complete it
 */
public class HostileCouples {
    static Pair dummy = new Pair();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);
        str = br.readLine().split(" ");
        int arr[] = new int[n];
        HashSet<Pair> pairs = new HashSet<Pair>();


        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(str[i]);
        }

        for (int i = 0; i < m; i++) {
            str = br.readLine().split(" ");
            pairs.add(new Pair(Integer.parseInt(str[0]), Integer.parseInt(str[1])));
            pairs.add(new Pair(Integer.parseInt(str[1]), Integer.parseInt(str[0])));
        }

        System.out.println(pairs);

        long count = 0;
        Queue<Integer> queue = new LinkedList<>();


        for (int i = 0; i < arr.length; i++) {
            if (i > 0) {
                dummy.x = arr[i];
                dummy.y = arr[i - 1];


                if (pairs.contains(dummy)) {
                    System.out.println(queue.size());
                    count += queue.size();
                    while (!queue.isEmpty()) {
                        queue.poll();
                    }
                }
            }
            dummy.x = arr[i];
            dummy.y = arr[i];
            if (!pairs.contains(dummy)) {
                count++;
            }

            queue.add(arr[i]);


        }
        System.out.println(queue.size());
        count += queue.size();
        System.out.println(count);
    }
}

@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
class Pair {
    int x;
    int y;

}
