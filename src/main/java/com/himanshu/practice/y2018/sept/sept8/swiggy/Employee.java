package com.himanshu.practice.y2018.sept.sept8.swiggy;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by himanshubhardwaj on 08/09/18.
 */
@ToString
@Data
@Builder
public class Employee {
    public String name;
    public int id;
    public double salary;
    public int raiting;
    public int bonus;
    public static Set<Employee> employees = new HashSet<>();
    public String desingnation;
    //some of them, like designation can be make private


    public Employee(String name, int id, double salary, int raiting, int bonus, String desingnation) {
        this.name = name;
        this.id = id;
        this.salary = salary;
        this.raiting = raiting;
        this.bonus = bonus;
        this.desingnation = desingnation;
        if (employees.contains(this)) {
            throw new RuntimeException("Employee with ID " + id + " already exist");
        } else if (raiting < 1 || raiting > 5) {
            throw new RuntimeException(id + "Raiting has to be between 1 and 5");
        } else {
            employees.add(this);
        }
    }

    public void printHierarchy() {

    }

    public void distributeBonus(double bonus) {

    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Employee)) return false;
        final Employee other = (Employee) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getId() != other.getId()) return false;
        return true;
    }

    public int hashCode() {
        return this.getId();
    }

    protected boolean canEqual(Object other) {
        return other instanceof Employee;
    }
}
