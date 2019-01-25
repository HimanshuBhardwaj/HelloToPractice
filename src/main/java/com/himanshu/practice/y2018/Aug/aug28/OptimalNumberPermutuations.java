package com.himanshu.practice.y2018.Aug.aug28;

/**
 * Created by himanshubhardwaj on 28/08/18.
 */
public class OptimalNumberPermutuations {
    static int min = Integer.MAX_VALUE;
    static int copuA[];

    public static void main(String[] args) {

POC();
    }


    //result of POC, for all n, minimum sum will be zero
    static void POC() {
        for (int i = 1; i <= 6; i++) {
            min = Integer.MAX_VALUE;
            int[] a = new int[2 * i];
            copuA = new int[2 * i];

            for (int j = 0; j < i; j++) {
                a[j] = j + 1;
                a[2 * i - 1 - j] = j + 1;
            }

            System.out.println("------------------------------");
            for (int x : a) {
                System.out.print(x + "\t");
            }
            System.out.println();
            permutuate(a, 0);
            System.out.println(min + "\t" + i);
        }
    }


    static void permutuate(int a[], int start) {
        if (start == a.length) {
            int d = computeD(a);
//            for (int x : a) {
//                System.out.print(x + " ");
//            }
//            System.out.println(d);

            if (d < min) {
                min = d;
//                for (int i = 0; i < copuA.length; i++) {
//                    copuA[i] = a[i];
//                }
            }
            return;
        }

        for (int i = start; i < a.length; i++) {
            swap(a, i, start);
            permutuate(a, start + 1);
            swap(a, i, start);
        }


    }

    static void swap(int a[], int pos1, int pos2) {
        int temp = a[pos1];
        a[pos1] = a[pos2];
        a[pos2] = temp;
    }


    static int computeD(int[] a) {
        int d[] = new int[1 + (a.length / 2)];
        for (int i = 0; i < d.length; i++) {
            d[i] = -1;
        }

        for (int i = 0; i < a.length; i++) {
            if (d[a[i]] == -1) {
                d[a[i]] = i;
            } else {
                d[a[i]] = Math.abs(d[a[i]] - i);
            }
        }
        int sum = 0;

        for (int i = 1; i < d.length; i++) {
            sum += Math.abs((d[i] - (a.length / 2) + i)) * ((a.length / 2) - i);
        }

        return sum;
    }
}
