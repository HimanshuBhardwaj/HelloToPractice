package com.himanshu.practice.july.july16;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Created by himanshubhardwaj on 16/07/18.
 */
public class ThreePartsOfTheArray {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        String s[] = br.readLine().split(" ");
        long a[] = new long[s.length];
        TreeMap<Long, TreeSet<Integer>> endRight = new TreeMap<Long, TreeSet<Integer>>();
        //TreeMapP<Long, TreeSet<Integer>> endleft = new TreeMapP<Long, TreeSet<Integer>>();
        long sl = 0;
        long sr = 0;

        for (int i = 0; i < n; i++) {
            a[i] = Long.parseLong(s[i]);
            a[n - 1 - i] = Long.parseLong(s[n - 1 - i]);
            sl += a[i];
            sr += a[n - 1 - i];
//            TreeSet<Integer> setWithSumPosLeft = endleft.get(sl);
//            if (setWithSumPosLeft != null) {
//                setWithSumPosLeft.add(i);
//            } else {
//                setWithSumPosLeft = new TreeSet<>();
//                setWithSumPosLeft.add(i);
//                endleft.put(sl, setWithSumPosLeft);
//            }

            //System.out.println((n - i - 1) + "\t" + a[n - i - 1] + "\t" + sr);
            TreeSet<Integer> setWithSumPosRight = endRight.get(sr);
            if (setWithSumPosRight != null) {
                setWithSumPosRight.add(n - 1 - i);
            } else {
                setWithSumPosRight = new TreeSet<>();
                setWithSumPosRight.add(n - 1 - i);
                endRight.put(sr, setWithSumPosRight);
            }
        }

//        System.out.println(endRight);

        sl = 0;
        long maximumSum = 0;

        for (int i = 0; i < a.length; i++) {
            sl += a[i];

            TreeSet<Integer> sumRightPos = endRight.get(sl);
            //System.out.println(i + "\t" + sl + "\t" + sumRightPos);
            if (sumRightPos != null) {
                for (int position : sumRightPos) {
                    if (position > (i)) {
                        maximumSum = Math.max(maximumSum, sl);
                    }
                }
            }
        }
        System.out.println(maximumSum);

    }
}
