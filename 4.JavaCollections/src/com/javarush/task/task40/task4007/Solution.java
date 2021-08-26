package com.javarush.task.task40.task4007;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/* 
Работа с датами
*/

public class Solution {
    public static void main(String[] args) {
        printDate("21.4.2014 15:56:45");
        System.out.println();
        printDate("21.4.2014");
        System.out.println();
        printDate("11:33:40");
    }

    public static void printDate(String date) {
        SimpleDateFormat sdf;
        Calendar calendar = Calendar.getInstance();

        try {
            if (date.contains(".") && date.contains(":")) {
                sdf = new SimpleDateFormat("dd.M.yyyy hh:mm:ss");
                calendar.setTime(sdf.parse(date));
                printDate(calendar);
                printTime(calendar);
            } else if (date.contains(".")) {
                sdf = new SimpleDateFormat("dd.M.yyyy");
                calendar.setTime(sdf.parse(date));
                printDate(calendar);
            } else {
                sdf = new SimpleDateFormat("hh:mm:ss");
                calendar.setTime(sdf.parse(date));
                printTime(calendar);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    private static void printDate(Calendar calendar) {
        System.out.println("День: " + calendar.get(Calendar.DATE));
        System.out.println("День недели: " + (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY ? 7 : calendar.get(Calendar.DAY_OF_WEEK) - 1));
        System.out.println("День месяца: " + calendar.get(Calendar.DAY_OF_MONTH));
        System.out.println("День года: " + calendar.get(Calendar.DAY_OF_YEAR));
        System.out.println("Неделя месяца: " + calendar.get(Calendar.WEEK_OF_MONTH));
        System.out.println("Неделя года: " + calendar.get(Calendar.WEEK_OF_YEAR));
        System.out.println("Месяц: " + (calendar.get(Calendar.MONTH) + 1));
        System.out.println("Год: " + calendar.get(Calendar.YEAR));
    }

    private static void printTime(Calendar calendar) {
        System.out.println("AM или PM: " + (calendar.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM"));
        System.out.println("Часы: " + calendar.get(Calendar.HOUR));
        System.out.println("Часы дня: " + calendar.get(Calendar.HOUR_OF_DAY));
        System.out.println("Минуты: " + calendar.get(Calendar.MINUTE));
        System.out.println("Секунды: " + calendar.get(Calendar.SECOND));
    }
}
