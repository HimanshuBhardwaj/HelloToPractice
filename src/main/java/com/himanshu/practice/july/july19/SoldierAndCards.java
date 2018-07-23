//package com.himanshu.practice.july.july19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by himanshubhardwaj on 19/07/18.
 */
public class SoldierAndCards {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numCard = Integer.parseInt(br.readLine());
        int MAX_POSSIBLE = 3629800;
        String[] str = br.readLine().split(" ");
        Queue<Integer> person1 = new LinkedList<>();
        Queue<Integer> person2 = new LinkedList<>();

        for (int i = 1; i < str.length; i++) {
            person1.add(Integer.parseInt(str[i]));
        }

        String str1[] = br.readLine().split(" ");

        for (int i = 1; i < str1.length; i++) {
            person2.add(Integer.parseInt(str1[i]));
        }

        int count = 0;

        //System.out.print(person1 + "\t\t");
        //System.out.println(person2);
        //System.out.println();

        while (!person1.isEmpty() && !person2.isEmpty() && (count < MAX_POSSIBLE)) {
            count++;
            int p1 = person1.poll();
            int p2 = person2.poll();
            //System.out.print(person1 + "\t\t");
            //System.out.println(person2);

            if (p1 > p2) {
                person1.add(p2);
                person1.add(p1);
            } else {
                person2.add(p1);
                person2.add(p2);
            }
        }

        if(count< MAX_POSSIBLE) {
            int winner = (person1.isEmpty()) ? 2 : 1;
            System.out.print(count + " " + winner);
        }else {
            System.out.print("-1");
        }

    }
}
