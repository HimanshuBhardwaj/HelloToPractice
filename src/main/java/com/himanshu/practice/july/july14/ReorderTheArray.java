package com.himanshu.practice.july.july14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;

/**
 * Created by himanshubhardwaj on 13/07/18.
 * Problem Set: https://codeforces.com/contest/1008/problem/C
 * This I should have been able to solve in contest
 * Submission: https://codeforces.com/contest/1008/submission/40302243
 * Algo: Balanced Tree
 */
public class ReorderTheArray {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int arr[] = new int[n];
        TreeSet<Number> set = new TreeSet<Number>();

        String str[] = br.readLine().split(" ");
        for (int i = 0; i < str.length; i++) {
            arr[i] = Integer.parseInt(str[i]);
            Number temp = new Number(arr[i], 1);
            if (set.contains(temp)) {
                temp = set.ceiling(temp);
                set.remove(temp);
                temp.frequency++;
                set.add(temp);
            } else {
                set.add(temp);
            }

        }

        int count = 0;

        for (int i = 0; i < n; i++) {
//            System.out.println(count + "\t\t" + set);
            Number curr = new Number(arr[i], -1);
            Number next = set.higher(curr);
            if (next != null) {
                count++;
                set.remove(next);
                next.frequency--;
                if (next.frequency > 0) {
                    set.add(next);
                }

            }
        }
        System.out.print(count);
    }
}


class Number implements Comparable<Number> {
    int number;
    int frequency;

    @java.beans.ConstructorProperties({"number", "frequency"})
    public Number(int number, int frequency) {
        this.number = number;
        this.frequency = frequency;
    }

    @Override
    public int compareTo(Number o) {
        return this.number - o.number;
    }

    //I know what I am doing
    public boolean equals(Object obj) {
        return this.number == ((Number) obj).number;
    }

    public String toString() {
        return "Number(number=" + this.number + ", frequency=" + this.frequency + ")";
    }
}