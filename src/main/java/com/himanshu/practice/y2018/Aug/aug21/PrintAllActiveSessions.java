package com.himanshu.practice.y2018.Aug.aug21;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.*;

/**
 * Created by himanshubhardwaj on 21/08/18.
 * uber
 * active sessions
 */
public class PrintAllActiveSessions {
    public static void main(String[] args) {
        Sessionshandler sessionshandler = new Sessionshandler();
        sessionshandler.insert(0d, 3d);
        sessionshandler.insert(1d, 1.2d);
        sessionshandler.insert(1d, 2d);
        sessionshandler.insert(1d, 2d);
        sessionshandler.insert(1.2d, 3d);
        sessionshandler.insert(1.2d, 3d);
        sessionshandler.insert(2d, 2.2d);
        sessionshandler.insert(2.2d, 3d);
        sessionshandler.printActiveSessions();

    }
}

class Sessionshandler {
    TreeMap<Double, LinkedList<Time>> sessions = new TreeMap<>();

    void insert(double start, double end) {
        if (!sessions.containsKey(start)) {
            sessions.put(start, new LinkedList<Time>());
        }

        if (!sessions.containsKey(end)) {
            sessions.put(end, new LinkedList<Time>());
        }

        sessions.get(start).addLast(new Time(start, true));
        sessions.get(end).addLast(new Time(end, false));
    }

    void printActiveSessions() {
        int count = 0;
        for (Map.Entry<Double, LinkedList<Time>> entry : sessions.entrySet()) {
            LinkedList<Time> list = entry.getValue();
            for (Time t : list) {
               // System.out.println(t);
                if (t.isSessionStart) {
                    count++;
                } else {
                    count--;
                }
            }
            System.out.println(entry.getKey() + "\t" + count);
        }
    }


}

@ToString
@AllArgsConstructor
class Time implements Comparable<Time> {
    double time;
    @EqualsAndHashCode.Exclude
    boolean isSessionStart;

    @Override
    public int compareTo(Time o) {
        return Double.compare(this.time, o.time);
    }
}
