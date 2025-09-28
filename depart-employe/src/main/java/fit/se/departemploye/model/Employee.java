package fit.se.departemploye.model;

import java.io.Serializable;

public class Employee implements Serializable {
    private int id;
    private String name;
    private double salary;
    private int departId;

    public Employee() {
    }

    public Employee(int id, String name, double salary, int departId) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.departId = departId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getDepartId() {
        return departId;
    }

    public void setDepartId(int departId) {
        this.departId = departId;
    }
}
