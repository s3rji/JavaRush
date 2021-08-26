package com.javarush.task.task22.task2202;

/* 
Найти подстроку
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(getPartOfString("JavaRush - лучший сервис обучения Java."));
    }

    public static String getPartOfString(String string) {
        if (string == null) throw new TooShortStringException();

        String[] arrayString = string.split(" ");

        if (arrayString.length < 5) throw new TooShortStringException();

        String result = "";
        for (int i = 1; i < 5; i++) {
            result += arrayString[i] + " ";
        }

        return result.trim();
    }

    public static class TooShortStringException extends RuntimeException {
    }
}
