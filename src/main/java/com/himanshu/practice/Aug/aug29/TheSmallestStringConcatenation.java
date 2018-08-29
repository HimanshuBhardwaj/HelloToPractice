package com.himanshu.practice.Aug.aug29;

import lombok.AllArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * Created by himanshubhardwaj on 29/08/18.
 * Problem Statement:
 * TODO: Complete it
 */
public class TheSmallestStringConcatenation {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pr = new PrintWriter(System.out);
        int n = Integer.parseInt(br.readLine());

        Str s[] = new Str[n];

        for (int i = 0; i < s.length; i++) {
            s[i] = new Str(br.readLine());
        }
        Arrays.sort(s);

        for (Str ss : s) {
            System.out.println(ss.s);
            pr.append(ss.s);
        }
        pr.flush();
        pr.close();
        br.close();
    }
}

@AllArgsConstructor
class Str implements Comparable<Str> {
    String s;

    @Override
    public int compareTo(Str o) {
        String a = this.s;
        String b = o.s;


        for (int i = 0; i < (Math.min(a.length(), b.length())); i++) {
            if (a.charAt(i) < b.charAt(i)) {
                return -1;
            } else if (a.charAt(i) > b.charAt(i)) {
                return 1;
            }
        }

        int sA = a.length();
        int sB = b.length();
        return Integer.compare(sA, sB);
    }
}