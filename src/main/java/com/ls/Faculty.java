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

    public static boolean validateIndex(Course[] arr, int index) {
        if (index < 0) return false;
        if (index > arr.length) return false;
        return arr[index] != null;
    }

    public Course getCourseTaught(int index) {
        if(!validateIndex(coursesTaught,index)) return null;
        for (int i = 0; i < coursesTaught.length; i++) {
            if(i == index) {
                return coursesTaught[i];
            }
        }
        return null;
    }

    public boolean doesFacultyTeachSelectedCourse(Course course) {
        if(coursesTaught == null) return false;
        for(int i = 0; i < coursesTaught.length; i++) {
            Course currentCourse = coursesTaught[i];
            if(currentCourse == null) break;
            System.out.println("what is courses taught" + coursesTaught[i]);
            if(course.equals(currentCourse)) return true;
        }
        return false;
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
