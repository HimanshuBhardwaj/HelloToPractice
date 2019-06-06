package com.himanshu.practice.y2019.june.june1;

/**
 * Created by himanshubhardwaj on 04/06/19.
 */
public class POCSTRTOK {
    public static void main(String[] args) {
        String str = "Himanshu Bhardwaj,is.A Great Person";
        String [] s = str.split(", .");
        for (String ss:s) {
            System.out.println(ss);
        }
    }
}
