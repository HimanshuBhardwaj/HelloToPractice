package com.himanshu.practice.july.july26.codeforces;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.TreeMap;

/**
 * Created by himanshubhardwaj on 26/07/18.
 */
public class A {
    static TreeMap<String, Integer> DP = new TreeMap<>();
    static int MAX = Integer.MAX_VALUE - 5000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int k = Integer.parseInt(str[1]);
        char[] states = br.readLine().toCharArray();

//        if (((int) Math.ceil(((double) n) / 2)) < k) {
//            System.out.println("-1");
//            return;
//        }
        Arrays.sort(states);

        int value = MAX;
        for (int i = 0; i < states.length; i++) {
            value = Math.min(getMinimumStates(i, k, states), value);
        }

        if (value == MAX) {
            System.out.print("-1");
        } else {
            System.out.print(value);
        }

    }

    private static int getMinimumStates(int state, int k, char[] states) {
        if (state < 0) {
            return MAX;
        }

        String stateMAp = getState(state, k);
        if (DP.containsKey(stateMAp)) {
            return DP.get(stateMAp);
        }
        int value = 0;

        if (k == 1) {
            value = states[state] - 'a' + 1;
            for (int i = state - 1; i >= 0; i--) {
                value = Math.min(value, 1 + states[i] - 'a');
            }
        } else {

            int temp = MAX;
            for (int i = state - 1; i >= 0; i--) {
                temp = Math.min(getMinimumStates(i, k, states), temp);
            }

            for (int i = state - 1; i >= 0; i--) {
                if ((states[state] - states[i]) > 1) {
                    temp = Math.min(temp, (states[state] - 'a' + 1) + getMinimumStates(i, k - 1, states));
                } else {
                    continue;
                }
            }
            value = temp;
        }

        DP.put(stateMAp, value);
        return value;
    }

    private static String getState(int state, int k) {
        return state + "|" + k;
    }
}

