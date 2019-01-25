package com.himanshu.practice.y2018.Aug.aug22.ubertest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * Created by himanshubhardwaj on 22/08/18.
 */
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        SmartSplit s = new SmartSplit();
        String str = br.readLine();
        //String str = "the best lies are always mixed with a little truth";
//        String str = "There is no creature on earth half so terrifying as a truly just man!!!!!";
//        String str = "you know nothing, jon snow";
        //String str = "The best lies are always mixed with a little truth";
        // System.out.println(str.length());
        System.out.print(s.numSmrtSplit(str.toCharArray(), 30));

    }
}


class SmartSplit {
    HashMap<String, Integer> dpMap = new HashMap<String, Integer>();

    int numSmrtSplit(char[] str, int bufferSize) {
        if (str == null || str.length == 0) {
            return 0;
        }

        if (str.length <= bufferSize) {
            return 1;
        }

        int minSplit = Integer.MAX_VALUE;
        int pos = -1;
        for (int i = 1; i <= 99; i++) {
            dpMap = new HashMap<>();
            int minLines = getMinSplit(str, 0, 1, bufferSize - 3 - numOfChars(i), i);
            if (minLines == i) {
                if (minLines < minSplit) {
                    minSplit = minLines;
                }
            }
        }

        return minSplit;
    }

    private int getMinSplit(char[] str, int pos, int lineNumber, int buffer, int totalNumLines) {
        if (pos >= str.length) {
            return 0;
        }


        int result = 0;

        int effectivebufer = buffer - numOfChars(lineNumber);

        if (effectivebufer <= 0) {
            return Integer.MAX_VALUE;
        }

        if (dpMap.containsKey(pos + "|" + lineNumber)) {
            return dpMap.get(pos + "|" + lineNumber);
        }


        int minLines = 1;
        if ((str.length - pos) < effectivebufer) {
            dpMap.put((pos + "|" + lineNumber), minLines);
            return minLines;
        }

        int count = Integer.MAX_VALUE;
        int splitPos = pos;
        for (int i = pos + 1; i < (pos + effectivebufer); i++) {
            if ((str[i] == ' ')) {
                int subMin = getMinSplit(str, i + 1, lineNumber + 1, buffer, totalNumLines);
                if (count > subMin) {
                    count = subMin;
                }
            }
        }

        if (count == Integer.MAX_VALUE) {
            dpMap.put((pos + "|" + lineNumber), count);
            return count;
        } else {
            dpMap.put((pos + "|" + lineNumber), 1 + count);
            return 1 + count;
        }
    }

    String getString(int pos, int lineNum, int maxLines) {
        return null;
    }

    int numOfChars(int x) {

        if (x == 0) {
            return 0;
        }
        int count = 1;
        while ((x / 10) > 0) {
            x = x / 10;
            count++;
        }
        return count;
    }
}


