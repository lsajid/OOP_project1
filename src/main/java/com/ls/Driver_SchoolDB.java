package com.ls;

import java.util.HashMap;
import java.util.Scanner;
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
        generateMenu();

        //generate classes as specified in file
        generateClassesFromMap(contentMap, courses);

        //generate course text
        generateCourseText(courses);
        //generate person text
        generatePersonText(people);
        //generate employee text
        generateEmployeeText(employees);
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

    public static void generateMenu() {
        System.out.println("**************************************************************");
        System.out.println("SCHOOL DATABASE INFO:");
        System.out.println();
        System.out.println("************************************************");
    }

    public static void generateClassesFromMap(HashMap<Integer, String> contentMap, Course[] courses) {
        //iterate through contentMap to get class type
        for (String line : contentMap.values()) {
            String classType = getClassType(line);
            String[] constructorParams = getConstructorParams(line);
            if (classType != null && !classType.isEmpty()) {
                createAndStoreClass(classType, constructorParams, courses);
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

    public static void createAndStoreClass(String classType, String[] constructorParams, Course[] courses) {
        switch (classType) {
            case "Course":
                boolean isGraduateCourse = Boolean.parseBoolean(constructorParams[0]);
                int courseNum = Integer.parseInt(constructorParams[1].trim());
                String courseDept = constructorParams[2].trim();
                int numCredits = Integer.parseInt(constructorParams[3].trim());

                Course courseToAdd = new Course(isGraduateCourse, courseNum, courseDept, numCredits);
                appendCourseToArray(courses, courseToAdd);
                break;
            case "General Staff":
                //check length of constructor params
                System.out.println("Create General Staff");
                break;
            case "Faculty":
                System.out.println("Faculty");
                break;
            case "Student":
                System.out.println("student");
            case "Employee":
                System.out.println("Employee");
            case "Person":
                System.out.println("Person");
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

}