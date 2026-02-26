package Hinheritance.test;

import Hinheritance.Address;
import Hinheritance.Employee;

public class InheritanceTest01 {
    public static void main(String[] args) {
        Address address = new Address();
        address.setStreet("12th street");
        address.setZipcode("12345");

        Employee employee = new Employee();
        employee.setName("John");
        employee.setSSN("15");
        employee.setAddress(address);
        employee.setSalary(1231313.0);

        employee.print();

        System.out.println("--------------------------");

        Manager manager = new Manager();

        manager.setName("Asta");
        manager.setSSN("20");
        manager.setAddress(address);
        manager.setSalary(1930242);
        manager.setDepartment("IT");

        manager.print();
    }
}
