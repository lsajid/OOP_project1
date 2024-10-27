package com.ls;

import javax.sound.midi.Soundbank;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
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

        //init String for content
        String content = "";

        //read file and store content in hashMap
        printAndStoreFileContent(true, contentMap, content);

        //print first part of stars
        content += generateMenuOrFooter("menu");

        //generate classes as specified in file
        generateClassesFromMap(contentMap, courses, generalStaffs, faculties, students);

        //functions to generate text from stored arrays
        content += generateCourseText(courses, true);
        content += generatePersonText(people);
        content += generateEmployeeText(employees);
        content += generateGeneralStaffText(generalStaffs, true);
        content += generateFacultyText(faculties, true);
        content += generateStudentText(students, true);

        content += generateMenuOrFooter("footer");

        //PART 2 ************************************************************************************************

        //create new instance of userMenu
        UserMenu userMenu = new UserMenu();
        String[] userInputItems = new String[100];

        //listen to userStream for inputs
        userMenu.addUserInputsToArray(userInputItems);

        //generate all items for class
        generateAndPrintAllItemsFromUserInputArray(userInputItems, courses, generalStaffs, faculties, students);

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
        Course[] coursesToAdd = new Course[] {
                new Course(true, 252, "CMP", 4),
                new Course(true, 168, "CMP", 4),
                new Course(false, 167, "CMP", 4),
                new Course(true, 382, "CHE", 4)
        };
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


        String input = "";
        Faculty facultyToCheck = null;
        Course courseToCheck = null;
        Scanner scnr = new Scanner(System.in);
        System.out.println("Please view Faculty and course from our menu");
        generateFacultyText(faculties, true);
        System.out.println("Arrays.toString(faculties)" + Arrays.toString(faculties));
        generateCourseText(courses, true);
        while(!input.equals("q")) {
            System.out.println("Enter faculty Employee Number of interest (Enter q to quit)");
            input = scnr.nextLine();
            if(!input.equals("q")) {
                facultyToCheck = getFacultyByEmployeeNumber(input, faculties);
                System.out.println("Enter Course Course Number of interest");
                String courseNumber = scnr.nextLine();
                courseToCheck = getCourseByCourseNumber(courseNumber, courses);

                if(facultyToCheck.doesFacultyTeachSelectedCourse(courseToCheck)){
                    System.out.println("Faculty: " + facultyToCheck.getName() + "has taught course " + courseToCheck.toString() + " Please use start over");
                } else{
                    System.out.println("Faculty: " + facultyToCheck.getName() + "has not taught course " + courseToCheck.toString() + " Please use start over");
                }
            }
        }
        System.out.println("Closing scanner...");
        if(scnr != null) {
            scnr.close();
        }

        System.out.println("\n\n(1 pts)Determine which Faculty object teaches the most and the least courses.");
        Faculty maxFaculty = getMaxFaculty(faculties);
        Faculty minFaculty = getMinFaculty(faculties);
        System.out.println("Faculty who taught the most courses: "+ maxFaculty);
        System.out.println("Faculty who taught the least courses: "+ minFaculty);

        System.out.println("\n\n(1 pts) Determine which Course is the minimum of all Course objects in the catalog.");
        Course minCourseFromCatalogue = getMinCourseFromCatalogue(courses);
        System.out.println("Course with minimum number: " + minCourseFromCatalogue);

        System.out.println("\n\n(1 pts) Determine which Course is the maximum of all Course objects in the catalog");
        Course maxCourseFromCatalogue = getMaxCourseFromCatalogue(courses);
        System.out.println("Course with maximum number: " + maxCourseFromCatalogue);

        System.out.println("\n\n(1 pts) Determine which Student has the most and least credits.");
        Student studentWithMaxCredits = getStudentWithMaxCredits(students);
        Student studentWithMinCredits = getStudentWithMinCredits(students);
        System.out.println("Student with the most credits: "+ studentWithMaxCredits);
        System.out.println("Student with the least credits: "+ studentWithMinCredits);

        //print all items using.toString()
        content += generateCourseText(courses, false);
        content += generateGeneralStaffText(generalStaffs, false);
        content += generateFacultyText(faculties, false);
        content += generateStudentText(students, false);

        //write to new file
        writeToNewFile("/Users/laibasajid/Desktop/project1/src/main/java/com/ls/generatedText.txt",content);

    }

    public static void writeToNewFile(String fileName, String content){
        PrintWriter outStream = null;//DECLARE
        try {
            outStream = new PrintWriter(fileName);//INITIALIZE... CREATE...
            outStream.println(content);//write to the file
            outStream.flush();
        } catch (FileNotFoundException e) {
            //CATCH EXCEPTION
            System.out.println("PROBLEM WRITE TO NEW FILE "+fileName);
            e.printStackTrace();
        }finally{
            if(outStream != null){
                outStream.close();
            }
            System.out.println("Done TRYING TO WRITE TO NEW FILE: "+fileName);
        }
    }

    public static Student getStudentWithMaxCredits(Student[] students) {
        int max = students[0].getTotalNumberOfCredits();

        for (Student student : students) {
            if(student == null) break;
            max = Math.max(max, student.getTotalNumberOfCredits());
        }

        for(Student student : students) {
            if(student == null) break;
            if(student.getTotalNumberOfCredits() == max) {
                return student;
            }
        }
        return null;
    }

    public static Student getStudentWithMinCredits(Student[] students) {
        int min = students[0].getTotalNumberOfCredits();

        for (Student student : students) {
            if(student == null) break;
            min = Math.min(min, student.getTotalNumberOfCredits());
        }

        for(Student student : students) {
            if(student == null) break;
            if(student.getTotalNumberOfCredits() == min) {
                return student;
            }
        }
        return null;
    }

    public static Course getMaxCourseFromCatalogue(Course[] courses) {
        int max = courses[0].getCourseNum();

        for (Course course : courses) {
            if(course == null) break;
            max = Math.max(max, course.getCourseNum());
        }

        for(Course course : courses) {
            if(course == null) break;
            if(course.getCourseNum() == max) {
                return course;
            }
        }
        return null;
    }

    public static Course getMinCourseFromCatalogue(Course[] courses) {
        int min = courses[0].getCourseNum();

        for (Course course : courses) {
            if(course == null) break;
            min = Math.min(min, course.getCourseNum());
        }

        for(Course course : courses) {
            if(course == null) break;
            if(course.getCourseNum() == min) {
                return course;
            }
        }
        return null;
    }

    public static Faculty getMaxFaculty(Faculty[] faculties) {
        int max = 0;

        for (Faculty faculty : faculties) {
            if(faculty == null) break;
            max = Math.max(max, faculty.getNumCoursesTaught());
        }

        for(Faculty faculty : faculties) {
            if(faculty == null) break;
            if(faculty.getNumCoursesTaught() == max) {
                return faculty;
            }
        }
        return null;
    }

    public static Faculty getMinFaculty(Faculty[] faculties) {
        int min = 0;

        for (Faculty faculty : faculties) {
            if(faculty == null) break;
            min = Math.min(min, faculty.getNumCoursesTaught());
        }

        for(Faculty faculty : faculties) {
            if(faculty == null) break;
            if(faculty.getNumCoursesTaught() == min) {
                return faculty;
            }
        }
        return null;
    }

    public static Course getCourseByCourseNumber(String courseNumber, Course[] courses) {
        int item = Integer.parseInt(courseNumber);
        for(Course course : courses) {
            if(course.getCourseNum() == item) {
                return course;
            }
        }
        return null;
    }


    public static Faculty getFacultyByEmployeeNumber(String employeeNum, Faculty[] faculties) {
        int item = Integer.parseInt(employeeNum);
        for(Faculty faculty : faculties) {
            if(faculty.getEmployeeID() == item) {
                return faculty;
            }
        }
        return null;
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

    public static void printAndStoreFileContent(boolean isLocal, HashMap<Integer, String> contentMap, String content) {
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
                System.out.println("I AM TRYING TO ADD CONTENT");
                content += lineInFile;
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

    public static String generateMenuOrFooter(String option) {
        if(option.equals("menu")){
            System.out.println("**************************************************************");
            System.out.println("SCHOOL DATABASE INFO:");
            System.out.println();
            System.out.println("************************************************");
            return "**************************************************************\nSCHOOL DATABASE INFO:\n************************************************";
        } else if(option.equals("footer")) {
            System.out.println("************************************************\n" +
                    "**************************************************************\n");
            return "************************************************\n**************************************************************\n";
        }
        return "ERROR: MUST ENTER OPTION";
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

    public static String generateCourseText(Course[] courses, boolean isPart1) {
        String s = "COURSES:";
        System.out.println("COURSES:");
        for (Course course : courses) {
            if(course == null) break;
            s += course.toString();
            System.out.println(course.toString());
        }
        if(isPart1) {
            s += "************************************************\n" +
                    "************************************************";
            System.out.println("************************************************\n" +
                    "************************************************");
        }
        return s;
    }

    public static String generatePersonText(Person[] people) {
        String s = "PERSONS:";
        System.out.println("PERSONS:");
        for (Person person : people) {
            if(person == null) break;
            s += person.toString();
            System.out.println(person.toString());
        }
        s += "************************************************\n" +
                "************************************************";
        System.out.println("************************************************\n" +
                "************************************************");
        return s;
    }

    public static String generateEmployeeText(Employee[] employees) {
        String s = "EMPLOYEES:";
        System.out.println("EMPLOYEES:");
        for (Employee employee : employees) {
            if(employee == null) break;
            s += employee.toString();
            System.out.println(employee.toString());
        }
        s += "************************************************\n" +
                "************************************************";
        System.out.println("************************************************\n" +
                "************************************************");
        return s;
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

    public static String generateGeneralStaffText(GeneralStaff[] generalStaffs, boolean isPart1) {
        String s = "GENERAL STAFF:";
        System.out.println("GENERAL STAFF:");
        for (GeneralStaff generalStaff : generalStaffs) {
            if(generalStaff == null) break;
            if(isPart1) {
                s += String.format(
                        "Person: Name: %30s | Birth Year: %4d Employee: Department: %20s | Employee Number: %3d GeneralStaff: Duty: %10s",
                        generalStaff.getName(),
                        generalStaff.getBirthYear(),
                        generalStaff.getDeptName(),
                        generalStaff.getEmployeeID(),
                        generalStaff.getDuty()
                );
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
            s += "************************************************\n" +
                    "************************************************";
            System.out.println("************************************************\n" +
                    "************************************************");
        }
        return s;
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

    public static String generateFacultyText(Faculty[] faculties, boolean isPart1){
        String s = "FACULTY:";
        System.out.println("FACULTY:");
        for(Faculty faculty : faculties) {
            if(faculty == null) break;
            if(isPart1) {
                s += String.format(
                        "Person: Name: %30s | Birth Year: %4d Employee: Department: %20s | Employee Number: %3d Faculty: %11s | Number of Courses Taught: %3d | Courses Taught: %s",
                        faculty.getName(),
                        faculty.getBirthYear(),
                        faculty.getDeptName(),
                        faculty.getEmployeeID(),
                        faculty.isTenured() ? "Is Tenured" : "Not Tenured",
                        faculty.getNumCoursesTaught(),
                        faculty.getAllCoursesTaughtAsString()
                );
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
            s += "************************************************\n" +
                    "************************************************";
            System.out.println("************************************************\n" +
                    "************************************************");
        }
        return s;
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

    public static String generateStudentText(Student[] students, boolean isPart1) {
        String s = "STUDENTS:";
        System.out.println("STUDENTS:");
        for(Student student : students) {
            if(student == null) break;
            if(isPart1) {
                s += String.format(
                        "Person: Name: %30s | Birth Year: %4d Student: studentID: %4s | Major %20s | %14s | Number of Courses Taken: %3d | Courses Taken: %s",
                        student.getName(),
                        student.getBirthYear(),
                        "000" + student.getStudentID(),
                        student.getMajor(),
                        student.isGraduate() ? "Graduate" : "Undergraduate",
                        student.getNumCoursesTaken(),
                        student.getAllCoursesTakenAsString()
                );
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
        return s;
    }

}