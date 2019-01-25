package com.himanshu.practice.y2018.Aug.aug22.ubertest.codeforces;


import lombok.AllArgsConstructor;
import lombok.ToString;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * Created by himanshubhardwaj on 22/08/18.
 * TODO: https://codeforces.com/problemset/problem/670/E
 */
public class CorrectBracketSequenceEditor {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);
        int p = Integer.parseInt(str[2]);

        String s = br.readLine();
        String operations = br.readLine();

        Bracks[] bracks = new Bracks[n];
        Stack<Bracks> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                bracks[i] = new Bracks(true, i, 0, false, i - 1, i + 1);
                stack.push(bracks[i]);
            } else {
                bracks[i] = new Bracks(false, i, 0, false, i + 1, i - 1);
                Bracks b = stack.pop();
                b.matchingIndex = i;
                bracks[i].matchingIndex = b.index;
            }
        }

        p--;
        //printBracks(bracks);


        System.out.println(stringBuilder(bracks) + "\t\t" + p);
        for (int i = 0; i < operations.length(); i++) {
            //char ch = operations.charAt(i);
            char ch = br.readLine().charAt(0);
            System.out.println("Character readed: " + ch);
            if (ch == 'R') {
                if (bracks[p].isOpening) {
                    p = bracks[p].sameDirNextIndex;
                } else {
                    p = bracks[p].oppositeDirNextIndex;
                }
            } else if (ch == 'L') {
                if (bracks[p].isOpening) {
                    p = bracks[p].oppositeDirNextIndex;
                } else {
                    p = bracks[p].sameDirNextIndex;
                }
            } else {
                //deletion case
                if (bracks[p].isOpening) {
                    //(
                    bracks[p].isDeleted = true;
                    bracks[bracks[p].matchingIndex].isDeleted = true;
                    System.out.println("Deleted: " + p + "\t" + bracks[p].matchingIndex);


                    if (bracks[p + 1].isDeleted) {
                        bracks[p].sameDirNextIndex = bracks[p].matchingIndex;
                    } else {
                        bracks[p].sameDirNextIndex = p + 1;
                    }

                    if (bracks[bracks[p].matchingIndex].oppositeDirNextIndex != n && bracks[bracks[bracks[p].matchingIndex].oppositeDirNextIndex].isOpening) {
                        bracks[bracks[bracks[p].matchingIndex].oppositeDirNextIndex].oppositeDirNextIndex = bracks[p].oppositeDirNextIndex; //Seems Okay
                    }

                    if (bracks[bracks[p].matchingIndex].oppositeDirNextIndex != n && (!bracks[bracks[bracks[p].matchingIndex].oppositeDirNextIndex].isOpening)) {
                        bracks[bracks[bracks[p].matchingIndex].oppositeDirNextIndex].sameDirNextIndex = bracks[p].oppositeDirNextIndex;
                    }

                    if (bracks[p].oppositeDirNextIndex != -1 && !bracks[bracks[p].oppositeDirNextIndex].isOpening) {
                        bracks[bracks[p].oppositeDirNextIndex].oppositeDirNextIndex = bracks[bracks[p].matchingIndex].oppositeDirNextIndex; //seems okay
                    }

                    if (bracks[bracks[p].matchingIndex].oppositeDirNextIndex != n) {
                        p = bracks[bracks[p].matchingIndex].oppositeDirNextIndex;
                    } else {
                        p = bracks[p].oppositeDirNextIndex;
                    }
                } else {
                    //closing
                    bracks[p].isDeleted = true;
                    bracks[bracks[p].matchingIndex].isDeleted = true;
                    System.out.println("Deleted: " + bracks[p].matchingIndex + "\t" + p);

                    if (bracks[bracks[p].matchingIndex].oppositeDirNextIndex != -1 && (!bracks[bracks[p].matchingIndex].isOpening)) {
                        bracks[bracks[bracks[p].matchingIndex].oppositeDirNextIndex].oppositeDirNextIndex = bracks[p].oppositeDirNextIndex;
                    }
                    if (bracks[p].oppositeDirNextIndex != n && (bracks[bracks[p].oppositeDirNextIndex].isOpening)) {
                        bracks[bracks[p].oppositeDirNextIndex].oppositeDirNextIndex = bracks[bracks[p].matchingIndex].oppositeDirNextIndex;
                    }

                    if (bracks[p].oppositeDirNextIndex != n && (bracks[bracks[p].oppositeDirNextIndex].isOpening)) {
                        bracks[bracks[p].oppositeDirNextIndex].oppositeDirNextIndex = bracks[bracks[p].matchingIndex].oppositeDirNextIndex;
                    }

                    if (bracks[p - 1].isDeleted) {
                        bracks[p].sameDirNextIndex = bracks[p].matchingIndex;
                    } else {
                        p = p - 1;
                    }

                    if (bracks[p].oppositeDirNextIndex != n) {
                        p = bracks[p].oppositeDirNextIndex;
                    } else {
                        p = bracks[bracks[p].matchingIndex].oppositeDirNextIndex;
                    }
                }
            }
            //printBracks(bracks);
            System.out.println(stringBuilder(bracks) + "\t\t" + p);
            printBracks(bracks);
        }
    }

    static boolean isValid(int pos, int n) {
        if (pos < 0 || pos >= n) {
            return false;
        }
        return true;
    }

    static String stringBuilder(Bracks[] bracks) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bracks.length; ) {
            if (bracks[i].isDeleted) {
                if (bracks[i].isOpening) {
                    i = bracks[bracks[i].matchingIndex].oppositeDirNextIndex;
                } else {
                    i++;
                    //do nothing
                }
            } else {
                if (bracks[i].isOpening) {
                    sb.append('(');
                } else {
                    sb.append(')');
                }
                i++;
            }
        }
        return sb.toString();

    }

    static void printBracks(Bracks[] bracks) {
        System.out.println("--------------------------------------------------------------------------------");
        for (int i = 0; i < bracks.length; i++) {
            System.out.print(bracks[i]);
            if (((i + 1) % 2) == 0) {
                System.out.println();
            } else {
                System.out.print("\t");
            }
        }
        System.out.println("--------------------------------------------------------------------------------");

    }
}


@ToString
@AllArgsConstructor
class Bracks {
    boolean isOpening;
    int index;
    int matchingIndex;
    boolean isDeleted;
    int oppositeDirNextIndex;
    int sameDirNextIndex;
}
