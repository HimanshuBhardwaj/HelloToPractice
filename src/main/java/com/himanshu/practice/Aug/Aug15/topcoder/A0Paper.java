package com.himanshu.practice.Aug.Aug15.topcoder;

/**
 * Created by himanshubhardwaj on 15/08/18.
 */
public class A0Paper {
    public static void main(String[] args) {
        A0Paper a0Paper = new A0Paper();
        long [] A = {4096, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1};
        System.out.println(a0Paper.canBuild(A));

    }


    public String canBuild(long[] A) {

        for (int i = 0; i < A.length; i++) {
            if (i < (A.length - 1)) {
                A[i + 1] += 2 * A[i];
                System.out.println(A[i + 1]);
            }
        }


        if ((double) (A[A.length - 1]) >= Math.pow(2, A.length - 1)) {
            return "Possible";
        } else {
            return "Impossible";
        }
    }
}
