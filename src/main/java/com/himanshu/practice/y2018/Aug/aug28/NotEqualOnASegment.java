package com.himanshu.practice.y2018.Aug.aug28;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Created by himanshubhardwaj on 28/08/18.
 * Statement: https://codeforces.com/contest/622/problem/C
 * 4:05 pm -- 7:00 pm
 * Algo: Segment PTree SolutionA: https://codeforces.com/contest/622/submission/42209283
 * Notmal SolutionA: O(n): https://codeforces.com/contest/622/submission/42209185
 * Segment PTree is giving TLE into this: https://codeforces.com/contest/622/submission/42207230 ; this is because i was using System.out.println, which is slow
 * later I used PrintWriter, then both segment treeO(nlogn) and O(n) solution got passed.
 * LEarning: use PrintWriter for tight limits
 */
public class NotEqualOnASegment {
    public static void main(String[] args) throws IOException {
        //Segment PTree was giving TLE
        segTreeSolution();

        //Ad-Hoc
//        adHocSolution();
    }

    private static void adHocSolution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter outFile = new PrintWriter(System.out, true);
        String str[] = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);

        str = br.readLine().split(" ");
        int arr[] = new int[n];
        Element e[] = new Element[n];

        Element prevDiffElement = null;
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(str[i]);
            e[i] = new Element(i, arr[i], -1);
            if (i == 0) {
                prevDiffElement = e[i];
            } else {
                if (prevDiffElement.value == e[i].value) {
                    e[i].prevDiffIndex = prevDiffElement.prevDiffIndex;
                    prevDiffElement = e[i];
                } else {
                    e[i].prevDiffIndex = prevDiffElement.index;
                    prevDiffElement = e[i];
                }
            }
            //System.out.println(e[i]);
        }


        int output, l, r, x;
        for (int i = 0; i < m; i++) {
            str = br.readLine().split(" ");
            l = Integer.parseInt(str[0]) - 1;
            r = Integer.parseInt(str[1]) - 1;
            x = Integer.parseInt(str[2]);


            if (e[r].value != x) {
                output = 1 + e[r].index;

            } else {
                if (e[r].prevDiffIndex < l) {
                    output = -1;
                } else {
                    output = 1 + e[r].prevDiffIndex;
                }
            }
            outFile.append(output + "\n");
        }
        outFile.flush();
        outFile.close();
        br.close();
    }

    static void segTreeSolution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter outFile = new PrintWriter(System.out, false);
        String str[] = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);

        str = br.readLine().split(" ");
        int arr[] = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(str[i]);
        }

        SegmentTree s = new SegmentTree(arr);

        int output;
        for (int i = 0; i < m; i++) {
            str = br.readLine().split(" ");
            int l = Integer.parseInt(str[0]) - 1;
            int r = Integer.parseInt(str[1]) - 1;
            int x = Integer.parseInt(str[2]);

            Position maximum = s.getMaximum(l, r);
            Position minimum = s.getMinimum(l, r);


//            System.out.println(l + "\t" + r + "\t" + x + "\t" + maximum.value + "\t" + minimum.value);
//
            if (x == maximum.value) {
                if (x == minimum.value) {
                    output = -1;
                } else {
                    output = minimum.index;
                }
            } else {
                output = maximum.index;
            }
            outFile.append(output + "\n");
        }
        outFile.flush();
        br.close();
    }

}


class SegmentTree {
    Position[] maximum;
    Position[] minimum;
    int endPos;
    int size;

