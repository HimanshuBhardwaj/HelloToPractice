package com.himanshu.practice.y2019.june.june7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Created by himanshubhardwaj on 07/06/19.
 */
public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Position position[] = new Position[n];
        for (int i = 0; i < n; i++) {
            position[i] = new Position();
        }

        position[0].row = 1;
        position[0].column = 1;
        int initialSize = 1;


        for (int i = 1; i < n; i++) {
            if (position[i - 1].row < initialSize) {
                position[i].column = position[i - 1].column;
                position[i].row = position[i - 1].row + 1;
            } else if (position[i - 1].column < initialSize) {
                position[i].column = position[i - 1].column + 1;
                position[i].row = position[i - 1].row;
            } else {
                initialSize++;
                i--;
            }
        }

        PrintWriter pw = new PrintWriter(System.out);
        pw.append(initialSize + "\n");


        for (int i = 0; i < n; i++) {
            pw.append(position[i].toString());
        }
        pw.flush();


    }
}


class Position {
    int row;
    int column;

    public Position() {
    }

    public String toString() {
        return row + " " + column + "\n";
    }
}