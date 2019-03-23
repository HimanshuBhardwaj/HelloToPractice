package com.himanshu.practice.y2019.jan.jan27;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by himanshubhardwaj on 27/01/19.
 * Statement: https://arena.topcoder.com/#/u/practiceCode/1391/1044/1044/2/1391
 * Algorithm: String mainipulations
 */
public class WordSpaces {
    HashSet<String>[] wordSet;

    public static void main(String[] args) {
        WordSpaces ws = new WordSpaces();
        String words[] = {"this","mat","zebra","hh"};
        ws.find("t ah mi as this", words);


    }


    public int[] find(String sentence, String[] words) {
        wordSet = new HashSet[sentence.length()];

        for (int i = 0; i < sentence.length(); i++) {
            wordSet[i] = new HashSet<String>();
        }

        //start position
        for (int i = 0; i < sentence.length(); i++) {
            //gap interval
            for (int j = 1; j <= sentence.length(); j++) {
                StringBuilder sb = new StringBuilder();

                for (int k = i; k < sentence.length(); k = k + j) {

                    sb.append(sentence.charAt(k));
                    wordSet[i].add(sb.toString());
                }
            }
        }

        int positions[] = new int[words.length];

        for (int i = 0; i < words.length; i++) {
            positions[i] = -1;

            for (int j=0;j<wordSet.length;j++) {
                if (wordSet[j].contains(words[i])) {
                    positions[i] =j;
                    break;
                }
            }
        }


        return positions;
    }


}
