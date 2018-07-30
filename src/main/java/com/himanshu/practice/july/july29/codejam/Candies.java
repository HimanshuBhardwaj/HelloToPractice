package com.himanshu.practice.july.july29.codejam;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by himanshubhardwaj on 29/07/18.
 * TODO: Look at it later
 *
 */
public class Candies {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());


        for (int testCase = 1; testCase <= t; testCase++) {
            long n, o, d;

            String str[] = br.readLine().split(" ");
            n = Long.parseLong(str[0]);
            o = Long.parseLong(str[1]);
            d = Long.parseLong(str[2]);

            long[] x = new long[(int) n + 1];
            long s[] = new long[(int) n + 1];

            long a, b, c, m, l;

            str = br.readLine().split(" ");
            x[1] = Long.parseLong(str[0]);
            x[2] = Long.parseLong(str[1]);
            a = Long.parseLong(str[2]);
            b = Long.parseLong(str[3]);
            c = Long.parseLong(str[4]);
            m = Long.parseLong(str[5]);
            l = Long.parseLong(str[6]);

            for (int i = 3; i <= n; i++) {
                x[i] = ((a * x[i - 1]) + (b * x[i - 2]) + c) % m;
            }

            for (int i = 1; i <= n; i++) {
                s[i] = x[i] + l;
                System.out.print(s[i] + " ");
            }
            System.out.println();


            long gettMaxCandies = getMaxCandies(n, d, o, s);
            System.out.println("Case #" + testCase + ": " + ((gettMaxCandies == Long.MIN_VALUE) ? "IMPOSSIBLE" : gettMaxCandies));
        }
    }

    private static long getMaxCandies(long n, long d, long o, long[] s) {
        Queue<Long> queue = new LinkedList<>();
        long max = Long.MIN_VALUE;
        int countOddInside = 0;
        long sumSweetness = 0; //sum of sweetness in the queue
        HashMap<Integer, Long> mapL = new HashMap<>();//num Odd to max sum


        for (int i = 1; i <= n; i++) {
            if (Math.abs(s[i] % 2) == 1) {
                if (countOddInside + 1 <= o) {
                    sumSweetness += s[i];
                    countOddInside++;
                    queue.add(s[i]);
                } else {
                    long tempSum = 0;
                    while (countOddInside >= o) {
                        long top = queue.poll();
                        tempSum = tempSum + top;
                        sumSweetness = sumSweetness - top;
                        countOddInside = countOddInside - (int) (Math.abs(top % 2));
                        max = (countOddInside <= o && sumSweetness <= d) ? Math.max(sumSweetness, max) : max;
                    }
                    sumSweetness += s[i];
                    queue.add(s[i]);
                    countOddInside++;
                }
            } else {
                queue.add(s[i]);
                sumSweetness += s[i];
            }

            if (sumSweetness <= d) {
                max = Math.max(sumSweetness, max);
            }
            System.out.println(i + "\t" + s[i] + "\t" + max + "\t" + countOddInside + "\t" + sumSweetness + "\t" + queue);
        }

        return max;
    }


}
