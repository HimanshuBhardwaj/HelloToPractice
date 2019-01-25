package com.himanshu.practice.y2018.Aug.aug18;

/**
 * Created by himanshubhardwaj on 19/08/18.
 */
public class Test {
    public static void main(String[] args) {
        char a = 'a';
        char c[] = new char[1000000];

        for (int i = 0; i < c.length; i++) {
            c[i] = a;
        }

        String string = new String(c);

        c = new char[1000];
        for (int i = 0; i < c.length; i++) {
            c[i] = a;
        }

        String subString = new String(c);

        long start = System.currentTimeMillis();
        int pos = string.lastIndexOf(subString);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        System.out.println(pos);


        start = System.currentTimeMillis();
        int lastPos = -1;
        for (int i = 0; (i + subString.length()) <= string.length(); i++) {
            boolean posible = true;

            for (int j = 0; posible && (j < subString.length()); j++) {
                if (subString.charAt(j) != string.charAt(i + j)) {
                    posible = false;
                }
            }

            if (posible) {
                lastPos = i;
            }
        }

        end = System.currentTimeMillis();
        System.out.println("---------");
        System.out.println(end - start);
        System.out.println(lastPos);


    }

}
