package com.himanshu.practice.y2018.sept.sept8.swiggy;


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

    //We can make this generic :-(
    private void printHierarchyHelper(CityManager f) {
        System.out.println(f);
        for (FleetManager fm : f.list) {
            fm.printHierarchy();
        }
    }

    public String toString() {
        //change this according to your needs
        return super.toString() + "Commulative Raiting: " + commulativeRaiting;
    }

    public void distributeBonus(double bonus) {
        if (list.size() == 0) {
            System.out.println(this + " City manager is not valid");
            return;
        }
        //with could losse some bonus into precision
        for (FleetManager f : list) {
            f.distributeBonus((f.commulativeRaiting * bonus / (double) commulativeRaiting));
        }
    }

    public boolean isValidCityManager() {
        //lazy checking
        if (list.size() > 0) {
            return true;
        }
        return false;
    }
}
