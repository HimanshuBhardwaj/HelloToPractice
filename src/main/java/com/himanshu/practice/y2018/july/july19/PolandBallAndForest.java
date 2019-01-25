package com.himanshu.practice.y2018.july.july19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by himanshubhardwaj on 19/07/18.
 * ProblemSet: https://codeforces.com/contest/755/problem/C
 * Algo: GraPh Theory
 * Submission: https://codeforces.com/contest/755/submission/40537920
 */
public class PolandBallAndForest {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        String str[] = br.readLine().split(" ");
        HashMap<Integer, Integer> forestDiameter = new HashMap<>();


        int pos = 0;
        int count1 = 0;
        for (String s : str) {
            pos++;
            Integer node = Integer.parseInt(s);
//            System.out.println(pos+"\t"+node);

            if (node.equals(pos)) {
                count1++;
            } else {
                if (forestDiameter.containsKey(node)) {
                    forestDiameter.put(node, forestDiameter.get(node) + 1);
                } else {
                    forestDiameter.put(node, 1);
                }
            }
        }


        int count2 = 0;

        for (Map.Entry<Integer, Integer> entry : forestDiameter.entrySet()) {
            count2++;

        }

        System.out.print((count2 / 2) + count1);


    }
}
