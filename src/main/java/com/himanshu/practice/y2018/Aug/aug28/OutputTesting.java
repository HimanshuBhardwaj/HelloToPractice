package com.himanshu.practice.y2018.Aug.aug28;

import java.io.PrintWriter;

/**
 * Created by himanshubhardwaj on 29/08/18.
 * Testing performance of PrintWriter and sout
 * for extensivr i/o, PrintWriter is paster
 */
public class OutputTesting {
    public static void main(String[] args) {
        char c[] = new char[100000];
        for (int i = 0; i < c.length; i++) {
            c[i] = 'a';
        }
        String s = new String(c);
        long a, b;

        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            System.out.println(s);
        }
        long end = System.currentTimeMillis();

        a = end - start;

        PrintWriter pr = new PrintWriter(System.out, true);
        start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            pr.append(s);
            pr.append("\n");
        }
        pr.flush();
        end = System.currentTimeMillis();

        System.out.println(a);
        System.out.println(end - start);
        pr.close();



    }
}
