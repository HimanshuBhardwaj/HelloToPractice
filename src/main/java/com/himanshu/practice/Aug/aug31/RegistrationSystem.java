package com.himanshu.practice.Aug.aug31;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by himanshubhardwaj on 31/08/18.
 * PrintWriter Solution: https://codeforces.com/contest/4/submission/42318977 : 374 ms
 * sout Solution: https://codeforces.com/contest/4/submission/42318959: 1934 ms
 * though problem was simple, here sout was 6 times slower than PrintWriter
 *
 */
public class RegistrationSystem {
    public static void main(String[] args) throws IOException {
//        soutSolution();
        printWriterSolution();

    }

    //1934 millisecond
    static void soutSolution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        HashMap<String, Integer> map = new HashMap<>();


        for (int i = 0; i < n; i++) {
            String s = br.readLine();

            if (map.containsKey(s)) {
                System.out.print(s);
                System.out.print(map.get(s));
                map.put(s, 1 + map.get(s));

            } else {
                map.put(s, 1);
                System.out.print("OK");
            }
            if (i != (n - 1)) {
                System.out.println();
            }
        }

        br.close();
    }


    //this worked in 374 ms
    static void printWriterSolution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        HashMap<String, Integer> map = new HashMap<>();
        PrintWriter pr = new PrintWriter(System.out);


        for (int i = 0; i < n; i++) {
            String s = br.readLine();

            if (map.containsKey(s)) {
                pr.append(s);
                pr.append(String.valueOf(map.get(s)));
                map.put(s, 1 + map.get(s));

            } else {
                map.put(s, 1);
                pr.append("OK");
            }
            if (i != (n - 1)) {
                pr.append("\n");
            }
        }

        pr.flush();
        pr.close();
        br.close();
    }
}
