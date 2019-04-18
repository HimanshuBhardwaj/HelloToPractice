package com.himanshu.practice.y2019.April.april19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by himanshubhardwaj on 18/04/19.
 */
public class D {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        ArrayList<Person> p = new ArrayList<>(n);

        for (int i = 0; i < n; i++) {
            String str[] = br.readLine().split(" ");
            int a = Integer.parseInt(str[0]);
            int b = Integer.parseInt(str[1]);
            p.add(new Person(i + 1, a, b));
        }
        Collections.sort(p);
        //System.out.println(p);

        long sum = 0;

        for (int i = 0; i < n; i++) {
            sum += ((long) i) * p.get(i).a + ((long) (n - i - 1)) * p.get(i).b;
        }

        System.out.print(sum);


    }
}

class Person implements Comparable<Person> {
    int index;
    int a;
    int b;

    @java.beans.ConstructorProperties({"index", "a", "b"})
    public Person(int index, int a, int b) {
        this.index = index;
        this.a = a;
        this.b = b;
    }

    @Override
    public int compareTo(Person o) {
        return -1 * ((this.a - this.b) - (o.a - o.b));
    }

    public String toString() {
        return "Person(index=" + this.index + ", a=" + this.a + ", b=" + this.b + ")\n";
    }
}
