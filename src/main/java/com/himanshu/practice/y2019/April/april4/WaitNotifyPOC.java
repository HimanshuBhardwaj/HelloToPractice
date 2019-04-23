package com.himanshu.practice.y2019.April.april4;

import lombok.AllArgsConstructor;
import lombok.Synchronized;
import lombok.ToString;

import java.lang.management.ThreadMXBean;

public class WaitNotifyPOC {
    public static void main(String[] args) throws InterruptedException {
        Messge msg = new Messge();
        Sender sender = new Sender(msg);
        Receiver receiver = new Receiver(msg);
        sender.start();
        receiver.start();
        Thread.sleep(1000);
        sender.interrupt();
        receiver.interrupt();


    }

}

@ToString
class Messge {
    String message;
}

@AllArgsConstructor
class Sender extends Thread {
    Messge msg;

    public void run() {
        while (!this.isInterrupted()) {
            synchronized (msg) {
                try {
                    msg.wait();
                    System.out.println(msg);
                    msg.notify();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }
    }
}

@AllArgsConstructor
class Receiver extends Thread {
    Messge msg;


    public void run() {
        int count = 0;
        while (!this.isInterrupted()) {
            synchronized (msg) {
                msg.message = "" + count + "";
                try {
                    msg.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count++;
                msg.notify();
            }
        }
    }
}
