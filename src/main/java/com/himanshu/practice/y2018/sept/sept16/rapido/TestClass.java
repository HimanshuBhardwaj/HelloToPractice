package com.himanshu.practice.y2018.sept.sept16.rapido;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by himanshubhardwaj on 16/09/18.
 */
public class TestClass {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pr = new PrintWriter(System.out);

        while (t-- > 0) {
            String str[] = br.readLine().split(" ");
            int n = Integer.parseInt(str[0]);
            int k = Integer.parseInt(str[1]);
            int q = Integer.parseInt(str[2]);


            str = br.readLine().split(" ");

            City[] cities = new City[n];
            City[] citiesO = new City[n];


            for (int i = 0; i < n; i++) {
                cities[i] = new City(Long.parseLong(str[i]), i, -1);
                citiesO[i] = cities[i];
            }

            Arrays.sort(cities);


            ArrayList<Cluster> cl = new ArrayList<>();

            int cId = 0;
            cities[0].cluster = cId;
            cl.add(new Cluster(cId));
            cl.get(0).cities.add(cities[0]);


            for (int i = 1; i < cities.length; ) {
                while (i < cities.length && (cities[i].location - cities[i - 1].location) <= k) {
                    cities[i].cluster = cId;
                    cl.get(cId).cities.add(cities[i]);
                    i++;
                }

                if (i < cities.length) {
                    cId++;
                    cl.add(cId, new Cluster(cId));
                    cl.get(cId).cities.add(cities[i]);
                    cities[i].cluster = cId;
                    i++;
                }
            }


            Comparator<City> comp = new Comparator<City>() {
                @Override
                public int compare(City o1, City o2) {
                    return o1.index - o2.index;
                }
            };

            for (int i = 0; i < cl.size(); i++) {
                Collections.sort(cl.get(i).cities, comp);
                //   System.out.println(cl.get(i));
            }

            for (int i = 0; i < q; i++) {
                str = br.readLine().split(" ");
                int l = Integer.parseInt(str[0]) - 1;
                int r = Integer.parseInt(str[1]) - 1;
                int x = Integer.parseInt(str[2]) - 1;
                pr.append(numbersAvailable(l, r, cl.get(citiesO[x].cluster).cities, comp));
                pr.append("\n");
            }

            pr.flush();


        }
    }


    //hehe we are given a sorted list of cities, we have to return
    private static String numbersAvailable(int l, int r, ArrayList<City> cities, Comparator<City> comp) {

        int lP = ceilSearch(cities, 0, cities.size() - 1, l);
        int hp = floorSearch(cities, 0, cities.size() - 1, r);

        if (lP >= 0 && hp >= 0 && l <= cities.get(lP).index && r >= cities.get(hp).index) {
            return String.valueOf(Math.abs(hp - lP) + 1);
        } else {
            return String.valueOf(0);
        }

    }


    /* Function to get index of floor of x in
arr[low..high] */
    static int floorSearch(ArrayList<City> arr, int low, int high, int x) {

        if (low > high)
            return -1;


        if (x >= arr.get(high).index)
            return high;


        int mid = (low + high) / 2;


        if (arr.get(mid).index == x)
            return mid;

        if (mid > 0 && arr.get(mid - 1).index <= x && x < arr.get(mid).index)
            return mid - 1;

        if (x < arr.get(mid).index)
            return floorSearch(arr, low, mid - 1, x);

        return floorSearch(arr, mid + 1, high, x);
    }


    static int ceilSearch(ArrayList<City> arr, int low, int high, int x) {

        int mid;

        if (x <= arr.get(low).index)
            return low;

        if (x > arr.get(high).index)
            return -1;


        mid = (low + high) / 2;  /* low + (high - low)/2 */

        if (arr.get(mid).index == x)
            return mid;

        else if (arr.get(mid).index < x) {
            if (mid + 1 <= high && x <= arr.get(mid + 1).index)
                return mid + 1;
            else
                return ceilSearch(arr, mid + 1, high, x);
        } else {
            if (mid - 1 >= low && x > arr.get(mid - 1).index)
                return mid;
            else
                return ceilSearch(arr, low, mid - 1, x);
        }
    }


}


class City implements Comparable<City> {
    long location;
    int index;
    int cluster;

    @java.beans.ConstructorProperties({"location", "index", "cluster"})
    public City(long location, int index, int cluster) {
        this.location = location;
        this.index = index;
        this.cluster = cluster;
    }

    @Override
    public int compareTo(City o) {
        return (int) (this.location - o.location);
    }

    public String toString() {
        return "City(location=" + this.location + ", index=" + this.index + ", cluster=" + this.cluster + ")";
    }
}

class Cluster {
    ArrayList<City> cities = new ArrayList<>();
    int id;

    @java.beans.ConstructorProperties({"cities", "id"})
    public Cluster(int id) {
        this.id = id;
    }

    public String toString() {
        return "Cluster(cities=" + this.cities + ", id=" + this.id + ")";
    }
}