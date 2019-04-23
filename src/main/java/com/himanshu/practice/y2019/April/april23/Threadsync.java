package com.himanshu.practice.y2019.April.april23;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by himanshubhardwaj on 23/04/19.
 */
public class Threadsync {
    static ArrayList<ThreadPOC> threadPOCArrayList;
    static int numberThreads;

    public static void main(String[] args) throws InterruptedException {
        numberThreads = 10;
        threadPOCArrayList = new ArrayList<>();
        IntegerD integerD = new IntegerD(new AtomicInteger(0));

        for (int i = 0; i < numberThreads; i++) {
            threadPOCArrayList.add(new ThreadPOC(integerD, i, threadPOCArrayList));
        }


        for (int i = 0; i < numberThreads; i++) {
            threadPOCArrayList.get(i).start();
        }
        Thread.sleep(1000);



        for (int i = 0; i < threadPOCArrayList.size(); i++) {
            System.out.println(threadPOCArrayList.get(i).getId() + "\t" + threadPOCArrayList.get(i).getState() + "\t\t" + threadPOCArrayList.get(i));
        }


        Thread.sleep(10000);


        for (int i = 0; i < threadPOCArrayList.size(); i++) {
            System.out.println(threadPOCArrayList.get(i).getId() + "\t" + threadPOCArrayList.get(i).getState() + "\t\t" + threadPOCArrayList.get(i));
        }

        for (Thread t : threadPOCArrayList) {
            t.interrupt();
        }


    }
}


@AllArgsConstructor
@ToString
@Slf4j
class ThreadPOC extends Thread {
    private volatile IntegerD integerD;
    private int threadIndex;
    @ToString.Exclude
    private ArrayList<ThreadPOC> threadPOCArrayList;

    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                if ((integerD.getN() % threadPOCArrayList.size()) != threadIndex) {
                    synchronized (threadPOCArrayList.get(threadIndex)) {
                        if (integerD.getN() % threadPOCArrayList.size() != threadIndex) {
                            //this wait belongs to object. not to thread
                            wait();
                        }
                    }
                }
                System.out.println(threadIndex + "\t\t" + integerD);
                integerD.increment();
                synchronized (threadPOCArrayList.get(integerD.getN() % threadPOCArrayList.size())) {
                    threadPOCArrayList.get(integerD.getN() % threadPOCArrayList.size()).notify();
                }
            }
        } catch (InterruptedException | IllegalMonitorStateException e) {
            e.printStackTrace();
        }
    }
}


@AllArgsConstructor
@ToString
class IntegerD {
    private AtomicInteger n;

    int getN() {
        return n.intValue();
    }

    void increment() {
        n.incrementAndGet();
    }
}