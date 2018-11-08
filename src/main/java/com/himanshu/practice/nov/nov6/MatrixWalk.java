package com.himanshu.practice.nov.nov6;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 06/11/18.
 */
public class MatrixWalk {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        String[] str = br.readLine().split(" ");
        int arr[] = new int[str.length];


        Integer width = null;
        int max = -1;
        boolean could = true;


        for (int i = 0; could && (i < arr.length); i++) {
            arr[i] = Integer.parseInt(str[i]);
            max = Math.max(max, arr[i]);
            if (i > 0) {
                if (Math.abs(arr[i] - arr[i - 1]) == 0) {
                    could = false;
                } else if (Math.abs(arr[i] - arr[i - 1]) != 1) {
                    if (width == null) {
                        width = Math.abs(arr[i] - arr[i - 1]);
                    } else if (width != Math.abs(arr[i] - arr[i - 1])) {
                        could = false;
                    }
                }
            }
        }


        if (could) {
            if (width == null) {
                width = 1;
            }
            int height = (int) Math.ceil((double) max / width);

            if (simulate(arr, width, height)) {
                System.out.println("YES");
                System.out.print(height + " " + width);
            } else {
                System.out.print("NO");
            }
        } else {
            System.out.print("NO");
        }

    }

    private static boolean simulate(int[] arr, Integer width, int height) {
        int r = arr[0] / width;
        int c = arr[0] % width;

        boolean could = true;

        //TODO: Complete it


        return could;
    }
}
