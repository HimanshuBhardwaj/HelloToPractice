package com.himanshu.practice.y2019.march.march31;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by himanshubhardwaj on 31/03/19.
 */
public class D {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int arr[] = new int[n];
        String[] str = br.readLine().split(" ");
        int freuency[] = new int[200001];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(str[i]);
            freuency[arr[i]]++;
        }

        int maxFreqElement = 0;

        for (int i = 0; i < freuency.length; i++) {
            if (freuency[i] > freuency[maxFreqElement]) {
                maxFreqElement = i;
            }
        }

        //now all wll converge to this element

        int posOfMAxFrequencyElement = -1;


        for (int i = 0; i < n; i++) {
            if (arr[i] == maxFreqElement) {
                posOfMAxFrequencyElement = i;
                break;
            }
        }

        //System.out.println(posOfMAxFrequencyElement + "\t\t" + maxFreqElement);


        Queue<String> operations = new LinkedList<>();

        for (int i = posOfMAxFrequencyElement - 1; i >= 0; i--) {
            if (arr[i] > arr[i + 1]) {
                String op = "2 " + (i + 1) + " " + (i + 2);
                operations.add(op);

            } else if (arr[i] < arr[i + 1]) {
                String op = "1 " + (i + 1) + " " + (i + 2);
                operations.add(op);
            }
            arr[i] = arr[i + 1];
            //System.out.println("After Inc.." + i + "\r\r" + arr[i]);
        }




        for (int i = posOfMAxFrequencyElement + 1; i < n; i++) {
            if (arr[i] > arr[i - 1]) {
                String op = "2 " + (i + 1) + " " + (i);
                operations.add(op);
            } else if (arr[i] < arr[i - 1]) {
                String op = "1 " + (i + 1) + " " + (i);
                operations.add(op);
            }
            arr[i] = arr[i - 1];
        }

        System.out.println(operations.size());
        while (!operations.isEmpty()) {
            System.out.println(operations.poll());
        }

    }
}
