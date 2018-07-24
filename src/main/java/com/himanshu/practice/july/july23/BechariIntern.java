package com.himanshu.practice.july.july23;

import java.util.ArrayList;

/**
 * Created by himanshubhardwaj on 23/07/18.
 */
class BechariIntern {

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<String>();

        list.add("Hello");
        list.add("Surabhi Gandhi");
        list.add("How are you?");
        list.add("Himanshu is a tapori idiot");
        list.add("Surabhi is a poor intern who does not know functional programming ");
        list.add("and instead of learning she is taking shortcuts");
        list.add("by asking tapori people");

        /*Just in case you do not like anyone containing H, etc */
        char charactersToExclude[] = {'H', 'o'};

        System.out.println(list);
        ArrayList<String> newString = excludeUnwantedCharContainingStrings(0, list, charactersToExclude, new ArrayList<String>());
        System.out.println(newString);
    }

    private static ArrayList<String> excludeUnwantedCharContainingStrings(int index, ArrayList<String> list, char[] charsWhichYouDoNotWant, ArrayList<String> strings) {
        return (index == list.size()) ? strings :
                (containsChar(list.get(index), 0, charsWhichYouDoNotWant) ? excludeUnwantedCharContainingStrings(index + 1, list, charsWhichYouDoNotWant, strings) :
                        excludeUnwantedCharContainingStrings(index + 1, list, charsWhichYouDoNotWant, addStringToList(strings, list.get(index))));
    }

    private static ArrayList<String> addStringToList(ArrayList<String> strings, String s) {
        strings.add(s);
        return strings;
    }

    private static boolean containsChar(String s, int pos, char[] charsWhichYouDoNotWant) {
        return (pos < charsWhichYouDoNotWant.length) ? (s.contains(String.valueOf(charsWhichYouDoNotWant[pos])) || containsChar(s, pos + 1, charsWhichYouDoNotWant)) : false;
    }
}



/*
Output:

[Hello, Surabhi Gandhi, How are you?, Himanshu is a tapori idiot, Surabhi is a poor intern who does not know functional programming , and instead of learning she is taking shortcuts, by asking tapori people]
[Surabhi Gandhi]

*
* */