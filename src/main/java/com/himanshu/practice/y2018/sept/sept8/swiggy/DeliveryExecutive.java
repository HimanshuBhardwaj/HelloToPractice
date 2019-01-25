package com.himanshu.practice.y2018.sept.sept8.swiggy;

import lombok.Getter;

import java.util.*;

/**
 * Created by himanshubhardwaj on 08/09/18.
 */
public class DeliveryExecutive extends Employee {

    @Getter
    private FleetManager fleetManager;
    //Array list because while sorting this will have locality of refrence. Also i'm doing .get(i)
    private static ArrayList<DeliveryExecutive> deliveryExecutives = new ArrayList<>();

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
        //assuming this function will be called very rarely; otherwise we will have to implement fibonacci heap which
        // could take care of dynamically changing priorities because of bonus distribution. PriorityQueue of java can
        // not handle that.
        Collections.sort(deliveryExecutives, new DeliveryExecutiveComparator());
        if (deliveryExecutives.size() < k) {
            //you dont know what all operations the caller is doing, so printing lones version.
            return new LinkedList<>(deliveryExecutives);
        }


        LinkedList<DeliveryExecutive> listReq = new LinkedList<>();

        for (int i = 0; i < k; i++) {
            listReq.addLast(deliveryExecutives.get(i));
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
            if (Double.compare(0d, o2.salary) == 0 || Double.compare(0d, o1.salary) == 0) {
                return Double.compare(o2.bonus, o1.bonus);
            } else {
                return Double.compare(o2.bonus / o2.salary, o1.bonus / o1.salary);
            }
        }

    }
}
