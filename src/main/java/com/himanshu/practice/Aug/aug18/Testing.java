package com.himanshu.practice.Aug.aug18;

/**
 * Created by himanshubhardwaj on 18/08/18.
 */
public class Testing {
    public static void main(String[] args) {
        Testing t = new B();
        t.hello();
        t = new Testing();
        t.hello();
    }


    public void hello() {
        System.out.println("Hello Testing");
    }
}


class B extends Testing {
    public void hello() {
        System.out.println("Hello C1");
    }
}
