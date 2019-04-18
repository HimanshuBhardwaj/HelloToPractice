package com.himanshu.practice.y2019.April.april19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by himanshubhardwaj on 18/04/19.
 * Statement:https://codeforces.com/contest/1151/problem/B
 * TODO: Finish it
 */
public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);
        int arr[][] = new int[n][m];

        for (int i = 0; i < n; i++) {
            str = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(str[j]);
            }
        }

//        ArrayList<HashMap<Integer, ArrayList<Integer>>> numbers = new ArrayList<>(n);
        boolean isPrinted = false;

        for (int i = 0; i < 10; i++) {
            if (isPrinted) {
                continue;
            }
            HashMap<Integer, Integer>[] listNumbers = new HashMap[n];
            for (int j = 0; j < n; j++) {
                listNumbers[j] = new HashMap<>();
                for (int k = 0; k < m; k++) {
                    if ((!listNumbers[j].containsKey(1)) && isSetBit(arr[j][k], i)) {
                        listNumbers[j].put(1, k + 1);
                    }
                    if ((!listNumbers[j].containsKey(0)) && !isSetBit(arr[j][k], i)) {
                        listNumbers[j].put(0, k + 1);
                    }
                }
            }

            boolean isPresent[][] = new boolean[n][2];
            TreeMap<Integer, Integer> selectedColumn = new TreeMap<>();
            ArrayList<Integer> indexContainingboth = new ArrayList<>();
            int zeroSum = 0;
            int oneSum = 0;


            for (int ti = 0; ti < n; ti++) {
                if ((listNumbers[ti].containsKey(0) && listNumbers[ti].containsKey(1))) {
                    indexContainingboth.add(ti);
                }
                if ((listNumbers[ti].containsKey(0) && !listNumbers[ti].containsKey(1))) {
                    zeroSum = (zeroSum + 1) % 2;
                    selectedColumn.put(ti, listNumbers[ti].get(0));
                }

                if ((listNumbers[ti].containsKey(1) && !listNumbers[ti].containsKey(0))) {
                    oneSum = (oneSum + 1) % 2;
                    selectedColumn.put(ti, listNumbers[ti].get(1));
                }


                for (int j = 0; j < 2; j++) {
                    if (listNumbers[ti].containsKey(j)) {
                        isPresent[ti][j] = true;
                    } else {
                        isPresent[ti][j] = false;
                    }
                }
            }

            if (zeroSum == 0 && oneSum == 0) {
                if (indexContainingboth.size() % 2 == 0) {
                    isPrinted = true;
                    System.out.println("TAK");
                    for (int ti = 0; ti < indexContainingboth.size(); ti++) {
                        selectedColumn.put(indexContainingboth.get(i), listNumbers[indexContainingboth.get(i)].get(ti % 2));
                    }

                    for (Map.Entry<Integer, Integer> entry : selectedColumn.entrySet()) {
                        System.out.print(entry.getKey() + " ");
                    }
                } else {
                    continue;
                }

            } else if (zeroSum == 0 && oneSum == 1) {


            } else if (zeroSum == 1 && oneSum == 0) {

            } else if (zeroSum == 1 && oneSum == 1) {

            }


//
//            boolean commulativeXOR[][][] = new boolean[n][2][2];
//            commulativeXOR[0][0][0] = isPresent[0][0];
//            commulativeXOR[0][1][0] = false;
//            commulativeXOR[0][0][1] = false;
//            commulativeXOR[0][1][1] = isPresent[0][1];
//
//
//            for (int ti = 0; ti < n; ti++) {
//                commulativeXOR[i][0][0] = isPresent[i][0] && (commulativeXOR[i - 1][0][1] || commulativeXOR[i - 1][1][1]);
//                commulativeXOR[i][1][0] = isPresent[i][1] && (commulativeXOR[i - 1][0][1] || commulativeXOR[i - 1][1][1]);
////                commulativeXOR[i][0][1] = isPrinted[i][0] &&
//                commulativeXOR[i][1][1] = isPresent[0][1];
//            }


        }


    }

    //tellis if bitNum is set in num
    static boolean isSetBit(int num, int bitNum) {
        if (((1 << bitNum) & (num)) == 0) {
            return false;
        }
        return true;
    }
}
