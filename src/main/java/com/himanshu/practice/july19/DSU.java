package com.himanshu.practice.july19;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by himanshubhardwaj on 19/07/18.
 */
public class DSU {
    public static void main(String[] args) {
        DisjointSets sdu = new DisjointSets(10);
        sdu.addElement(1, 0);
        sdu.addElement(2, 1);
        sdu.addElement(3, 2);

        sdu.addElement(4, 0);
        sdu.addElement(5, 4);
        sdu.addElement(6, 0);
        sdu.addElement(7, 6);
        sdu.addElement(8, 0);
        sdu.addElement(9, 8);
        sdu.addElement(10, 0);
        sdu.merge(10, 9);
        //System.out.println(sdu.sets[10] + "\t" + sdu.sets[6]);
        System.out.println(sdu.findSet(10) + "\t" + sdu.findSet(6));
        sdu.merge(10, 6);
        sdu.merge(1, 4);
        sdu.merge(9, 4);
        System.out.println(sdu.findSet(10) + "\t" + sdu.findSet(6));
        sdu.printAllSet();
    }
}


class DisjointSets {
    int sets[];
    int height[]; //weight of set
    int size;

    //1....size
    public DisjointSets(int numElements) {
        size = numElements;
        numElements++;
        sets = new int[numElements];
        height = new int[numElements];
        //sets0 means ke that element not present
    }


    //add element to a set containing setElement
    void addElement(int element, int setElement) {
        if (setElement <= 0) {
            //means we are adding new element
            makeSet(element);
        } else {
            int set = findSet(setElement);
            sets[element] = set;
            height[set]++;
        }
    }

    boolean areSameSet(int x, int y) {
        return findSet(x) == findSet(y);
    }

    //Merge set x and y
    void merge(int x, int y) {
        int setX = findSet(x);
        int setY = findSet(y);

        if (setX == 0 || setY == 0 || setX == setY) {
            return;
        }

        if (height[setX] > height[setY]) {
            sets[setY] = setX;
        } else if (height[setX] < height[setY]) {
            sets[setX] = setY;
        } else {
            sets[setX] = setY;
            height[setY]++;
        }
    }

    //will return 0 if element is not present; it is assumed that 0 could not be an element
    int findSet(int x) {
        if (sets[x] == x || sets[x] == 0) {
            return sets[x];
        }
        sets[x] = findSet(sets[x]);
        return sets[x];
    }


    //this will ake a new set containing only element x
    void makeSet(int x) {
        sets[x] = x;
        height[x] = 1;
    }


    //element belongsto set, remove it fromt hat set and make seperate set out of it.
    void disunionList(int set, int element) {

    }


    void printAllSet() {
        HashMap<Integer, LinkedList<Integer>> setMap = new HashMap<>();
        for (int i = 1; i <= size; i++) {
            findSet(i);
        }
        for (int i = 1; i <= size; i++) {
            if (setMap.containsKey(sets[i])) {
                setMap.get(sets[i]).add(i); //list all elements corresponding to particular set
            } else {
                LinkedList<Integer> list = new LinkedList<>();
                list.add(i);
                setMap.put(sets[i], list);
            }
        }

        for (Map.Entry<Integer, LinkedList<Integer>> entry : setMap.entrySet()) {
            System.out.print(entry.getKey() + "{" + height[entry.getKey()] + ": ");
            for (int element : entry.getValue()) {
                System.out.print(element + ", ");
            }
            System.out.println("}");
        }
    }
}
