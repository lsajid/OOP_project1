package com.ls;

public class Faculty extends Employee {
    private Course[] coursesTaught;
    private int numCoursesTaught;
    private boolean isTenured;

    public Faculty() {
        this.coursesTaught = new Course[0];
        this.numCoursesTaught = 0;
        this.isTenured = false;
    }

    public Faculty(boolean isTenured) {
        this.coursesTaught = new Course[0];
        this.numCoursesTaught = 0;
        this.isTenured = isTenured;
    }

    public Faculty(String deptName, boolean isTenured) {
        this.coursesTaught = new Course[0];
        this.isTenured = isTenured;
    }

    public boolean isTenured() {
        return isTenured;
    }

    public int getNumCoursesTaught() {
        return numCoursesTaught;
    }

}
