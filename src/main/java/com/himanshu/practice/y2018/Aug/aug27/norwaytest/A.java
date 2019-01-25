package com.himanshu.practice.y2018.Aug.aug27.norwaytest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/**
 * Created by himanshubhardwaj on 27/08/18.
 */
public class A {
    static TreeSet<Integer> primesS;
    static Pair primeP = new Pair(0, 1);
    static Pair normalP = new Pair(0, 1);

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        primesS = SieveOfEratosthenes.sieveOfEratosthenes(100000);

//        for (int x : primes) {
//            map.put(x, new Pair(x, 1));
//        }


        int number = Integer.parseInt(br.readLine());
//        int number = 5;


        while (number != 4) {
            Pair p;
            if (!primesS.contains(number)) {
                p = computeReduction(number);
            } else {
                p = primeP;
                p.setKey(number);
            }

            System.out.println(p.getKey() + " " + p.getValue());
            number = Integer.parseInt(br.readLine());
//            System.out.println(number+"\t"+p.getKey() + " " + p.getValue());
//            number++;
        }


        br.close();


    }

    private static Pair computeReduction(int number) {
        if (primesS.contains(number)) {
            primeP.setKey(number);
            return primeP;
        }

        int half = ((int) Math.sqrt(number)) + 1;
        int sum = 0;
        int ts = number;

        for (Integer pr : primesS) {
            if (pr > half) {
                break;
            }
            while ((number % pr) == 0) {
                sum += pr;
                number = number / pr;
            }
        }
        sum += (number > 1) ? number : 0;

        if (ts == sum) {
            primeP.setKey(number);
            return primeP;
        }

        Pair rp = computeReduction(sum);
        normalP.setKey(rp.getKey());
        normalP.setValue(1 + rp.getValue());
        return normalP;
    }
}


class SieveOfEratosthenes {
    static TreeSet<Integer> sieveOfEratosthenes(int n) throws IOException {
        // Create a boolean array "prime[0..n]" and initialize
        // all entries it as true. DEShaw value in prime[i] will
        // finally be false if i is Not a prime, else true.


        TreeSet<Integer> primeList = new TreeSet<>();
        boolean prime[] = new boolean[n + 1];
        for (int i = 0; i < n; i++)
            prime[i] = true;

        for (int p = 2; p * p <= n; p++) {
            // If prime[p] is not changed, then it is a prime
            if (prime[p] == true) {
                // Update all multiples of p
                for (int i = p * 2; i <= n; i += p)
                    prime[i] = false;
            }
        }

        // Print all prime numbers
        for (int i = 2; i <= n; i++) {
            if (prime[i] == true) {
                primeList.add(i);
            }
        }

        return primeList;
    }

}

class Pair {
    private int key;
    private int value;

    @java.beans.ConstructorProperties({"key", "value"})
    public Pair(int key, int value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return this.key;
    }

    public int getValue() {
        return this.value;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Pair)) return false;
        final Pair other = (Pair) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getKey() != other.getKey()) return false;
        if (this.getValue() != other.getValue()) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getKey();
        result = result * PRIME + this.getValue();
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof Pair;
    }

    public String toString() {
        return "Pair(key=" + this.getKey() + ", value=" + this.getValue() + ")";
    }
}