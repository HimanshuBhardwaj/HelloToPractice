package com.himanshu.practice.y2018.Aug.Aug3.codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 03/08/18.
 */
public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s[] = br.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int m = Integer.parseInt(s[1]);
        int q = Integer.parseInt(s[2]);

        String str = br.readLine();
        String subStr = br.readLine();
        int freq[] = new int[str.length()];
        int leftFrq[] = new int[str.length()];


        for (int i = 0; (i + subStr.length() - 1) < str.length(); i++) {
            //System.out.println(str.substring(i) + "\t" + subStr + "\t" + str.substring(i, i + subStr.length()).equals(subStr));
            if (str.substring(i, i + subStr.length()).equals(subStr)) {
                freq[i] = 1;
            }
            //System.out.println((i + 1) + "\t" + freq[i]);
        }

        leftFrq[0] = freq[0];

        for (int i = 1; i < (freq.length); i++) {
            leftFrq[i] += leftFrq[i - 1] + freq[i];
        }


        for (int i = 0; i < q; i++) {
            s = br.readLine().split(" ");
            int l = Integer.parseInt(s[0]) - 1;
            int r = Integer.parseInt(s[1]) - subStr.length();

            if (r < l) {
                System.out.println(0);
                continue;
            }

            if (l == 0) {
                System.out.println(leftFrq[r]);
            } else {
                System.out.println((leftFrq[r] - leftFrq[l - 1]));
            }
        }

    }
}
