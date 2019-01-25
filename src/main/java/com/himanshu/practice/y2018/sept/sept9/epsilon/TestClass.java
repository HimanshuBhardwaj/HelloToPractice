package com.himanshu.practice.y2018.sept.sept9.epsilon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

/**
 * Created by himanshubhardwaj on 09/09/18.
 */
public class TestClass {
    static HashSet<Character> vowels = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');
        vowels.add('A');
        vowels.add('E');
        vowels.add('I');
        vowels.add('O');
        vowels.add('U');

        while ((t--) > 0) {
            String s = br.readLine();
            System.out.println(bruteforce(s));
        }


    }


    static long bruteforce(String s) {

        long sum = 0l;
        int n[] = new int[s.length()];

        for (int i = 0; i < n.length; i++) {
            if (i == 0) {
                n[i] = (vowels.contains(s.charAt(i))) ? 1 : 0;
            } else {
                n[i] = n[i - 1] + ((vowels.contains(s.charAt(i))) ? 1 : 0);
            }
        }

        for (int i = 0; i < n.length; i++) {
            for (int j = i; j < n.length; j++) {
                sum += n[j] - n[i] + ((vowels.contains(s.charAt(i))) ? 1 : 0);
            }
        }

        return sum;
    }
}

/*
1
abacce
24
* */