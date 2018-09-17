package com.himanshu.practice.sept.sept16.codeforces509;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.TreeSet;

/**
 * Created by himanshubhardwaj on 16/09/18.
 */
public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1000000);

        String str[] = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);
        int d = Integer.parseInt(str[2]);

        Break brk[] = new Break[n];
        TreeSet<Break> tree = new TreeSet<>();
        str = br.readLine().split(" ");

        for (int i = 0; i < str.length; i++) {
            brk[i] = new Break(i, Long.parseLong(str[i]), -1);
            tree.add(brk[i]);
        }

        int count = 1;
        Break dummyBreak = new Break(-1, -1, -1);
        Break previoudBreak = null;
        while (!tree.isEmpty()) {
            if (previoudBreak == null) {
                Break b = tree.first();
                tree.remove(b);
                b.dayTaken = count;
                previoudBreak = b;
            } else {
                if ((previoudBreak.time + d) >= m) {
                    count++;
                    previoudBreak = null;
                } else {
                    dummyBreak.time = 1 + previoudBreak.time + d;
                    Break b = tree.ceiling(dummyBreak);

                    if (b == null) {
                        count++;
                        previoudBreak = null;
                    } else {
                        tree.remove(b);
                        b.dayTaken = count;
                        previoudBreak = b;
                    }
                }
            }
        }

        PrintWriter pw = new PrintWriter(System.out);
        pw.append(String.valueOf(count));
        pw.append("\n");
        for (int i = 0; i < brk.length; i++) {
            pw.append(String.valueOf(brk[i].dayTaken));
            pw.append(" ");
        }
        pw.flush();
    }
}


class Break implements Comparable<Break> {
    int index;
    long time;
    int dayTaken;

    @java.beans.ConstructorProperties({"index", "time", "dayTaken"})
    public Break(int index, long time, int dayTaken) {
        this.index = index;
        this.time = time;
        this.dayTaken = dayTaken;
    }

    @Override
    public int compareTo(Break o) {
        return (int) (this.time - o.time);
    }
}
