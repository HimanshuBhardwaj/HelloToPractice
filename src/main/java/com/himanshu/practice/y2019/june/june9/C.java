package com.himanshu.practice.y2019.june.june9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * Created by himanshubhardwaj on 09/06/19.
 */
public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        String[] str = br.readLine().split(" ");

        HashMap<Integer, TreeSet<Element>> map = new HashMap<>();
        map.put(4, new TreeSet<>());
        map.put(8, new TreeSet<>());
        map.put(15, new TreeSet<>());
        map.put(16, new TreeSet<>());
        map.put(23, new TreeSet<>());
        map.put(42, new TreeSet<>());


        for (int i = 0; i < str.length; i++) {
            int element = Integer.parseInt(str[i]);
            map.get(element).add(new Element(i));
        }


        TreeSet<Element> tempFourPos = (TreeSet<Element>) map.get(4).clone();
        for (Element fourPos : tempFourPos) {
            Element eightPos = map.get(8).ceiling(fourPos);
            if (eightPos == null) {
                break;
            }

            Element fifteenPos = map.get(15).ceiling(eightPos);
            if (fifteenPos == null) {
                break;
            }


            Element sixteenPos = map.get(16).ceiling(fifteenPos);
            if (sixteenPos == null) {
                break;
            }


            Element twentyThreePos = map.get(23).ceiling(sixteenPos);
            if (twentyThreePos == null) {
                break;
            }

            Element fourtyTwoPos = map.get(42).ceiling(twentyThreePos);
            if (fourtyTwoPos == null) {
                break;
            }

            map.get(4).remove(fourPos);
            map.get(8).remove(eightPos);
            map.get(15).remove(fifteenPos);
            map.get(16).remove(sixteenPos);
            map.get(23).remove(twentyThreePos);
            map.get(42).remove(fourtyTwoPos);
        }

        int count = 0;
        for (Map.Entry<Integer, TreeSet<Element>> entry : map.entrySet()) {
            count += entry.getValue().size();
        }

        System.out.println(count);


    }
}


class Element implements Comparable<Element> {
    int index;

    @java.beans.ConstructorProperties({"index"})
    public Element(int index) {
        this.index = index;
    }

    @Override
    public int compareTo(Element o) {
        return this.index - o.index;
    }
}
