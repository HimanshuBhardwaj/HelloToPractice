package com.himanshu.practice.y2018.sept.sept15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by himanshubhardwaj on 15/09/18.
 * given 5 points, detect if they form T
 */
public class SolutionA {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        int t = Integer.parseInt(br.readLine());
        PrintWriter pr = new PrintWriter(System.out);
        String str[] = null;

        while ((t--) > 0) {
            ArrayList<Points> points = new ArrayList<>();

            for (int i = 0; i < 5; i++) {
                str = br.readLine().split(" ");
                int x = Integer.parseInt(str[0]);
                int y = Integer.parseInt(str[1]);
                points.add(new Points(x, y));
            }

            boolean result = normalise(points);
            pr.append(result ? "Yes" : "No");
            if(t >0)
            pr.append("\n");
        }
        pr.flush();

    }

    private static boolean normalise(ArrayList<Points> points) {
        int minX = points.get(0).x;
        int minY = points.get(0).y;

        for (int i = 1; i < points.size(); i++) {
            minX = Math.min(minX, points.get(i).x);
            minY = Math.min(minY, points.get(i).y);
        }

        for (int i = 0; i < points.size(); i++) {
            points.get(i).x -= minX;
            points.get(i).y -= minY;
        }

        int xP[] = new int[3];
        int yP[] = new int[3];


        for (int i = 0; i < points.size(); i++) {
            if (points.get(i).x > 2 || points.get(i).y > 2) {
                return false;
            } else {
                xP[points.get(i).x]++;
                yP[points.get(i).y]++;
            }
        }


        int arr[][] = new int[3][3];
        int tempP[][] = {{1, 1, 1}, {0, 1, 0}, {0, 1, 0}};
        IntSquareMatrix.mat = tempP;

        for (int i = 0; i < points.size(); i++) {
            arr[points.get(i).x][points.get(i).y] = 1;
        }

        for (int i = 0; i < 5; i++) {
            if (isEqual(IntSquareMatrix.mat, arr)) {
                return true;
            } else {
                IntSquareMatrix.rotateClockwise();
            }
        }
        return false;
    }

    private static boolean isEqual(int[][] tempP, int[][] arr) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tempP[i][j] != arr[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}


class Points {
    int x;
    int y;

    @java.beans.ConstructorProperties({"x", "y"})
    public Points(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "Points(x=" + this.x + ", y=" + this.y + ")";
    }
}

class IntSquareMatrix {

    static int[][] mat;

    /**
     * Creates a matrix from given array.
     */

    /**
     * Creates a matrix with continuous values for tests.
     */

    static public void rotateClockwise() {
        int temp;
        final int len = mat.length;
        // For each concentric square around the middle of the matrix to rotate...
        // This value will be used as (m, n) offset when moving in.
        // Integer division by 2 will skip center if odd length.
        for (int s = 0; s < len / 2; s++)
            // for the length of this ring
            for (int i = 0; i < len - 2 * s - 1; i++) {
                temp = mat[s][s + i];
                mat[s][s + i] = mat[len - s - i - 1][s];
                mat[len - s - i - 1][s] = mat[len - s - 1][len - s - i - 1];
                mat[len - s - 1][len - s - i - 1] = mat[s + i][len - s - 1];
                mat[s + i][len - s - 1] = temp;
            }
    }
}