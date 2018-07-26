package com.himanshu.practice.july.july26;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * Created by himanshubhardwaj on 26/07/18.
 */
public class StrippOfVovelsFromListOFStrings {
    static HashSet<Character> vowelsSet = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));


    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>();
        list.add("It matters not how strait the gate");
        list.add("How charged with punishments the scroll");
        list.add("You are the master of your fate");
        list.add("You are the captain of your soul");

        LinkedList<String> prunedList = new LinkedList<String>();
        printListWithoutForLoop(list, 0);
        System.out.println("-----------------------pruning vowels from list ----------------------------------------");
        stripOffVowelsFromList(list, prunedList, 0);
        printListWithoutForLoop(prunedList, 0);

    }

    public static void stripOffVowelsFromList(LinkedList<String> list, LinkedList<String> newList, int pos) {
        if (pos < list.size()) {
            stripOffVowelsFromList(list, (newList.add(stripOff(list.get(pos), "", 0))) ? newList : newList, pos + 1);
        }
    }

    public static String stripOff(String str, String newString, int pos) {
        if (pos == str.length()) {
            return newString;
        }

        if (vowelsSet.contains(str.charAt(pos))) {
            return stripOff(str, newString, pos + 1);
        } else {
            return stripOff(str, newString + str.charAt(pos), pos + 1);
        }
    }

    public static void printListWithoutForLoop(LinkedList<String> list, int pos) {
        if (pos < list.size()) {
            System.out.println(list.get(pos));
            printListWithoutForLoop(list, pos + 1);
        }
    }


}


/*
*
*
Output:


It matters not how strait the gate
How charged with punishments the scroll
You are the master of your fate
You are the captain of your soul
-----------------------pruning vowels from list ----------------------------------------
t mttrs nt hw strt th gt
Hw chrgd wth pnshmnts th scrll
Y r th mstr f yr ft
Y r th cptn f yr sl


* */