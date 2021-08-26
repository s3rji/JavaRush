package com.javarush.task.task40.task4008;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalField;
import java.util.Calendar;

/* 
Работа с Java 8 DateTime API
*/

public class Solution {
    public static void main(String[] args) {
        printDate("9.10.2017 5:56:45");
        System.out.println();
        printDate("21.4.2014");
        System.out.println();
        printDate("17:33:40");
    }

    public static void printDate(String date) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d.M.y H:m:s");
        boolean printDate = false;
        boolean printTime = false;

        if (date.contains(".")) printDate = true;
        if (date.contains(":")) printTime = true;

        if (printDate && !printTime) {
            dtf = DateTimeFormatter.ofPattern("d.M.y");
        }

        if (!printDate && printTime) {
            dtf = DateTimeFormatter.ofPattern("H:m:s");
        }

        if (printDate) {
            LocalDate localDate = LocalDate.parse(date, dtf);
            printDate(localDate);
        }

        if (printTime) {
            LocalTime localTime = LocalTime.parse(date, dtf);
            printTime(localTime);
        }

    }

    private static void printDate(LocalDate localDate) {
        System.out.println("День: " + localDate.get(ChronoField.DAY_OF_MONTH));
        System.out.println("День недели: " + localDate.get(ChronoField.DAY_OF_WEEK));
        System.out.println("День месяца: " + localDate.get(ChronoField.DAY_OF_MONTH));
        System.out.println("День года: " + localDate.get(ChronoField.DAY_OF_YEAR));
        System.out.println("Неделя месяца: " + localDate.format(DateTimeFormatter.ofPattern("W")));
        System.out.println("Неделя года: " + localDate.format(DateTimeFormatter.ofPattern("w")));
        System.out.println("Месяц: " + localDate.get(ChronoField.MONTH_OF_YEAR));
        System.out.println("Год: " + localDate.get(ChronoField.YEAR));
    }

    private static void printTime(LocalTime localTime) {
        System.out.println("AM или PM: " + localTime.format(DateTimeFormatter.ofPattern("a")));
        System.out.println("Часы: " + localTime.format(DateTimeFormatter.ofPattern("K")));
        System.out.println("Часы дня: " + localTime.get(ChronoField.HOUR_OF_DAY));
        System.out.println("Минуты: " + localTime.get(ChronoField.MINUTE_OF_HOUR));
        System.out.println("Секунды: " + localTime.get(ChronoField.SECOND_OF_MINUTE));
    }
}
