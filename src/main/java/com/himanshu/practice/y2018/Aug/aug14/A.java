package com.himanshu.practice.y2018.Aug.aug14;


import java.util.StringTokenizer;

/**
 * Created by himanshubhardwaj on 14/08/18.
 * Grab Test
 */
public class A {
    public static void main(String[] args) {
        SolutionA s = new SolutionA();
        System.out.println(s.solution("00:00", "23:59"));
    }
}


class SolutionA {
    public int solution(String E, String L) {
        StringTokenizer st = new StringTokenizer(E, ":");
        int eHour = Integer.parseInt(st.nextToken());
        int eMin = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(L, ":");
        int lHour = Integer.parseInt(st.nextToken());
        int lMin = Integer.parseInt(st.nextToken());


        int cost = 2; //entering cost
        eHour++;
        cost += 3; //first hour cost

        cost += (numberOFHoursInBetween(eHour, eMin, lHour, lMin) * 4); //remaining hours cost

        return cost;
    }

    private int numberOFHoursInBetween(int eHour, int eMin, int lHour, int lMin) {
        int numberOfHours = 0;

        if (eHour > lHour) {
            numberOfHours = 0;
        } else {
            numberOfHours = lHour - eHour;
            numberOfHours += (eMin >= lMin) ? 0 : 1;
        }

        return numberOfHours;
    }
}
