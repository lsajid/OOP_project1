package com.ls;

import java.util.*;
import java.io.File;

public class Driver_SchoolDB {
    public static void main(String[] args) {
        //PART 1 ************************************************************************************************

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
        generateCourseText(courses, true);
        generatePersonText(people);
        generateEmployeeText(employees);
        generateGeneralStaffText(generalStaffs, true);
        generateFacultyText(faculties, true);
        generateStudentText(students, true);

        generateMenuOrFooter("footer");

        //PART 2 ************************************************************************************************

        //create new instance of userMenu
        UserMenu userMenu = new UserMenu();
        String[] userInputItems = new String[100];

        //listen to userStream for inputs
        userMenu.addUserInputsToArray(userInputItems);

        //generate all items for class
        generateAndPrintAllItemsFromUserInputArray(userInputItems, courses, generalStaffs, faculties, students);

        //show All courses
        generateCourseText(courses, false);
        generateGeneralStaffText(generalStaffs, false);
        generateFacultyText(faculties, false);
        generateStudentText(students, false);

        //close scanner instance
        userMenu.closeUserScanner();

        //Add 2 new Courses to a Faculty object
        System.out.println("(2 pts) Add 2 new Courses to a Faculty object");
        Faculty supermanFaculty = faculties[3];
        supermanFaculty.addCourseTaught(new Course(false, 123, "CMP", 4));
        supermanFaculty.addCourseTaught(new Course(true, 321, "MAT", 3));
        System.out.println("Faculty object: " + supermanFaculty.toString()+ "\n\n");

        //Add 2 new Courses to a Student object
        System.out.println("(2 pts) Add 2 new Courses to a Student object");
        Student wonderWomanStudent = students[3];
        wonderWomanStudent.addCourseTaken(new Course(false, 123, "CMP", 4));
        wonderWomanStudent.addCourseTaken(new Course(true, 321, "MAT", 3));
        System.out.println("Student object: " + wonderWomanStudent.toString() + "\n\n");

        //Add an array of 2 Courses to a Faculty object
        System.out.println("(1 pts) Add an array of 2 Courses to a Faculty object");
        appendFacultyToArray(faculties, new Faculty("Prof. Sofianos", 2024, "CMP", true));
        Faculty professor = getFacultyByName("Prof. Sofianos", faculties);
        Course[] coursesToAdd = new Course[] {new Course(true, 252, "CMP", 4), new Course(true, 168, "CMP", 4)};
        professor.addCoursesTaught(coursesToAdd);
        System.out.println("Faculty object: " + professor.toString()+ "\n\n");

        //Add an array of 2 courses to a Student Object
        System.out.println("(1 pts) Add an array of 2 Courses to a Student object");
        appendStudentToArray(students, new Student("Laiba", 1998, "Computer Science", false));
        Student laiba = getStudentByName("Laiba", students);
        laiba.addCoursesTaken(coursesToAdd);
        System.out.println("Student object: " + laiba.toString() + "\n\n");

        //Get the Course at index (valid and invalid indexes) from a Faculty object
        System.out.println("(1 pts) Get the Course at index (valid and invalid indexes) from a Faculty object");
        Course validCourseFromProfessor = professor.getCourseTaught(0);
        Course invalidCourseFromProfessor = professor.getCourseTaught(100000);
        System.out.println("Valid Course: " + validCourseFromProfessor + "\nInvalid Index: " + invalidCourseFromProfessor + "\n\n");

        //Get the Course at index (valid and invalid indexes) from a Student object
        System.out.println("(1 pts) Get the Course at index (valid and invalid indexes) from a Student object");
        Course validCourseFromLaiba = laiba.getCourseTaken(0);
        Course invalidCourseFromLaiba = laiba.getCourseTaken(10000);
        System.out.println("Valid Course: " + validCourseFromLaiba + "\nInvalid Index: " + invalidCourseFromLaiba + "\n\n");


    }

    public static Boolean doesFacultyTeachCourse() {

        return false;
    }

    public static Faculty getFacultyByName(String name, Faculty[] faculties) {
        for(Faculty faculty : faculties) {
            if(faculty.getName().equals(name)) {
                return faculty;
            }
        }
        return null;
    }

    public static Student getStudentByName(String name, Student[] students) {
        for(Student student : students) {
            if(student.getName().equals(name)) {
                return student;
            }
        }
        return null;
    }

