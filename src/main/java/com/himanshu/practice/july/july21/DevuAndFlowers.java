package com.himanshu.practice.july.july21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by himanshubhardwaj on 21/07/18.
 * TODO: Nai samajh aaraha..understand it later and then finish
 */
public class DevuAndFlowers {
    static long PRIME = 1000000007;
    static long INVERSE[] = new long[21];


    public static void main(String[] args) throws IOException {

        for (int i = 1; i <= 20; i++) {
            INVERSE[i] = computePower(i, PRIME - 2);
        }


        long n, s;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");

        n = Long.parseLong(str[0]);
        s = Long.parseLong(str[1]);


        long f[] = new long[(int) n];
        str = br.readLine().split(" ");


        long count = 0;
        for (int i = 0; i < str.length; i++) {
            f[i] = Long.parseLong(str[i]);
            if (f[i] > s) {
                f[i] = s;
            }
            count += f[i];
        }
        if (count < s) {
            System.out.println(0);
            return;
        }

        System.out.println(computeNumberOFWaysComputationally(n, s, f));


    }

    private static long computeNumberOFWaysComputationally(long n, long s, long[] f) {
        HashMap<Long, Long> powerToCofficientMapping = new HashMap<>();
        computePowerToCofficientMapping(n, 0, f, powerToCofficientMapping);
//        System.out.println("Printing cofficient map:");
//        for (Map.Entry<Long, Long> entry : powerToCofficientMapping.entrySet()) {
//            System.out.println(entry.getKey() + "\t\t" + entry.getValue());
//        }
        long result = 0;

        for (Map.Entry<Long, Long> entry : powerToCofficientMapping.entrySet()) {
            if (s >= entry.getKey()) {
                result = (result + (entry.getValue() * getChoose(n, s - entry.getKey()))) % PRIME;
            } /*else if ((entry.getKey() - n) > s) {
                result = (result - (entry.getValue() * getChoose(n, s - (entry.getKey() - n)))) % PRIME;
            }*/
        }

        return result;
    }

    //This seems okay
    private static void computePowerToCofficientMapping(long n, int pos, long[] f, HashMap<Long, Long> powerToCofficientMapping) {
        if (pos >= n) {
            return;
        }
        if (pos == 0) {
            powerToCofficientMapping.put(f[0] + 1, -1l);
            powerToCofficientMapping.put(new Long(0), 1l);


        } else {
            Set<Long> keys = new TreeSet<>(powerToCofficientMapping.keySet());

            for (long power : keys) {
                long cofficient = powerToCofficientMapping.get(power);
                long newPower = power + f[pos] + 1;

                if (powerToCofficientMapping.containsKey(newPower)) {
                    powerToCofficientMapping.put(newPower, powerToCofficientMapping.get(newPower) - cofficient);
                } else {
                    powerToCofficientMapping.put(newPower, -1 * cofficient);
                }
                newPower = power;
                powerToCofficientMapping.put(newPower, (cofficient));
            }
        }
        computePowerToCofficientMapping(n, pos + 1, f, powerToCofficientMapping);
    }


    //seems okay
    //k will never be negative
    public static long getChoose(long n, long k) { // C(n+k-1,k)%PRIME
        long result = 1;

        for (long i = 1; i < n; i++) {
            result = (result * (n + k - i)) % PRIME;
        }

        for (int i = 1; i < n; i++) {
            // System.out.println("Inverse: " + i + "\t" + inverse + "\t" + isInverseTrue(i, inverse));
            result = (result * INVERSE[i]) % PRIME;
        }
        //System.out.println("(" + "n= " + n + ", " + (n + k - 1) + "," + k + "):  " + result);
        return result;

    }


    //Seems okay
    public static long computePower(long k, long p) {//k^p
        if (k == 0) {
            return 0;
        }
        if (p == 0 || k == 1) {
            return 1l;
        }
        if (p == 1) {
            return k % PRIME;
        }
        long temp = computePower(k, p / 2);
        if (p % 2 == 0) {
            return (temp * temp) % PRIME;
        } else {
            return (((temp * temp) % PRIME) * k) % PRIME;
        }
    }

    //Okay
    static boolean isPrime(long x) {
        long val = (long) Math.ceil(Math.sqrt(x));

        for (int i = 2; i <= val; i++) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }

    //OKay
    static boolean isInverseTrue(long x, long y) {
        if ((x * y) % PRIME == 1) {
            return true;
        }

        return false;
    }
}
