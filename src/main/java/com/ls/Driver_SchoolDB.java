package com.ls;

import java.util.HashMap;
import java.util.Scanner;
import java.io.File;

public class Driver_SchoolDB {
    public static void main(String[] args) {
        //init hashMap to store lineContent and index
        HashMap<Integer, String> contentMap = new HashMap<>();

        //read file and store content in hashMap
        printAndStoreFileContent(true, contentMap);

        //print first part of stars
        generateMenu();

        //generate classes as specified in file
        generateFormattedContent(contentMap);
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

    public static void generateFormattedContent(HashMap<Integer, String> contentMap) {
        //
    }

    public String getClassType (String str) {
        return "";
    }

    public String[] getConstructorParams(String str) {
        String[] strArr = new String[1];
        return strArr;
    }

    public static void createClass(String classType, String constructorParams) {
        switch (classType) {
            case "Course":
                System.out.println("Create course");
                break;
            case "General Staff":
                System.out.println("");
                break;
            case "":

                break;
        }
    }
}