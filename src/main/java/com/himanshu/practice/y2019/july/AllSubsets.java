package com.himanshu.practice.y2019.july;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by himanshubhardwaj on 03/07/19.
 */
public class AllSubsets {
    static long count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");
        ArrayList<Long> list = new ArrayList<>();

        for (int i = 0; i < str.length; i++) {
            list.add(Long.parseLong(str[i]));
        }

        printAllSubsets(0, new LinkedList<Long>(), list);
    }

    private static void printAllSubsets(int index, LinkedList<Long> currentSubSet, ArrayList<Long> list) {

        if (index == list.size()) {
            System.out.print((count + 1) + ":\t");
            for (long x : currentSubSet) {
                System.out.print(x + ",");
            }
            System.out.println();
            count++;
            return;
        }

        currentSubSet.addLast(list.get(index));
        printAllSubsets(index + 1, currentSubSet, list);
        currentSubSet.removeLast();
        printAllSubsets(index + 1, currentSubSet, list);
    }
}
