package Hinheritance;

public class Employee {
    private String name;
    private String SSN;
    private double salary;

    private Address address;

    public void print() {
        System.out.println(this.name);
        System.out.println(this.SSN);
        System.out.println(this.salary);
        System.out.println(this.address.getStreet() + " " + this.getAddress().getZipcode());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSSN() {
        return SSN;
    }

    public void setSSN(String SSN) {
        this.SSN = SSN;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
