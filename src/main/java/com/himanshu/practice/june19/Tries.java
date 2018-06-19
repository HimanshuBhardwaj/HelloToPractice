package com.himanshu.practice.june19;

import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by himanshubhardwaj on 19/06/18.
 */
public class Tries {
    public static void main(String[] args) {
        Node trieRoot = new Node('*');
        trieRoot.insertWord(trieRoot, "Bhardwaj");
        trieRoot.insertWord(trieRoot, "Himanshu");
        trieRoot.insertWord(trieRoot, "Jayant");
        trieRoot.insertWord(trieRoot, "veer");
        trieRoot.insertWord(trieRoot, "singh");
        trieRoot.insertWord(trieRoot, "deepankar");
        trieRoot.insertWord(trieRoot, "karan");
        trieRoot.insertWord(trieRoot, "kaddu");
        trieRoot.insertWord(trieRoot, "singhal");
        trieRoot.insertWord(trieRoot, "sin");
        System.out.println(trieRoot);


        trieRoot.printAllwords(trieRoot, "");

//
//        String word = "Bhardwaj";
//        char[] wordArr = word.toCharArray();
//        System.out.println(wordArr.length);
//        System.out.println(wordArr[6]);
//        System.out.println(wordArr[7]);
//        System.out.println(wordArr[6]);
    }
}


class Node {
    char value;
    Map<Character, Node> children;

    public String toString() {
        return new String(Character.toString(value)) + ": " + children;
    }


    public Node(char value) {
        this.value = value;
        children = new HashMap<Character, Node>();
    }

    Node insertWord(Node root, String word) {
        word = word + "$";
        if (root == null) {
            root = new Node('*');
        }
        char[] wordArr = word.toCharArray();
        if (!root.children.containsKey(wordArr[0])) {
            root.children.put(wordArr[0], new Node(wordArr[0]));
        }
        insertWordHelper(root.children.get(wordArr[0]), wordArr, 0);


        return root;
    }


    //newnode will never be null
    //value of this newNode is wordArr[pos]
    Node insertWordHelper(Node newNode, char[] wordArr, int pos) {
        if (pos == wordArr.length) {
            return null;
        }

        if (pos == wordArr.length - 1) {
            //no need to add anything as $ is already insweted
            return newNode;
        }
        if (!newNode.children.containsKey(wordArr[pos + 1])) {
            Node node1 = new Node(wordArr[pos + 1]);
            newNode.children.put(wordArr[pos + 1], node1);
        }
        insertWordHelper(newNode.children.get(wordArr[pos + 1]), wordArr, pos + 1);

        return newNode;
    }


    public void printAllwords(Node trieRoot, String currentWord) {
        if (trieRoot.value == '$') {
            System.out.println(currentWord);
        }
        if (trieRoot.value == '*') {
            for (Map.Entry<Character, Node> entry : trieRoot.children.entrySet()) {
                printAllwords(entry.getValue(), currentWord);
            }
            return;
        }
        currentWord = currentWord + trieRoot.value;
        for (Map.Entry<Character, Node> entry : trieRoot.children.entrySet()) {
            printAllwords(entry.getValue(), currentWord);
        }


    }


}