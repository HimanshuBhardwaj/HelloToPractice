package com.himanshu.practice.Aug.aug21;

import java.util.HashMap;
import java.util.Stack;

/**
 * Created by himanshubhardwaj on 21/08/18.
 * uber
 */
//assume string size be 100
public class StringSplit {
    public static void main(String[] args) {
        Split split = new Split();
        String str = "Hello How are you I am himanshu";
        int bufferSize = 22;

        //System.out.println(split.splitStringNormal(str.toCharArray(), 0, bufferSize));
        //Split.printNormalStack();
        //System.out.println(split.splitStringWithNumbers(str.toCharArray(), 0, bufferSize, 1));
        //split.cutStringIntoBufferSizeWhilstMaintainingWordIntegrity(str, bufferSize);

        SmartSplit smartSplit = new SmartSplit();
        System.out.println(smartSplit.numSmrtSplit(str.toCharArray(), bufferSize));

    }
}


class SmartSplit {
    HashMap<String, Stack<String>> map = new HashMap<String, Stack<String>>();

    int numSmrtSplit(char[] str, int bufferSize) {
        if (str == null || str.length == 0) {
            return 0;
        }

        int minSplit = Integer.MAX_VALUE;
        int pos = -1;
        for (int i = 1; i <= str.length; i++) {
            int minLines = getMinSplit(str, 0, 1, bufferSize - 3 - numOfChars(i), i);
            if (minLines < minSplit) {
                minSplit = minLines;
            }
        }

        return minSplit;
    }

    private int getMinSplit(char[] str, int pos, int lineNumber, int buffer, int totalNumLines) {
        if (pos >= str.length) {
            return Integer.MAX_VALUE;
        }

        int effectivebufer = buffer - numOfChars(lineNumber);

        if (effectivebufer <= 0) {
            return Integer.MAX_VALUE;
        }


        int minLines = 1;
        if ((str.length - pos) <= effectivebufer) {
            return minLines;
        }

        int count = Integer.MAX_VALUE;
        int splitPos = pos;
        for (int i = pos + 1; i <= (pos + effectivebufer); i++) {
            int subMin = getMinSplit(str, i, lineNumber + 1, buffer, totalNumLines);
            if (count > subMin) {
                count = subMin;
                splitPos = i;
            }
        }

        if (count == Integer.MAX_VALUE) {
            return count;
        } else {
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


class Split {
    static Stack<String> normal = new Stack<String>();

    int splitStringNormal(char str[], int pos, int bufferSize) {
        if ((str.length - 1 - pos) <= bufferSize) {
            return 1;
        }
        if ((pos - (str.length - 1)) > 0) {
            return 0;
        }

        int min = Integer.MAX_VALUE;
        int position = pos;

        for (int i = pos + 1; i <= (bufferSize + pos); i++) {
            if (str[i] == ' ') {
                int value = splitStringNormal(str, i, bufferSize);
                if (min > value) {
                    position = i;
                    min = value;
                }
            }
        }
        String tempStr = "";
        for (int i = pos; i < position; i++) {
            tempStr += Character.toString(str[i]);
        }

        normal.push(tempStr);


        return 1 + min;
    }

    public static void printNormalStack() {
        while (!normal.isEmpty()) {
            System.out.println(normal.pop());
        }
    }


    int splitStringWithNumbers(char str[], int pos, int bufferSize, int linenumber) {
        int max = Integer.MAX_VALUE;

        if (pos >= str.length) {
            return linenumber - 1;
        }

        for (int realBuffersize = bufferSize - 9; realBuffersize <= (bufferSize - 5); realBuffersize++) {
            int value = Integer.MAX_VALUE;
            for (int i = pos + 1; i <= (pos + realBuffersize); i++) {
                if (str[i] == ' ') {
                    value = splitStringWithNumbers(str, i, bufferSize, linenumber);

                    int numdigits = 1;
                    while ((value / 10) > 0) {
                        numdigits++;
                    }

                    switch (numdigits) {
                        case 1:
                            if (realBuffersize == bufferSize - 5)
                                break;
                        case 2:
                            break;
                        case 3:
                            break;
                    }
                }
            }

        }
        return 1;
    }

    public static void cutStringIntoBufferSizeWhilstMaintainingWordIntegrity(String source, int bufferSize) {
        int lastIndexProcessed = 0;
        while (lastIndexProcessed <
                source.length()) {
            int endIndex = (lastIndexProcessed + bufferSize);
            if (endIndex <= source.length()) {
                String choppedString = source.substring(lastIndexProcessed, (lastIndexProcessed + bufferSize));
                int minIndex = Math.min(choppedString.length(), choppedString.lastIndexOf(" "));
                choppedString = choppedString.substring(0, minIndex);
                System.out.println("[" + choppedString + "]");
                lastIndexProcessed = lastIndexProcessed + choppedString.length();
            } else {
                String choppedString = source.substring(lastIndexProcessed, source.length());
                System.out.println("[" + choppedString + "]");
                lastIndexProcessed = source.length();
            }
        }
    }


}
