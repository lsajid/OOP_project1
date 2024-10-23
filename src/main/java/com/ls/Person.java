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

    public String getName() {
        return name;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }

        if (this == object) {
            return true;
        }

        if (object instanceof Person) {
            Person otherPerson = (Person) object;
             if ((this.name == otherPerson.name) && (this.birthYear == otherPerson.birthYear)) {
                 return true;
             }
        }
        return false;
    }

    @Override
    public String toString () {
        String s = "";
        s = String.format(
                "Person: Name: %30s | Birth Year: %4d",
                name,
                birthYear
        );
        return s;
    }


    public int compareTo(Person otherPerson) {
        if (otherPerson == null) {return 0;}

        if (this.birthYear < otherPerson.birthYear) {
            return -1;
        } else if (this.birthYear > otherPerson.birthYear) {
            return 1;
        }
        return 0;
    }
}
