package com.himanshu.practice.y2018.sept.moveInSync;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by himanshubhardwaj on 05/09/18.
 * Reader: Read file in which two threads/process were writing even/odd and print even/odd subsequently
 *
 */
public class Reader {
    static Queue<Number> odd = new LinkedList<>();
    static Queue<Number> even = new LinkedList<>();

    public static void main(String[] args) throws IOException, InterruptedException {
        BufferedReader br = new BufferedReader(new FileReader("moveInSync.txt"));


        for (int i = 0; i < 200000; i++) {
            Thread.sleep(1000);

            if (br.ready()) {
                String[] str = br.readLine().split(" ");
                int n = Integer.parseInt(str[0]);
                long timestamp = Long.parseLong(str[1]);

                if (n % 2 == 0) {
                    even.add(new Number(n, timestamp));
                } else {
                    odd.add(new Number(n, timestamp));
                }

                if (i % 2 == 0) {
                    if (!even.isEmpty()) {
                        System.out.println(even.poll());
                    } else {
                        Thread.sleep(500);
                        i--;
                    }
                } else {
                    if (!odd.isEmpty()) {
                        System.out.println(odd.poll());
                    } else {
                        Thread.sleep(500);
                        i--;
                    }
                }
            } else {
                Thread.sleep(500);
                i--;
            }
        }


    }
}


@ToString
@AllArgsConstructor
class Number {
    int n;
    long timestamp;
}
