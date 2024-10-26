package com.ls;

import java.util.*;
import java.io.File;

public class Driver_SchoolDB {
    public static void main(String[] args) {
        //init hashMap to store lineContent and index
        HashMap<Integer, String> contentMap = new HashMap<>();

        //init arrays to store objects
        Course[] courses = new Course[15];
        Faculty[] faculties = new Faculty[15];
        GeneralStaff[] generalStaffs = new GeneralStaff[15];
        Employee[] employees = new Employee[15];
        Person[] people = new Person[15];
        Student[] students = new Student[15];

        //read file and store content in hashMap
        printAndStoreFileContent(true, contentMap);

        //print first part of stars
        generateMenuOrFooter("menu");

        //generate classes as specified in file
        generateClassesFromMap(contentMap, courses, generalStaffs, faculties, students);

        //functions to generate text from stored arrays
        generateCourseText(courses);
        generatePersonText(people);
        generateEmployeeText(employees);
        generateGeneralStaffText(generalStaffs);
        generateFacultyText(faculties);
        generateStudentText(students);

        generateMenuOrFooter("footer");
    }

    public static void printAndStoreFileContent(boolean isLocal, HashMap<Integer, String> contentMap) {
        //get path str for local testing / Zybooks test
        String path = isLocal ? "/Users/laibasajid/Desktop/project1/src/main/java/com/ls/SchoolDB_Initial.txt" : "SchoolDB_Initial.txt";
        Scanner fileStream = null;
        try {
            //create new instance of file obj for scanner
            File file = new File(path);
            //create instance of Scanner for file
            fileStream = new Scanner(file);
            int lineNumber = 0;

            while(fileStream.hasNextLine()) {
                String lineInFile = fileStream.nextLine();
                System.out.println(lineInFile);
                contentMap.put(lineNumber++, lineInFile);
            }
            System.out.println();
        } catch (Exception err) {
            System.out.println("File Not Found ");
        } finally {
            if(fileStream != null) {
                fileStream.close();
            }
        }
    }

    public static void generateMenuOrFooter(String option) {
        if(option.equals("menu")){
            System.out.println("**************************************************************");
            System.out.println("SCHOOL DATABASE INFO:");
            System.out.println();
            System.out.println("************************************************");
        } else if(option.equals("footer")) {
            System.out.println("************************************************\n" +
                    "**************************************************************\n");
        }
    }

    public static void generateClassesFromMap(HashMap<Integer, String> contentMap, Course[] courses, GeneralStaff[] generalStaffs, Faculty[] faculties, Student[] students) {
        //iterate through contentMap to get class type
        for (String line : contentMap.values()) {
            String classType = getClassType(line);
            String[] constructorParams = getConstructorParams(line);
            if (classType != null && !classType.isEmpty()) {
                createAndStoreClass(classType, constructorParams, courses, generalStaffs, faculties, students);
            }
        }
    }

    public static String getClassType (String str) {
        if (str.isEmpty()) {return null;}
        return str.substring(0, str.indexOf(":"));
    }

    public static String[] getConstructorParams(String str) {
        if(str.isEmpty()) return null;
        return str.substring(str.indexOf(":")+1).trim().split(",");
    }

    public static void createAndStoreClass(String classType, String[] constructorParams, Course[] courses, GeneralStaff[] generalStaffs, Faculty[] faculties, Student[] students) {
        switch (classType) {
            case "Course":
                boolean isGraduateCourse = Boolean.parseBoolean(constructorParams[0]);
                int courseNum = Integer.parseInt(constructorParams[1].trim());
                String courseDept = constructorParams[2].trim();
                int numCredits = Integer.parseInt(constructorParams[3].trim());

                Course courseToAdd = new Course(isGraduateCourse, courseNum, courseDept, numCredits);
                appendCourseToArray(courses, courseToAdd);
                break;
            case "GeneralStaff":
                GeneralStaff generalStaffToAdd = null;
                if(constructorParams.length == 1) {
                    if(Objects.equals(constructorParams[0], "")){
                        generalStaffToAdd = new GeneralStaff();
                    }else {
                        generalStaffToAdd = new GeneralStaff(constructorParams[0]);
                    }
                }

                if(constructorParams.length == 2) {
                    String deptName = constructorParams[0];
                    String duty = constructorParams[1];
                    generalStaffToAdd = new GeneralStaff(deptName, duty);
                }

                if(constructorParams.length == 4) {
                    String name = constructorParams[0];
                    int birthYear = Integer.parseInt(constructorParams[1].trim());
                    String deptName = constructorParams[2];
                    String duty = constructorParams[3];
                    generalStaffToAdd = new GeneralStaff(name, birthYear, deptName, duty);
                }

                appendGeneralStaffToArray(generalStaffs, generalStaffToAdd);
                break;
            case "Faculty":
                Faculty facultyToAdd = null;
                if(constructorParams.length == 1) {
                    if(Objects.equals(constructorParams[0], "")){
                        facultyToAdd = new Faculty();
                    }else {
                        boolean isTenured = Boolean.parseBoolean(constructorParams[0]);
                        facultyToAdd = new Faculty(isTenured);
                    }
                }
                if(constructorParams.length == 2) {
                    String deptName = constructorParams[0];
                    boolean isTenured = Boolean.parseBoolean(constructorParams[1]);

                    facultyToAdd = new Faculty(deptName, isTenured);
                }
                if(constructorParams.length == 4) {
                    String name = constructorParams[0];
                    int birthYear = Integer.parseInt(constructorParams[1].trim());
                    String deptName = constructorParams[2];
                    boolean isTenured = Boolean.parseBoolean(constructorParams[3]);

                    facultyToAdd = new Faculty(name, birthYear, deptName, isTenured);
                }
                appendFacultyToArray(faculties, facultyToAdd);
                break;
            case "Student":
                Student studentToAdd = null;
                if(constructorParams.length == 1) {
                    if(Objects.equals(constructorParams[0], "")){
                        studentToAdd = new Student();
                    }else {
                        boolean isGraduate = Boolean.parseBoolean(constructorParams[0]);
                        studentToAdd = new Student(isGraduate);
                    }
                }
                if(constructorParams.length == 2) {
                    String major = constructorParams[0];
                    boolean isGraduate = Boolean.parseBoolean(constructorParams[1]);
                    studentToAdd = new Student(major, isGraduate);
                }
                if(constructorParams.length == 4) {
                    String name = constructorParams[0];
                    int birthYear = Integer.parseInt(constructorParams[1].trim());
                    String major = constructorParams[2];
                    boolean isGraduate = Boolean.parseBoolean(constructorParams[3]);

                    studentToAdd = new Student(name, birthYear, major, isGraduate);
                }
                appendStudentToArray(students ,studentToAdd);
                break;
            case "Employee":
                break;
            case "Person":
                break;
        }
    }

