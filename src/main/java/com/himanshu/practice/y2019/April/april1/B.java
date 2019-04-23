package com.himanshu.practice.y2019.April.april1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by himanshubhardwaj on 01/04/19.
 */
public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            char[] str = br.readLine().toCharArray();


            LinkedList<Character> list = new LinkedList<Character>();

            for (int i = 0; i < str.length; i++) {
                list.addLast(new Character(str[i]));
            }

            //System.out.println(list);

            boolean isHet = false;
            int delete = 0;

            while (!isHomo(list)) {

                for (int i = 0; i < list.size(); i++) {
                    for (int j = i + 1; j < list.size(); j++) {
                        if (!list.get(i).equals(list.get(j))) {

                        }
                    }

                }

            }

            System.out.println(list.size() - 1);


        }
    }


    static boolean isHomo(LinkedList<Character> list) {
        if (list == null || list.size() == 1) {
            return true;
        }

        for (int i = 1; i < list.size(); i++) {
            if (!list.get(i).equals(list.get(0))) {
                return false;
            }
        }
        return true;
    }
}
