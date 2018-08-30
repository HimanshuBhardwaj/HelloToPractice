package com.himanshu.practice.Aug.aug29;

/**
 * Created by himanshubhardwaj on 30/08/18.
 */
public class MaxElement {
    int maxProfit(int[] arr, int money) {
        int maxR[] = new int[30];
        maxR[arr.length - 1] = arr[30 - 1];
        int maxRPOS[] = new int[30];
        maxRPOS[29] = -1;
        int pos = 29;


        for (int i = arr.length - 2; i >= 0; i--) {
            if (arr[i] > maxR[i + 1]) {
                maxR[i] = arr[i];
                pos = i;
                maxRPOS[i] = pos;
            } else {
                maxR[i] = maxR[i + 1];
                maxRPOS[i] = pos;
            }
        }


        //assuming we can  buy and sell only once,
        int profit = Integer.MIN_VALUE;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] <= money) {
                int tProfit = (money / arr[i]) * (maxR[i] - arr[i]);

                if (tProfit > profit) {
                    profit = tProfit;
                }
            }
        }

        return money + ((profit >= 0) ? profit : 0);
    }
}
