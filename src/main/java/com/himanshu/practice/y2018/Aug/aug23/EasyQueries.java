package com.himanshu.practice.y2018.Aug.aug23;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;

/**
 * Created by himanshubhardwaj on 25/08/18.
 */
public class EasyQueries {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int q = Integer.parseInt(str[1]);
        str = br.readLine().split(" ");
        TreeSet<Integer> set = new TreeSet<>();


        for (int i = 0; i < str.length; i++) {
            int element = Integer.parseInt(str[i]);
            if (element == 1) {
                set.add(i);
            }
        }

        System.out.println(set);


        for (int i = 0; i < q; i++) {
            str = br.readLine().split(" ");
            int opt = Integer.parseInt(str[0]);
            int index = Integer.parseInt(str[1]);


            if (opt == 0) {
                Integer left = set.lower(index);
                Integer right = set.higher(index);
                System.out.println(((left == null) ? -1 : left) + " " + ((right == null) ? -1 : right));
            } else {
                set.add(index);
            }
        }

        br.close();


    }
}
