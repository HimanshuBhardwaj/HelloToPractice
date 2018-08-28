package com.himanshu.practice.Aug.aug28;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Created by himanshubhardwaj on 29/08/18.
 */
public class BearAndStringDistance {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pr = new PrintWriter(System.out, true);
        String[] s = br.readLine().split(" ");
        char c[] = br.readLine().toCharArray();
        int k = Integer.parseInt(s[1]);


        for (int i = 0; (i < c.length) && (k > 0); i++) {
            if ((c[i] - 'a') > ('z' - c[i])) {
                int diff = c[i] - 'a';
                if (k >= diff) {
                    k = k - diff;
                    c[i] = 'a';
                } else {
                    c[i] = (char) ((int) c[i] - k);
                    k = 0;
                }
            } else {
                int diff = 'z' - c[i];
                if (k >= diff) {
                    k = k - diff;
                    c[i] = 'z';
                } else {
                    c[i] = (char) ((int) c[i] + k);
                    k = 0;
                }
            }
        }

        if (k == 0) {
            for (char ch : c) {
                System.out.print(ch);
            }
        } else {
            System.out.println(-1);
        }

        pr.close();
        br.close();
    }
}
