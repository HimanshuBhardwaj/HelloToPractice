package com.himanshu.practice.y2018.sept.moveInSync;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by himanshubhardwaj on 05/09/18.
 * RotateAndSpeakGame process which will append even numbes to file
 */
public class WriterB {
    public static void main(String[] args) throws IOException, InterruptedException {
        for (int i = 0; i < 10000; i++) {
            Thread.sleep(500);
            FileWriter fw = new FileWriter("moveInSync.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw);
            out.println((2 * i) + " " + System.currentTimeMillis());
            out.close();
        }
    }
}
