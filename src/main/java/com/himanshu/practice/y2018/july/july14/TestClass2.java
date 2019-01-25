package com.himanshu.practice.y2018.july.july14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * Created by himanshubhardwaj on 15/07/18.
 * Coding Test
 */
public class TestClass2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        char charStr[] = str.toCharArray();
        int k = Integer.parseInt(br.readLine());
        char charArray[] = new char[charStr.length];
        PriorityQueue<Character> pr = new PriorityQueue<>();


        for (int i = 0; i < k; i++) {
            pr.add(charStr[i]);
        }

        int pos = 0;

        for (int i = k; i < charStr.length; i++) {
            charArray[pos] = pr.remove();
            pos++;
            pr.add(charStr[i]);
        }

        while (!pr.isEmpty()) {
            charArray[pos] = pr.remove();
            pos++;
        }
        for (char c : charArray) {
            System.out.print(c);
        }


    }
}


