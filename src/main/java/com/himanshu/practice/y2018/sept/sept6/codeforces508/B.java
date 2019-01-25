package com.himanshu.practice.y2018.sept.sept6.codeforces508;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;

/**
 * Created by himanshubhardwaj on 06/09/18.
 */
public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        if (n <= 2) {
            System.out.print("No");
            return;
        }

        TreeSet<Integer> set1 = new TreeSet<>();
        TreeSet<Integer> set2 = new TreeSet<>();

        if (n % 2 == 0) {
            set2.add(n);

            for (int i = 1; i <= (n - 1); i++) {
                set1.add(i);
            }

        } else {
            for (int i = 1; i <= n; i++) {
                if (i % 2 == 1) {
                    set1.add(i);
                } else {
                    set2.add(i);
                }
            }
        }


        System.out.println("Yes");
        System.out.print(set1.size()+" ");
        for (int x : set1) {
            System.out.print(x + " ");
        }
        System.out.println();
        System.out.print(set2.size()+" ");
        for (int x : set2) {
            System.out.print(x + " ");
        }

    }
}
