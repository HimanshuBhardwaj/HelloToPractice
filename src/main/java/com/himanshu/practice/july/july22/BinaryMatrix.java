package com.himanshu.practice.july.july22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by himanshubhardwaj on 22/07/18.
 */
public class BinaryMatrix {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n, m;
        String str[] = br.readLine().split(" ");

        n = Integer.parseInt(str[0]);
        m = Integer.parseInt(str[1]);

        ArrayList<Number> numbers = new ArrayList<>(n);


        for (int i = 0; i < n; i++) {
            String num = br.readLine();
            Number number = new Number(m, i);

            for (int j = 0; j < m; j++) {
                if (num.charAt(j) == '0') {
                    number.number[j] = 0;
                } else {
                    number.number[j] = 1;
                }
            }

            numbers.add(number);
        }

        Collections.sort(numbers);
        System.out.print((numbers.get(0).index + 1));

//        for(Number nn:numbers) {
//            System.out.println(nn);
//        }
//        System.out.println();System.out.println();

    }


}

class Number implements Comparable<Number> {
    int[] number;
    int index;


    public Number(int size, int index) {
        this.index = index;
        this.number = new int[size];
    }

    @Override
    public int compareTo(Number o) {
        //this.x-o.x for increasing
        for (int i = 0; i < this.number.length; i++) {
            if ((o.number[i] - this.number[i]) != 0) {
                return o.number[i] - this.number[i];
            }
        }
        return this.index - o.index;
    }

    public String toString() {
        String str = index + ": ";

        for (int i = 0; i < number.length; i++) {
            str = str + number[i];
        }
        return str;
    }
}


/*
*
*
*

5 4
0100
1010
0010
1010
0011



* */
