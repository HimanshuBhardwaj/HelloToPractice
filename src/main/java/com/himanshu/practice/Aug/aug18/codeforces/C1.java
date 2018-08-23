package com.himanshu.practice.Aug.aug18.codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Queue;

/**
 * Created by himanshubhardwaj on 18/08/18.
 */
public class C1 {
    public static void main(String[] args) throws IOException {
        long LONG_MAX = Long.MAX_VALUE / 10000000000l;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            String st[] = br.readLine().split(" ");
            long arr[] = new long[n];
            for (int i = 0; i < n; i++) {
                arr[i] = Long.parseLong(st[i]);
            }
            Arrays.sort(arr);
            long queue[] = new long[4];
            long queue1[] = new long[4];
            long queue2[] = new long[4];
            int pos = -1;
            long globalMax = LONG_MAX;
            long gx = 0;
            long gy = 0;

            for (int i = 0; i < n; i++) {
                pos = (pos + 1) % 4;
                queue[pos] = arr[i];

                if (i >= 3) {
                    for (int j = 0; j < queue1.length; j++) {
                        queue1[j] = queue[j];
                    }
                    Arrays.sort(queue1);
                    if ((queue1[0] == queue1[1]) && (queue1[2] == queue1[3])) {
                        long x = queue1[0];
                        long y = queue1[2];
                        long temp = (((x * x) + (y * y) + (2*x*y)));

                        if ((Double.compare(globalMax, LONG_MAX) == 0) || (Long.compare(temp * gx * gy, x * y * globalMax) == -1)) {
                            globalMax = temp;
                            gx = x;
                            gy = y;
                            queue2[0] = queue1[0];
                            queue2[1] = queue1[1];
                            queue2[2] = queue1[2];
                            queue2[3] = queue1[3];
                        }
                    }
                }
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < queue2.length; i++) {
                sb.append(queue2[i]);
                sb.append(" ");
            }
            System.out.println(sb.toString());
        }


    }

    private static boolean satisFiesConstraint(Queue<Integer> queue) {
        return false;
    }
}