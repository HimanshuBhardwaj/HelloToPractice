package com.himanshu.practice.june.june26.hour4;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Himanshu Bhardwaj on 26/06/18.
 */
public class Trie {
    public static void main(String[] args) {
        Node trie = new Node('*');
        trie.insert(trie, "Himanshu");
        trie.insert(trie, "Sinha");
        trie.insert(trie, "Bhardwaj");
        trie.insert(trie, "Himanshu Bhardwaj");
        trie.insert(trie, "Jayant");
        trie.printAllString("", trie);
        System.out.println(trie.stringCounter);

        System.out.println();
        System.out.println("Deleting Jayant");
        System.out.println();
        trie.delete(trie, "Himanshu Bhardwaj");
        trie.delete(trie, "Himanshu");
        trie.delete(trie, "Sinha");
        trie.delete(trie, "Bhardwaj");
        trie.delete(trie, "Jayant");

        trie.printAllString("", trie);
        System.out.println(trie.stringCounter);
        trie.printAllString("", trie);
    }
}


class Node {
    char value;
    Map<Character, Node> children;
    int stringCounter = 0;

    public Node(char value) {
        this.value = value;
        children = new HashMap<Character, Node>();
    }

    public void insert(Node root, String string) {
        string = string + "$";
        insertHelper(0, string.toCharArray(), root);
    }

    //root is node corresponding to string[pose]
    //root will never be null and in the child of root we will add string[pose]
    private void insertHelper(int pose, char[] string, Node root) {
        if (pose == string.length) {
            stringCounter++;
            return;
        }

        if (!root.children.containsKey(string[pose])) {
            Node newNode = new Node(string[pose]);
            root.children.put(string[pose], newNode);
        }
        insertHelper(pose + 1, string, root.children.get(string[pose]));
    }


    public void printAllString(String comm, Node node) {
        if (node.value == '$') {
            System.out.println(comm.substring(1));
            return;
        }

        comm = comm + Character.toString(node.value);
        for (Map.Entry<Character, Node> entry : node.children.entrySet()) {
            printAllString(comm, entry.getValue());
        }
    }


    public void delete(Node root, String string) {
        boolean result = deleteHelper(0, string.toCharArray(), root);
        if (!result) {
            root.children.remove(string.toCharArray()[0]);
            if (stringCounter != 0) {
                System.out.println("Something wrong");
            }
        } else {
            System.out.println("string remaining: " + stringCounter);
        }
    }

    //read
    private boolean deleteHelper(int index, char[] string, Node root) {
        if (index == string.length) {
            if (root.children.containsKey('$')) {
                root.children.remove('$');
                stringCounter--;
                if (root.children.size() == 0) {
                    return false;
                }
            }
            return true;
        }

        if (root.children.containsKey(string[index])) {
            boolean result = deleteHelper(index + 1, string, root.children.get(string[index]));
            if (result == false) {
                root.children.remove(string[index]);
            }
        }

        if (root.children.size() > 0) {
            return true;
        } else {
            return false;
        }
    }
}