    public static void appendCourseToArray(Course[] courses, Course course) {
        //TODO: Add check length of array
        for(int i = 0; i < courses.length; i++) {
            if(courses[i] == null) {
                courses[i] = course;
                break;
            }
        }
    }

    public static void generateCourseText(Course[] courses) {
        System.out.println("COURSES:");
        for (Course course : courses) {
            if(course == null) break;
            System.out.println(course.toString());
        }
        System.out.println("************************************************\n" +
                "************************************************");
    }

    public static void generatePersonText(Person[] people) {
        System.out.println("PERSONS:");
        for (Person person : people) {
            if(person == null) break;
            System.out.println(person.toString());
        }
        System.out.println("************************************************\n" +
                "************************************************");
    }

    public static void generateEmployeeText(Employee[] employees) {
        System.out.println("EMPLOYEES:");
        for (Employee employee : employees) {
            if(employee == null) break;
            System.out.println(employee.toString());
        }
        System.out.println("************************************************\n" +
                "************************************************");
    }

    public static void appendGeneralStaffToArray(GeneralStaff[] generalStaffs, GeneralStaff generalStaff) {
        //TODO: Add check length of array
        for(int i = 0; i < generalStaffs.length; i++) {
            if(generalStaffs[i] == null) {
                generalStaffs[i] = generalStaff;
                break;
            }
        }
    }

    public static void generateGeneralStaffText(GeneralStaff[] generalStaffs) {
        //    Person: Name:                                | Birth Year:    0 Employee: Department:                      | Employee Number:   5 GeneralStaff: Duty:
        System.out.println("GENERAL STAFF:");
        for (GeneralStaff generalStaff : generalStaffs) {
            if(generalStaff == null) break;
            System.out.println(String.format(
                    "Person: Name: %30s | Birth Year: %4d Employee: Department: %30s | Employee Number: %4d GeneralStaff: Duty: %20s",
                    generalStaff.getName(),
                    generalStaff.getBirthYear(),
                    generalStaff.getDeptName(),
                    generalStaff.getEmployeeID(),
                    generalStaff.getDuty()
            ));
        }
        System.out.println("************************************************\n" +
                "************************************************");
    }

    public static void appendFacultyToArray(Faculty[] faculties, Faculty faculty) {
        //TODO: Add check length of array
        for(int i = 0; i < faculties.length; i++) {
            if(faculties[i] == null) {
                faculties[i] = faculty;
                break;
            }
        }
    }

    public static void generateFacultyText(Faculty[] faculties){
        System.out.println("FACULTY:");
        for(Faculty faculty : faculties) {
            if(faculty == null) break;
            System.out.println(String.format(
                    "Person: Name: %30s | Birth Year: %4d Employee: Department: %20s | Employee Number: %4d Faculty: %12s | Number of Courses Taught: %4d | Courses Taught: %20s",
                    faculty.getName(),
                    faculty.getBirthYear(),
                    faculty.getDeptName(),
                    faculty.getEmployeeID(),
                    faculty.isTenured() ? "Is Tenured" : "Not Tenured",
                    faculty.getNumCoursesTaught(),
                    faculty.getAllCoursesTaughtAsString()
            ));
        }
        System.out.println("************************************************\n" +
                "************************************************");
    }

    public static void appendStudentToArray(Student[] students, Student student) {
        //TODO: Add check length of array
        for(int i = 0; i < students.length; i++) {
            if(students[i] == null) {
                students[i] = student;
                break;
            }
        }
    }

    public static void generateStudentText(Student[] students) {
        System.out.println("STUDENTS:");
        for(Student student : students) {
            if(student == null) break;
            System.out.println(String.format(
                    "Person: Name: %30s | Birth Year: %4d Student: studentID: %4s | Major %15s | %14s | Number of Courses Taken: %4d | Courses Taken: %20s",
                    student.getName(),
                    student.getBirthYear(),
                    student.getStudentID(),
                    student.getMajor(),
                    student.isGraduate() ? "Graduate" : "Undergraduate",
                    student.getNumCoursesTaken(),
                    student.getAllCoursesTakenAsString()
            ));
        }
    }
}