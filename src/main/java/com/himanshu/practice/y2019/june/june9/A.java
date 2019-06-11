package com.himanshu.practice.y2019.june.june9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * Created by himanshubhardwaj on 09/06/19.
 */
public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int q = Integer.parseInt(br.readLine());
        HashMap<Long, Long> minimumNumber = new HashMap<>();


        PrintWriter pw = new PrintWriter(System.out);
        while (q-- > 0) {
            long n = Long.parseLong(br.readLine());
            long answer = getMinimum(n, minimumNumber);
            if (answer >= Integer.MAX_VALUE) {
                answer = -1;
            }
            pw.append(answer + "\n");
        }
        pw.flush();
    }

    private static long getMinimum(long num, HashMap<Long, Long> minimumNumber) {
        if (minimumNumber.containsKey(num)) {
            return minimumNumber.get(num);
        }

        if (num == 1) {
            return 0;
        }

        long minimum = Integer.MAX_VALUE;

        if (num % 2 == 0) {
            minimum = Math.min(1 + getMinimum(num / 2, minimumNumber), minimum);
        } else if (num % 3 == 0) {
            minimum = Math.min(1 + getMinimum((2 * num) / 3, minimumNumber), minimum);
        } else if (num % 5 == 0) {
            minimum = Math.min(1 + getMinimum((4 * num) / 5, minimumNumber), minimum);
        }

        if (minimum < Integer.MAX_VALUE) {
            minimumNumber.put(num, minimum);
        } else {
            return Integer.MAX_VALUE;
        }
        return minimum;
    }
}