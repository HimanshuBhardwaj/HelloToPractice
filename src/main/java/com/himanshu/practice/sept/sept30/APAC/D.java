package com.himanshu.practice.sept.sept30.APAC;

import java.io.*;

/**
 * Created by himanshubhardwaj on 30/09/18.
 */
public class D {
    public static void main(String[] args) throws IOException {
        readFile("/Users/himanshubhardwaj/Desktop/apac/d/hellp.input");
        writeFile("/Users/himanshubhardwaj/Desktop/apac/d/hellp.output");


    }

    static void readFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String currentLine = null;

        while ((currentLine = br.readLine()) != null) {
            System.out.println(currentLine);
        }


        br.close();
    }

    static void writeFile(String fileName) throws IOException {
        BufferedWriter br = new BufferedWriter(new FileWriter(fileName));

        br.write("Hello how are you\n");
        br.write("fine, thank you\n");
        br.flush();
        br.close();
    }
}
