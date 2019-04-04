package com.himanshu.practice.y2019.April.april3;

import lombok.AllArgsConstructor;

import java.util.concurrent.Callable;

/**
 * Created by himanshubhardwaj on 05/04/19.
 */
@AllArgsConstructor
public class CallablePOCMain extends Thread {
    Callable c;

    @Override
    public void run() {
        try {
            c.call();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        CallableImp cimp = new CallableImp();

        CallablePOCMain t1 = new CallablePOCMain(cimp);
        CallablePOCMain t2 = new CallablePOCMain(cimp);
        CallablePOCMain t3 = new CallablePOCMain(cimp);

        t1.start();
        t2.start();
        t3.start();


    }
}


class CallableImp implements Callable {

    @Override
    public Object call() throws Exception {
        System.out.println("Call called from Thread:" + Thread.currentThread().getName());
        return null;
    }
}
