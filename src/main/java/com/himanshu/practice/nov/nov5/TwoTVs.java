package com.himanshu.practice.nov.nov5;





import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by himanshubhardwaj on 06/11/18.
 * Statement: https://codeforces.com/contest/845/problem/C
 * Algo: Sorting
 * Submission:https://codeforces.com/contest/845/submission/45341247
 */
public class TwoTVs {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        ArrayList<Timing> listTiming = new ArrayList<>(n);


        for (int i = 0; i < n; i++) {
            String[] str = br.readLine().split(" ");
            listTiming.add(new Timing(Integer.parseInt(str[0]), 0));
            listTiming.add(new Timing(Integer.parseInt(str[1]), 1));
        }

        Collections.sort(listTiming);
        //System.out.println(listTiming);

        int min_tv_req = 0;
        int running_count = 0;


        for (int i = 0; i <listTiming.size(); i++) {
            min_tv_req = Math.max(running_count, min_tv_req);
            //System.out.println(i+"\t"+min_tv_req);
            if (listTiming.get(i).isEnd ==1) {
                running_count--;
            } else {
                running_count++;
            }
        }

        if(min_tv_req > 2) {
            System.out.print("NO");
        } else {
            System.out.print("YES");
        }


    }
}


class Timing implements Comparable<Timing> {
    int time;
    int isEnd;

    @java.beans.ConstructorProperties({"time", "isEnd"})
    public Timing(int time, int isEnd) {
        this.time = time;
        this.isEnd = isEnd;
    }

    @Override
    public int compareTo(Timing o) {
        if (this.time != o.time) {
            return this.time - o.time;
        } else {
            return this.isEnd - o.isEnd;
        }
    }
}
