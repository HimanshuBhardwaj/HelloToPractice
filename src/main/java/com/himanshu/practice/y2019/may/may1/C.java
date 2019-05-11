package com.himanshu.practice.y2019.may.may1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Created by himanshubhardwaj on 01/05/19.
 */
public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        TreeMap<Long, Long> treeMAp = new TreeMap();

        String str[] = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        long k = Long.parseLong(str[1]);

        str = br.readLine().split(" ");
        ArrayList<Long> arrayList = new ArrayList<>();


        for (int i = 0; i < str.length; i++) {
            long num = Long.parseLong(str[i]);
            arrayList.add(num);
            Long ef = treeMAp.get(num);
            if (ef == null) {
                treeMAp.put(num, 0l);
                ef = 0l;
            }
            treeMAp.put(num, ef + 1);
        }

        Collections.sort(arrayList, Collections.reverseOrder());
        System.out.println(arrayList);
        long count = 0;


        for (long element : arrayList) {
            Long frequency = treeMAp.get(element);
            if (frequency == null) {
                continue;
            } else if (frequency == 1) {
                treeMAp.remove(element);
            } else {
                treeMAp.put(element, frequency - 1);
            }


            Long pair = treeMAp.floorKey(element - k);

            if (pair == null) {
                continue;
            } else {
                Long pairFreq = treeMAp.get(pair);
                count++;
                if (pairFreq == 1) {
                    treeMAp.remove(pair);
                } else {
                    treeMAp.put(pair, pairFreq - 1);
                }
            }
        }

        System.out.print(count);

    }
}
