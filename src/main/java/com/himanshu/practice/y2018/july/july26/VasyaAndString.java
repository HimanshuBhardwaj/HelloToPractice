package com.himanshu.practice.y2018.july.july26;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by himanshubhardwaj on 26/07/18.
 * Statement: https://codeforces.com/contest/676/problem/C
 * Algo: Adhoc
 * Submission: https://codeforces.com/contest/676/submission/40764538
 */
public class VasyaAndString {
    static PriorityQueue<Char> priorityQueue = new PriorityQueue<>();
    static int numOfCharsInList = 0;
    static int[] frequency = new int[26];


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int k = Integer.parseInt(str[1]);
        char[] list = br.readLine().toCharArray();


        Queue<Character> queue = new LinkedList<>();

        long maxSum = 0;

        for (int i = 0; i < list.length; i++) {
            while (!couldBeConverted(queue, list[i], k)) {
                remove(queue, k);
            }
            addElement(queue, list[i], k);
            maxSum = Math.max(maxSum, queue.size());
            //System.out.print(i + "\t" + queue + "\t" + k + "\t");
            //System.out.println(priorityQueue);
        }

        System.out.print(maxSum);

    }

    private static void remove(Queue<Character> queue, int k) {
        char c = queue.poll();
        priorityQueue.remove(new Char(c, 0));

        frequency[c - 'a']--;
        if (frequency[c - 'a'] > 0) {
            priorityQueue.add(new Char(c, frequency[c - 'a']));
        }
    }

    private static void addElement(Queue<Character> queue, char c, int k) {
        if (!priorityQueue.isEmpty() && c == priorityQueue.peek().value) {
            priorityQueue.peek().frequency++;
            frequency[c - 'a']++;
            queue.add(c);
            return;
        }
        priorityQueue.remove(new Char(c, 0));
        priorityQueue.add(new Char(c, frequency[c - 'a'] + 1));
        frequency[c - 'a']++;
        queue.add(c);
    }

    private static boolean couldBeConverted(Queue<Character> queue, char c, int k) {
        if (priorityQueue.isEmpty()) {
            return true;
        }

        int offset = 0;
        if (c != priorityQueue.peek().value) {
            offset++;
        }

        if ((queue.size() - priorityQueue.peek().frequency + offset) <= k) {
            return true;
        }
        return false;
    }
}


class Char implements Comparable<Char> {
    char value;
    int frequency;

    public Char(char value, int frequency) {
        this.value = value;
        this.frequency = frequency;
    }


    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Char) {
            Char newChar = (Char) obj;
            return newChar.value == this.value;
        }
        return false;
    }


    @Override
    public int compareTo(Char o) {
        return o.frequency - this.frequency;
    }

    public String toString() {
        return "Char(value=" + this.value + ", frequency=" + this.frequency + ")";
    }
}
