package com.himanshu.practice.y2019.feb.feb2.feb24;


import lombok.AllArgsConstructor;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;
import java.lang.Thread;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by himanshubhardwaj on 26/03/19.
 */
public class SemophorePOC {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(5);

        LinkedList<ThreadPOC2> threads = new LinkedList<>();
        AtomicInteger am = new AtomicInteger(10000);

        for (int i = 0; i < 10; i++) {
            threads.addLast(new ThreadPOC2(semaphore, Integer.toString(i), am));
        }

        for (ThreadPOC2 t : threads) {
            t.start();
        }
    }
}


@AllArgsConstructor
class ThreadPOC2 extends Thread {
    Semaphore semaphore;
    String threadName;
    AtomicInteger atomicInteger;

    public void run() {
        while (atomicInteger.intValue() > 0) {
            try {
                if (atomicInteger.intValue() > 0) {
                    semaphore.acquire();
                    if (atomicInteger.intValue() > 0) {
                        System.out.println("Semaphore acquired in thread: " + threadName);
                        atomicInteger.decrementAndGet();
                    }
                    semaphore.release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(threadName+"\t"+semaphore.availablePermits());
    }
}