package com.ls;

public class GeneralStaff extends Employee {
    private String duty;

    public GeneralStaff() {
        this.duty = "";
    }

    public GeneralStaff(String duty) {
        this.duty = duty;
    }

    public GeneralStaff(String deptName, String duty) {
    }

    public GeneralStaff(String name, int birthYear, String deptName, String duty) {}

    public String getDuty() {
        return this.duty;
    }

    @Override
    public boolean equals (Object object) {
        return false;
    }

    @Override
    public String toString() {
        return "";
    }
}
