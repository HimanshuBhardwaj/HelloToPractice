package com.himanshu.practice.sept.sept8.swiggy;

import lombok.Getter;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by himanshubhardwaj on 08/09/18.
 */
public class DeliveryExecutive extends Employee {

    @Getter
    private FleetManager fleetManager;
    private static PriorityQueue<DeliveryExecutive> deliveryExecutives = new PriorityQueue<>(new DeliveryExecutiveComparator());

    public DeliveryExecutive(FleetManager f, String name, int id, double salary, int raiting) {
        this(name, id, salary, raiting);
        addFleetManager(f);
    }

    public DeliveryExecutive(String name, int id, double salary, int raiting) {
        super(name, id, salary, raiting, 0, "Delivery Executive");
        deliveryExecutives.add(this);
    }

    public void addFleetManager(FleetManager f) {
        this.fleetManager = f;
        if (!f.list.contains(this)) {
            f.list.add(this);
            f.commulativeRaiting += this.raiting;
            f.cityManager.setCommulativeRaiting(f.cityManager.getCommulativeRaiting() + this.raiting);
        }
    }

    @Override
    public void printHierarchy() {
        printHierarchyHelper(this);
    }

    private void printHierarchyHelper(DeliveryExecutive f) {
        System.out.println(this);
    }

    public String toString() {
        return super.toString();
    }

    static public List<DeliveryExecutive> getTopDeliveryExecutive() {
        return getTopKDeliveryExecutive(5);

    }

    static public List<DeliveryExecutive> getTopKDeliveryExecutive(int k) {
        if (deliveryExecutives.size() < k) {
            return new LinkedList<>(deliveryExecutives);
        }

        //this is a hack
        PriorityQueue<DeliveryExecutive> pq = new PriorityQueue<>(new DeliveryExecutiveComparator());
        pq.addAll(deliveryExecutives);
        deliveryExecutives = pq;


        LinkedList<DeliveryExecutive> listReq = new LinkedList<>();

        for (int i = 0; i < k; i++) {
            listReq.addLast(deliveryExecutives.poll());
        }
        deliveryExecutives.addAll(listReq);
        return listReq;
    }

    public void distributeBonus(double b) {
        this.bonus += b;
    }

    private static class DeliveryExecutiveComparator implements Comparator<DeliveryExecutive> {
        @Override
        public int compare(DeliveryExecutive o1, DeliveryExecutive o2) {
            if (Double.compare(0d, o2.salary) == 0) {
                return 1;
            } else if (Double.compare(0d, o1.salary) == 0) {
                return -1;
            } else {
                return Double.compare(o2.bonus / o2.salary, o1.bonus / o1.salary);
            }
        }

    }
}