    public SegmentTree(int arr[]) {
        size = (int) Math.pow(2, Math.ceil(Math.log(arr.length) / Math.log(2)));
        size = 2 * size - 1;
        maximum = new Position[size];
        minimum = new Position[size];

        for (int i = 0; i <= size / 2; i++) {
            if (i < arr.length) {
                maximum[i + size / 2] = new Position(i + 1, arr[i]);
                minimum[i + size / 2] = maximum[i + size / 2];
                if (i == (arr.length - 1)) {
                    endPos = i + size / 2;
                }
            }
        }

        for (int i = (size / 2) - 1; i >= 0; i--) {
            if (maximum[2 * i + 2] == null) {
                maximum[i] = maximum[2 * i + 1];
            } else if (maximum[2 * i + 1] != null) {
                if (maximum[2 * i + 1].value > maximum[2 * i + 2].value) {
                    maximum[i] = maximum[2 * i + 1];
                } else {
                    maximum[i] = maximum[2 * i + 2];
                }
            } else {
                maximum[i] = null;
            }


            if (minimum[2 * i + 2] == null) {
                minimum[i] = minimum[2 * i + 1];
            } else if (minimum[2 * i + 1] != null) {
                if (minimum[2 * i + 1].value <= minimum[2 * i + 2].value) {
                    minimum[i] = minimum[2 * i + 1];
                } else {
                    minimum[i] = minimum[2 * i + 2];
                }
            } else {
                minimum[i] = null;
            }
        }
        //perfect till here
    }

    public Position getMinimum(int start, int end) {
        return getMinimumHelper(0, size / 2, start, end, 0);
    }

    private Position getMinimumHelper(int segTS, int segTE, int rS, int sE, int index) {
        if (segTE < 0 || segTE > size / 2 || segTE < segTS || segTE < rS || segTS > sE || index < 0 || index > endPos || minimum[index] == null) {
            return null;
        }

        if (segTS >= rS && segTE <= sE) {
            return minimum[index];
        }

        int mid = segTS + (segTE - segTS) / 2;

        Position left = getMinimumHelper(segTS, mid, rS, sE, 2 * index + 1);
        Position right = getMinimumHelper(mid + 1, segTE, rS, sE, 2 * index + 2);

        if (right == null) {
            return left;
        } else if (left != null) {
            return (left.value <= right.value) ? left : right;
        } else {
            return right;
        }
    }

    public Position getMaximum(int start, int end) {
        return getMaxiumHelper(0, size / 2, start, end, 0);
    }

    private Position getMaxiumHelper(int segTS, int segTE, int rS, int sE, int index) {
        if (segTE < 0 || segTE > size / 2 || segTE < segTS || segTE < rS || segTS > sE || index < 0 || index > endPos || maximum[index] == null) {
            return null;
        }

        if (segTS >= rS && segTE <= sE) {
            return maximum[index];
        }


        int mid = segTS + (segTE - segTS) / 2;

        Position left = getMaxiumHelper(segTS, mid, rS, sE, 2 * index + 1);
        Position right = getMaxiumHelper(mid + 1, segTE, rS, sE, 2 * index + 2);

        if (right == null) {
            return left;
        } else if (left != null) {
            return (left.value <= right.value) ? right : left;
        } else {
            return right;
        }
    }


    public void printSegmentTree() {
        System.out.println("Size: " + size);
        System.out.println("End Position: " + endPos);
        System.out.println("printing maximum");

        for (int i = 0; i <= endPos; i++) {
            if (minimum[i] != null) {
                System.out.print(maximum[i].value + "\t");
            } else {
                System.out.print((String) null);
            }
        }

        System.out.println();
        System.out.println("Printing minimum");


        for (int i = 0; i <= endPos; i++) {
            if (minimum[i] != null) {
                System.out.print(minimum[i].value + "\t");
            } else {
                System.out.print((String) null);
            }
        }
        System.out.println();
    }
}

class Position {
    int index;
    int value;

    @java.beans.ConstructorProperties({"index", "value"})
    public Position(int index, int value) {
        this.index = index;
        this.value = value;
    }

    public String toString() {
        return "Position(index=" + this.index + ", value=" + this.value + ")";
    }
}

class Element {
    int index;
    int value;
    int prevDiffIndex;

    @java.beans.ConstructorProperties({"index", "value", "prevDiffIndex"})
    public Element(int index, int value, int prevDiffIndex) {
        this.index = index;
        this.value = value;
        this.prevDiffIndex = prevDiffIndex;
    }

    public String toString() {
        return "Element(index=" + this.index + ", value=" + this.value + ", prevDiffIndex=" + this.prevDiffIndex + ")";
    }
}