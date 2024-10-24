package com.ls;

public class Faculty extends Employee {
    private Course[] coursesTaught;
    private int numCoursesTaught;
    private boolean isTenured;

    public Faculty() {
        // coursesTaught = [], numCoursesTaught = 0, isTenured = false
        super();
        this.coursesTaught = new Course[100];
        this.numCoursesTaught = 0;
        this.isTenured = false;
    }

    public Faculty(boolean isTenured) {
        super();
        this.coursesTaught = new Course[100];
        this.numCoursesTaught = 0;
        this.isTenured = isTenured;
    }

    public Faculty(String deptName, boolean isTenured) {
        super(deptName);
        this.setDeptName(deptName);
        this.coursesTaught = new Course[100];
        this.isTenured = isTenured;
    }

    public Faculty(String name, int birthYear, String deptName, boolean isTenured) {
        super(name, birthYear, deptName);
        this.setName(name);
        this.setBirthYear(birthYear);
        this.setDeptName(deptName);
        this.coursesTaught = new Course[100];
        this.isTenured = isTenured;
    }

    public boolean isTenured() {
        return isTenured;
    }

    public int getNumCoursesTaught() {
        return numCoursesTaught;
    }

    public void setIsTenured(boolean isTenured) {
        this.isTenured = isTenured;
    }

    public void addCourseTaught(Course course) {

        if(numCoursesTaught < 100) {
            coursesTaught[numCoursesTaught] = course;
            numCoursesTaught++;
        }
    }



    public void addCoursesTaught(Course[] courses) {
        for (int i=0; i < courses.length; i++) {
            addCourseTaught(courses[i]);
        }
    }

    public Course getCourseTaught(int index) {
        for (int i = 0; i < coursesTaught.length; i++) {
            if(i == index) {
                return coursesTaught[i];
            }
        }
        return null;
    }

    public String getCourseTaughtAsString(int index) {
        Course course = getCourseTaught(index);
        return course.toString();
    }

    public String getAllCoursesTaughtAsString() {
        String s = "";
        if (coursesTaught == null) return "";
        for(int i = 0; i < coursesTaught.length; i++) {
            if (coursesTaught[i] == null) break;
            s += getCourseTaughtAsString(i);
        }
        return s;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }

        if (super.equals(object)) {
            Faculty otherFaculty = (Faculty) object;
            if ((this.isTenured == otherFaculty.isTenured) && (this.numCoursesTaught == otherFaculty.numCoursesTaught) && (this.coursesTaught == otherFaculty.coursesTaught)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String employeeInfo = super.toString(true) + " Faculty:  ";

        String s = String.format(" | Number of Courses Taught: %3d | Courses Taught: %s", numCoursesTaught, getAllCoursesTaughtAsString());

        if (isTenured) {
            employeeInfo += "Is Tenured";
        } else {
            employeeInfo += "Not Tenured";
        }

        return employeeInfo + s;
    }

    @Override
    public int compareTo(Person p) {
        if(p == null) {
            return 0;
        }
        if (p instanceof Faculty) {
            Faculty otherFaculty = (Faculty) p;
            if (this.numCoursesTaught > otherFaculty.numCoursesTaught) {
                return 1;
            }
            if (this.numCoursesTaught < otherFaculty.numCoursesTaught) {
                return -1;
            }
        }
        return 0;
    }
}
