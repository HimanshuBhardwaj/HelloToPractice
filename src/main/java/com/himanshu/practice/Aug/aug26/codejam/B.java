package com.himanshu.practice.Aug.aug26.codejam;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by himanshubhardwaj on 26/08/18.
 */
public class B {
    static String[] output;

    public static void main(String[] args) throws IOException {
        readFile("/Users/himanshubhardwaj/Desktop/b/Source-small-attempt1.in");
        writeFile("/Users/himanshubhardwaj/Desktop/b/hello.output");

    }

    static void readFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));


        int t = Integer.parseInt(br.readLine());
        output = new String[t];


        for (int tc = 0; tc < t; tc++) {
            String str[] = br.readLine().split(" ");
            int n = Integer.parseInt(str[0]);
            int m = Integer.parseInt(str[1]);
            int p = Integer.parseInt(str[2]);

            HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
            Set<Integer> forbidden = new HashSet<>();

            for (int i = 0; i < n; i++) {
                int element = 0;
                String eS = br.readLine();

                for (int j = 0; j < p; j++) {
                    if (eS.charAt(j) == '1') {
                        element = (element * 2) + 1;
                    } else {
                        element = element * 2;
                    }
                }
                if (map.containsKey(element)) {
                    map.put(element, map.get(element) + 1);
                } else {
                    map.put(element, 1);
                }
            }


            for (int i = 0; i < m; i++) {
                int element = 0;
                String eS = br.readLine();

                for (int j = 0; j < p; j++) {
                    if (eS.charAt(j) == '1') {
                        element = (element * 2) + 1;
                    } else {
                        element = element * 2;
                    }
                }
                forbidden.add(element);
            }


            int limit = (int) Math.pow(2, p);
            int min = Integer.MAX_VALUE;

            for (int i = 0; i <= limit; i++) {

                if (!forbidden.contains(i)) {

                    int anger = computeAnger(i, map);
                    if (min > anger) {
                        min = anger;
                    }
                }
            }
            output[tc] = "Case #" + (tc + 1) + ": " + min + "\n";
        }


        br.close();
    }

    private static int computeAnger(int choice, HashMap<Integer, Integer> map) {
        int count = 0;
        int tempCou = 0;

        for (HashMap.Entry<Integer, Integer> entry : map.entrySet()) {
            int key = entry.getKey();
            int tempC = choice;
            tempCou = 0;

            while (key > 0 || tempC > 0) {
                if ((key & 1) != (tempC & 1)) {
                    tempCou++;

                }
                key = key / 2;
                tempC = tempC / 2;
            }
            tempCou = tempCou * entry.getValue();
            count += tempCou;
        }

        return count;
    }


    static void writeFile(String fileName) throws IOException {
        BufferedWriter br = new BufferedWriter(new FileWriter(fileName));
        for (int i = 0; i < output.length; i++) {
            if (i == 99) {
                System.out.print(output[i]);
            }
            br.write(output[i]);
        }
        br.flush();
        br.close();
    }
}
