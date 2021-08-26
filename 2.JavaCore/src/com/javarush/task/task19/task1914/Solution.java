package com.javarush.task.task19.task1914;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.stream.Collectors;

/* 
Решаем пример
*/

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream defaultConsole = System.out;

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));
        testString.printSomething();
        System.setOut(defaultConsole);
        String[] result = byteArrayOutputStream.toString().split(" ");

        switch (result[1]) {
            case "+":
                int sum = Integer.parseInt(result[0]) + Integer.parseInt(result[2]);
                result[4] = String.valueOf(sum);
                break;
            case "-":
                int dif = Integer.parseInt(result[0]) - Integer.parseInt(result[2]);
                result[4] = String.valueOf(dif);
                break;
            case "*":
                int times = Integer.parseInt(result[0]) * Integer.parseInt(result[2]);
                result[4] = String.valueOf(times);
                break;
        }

        System.out.println(String.join(" ", result));

    }

    public static class TestString {
        public void printSomething() {
            System.out.println("3 + 6 = ");
        }
    }
}

