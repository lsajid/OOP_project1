package com.ls;

public class Employee extends Person {
    private String deptName;
    private static int numEmployees=0;
    private int employeeID;

    public Employee() {
        this.deptName = "";
        this.employeeID = ++numEmployees;
    }

    public Employee(String deptName) {
        this.deptName = deptName;
        this.employeeID = ++numEmployees;
    }

    public Employee(String name, int birthYear, String deptName) {
        super(name, birthYear);
        this.setName(name);
        this.setBirthYear(birthYear);
        this.deptName = deptName;
        employeeID = ++numEmployees;
    }

    public String getDeptName() {
        return deptName;
    }

    public static int getNumEmployees () {
        return numEmployees;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (super.equals(object)) {
            Employee otherEmployee = (Employee) object;
            if (this.deptName.equalsIgnoreCase(otherEmployee.deptName) && this.employeeID == otherEmployee.employeeID ) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return super.toString() +
                " Employee: Department:           " + deptName + " | Employee Number:   " + employeeID;
    }



    @Override
    public int compareTo(Person p) {
        if (p == null) {
            return 0;
        }
        int num = super.compareTo(p);
        if (p instanceof Employee) {
            Employee otherEmployee = (Employee) p;
            if (num == 1 && this.employeeID > otherEmployee.employeeID) {
                return 1; //older and has higher employeeID
            }
            if (num == -1 && this.employeeID < otherEmployee.employeeID) {
                return -1;//younger and has a lower employeeID;
            }
            if (num == 0) {
                if(this.employeeID > otherEmployee.employeeID) {
                    return 1;//same age but has higher employeeID
                }else if (this.employeeID < otherEmployee.employeeID) {
                    return -1;// same age lower employeeID
                }
            }
            return num;
        }
        return 0;
    }
}
