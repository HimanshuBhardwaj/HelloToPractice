package com.himanshu.practice.Aug.aug21;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.HashMap;

/**
 * Created by himanshubhardwaj on 21/08/18.
 * Algo: String and regX matching
 * Uber
 * DP
 */

@AllArgsConstructor
@ToString
public class PatternMatching {
    private final int age = 5;

    public static void main(String[] args) {
        String s = "aaaa";
        String p = "a*a*aa";

        Solution sol = new Solution();

//        System.out.println(s.length() + "\t" + p.length());
        System.out.println(sol.isMatch(s, p));
//        System.out.println(Solution.count);
//        System.out.println(Solution.map.size());
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof PatternMatching)) return false;
        final PatternMatching other = (PatternMatching) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.age != other.age) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.age;
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof PatternMatching;
    }
}



class Solution {
    static int count = 0;
    static HashMap<String, Boolean> map = new HashMap<>();


    public boolean isMatch(String s, String p) {
        int length = 0;

        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '*') {
                continue;
            } else {
                length++;
            }
        }
        boolean result = matchHelper(s, 0, p, 0, length);
        map = new HashMap<>();
        count = 0;
        return result;
    }

    private boolean matchHelper(String s, int indexS, String p, int indexP, int length) {
        count++;
        boolean result = true;

        if (map.containsKey(indexS + "|" + indexP)) {
            return map.get(indexS + "|" + indexP);
        }
        if (indexS >= s.length()) {
            if (length == 0) {
                result = true;
            } else {
                result = false;
            }
        } else if (length == 0) {
            if (indexP >= p.length()) {
                if (indexS >= s.length()) {
                    return true;
                } else {
                    return false;
                }
            } else {
                if (p.charAt(indexP) == '*') {
                    return true;
                } else {
                    //wont reach till here
                    return false;
                }
            }
        } else if (p.charAt(indexP) == '*') {
            result = matchHelper(s, indexS + 1, p, indexP, length) ||
                    matchHelper(s, indexS + 1, p, indexP + 1, length) ||
                    matchHelper(s, indexS, p, indexP + 1, length);
        } else if (p.charAt(indexP) == '.') {
            result = matchHelper(s, indexS + 1, p, indexP + 1, length - 1);
        } else {
            if (p.charAt(indexP) == s.charAt(indexS)) {
                result = matchHelper(s, indexS + 1, p, indexP + 1, length - 1);
            } else {
                result = false;
            }
        }
        map.put(indexS + "|" + indexP, result);
        return result;
    }
}
