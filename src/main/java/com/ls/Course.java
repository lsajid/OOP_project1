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

    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }

        if (this == object) {
            return true;
        }

        if (object instanceof Course) {
            Course otherCourse = (Course) object;
            return (this.isGraduateCourse == otherCourse.isGraduateCourse)
                    && (this.courseNum == otherCourse.courseNum)
                    && (this.courseDept.equalsIgnoreCase(otherCourse.courseDept)
                    && (this.numCredits == otherCourse.numCredits));
        }

        return false;
    }

    @Override
    public String toString() {
        String s =
                String.format(
                        "Course: %3s-%3d | Number of Credits: %02d |",
                        this.courseDept,
                        this.courseNum,
                        this.numCredits
                );

        if (isGraduateCourse) {
            s += " Graduate";
        } else {
            s += " Undergraduate";
        }
        return s;
    }

    public int compareTo(Course otherCourse) {
        if (otherCourse == null) {return 0;}
        if (this.courseNum < otherCourse.courseNum) {
            return -1;
        } else if (this.courseNum > otherCourse.courseNum) {
            return 1;
        }
        return 0;
    }
}
