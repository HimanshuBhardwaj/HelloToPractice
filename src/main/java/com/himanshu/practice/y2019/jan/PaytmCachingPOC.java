package com.himanshu.practice.y2019.jan;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by himanshubhardwaj on 25/01/19.
 */

public class PaytmCachingPOC implements Runnable {
    int threadId;
    AtomicInteger ai=new AtomicInteger(0);

    public PaytmCachingPOC(int threadId) {
        this.threadId = threadId;
    }

    @Override
    public void run() {

        if (threadId == 1) {
            UtilClass.thread1Mainipulations();
        } else {
            UtilClass.thread2Mainipulations();
        }
    }

    public static void main(String[] args) {
        PaytmCachingPOC thread1 = new PaytmCachingPOC(1);
        PaytmCachingPOC thread2 = new PaytmCachingPOC(2);

        Thread t1 = new Thread(thread1);
        Thread t2 = new Thread(thread2);

        t2.start();
        t1.start();
    }
}


class UtilClass {
    static int value = 1;
    static AtomicBoolean flag=new AtomicBoolean(true);

    static void thread1Mainipulations() {
        while (flag.equals(false)) {
            value++;
        }
        System.out.println("thread1Mainipulations ended "+ value);
    }


    static void thread2Mainipulations() {
        try {
            Thread.sleep(1000);
            flag.getAndSet(false);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("thread2Mainipulations ended");
    }
}