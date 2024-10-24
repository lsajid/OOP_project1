package com.ls;

public class GeneralStaff extends Employee {
    private String duty;

    public GeneralStaff() {
        super();
        this.duty = "";
    }

    public GeneralStaff(String duty) {
        super();
        this.duty = duty;
    }

    public GeneralStaff(String deptName, String duty) {
        super(deptName);
        setDeptName(deptName);
        this.duty = duty;
    }

    public GeneralStaff(String name, int birthYear, String deptName, String duty) {
        super(name, birthYear, deptName);
        setName(name);
        setBirthYear(birthYear);
        setDeptName(deptName);
        this.duty = duty;
    }

    public String getDuty() {
        return this.duty;
    }

    @Override
    public boolean equals (Object object) {
        if (object == null) {
            return false;
        }

        if (super.equals(object)) {
            GeneralStaff otherGeneralStaff = (GeneralStaff) object;
            if ((this.duty == otherGeneralStaff.duty)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String s = String.format(" GeneralStaff: Duty: %10s", duty);
        return super.toString() + s;
    }
}
