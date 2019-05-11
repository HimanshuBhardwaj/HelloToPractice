package com.himanshu.practice.y2019.may.may1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Created by himanshubhardwaj on 01/05/19.
 */
public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            char[] str = br.readLine().toCharArray();
            char[] newStr = new char[str.length];
            Element element[] = new Element[26];
            for (int i = 0; i < element.length; i++) {
                element[i] = new Element(i, 0);
            }

            for (int i = 0; i < str.length; i++) {
                element[str[i] - 'a'].frequency++;
            }

            Arrays.sort(element);
            int index = 0;
            for (int i = 0; i < element.length; i++) {
                if (element[i].frequency == 0) {
                    continue;
                }


                int next = Element.getNext(element[i].index);
                int previous = Element.getPRevious(element[i].index);
                newStr[index] = (char) ('a' + element[i].index);
                element[i].frequency--;
                index++;
                while (element[i].frequency > 0) {
                    for (int j = i + 1; element[j].frequency > 0 && element[j].index != next && element[j].index != previous && j < element.length; j++) {
                        newStr[index] = (char) ('a' + element[j].index);
                        element[j].frequency--;
                        index++;
                        break;
                    }
                    newStr[index] = (char) ('a' + element[i].index);
                    element[i].frequency--;
                    index++;
                }
            }


        }


    }
}

class Element implements Comparable<Element> {
    int index;
    int frequency;

    @java.beans.ConstructorProperties({"index", "frequency"})
    public Element(int index, int frequency) {
        this.index = index;
        this.frequency = frequency;
    }


    static int getNext(int pos) {
        return (pos + 1) % 26;
    }

    static int getPRevious(int pos) {
        return (26 + pos - 1) % 26;

    }

    @Override
    public int compareTo(Element o) {
        return o.frequency - this.frequency;
    }
}