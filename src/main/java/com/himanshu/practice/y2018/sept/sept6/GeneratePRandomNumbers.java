package com.himanshu.practice.y2018.sept.sept6;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by himanshubhardwaj on 06/09/18.
 * Problem Statement: https://codeforces.com/contest/1039/problem/A
 */
public class GeneratePRandomNumbers {
    static int totalNumberRequired;
    static int totalCallsMAde;
    static int numberOFPrimesUnderConsideration;
    static ArrayList<Long> listofPrimes;
    static int numberofIterationsforEachPrime;
    static HashSet<Long> oneMillionRandomNumbers;
    static int numberOFtimesFunctionCalled = 0;
    private static long randomMaths;

    public static void main(String[] args) {
        numberOFPrimesUnderConsideration = 1000000;
        numberofIterationsforEachPrime = 10;
        oneMillionRandomNumbers = new HashSet<Long>(1000000);
        totalNumberRequired = 1000000;

        computePrimes();
        System.out.println("List of primes: ");
        System.out.println(listofPrimes);
        int number = 0;

//        while (oneMillionRandomNumbers.size() < totalNumberRequired) {
//            long getRandom = getRandom();
//
//            //System.out.println(getRandom + "\t" + oneMillionRandomNumbers.contains(getRandom) + "\t" + oneMillionRandomNumbers.size());
//            if (!oneMillionRandomNumbers.contains(getRandom)) {
//                oneMillionRandomNumbers.add(getRandom);
//            }
//        }
//
//        System.out.println("Collissions: " + (numberOFtimesFunctionCalled - totalNumberRequired));

        /**
         *
         * My second method
         * 1000: 0
         * 10000: 77
         * 100000:8342
         *1000000: 4808891
         */

        numberOFtimesFunctionCalled = 0;
        oneMillionRandomNumbers = new HashSet<>();
        while (oneMillionRandomNumbers.size() < totalNumberRequired) {
            long getRandom = getRandomMaths();

            //System.out.println(getRandom + "\t" + oneMillionRandomNumbers.contains(getRandom) + "\t" + oneMillionRandomNumbers.size());
            if (!oneMillionRandomNumbers.contains(getRandom)) {
                oneMillionRandomNumbers.add(getRandom);
            }
        }
        System.out.println("Collissions: " + (numberOFtimesFunctionCalled - totalNumberRequired));
        System.out.println(oneMillionRandomNumbers.size());

        /*
        * Using this I did not any collissio at all
        *
        * */


    }


    private static void computePrimes() {
        listofPrimes = new ArrayList<>();
        for (long i = 200000; listofPrimes.size() < numberOFPrimesUnderConsideration; i++) {
            if (isPrime(i)) {
                listofPrimes.add(i);
            }
        }
    }


    private static boolean isPrime(long x) {
        int sqrt = (int) Math.sqrt(x) + 1;

        for (int i = 2; i <= sqrt; i++) {
            if ((x % sqrt) == 0) {
                return false;
            }
        }
        return true;
    }


    public static long getRandom() {
        numberOFtimesFunctionCalled++;
        int pos1 = (int) (Math.random() * (listofPrimes.size()));
        int pos2 = (int) (Math.random() * (listofPrimes.size()));

        long prime1 = listofPrimes.get(pos1);
        long prime2 = listofPrimes.get(pos2);
        long seed = System.currentTimeMillis();

        for (int i = 0; i < numberofIterationsforEachPrime; i++) {
            pos1 = (int) (Math.random() * (listofPrimes.size()));
            pos2 = (int) (Math.random() * (listofPrimes.size()));
            prime1 = listofPrimes.get(pos1);
            prime2 = listofPrimes.get(pos2);
            seed = (seed * prime1) % prime2;
        }

        return seed;
    }

    public static long getRandomMaths() {
        numberOFtimesFunctionCalled++;

        return (long) (Math.random() * 100000000d);
    }
}
