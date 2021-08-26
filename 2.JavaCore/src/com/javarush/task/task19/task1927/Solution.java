package com.javarush.task.task19.task1927;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/* 
Контекстная реклама
*/

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream defaultConsole = System.out;

        ByteArrayOutputStream console = new ByteArrayOutputStream();
        System.setOut(new PrintStream(console));

        testString.printSomething();

        System.setOut(defaultConsole);

        String[] splitedString = console.toString().split("\\n");
        for (int i = 0; i < splitedString.length; i++) {
            if (i > 0 && i % 2 == 0)
                System.out.println("JavaRush - курсы Java онлайн");
            System.out.println(splitedString[i]);
        }

    }

    public static class TestString {
        public void printSomething() {
            System.out.println("first");
            System.out.println("second");
            System.out.println("third");
            System.out.println("fourth");
            System.out.println("fifth");
        }
    }
}
