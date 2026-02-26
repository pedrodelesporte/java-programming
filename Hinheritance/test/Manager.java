package Hinheritance.test;

import Hinheritance.Address;
import Hinheritance.Employee;

public class Manager extends Employee {
    private String department;

    @Override
    public void print() {
        super.print();
        System.out.println(this.department);
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }


}
