package com.javarush.task.task36.task3602;

import java.util.Collections;

/* 
Найти класс по описанию Ӏ Java Collections: 6 уровень, 6 лекция
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(getExpectedClass());
    }

    public static Class getExpectedClass() {
        Class<?>[] classes = Collections.class.getDeclaredClasses();
        for (Class<?> cl : classes) {
            if (cl.getSimpleName().equals("EmptyList"))
                return cl;
        }

        return null;
    }
}
