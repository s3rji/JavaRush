package com.javarush.task.task38.task3802;

/* 
Проверяемые исключения (checked exception)
*/

import java.text.SimpleDateFormat;
import java.util.Date;

public class VeryComplexClass {
    public void veryComplexMethod() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println(sdf.parse("12.10.2021"));
    }

    public static void main(String[] args) throws Exception {
        new VeryComplexClass().veryComplexMethod();
    }
}
