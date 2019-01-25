package com.himanshu.practice.y2018.sept.sept9.jiva;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by himanshubhardwaj on 09/09/18.
 */
public class LargestBalancedString {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int t = Integer.parseInt(br.readLine());
        Map<Character, Integer> map = new HashMap<>();
        map.put('(', 0);
        map.put(')', 1);
        map.put('[', 2);
        map.put(']', 3);
        map.put('{', 4);
        map.put('}', 5);

        while ((t--) > 0) {
            int count[] = new int[6];
            String s = br.readLine();

            for (int i = 0; i < s.length(); i++) {
                count[map.get(s.charAt(i))]++;
            }

            int c = Math.min(count[0], count[1]);
            c += Math.min(count[2], count[3]);
            c += Math.min(count[4], count[5]);

            c = c * 2;
            pw.append(String.valueOf(c));
            if (t != 0) {
                pw.append("\n");
            }
        }
        pw.flush();
    }
}
