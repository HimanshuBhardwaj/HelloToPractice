package com.himanshu.practice.y2018.nov.nov6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 06/11/18.
 * Statement: https://codeforces.com/contest/863/problem/C
 * Algo:
 * Submission:
 */

//TODO: Complete it; Not Working
public class OneTwoThree {
    static long A[][] = new long[3][3];
    static long B[][] = new long[3][3];
    static long cycle_count = 0;
    static long pointA = 0;
    static long pointB = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        long k = Long.parseLong(str[0]);
        long a = Long.parseLong(str[1]);
        long b = Long.parseLong(str[2]);


        long tA = a;
        long tB = b;


        for (int i = 0; i < 3; i++) {
            str = br.readLine().split(" ");
            A[i][0] = Long.parseLong(str[0]);
            A[i][1] = Long.parseLong(str[1]);
            A[i][2] = Long.parseLong(str[2]);
        }


        for (int i = 0; i < 3; i++) {
            str = br.readLine().split(" ");
            B[i][0] = Long.parseLong(str[0]);
            B[i][1] = Long.parseLong(str[1]);
            B[i][2] = Long.parseLong(str[2]);
        }

        cycleSize(tA, tB, k);

        long tpA = pointA * (k / cycle_count);
        long tpB = pointB * (k / cycle_count);

        pointB = 0;
        pointA = 0;
        k = k % cycle_count;

        cycleSize(tA, tB, k);

        tpA += pointA;
        tpB += pointB;

        System.out.print(tpA + " " + tpB);
    }

    private static void cycleSize(long tA, long tB, Long restriction) {
        System.out.println("Computing cycle");
        long nA = tA;
        long nB = tB;
        do {
            System.out.println(nA + " " + nB + "\t\t" + tA + " " + tB);
            if (restriction != null) {
                if (cycle_count == restriction) {
                    break;
                }
            }

            long tTA = nA;
            long tTB = nB;

            cycle_count++;
            nA = A[(int) (tTA - 1)][(int) (tTB - 1)];
            nB = B[(int) (tTB - 1)][(int) (tTA - 1)];

            if (nA == nB) {
                continue;
            } else {
                if (nA == 1) {
                    if (nB == 1) {
                        continue;
                    } else if (nB == 2) {
                        pointB++;
                    } else if (nB == 3) {
                        pointA++;
                    }
                } else if (nA == 2) {
                    if (nB == 1) {
                        pointA++;
                    } else if (nB == 2) {
                        continue;
                    } else if (nB == 3) {
                        pointB++;
                    }

                } else if (nA == 3) {
                    if (nB == 1) {
                        pointB++;
                    } else if (nB == 2) {
                        pointA++;
                    } else if (nB == 3) {
                        continue;
                    }
                }
            }


        } while ((nA != tA && nB != tB));

    }
}
