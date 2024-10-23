package com.ls;

public class Employee extends Person {
    private String deptName;
    private static int numEmployees;
    private int employeeID;

    public Employee() {
        this.deptName = "";
        this.employeeID = 0;
    }

    public Employee(String deptName) {}

    public Employee(String name, int birthYear, String deptName) {}

    public String getDeptName() {
        return deptName;
    }

    public static int getNumEmployees () {
        return numEmployees;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setDeptName(String deptName) {}

    @Override
    public boolean equals(Object obj) {
        return false;
    }

    @Override
    public String toString() {
        return "";
    }

    @Override
    public int compareTo(Person p) {
        return -1;
    }
}
