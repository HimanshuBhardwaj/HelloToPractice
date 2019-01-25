package com.himanshu.practice.y2018.sept.sept23.codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Created by himanshubhardwaj on 23/09/18.
 */
public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        PrintWriter pw = new PrintWriter(System.out);

        int n = Integer.parseInt(str[0]);
        int d = Integer.parseInt(str[1]);

        int q = Integer.parseInt(br.readLine());

        while (q-- > 0) {
            str = br.readLine().split(" ");
            int x = Integer.parseInt(str[0]);
            int y = Integer.parseInt(str[1]);

            if (isOutside(n, d, x, y)) {
                pw.append("NO");
            } else {
                pw.append("YES");
            }
            pw.append("\n");
        }

        pw.flush();

    }

    private static boolean isOutside(int n, int d, int x, int y) {
        if ((x + y - d) < 0) {
            return true;
        }

        if ((y - x + d) < 0) {
            return true;
        }

        if ((x + y - (2 * n) + d) > 0) {
            return true;
        }

        if ((y - x - d) > 0) {
            return true;
        }

        return false;

    }


}
