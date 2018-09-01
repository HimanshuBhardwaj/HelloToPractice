package com.himanshu.practice.sept;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Created by himanshubhardwaj on 01/09/18.
 */
public class Source {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pr = new PrintWriter(System.out);
        int n = Integer.parseInt(br.readLine());

        //if you just have to rotate image, it doesnot matter if this is integer of String. String is easy to print. for Int we will have to parse it twice
        String orignal[][] = new String[n][n];

        for (int i = 0; i < n; i++) {
            String str[] = br.readLine().split(" ");
            for (int j = 0; j < str.length; j++) {
                orignal[j][n - 1 - i] = str[j];
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                pr.append(orignal[i][j]);
                if(j != (n-1)){
                    pr.append(" ");
                } else {
                    if(i != (n-1)) {
                        pr.append("\n");
                    }
                }
            }
        }

        pr.flush();
        pr.close();
        br.close();
    }
}
