package com.himanshu.practice.y2019.feb.feb2.feb24;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by himanshubhardwaj on 24/02/19.
 *
 */
public class ThreadSync {
    public static void main(String[] args) {
        int numThreads = 10;

        ArrayList<Thread> threadPOCS = new ArrayList<>();
        AtomicInteger atomicInteger = new AtomicInteger(0);

        for (int i = 0; i < numThreads; i++) {
            threadPOCS.add(new Thread(new ThreadPOC(i, atomicInteger, numThreads)));
        }

        for(Thread t: threadPOCS) {
            t.start();
        }

    }
}


@AllArgsConstructor
class ThreadPOC implements Runnable {
    int threadID;
    AtomicInteger value;
    int numThreads;


    @Override
    public void run() {
        while (true) {
            if (threadID == (value.intValue() % numThreads)) {
                synchronized (value) {
                    if (threadID == (value.intValue() % numThreads)) {
                        System.out.println(threadID+ "\t" + value.intValue());
                        value.incrementAndGet();
                    }
                }
            }
        }
    }
}