package com.himanshu.practice.Aug.aug29;

/**
 * Created by himanshubhardwaj on 30/08/18.
 */
public class MaxElement {
    int maxProfit(int[] arr, int money) {
        int maxR[] = new int[30];
        maxR[30 - 1] = arr[30 - 1];
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


        int profitPerShare = Integer.MIN_VALUE;
        int newMoney = money;
        int buy = Integer.MAX_VALUE;
        int newTotalProfit = 0;

        for (int i = 0; i < arr.length; i++) {
            //suppose we sell stock today then money we will be having
            if (buy < Integer.MAX_VALUE) {
                profitPerShare = arr[i] - buy;
                if (profitPerShare > 0) {
                    newTotalProfit = (newMoney / buy) * profitPerShare;
                    newMoney = newMoney + newTotalProfit;
                }
            }
            //this will always greater than money(orignal money)
            if (arr[i] <= newMoney) {
                buy = arr[i];
            }
        }

        return money + ((profitPerShare >= 0) ? profitPerShare : 0);
    }
}

//2, 3, 4, 5