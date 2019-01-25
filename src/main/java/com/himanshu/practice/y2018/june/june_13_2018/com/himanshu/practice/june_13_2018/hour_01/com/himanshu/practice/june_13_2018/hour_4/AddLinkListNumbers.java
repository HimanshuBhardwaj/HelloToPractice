package com.himanshu.practice.y2018.june.june_13_2018.com.himanshu.practice.june_13_2018.hour_01.com.himanshu.practice.june_13_2018.hour_4;

/**
 * Created by Himanshu Bhardwaj on 13/06/18.
 */
//Not working
    //improve it
public class AddLinkListNumbers {

    public static void main(String[] args) {
        Number number = new Number();
        number = number.addDigit(number, 4);
        number = number.addDigit(number, 9);
        number = number.addDigit(number, 4);

        System.out.print("Printing number ");
        number.printDigit();
        System.out.println(number.size(number));


//
//        number = number.addDigit(number, 5);


    }
}


class Number {
    Node start = null;
    Node end = null;

    //add digit to a number
    Number addDigit(Number number, int digit) {
        Node node = new Node(digit);

        if (number.end == null) {
            number.start = node;
            number.end = node;
            return number;
        }

        number.start = node.addDigits(number.end, node); // instead of a value, end could be end of some other number also
        number.end = node.getEnd(number.start);
        return number;
    }


    int size(Number number) {
        int size = 0;
        Node temp = number.start;

        while (temp != null) {
            size++;
            temp = temp.next;
        }
        return size;
    }


    Number addDigits(Number number1, Number number2) {


        return null;
    }

    void printDigit() {
        if (start == null) {
            System.out.print("empty number");
        }
        Node.print(start);
    }
}


class Node {
    int number;
    Node next;
    Node Previous;

    public Node(int digit) {
        this.number = digit;
        next = null;
        Previous = null;
    }

    static void print(Node start) {
        Node temp = start;
        while (temp != null) {
            System.out.print(temp.number);
            temp = temp.next;
        }
        System.out.println();
    }


    Node addDigits(Node end1, Node end2) {
        Node newNumber = null;

        int remainder = 0;
        int carry = 0;

        while (end1 != null && end2 != null) {
            remainder = (end1.number + end2.number + carry) % 10;
            carry = (end1.number + end2.number + carry) / 10;
            newNumber = Node.addDigit(newNumber, remainder);
            end1 = end1.Previous;
            end2 = end2.Previous;
        }

        if (end1 == null) {
            while (end2 != null) {
                remainder = (end2.number + carry) % 10;
                carry = (end2.number + carry) / 10;
                newNumber = Node.addDigit(newNumber, remainder);
                end2 = end2.Previous;
            }
        } else if (end2 == null) {
            while (end1 != null) {
                remainder = (carry + end1.number) % 10;
                carry = (carry + end1.number) / 10;
                newNumber = Node.addDigit(newNumber, remainder);
                end1 = end1.Previous;
            }
        }

        while (carry > 0) {
            newNumber = Node.addDigit(newNumber, carry % 10);
            carry = carry / 10;
        }

        return newNumber;
    }


    static Node addDigit(Node number, int carry) {
        int reminder = 0;
        Node node = number;

        while (carry > 0) {
            reminder = (carry) % 10;
            carry = (carry) / 10;


            Node node1 = new Node(reminder);
            node1.next = number;
            number = node1;
        }

        return number;
    }

    Node getEnd(Node start) {
        if (start == null || start.next == null) {
            return start;
        }
        return getEnd(start.next);

    }

}
