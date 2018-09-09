package com.himanshu.practice.sept.sept9.epsilon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Created by himanshubhardwaj on 09/09/18.
 */
public class RotateAndSpeakGame {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pr = new PrintWriter(System.out);


        while ((t--) > 0) {

            String str[] = br.readLine().split(" ");
            int n = Integer.parseInt(str[0]);
            str = br.readLine().split(" ");
            int arr[] = new int[n];

            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(str[i]);
            }

            int q = Integer.parseInt(br.readLine());
            int start = 0;
            for (int i = 0; i < q; i++) {
                str = br.readLine().split(" ");
                int a = Integer.parseInt(str[0]);
                int b = Integer.parseInt(str[1]);

                switch (a) {
                    case 1:
                        start = (start + b) % n;
                        break;
                    case 2:
                        start = (start - b + n) % n;
                        break;
                    case 3:
                        pr.append(String.valueOf(arr[(start + b) % n]));
                        pr.append("\n");
                        break;
                }
            }
        }
        pr.flush();
        pr.close();
        br.close();
    }
}
