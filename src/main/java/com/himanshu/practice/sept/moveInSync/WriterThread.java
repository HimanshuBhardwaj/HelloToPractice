package com.himanshu.practice.sept.moveInSync;

import lombok.AllArgsConstructor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by himanshubhardwaj on 05/09/18.
 * Two threads which will write even and odd numbers respectively in samefile
 */
public class WriterThread {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Writer w1 = new Writer(1);
        Writer w2 = new Writer(0);

        executor.execute(w1);
        executor.execute(w2);

    }
}


@AllArgsConstructor
class Writer implements Runnable {
    int parity;

    @Override
    public void run() {
        long value = 1;
        while (true) {
            try {
                FileWriter fw = new FileWriter("moveInSync.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw);
                out.println((2 * value + parity) + " " + System.currentTimeMillis());
                out.close();
                value++;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}