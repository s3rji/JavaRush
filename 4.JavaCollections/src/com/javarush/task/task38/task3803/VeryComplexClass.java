package com.javarush.task.task38.task3803;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/* 
Runtime исключения (unchecked exception)
*/

public class VeryComplexClass {
    public void methodThrowsClassCastException() {
        ArrayList list = new ArrayList();
        list.add(15);
        String s = (String) list.get(0);
    }

    public void methodThrowsNullPointerException() {
        String s = null;
        System.out.println(s.replace("a", "b"));
    }

    public static void main(String[] args) {
        new VeryComplexClass().methodThrowsClassCastException();
        new VeryComplexClass().methodThrowsNullPointerException();
    }
}
