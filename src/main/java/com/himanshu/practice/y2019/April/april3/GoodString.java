package com.himanshu.practice.y2019.April.april3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 03/04/19.
 * Problem Statement: https://codeforces.com/contest/1140/problem/B
 * Also: TODO: Complete it
 */
public class GoodString {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            br.readLine();
            System.out.println(delete(br.readLine()) - 1);
        }


    }

    static int delete(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }

        if (str.charAt(0) == '>' || str.charAt(str.length() - 1) == '<' || (str.indexOf('>') == -1) ||
                (str.lastIndexOf('<') == str.length() - 1)) {
            return 1;

        }
        int posFL = str.indexOf('>');
        String remainStringL = str.substring(0, posFL);

        int posR = str.lastIndexOf('<');
        String remainStringR = str.substring(posR + 1);

        return Math.min(1 + delete(remainStringL), 1 + delete(remainStringR));
    }
}
