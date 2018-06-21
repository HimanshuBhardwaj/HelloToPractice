package com.himanshu.practice.june21;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by himanshubhardwaj on 21/06/18.
 *
 *
 */


public class KnapSack {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numItem = Integer.parseInt(sc.next());
        Item items[] = new Item[numItem];

        for (int i = 0; i < numItem; i++) {
            StringTokenizer st = new StringTokenizer(sc.next(), ",");
            int value = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            items[i] = new Item(value, weight);
        }

        for (Item i : items) {
            System.out.println(i);
        }

        System.out.println();
        System.out.println(maxValue(99, items.length - 1, items));
        System.out.println();
        System.out.println(maxValueDP(99, items));

    }

    static int maxValueDP(int capacity, Item[] items) {

        int DP[][] = new int[items.length][capacity + 1];
        int max = 0;


        for (int i = 0; i < items.length; i++) {
            for (int j = 0; j <= capacity; j++) {
                if (i == 0) {
                    if (j >= items[0].getWeight()) {
                        DP[0][j] = items[0].getValue();
                        max = Math.max(max, DP[0][j]);
                    }
                } else if (j >= items[i].getWeight()) {
                    DP[i][j] = Math.max(DP[i - 1][j], items[i].getValue() + DP[i - 1][j - items[i].getWeight()]);
                    max = Math.max(max, DP[i][j]);
                } else if (j < items[i].getWeight()) {
                  DP[i][j] = DP[i-1][j];
                }
            }
        }
        return max;
    }


    //return maxValue from capacity with 0,item elements
    static int maxValue(int capacity, int itemIndex, Item[] items) {
        //one way is to save state in hashmap and do memorisation
        if (itemIndex < 0 || capacity < 0) {
            return 0;
        }

        if (capacity < items[itemIndex].weight) {
            return maxValue(capacity, itemIndex - 1, items);
        } else {
            return Math.max(items[itemIndex].getValue() + maxValue(capacity - items[itemIndex].getWeight(), itemIndex - 1, items),
                    maxValue(capacity, itemIndex - 1, items));
        }
    }
}


@AllArgsConstructor
@Getter
class Item {
    int value;
    int weight;

    public String toString() {
        return "value: " + value + "\tweight: " + weight;
    }
}
