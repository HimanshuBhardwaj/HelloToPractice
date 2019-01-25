package com.himanshu.practice.y2018.july.july14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 14/07/18.
 */
public class GameShopping {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);
        int costs[] = new int[n];
        int[] bills = new int[m];

        int costPos = 0;
        int bilPos = 0;
        int count = 0;

        str = br.readLine().split(" ");

        for (int i = 0; i < n; i++) {
            costs[i] = Integer.parseInt(str[i]);
        }

        str = br.readLine().split(" ");
        for (int i = 0; i < m; i++) {
            bills[i] = Integer.parseInt(str[i]);
        }


        //System.out.println();
        while (costPos < costs.length && bilPos < bills.length) {
            if (bills[bilPos] >= costs[costPos]) {
                costPos++;
                bilPos++;
                count++;
            } else {
                costPos++;
            }
            //System.out.println(costPos + "\t" + bilPos + "\t" + count);
        }

        System.out.print(count);

    }
}
