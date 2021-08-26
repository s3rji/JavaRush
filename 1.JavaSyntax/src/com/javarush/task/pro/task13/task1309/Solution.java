package com.javarush.task.pro.task13.task1309;

import java.util.HashMap;

/* 
Успеваемость студентов
*/

public class Solution {
    public static HashMap<String, Double> grades = new HashMap<>();

    public static void main(String[] args) {
        addStudents();
        System.out.println(grades);
    }

    public static void addStudents() {
        grades.put("Gusarov", 4.5);
        grades.put("Gusar", 4.5);
        grades.put("Gus", 4.5);
        grades.put("Gusarovvv", 4.5);
        grades.put("Gus.rov", 4.5);
    }
}
