package com.ls;

public class Person {
    private String name;
    private int birthYear;

    public Person() {
        this.name = "";
        this.birthYear = 0;
    }

    //
    public Person(String name, int birthYear) {
        this.name = name;
        this.birthYear = birthYear;
    }

    public String getName(String name) {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }

    @Override
    public String toString () {
        return "";
    }

    public int compareTo(Person p) {
        return -1;
    }
}
