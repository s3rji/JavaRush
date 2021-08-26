package com.javarush.task.task17.task1711;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* 
CRUD 2
*/

public class Solution {
    public static volatile List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static final SimpleDateFormat formatterIn = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    public static final SimpleDateFormat formatterOut = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

    public static void main(String[] args) throws Exception {
        if (args == null || args.length < 1)
            throw new IllegalArgumentException();

        Person person;
        Date date;

        switch (args[0]) {
            case "-c":
                for (int i = 1; i < args.length; i += 3) {
                    String name = args[i];
                    date = formatterIn.parse(args[i + 2]);
                    person = args[i + 1].equals("м") ? Person.createMale(name, date) : Person.createFemale(name, date);
                    synchronized (allPeople) {
                        allPeople.add(person);
                        System.out.println(allPeople.size() - 1);
                    }
                }
                break;

            case "-u":
                for (int i = 1; i < args.length; i += 4) {
                    int index = Integer.parseInt(args[i]);
                    String name = args[i + 1];
                    date = formatterIn.parse(args[i + 3]);
                    person = args[i + 2].equals("м") ? Person.createMale(name, date) : Person.createFemale(name, date);
                    synchronized (allPeople) {
                        allPeople.set(index, person);
                    }
                }
                break;

            case "-d":
                for (int i = 1; i < args.length; i++) {
                    int index = Integer.parseInt(args[i]);
                    synchronized (allPeople) {
                        person = allPeople.get(index);
                        person.setName(null);
                        person.setSex(null);
                        person.setBirthDate(null);
                    }
                }
                break;

            case "-i":
                for (int i = 1; i < args.length; i++) {
                    int index = Integer.parseInt(args[i]);
                    synchronized (allPeople) {
                        person = allPeople.get(index);
                        String sex = person.getSex() == Sex.MALE ? "м" : "ж";
                        String dateOut = formatterOut.format(person.getBirthDate());
                        System.out.println(person.getName() + " " + sex + " " + dateOut);
                    }
                }
                break;
        }

    }
}
