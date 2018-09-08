package com.himanshu.practice.sept.sept8.swiggy;


import lombok.Getter;
import lombok.Setter;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by himanshubhardwaj on 08/09/18.
 */


public class CityManager extends Employee {
    public List<FleetManager> list;
    @Getter
    @Setter
    private long commulativeRaiting;


    public CityManager(FleetManager f, String name, int id, double salary, int raiting) {
        this(name, id, salary, raiting);
        addFleetManager(f);
    }

    public CityManager(String name, int id, double salary, int raiting) {
        super(name, id, salary, raiting, 0, "City Manager");
        list = new LinkedList<>();
        commulativeRaiting = 0;
    }


    public void addFleetManager(FleetManager f) {
        list.add(f);
        commulativeRaiting += f.commulativeRaiting;
    }

    @Override
    public void printHierarchy() {
        printHierarchyHelper(this);
    }

    private void printHierarchyHelper(CityManager f) {
        System.out.println(f);
        for (FleetManager fm : f.list) {
            fm.printHierarchy();
        }
    }

    public String toString() {
        return super.toString() + "Commulative Raiting: " + commulativeRaiting;
    }

    public void distributeBonus(double bonus) {
        if (list.size() == 0) {
            System.out.println(this + " City manager is not valid");
            return;
        }
        for (FleetManager f : list) {
            f.distributeBonus((f.commulativeRaiting * bonus / (double) commulativeRaiting));

        }
    }

    public boolean isValidCityManager() {
        if (list.size() > 0) {
            return true;
        }
        return false;
    }
}
