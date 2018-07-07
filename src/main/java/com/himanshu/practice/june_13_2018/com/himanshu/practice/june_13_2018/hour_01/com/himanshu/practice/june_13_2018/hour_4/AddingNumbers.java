package com.himanshu.practice.june_13_2018.com.himanshu.practice.june_13_2018.hour_01.com.himanshu.practice.june_13_2018.hour_4;

import lombok.Data;

/**
 * Created by Himanshu Bhardwaj on 13/06/18.
 */
public class AddingNumbers {
    public static void main(String[] args) {
        Digit digit1 = new Digit(5);
        digit1.setNextDigit(new Digit(4));

        digit1.getNextDigit().setNextDigit(new Digit(1));
        digit1.getNextDigit().getNextDigit().setNextDigit(new Digit(3));
        digit1.getNextDigit().getNextDigit().getNextDigit().setNextDigit(new Digit(2));


        digit1.print(digit1);
        System.out.println();


        Digit digit2 = new Digit(5);
        digit2.setNextDigit(new Digit(5));

        digit2.getNextDigit().setNextDigit(new Digit(5));
        digit2.getNextDigit().getNextDigit().setNextDigit(new Digit(6));
        digit2.getNextDigit().getNextDigit().getNextDigit().setNextDigit(new Digit(9));


        digit2.print(digit2);
        System.out.println();
        digit1.print(Digit.addNumbers(digit1, digit2));


    }

}


@Data
class Digit {
    private int number;
    private Digit nextDigit;

    public Digit(int number) {
        this.number = number;
        this.nextDigit = null;
    }

    static Digit addNumbers(Digit digit1, Digit digit2) {
        if (digit1 == null) {
            return digit2;
        } else if (digit2 == null) {
            return digit1;
        }

        int carry = 0;
        int remainder = 0;
        Digit newLeft = null;
        Digit newRight = null;

        while (digit1 != null || digit2 != null) {
            remainder = (carry + ((digit1 != null) ? digit1.getNumber() : 0) + ((digit2 != null) ? digit2.getNumber() : 0)) % 10;
            carry = (carry + ((digit1 != null) ? digit1.getNumber() : 0) + ((digit2 != null) ? digit2.getNumber() : 0)) / 10;
            Digit newDigit = new Digit(remainder);

            if (newLeft == null) {
                newLeft = newDigit;
                newRight = newDigit;
            } else {
                newRight.nextDigit = newDigit;
                newRight = newDigit;
            }
            digit1 = digit1.nextDigit;
            digit2 = digit2.nextDigit;
        }

        while (carry > 0) {
            remainder = carry % 10;
            carry = carry / 10;

            Digit newDigit = new Digit(remainder);

            if (newLeft == null) {
                newLeft = newDigit;
                newRight = newDigit;
            } else {
                newRight.nextDigit = newDigit;
                newRight = newDigit;
            }
        }

        return newLeft;
    }


    void print(Digit digit) {
        if (digit == null) return;
        print(digit.getNextDigit());
        System.out.print(digit.getNumber());

    }

}