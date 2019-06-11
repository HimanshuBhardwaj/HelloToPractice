package com.himanshu.practice.y2019.june.june9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Created by himanshubhardwaj on 09/06/19.
 */
public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        PrintWriter pw = new PrintWriter(System.out);
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            String[] str = br.readLine().split(" ");
            int modulo[] = new int[3];

            for (int i = 0; i < str.length; i++) {
                int num = Integer.parseInt(str[i]);
                modulo[num % 3]++;
            }

            if (modulo[2] >= modulo[1]) {
                modulo[0] += modulo[1];
                modulo[2] -= modulo[1];
                modulo[0] += modulo[2] / 3;
                pw.append(modulo[0] + "\n");
            } else {
                modulo[0] += modulo[2];
                modulo[1] -= modulo[2];
                modulo[2] = 0;
                modulo[0] += (modulo[1] / 3);
                pw.append(modulo[0] + "\n");
                continue;
            }
        }
        pw.flush();
    }
}
