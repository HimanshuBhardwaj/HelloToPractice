package com.himanshu.practice.y2019.April.april28;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by himanshubhardwaj on 28/04/19.
 * TODO: Implement it via SQRT Decomposition and Persistant Segment Tree
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

        SqrtDecomposition sqrtDecomposition = new SqrtDecomposition(n, arr);

//        CommulativeSubtraction commulativeSubtraction = new CommulativeSubtraction(n, arr);
//
        for (int i = 0; i < m; i++) {
            str = br.readLine().split(" ");
            int l = Integer.parseInt(str[0]) - 1;
            int r = Integer.parseInt(str[1]) - 1;
            //pw.append(SolutionBruteForce.getCount(arr, l, r) + "\n");
            pw.append(sqrtDecomposition.getCount(l, r) + "\n");
        }
        pw.flush();
    }
}


class PersistentSegmentTree {


}


//TLE On this also
class SqrtDecomposition {
    int arr[];
    int size;
    TreeMap<Integer, Integer>[] sqrtFrequencies;
    int compomentsNum;
    int componentSize;


    public SqrtDecomposition(int n, int[] arr) {
        this.componentSize = (int) Math.sqrt(arr.length);
        this.compomentsNum = (int) Math.ceil(((double) arr.length) / componentSize);
        this.size = arr.length;
        this.arr = arr;

        sqrtFrequencies = new TreeMap[compomentsNum];


        for (int i = 0; i < compomentsNum; i++) {
            sqrtFrequencies[i] = new TreeMap<>();
        }

        for (int i = 0; i < arr.length; i++) {
            if (!sqrtFrequencies[getComponent(i)].containsKey(arr[i])) {
                sqrtFrequencies[getComponent(i)].put(arr[i], 0);//frequency
            }
            sqrtFrequencies[getComponent(i)].put(arr[i], 1 + sqrtFrequencies[getComponent(i)].get(arr[i]));
        }

//        System.out.println("Printing Function");
//        for (int i = 0; i < sqrtFrequencies.length; i++) {
//            System.out.println(i + "\t\t" + sqrtFrequencies[i]);
//        }
//        System.out.println("-----prep over-----");
    }


    void mergeFrequencies(TreeMap<Integer, Integer> ourMap, final TreeMap<Integer, Integer> systemMap) {
        for (Map.Entry<Integer, Integer> entry : systemMap.entrySet()) {
            if (!ourMap.containsKey(entry.getKey())) {
                ourMap.put(entry.getKey(), 0);
            }
            ourMap.put(entry.getKey(), ourMap.get(entry.getKey()) + entry.getValue());
        }
    }

    public int getComponent(int index) {
        return index / componentSize;
    }

    public int getCount(int start, int end) {
        int startComponent = getComponent(start);
        int endComponent = getComponent(end);

        TreeMap<Integer, Integer> frequency = new TreeMap<>();

        if (startComponent == endComponent) {
            for (int i = start; i <= end; i++) {
                if (!frequency.containsKey(arr[i])) {
                    frequency.put(arr[i], 0);
                }
                frequency.put(arr[i], frequency.get(arr[i]) + 1);
            }
        } else {
            for (int i = startComponent + 1; i <= endComponent - 1; i++) {
                mergeFrequencies(frequency, sqrtFrequencies[i]);
            }
            for (int i = start; i < ((startComponent + 1) * componentSize); i++) {
                if (!frequency.containsKey(arr[i])) {
                    frequency.put(arr[i], 0);
                }
                frequency.put(arr[i], frequency.get(arr[i]) + 1);
            }

            for (int i = endComponent * componentSize; i <= end; i++) {
                if (!frequency.containsKey(arr[i])) {
                    frequency.put(arr[i], 0);
                }
                frequency.put(arr[i], frequency.get(arr[i]) + 1);
            }
        }


        int count = 0;

        for (Map.Entry<Integer, Integer> entry : frequency.entrySet()) {
            if (entry.getKey() == entry.getValue()) {
                count++;
            }
        }
//        System.out.println("L:" + start + ",R:" + end + "=frequency:\t\t" + frequency);
//        System.out.println("L:" + start + ",R:" + end + "=Count:" + count);
//        System.out.println();
//        System.out.println();

        return count;
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

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == entry.getKey()) {
                count++;
            }
        }

        return count;

    }
}