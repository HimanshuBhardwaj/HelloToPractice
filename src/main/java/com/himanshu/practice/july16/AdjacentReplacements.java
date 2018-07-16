//package com.himanshu.practice.july16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 16/07/18.
 */
public class AdjacentReplacements {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String st[] = br.readLine().split(" ");
        int ar[] = new int[st.length];
        for (int i = 0; i < ar.length; i++) {
            ar[i] = Integer.parseInt(st[i]);
            //System.out.print(ar[i] + " ");

        }

        for (int i = 0; i < ar.length; i++) {
            if (ar[i] % 2 == 0) {
                ar[i]--;
            }
        }

        for (int i = 0; i < ar.length; i++) {
            System.out.print(ar[i] + " ");
        }


    }
}
