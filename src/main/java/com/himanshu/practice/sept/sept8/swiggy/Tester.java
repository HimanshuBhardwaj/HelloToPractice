package com.himanshu.practice.sept.sept8.swiggy;

import java.util.List;

/**
 * Created by himanshubhardwaj on 08/09/18.
 */
public class Tester {
    public static void main(String[] args) {


        CityManager delhi = new CityManager("Himanshu", 1, 20, 1);
        FleetManager delhi1 = new FleetManager("Karan", 2, 10, 1);
        FleetManager delhi2 = new FleetManager("Karan", 3, 10, 4);
        delhi1.addCityManager(delhi);
        delhi2.addCityManager(delhi);

        DeliveryExecutive d11 = new DeliveryExecutive("D11", 5, 1, 1);
        DeliveryExecutive d12 = new DeliveryExecutive("D12", 6, 2, 4);
        DeliveryExecutive d13 = new DeliveryExecutive("D13", 7, 2, 4);
        DeliveryExecutive d14 = new DeliveryExecutive("D14", 8, 2, 4);
        DeliveryExecutive d15 = new DeliveryExecutive("D15", 9, 2, 4);

        DeliveryExecutive d21 = new DeliveryExecutive("D21", 10, 3, 2);
        DeliveryExecutive d22 = new DeliveryExecutive("D22", 11, 4, 3);
        DeliveryExecutive d23 = new DeliveryExecutive("D23", 12, 4, 3);
        DeliveryExecutive d24 = new DeliveryExecutive("D24", 13, 4, 3);
        DeliveryExecutive d25 = new DeliveryExecutive("D25", 14, 4, 3);

        d11.addFleetManager(delhi1);
        d12.addFleetManager(delhi1);
        d13.addFleetManager(delhi1);
        d14.addFleetManager(delhi1);
        d15.addFleetManager(delhi1);

        d21.addFleetManager(delhi2);
        d22.addFleetManager(delhi2);
        d23.addFleetManager(delhi2);
        d24.addFleetManager(delhi2);
        d25.addFleetManager(delhi2);


        //printing hierarchy
        delhi.printHierarchy();

        delhi.distributeBonus(93);

        System.out.println("-----");
        delhi.printHierarchy();


        System.out.println();

        List<DeliveryExecutive> de = DeliveryExecutive.getTopKDeliveryExecutive(5);
        for (DeliveryExecutive d : de) {
            System.out.println(d);
        }
    }
}
