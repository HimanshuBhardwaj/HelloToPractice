package com.himanshu.practice.july14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.TreeSet;

/**
 * Created by himanshubhardwaj on 15/07/18.
 */
public class TestClass {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String str[] = br.readLine().split(" ");
        long arr[] = new long[str.length];
        TreeSet<ANumber> aNumber = new TreeSet<ANumber>();

        for (int i = 0; i < str.length; i++) {
            arr[i] = Long.parseLong(str[i]);
        }
        Arrays.sort(arr);
        long sum = 0;

        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            aNumber.add(new ANumber(i, ((double) sum) / (i + 1), sum));
        }

        int q = Integer.parseInt(br.readLine());
        ANumber temp = new ANumber(0, 0, 0);


        for (int i = 1; i <= q; i++) {
            double maxAvg = Double.parseDouble(br.readLine());
            temp.average = maxAvg;
            ANumber an = aNumber.lower(temp);
            if (an == null) {
                System.out.println(0);
            } else {
                System.out.println(an.pos + 1);
            }
        }
    }
}


class ANumber implements Comparable<ANumber> {
    int pos;
    double average;
    long CommulativeSum;

    public ANumber(int pos, double average, long CommulativeSum) {
        this.pos = pos;
        this.average = average;
        this.CommulativeSum = CommulativeSum;
    }

    @Override
    public int compareTo(ANumber o) {
        return Double.compare(this.average, o.average);
    }
}
