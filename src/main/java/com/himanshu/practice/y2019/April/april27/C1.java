package com.himanshu.practice.y2019.April.april27;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 26/04/19.
 */
public class C1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String str[] = br.readLine().split(" ");
        int arr[] = new int[str.length];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(str[i]);
        }

        char newArray[] = new char[arr.length];

        int first = 0;
        int last = arr.length - 1;
        int index = 0;
        int lastE = Integer.MIN_VALUE;


        while (first <= last) {
            if (arr[first] > arr[last]) {
                if (arr[last] > lastE) {
                    newArray[index] = 'R';
                    lastE = arr[last];
                    last--;
                    index++;
                } else if (arr[first] > lastE) {
                    newArray[index] = 'L';
                    lastE = arr[first];
                    first++;
                    index++;
                } else {
                    break;
                }
            } else {
                if (arr[first] > lastE) {
                    newArray[index] = 'L';
                    lastE = arr[first];
                    first++;
                    index++;
                } else if (arr[last] > lastE) {
                    newArray[index] = 'R';
                    lastE = arr[last];
                    last--;
                    index++;
                } else {
                    break;
                }
            }
        }


        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < index; i++) {
            sb.append(newArray[i]);
        }

        System.out.println(index);
        System.out.print(sb.toString());

    }
}