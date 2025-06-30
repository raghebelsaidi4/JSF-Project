package com.ragheb.bean;

public class Employee {
    private Integer id;
    private String fullName;
    private String address;
    private String email;
    private String phone;
    private double salary;

    public Employee() {
        this.id = 0;
        this.fullName = "";
        this.address = "";
        this.email = "";
        this.phone = "";
        this.salary = 0;
    }

    public Employee(String fullName, String address, String email, String phone, int salary) {
        this.id = 0;
        this.fullName = fullName;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
    }

    public Employee(Integer id, String fullName, String address, String email, String phone, int salary) {
        this.id = id;
        this.fullName = fullName;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
