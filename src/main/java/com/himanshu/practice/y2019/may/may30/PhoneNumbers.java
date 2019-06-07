package com.himanshu.practice.y2019.may.may30;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by himanshubhardwaj on 01/06/19.
 * Problem: https://codeforces.com/contest/898/problem/C
 * Algo: Ad-Hoc
 * Submission: https://codeforces.com/contest/898/submission/54913569
 */
public class PhoneNumbers {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        HashMap<String, ArrayList<String>> numberBook = new HashMap<>();

        while (t-- > 0) {
            String[] str = br.readLine().split(" ");
            int n = Integer.parseInt(str[1]);

            if (!numberBook.containsKey(str[0])) {
                numberBook.put(str[0], new ArrayList<>());
            }

            for (int i = 2; i < str.length; i++) {
                numberBook.get(str[0]).add(str[i]);
            }
        }

        HashMap<String, ArrayList<String>> numberBookClone = (HashMap<String, ArrayList<String>>) numberBook.clone();

        PrintWriter pw = new PrintWriter(System.out);
        pw.append(numberBookClone.size() + "\n");


        for (Map.Entry<String, ArrayList<String>> entry : numberBook.entrySet()) {
            pw.append(entry.getKey() + " ");
            ArrayList<String> resolve = resolve(entry.getValue());
            pw.append(resolve.size() + " ");
            for (String s : resolve) {
                pw.append(s + " ");
            }
            pw.append("\n");
        }
        pw.flush();
    }

    private static ArrayList<String> resolve(ArrayList<String> value) {
        ArrayList<String> list = new ArrayList<>();

        Comparator<String> comp = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (int)(o1.length() - o2.length());
            }
        };

        value.sort(comp);


        for (int i = 0; i < value.size(); i++) {
            boolean flag = true;
            for (int j = i+1; flag && (j < value.size()); j++) {
                if (isSuffix(value.get(i), value.get(j))) {
                    flag = false;
                }
            }

            if (flag) {
                list.add(value.get(i));
            }
        }
        return list;
    }

    //returns true is s is suffix of s1
    public static boolean isSuffix(String s, String s1) {
        boolean result = false;
        if (!(s1 == null || s == null || s1.length() < s.length())) {
            result = s1.substring(s1.length() - s.length()).equals(s);
        }
        return result;
    }
}
