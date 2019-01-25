package com.himanshu.practice.y2018.july.july22.hour2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * Created by himanshubhardwaj on 22/07/18.
 *
 Statement:

 Write a program to sort a set of given units and output a single relationship equation among the units in descending order of size.
 The input given will be a series of comma separated units and a set of relationship equations between them.
 From these equations, you are expected to derive a single relationship equation in descending order of the units, with the largest unit on the left.
 Further, the following are given: The number of equations given will be 1 less than the number of units given
 To keep it simple, only units that can be expressed as integer multiples of each other should be considered. Meaning, the equations must not contain fractional multipliers


 TODO: Took it lightly, could not finish it in time. But this was a good question
 *
 */
public class TestClass {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String units[] = br.readLine().split(",");
        Unit.unitMap = new Unit[units.length];
        long weights[][] = new long[units.length][units.length];


        int freQUnits[] = new int[units.length];

        for (int i = 0; i < units.length; i++) {
            Unit.unitToIntMap.put(units[i], i);
            Unit.intToUnitMap.put(i, units[i]);
            Unit.unitMap[i] = new Unit();
            Unit.unitMap[i].index = i;

        }

        // System.out.println();
        for (int i = 0; i < (Unit.unitToIntMap.size() - 1); i++) {
            String relation = br.readLine();
            StringTokenizer st = new StringTokenizer(relation, " ");
            String from = st.nextToken();
            st.nextToken();//of no use
            int relationCofficient = Integer.parseInt(st.nextToken());
            String to = st.nextToken();
//            System.out.println(from+"\t"+relationCofficient+"\t"+to);

            freQUnits[Unit.unitToIntMap.get(from)]++;
            freQUnits[Unit.unitToIntMap.get(to)]++;


            weights[Unit.unitToIntMap.get(from)][Unit.unitToIntMap.get(to)] = relationCofficient;


            Unit fromUnit = Unit.unitMap[Unit.unitToIntMap.get(from)];
            fromUnit.index = Unit.unitToIntMap.get(from);

//
//            if (fromUnit.nextUnit == null) {
//                fromUnit.nextUnit = Unit.unitMap[Unit.unitToIntMap.get(to)];
//                fromUnit.relationCofficient = relationCofficient;
//            } else {
//                if (fromUnit.relationCofficient > relationCofficient) {
//                    int temp = fromUnit.relationCofficient;
//                    Unit.unitMap[Unit.unitToIntMap.get(to)] = fromUnit.nextUnit;
//                    Unit.unitMap[Unit.unitToIntMap.get(to)].relationCofficient = fromUnit.relationCofficient / relationCofficient;
//                    fromUnit.relationCofficient = relationCofficient;
//                } else {
//
//                }
//            }


        }


        for (int k = 0; k < units.length; k++) {
            for (int i = 0; i < units.length; i++) {
                for (int j = 0; j < units.length; j++) {
                    if (i == j || j == k) {
                        continue;
                    }

                    if (weights[i][k] > 0 && weights[k][j] > 0) {
                        weights[i][j] = weights[i][k] * weights[k][j];
                    }
                    if (weights[i][k] > 0 && weights[j][k] > 0) {
                        if (weights[i][k] > weights[j][k]) {
                            weights[i][j] = weights[i][k] / weights[j][k];
                        } else {
                            weights[j][i] = weights[j][k] / weights[i][k];
                        }
                    }

                    if (weights[k][i] > 0 && weights[k][j] > 0) {
                        if (weights[k][i] > weights[k][j]) {
                            weights[j][i] = weights[k][i] / weights[k][j];
                        } else {
                            weights[i][j] = weights[k][j] / weights[k][i];
                        }
                    }
                }
            }
        }


        int root = -1;
        for (int k = 0; k < units.length; k++) {
            for (int i = 0; i < units.length; i++) {
                int parent = i;
                int count = 0;
                for (int j = 0; j < units.length; j++) {
                    if (weights[i][k] > 0 && weights[k][j] > 0 && weights[i][j] > 0) {
                        weights[i][j] = 0;
                    }
                }
                for (int j = 0; j < units.length; j++) {
                    if (weights[parent][j] > 0) {
                        count++;
                    }
                }
//                System.out.println(parent+"\t"+count+"to find parent");

                if (count == (units.length - 1)) {
                    root = parent;
                }

            }
        }

//        for (int i = 0; i < units.length; i++) {
//            for (int j = 0; j < units.length; j++) {
//                System.out.print(weights[i][j] + "\t");
//            }
//            System.out.println();
//        }
//        System.out.println();
//        System.out.println();


//        for (int i = 0; i < Unit.unitMap.length; i++) {
//            System.out.println(Unit.intToUnitMap.get(i) + "\t" + freQUnits[i]);
//        }
//        System.out.println(Unit.intToUnitMap);
//        System.out.println(Unit.unitToIntMap);
//        System.out.println();
//        System.out.println();

        for (Unit u : Unit.unitMap) {
            //System.out.println(u);
            if (u.nextUnit == null) {
                Unit.leaf = Unit.intToUnitMap.get(u.index);
            }
        }


        int parent = 0;
//        for (int i = 0; i < freQUnits.length; i++) {
//            //System.out.println("Frequency: "+freQUnits[i]);
//            if (freQUnits[i] == 1) {
//              //  System.out.println(Unit.unitMap[i]);
//                if (Unit.unitMap[i].nextUnit != null) {
//                    //parent = Unit.unitMap[i].index;
//                }
//            }
//        }

        Unit.dfs(root, 1, weights, new boolean[Unit.unitMap.length]);
    }
}


class Unit {
    static HashMap<String, Integer> unitToIntMap = new HashMap<>();
    static HashMap<Integer, String> intToUnitMap = new HashMap<>();
    static Unit[] unitMap;
    static long startingCofficient = 1l;
    static String leaf;


    int index;
    int relationCofficient;
    Unit nextUnit;

    public Unit() {
    }

    public String toString() {
        String returnVal = String.valueOf(startingCofficient) + intToUnitMap.get(index);
        startingCofficient = startingCofficient * relationCofficient;

        if (!intToUnitMap.get(index).equals(leaf)) {
            returnVal += " = " + nextUnit;
        }
        return returnVal;
    }

    public static void dfs(int node, long commulativeWeight, long[][] weights, boolean[] visited) {
        if (node < 0 || visited[node]) {
            return;
        }

        System.out.print(commulativeWeight + intToUnitMap.get(node));
        visited[node] = true;

        long minWeight = Long.MAX_VALUE;
        int index = -1;

        for (int i = 0; i < unitMap.length; i++) {
            if (!visited[i] && weights[node][i] > 0 && weights[node][i] < minWeight) {
                minWeight = weights[node][i];
                index = i;
            }
        }


        if (minWeight != Long.MAX_VALUE && index != -1) {
            System.out.print(" = ");
            dfs(index, commulativeWeight * weights[node][index], weights, visited);
        }
    }
}



/*


a,b,c,d
a = 2 b
a = 4 c
c = 2 d


* */