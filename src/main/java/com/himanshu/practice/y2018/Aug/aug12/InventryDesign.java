//package com.himanshu.practice.y2018.Aug.aug12;

import java.util.*;

/**
 * Created by himanshubhardwaj on 12/08/18.
 * This was an online test.
 */
public class InventryDesign {
    public static void main(String[] args) {
        Taxes taxes = DummyData.getTaxesDummyData();
        serveFirstcustomer(taxes);

        System.out.println();
        System.out.println("----------------------------------------------------------");
        System.out.println();

        serveSecondcustomer(taxes);
    }

    static void serveFirstcustomer(Taxes taxes) {
        //Generate Data of Taxes and input1
        String input1[] = DummyData.getInput1();
        Driver customer1 = new Driver();
        //Computing output recipt
        String outputRecipt1[] = customer1.drive(input1, taxes);
        //Printing this output recipt;
        for (String entry : outputRecipt1) {
            System.out.println(entry);
        }
    }

    static void serveSecondcustomer(Taxes taxes) {
        //Generate Data of Taxes and input1
        String input2[] = DummyData.getInput2();
        Driver customer2 = new Driver();
        //Computing output recipt
        String outputRecipt2[] = customer2.drive(input2, taxes);
        //Printing this output recipt;
        for (String entry : outputRecipt2) {
            System.out.println(entry);
        }
    }
}


class Driver {
    private String medicinesType[] = {"headache pills", "syrup"};

    public String[] drive(String[] inputItems, Taxes taxes) {
        List<Item> itemList = new ArrayList<>();

        for (String itemS : inputItems) {
            itemList.add(parseItem(itemS, medicinesType));
        }

        String output[] = new String[itemList.size() + 2];

        for (int i = 0; i < itemList.size(); i++) {
            output[i] = itemList.get(i).getReciptEntry(taxes);
        }

        output[itemList.size()] = "Sales Taxes: " + Double.toString(computeServiceTax(itemList));
        output[itemList.size() + 1] = "Total: " + Double.toString(computeTotal(itemList));
        return output;
    }

    private double computeTotal(List<Item> items) {
        double sum = 0d;

        for (Item item : items) {
            sum += item.getTaxIncludedPricePerItem();
        }
        return sum;
    }

    private double computeServiceTax(List<Item> items) {
        double tax = 0d;

        for (Item item : items) {
            tax += (item.getTaxIncludedPricePerItem() - item.getPricePerItem());
        }

        return tax;
    }

    private Item parseItem(String itemS, String[] medicinesType) {
        StringTokenizer st = new StringTokenizer(itemS, " ");
        String number = st.nextToken();
        StringBuilder nameBuilder = new StringBuilder();
        String price = "";

        while (st.hasMoreElements()) {
            String token = st.nextToken();
            if (st.hasMoreElements()) {
                nameBuilder.append(token);
                nameBuilder.append(" ");
            } else {
                price = token;
            }
        }
        String name = nameBuilder.toString();
        //trim extraSpaces
        number.trim();
        name.trim();
        price.trim();
        boolean isMedicine = false;


        for (String med : medicinesType) {
            if (name.toUpperCase().contains(med.toUpperCase())) {
                isMedicine = true;
                break;
            }
        }
        return new Item(name, Double.parseDouble(price), Integer.parseInt(number), (isMedicine) ? Type.MEDICAL : Type.NON_MEDICAL);
    }
}


class Item {
    private String name;
    private double pricePerItem;
    private int number;
    private Type itemType;
    private double taxIncludedPricePerItem;

    public Item(String name, double price, int number, Type type) {
        this.name = name;
        this.pricePerItem = price;
        this.number = number;
        this.itemType = type;
    }

    public String getReciptEntry(Taxes taxes) {
        computerTaxIncludedPrice(taxes);
        StringBuilder sb = new StringBuilder();
        sb.append(number).append("\t");
        sb.append(name).append("\t");
        sb.append(taxIncludedPricePerItem * number);
        return sb.toString();
    }

    private void computerTaxIncludedPrice(Taxes taxes) {
        double taxRate = taxes.getTaxRate(itemType);
        taxIncludedPricePerItem = pricePerItem + ((pricePerItem * taxRate) / 100d);//tax rate is written in form of percentage
    }

    public String getName() {
        return this.name;
    }

    public double getPricePerItem() {
        return this.pricePerItem;
    }

    public int getNumber() {
        return this.number;
    }

    public Type getItemType() {
        return this.itemType;
    }

    public double getTaxIncludedPricePerItem() {
        return this.taxIncludedPricePerItem;
    }
}

class Taxes {
    private Map<Type, Double> taxRate;

    public Taxes() {
        taxRate = new HashMap<>();
    }

    public boolean insertTaxRate(Type type, double rate) {
        try {
            taxRate.put(type, rate);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public double getTaxRate(Type itemType) {
        if (!taxRate.containsKey(itemType)) {
            throw new InvalidItemTypeException("Invalid Item type");
        } else {
            return taxRate.get(itemType);
        }
    }
}


enum Type {
    MEDICAL, NON_MEDICAL;
}


class InvalidItemTypeException extends RuntimeException {

    public InvalidItemTypeException(String message) {
        super(message);
    }
}


class DummyData {

    public static Taxes getTaxesDummyData() {
        Taxes taxes = new Taxes();
        taxes.insertTaxRate(Type.MEDICAL, 0d);
        taxes.insertTaxRate(Type.NON_MEDICAL, 20d);
        return taxes;
    }

    public static String[] getInput1() {
        String input[] = new String[3];
        input[0] = "1 bottle of wine: 20.00";
        input[1] = "2 box of headache pills: 4.00";
        input[2] = "1 box of pens: 10.00";
        return input;
    }

    public static String[] getInput2() {
        String input[] = new String[2];
        input[0] = "1 book: 30";
        input[1] = "1 chocolate: 1";
        return input;
    }
}