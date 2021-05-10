package com.company;

import com.company.exception.StudentGroupOverflowException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Group implements MilitaryCommissar {
    public static final int GROUP_CAPACITY = 10;
    public static final int AGE_ARMY = 18;
    private final ArrayList<Human> students;
    private int studentsInGroup;

    public Group(ArrayList<Human> students) {
        this.students = students;
    }

    public void addStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter sudent name: ");
        String studentName = scanner.nextLine();
        System.out.println("Enter student sex: ");
        String studentSex = scanner.nextLine();
        System.out.println("Enter student subject: ");
        String studentSubject = scanner.nextLine();
        System.out.println("Enter student age: ");
        int studentAge = scanner.nextInt();
        System.out.println("Enter student course: ");
        int studentCourse = scanner.nextInt();

        Student student = new Student(studentName, studentSex, studentAge, studentCourse, studentSubject);
        this.addStudent(student);

    }

    public void addStudent(Human student) {
        try {
            checkGroupCapacity();
            students.add(student);
            studentsInGroup++;
        } catch (StudentGroupOverflowException e) {
            System.out.printf("NOT added %s \n", student.getHumanInfo());
        }

    }

    public void removeStudent(Human student) {
        students.remove(student);
    }

    public void searchStudentBySurname(String surname) {
        boolean isFound = false;
        for (Human student : students) {
            if (student.getHumanInfo().contains(surname)) {
                isFound = true;
                break;
            }
        }

        String result = "user with surname %s is found \n";

        if (!isFound) {
            result = "user with surname %s is NOT found \n";
        }

        System.out.printf(result, surname);

    }

    public void sort(String sortBy) {
        Student.setSortBy(sortBy);

        int emptyPlaceGroupIndex = 0;

        for (Human student : students) {
            if (Objects.nonNull(student)) {
                emptyPlaceGroupIndex++;
            }
        }

        Arrays.sort(new ArrayList[]{students}, 0, emptyPlaceGroupIndex);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Group{ \n");
        for (Human human : students) {
            if (human != null) {
                builder.append(human.getHumanInfo());
            }
        }
        return builder.toString();
    }

    private boolean checkGroupCapacity() {
        if (studentsInGroup < GROUP_CAPACITY) {
            return true;
        }
        throw new StudentGroupOverflowException("Wrong amount of students. Only 10 students per group allowed");
    }

    @Override
    public Human[] getStudentsReadyForArmy() {
        int countStudents = 0;
        for (Human student : students) {
            if (Objects.nonNull(student) && student.getAge() > AGE_ARMY) {
                countStudents++;
            }
        }

        final Human[] studentsArmy = new Human[countStudents];

        for (int i = 0, j = 0; i < students.size(); i++) {
            if (Objects.nonNull(students.get(i)) && students.get(i).getAge() > AGE_ARMY) {
                studentsArmy[j] = students.get(i);
                j++;
            }
        }

        return studentsArmy;
    }
}
