package com.himanshu.practice.sept.sept6.codeforces508;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * Created by himanshubhardwaj on 06/09/18.
 */
public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int k = Integer.parseInt(str[1]);
        HashMap<Character, Integer> mapping = new HashMap<>();
        String s = br.readLine();

        for (int i = 0; i < s.length(); i++) {
            if (!mapping.containsKey(s.charAt(i))) {
                mapping.put(s.charAt(i), mapping.size());
            }
        }

  //      System.out.println(mapping);

        int freq[] = new int[k];

        for (int i = 0; i < s.length(); i++) {
            freq[mapping.get(s.charAt(i))]++;
        }

        int min = Integer.MAX_VALUE;


        for (int i = 0; i < freq.length; i++) {
//            System.out.print(freq[i]+" ");
            min = Math.min(min, freq[i]);
        }

        System.out.println(min * k);

    }

}
