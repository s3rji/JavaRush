package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class University {
    private List<Student> students = new ArrayList<>();
    private String name;
    private int age;

    public University(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Student getStudentWithAverageGrade(double averageGrade) {
        for (Student student : students) {
            if (averageGrade == student.getAverageGrade())
                return student;
        }
        return null;
    }

    public Student getStudentWithMaxAverageGrade() {
        List<Student> sortedStudents = new ArrayList<>(students);
        sortedStudents.sort(Comparator.comparingDouble(Student::getAverageGrade));
        return sortedStudents.get(sortedStudents.size() -1);
    }

    public Student getStudentWithMinAverageGrade() {
        List<Student> sortedStudents = new ArrayList<>(students);
        sortedStudents.sort(Comparator.comparingDouble(Student::getAverageGrade));
        return sortedStudents.get(0);
    }

    public void expel(Student student) {
        students.remove(student);
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}