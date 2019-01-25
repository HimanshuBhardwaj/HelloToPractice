package com.himanshu.practice.y2018.Aug.aug26.codejam;

import java.io.*;
import java.util.Arrays;

/**
 * Created by himanshubhardwaj on 26/08/18.
 */
public class A {
    static String[] output;

    public static void main(String[] args) throws IOException {
        readFile("/Users/himanshubhardwaj/Desktop/a/RotateAndSpeakGame-large.in");
        writeFile("/Users/himanshubhardwaj/Desktop/a/output");

    }

    static void readFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        int t = Integer.parseInt(br.readLine());
        output = new String[t];


        for (int tc = 0; tc < t; tc++) {
            String str[] = br.readLine().split(" ");
            int n = Integer.parseInt(str[0]);
            int k = Integer.parseInt(str[1]);

            long arr[] = new long[n];

            str = br.readLine().split(" ");

            for (int i = 0; i < str.length; i++) {
                arr[i] = Long.parseLong(str[i]);
            }


            Arrays.sort(arr);

            int day = 0;
            int count = 0;
            int index = 0;

            while (day < arr.length && (index < arr.length)) {
                int countofDay = 0;
                while (countofDay < k && (index < arr.length)) {
                    if (arr[index] > day) {
                        countofDay++;
                    }
                    index++;
                }
                count += countofDay;
                day++;
            }

            output[tc] = "Case #" + (tc + 1) + ": " + count + "\n";
        }


        br.close();
    }


    static void writeFile(String fileName) throws IOException {
        BufferedWriter br = new BufferedWriter(new FileWriter(fileName));
        for (int i = 0; i < output.length; i++) {
            br.write(output[i]);
            System.out.print(output[i]);
        }
        br.flush();
        br.close();
    }


}
