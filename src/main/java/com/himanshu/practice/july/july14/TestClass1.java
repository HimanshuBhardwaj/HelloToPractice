package com.himanshu.practice.july.july14;

/**
 * Created by himanshubhardwaj on 15/07/18.
 */
/* IMPORTANT: Multiple classes and nested static classes are supported */

/*
 * uncomment this if you want to read input.
//imports for BufferedReader
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

//import for Scanner and other utility classes


// Warning: Printing unwanted or ill-formatted data to output will cause the test cases to fail

class TestClass1 {
    public static void main(String args[]) throws Exception {
        /* Sample code to perform I/O:
         * Use either of these methods for input
         */

        //BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());


        for (int cc = 0; cc < t; cc++) {
            String str[] = br.readLine().split(" ");
            int n = Integer.parseInt(str[0]);
            int k = Integer.parseInt(str[1]);
            int arr[] = new int[n];
            str = br.readLine().split(" ");
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(str[i]);

            }
            int prevMax = -1;
            int count = 0;
            int start = 0;
            int i = 0;
            int max = Integer.MIN_VALUE;
            for (i = 0; i < n; i++) {
                if (arr[i] > k) {
                    if (start == 1 && max == k) {
                        count = count + (i - prevMax);
                        start = 0;
                    }
                    prevMax = -1;
                    max = Integer.MIN_VALUE;
                } else if (prevMax == -1 && arr[i] <= k) {
                    start = 1;
                    prevMax = i;
                    max = Math.max(max,arr[i]);
                } else {
                    max = Math.max(max, arr[i]);
                }
            }
            if (prevMax != -1 && max==k) {
                count += (n - prevMax);
            }

            System.out.println(count);
        }

/*
        //Scanner
        Scanner s = new Scanner(System.in);
        String name = s.nextLine();                 // Reading input from STDIN
        System.out.println("Hi, " + name + ".");    // Writing output to STDOUT

        */

        // Write your code here

    }
}
