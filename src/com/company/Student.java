package com.company;

public class Student extends Human implements Comparable<Human> {
    private int course;
    private String mainSubject;
    private static String sortBy = "name";

    public Student(String name, String sex, int age, int course, String mainSubject) {
        super(name, sex, age);
        this.course = course;
        this.mainSubject = mainSubject;
    }

    public static void setSortBy(String direction) {
        sortBy = direction;
    }

    public int getCourse() {
        return course;
    }

    public String getMainSubject() {
        return mainSubject;
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public String getHumanInfo() {
        return "Student " + super.getHumanInfo() + " course: " + getCourse() + " subject " + getMainSubject() + "\n";
    }

    @Override
    public int compareTo(Human o) {
        if (o == null) {
            return -1;
        }
        Student anotherStudent = (Student) o;

        if (sortBy.equalsIgnoreCase("name")) {
            return this.getName().compareToIgnoreCase(anotherStudent.getName());
        }

        if (sortBy.equalsIgnoreCase("sex")) {
            return this.getSex().compareToIgnoreCase(anotherStudent.getSex());
        }

        if (sortBy.equalsIgnoreCase("course")) {
            return this.getCourse() - anotherStudent.getCourse();
        }

        if (sortBy.equalsIgnoreCase("age")) {
            return this.getAge() - anotherStudent.getAge();
        }

        if (sortBy.equalsIgnoreCase("subject") || sortBy.equalsIgnoreCase("mainSubject")) {

            return this.getMainSubject().compareToIgnoreCase(anotherStudent.getMainSubject());
        }

        return this.getName().compareToIgnoreCase(anotherStudent.getName());
    }

    public enum Sort {
        NAME,
        SEX,
        AGE,
        COURSE,
        SUBJECT;

        String by() {
            switch (this) {
                case NAME:
                    return "name";
                case SEX:
                    return "sex";
                case AGE:
                    return "age";
                case COURSE:
                    return "course";
                case SUBJECT:
                    return "subject";
                default:
                    throw new RuntimeException("Unknown sort type");
            }
        }

    }
}
