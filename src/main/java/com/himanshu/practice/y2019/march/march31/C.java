package com.himanshu.practice.y2019.march.march31;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

/**
 * Created by himanshubhardwaj on 31/03/19.
 */
public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        String str[] = br.readLine().split(" ");
        int[] arr = new int[str.length];

        for (int i = 0; i < str.length; i++) {
            arr[i] = Integer.parseInt(str[i]);
        }

        Arrays.sort(arr);

        for (int i = 2; i < arr.length; i++) {
            if ((arr[i] == arr[i - 1]) && (arr[i - 1] == arr[i - 2])) {
                System.out.println("NO");
                return;
            }
        }

        LinkedList<Integer> increasingSeq = new LinkedList<>();
        LinkedList<Integer> decreasingSeq = new LinkedList<>();

        increasingSeq.add(arr[0]);


        for (int i = 1; i < arr.length; i++) {
            if (increasingSeq.getLast() == arr[i - 1]) {
                decreasingSeq.addLast(arr[i]);
            } else {
                increasingSeq.addLast(arr[i]);
            }
        }
        Collections.sort(increasingSeq);
        Collections.sort(decreasingSeq,Collections.reverseOrder());

        System.out.println("YES");
        System.out.println(increasingSeq.size());

        for (int x:increasingSeq) {
            System.out.print(x+" ");
        }
        System.out.println();
        System.out.println(decreasingSeq.size());
        for (int x:decreasingSeq) {
            System.out.print(x+" ");
        }





    }
}
