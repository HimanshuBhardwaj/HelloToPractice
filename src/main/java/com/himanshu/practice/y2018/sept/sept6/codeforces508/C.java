package com.himanshu.practice.y2018.sept.sept6.codeforces508;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


/**
 * Created by himanshubhardwaj on 06/09/18.
 */
public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String str[] = br.readLine().split(" ");
        int A[] = new int[n];
        int B[] = new int[n];

        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(str[i]);
        }

        str = br.readLine().split(" ");

        for (int i = 0; i < n; i++) {
            B[i] = Integer.parseInt(str[i]);
        }

        Arrays.sort(A);
        Arrays.sort(B);

        int posA = A.length - 1;
        int posB = B.length - 1;

        long sum = 0l;

        for (int i = n - 1; i >= 0; i--) {
            //RotateAndSpeakGame's chance
            if (posA >= 0 && posB >= 0) {
                if (A[posA] >= B[posB]) {
                    sum += A[posA];
                    posA--;
                } else {
                    posB--;
                }
            } else if (posA >= 0) {
                sum+=A[posA];
                posA--;
            } else if(posB >=0) {
                posB--;
            }

            //TestClass's chance
            if (posA >= 0 && posB >= 0) {
                if (A[posA] <= B[posB]) {
                    sum -= B[posB];
                    posB--;
                } else {
                    posA--;
                }
            } else if (posB >= 0) {
                sum-=B[posB];
                posB--;
            } else if(posA >=0) {
                posA--;
            }
        }

        System.out.print(sum);


    }
}
