package com.javarush.task.task25.task2512;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/* 
Живем своим умом
*/

public class Solution implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        t.interrupt();

        List<String> exceptionMessages = new ArrayList<>();
        exceptionMessages.add(e.toString());

        while (e.getCause() != null) {
            e = e.getCause();
            exceptionMessages.add(0, e.toString());
        }

        for (String message : exceptionMessages) {
            System.out.println(message);
        }
    }

    public static void main(String[] args) {
        new Solution().uncaughtException(Thread.currentThread(), new Exception("ABC", new RuntimeException("DEF", new IllegalAccessException("GHI"))));
    }
}
