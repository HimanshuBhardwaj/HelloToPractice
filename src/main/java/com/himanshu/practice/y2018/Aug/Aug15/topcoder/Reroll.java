package com.himanshu.practice.y2018.Aug.Aug15.topcoder;

import java.util.Arrays;

/**
 * Created by himanshubhardwaj on 15/08/18.
 */
public class Reroll {
    public static void main(String[] args) {
        Reroll reroll = new Reroll();
        int[] dice = {1,3,5,3,6,4,2};

        int target = 42;

        System.out.println(reroll.minimumDice(target, dice));

    }

    public int minimumDice(int target, int[] dice) {
        int sum = 0;

        for (int x : dice) {
            sum += x;
        }

        Arrays.sort(dice);
        int count = 0;

        if (sum == target) {
            count = 0;
        } else if (sum > target) {
            //we have to decrease

            for (int i = dice.length - 1; i >= 0; i--) {
                int diff = sum - target;

                if (diff >= (dice[i] - 1)) {
                    count++;
                    sum = sum - (dice[i] - 1);
                    if (sum == target) {
                        break;
                    }
                } else {
                    count++;
                    break;
                }

            }
        } else {
            //sum < target

            for (int i = 0; i < dice.length; i++) {
                int diff = 6 - dice[i];

                if (sum + diff < target) {
                    count++;
                    sum = sum + diff;
                } else {
                    count++;
                    break;
                }
            }
        }


        return count;
    }

}