    public static void generateAndPrintAllItemsFromUserInputArray(String[] userInputItems, Course[] courses, GeneralStaff[] generalStaffs, Faculty[] faculties, Student[] students) {
        System.out.println("_______\nAttempting to create stated items above please hold");
        //iterate through array
        parseClassesFromUserInputsAndCreateClass(userInputItems, courses, generalStaffs, faculties, students);
        //
        System.out.println("_______\nDone creating items above");
    }

    public static void parseClassesFromUserInputsAndCreateClass(String[] userInputItems, Course[] courses, GeneralStaff[] generalStaffs, Faculty[] faculties, Student[] students) {
        //iterate through contentMap to get class type
        for (String line : userInputItems) {
            if(line == null) break; //if current line is null do not generate class
            String classType = getClassType(line);
            String[] constructorParams = getConstructorParams(line);
            if (classType != null && !classType.isEmpty()) {
                createAndStoreClass(classType, constructorParams, courses, generalStaffs, faculties, students);
            }
        }
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

    public static Course createCourseClass(String[] constructorParams) {
        boolean isGraduateCourse = Boolean.parseBoolean(constructorParams[0]);
        int courseNum = Integer.parseInt(constructorParams[1].trim());
        String courseDept = constructorParams[2].trim();
        int numCredits = Integer.parseInt(constructorParams[3].trim());

        return new Course(isGraduateCourse, courseNum, courseDept, numCredits);
    }

    public static void createAndStoreClass(String classType, String[] constructorParams, Course[] courses, GeneralStaff[] generalStaffs, Faculty[] faculties, Student[] students) {
        switch (classType) {
            case "Course":
                Course courseToAdd = createCourseClass(constructorParams);
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
            default:
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

    public static void generateCourseText(Course[] courses, boolean isPart1) {
        System.out.println("COURSES:");
        for (Course course : courses) {
            if(course == null) break;
            System.out.println(course.toString());
        }
        if(isPart1) {
            System.out.println("************************************************\n" +
                    "************************************************");
        }
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

    public static void generateGeneralStaffText(GeneralStaff[] generalStaffs, boolean isPart1) {
        System.out.println("GENERAL STAFF:");
        for (GeneralStaff generalStaff : generalStaffs) {
            if(generalStaff == null) break;
            if(isPart1) {
                System.out.println(String.format(
                        "Person: Name: %30s | Birth Year: %4d Employee: Department: %20s | Employee Number: %3d GeneralStaff: Duty: %10s",
                        generalStaff.getName(),
                        generalStaff.getBirthYear(),
                        generalStaff.getDeptName(),
                        generalStaff.getEmployeeID(),
                        generalStaff.getDuty()
                ));
            } else {
                System.out.println(generalStaff.toString() + "\n\n");
            }
        }
        if(isPart1) {
            System.out.println("************************************************\n" +
                    "************************************************");
        }
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

    public static void generateFacultyText(Faculty[] faculties, boolean isPart1){
        System.out.println("FACULTY:");
        for(Faculty faculty : faculties) {
            if(faculty == null) break;
            if(isPart1) {
                System.out.println(String.format(
                        "Person: Name: %30s | Birth Year: %4d Employee: Department: %20s | Employee Number: %3d Faculty: %11s | Number of Courses Taught: %3d | Courses Taught: %s",
                        faculty.getName(),
                        faculty.getBirthYear(),
                        faculty.getDeptName(),
                        faculty.getEmployeeID(),
                        faculty.isTenured() ? "Is Tenured" : "Not Tenured",
                        faculty.getNumCoursesTaught(),
                        faculty.getAllCoursesTaughtAsString()
                ));
            } else {
                System.out.println(faculty.toString() + "\n\n");
            }
        }
        if(isPart1) {
            System.out.println("************************************************\n" +
                    "************************************************");
        }
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

    public static void generateStudentText(Student[] students, boolean isPart1) {
        System.out.println("STUDENTS:");
        for(Student student : students) {
            if(student == null) break;
            if(isPart1) {
                System.out.println(String.format(
                        "Person: Name: %30s | Birth Year: %4d Student: studentID: %4s | Major %20s | %14s | Number of Courses Taken: %3d | Courses Taken: %s",
                        student.getName(),
                        student.getBirthYear(),
                        "000" + student.getStudentID(),
                        student.getMajor(),
                        student.isGraduate() ? "Graduate" : "Undergraduate",
                        student.getNumCoursesTaken(),
                        student.getAllCoursesTakenAsString()
                ));
            } else {
                System.out.println(student.toString() + "\n\n");
            }
        }
    }
}