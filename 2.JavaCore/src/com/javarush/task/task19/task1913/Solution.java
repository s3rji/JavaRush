package com.javarush.task.task19.task1913;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.SQLOutput;

/* 
Выводим только цифры
*/

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream console = System.out;

        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArray);

        System.setOut(printStream);

        testString.printSomething();

        String replacedString = byteArray.toString().replaceAll("\\D", "");

        System.setOut(console);

        System.out.println(replacedString);
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("it's 1 a 23 text 4 f5-6or7 tes8ting");
        }
    }
}
