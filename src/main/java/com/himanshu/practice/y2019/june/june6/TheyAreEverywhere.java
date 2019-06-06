package com.himanshu.practice.y2019.june.june6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by himanshubhardwaj on 06/06/19.
 * Statement: https://codeforces.com/problemset/problem/701/C
 * Algo: Two pointers
 * Submission: https://codeforces.com/contest/701/submission/55199722
 */
public class TheyAreEverywhere {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String str = br.readLine();
        HashSet<Character> distinctCharacters = new HashSet<>();

        for (int i = 0; i < str.length(); i++) {
            distinctCharacters.add(str.charAt(i));
        }

        HashMap<Character, Integer> frequecy = new HashMap<>();
        Queue<Character> queue = new LinkedList<>();

        long size = Long.MAX_VALUE;

        for (int i = 0; i < str.length(); i++) {
            queue.add(str.charAt(i));
            addChar(frequecy, str.charAt(i));

            while (frequecy.size() == distinctCharacters.size()) {
                size = Math.min(size, queue.size());
                removeChar(frequecy, queue.poll());
            }
        }

        System.out.print(size);
    }

    private static void removeChar(HashMap<Character, Integer> frequecy, Character poll) {
        if (frequecy.get(poll) == 1) {
            frequecy.remove(poll);
        } else {
            frequecy.put(poll, frequecy.get(poll) - 1);
        }
    }

    private static void addChar(HashMap<Character, Integer> frequecy, char c) {
        if (frequecy.containsKey(c)) {
            frequecy.put(c, 1 + frequecy.get(c));
        } else {
            frequecy.put(c, 1);
        }
    }
}
