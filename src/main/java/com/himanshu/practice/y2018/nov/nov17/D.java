package com.himanshu.practice.y2018.nov.nov17;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by himanshubhardwaj on 17/11/18.
 */
public class D {


    public static void main(String[] args) throws IOException {
        D d = new D();
        System.out.println(d.minSteps("00001010000001", "000010100100000010100000000101100011"));

    }

//
//    public int minSteps(String A, String B) {
//        if (A.length() > B.length()) {
//            return -1;
//        }
//
//        if (A.length() == B.length()) {
//            if (A.equals(B)) {
//                return 0;
//            } else {
//                return -1;
//            }
//        }
//
//        int cost = 0;
//        Queue<String> queue = new LinkedList<>();
//        queue.add(A);
//
//        while (!queue.isEmpty()) {
//            LinkedList<String> strList = new LinkedList<>(queue);
//            queue = new LinkedList<>();
//            HashSet<String> strAdded = new HashSet<>();
//
//            for (String list : strList) {
//                if (list.equals(B)) {
//                    return cost;
//                }
//
//                if (list.length() < B.length()) {
//                    String tA = list + "1";
//                    String appended0 = "0" + list;
//                    String tB = reverse(appended0);
//
//                    if (!strAdded.contains(tA) && (B.contains(tA) || B.contains(reverse(tA)))) {
//                        queue.add(tA);
//                        strAdded.add(tA);
//                    }
//                    if (!strAdded.contains(tB) && (B.contains(tB) || B.contains(appended0))) {
//                        queue.add(tB);
//                        strAdded.add(tB);
//                    }
//                }
//            }
//            cost++;
//        }
//        return -1;
//    }

    public int minSteps(String A, String B) {
        if (A.length() > B.length()) {
            return -1;
        }

        if (A.length() == B.length()) {
            if (A.equals(B)) {
                return 0;
            } else {
                return -1;
            }
        }

        int cost = 0;
        Queue<String> queue = new LinkedList<>();
        queue.add(A);

        while (!queue.isEmpty()) {
            LinkedList<String> strList = new LinkedList<>(queue);
            queue = new LinkedList<>();
            HashSet<String> strAdded = new HashSet<>();
            HashSet<Integer> strHashAdded = new HashSet<>();

            for (String list : strList) {
                if (list.equals(B)) {
                    return cost;
                }

                if (list.length() < B.length()) {
                    String tA = list + "1";
                    String appended0 = "0" + list;
                    String tB = reverse(appended0);
                    int hashtA = getHash(tA);
                    int hashtB = getHash(tB);

                    if (strHashAdded.contains(hashtA)) {
                        if (!strAdded.contains(tA)) {
                            if (B.contains(tA) || B.contains(reverse(tA))) {
                                queue.add(tA);
                                strAdded.add(tA);
                                strHashAdded.add(hashtA);
                            }

                        }

                    } else {
                        queue.add(tA);
                        strAdded.add(tA);
                        strHashAdded.add(hashtA);
                    }

                    if (strHashAdded.contains(hashtB)) {
                        if (!strAdded.contains(tB)) {
                            if (B.contains(tB) || B.contains(reverse(tB))) {
                                queue.add(tB);
                                strAdded.add(tB);
                                strHashAdded.add(hashtB);
                            }
                        }
                    } else {
                        queue.add(tB);
                        strAdded.add(tB);
                        strHashAdded.add(hashtB);
                    }
                }
            }
            cost++;
        }
        return -1;
    }

    private String reverse(String B) {
        char[] b = B.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = b.length - 1; i >= 0; i--) {
            sb.append(b[i]);
        }
        return sb.toString();
    }

    int getHash(String A) {
        long hash = 0l;
        int prime = 1000000007;

        for (int i = 0; i < A.length(); i++) {
            hash = (hash * 37l + (A.charAt(i) - '0')) % prime;
        }
        //System.out.println(hash+"\t\t"+(int)hash);
        return (int) hash;
    }


}
