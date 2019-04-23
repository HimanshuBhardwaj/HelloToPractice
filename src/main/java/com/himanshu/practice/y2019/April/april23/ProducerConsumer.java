package com.himanshu.practice.y2019.April.april23;


import lombok.AllArgsConstructor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ProducerConsumer {
    public static void main(String[] args) throws InterruptedException {
        Queue q = new Queue(10);
        Producer p = new Producer(q);

        //consumer group
        Consumer c1 = new Consumer(q);
        Consumer c2 = new Consumer(q);
        Consumer c3 = new Consumer(q);
        p.start();
        c1.start();
        c2.start();
        c3.start();

        Thread.sleep(500);
        p.interrupt();
        c1.interrupt();
        c2.interrupt();
        c3.interrupt();

    }
}


class Queue {
    int maxSize;
    BlockingQueue<String> blockingQueue;

    public Queue(int maxSize) {
        this.maxSize = maxSize;
        this.blockingQueue = new LinkedBlockingQueue<>(maxSize);
    }
}

@AllArgsConstructor
class Consumer extends Thread {
    private volatile Queue queue;

    public void run() {
        while (!isInterrupted()) {
            try {
                System.out.println(queue.blockingQueue.take()+"\t\t"+Thread.currentThread().getId());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}



@AllArgsConstructor
class Producer extends Thread {
    private volatile Queue queue;

    public void run() {

        int count = 0;

        while (!isInterrupted()) {
            if (queue.blockingQueue.size()==queue.maxSize) {
                queue.blockingQueue.poll();
            }
            queue.blockingQueue.add("" + count);
            count++;
        }

    }

}
