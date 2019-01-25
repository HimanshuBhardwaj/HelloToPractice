package com.himanshu.practice.y2018.Aug.aug13.educationalround;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by himanshubhardwaj on 13/08/18.
 * Problem Statement: https://codeforces.com/contest/600/problem/D
 * TODO: Find Bug In the program
 */
public class AreaOfTwoCirclesIntersection {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        List<Circle> circles = new LinkedList<>();

        circles.add(new Circle(Double.parseDouble(str[0]), Double.parseDouble(str[1]), Double.parseDouble(str[2])));
        str = br.readLine().split(" ");
        circles.add(new Circle(Double.parseDouble(str[0]), Double.parseDouble(str[1]), Double.parseDouble(str[2])));
        Collections.sort(circles);
        double distance = Circle.distance(circles.get(0), circles.get(1));

        circles.get(0).x = 0;
        circles.get(0).y = 0;
        circles.get(1).y = 0;
        circles.get(1).x = distance;
        Circle circle1 = circles.get(0);
        Circle circle2 = circles.get(1);

        //----------------using formula

        System.out.println(Circle.getAreaFormula(circle2.r, circle1.r, circle2.x));

        if (circles != null) {
            return;
        }
        //--------------

        //TODO: Finish it using programming only
        //System.out.println(circles);

        if ((circle1.r + circle2.r) <= circle2.x) {
            System.out.println(0);
        } else if (circle2.x > circle1.r) {
            double cosa = ((circle1.r * circle1.r) + (circle2.x * circle2.x) - (circle2.r * circle2.r)) / (2 * circle1.r * circle2.x);
            double sin2a = cosa * Math.sqrt(1 - (cosa * cosa)) * 2;
            double a = Math.acos(cosa);


            double cosb = ((circle2.x * circle2.x) + (circle2.r * circle2.r) - (circle1.r * circle1.r)) / (2 * circle2.x * circle2.r);
            double sin2b = cosb * Math.sqrt(1 - (cosb * cosb)) * 2;
            double b = Math.acos(cosb);

            double area = (2 * ((a * circle1.r * circle1.r) + (b * circle2.r * circle2.r))) - (((circle1.r * circle1.r * sin2a) + (circle2.r * circle2.r * sin2b)));
            area = area / (2);

            System.out.println(area);
        } else if (circle2.x <= circle1.r && ((circle2.x + circle2.r) > circle1.r)) {
            double cosa = ((circle1.r * circle1.r) + (circle2.x * circle2.x) - (circle2.r * circle2.r)) / (2 * circle1.r * circle2.x);
            double sin2a = cosa * Math.sqrt(1 - (cosa * cosa)) * 2;
            double a = Math.acos(cosa);

            double cosb = (((circle2.x * circle2.x) + (circle2.r * circle2.r) - (circle1.r * circle1.r)) / (2 * circle2.x * circle2.r));
            double sin2b = cosb * Math.sqrt(1 - (cosb * cosb)) * 2;
            double b = Math.acos(cosb);

            double triangleArea = areaOFTriangle(circle1.r, circle2.r, circle2.x);
            double area = (a * circle1.r * circle1.r) + (b * circle2.r * circle2.r) - triangleArea;
            System.out.println(area);
        } else if (circle2.x < circle1.r && ((circle2.x + circle2.r) <= circle1.r)) {
            double area = Math.PI * circle2.r * circle2.r;
            System.out.println(area);
        }


    }

    static double areaOFTriangle(double a, double b, double c) {
        double cosA = ((b * b) + (c * c) - (a * a)) / (2 * b * c);
        double sinA = Math.sqrt(1 - (cosA * cosA));
        return (b * c * sinA * (0.5d));
    }
}


class Circle implements Comparable<Circle> {
    double x;
    double y;
    double r;

    @java.beans.ConstructorProperties({"x", "y", "r"})
    public Circle(double x, double y, double r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    @Override
    public int compareTo(Circle o) {
        return Double.compare(o.r, this.r);
    }

    static public double distance(Circle a, Circle b) {
        return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
    }

    public String toString() {
        return "Circle(x=" + this.x + ", y=" + this.y + ", r=" + this.r + ")";
    }


    static double getAreaFormula(double r, double R, double d) {
        double term1 = r * r * Math.acos(((d * d + r * r) - (R * R)) / (2 * d * r));
        double term2 = R * R * Math.acos((d * d + R * R - r * r) / (2 * d * R));
        double term3 = Math.sqrt((-d + r + R) * (d + r - R) * (d - r + R) * (d + r + R));
        double term4 = (0.5d) * term3;

        return term1 + term2 - term3;
    }
}