package com.himanshu.practice.sept.sept9.jiva;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;

/**
 * Created by himanshubhardwaj on 09/09/18.
 */
public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(br.readLine().trim());
        int n = Integer.parseInt(br.readLine().trim());

        int a[] = new int[n];
        int next[] = new int[n];
        boolean visited[] = new boolean[n];
        String[] str = br.readLine().split(" ");
        TreeSet<Element> set = new TreeSet<>();
        Element element = new Element(7, 0);


        for (int i = n - 1; i >= 0; i--) {
            a[i] = Integer.parseInt(str[i]);
            set.add(new Element(i, a[i]));
            element.value = a[i] + k;
            Element nextIndex = set.ceiling(element);

            if (nextIndex == null) {
                next[i] = n;
            } else {
                next[i] = nextIndex.index;
            }
        }

        int maximumJump = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                int tempJumpSize = 1;
                int pos = i;
                while (next[pos] < n) {
                    tempJumpSize++;
                    visited[pos] = true;
                    pos = next[pos];
                }
                maximumJump = Math.max(maximumJump, tempJumpSize);
            }
        }

        System.out.println(maximumJump);


    }
}


class Element implements Comparable<Element> {
    int index;
    int value;

    @java.beans.ConstructorProperties({"index", "value"})
    public Element(int index, int value) {
        this.index = index;
        this.value = value;
    }

    @Override
    public int compareTo(Element o) {
        return this.value - o.value;
    }
}