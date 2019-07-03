package com.himanshu.practice.y2019.july;

import lombok.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by himanshubhardwaj on 03/07/19.
 * //TODO:
 */
public class SellingSouvenirs {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);

        ArrayList<Long>[] weights = new ArrayList[3];
        weights[0] = new ArrayList<>();
        weights[1] = new ArrayList<>();
        weights[2] = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            str = br.readLine().split(" ");
            weights[Integer.parseInt(str[0]) - 1].add(Long.parseLong(str[1]));
        }


        Collections.sort(weights[0], Comparator.reverseOrder());
        Collections.sort(weights[1], Comparator.reverseOrder());
        Collections.sort(weights[2], Comparator.reverseOrder());

        long maxPossibleWeight[] = new long[m + 1];

        computeQualitywith1And2Weights(maxPossibleWeight, weights[0], weights[1]);

        long max = maxPossibleWeight[m];
        long prefixSum = 0l;

        for (int i = 0; i < weights[2].size(); i++) {
            if ((i + 1) * 3 > m) {
                break;
            }
            prefixSum += weights[2].get(i);
            max = Math.max(prefixSum + maxPossibleWeight[m - 3 * (i + 1)], max);
        }

        System.out.print(max);

    }

    private static void computeQualitywith1And2Weights(long[] maxPossibleWeight, ArrayList<Long> weightOne, ArrayList<Long> weightTwo) {
        MAxValue[] maxValues = new MAxValue[maxPossibleWeight.length];

        for (int i = 0; i < maxValues.length; i++) {
            maxValues[i] = new MAxValue();
        }

        int pos1 = 0;
        int pos2 = 0;

        for (int i = 1; i < maxPossibleWeight.length; i++) {
            if (i == 1) {
                if (weightOne.size() > 0) {
                    maxValues[i].maxValue = 1;
                    maxValues[i].onePos = 1;
                    maxValues[i].twoPos = 0;
                }
            } else {
                //if ()

            }


        }

    }
}


class MAxValue {
    long maxValue = 0;
    int onePos = 0;
    int twoPos = 0;
}
