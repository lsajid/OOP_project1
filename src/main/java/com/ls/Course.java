package com.ls;

public class Course {
    private boolean isGraduateCourse;
    private int courseNum;
    private String courseDept;
    private int numCredits;

    Course(boolean isGraduateCourse, int courseNum, String courseDept, int numCredits) {
        this.isGraduateCourse = isGraduateCourse;
        this.courseNum = courseNum;
        this.courseDept = courseDept;
        this.numCredits = numCredits;
    }

    public boolean isGraduateCourse() {
     return isGraduateCourse;
    }

    public int getCourseNum() {
        return courseNum;
    }

    public String getCourseDept() {
        return courseDept;
    }

    public int getNumCredits () {
        return numCredits;
    }

    public String getCourseName () {
        String name = "";

        if (this.isGraduateCourse) {
            name += "G";
        } else {
            name += "U";
        }

        name += this.courseDept + this.courseNum;
        return name;
    }

    @Override
    public String toString() {
        String s =
                String.format(
                        "Course: %3s-%3d | Number of Credits: %02d | Graduate/Undergraduate",
                        this.courseDept,
                        this.courseNum,
                        this.numCredits,
                        this.isGraduateCourse
                );
        return s;
    }

    public int compareTo(Course c) {
        return -1;
    }
}
