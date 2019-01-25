package com.himanshu.practice.y2018.july.july30;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;

/**
 * Created by himanshubhardwaj on 30/07/18.
 * Problem Set: https://codeforces.com/contest/570/problem/C
 * Algo: Implementaton, balanced trees
 * TODO: Gettig TLE, make it fast
 * Submisstion: https://codeforces.com/contest/570/submission/41003886
 */
public class Replacement {
    static int numGaps = 0; //of size >1
    static int totalSizeofGaps = 0; //of size> 1
    static Gap dummyGap = new Gap(0,0);

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int n, q, m;

        n = Integer.parseInt(str[0]);
        q = Integer.parseInt(str[1]);
        char[] cArr = br.readLine().toCharArray();
        int pos = 0;
        TreeSet<Gap> map = new TreeSet<>();


        while (pos < n) {
            if (cArr[pos] == '.') {
                int start = pos;
                while ((pos < n) && (cArr[pos] == '.')) {
                    pos++;
                }
                Gap gap = new Gap(start, pos - 1);
                map.add(gap);
                int size = pos - start;
                if (size > 1) {
                    numGaps++;
                    totalSizeofGaps += size;
                }
            } else {
                pos++;
            }
        }

        //System.out.println("Number of Gaps: " + numGaps + "\tTotal Size of Gaps: " + totalSizeofGaps + "\t" + map);


        for (int i = 0; i < q; i++) {
            str = br.readLine().split(" ");
            System.out.println(handleQuery(map, str));
            // System.out.println("Number of Gaps: " + numGaps + "\tTotal Size of Gaps: " + totalSizeofGaps + "\t" + map);
        }
    }

    private static int handleQuery(TreeSet<Gap> map, String str[]) throws IOException {


        int pos = Integer.parseInt(str[0]);
        pos--;//to notmalise it for 0..
        char ch = str[1].toCharArray()[0];


        if (ch == '.') {
            dummyGap.start = pos;
            Gap floorGap = map.floor(dummyGap);
            if (floorGap != null && floorGap.end >= pos) {
                return (totalSizeofGaps - numGaps);
            } else if (floorGap != null && floorGap.end == (pos - 1)) {
                dummyGap.start = pos;
                Gap ceilGap = map.ceiling(dummyGap);
                if (ceilGap != null && ceilGap.start == pos + 1) {
                    int size = floorGap.end - floorGap.start + 1;
                    if (size > 1) {
                        numGaps--;
                        totalSizeofGaps -= size;
                    }
                    map.remove(floorGap);

                    size = ceilGap.end - ceilGap.start + 1;
                    if (size > 1) {
                        numGaps--;
                        totalSizeofGaps -= size;
                    }
                    map.remove(ceilGap);


                    ceilGap.start = floorGap.start;
                    size = ceilGap.end - ceilGap.start + 1;
                    if (size > 1) {
                        numGaps++;
                        totalSizeofGaps += size;
                    }
                    map.add(ceilGap);
                } else {
                    int size = floorGap.end - floorGap.start + 1;
                    if (size > 1) {
                        totalSizeofGaps -= size;
                        numGaps--;
                    }
                    map.remove(floorGap);
                    floorGap.end = pos;
                    size = floorGap.end - floorGap.start + 1;
                    if (size > 1) {
                        totalSizeofGaps += size;
                        numGaps++;
                    }
                    if (size >= 1) {
                        map.add(floorGap);
                    }
                }
            } else {
                dummyGap.start = pos;
                Gap ceilGap = map.ceiling(dummyGap);

                if (ceilGap != null && ceilGap.start == pos) {
                    //do nothing
                } else if (ceilGap != null && ceilGap.start == (pos + 1)) {
                    map.remove(ceilGap);
                    ceilGap.start = pos;
                    totalSizeofGaps++;
                    map.add(ceilGap);
                } else {
                    Gap newGap = new Gap(pos, pos);
                    map.add(newGap);
                }

                //handle null ceil and floor seprately
            }
        } else { //case when we do not have . in query
            dummyGap.start = pos;
            Gap ceilGap = map.ceiling(dummyGap);
            if (ceilGap != null && (ceilGap.start == pos)) {
                int size = ceilGap.end - ceilGap.start + 1;
                map.remove(ceilGap);
                if (size > 1) {
                    totalSizeofGaps -= size;
                    numGaps--;
                }
                ceilGap.start++;
                size = ceilGap.end - ceilGap.start + 1;

                if (size > 1) {
                    totalSizeofGaps += size;
                    numGaps++;
                }
                if (size >= 1) {
                    map.add(ceilGap);
                }
            }


            dummyGap.start = pos;
            Gap floorGap = map.floor(dummyGap);
            if (floorGap != null && floorGap.end < pos) {

            } else if (floorGap != null && floorGap.end == pos) {
                map.remove(floorGap);

                int size = floorGap.end - floorGap.start + 1;
                map.remove(floorGap);
                if (size > 1) {
                    totalSizeofGaps -= size;
                    numGaps--;
                }
                floorGap.end--;

                size = floorGap.end - floorGap.start + 1;
                if (size > 1) {
                    totalSizeofGaps += size;
                    numGaps++;
                }

                if (size >= 1) {
                    map.add(floorGap);
                }


            } else if (floorGap != null) {
                //split

                map.remove(floorGap);
                int size = floorGap.end - floorGap.start + 1;
                if (size > 1) {
                    totalSizeofGaps -= size;
                    numGaps--;
                }

                Gap newGap = new Gap(pos + 1, floorGap.end);
                size = newGap.end - newGap.start + 1;

                if (size > 1) {
                    totalSizeofGaps += size;
                    numGaps++;
                }
                if (size >= 1) {
                    map.add(newGap);
                }

                floorGap.end = pos - 1;
                size = floorGap.end - floorGap.start + 1;

                if (size > 1) {
                    totalSizeofGaps += size;
                    numGaps++;
                }

                if (size >= 1) {
                    map.add(floorGap);
                }
            }

        }
        return (totalSizeofGaps - numGaps);
    }
}


class Gap implements Comparable<Gap> {
    int start;
    int end;

    @java.beans.ConstructorProperties({"start", "end"})
    public Gap(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Gap o) {
        return this.start - o.start;
    }

    public String toString() {
        return "Gap(start=" + this.start + ", end=" + this.end + ")";
    }
}
