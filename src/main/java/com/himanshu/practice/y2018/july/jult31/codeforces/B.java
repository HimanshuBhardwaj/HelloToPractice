package com.himanshu.practice.y2018.july.jult31.codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * Created by himanshubhardwaj on 31/07/18.
 */
public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        char[] s = br.readLine().toCharArray();
        String t = br.readLine();
        LinkedList<Integer> order = new LinkedList<>();
        boolean isPossible = true;

        for (int i = 0; (i < s.length) && isPossible; i++) {
            if (s[i] == t.charAt(i)) {
                continue;
            } else {
                int pos = positionContainingSameChar(t.charAt(i), i + 1, s);
                if (pos == -1) {
                    isPossible = false;
                } else {
                    for (int j = pos - 1; j >= i; j--) {
                        swap(j, j + 1, s);
                        order.addLast(j + 1);
                    }
                }
            }
        }

//        for (int i = 0; i < s.length; i++) {
//            System.out.print(s[i]);
//        }
//        System.out.println();

        if (isPossible) {
            System.out.println(order.size());
            for (int i : order) {
                System.out.print(i + " ");
            }
        } else {
            System.out.print(-1);
        }
    }

    private static void swap(int j, int end, char[] s) {
        char temp = s[j];
        s[j] = s[end];
        s[end] = temp;
    }

    private static int positionContainingSameChar(char ch, int startPos, char[] s) {
        int returnPos = -1;

        for (int i = startPos; i < s.length; i++) {
            if (ch == s[i]) {
                return i;
            }
        }
        return returnPos;
    }


}
