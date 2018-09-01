package com.himanshu.practice.Aug.aug31;

import javafx.util.Pair;

/**
 * Created by himanshubhardwaj on 31/08/18.
 */
class Solution {

    public static void main(String[] args) {
        System.out.println(maximumPal(7));


    }

    //n<=7
    static long maximumPal(int n) {
        long start = (long) Math.pow(10, n - 1);
        long end = (long) Math.pow(10, n);
        //System.out.println(start + "\t" + end);

        long max = 1;
        boolean flag = false;
        long i;
        for (i = start; i < end && (!flag); i++) {
            for (long j = i / max; j < end; j++) {
                if (isPalindrome(i * j)) {
                    max = Math.max(max, i * j);
                    flag = true;
                }
            }
        }

        long nextPalindrome = Nextplaindrome.nextPalindrome(max);
        for (; i < end; i++) {
            for (long j = nextPalindrome / i; j < end; j++) {
                if (isPalindrome(i * j)) {
                    max = Math.max(max, i * j);
                    nextPalindrome = Nextplaindrome.nextPalindrome(max);
                }
            }
        }

        return max;
    }

    static boolean isPalindrome(long n) {
        if (n < 10) {
            return true;
        }

        long temp = n;
        long k = 0;
        while (temp > 0) {
            k = k * 10;
            k += temp % 10;
            temp = temp / 10;
        }

        if (k == n) {
            return true;
        } else {
            return false;
        }
    }
}

class Nextplaindrome {
    // Returns next palindrome of a given
    // number num[]. This function is for
    // input type 2 and 3
    static void generateNextPalindromeUtil(int num[], int n) {
        int mid = n / 2;

        // end of left side is always 'mid -1'
        int i = mid - 1;

        // Begining of right side depends
        // if n is odd or even
        int j = (n % 2 == 0) ? mid : mid + 1;

        // Source bool variable to check if copy of left
        // side to right
        // is sufficient or not
        boolean leftsmaller = false;

        // Initially, ignore the middle same digits
        while (i >= 0 && num[i] == num[j]) {
            i--;
            j++;
        }

        // Find if the middle digit(s) need to
        // be incremented or not (or copying left
        // side is not sufficient)
        if (i < 0 || num[i] < num[j]) {
            leftsmaller = true;
        }

        // Copy the mirror of left to tight
        while (i >= 0) {
            num[j++] = num[i--];
        }

        // Handle the case where middle digit(s)
        // must be incremented. This part of code
        // is for CASE 1 and CASE 2.2
        if (leftsmaller) {
            int carry = 1;

            // If there are odd digits, then increment
            // the middle digit and store the carry
            if (n % 2 == 1) {
                num[mid] += 1;
                carry = num[mid] / 10;
                num[mid] %= 10;
            }
            i = mid - 1;
            j = (n % 2 == 0 ? mid : mid + 1);

            // Add 1 to the rightmost digit of the left
            // side, propagate the carry towards MSB digit
            // and simultaneously copying mirror of the
            // left side to the right side.
            while (i >= 0) {
                num[i] = num[i] + carry;
                carry = num[i] / 10;
                num[i] %= 10;
                num[j] = num[i];// copy mirror to right
                i--;
                j++;
            }

        }
    }

    // The function that prints next palindrome
    // of a given number num[] with n digits.
    static int[] generateNextPalindrome(int num[], int n) {
        //System.out.println("Next Palindrome is:");

        // Input type 1: All the digits are 9,
        // simply o/p 1 followed by n-1 0's
        // followed by 1.


        if (isAll9(num, n)) {
            int[] numA = new int[n + 1];
            numA[0] = 1;

            for (int i = 0; i < n - 1; i++) {
                numA[i + 1] = 0;
            }

            numA[n] = 1;
            num = numA;
        }

        // Input type 2 and 3
        else {
            generateNextPalindromeUtil(num, n);

        }
        return num;
    }

    // Source utility function to check if num has all 9s
    static boolean isAll9(int num[], int n) {
        for (int i = 0; i < n; i++)
            if (num[i] != 9)
                return false;
        return true;
    }

    /* Utility that prints out an array on a line */
    static void printarray(int num[]) {
        for (int i = 0; i < num.length; i++)
            System.out.print(num[i]);
        System.out.println();
    }

    static long nextPalindrome(long n) {
        int numdigits = 0;
        long k = n;

        while (k > 0) {
            numdigits++;
            k = k / 10;
        }

        int arr[] = new int[numdigits];

        for (int i = 0; i < numdigits; i++) {
            arr[i] = (int) (n % 10);
            n = n / 10;
            //System.out.print(arr[i]);
        }
        arr = generateNextPalindrome(arr, numdigits);
        long num = 0;
        for (int x : arr) {
            num = (num * 10) + x;

        }
        return num;

    }

//    public static void main(String[] args) {
//        int num[] = {9, 4, 1, 8, 7, 9, 7, 8, 3, 2, 2};
//        //generateNextPalindrome(num, num.length);
//        nextPalindrome(1);
//
//
//    }
}


