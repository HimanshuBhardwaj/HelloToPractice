package com.himanshu.practice.y2019.july.july24;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by himanshubhardwaj on 14/07/19.
 */
public class D {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            String[] str = br.readLine().split(" ");
            long n = Long.parseLong(str[0]);
            long k = Long.parseLong(str[1]);

            if (n == 0) {
                pw.append("Bob\n");
                continue;
            }
            if (n == 1) {
                pw.append("Alice\n");
                continue;
            }
            if (n == 2) {
                pw.append("Alice\n");
                continue;
            }


            PriorityQueue<Long> priorityQueue = new PriorityQueue<>();
            priorityQueue.add(1l);
            priorityQueue.add(2l);
            HashSet<Long> visited = new HashSet<>();
            visited.add(1l);
            boolean flag = false;

            while (!flag && !priorityQueue.isEmpty() && (priorityQueue.peek() < n)) {
                Long top = priorityQueue.poll();

                if ((n == (top + 1)) || (n == top + 2) || (n == (top + k))) {
                    pw.append("Alice\n");
                    flag = true;
                    continue;
                }

                if (!visited.contains(top + 1)) {
                    if ((top + 1) < n) {
                        visited.add(top + 1);
                        priorityQueue.add(top + 1);
                    }
                }
                if (!visited.contains(top + 2)) {
                    if (top + 2 < n) {
                        visited.add(top + 2);
                        priorityQueue.add(top + 2);
                    }
                }

                if (!visited.contains(top + k)) {
                    if (top + k < n) {
                        visited.add(top + k);
                        priorityQueue.add(top + k);
                    }
                }


            }
            if (!flag) {
                pw.append("Bob\n");
            }
        }

        pw.flush();


    }
}
