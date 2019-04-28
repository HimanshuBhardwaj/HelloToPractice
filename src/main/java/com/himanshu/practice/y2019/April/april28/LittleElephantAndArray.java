package com.himanshu.practice.y2019.April.april28;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by himanshubhardwaj on 28/04/19.
 * TODO: Implement it via SQRT Decomposition and Persistant Segment Tree
 *
 */
public class LittleElephantAndArray {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);

        str = br.readLine().split(" ");
        int arr[] = new int[str.length];


        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(str[i]);
        }

        PrintWriter pw = new PrintWriter(System.out);

        CommulativeSubtraction commulativeSubtraction = new CommulativeSubtraction(n, arr);

        for (int i = 0; i < m; i++) {
            str = br.readLine().split(" ");
            int l = Integer.parseInt(str[0]) - 1;
            int r = Integer.parseInt(str[1]) - 1;
            //pw.append(SolutionBruteForce.getCount(arr, l, r) + "\n");
            pw.append(commulativeSubtraction.getCount(arr, l, r) + "\n");
        }
        pw.flush();
    }
}



//Complexity: O(m*DistinctElements)
class CommulativeSubtraction {
    HashMap<Integer, Integer> map[] = null;

    public CommulativeSubtraction(int size, int arr[]) {
        map = new HashMap[size];
        for (int i = 0; i < size; i++) {
            map[i] = new HashMap<>();
        }


        for (int i = 0; i < arr.length; i++) {
            if (i == 0) {
                map[i] = new HashMap<Integer, Integer>();
                map[i].put(arr[i], 1);
            } else {
                map[i] = new HashMap<>(map[i - 1]);
                if (!map[i].containsKey(arr[i])) {
                    map[i].put(arr[i], 0);
                }

                map[i].put(arr[i], map[i].get(arr[i]) + 1);
            }
        }

    }

    public int getCount(int[] arr, int start, int end) {
        HashMap<Integer, Integer> mapT = new HashMap<>(map[end]);
        int count = 0;
        if (start != 0) {
            for (Map.Entry<Integer, Integer> entry : map[end].entrySet()) {
                if (entry.getKey() == (entry.getValue() - ((map[start - 1].containsKey(entry.getKey())) ? map[start - 1].get(entry.getKey()) : 0))) {
                    count++;
                }
            }
        } else {
            for (Map.Entry<Integer, Integer> entry : map[end].entrySet()) {
                if (entry.getKey() == entry.getValue()) {
                    count++;
                }
            }

        }
        return count;
    }


}

//TLE of testcase 5
//Complexity: O(m*n)
class SolutionBruteForce {
    public static int getCount(int[] arr, int start, int end) {
        HashMap<Integer, Integer> map = new HashMap();
        int count = 0;


        for (int i = start; i <= end; i++) {
            if (!map.containsKey(arr[i])) {
                map.put(arr[i], 0);
            }
            map.put(arr[i], map.get(arr[i]) + 1);
        }
        //System.out.println(map);

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == entry.getKey()) {
                count++;
            }
        }

        return count;

    }
}