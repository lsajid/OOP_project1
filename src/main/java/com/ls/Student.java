package com.ls;

import java.util.Objects;

public class Student extends Person {
    private static int numStudents = 0;
    private int studentID;
    private Course[] coursesTaken;
    private int numCoursesTaken;
    private boolean isGraduate;
    private String major = "undeclared";

    public Student() {
        super();
        this.coursesTaken = new Course[50];
        this.numCoursesTaken = 0;
        this.isGraduate = false;
        this.studentID = ++numStudents;
    }

    public Student(boolean isGraduate) {
        super();
        this.coursesTaken = new Course[50];
        this.isGraduate = isGraduate;
        this.studentID = ++numStudents;
    }

    public Student(String major, boolean isGraduate) {
        super();
        this.coursesTaken = new Course[50];
        this.major = major;
        this.isGraduate = isGraduate;
        this.studentID = ++numStudents;
    }

    public Student(String name, int birthYear, String major, boolean isGraduate) {
        super(name, birthYear);
        this.coursesTaken = new Course[50];
        this.major = major;
        this.isGraduate = isGraduate;
        this.studentID = ++numStudents;
    }

    public boolean isGraduate() {
        return isGraduate;
    }

    public int getNumCoursesTaken() {
        return numCoursesTaken;
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

    public static boolean validateIndex(Course[] arr, int index) {
        if (index < 0) return false;
        if (index > arr.length) return false;
        return true;
    }

    public void addCourseTaken(Course course) {
        if(numCoursesTaken < 50) {
            coursesTaken[numCoursesTaken] = course;
            numCoursesTaken++;
        }
    }

    public void addCoursesTaken(Course[] courses) {
        for (int i=0; i < courses.length; i++) {
            addCourseTaken(courses[i]);
        }
    }

    public Course getCourseTaken(int index) {
        if(!validateIndex(coursesTaken,index)) return null;
        for (int i = 0; i < coursesTaken.length; i++) {
            if(i == index) {
                return coursesTaken[i];
            }
        }
        return null;
    }

    public String getCourseTakenAsString(int index) {
        Course course = getCourseTaken(index);
        return course.toString();
    }

    public String getAllCoursesTakenAsString() {
        String s = "";
        if (coursesTaken == null) return "";
        for(int i = 0; i < coursesTaken.length; i++) {
            if (coursesTaken[i] == null) break;
            s += getCourseTakenAsString(i);
        }
        return s;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        if (this == object) return true;

        if (super.equals(object)) {
            Student otherStudent = (Student) object;
            if (
                    studentID == otherStudent.studentID &&
                    numCoursesTaken == otherStudent.numCoursesTaken &&
                            isGraduate == otherStudent.isGraduate &&
                            Objects.deepEquals(coursesTaken, otherStudent.coursesTaken) &&
                            Objects.equals(major, otherStudent.major)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String toString() {
        String graduate = isGraduate ? "Graduate" : "Undergraduate";
        String studentIDFormat = String.format(" Student: studentID: %04d", studentID);
        return super.toString() + studentIDFormat +" | Major           " + major + " |       " + graduate + " | Number of Courses Taken:   " + numCoursesTaken + " | Courses Taken: " + getAllCoursesTakenAsString();
    }

    @Override
    public int compareTo(Person p) {
        if(p == null) {
            return 0;
        }
        if (p instanceof Student) {
            Student otherStudent = (Student) p;
            if (this.getTotalNumberOfCredits() > otherStudent.getTotalNumberOfCredits()) {
                return 1;
            }
            if (this.getTotalNumberOfCredits() < otherStudent.getTotalNumberOfCredits()) {
                return -1;
            }
        }
        return 0;
    }

    public int getTotalNumberOfCredits() {
        int totalNumberOfCredits = 0;
        for(int i=0; i < coursesTaken.length; i++) {
            if(coursesTaken[i] == null) break;
            totalNumberOfCredits += coursesTaken[i].getNumCredits();
        }
        return totalNumberOfCredits;
    }
}
