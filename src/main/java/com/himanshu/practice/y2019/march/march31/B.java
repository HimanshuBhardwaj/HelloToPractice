package com.himanshu.practice.y2019.march.march31;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by himanshubhardwaj on 31/03/19.
 */
public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        ArrayList<Integer> even = new ArrayList<>();
        ArrayList<Integer> odd = new ArrayList<>();

        String[] str = br.readLine().split(" ");
        int totalSum = 0;

        for (String tk : str) {
            int num = Integer.parseInt(tk);
            if (num % 2 == 0) {
                even.add(num);
            } else {
                odd.add(num);
            }
            totalSum += num;
        }
        Collections.sort(even, Collections.reverseOrder());
        Collections.sort(odd, Collections.reverseOrder());
        //System.out.println(even);
        //System.out.println(odd);

        if (even.size() == odd.size() || (Math.abs(even.size()-odd.size())==1)) {
            System.out.println(0);
            return;
        } else if (even.size() > odd.size()) {
            int sum = 0;
            for (int i=odd.size()+1;i<even.size();i++) {
                sum+=even.get(i);
            }
            System.out.println(sum);
            return;
        } else {
            int sum = 0;
            for (int i=even.size()+1;i<odd.size();i++) {
                sum+=odd.get(i);
            }
            System.out.println(sum);
            return;
        }


    }
}
