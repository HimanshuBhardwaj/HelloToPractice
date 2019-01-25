package com.himanshu.practice.y2018.sept.sept8.swiggy;

import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by himanshubhardwaj on 08/09/18.
 */

public class FleetManager extends Employee {
    List<DeliveryExecutive> list;
    CityManager cityManager;
    @Getter
    long commulativeRaiting;


    public FleetManager(DeliveryExecutive d, CityManager c, String name, int id, double salary, int raiting) {
        this(name, id, salary, raiting);
        list.add(d);
        addCityManager(c);
    }

    public FleetManager(String name, int id, double salary, int raiting) {
        super(name, id, salary, raiting, 0, "Fleet Manager");
        list = new LinkedList<>();
    }

    public void addCityManager(CityManager c) {
        //assuming we are changing city manager onlyonce
        if (this.cityManager == null) {
            this.cityManager = c;
            c.list.add(this);
            c.setCommulativeRaiting(c.getCommulativeRaiting() + this.commulativeRaiting);
        }
    }


    public void addDeliveryExecutive(DeliveryExecutive d) {
        list.add(d);
        commulativeRaiting += d.raiting;
        cityManager.setCommulativeRaiting(cityManager.getCommulativeRaiting() + d.raiting);
    }

    @Override
    public void printHierarchy() {
        printHierarchyHelper(this);
    }

    private void printHierarchyHelper(FleetManager f) {
        System.out.println(f);
        for (DeliveryExecutive d : f.list) {
            d.printHierarchy();
        }
    }

    public String toString() {
        return super.toString() + "Commulative Raiting: " + commulativeRaiting;

    }

    public void distributeBonus(double bonus) {
        if (!isValidFleetManager()) {
            //lazy checking
            System.out.println(this + " fleet mananager is not valid");
            return;
        }

        for (DeliveryExecutive d : list) {
            d.distributeBonus((d.raiting * bonus) / commulativeRaiting);
        }
    }

    public boolean isValidFleetManager() {
        if (list.size() > 0 ) {
            return true;
        }
        return false;
    }
}
