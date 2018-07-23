//package com.himanshu.practice.july.july16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by himanshubhardwaj on 16/07/18.
 */
public class PolycarpPractice {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String st[] = br.readLine().split(" ");
        int n = Integer.parseInt(st[0]);
        int k = Integer.parseInt(st[1]);
        st = br.readLine().split(" ");
        int a[] = new int[n];


        ArrayList<Value> v = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st[i]);
            v.add(new Value(a[i], i));
        }


        Collections.sort(v, new Comparator<Value>() {
            @Override
            public int compare(Value o1, Value o2) {
                return o1.value - o2.value;
            }
        });


        ArrayList<Value> maxElements = new ArrayList<>(k);
        int sum = 0;
        for (int i = n - k; i < n; i++) {
            maxElements.add(v.get(i));
            sum += v.get(i).value;
        }

        System.out.println(sum);

        Collections.sort(maxElements, new Comparator<Value>() {
            @Override
            public int compare(Value o1, Value o2) {
                return o1.position - o2.position;
            }
        });

        int count = 0;

        int prev = -1;
        for (int i = 0; i < maxElements.size(); i++) {
            if (i != (maxElements.size() - 1)) {
                System.out.print((maxElements.get(i).position - prev) + " ");
                prev = maxElements.get(i).position;
            } else {
                System.out.println(n - prev - 1);

            }
        }


    }
}


class Value {
    int value;
    int position;

    public Value(int value, int position) {
        this.value = value;
        this.position = position;
    }

    public String toString() {
        return "Value(value=" + this.value + ", position=" + this.position + ")";
    }
}