package com.ls;

public class Student extends Person {
    private static int numStudents;
    private int studentID;
    private Course[] coursesTaken;
    private int numCoursesTaken;
    private boolean isGraduate;
    private String major;

    public Student() {
        this.coursesTaken = new Course[0];
        this.numCoursesTaken = 0;
        this.isGraduate = false;
    }

    public Student(boolean isGraduate) {
        this.isGraduate = isGraduate;
    }

    public Student(String major, boolean isGraduate) {
        this.major = major;
        this.isGraduate = isGraduate;
    }

    public boolean isGraduate() {
        return isGraduate;
    }

    public static int getNumStudents() {
        return numStudents;
    }

    public int getStudentID() {
        return studentID;
    }

    public String getMajor() {
        return this.major;
    }

    public void setIsGraduate(boolean isGraduate) {
        this.isGraduate = isGraduate;
    }

    public void setMajor(String major) {
        this.major = major;
    }

}
