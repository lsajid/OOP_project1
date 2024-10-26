package com.ls;

import java.util.Scanner;

public class UserMenu {
    private Scanner scnr;

    UserMenu() {}

    UserMenu(Scanner scnr) {
        startUserMenu(scnr);
    }

    public void startUserMenu(Scanner scnr) {
        System.out.println("Loading.....");
        this.scnr = scnr;
        System.out.println("Welcome to Lehman College Database");
    }

    public void closeUserScanner(Scanner scnr) {
        System.out.println("Close scanner");
        if(scnr != null) {
            scnr.close();
        }
    }
}
