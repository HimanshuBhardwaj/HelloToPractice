//package com.himanshu.practice.y2018.Aug.aug17;
//
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.TreeSet;
//import javafx.util.Pair;
//
///**
// * Created by himanshubhardwaj on 16/08/18.
// * statement: https://codeforces.com/problemset/problem/620/D
// * TODO: Complete it
// *
// */
//public class ProfessorGukiZAndTwoArrays {
//    static long minDiff = Long.MAX_VALUE;
//    static Pair pairOneSwap[] = new Pair[2];
//    static boolean isSwapped = false;
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        br.readLine();
//        String str[] = br.readLine().split(" ");
//        long a[] = new long[str.length];
//        long sa = 0l;
//
//        for (int i = 0; i < str.length; i++) {
//            a[i] = Long.parseLong(str[i]);
//            sa += a[i];
//        }
//
//
//        str = br.readLine().split(" ");
//        str = br.readLine().split(" ");
//        long b[] = new long[str.length];
//        long sb = 0l;
//        for (int i = 0; i < str.length; i++) {
//            b[i] = Long.parseLong(str[i]);
//            sb += b[i];
//        }
//
//        minDiff = Math.abs(sa - sb);
//
//        if (a.length > b.length) {
//            long tempSum = sa;
//            sa = sb;
//            sb = tempSum;
//
//            long[] temp = a;
//            a = b;
//            b = temp;
//            isSwapped = true;
//        }
//
//        //a.length <=b.length
//
//        TreeSet<Element> setB = new TreeSet<>();
//
//        for (int i = 0; i < b.length; i++) {
//            setB.add(new Element(i, b[i]));
//        }
//
//        Element tempElement1 = new Element();
//        Element tempElement2 = new Element();
//
//
//        //------------------------
//        if (isSwapped) {
//            for (long c : b) {
//                System.out.print(c + " ");
//            }
//            System.out.println();
//            System.out.println(sb);
//        }
//        for (long c : a) {
//            System.out.print(c + " ");
//        }
//        System.out.println();
//        System.out.println(sa);
//
//        if (!isSwapped) {
//            for (long c : b) {
//                System.out.print(c + " ");
//            }
//            System.out.println();
//            System.out.println(sb);
//        }
//        System.out.println(minDiff);
//        System.out.println("------------------");
//        //--------------------------
//
//        //only one swap
//        for (int i = 0; i < a.length; i++) {
//            tempElement1.element = a[i];
//            for (int j = 0; j <= 1; j++) {
//                Element rb = (j == 0) ? setB.higher(tempElement1) : setB.lower(tempElement1);
//                if (rb != null) {
//                    long tempSumB = sb - rb.element + a[i];
//                    long tempSumA = sa - a[i] + rb.element;
//                    if (Math.abs(tempSumB - tempSumA) < minDiff) {
//                        minDiff = Math.abs(tempSumB - tempSumA);
//                        if (isSwapped) {
//                            pairOneSwap[0] = new Pair(rb.index, i);
//                        } else {
//                            pairOneSwap[0] = new Pair(i, rb.index);
//                        }
//                    }
//                }
//            }
//        }
//
//
//        for (int i = 0; i < a.length; i++) {
//            for (int j = i + 1; j < a.length; j++) {
//                tempElement1.element = a[i];
//                tempElement2.element = a[j];
//                for (int k = 0; k <= 1; k++) {
//                    for (int l = 0; l <= 1; l++) {
//                        Element rb1 = (k == 0) ? setB.higher(tempElement1) : setB.lower(tempElement1);
//                        Element rb2 = (l == 0) ? setB.higher(tempElement2) : setB.lower(tempElement2);
//                        if (rb1 != null && rb2 != null && rb1.index != rb2.index) {
//                            long tempSumB = sb - rb1.element - rb2.element + tempElement1.element + tempElement2.element;
//                            long tempSumA = sa - tempElement1.element - tempElement2.element + rb1.element + rb2.element;
//                            long tempDiff = Math.abs(tempSumA - tempSumB);
//                            if (tempDiff < minDiff) {
//                                minDiff = tempDiff;
//                                if (isSwapped) {
//                                    pairOneSwap[0] = new Pair(rb1.index, i);
//                                    pairOneSwap[1] = new Pair(rb2.index, j);
//                                } else {
//                                    pairOneSwap[0] = new Pair(i, rb1.index);
//                                    pairOneSwap[1] = new Pair(j, rb2.index);
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//
//        System.out.println(minDiff);
//        if (pairOneSwap[0] == null && pairOneSwap[1] == null) {
//            System.out.println(0);
//        } else if (pairOneSwap[1] == null) {
//            System.out.println(1);
//            System.out.println(pairOneSwap[0].getKey() + " " + pairOneSwap[0].getValue());
//        } else {
//            System.out.println(2);
//            System.out.println(pairOneSwap[0].getKey() + " " + pairOneSwap[0].getValue());
//            System.out.println(pairOneSwap[1].getKey() + " " + pairOneSwap[1].getValue());
//        }
//
//    }
//}
//
//@NoArgsConstructor
//@AllArgsConstructor
//class Element implements Comparable<Element> {
//    int index;
//    long element;
//
//    @Override
//    public int compareTo(Element o) {
//        return (int) (this.element - o.element);
//    }
//}