package com.himanshu.practice.sept.sept9.epsilon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashSet;

/**
 * Created by himanshubhardwaj on 09/09/18.
 */
public class C {
    static HashSet<Character> vowels = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pr = new PrintWriter(System.out);
        int t = Integer.parseInt(br.readLine());
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');

        while ((t--) > 0) {
            String s = br.readLine();

            boolean isGood = true;
            boolean isBad = true;
            char prevC = '0';

            for (int i = 0; (isBad || isGood) && (i < s.length()); i++) {
                if (vowels.contains(s.charAt(i))) {
                    if (prevC == '0') {

                    } else {
//                        System.out.println(i + "\t" + s.charAt(i) + "\t" + prevC + "\t" +
//                                ((char) (prevC + 1)) + "\t" + (s.charAt(i) != (char) (prevC + 1)));
                        if (isGood && (s.charAt(i) < prevC)) {
                            isGood = false;
                        }
                        if (isBad && (s.charAt(i) > prevC)) {
                            isBad = false;
                        }
                    }
                    prevC = s.charAt(i);
                }
            }

            if (isGood) {
                pr.append("Good\n");
            } else if (isBad) {
                pr.append("Worst\n");
            } else {
                pr.append("Bad\n");
            }
        }
        pr.flush();
        pr.close();
        br.close();
    }
}
