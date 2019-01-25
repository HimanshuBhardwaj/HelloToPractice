package com.himanshu.practice.y2018.Aug.Aug11.codeforces;

import lombok.AllArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;

/**
 * Created by himanshubhardwaj on 11/08/18.
 */
public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);
        LinkedList<Voter> v = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            str = br.readLine().split(" ");
            v.add(new Voter(Integer.parseInt(str[0]), Integer.parseInt(str[1])));
        }
        Collections.sort(v);

        long couuntM = 0;
        int countV = 0;


        for (Voter vot : v) {
            if (vot.party == 1) {
                countV++;
            } else {
                countV++;
                couuntM += vot.cost;
            }

            if ((2 * countV) > n) {
                break;
            }
        }

        System.out.println(couuntM);
    }

//    boolean isSatisfied(int frequency[]) {
//        boolean isSatisfied = true;
//    }
}


@AllArgsConstructor
class Voter implements Comparable<Voter> {
    int party;
    long cost;

    @Override
    public int compareTo(Voter o) {
        if (this.party != o.party) {
            return this.party - o.party;
        } else {
            return (int) (this.cost - o.cost);
        }
    }
}


