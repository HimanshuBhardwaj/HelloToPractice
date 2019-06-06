package com.himanshu.practice.y2019.june.june6;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by himanshubhardwaj on 06/06/19.
 * Statement:
 * Also: Binary search
 * Submission: https://codeforces.com/contest/637/submission/55193376
 *
 */
public class ChatOrder {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        HashMap<String, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            hashMap.put(str, i);
        }

        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        };

        TreeMap<Integer, String> reverseMAp = new TreeMap<>(comparator);

        for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
            reverseMAp.put(entry.getValue(), entry.getKey());
        }

        PrintWriter pw = new PrintWriter(System.out);
        for (Map.Entry<Integer, String> entry : reverseMAp.entrySet()) {
            pw.append(entry.getValue());
            pw.append("\n");
        }
        pw.flush();
    }
}