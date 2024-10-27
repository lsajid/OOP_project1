package com.ls;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class UserMenu {
    private Scanner scnr;
    private String userInput = "";


    UserMenu() {
        startUserMenu();
        this.scnr = new Scanner(System.in);
    }


    public void startUserMenu() {
        System.out.println("Loading... Connecting to database.....");
        System.out.println("Status: Success\n\n");
        System.out.println("Welcome to Lehman College Database");
    }

    public void closeUserScanner() {
        System.out.println("_______\nClosing port\nDisconnecting from database...");
        if(scnr != null) {
            scnr.close();
            System.out.println("Status: Success\n");
        }
    }

    public void addUserInputsToArray(String[] userInputItems) {
        while(!userInput.equals("q")) {
            System.out.println("Please enter item(s) to create (Enter 'q' to quit) see options below");
            System.out.println("_______\nOpt Course ---- isGraduate:boolean, int courseNum, String courseDept, int numCredits EXAMPLE: 'Course: true, 771, MAT, 4'");
            System.out.println("Opt GeneralStaff --- String name, int birthYear, String deptName, String duty ---- EXAMPLE: 'GeneralStaff: Batman,1934,Security,safety'");
            System.out.println("Opt Faculty --- String name, int birthYear, String deptName, boolean isTenured --- EXAMPLE: 'Faculty: Superman,1938,PHY,true'");
            System.out.println("Opt Student --- String name, int birthYear, String major, boolean isGraduate --- EXAMPLE: 'Student: WonderWoman,1941,JST,true'");
            System.out.println("listening for input : )\n...");
            userInput = scnr.nextLine();
            if(!userInput.equals("q")) {
                appendItemToUserInputs(userInput, userInputItems);
            }
        }
    }

    public void appendItemToUserInputs(String lineContent, String[] userInputItems) {
        for(int i = 0; i < userInputItems.length; i++) {
            if(userInputItems[i] == null) {
                userInputItems[i] = lineContent;
                break;
            }
        }
    }
}
